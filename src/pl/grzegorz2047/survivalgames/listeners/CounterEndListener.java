package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.grzegorz2047.survivalgames.GameManager;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.events.CounterEndEvent;
import pl.grzegorz2047.survivalgames.runnable.Counter;
import pl.neksi.craftgames.game.ArenaStatus;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class CounterEndListener implements Listener {

    SurvivalGames sg;

    public CounterEndListener(SurvivalGames sg) {
        this.sg = sg;
    }

    GameManager g;

    @EventHandler
    void onCounterEnd(CounterEndEvent e) {
        g = sg.getGameManager();
        MsgManager.debug("Timer zostal zakonczony podczas " + g.getGameState() + ", dm = " + g.isDeathMatch());
        if (g.getGameState().equals(GameManager.GameState.STARTING)) {
            g.setGameState(GameManager.GameState.INGAME);
            Counter counter = new Counter(sg, g.getMainTime());
            counter.start();
            ArenaStatus.setStatus(ArenaStatus.Status.INGAME);
            Bukkit.broadcastMessage(MsgManager.msg("Zostala wlaczona ochrona przed graczami na 30 sekund!"));
            Bukkit.broadcastMessage(MsgManager.msg(ChatColor.GREEN+"Niewidzialnosc zostala wlaczona dla rangi VIP!"));
        } else if (g.isInGame()) {
            if (g.isDeathMatch()) {//If there was a deathmatch
                g.end(g.getStats().getActivePlayers());//end this
            } else if (!g.isDeathMatch()) {
                if(g.getStats().getActivePlayers()== 1){//if force start was fired then turn it off
                    g.end(g.getStats().getActivePlayers());
                    MsgManager.debug("Gra zostala wymuszona z 1 graczem, wiec koncze gre!");
                }
                MsgManager.debug("Uruchamiam dm!");
                Counter counter = new Counter(sg, g.getDmTime());
                counter.start();
                g.startDeathMatch();

            }
        }

    }
}
