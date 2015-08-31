
package pl.grzegorz2047.survivalgames;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.grzegorz2047.survivalgames.commands.SGCommand;
import pl.grzegorz2047.survivalgames.files.YmlFileHandler;
import pl.grzegorz2047.survivalgames.listeners.*;
import pl.grzegorz2047.survivalgames.utils.GhostUtil;

public class SurvivalGames extends JavaPlugin {
    SurvivalGames sg;
    Game game;
    YmlFileHandler mapfileHandler;
    GhostUtil ghostUtil;


    /*                                                      */
    public static boolean debugMode = true;
    /*                                                      */



    public YmlFileHandler getMapfileHandler() {
        return mapfileHandler;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        System.out.println(this.getName() + " zostal wylaczony!");

    }

    public void onEnable() {
        this.sg = this;
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        this.ghostUtil = new GhostUtil(this);
        Bukkit.getWorlds().get(0).setAutoSave(false);
        mapfileHandler = new YmlFileHandler(sg, this.getDataFolder().getAbsolutePath(),"TestMap");
        mapfileHandler.load();

        this.initManagers();
        this.registerListeners();
        this.getCommands();
        System.out.println(this.getName() + " zostal wlaczony!");
    }

    public void getCommands() {
        this.getCommand("sg").setExecutor(new SGCommand(this.sg));
    }

    public void registerListeners() {
        PluginManager pl = Bukkit.getPluginManager();
        pl.registerEvents(new JoinListener(sg), this);
        pl.registerEvents(new PlayerQuitListener(sg), this);
        pl.registerEvents(new CounterEndListener(sg), this);
        pl.registerEvents(new PlayerMoveListener(sg), this);
        pl.registerEvents(new PlayerDamageListener(sg), this);
        pl.registerEvents(new PlayerDeathListener(sg), this);
        pl.registerEvents(new BlockBreakListener(sg), this);
        pl.registerEvents(new BlockPlaceListener(sg),this);
        pl.registerEvents(new EntityExplodeListener(sg), this);
        pl.registerEvents(new CountdownSecondListener(sg),this);
        pl.registerEvents(new ForCompassListeners(sg),this);
        pl.registerEvents(new PlayerMoveListener(sg), this);
    }

    public void initManagers() {
        game = new Game(sg);
    }

    public Game getGame() {
        return game;
    }
}
