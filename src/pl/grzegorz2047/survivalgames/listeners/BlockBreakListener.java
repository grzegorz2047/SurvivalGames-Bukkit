package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class BlockBreakListener implements Listener {


    private SurvivalGames sg;

    public BlockBreakListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onBlockBreak(BlockBreakEvent e) {
        e.setCancelled(true);
    }
}
