package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 04.09.2015.
 */
public class PlayerTeleportTrackingListeners implements Listener {

    private SurvivalGames sg;

    public PlayerTeleportTrackingListeners(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onTp(PlayerTeleportEvent e){
        if(e.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)){
            e.setCancelled(true);
        }
    }

}
