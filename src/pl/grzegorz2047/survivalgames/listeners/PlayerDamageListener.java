package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.event.Listener;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 29.08.2015.
 */
public class PlayerDamageListener implements Listener {


    private final SurvivalGames sg;

    public PlayerDamageListener(SurvivalGames sg) {
        this.sg = sg;
    }
}
