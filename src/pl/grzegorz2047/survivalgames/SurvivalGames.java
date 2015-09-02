
package pl.grzegorz2047.survivalgames;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.grzegorz2047.survivalgames.commands.SGCommand;
import pl.grzegorz2047.survivalgames.listeners.*;
import pl.grzegorz2047.survivalgames.utils.GhostUtil;

public class SurvivalGames extends JavaPlugin {
    SurvivalGames sg;
    GameManager gameManager;

    GhostUtil ghostUtil;


    /*                                                      */
    public static boolean debugMode = true;
    /*                                                      */


    public boolean isDebugMode() {
        return debugMode;
    }

    public void onEnable() {
        this.sg = this;
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        this.initManagers();
        this.registerListeners();
        this.getCommands();
        System.out.println(this.getName() + " zostal wlaczony!");
    }

    public void onDisable() {
        this.clearMemory();
        System.out.println(this.getName() + " zostal wylaczony!");

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
        pl.registerEvents(new BlockPlaceListener(sg), this);
        pl.registerEvents(new EntityExplodeListener(sg), this);
        pl.registerEvents(new CountdownSecondListener(sg), this);
        pl.registerEvents(new ForCompassListeners(sg), this);
        pl.registerEvents(new PlayerMoveListener(sg), this);
        pl.registerEvents(new PlayerLoginListener(sg), this);
        pl.registerEvents(new PlayerChatListeners(sg), this);
        pl.registerEvents(new PlayerManipulateListeners(sg), this);
        pl.registerEvents(new PlayerPickDropListener(sg), this);
        pl.registerEvents(new PlayerBucketListener(sg), this);
    }

    public void initManagers() {
        gameManager = new GameManager(this);
        this.ghostUtil = new GhostUtil(this);
    }

    public void clearMemory(){
        Bukkit.getScheduler().cancelTasks(sg);
        this.gameManager = null;
        this.ghostUtil = null;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void resetPlugin() {
        clearMemory();
        initManagers();
    }


}
