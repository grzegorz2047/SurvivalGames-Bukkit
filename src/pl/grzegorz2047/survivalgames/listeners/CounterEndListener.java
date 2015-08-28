package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pl.grzegorz2047.survivalgames.Game;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.events.CounterEndEvent;

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
        Bukkit.broadcastMessage("Timer zostal zakonczony przy  stanie "+ g.getGameState());
    }
}
