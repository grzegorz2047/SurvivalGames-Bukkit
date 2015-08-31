package pl.grzegorz2047.survivalgames.spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.files.YmlFileHandler;
import pl.grzegorz2047.survivalgames.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class Spawn {


    private Location spectatorLoc;
    private List<SpawnPoint> spawnPoints = new ArrayList<SpawnPoint>();

    private SurvivalGames sg;

    public Spawn(SurvivalGames sg) {
        this.sg = sg;
        this.loadSpawnFromFile(sg.getMapfileHandler());
        this.loadSpecLocFromFile(sg.getMapfileHandler());
    }

    public Location getSpectatorLoc() {
        return spectatorLoc;
    }

    public void setSpectatorLoc(Location spectatorLoc) {
        this.spectatorLoc = spectatorLoc;
    }

    public void addSpawnPoint(SpawnPoint spawnPoint) {
        this.spawnPoints.add(spawnPoint);
    }

    public List<SpawnPoint> getSpawnPoints() {
        return this.spawnPoints;
    }

    private SpawnPoint getFreeSpawnPoint() {
        for (SpawnPoint sp : this.spawnPoints) {
            if (sp.isFree()) {
                return sp;
            }
        }
        return null;
    }

    private SpawnPoint getPlayerSpawnPoint(String username) {
        for (SpawnPoint sp : this.spawnPoints) {
            if (sp.
                    getOccupiedBy().
                    equals(username)) {
                return sp;
            }
        }
        return null;
    }

    public void placePlayer(User user) {
        SpawnPoint sp = this.getFreeSpawnPoint();
        if (sp != null) {
            user.getPlayer().teleport(sp.getLocation());
            sp.setFree(false, user.getUsername());
        }
    }

    public void displacePlayer(User user) {//set Free spawnpoint
        SpawnPoint sp = this.getPlayerSpawnPoint(user.getUsername());
        if (sp != null) {
            user.getPlayer().teleport(sp.getLocation());
            sp.setFree(true, null);
        }
    }

    public void saveSpawnToFile(YmlFileHandler file) {

        file.getConfig().set("numOfSpawns", this.spawnPoints.size());

        StringBuilder sb;
        int index = 0;
        for (SpawnPoint sp : this.spawnPoints) {
            sb = new StringBuilder();
            sb.append(sp.getX()).append(':').append(sp.getY()).append(':').append(sp.getZ()).append(':').append(sp.getPitch()).append(':').append(sp.getYaw()).append(':').append(sp.getWorldName());
            String cords = sb.toString();
            file.getConfig().set("spawns." + index, cords);
            index++;
        }
        file.save();
    }

    public void loadSpawnFromFile(YmlFileHandler file) {
        int numOfSpawns = file.getConfig().getInt("numOfSpawns");
        MsgManager.debug("Liczba SpawnPointow " + numOfSpawns + " do wczytania");
        if (numOfSpawns != 0) {
            for (int i = 0; i < numOfSpawns; i++) {
                String spawnPointString = file.getConfig().getString("spawns."+i);
                String[] spArray = spawnPointString.split(":");
                double x = Double.parseDouble(spArray[0]);
                double y = Double.parseDouble(spArray[1]);
                double z = Double.parseDouble(spArray[2]);
                double pitch = Double.parseDouble(spArray[3]);
                double yaw = Double.parseDouble(spArray[4]);
                String worldName = spArray[5];

                SpawnPoint sp = new SpawnPoint(x,y,z,pitch,yaw,worldName);
                this.addSpawnPoint(sp);

            }
        }
    }
    public void loadSpecLocFromFile(YmlFileHandler file) {
        String spawnPointString = file.getConfig().getString("spawns.spectator");
        if (spawnPointString != null) {
            String[] spArray = spawnPointString.split(":");
            double x = Double.parseDouble(spArray[0]);
            double y = Double.parseDouble(spArray[1]);
            double z = Double.parseDouble(spArray[2]);
            double pitch = Double.parseDouble(spArray[3]);
            double yaw = Double.parseDouble(spArray[4]);
            String worldName = spArray[5];

            this.spectatorLoc = new Location(Bukkit.getWorld(worldName),x,y,z);
            MsgManager.debug("Spawn spectatora wczytany!");
        }else{
            MsgManager.debug("Spawn spectatora nie istnieje!");
        }
    }

}
