package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.grzegorz2047.survivalgames.Game;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.events.CounterEndEvent;
import pl.grzegorz2047.survivalgames.runnable.Counter;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class CounterEndListener implements Listener {

    SurvivalGames sg;
    public CounterEndListener(SurvivalGames sg){
        this.sg = sg;
    }

    Game g;

    @EventHandler
    void onCounterEnd(CounterEndEvent e) {
        g = sg.getGame();
        MsgManager.debug("Timer zostal zakonczony podczas " + g.getGameState() + ", dm = " + g.isDeathMatch());
        if(g.getGameState().equals(Game.GameState.STARTING)){
            g.setGameState(Game.GameState.INGAME);
            Counter counter = new Counter(sg,g.getMainTime());
            counter.start();
            Bukkit.broadcastMessage(MsgManager.msg("Zostala wlaczona ochrona przed graczami na 30 sekund!"));
        }
        else if(g.isInGame()){
            if(g.isDeathMatch()){
                g.end();
            }
            else if(!g.isDeathMatch()){
                Counter counter = new Counter(sg,g.getDmTime());
                counter.start();
                g.setDeathMatch(true);
                g.startDeatchMatch();

            }
        }

    }
}
