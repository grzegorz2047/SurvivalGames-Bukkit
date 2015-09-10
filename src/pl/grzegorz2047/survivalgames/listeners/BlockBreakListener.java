package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class BlockBreakListener implements Listener {


    private SurvivalGames sg;
    Material[] materials = {Material.MELON_BLOCK, Material.WATER_LILY, Material.MELON, Material.DOUBLE_PLANT, Material.LEAVES, Material.LEAVES_2, Material.VINE, Material.LONG_GRASS, Material.YELLOW_FLOWER, Material.RED_ROSE, Material.BROWN_MUSHROOM, Material.RED_MUSHROOM};
    private List<Material> exceptionMaterialList = new ArrayList<Material>();

    public BlockBreakListener(SurvivalGames sg) {
        this.sg = sg;
        this.exceptionMaterialList = Arrays.asList(materials);
    }


    @EventHandler
    void onBlockBreak(BlockBreakEvent e) {
        if (!sg.getGameManager().isInGame()) {
            e.setCancelled(true);
        }
        if (sg.getGameManager().getPlayers().get(e.getPlayer().getName()).isSpectator()) {
            e.setCancelled(true);
        }
        if (!this.exceptionMaterialList.contains(e.getBlock().getType())) {
            e.setCancelled(true);
        }

    }
}
