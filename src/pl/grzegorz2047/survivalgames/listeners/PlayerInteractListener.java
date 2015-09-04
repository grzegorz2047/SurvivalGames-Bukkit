package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;

import java.util.List;

/**
 * Created by Grzegorz2047. 03.09.2015.
 */
public class PlayerInteractListener implements Listener {

    private SurvivalGames sg;

    public PlayerInteractListener(SurvivalGames sg) {
        this.sg = sg;

    }


    @EventHandler
    void onPlayerInteract(PlayerInteractEvent e) {
        if(sg.getGameManager().getPlayers().get(e.getPlayer().getName()).isSpectator()){
            e.setCancelled(true);
        }
        if (sg.getGameManager().getChestManager().isEditMode()){
            if(e.getClickedBlock() != null){
                Block b = e.getClickedBlock();
                MsgManager.debug("Cos klinalem "+b.getType()+" przez "+e.getPlayer().getName());
                Location loc = b.getLocation();
                List<Location> chests = this.sg.getGameManager().getChestManager().getChests();
                List<Location> doubleChests = this.sg.getGameManager().getChestManager().getDoubleChests();

                if(b.getState() instanceof Chest){
                    MsgManager.debug("Skrzynka!");
                    if(!chests.contains(loc)){
                        chests.add(loc);
                        e.getPlayer().sendMessage(MsgManager.msg(ChatColor.GREEN + "Skrzynka zostala dodana!"));
                    }else{
                        e.getPlayer().sendMessage(MsgManager.msg(ChatColor.RED+"Ta skrzynka zostala juz dodana!"));
                    }
                }else if(b.getState() instanceof DoubleChest){
                    MsgManager.debug("double Skrzynka!");
                    if(!doubleChests.contains(loc)){
                        doubleChests.add(loc);
                        e.getPlayer().sendMessage(MsgManager.msg(ChatColor.GREEN + "Skrzynka zostala dodana!"));
                    }else{
                        e.getPlayer().sendMessage(MsgManager.msg(ChatColor.RED+"Ta skrzynka zostala juz dodana!"));
                    }
                }
            }
        }
    }


}
