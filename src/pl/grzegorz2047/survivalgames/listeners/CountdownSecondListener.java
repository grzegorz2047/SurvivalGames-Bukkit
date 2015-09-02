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
        if (sg.getGameManager().isInGame()) {
            if (sg.getGameManager().isProtection()) {
                sg.getGameManager().setProtectionTime(sg.getGameManager().getProtectionTime() - 1);
                if(sg.getGameManager().getProtectionTime() <= 0){
                    sg.getGameManager().setProtection(false);
                    Bukkit.broadcastMessage(MsgManager.msg("Ochrona przed graczami zostala wylaczona!"));
                }

            }
            if(!sg.getGameManager().isDeathMatch()){
                if(e.getCurrentTime()<=5){
                    Bukkit.broadcastMessage(MsgManager.msg("DeathMatch rozpocznie sie za "+e.getCurrentTime()+".."));
                }
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                ScoreboardUtil sb = new ScoreboardUtil(p, false);
                sb.setDisplayName(TimeUtil.formatHHMMSS(e.getCurrentTime()) + sb.getMinigamePrefix() + stats.getMinMaxPlayers(false));
                sb.setScore(sb.getObjective(),sb.getScAlive(),stats.getActivePlayers());
            }
        } else {
            Bukkit.broadcastMessage(MsgManager.msg("Arena wystartuje za " + e.getCurrentTime() + ".."));
            for (Player p : Bukkit.getOnlinePlayers()) {
                ScoreboardUtil sb = new ScoreboardUtil(p, false);
                sb.setDisplayName(TimeUtil.formatHHMMSS(e.getCurrentTime()) + sb.getMinigamePrefix() + stats.getMinMaxPlayers(false));
            }
        }
    }
}
