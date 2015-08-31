package pl.grzegorz2047.survivalgames.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class User {

    private String username;
    private boolean spectator;

    private int money, kills, deaths, wins = 0;//?

    public User(String username, boolean spectator) {
        this.username = username;
        this.spectator = spectator;
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
}
