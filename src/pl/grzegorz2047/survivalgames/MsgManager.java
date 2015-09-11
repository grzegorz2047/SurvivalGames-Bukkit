package pl.grzegorz2047.survivalgames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * Created by Grzegorz2047. 30.08.2015.
 */
public class MsgManager {

    private static String prefix = ChatColor.translateAlternateColorCodes('&', "&7[&cSG&7] ");

    public static String msg(String msg) {
        String message = prefix + msg;

        return message;
    }

    public static void debug(String msg){
        if(SurvivalGames.debugMode){
            Bukkit.broadcastMessage(msg);
            //Bukkit.getLogger().log(Level.WARNING,msg);
        }
    }

    public String getPrefix() {
        return prefix;
    }

}
