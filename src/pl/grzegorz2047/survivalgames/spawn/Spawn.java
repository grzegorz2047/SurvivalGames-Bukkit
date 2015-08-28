package pl.grzegorz2047.survivalgames.spawn;

import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class Spawn {

    private List<SpawnPoint> spawnPoints = new ArrayList<SpawnPoint>();

    private SurvivalGames sg;

    public Spawn(SurvivalGames sg) {
        this.sg = sg;
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
            if (sp.getOccupiedBy().equals(username)) {
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

    public void displacePlayer(User user) {//Free spawnpoint
        SpawnPoint sp = this.getPlayerSpawnPoint(user.getUsername());
        if (sp != null) {
            user.getPlayer().teleport(sp.getLocation());
            sp.setFree(true, null);
        }
    }


}
