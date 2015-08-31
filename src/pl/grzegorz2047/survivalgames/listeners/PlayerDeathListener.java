package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.utils.BungeeUtil;

import java.util.Iterator;

/**
 * Created by Grzegorz2047. 29.08.2015.
 */
public class PlayerDeathListener implements Listener {


    private final SurvivalGames sg;

    public PlayerDeathListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(final PlayerDeathEvent e) {
        final Player player = e.getEntity();
        sg.getGame().getPlayers().get(player.getName()).setSpectator(true);
        if (!sg.getServer().getPlayer(player.getName()).isOnline()) {
            return;
        }

        if (player.hasPermission(Permission.PERMISSIONS_VIP)) {
            player.setHealth(player.getMaxHealth());
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setHealth(player.getMaxHealth());

                    sg.
                            getGame().
                            getGhostUtil().
                            addPlayer(player);
                }
            }.runTaskLater(sg, 1L);

        } else {
            player.sendMessage(MsgManager.msg(ChatColor.GREEN + "Chcesz ogladac arene? Zakup range VIP!"));
            BungeeUtil.changeServer(sg, player, BungeeUtil.lobbyServer);

        }

        Iterator<ItemStack> i = e.getDrops().iterator();
        while (i.hasNext()) {
            ItemStack item = i.next();
            if (item.getType() == Material.COMPASS) {
                i.remove();
            }
        }
    }
}
