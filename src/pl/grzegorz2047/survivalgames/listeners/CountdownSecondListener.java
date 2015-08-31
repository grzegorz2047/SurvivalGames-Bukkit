package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.events.CountdownSecondEvent;
import pl.grzegorz2047.survivalgames.scoreboard.ScoreboardUtil;
import pl.grzegorz2047.survivalgames.stats.ServerStats;
import pl.grzegorz2047.survivalgames.utils.TimeUtil;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class CountdownSecondListener implements Listener {

    private SurvivalGames sg;
    private ServerStats stats;

    public CountdownSecondListener(SurvivalGames sg) {
        this.sg = sg;
        stats = new ServerStats(sg);
    }


    @EventHandler
    void onEverySecond(CountdownSecondEvent e) {
        if (sg.getGame().isInGame()) {
            if (sg.getGame().isProtection()) {
                sg.getGame().setProtectionTime(sg.getGame().getProtectionTime() - 1);
                if(sg.getGame().getProtectionTime() <= 0){
                    sg.getGame().setProtection(false);
                    Bukkit.broadcastMessage(MsgManager.msg("Ochrona przed graczami zostala wylaczona!"));
                }

            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                ScoreboardUtil sb = new ScoreboardUtil(p, false);
                sb.setDisplayName(TimeUtil.formatHHMMSS(e.getCurrentTime()) + sb.getMinigamePrefix() + stats.getMinMaxPlayers());
                sb.setScore(sb.getObjective(),sb.getScAlive(),stats.getActivePlayers());
            }
        } else {
            Bukkit.broadcastMessage(MsgManager.msg("Arena wystartuje za " + e.getCurrentTime() + ".."));
            for (Player p : Bukkit.getOnlinePlayers()) {
                ScoreboardUtil sb = new ScoreboardUtil(p, false);
                sb.setDisplayName(TimeUtil.formatHHMMSS(e.getCurrentTime()) + sb.getMinigamePrefix() + stats.getMinMaxPlayers());
            }
        }
    }
}
