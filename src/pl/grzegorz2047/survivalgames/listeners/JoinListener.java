
package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.grzegorz2047.survivalgames.Game;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.runnable.Counter;
import pl.grzegorz2047.survivalgames.spawn.SpawnPoint;

public class JoinListener implements Listener {

    SurvivalGames sg;

    public JoinListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
        //TODO Make sure that event is firing for PLAYER not for null! scheduler?
    void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(p == null){
            return;
        }
        Bukkit.broadcastMessage(p.getDisplayName() + " dolaczyl do areny!");
        sg.getGame().addPlayer(p);

    }
}
