
package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.grzegorz2047.survivalgames.Game;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.runnable.Counter;
import pl.grzegorz2047.survivalgames.scoreboard.ScoreboardUtil;
import pl.grzegorz2047.survivalgames.spawn.SpawnPoint;
import pl.grzegorz2047.survivalgames.user.User;
import pl.grzegorz2047.survivalgames.utils.TimeUtil;

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

        e.setJoinMessage(null);
        Bukkit.broadcastMessage(MsgManager.msg(p.getDisplayName() + " dolaczyl do areny!"));
        User user = sg.getGame().addPlayer(p);
        ScoreboardUtil sc = new ScoreboardUtil(p, true);
        sc.setScore(sc.getObjective(), sc.getScMoney(), user.getMoney());
        sc.setScore(sc.getObjective(), sc.getScKills(), user.getKills());
        sc.setDisplayName(TimeUtil.formatHHMMSS(0)+sc.getMinigamePrefix()+sg.getGame().getStats().getMinMaxPlayers());

    }
}
