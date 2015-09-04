
package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;

public class PlayerQuitListener implements Listener {

    SurvivalGames sg;

    public PlayerQuitListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage(null);
        sg.
                getGameManager().
                removePlayer(p);
        Bukkit.broadcastMessage(MsgManager.msg(p.getName() + " opuscil serwer!"));
        if (!sg.getGameManager().isInGame()) {
            sg.getGameManager().checkRequirementToStop();
        }
    }
}
