package pl.grzegorz2047.survivalgames.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import pl.grzegorz2047.survivalgames.MsgManager;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class PlayerLoginListener implements Listener {

    @EventHandler
    void onPlayerLogin(PlayerLoginEvent e){
        if(!e.getPlayer().hasPermission(Permission.PERMISSIONS_VIP)){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, MsgManager.msg(ChatColor.RED+"Nie mozesz obserwowac bez rangi VIP!"));
        }
    }

}
