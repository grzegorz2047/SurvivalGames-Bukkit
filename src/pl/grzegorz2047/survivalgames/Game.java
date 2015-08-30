package pl.grzegorz2047.survivalgames;

import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.WorldController.WorldManager;
import pl.grzegorz2047.survivalgames.runnable.Counter;
import pl.grzegorz2047.survivalgames.spawn.Spawn;
import pl.grzegorz2047.survivalgames.user.User;
import pl.grzegorz2047.survivalgames.votesystem.Vote;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class Game {

    private SurvivalGames sg;
    private Spawn spawn;
    private Vote vote;
    private WorldManager wm;

    public Game(SurvivalGames sg) {
        String worldName = "MapaSG";
        this.wm = new WorldManager();
        this.wm.unloadWorld(worldName);
        try {
            this.wm.load(worldName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.sg = sg;
        this.spawn = new Spawn(sg);
        this.vote = new Vote(sg);
    }

    Map<String, User> players = new HashMap<String, User>();

    public Spawn getSpawn() {
        return spawn;
    }

    public WorldManager getWorldManager() {
        return wm;
    }

    public enum GameState {WAITING, STARTING, INGAME, RESTARTING}



    private GameState state = GameState.WAITING;

    public GameState getGameState() {
        return this.state;
    }

    public void setGameState(GameState state) {
        this.state = state;
    }

    public void start() {
        if (this.getGameState().equals(GameState.WAITING)) {
            Counter counter = new Counter(sg, 10);
            counter.start();
            this.setGameState(Game.GameState.STARTING);
        }
    }

    public boolean isInGame() {
        return state.equals(GameState.INGAME);
    }


    public User addPlayer(Player p) {
        User user = new User(p.getName(), true);
        this.players.put(p.getName(), user);
        this.getSpawn().placePlayer(user);
        return user;
    }

    public void setSpectator() {

    }

    public void removePlayer(Player p) {
        User user = this.players.get(p.getName());
        this.getSpawn().displacePlayer(user);
        this.players.remove(p.getName());
    }

}
