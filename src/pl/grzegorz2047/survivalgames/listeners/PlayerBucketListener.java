package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 01.09.2015.
 */
public class PlayerBucketListener implements Listener {

    private SurvivalGames sg;

    public PlayerBucketListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onEmptyBucket(PlayerBucketEmptyEvent e) {
        Player p = e.getPlayer();
        if (sg.getGameManager().getPlayers().get(p.getName()).isSpectator()) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    void onEmptyBucket(PlayerBucketFillEvent e) {
        Player p = e.getPlayer();
        if (sg.getGameManager().getPlayers().get(p.getName()).isSpectator()) {
            e.setCancelled(true);
        }
    }
}
