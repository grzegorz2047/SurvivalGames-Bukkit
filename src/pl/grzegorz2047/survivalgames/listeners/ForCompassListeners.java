package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.utils.GhostUtil;
import pl.grzegorz2047.survivalgames.utils.IconMenuUtil;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class ForCompassListeners implements Listener {
    private final SurvivalGames sg;
    private IconMenuUtil spectatorCompass;

    public ForCompassListeners(SurvivalGames sg) {
        this.sg = sg;
    }

    public IconMenuUtil initCompass() {
        int size = 9;
        int arenaPlayer = sg.getGameManager().getStats().getActivePlayers();

        if (arenaPlayer > 27) {
            size = 54;
        }
        if (arenaPlayer < 27) {
            size = 27;
        }
        if (arenaPlayer < 18) {
            size = 18;
        }
        if (arenaPlayer < 9) {
            size = 9;
        }

        spectatorCompass = new IconMenuUtil("Teleport do gracza", size, new IconMenuUtil.OptionClickEventHandler() {
            @Override
            public void onOptionClick(IconMenuUtil.OptionClickEvent event) {
                Player p = event.getPlayer();
                String nameItem = "";
                if (event.getName().toLowerCase() != " ") {
                    p.teleport(Bukkit.getPlayer(event.getName()));
                }
                event.setWillClose(true);
                event.setWillDestroy(true);
            }
        }, sg);

        int j = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!GhostUtil.ghosts.contains(p.getName())) {
                j++;
                spectatorCompass.setOption(j, new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal()), p.getName());
            }
        }

        for (int i = 0; i < spectatorCompass.getSize(); i++) {
            if (spectatorCompass.getItem(i) == null) {
                spectatorCompass.setOption(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData()), " ");
            }
        }

        return spectatorCompass;
    }

    @EventHandler
    public void onOpenCompass(PlayerInteractEvent ev) {
        if (GhostUtil.ghosts.contains(ev.getPlayer().getName())) {
            if (ev.getItem() != null) {
                if (ev.getItem().equals(GhostUtil.spectatorCompass)) {
                    initCompass();
                    spectatorCompass.open(ev.getPlayer());
                }
            }
        }
    }
}