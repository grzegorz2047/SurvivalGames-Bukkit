package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 01.09.2015.
 */
public class PlayerManipulateListeners implements Listener {

    private SurvivalGames sg;

    public PlayerManipulateListeners(SurvivalGames sg){
        this.sg = sg;
    }


    @EventHandler
         void onPlayerMoveItem(InventoryInteractEvent e){
        if(!(e.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player) e.getWhoClicked();
        if (sg.getGameManager().getPlayers().get(p.getName()).isSpectator()) {
            e.setCancelled(true);
        }

    }
    @EventHandler
    void onPlayerMoveItem(InventoryDragEvent e){
        if(!(e.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player) e.getWhoClicked();
        if (sg.getGameManager().getPlayers().get(p.getName()).isSpectator()) {
            e.setCancelled(true);
        }

    }
    @EventHandler
    void onPlayerMoveItem(InventoryClickEvent e){
        if(!(e.getWhoClicked() instanceof Player)){
            return;
        }
        Player p = (Player) e.getWhoClicked();
        if (sg.getGameManager().getPlayers().get(p.getName()).isSpectator()) {
            e.setCancelled(true);
        }

    }
}
