package pl.grzegorz2047.survivalgames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.WorldController.WorldManager;
import pl.grzegorz2047.survivalgames.runnable.Counter;
import pl.grzegorz2047.survivalgames.spawn.Spawn;
import pl.grzegorz2047.survivalgames.stats.ServerStats;
import pl.grzegorz2047.survivalgames.user.User;
import pl.grzegorz2047.survivalgames.utils.BungeeUtil;
import pl.grzegorz2047.survivalgames.utils.GhostUtil;
import pl.grzegorz2047.survivalgames.votesystem.Vote;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class Game {

    private SurvivalGames sg;
    private Spawn spawn;
    private Vote vote;
    private WorldManager wm;
    ServerStats stats;


    private int mainTime = 1 * 60;//in seconds
    private int dmTime = 3 * 60;
    private boolean protection = true;
    private int protectionTime = 30;//in seconds
    private GhostUtil ghostUtil;

    public boolean isProtection() {
        return protection;
    }

    public void setProtection(boolean protection) {
        this.protection = protection;
    }

    public int getProtectionTime() {
        return protectionTime;
    }

    public void setProtectionTime(int protectionTime) {
        this.protectionTime = protectionTime;
    }

    public Game(SurvivalGames sg) {
        String worldName = "MapaSG";
        this.stats = new ServerStats(sg);
        this.wm = new WorldManager();
        this.wm.unloadWorld(worldName);
        this.ghostUtil = new GhostUtil(sg);
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

    public int getMainTime() {
        return mainTime;
    }

    public void setDeathMatch(boolean deathMatch) {
        this.deathMatch = deathMatch;
    }

    public int getDmTime() {
        return dmTime;
    }

    public ServerStats getStats() {
        return stats;
    }


    public GhostUtil getGhostUtil() {
        return ghostUtil;
    }


    public enum GameState {WAITING, STARTING, INGAME, RESTARTING}

    public Map<String, User> getPlayers() {
        return players;
    }

    private GameState state = GameState.WAITING;

    public GameState getGameState() {
        return this.state;
    }

    public void setGameState(GameState state) {
        this.state = state;
    }

    public boolean startGame() {
        if (this.getGameState().equals(GameState.WAITING)) {
            Counter counter = new Counter(sg, 10);
            counter.start();
            this.setGameState(Game.GameState.STARTING);
            return true;
        }
        return false;
    }

    private boolean deathMatch = false;

    public boolean isDeathMatch() {
        return this.deathMatch;
    }

    public boolean startDeatchMatch() {
        if (isDeathMatch()) {
            return false;
        }
        this.deathMatch = true;
        return true;
    }

    public boolean isInGame() {
        return state.equals(GameState.INGAME);
    }


    public User addPlayer(Player p, boolean spectator) {
        User user = new User(p.getName(), spectator);
        this.players.put(p.getName(), user);
        if (!spectator) {
            this.getSpawn().placePlayer(user);
        } else {
            p.teleport(this.getSpawn().getSpectatorLoc());
        }
        return user;
    }

    public void setSpectator() {

    }

    public void removePlayer(Player p) {
        User user = this.players.get(p.getName());
        this.getSpawn().displacePlayer(user);
        this.players.remove(p.getName());
    }

    public void end() {
        if (stats.getActivePlayers() > 1) {
            Bukkit.broadcastMessage(
                    MsgManager.msg(
                            ChatColor.RED +
                                    "Gra nie zostala zakonczona! Gracze zostaja ukarani ujemnymi punktami expa za przedluzanie rozgrywki!"));
        } else {
            Bukkit.broadcastMessage(
                    MsgManager.msg(
                            ChatColor.RED +
                                    "Cos poszlo nie tak. Zglos sie do moderatora."));
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            BungeeUtil.changeServer(sg, p, BungeeUtil.lobbyServer);
        }
    }

    public void reset() {
        sg.getGame().setGameState(GameState.RESTARTING);
    }


}
