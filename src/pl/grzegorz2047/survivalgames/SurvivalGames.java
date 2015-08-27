
package pl.grzegorz2047.survivalgames;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.grzegorz2047.survivalgames.commands.SGCommand;
import pl.grzegorz2047.survivalgames.listeners.JoinListener;
import pl.grzegorz2047.survivalgames.listeners.QuitListener;

public class SurvivalGames extends JavaPlugin {
    SurvivalGames sg = this;

    public SurvivalGames() {

    }

    public void onDisable() {
        System.out.println(this.getName() + " zostal wylaczony!");
    }

    public void onEnable() {
        this.registerListeners();
        this.getCommands();
        System.out.println(this.getName() + " zostal wlaczony!");
    }

    public void getCommands() {
        this.getCommand("sg").setExecutor(new SGCommand(this.sg));
    }

    public void registerListeners() {
        PluginManager pl = Bukkit.getPluginManager();
        pl.registerEvents(new JoinListener(), this);
        pl.registerEvents(new QuitListener(), this);
    }
}
