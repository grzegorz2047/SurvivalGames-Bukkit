package pl.grzegorz2047.survivalgames.listeners;

import dram.CoinsMod.CoinsMod;
import dram.rankmod.main.RankMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.permission.Permission;
import pl.grzegorz2047.survivalgames.scoreboard.ScoreboardUtil;
import pl.grzegorz2047.survivalgames.user.User;
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
        Player k = e.getEntity().getKiller();
        if (k != null && !k.equals(player)) {//If there is a killer

            User userKiller = sg.getGameManager().getPlayers().get(k.getName());
            userKiller.setKills(userKiller.getKills() + 1);
            ScoreboardUtil sb = new ScoreboardUtil(k, false);
            sb.setScore(sb.getObjective(), sb.getScKills(), userKiller.getKills());
            Bukkit.broadcastMessage(MsgManager.msg("Gracz " + ChatColor.RED + k.getName() + ChatColor.GRAY + " zabil gracza " + ChatColor.RED + e.getEntity().getName()));
            CoinsMod.ChangePlayerMoneyWOMultiplier(k, sg.getGameManager().getMoneyForKills(), true);
            if (player.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
                RankMain.addPlayerKill(k, RankMain.killsType.bowKill);
            } else {
                RankMain.addPlayerKill(k, RankMain.killsType.normalKill);
            }
            RankMain.addPlayerXp(k.getName(), sg.getGameManager().getExpForKills());
        } else {
            Bukkit.broadcastMessage(MsgManager.msg("Gracz " + e.getEntity().getName() + " zginal!"));
        }
        //RankMain.addPlayerXp(player.getName(), sg.getGameManager().getExpForDeath());//Player who died receive minus exp
        RankMain.addPlayerXp(player, RankMain.xpKeys.dead);
        sg.getGameManager().getPlayers().get(player.getName()).setSpectator(true);
        sg.getGameManager().getStats().updateStats();//update stats
        if (!sg.getServer().getPlayer(player.getName()).isOnline()) {
            return;
        }

        if (player.hasPermission(Permission.PERMISSIONS_VIP) || sg.isDebugMode()) {
            player.setHealth(player.getMaxHealth());
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setHealth(player.getMaxHealth());
                    player.getInventory().setArmorContents(null);
                    player.getInventory().clear();
                    sg.
                            getGameManager().
                            getGhostUtil().
                            addPlayer(player);
                    player.sendMessage(MsgManager.msg("Aby opuscic rozgrywke wpisz /sg leave"));
                }
            }.runTaskLater(sg, 1L);

        } else {
            player.sendMessage(MsgManager.msg("Chcesz ogladac arene? Zakup range VIP w http://www.craftgames.pl/sklep"));
            BungeeUtil.changeServer(sg, player, BungeeUtil.lobbyServer);

        }

        Iterator<ItemStack> i = e.getDrops().iterator();
        while (i.hasNext()) {
            ItemStack item = i.next();
            if (item.getType() == Material.COMPASS) {
                i.remove();
            }
        }
        int activePlayers = sg.getGameManager().getStats().getActivePlayers();
        MsgManager.debug("Ilosc aktywnych graczy przy playerdeathevent " + activePlayers);
        if (activePlayers == 1) {
            sg.getGameManager().end(activePlayers);
        }
    }
}
