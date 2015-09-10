package pl.grzegorz2047.survivalgames.user;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.logging.Level;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class User {

    private String username;
    private boolean spectator;
    private boolean playedBefore;
    private int money, kills, deaths, wins = 0;//?

    public User(String username, boolean spectator) {
        Validate.notNull(username);
        if(username == null){
            Bukkit.getLogger().log(Level.WARNING,"Username null");
        }
        this.username = username;
        this.spectator = spectator;
        if (!spectator) {
            playedBefore = true;
        } else {
            playedBefore = false;
        }
    }

    public String getUsername() {
        return username;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(username);
    }


    public boolean isSpectator() {
        return spectator;
    }

    public int getMoney() {
        return money;
    }

    public int getKills() {
        return kills;
    }

    public void setSpectator(boolean spectator) {
        this.spectator = spectator;
    }

    public int getWins() {
        return wins;
    }

    public void heal() {
        this.getPlayer().setHealth(this.getPlayer().getMaxHealth());
        this.getPlayer().setFoodLevel(20);
    }

    public boolean hasPlayedBefore() {
        return this.playedBefore;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
}
