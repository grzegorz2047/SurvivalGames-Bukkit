package pl.grzegorz2047.survivalgames.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class User {

    private String username;
    private boolean playing;

    private int money, kills, deaths, wins = 0;//?

    public User(String username, boolean playing) {
        this.username = username;
        this.playing = playing;
    }

    public String getUsername() {
        return username;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(username);
    }


    public boolean isSpectator() {
        return !playing;
    }

    public int getMoney() {
        return money;
    }

    public int getKills() {
        return kills;
    }
}
