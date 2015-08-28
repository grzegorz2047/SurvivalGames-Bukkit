
package pl.grzegorz2047.survivalgames;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.grzegorz2047.survivalgames.commands.SGCommand;
import pl.grzegorz2047.survivalgames.listeners.CounterEndListener;
import pl.grzegorz2047.survivalgames.listeners.JoinListener;
import pl.grzegorz2047.survivalgames.listeners.PlayerMoveListener;
import pl.grzegorz2047.survivalgames.listeners.QuitListener;

public class SurvivalGames extends JavaPlugin {
    SurvivalGames sg = this;
    Game game;

    /*                                                      */
    private static boolean debugMode = true;
    /*                                                      */

    public static void debug(String Msg){
        if(debugMode){
            Bukkit.broadcastMessage(Msg);
        }
    }

    public SurvivalGames() {

    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        System.out.println(this.getName() + " zostal wylaczony!");
    }

    public void onEnable() {
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
        pl.registerEvents(new QuitListener(sg), this);
        pl.registerEvents(new CounterEndListener(sg), this);
        pl.registerEvents(new PlayerMoveListener(sg), this);
    }

    public void initManagers() {
        game = new Game(sg);
    }

    public Game getGame() {
        return game;
    }
}
