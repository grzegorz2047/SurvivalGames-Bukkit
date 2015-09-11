package pl.grzegorz2047.survivalgames.utils;

/**
 * Created by Grzegorz2047. 11.09.2015.
 */

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.user.User;

/**
 * @author Grzegorz
 */
public class NearestPlayerCompassUtil implements Runnable {

    private SurvivalGames sg;

    public NearestPlayerCompassUtil(SurvivalGames sg) {
        this.sg = sg;
    }

    @Override
    public void run() {
        if (sg.getGameManager().getStats().getActivePlayers() >= 2) {
            updateCompases((User[]) sg.getGameManager().getActivePlayers().toArray());
        }

    }

    public static void updateCompases(User[] players) {
        for (int i = 0; i < players.length; i++) {
            Location l = players[i].getPlayer().getLocation();
            double shortestDistance = 99999;
            int nearestPlayer = i;
            for (int j = 0; j < players.length; j++) {
                double distanceToPlayer = l.distance(players[j].getPlayer().getLocation());
                if (distanceToPlayer < shortestDistance && i != j) {
                    nearestPlayer = j;
                    shortestDistance = distanceToPlayer;
                }
            }

            players[i].getPlayer().setCompassTarget(players[nearestPlayer].getPlayer().getLocation());
            int dystans = (int) players[i].getPlayer().getLocation().distance(players[nearestPlayer].getPlayer().getLocation());
            if (players[i].getPlayer().getItemInHand().getType().equals(Material.COMPASS)) {
                ItemMeta it = players[i].getPlayer().getInventory().getItemInHand().getItemMeta();
                it.setDisplayName("§7Najblizszy przeciwnik: §a" + players[nearestPlayer].getPlayer().getName() + " §7Odleglosc: §a" + dystans + " §7metrow");
                players[i].getPlayer().getItemInHand().setItemMeta(it);
            }
        }
    }
}
