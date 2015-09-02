package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 01.09.2015.
 */
public class PlayerPickDropListener implements Listener {

    private SurvivalGames sg;

    public PlayerPickDropListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onPlayerPickup(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        if(sg.getGameManager().getPlayers().get(p.getName()).isSpectator()){
            e.setCancelled(true);
        }
    }
    @EventHandler
    void onPlayerPickup(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(sg.getGameManager().getPlayers().get(p.getName()).isSpectator()){
            e.setCancelled(true);
        }
    }
}
