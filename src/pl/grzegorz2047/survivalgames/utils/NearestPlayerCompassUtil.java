package pl.grzegorz2047.survivalgames.utils;

/**
 * Created by Grzegorz2047. 11.09.2015.
 */

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.user.User;

import java.util.List;

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
        //System.out.println("jest task!");
        if (sg.getGameManager().getStats().getActivePlayers() >= 2) {
            //System.out.print("Graczy wiecej niz 1");
            updateCompasses(sg.getGameManager().getActivePlayers());
        }

    }

    public static void updateCompasses(List<User> players) {
        for (int i = 0; i < players.size(); i++) {
            Location l = players.get(i).getPlayer().getLocation();
            double shortestDistance = 99999;
            int nearestPlayer = i;
            for (int j = 0; j < players.size(); j++) {
                double distanceToPlayer = l.distance(players.get(j).getPlayer().getLocation());
                if (distanceToPlayer < shortestDistance && i != j) {
                    nearestPlayer = j;
                    shortestDistance = distanceToPlayer;
                }
            }

            players.get(i).getPlayer().setCompassTarget(players.get(nearestPlayer).getPlayer().getLocation());
            int dystans = (int) players.get(i).getPlayer().getLocation().distance(players.get(nearestPlayer).getPlayer().getLocation());
            if (players.get(i).getPlayer().getItemInHand().getType().equals(Material.COMPASS)) {
                ItemMeta it = players.get(i).getPlayer().getInventory().getItemInHand().getItemMeta();
                it.setDisplayName("§7Przeciwnik: §a" + players.get(nearestPlayer).getPlayer().getName() + " §7Odleglosc: §a" + dystans + " §7m");
                players.get(i).getPlayer().getItemInHand().setItemMeta(it);
            }
        }
    }
}
