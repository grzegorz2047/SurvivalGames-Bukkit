package pl.grzegorz2047.survivalgames.user;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class User {

    private String username;
    private boolean playing;

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
}
