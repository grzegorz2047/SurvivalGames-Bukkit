package pl.grzegorz2047.survivalgames.WorldController;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.MsgManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by Grzegorz2047. 29.08.2015.
 */
public class WorldManager {

    public void load(String worldName) throws IOException {
        File from = new File("Mapy"+File.separator+worldName);
        MsgManager.debug("From " + from.getAbsolutePath());

        File to = new File(worldName);

        if (to.exists()) {
            to.delete();
        }

        FileUtils.copyDirectory(from, to);
        new File(to, "uid.dat").delete();
        new File(to, "session.lock").delete();

        WorldCreator creator = new WorldCreator(worldName);
        creator.environment(World.Environment.NORMAL);
        creator.generateStructures(false);
        creator.generator(new AirGenerator());
        creator.type(WorldType.FLAT);

        World world = Bukkit.getServer().createWorld(creator);
        world.setAutoSave(false);
        world.setDifficulty(Bukkit.getWorlds().get(0).getDifficulty());
        world.setPVP(true);
    }

    public void unloadWorld(String worldName) {
        if (Bukkit.getOnlinePlayers().length > 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.kickPlayer(MsgManager.msg("Serwer restartuje sie!"));
            }
        }
        Bukkit.unloadWorld(Bukkit.getWorld(worldName), false);

    }

}
