package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import pl.grzegorz2047.survivalgames.SurvivalGames;

import java.util.List;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class AntiBlockListener implements Listener {
    private SurvivalGames sg;

    public AntiBlockListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent ev) {
        Player p = ev.getPlayer();
        List<Entity> entities = p.getNearbyEntities(5D, 10D, 5D);

        for (Entity entity : entities) {
            if (entity.getType().equals(EntityType.PLAYER)) {
                Player player = (Player) entity;
                if (p.hasPermission(Permission.PERMISSIONS_VIP)) {
                    return;
                }
                if (p.hasPotionEffect(PotionEffectType.INVISIBILITY) && !player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                    Vector vector = ev.getFrom().toVector().subtract(player.getLocation().toVector());
                    Vector vector_;
                    if (Math.abs(vector.getX()) > Math.abs(vector.getZ())) {
                        vector_ = new Vector(vector.getX() > 0 ? 1 : -1, 1, 0);
                    } else {
                        vector_ = new Vector(0, 1, vector.getZ() > 0 ? 1 : -1);
                    }
                    p.setVelocity(vector_);
                }
            }
        }
    }
}

