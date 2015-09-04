
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
        if(p == null){
            return;
        }

        e.setQuitMessage(null);
        sg.
                getGameManager().
                removePlayer(p);
        Bukkit.broadcastMessage(MsgManager.msg(p.getName() + " opuscil serwer!"));
        if (!sg.getGameManager().isInGame()) {
            sg.getGameManager().checkRequirementToStop();
        }
        sg.getGameManager().getStats().updateStats();
        int activePlayers = sg.getGameManager().getStats().getActivePlayers();
        MsgManager.debug("Ilosc aktywnych graczy przy playerquitevent "+activePlayers);
        if(activePlayers == 1 && sg.getGameManager().isInGame()){
            sg.getGameManager().end(activePlayers);
        }
    }
}
