
package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.grzegorz2047.survivalgames.Game;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.runnable.Counter;

public class JoinListener implements Listener {

    SurvivalGames sg;
    public JoinListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Bukkit.broadcastMessage(p.getDisplayName() + " dolaczyl do areny!");


    }
}
