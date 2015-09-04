package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 04.09.2015.
 */
public class LightningStrikeListeners implements Listener {

    private SurvivalGames sg;

    public LightningStrikeListeners(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onFireIgnite(BlockIgniteEvent e){
        if(e.getCause().equals(BlockIgniteEvent.IgniteCause.LIGHTNING)){
            e.setCancelled(true);
        }
    }

}
