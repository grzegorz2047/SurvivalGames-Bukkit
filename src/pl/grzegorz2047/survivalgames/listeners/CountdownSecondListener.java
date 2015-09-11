package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.events.CountdownSecondEvent;
import pl.grzegorz2047.survivalgames.permission.Permission;
import pl.grzegorz2047.survivalgames.scoreboard.ScoreboardUtil;
import pl.grzegorz2047.survivalgames.stats.ServerStats;
import pl.grzegorz2047.survivalgames.user.User;
import pl.grzegorz2047.survivalgames.utils.TimeUtil;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class CountdownSecondListener implements Listener {

    private SurvivalGames sg;
    private ServerStats stats;
    private boolean notified = false;
    public CountdownSecondListener(SurvivalGames sg) {
        this.sg = sg;
        stats = new ServerStats(sg);
    }

    private boolean refill = false;
    @EventHandler
    void onEverySecond(CountdownSecondEvent e) {
        this.stats.updateStats();
        if (sg.getGameManager().isInGame()) {
            if (sg.getGameManager().isProtection()) {
                if (e.getCurrentTime() == sg.getGameManager().getMainTime() - 1) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.hasPermission(Permission.PERMISSIONS_VIP)) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20 * 30, 1));// 30 sekundowa niewidzialnosc dla vipa
                        }
                    }

                }
                sg.getGameManager().setProtectionTime(sg.getGameManager().getProtectionTime() - 1);
                if (sg.getGameManager().getProtectionTime() <= 0) {
                    sg.getGameManager().setProtection(false);
                    Bukkit.broadcastMessage(MsgManager.msg("Ochrona przed graczami zostala wylaczona!"));
                }

            }

            if (!sg.getGameManager().isDeathMatch()) {//Odliczanie przed dm
                if(e.getCurrentTime() <= 60*5 && !refill){
                    refill = true;
                    sg.getGameManager().getChestManager().fillChests();
                    Bukkit.broadcastMessage(MsgManager.msg("Skrzynki zostaly uzupelnione!"));
                }
                if (e.getCurrentTime() <= 5) {
                    Bukkit.broadcastMessage(MsgManager.msg("DeathMatch rozpocznie sie za " + e.getCurrentTime() + "..."));
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        ScoreboardUtil sb = new ScoreboardUtil(p, false);
                        sb.setDisplayName(TimeUtil.formatHHMMSS(e.getCurrentTime()) + sb.getMinigamePrefix() + stats.getMinMaxPlayers(false));
                        sb.setScore(sb.getObjective(), sb.getScAlive(), stats.getActivePlayers());
                        sb.setScore(sb.getObjective(), sb.getScSpect(), stats.getSpectatingPlayers());
                        p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    }

                } else {
                    //MsgManager.debug("Updatuje scoreobard podczas dma > 5 sekund");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        ScoreboardUtil sb = new ScoreboardUtil(p, false);
                        sb.setDisplayName(TimeUtil.formatHHMMSS(e.getCurrentTime()) + sb.getMinigamePrefix() + stats.getMinMaxPlayers(false));
                        sb.setScore(sb.getObjective(), sb.getScAlive(), stats.getActivePlayers());
                        sb.setScore(sb.getObjective(), sb.getScSpect(), stats.getSpectatingPlayers());
                    }
                }
                if(sg.getGameManager().getStats().getActivePlayers() == 3  &&e.getCurrentTime() > 30){
                    e.setCurrentTime(10);
                }

            } else {

                for (Player p : Bukkit.getOnlinePlayers()) {
                    User user = sg.getGameManager().getPlayers().get(p.getName());
                    if (!user.isSpectator()) {
                        if(e.getCurrentTime()<30){
                            if(!notified){
                                notified= true;
                                Bukkit.broadcastMessage(MsgManager.msg("Wszyscy gracze sa zmeczeni i traca punkty zdrowia!"));
                            }
                            p.damage(1);
                        }
                        if (e.getCurrentTime() % 5 == 0) {
                            p.getWorld().strikeLightning(p.getLocation());//During dm strike
                        }
                    }

                    ScoreboardUtil sb = new ScoreboardUtil(p, false);
                    sb.setDisplayName(TimeUtil.formatHHMMSS(e.getCurrentTime()) + sb.getMinigamePrefix() + stats.getMinMaxPlayers(false));
                    sb.setScore(sb.getObjective(), sb.getScAlive(), stats.getActivePlayers());
                    sb.setScore(sb.getObjective(), sb.getScSpect(), stats.getSpectatingPlayers());
                }
            }

        } else {
            if(e.getCurrentTime() % 5 == 0){
                Bukkit.broadcastMessage(MsgManager.msg("Arena wystartuje za " + e.getCurrentTime() + "..."));
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                ScoreboardUtil sb = new ScoreboardUtil(p, false);
                sb.setDisplayName(TimeUtil.formatHHMMSS(e.getCurrentTime()) + sb.getMinigamePrefix() + stats.getMinMaxPlayers(false));
                sb.setScore(sb.getObjective(), sb.getScAlive(), stats.getActivePlayers());
                sb.setScore(sb.getObjective(), sb.getScSpect(), stats.getSpectatingPlayers());
            }
        }
    }

}
