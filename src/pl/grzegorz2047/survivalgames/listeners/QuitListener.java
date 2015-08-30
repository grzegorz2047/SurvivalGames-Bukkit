
package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

public class QuitListener implements Listener {

    SurvivalGames sg;
    public QuitListener(SurvivalGames sg) {
    this.sg = sg;
    }

    @EventHandler
    void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage(null);
        sg.getGame().removePlayer(p);
        Bukkit.broadcastMessage(p.getDisplayName() + " opuscil serwer!");
    }
}
