package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class EntityExplodeListener implements Listener {

    private SurvivalGames sg;

    public EntityExplodeListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onEntityExplode(EntityExplodeEvent e){
        e.blockList().clear();//Block block damage
    }
}
