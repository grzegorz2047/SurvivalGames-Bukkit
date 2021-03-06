
package pl.grzegorz2047.survivalgames.listeners;

import dram.rankmod.main.RankMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.grzegorz2047.survivalgames.GameManager;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.scoreboard.ScoreboardUtil;
import pl.grzegorz2047.survivalgames.utils.TimeUtil;
import pl.neksi.craftgames.game.ArenaStatus;

public class PlayerQuitListener implements Listener {

    SurvivalGames sg;

    public PlayerQuitListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p == null || sg.isRestarting()) {
            return;
        }

        e.setQuitMessage(null);

        if (sg.getGameManager().isInGame()) {
            if (!sg.getGameManager().getPlayers().get(p.getName()).isSpectator()) {
                RankMain.addPlayerXp(p.getName(), sg.getGameManager().getExpForleaving());
                p.sendMessage(MsgManager.msg("Otrzymales ujemne punkty exp za opuszczenie rozgrywki! (" + sg.getGameManager().getExpForleaving() + ")"));
            }
        }
        sg.
                getGameManager().
                removePlayer(p);
        Bukkit.broadcastMessage(MsgManager.msg(p.getName() + " opuscil serwer!"));
        sg.getGameManager().getStats().updateStats();
        int activePlayers = sg.getGameManager().getStats().getActivePlayers();
        ArenaStatus.setPlayers(activePlayers);
        MsgManager.debug("Ilosc aktywnych graczy przy playerquitevent " + activePlayers);
        if (!sg.getGameManager().isInGame()) {
            sg.getGameManager().checkRequirementToStop();
            ScoreboardUtil sc;
            if (!sg.getGameManager().getGameState().equals(GameManager.GameState.STARTING)) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    sc = new ScoreboardUtil(player, false);
                    sc.setDisplayName(TimeUtil.formatHHMMSS(0) + sc.getMinigamePrefix() + sg.getGameManager().getStats().getMinMaxPlayers(false));
                }
            }

        }
        if (activePlayers == 1 && sg.getGameManager().isInGame()) {
            sg.getGameManager().end(activePlayers);
        }
    }
}
