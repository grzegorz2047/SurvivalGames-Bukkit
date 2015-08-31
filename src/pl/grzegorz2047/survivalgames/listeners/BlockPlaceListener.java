package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class BlockPlaceListener implements Listener {

    private SurvivalGames sg;

    public BlockPlaceListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onBlockPlace(BlockPlaceEvent e) {
        e.setCancelled(true);
    }

}
