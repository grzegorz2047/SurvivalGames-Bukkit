package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.permission.Permission;

/**
 * Created by Grzegorz2047. 01.09.2015.
 */
public class PlayerChatListeners implements Listener {

    SurvivalGames sg;
    public PlayerChatListeners(SurvivalGames sg) {
        this.sg = sg;
    }


    @EventHandler
    void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(sg.getGameManager().getPlayers().get(p.getName()).isSpectator()){
            if(!p.isOp() && !p.hasPermission(Permission.PERMISSIONS_EKIPA)){
                e.setCancelled(true);
            }

        }
    }

}
