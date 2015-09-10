package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import pl.grzegorz2047.survivalgames.MsgManager;
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
        if(sg.getGameManager().isDeathMatch()){
            if(e.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)){
                if(sg.getGameManager().getSpawnManager().getSpectatorLoc().distance(e.getTo()) > sg.getGameManager().getDmPlayersMaxDistance()){
                    e.getPlayer().sendMessage(MsgManager.msg("Nie mozna teleportowac sie poza obszar death matchu!"));
                    e.setCancelled(true);
                }

            }
        }

    }

}
