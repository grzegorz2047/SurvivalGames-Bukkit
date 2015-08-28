package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class PlayerMoveListener implements Listener {

    SurvivalGames sg;

    public PlayerMoveListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent e) {
        if (!sg.getGame().isInGame() && !sg.isDebugMode()){
            e.setTo(e.getFrom());
        }
    }
}
