package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 29.08.2015.
 */
public class PlayerDamageListener implements Listener {


    private final SurvivalGames sg;

    public PlayerDamageListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    public void entityDamege(EntityDamageByEntityEvent e) {
        if(!sg.getGame().isInGame() || sg.getGame().isProtection()){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void entityDamege(EntityDamageEvent e) {
        if(!sg.getGame().isInGame() || sg.getGame().isProtection()){
            e.setCancelled(true);
        }
    }

/*
    @EventHandler
    public void entityDamege(EntityDamageByEntityEvent ev) {
        if(sg.getGame().isInGame()){
            final Player dmger;
            if (ev.getDamager() instanceof Player) {
                dmger = (Player) ev.getDamager();
            } else if (ev.getDamager() instanceof Projectile && ((Projectile) ev.getDamager()).getShooter() instanceof Player) {
                dmger = (Player) ((Projectile) ev.getDamager()).getShooter();
            } else {
                dmger = null;
            }
            if (ev.getEntityType() != EntityType.PLAYER) {
                return;
            }

            Player entity = (Player) ev.getEntity();

            if (!sg.players.contains(dmger.getName())) {
                ev.setDamage(0);
                ev.setCancelled(true);
                return;
            }

            if (dmger != null) {
                if (dmger == entity) {
                    return;
                }
            }
            entity.setLastDamageCause(ev);
            if (sg.getServer().getPlayer(dmger.getName()).isOnline()) { //null
                sg.getLastHit().put(entity.getName(), dmger);
                sg.getLastHitTime().put(entity.getName(), System.currentTimeMillis());
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player p = (Player) event.getEntity();
        if (!sg.players.contains(p.getName())) {
            event.setCancelled(true);
            return;
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }
*/

/*
    private void deathMessage(Player ev) {
        Player player = sg.getLastHit().get(ev.getName()) == null ? null : sg.getLastHit().get(ev.getName()); //killer
        Long hitTime = sg.getLastHitTime().get(ev.getName()) == null ? 0 : sg.getLastHitTime().get(ev.getName());
        int killCount = player == null ? 0 : (sg.getPlayerKills().get(player.getName()) == null ? 0 : sg.getPlayerKills().get(player.getName()));

        if (player != null) {
            if (System.currentTimeMillis() - hitTime < 10000) { // 10 sekund
                sg.broadcast(sg.getPREFIX() + ev.getDisplayName() + ChatColor.DARK_AQUA + " zostal zabity przez " + player.getDisplayName());
                sg.broadcast(sg.getPREFIX() + ChatColor.DARK_AQUA + "Pozostalo " + ChatColor.YELLOW + sg.players.size() + ChatColor.DARK_AQUA + " graczy");
                player.playSound(player.getLocation(), Sound.PIG_DEATH, 1F, 1F);
                FireworksUtil.randomFirework(player.getLocation());

                //-DB CASE
                PlayerManager pm = sg.getPlayerDB().get(player.getName());
                pm.addKills(1);
                pm.addCoins(sg.getCoins(player, 4));
                player.sendMessage(ChatColor.GOLD + "Otrzymujesz +" + sg.getCoins(player, 4) + " monet!");
            }
        } else {
            sg.broadcast(sg.getPREFIX() + ChatColor.DARK_AQUA + "Gracz " + ev.getDisplayName() + ChatColor.DARK_AQUA + " zginal!");
        }

        if (player != null) {
            sg.getPlayerKills().put(player.getName(), killCount + 1);
        }
    }*/
}
