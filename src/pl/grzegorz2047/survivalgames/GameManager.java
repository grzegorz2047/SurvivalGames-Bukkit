package pl.grzegorz2047.survivalgames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.WorldController.WorldManager;
import pl.grzegorz2047.survivalgames.chests.ChestManager;
import pl.grzegorz2047.survivalgames.runnable.Counter;
import pl.grzegorz2047.survivalgames.scoreboard.ScoreboardUtil;
import pl.grzegorz2047.survivalgames.spawn.SpawnManager;
import pl.grzegorz2047.survivalgames.spawn.SpawnPoint;
import pl.grzegorz2047.survivalgames.stats.ServerStats;
import pl.grzegorz2047.survivalgames.user.User;
import pl.grzegorz2047.survivalgames.utils.BungeeUtil;
import pl.grzegorz2047.survivalgames.utils.GhostUtil;
import pl.grzegorz2047.survivalgames.utils.TimeUtil;
import pl.grzegorz2047.survivalgames.votesystem.Vote;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class GameManager {

    private SurvivalGames sg;
    private SpawnManager spawnManager;
    private ChestManager chestManager;
    private Vote vote;
    private WorldManager wm;
    ServerStats stats;
    private int minReqPlayers;//(active/2);

    private int mainTime = 6 * 60;//in seconds
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

    public GameManager(SurvivalGames sg) {
        this.setGameState(GameState.RESTARTING);
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
        this.spawnManager = new SpawnManager(sg);
        this.chestManager = new ChestManager(sg);
        this.vote = new Vote(sg);
        this.minReqPlayers = this.spawnManager.getSpawnPoints().size()/2;
        sg.setIsRestarting(false);//When plugin is restarting doesnt allow them
        this.setGameState(GameState.WAITING);
    }

    Map<String, User> players = new HashMap<String, User>();

    public SpawnManager getSpawnManager() {
        return spawnManager;
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

    public ChestManager getChestManager() {
        return chestManager;
    }


    public enum GameState {WAITING, STARTING, INGAME, RESTARTING}

    public Map<String, User> getPlayers() {
        return players;
    }

    private GameState state = GameState.RESTARTING;

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
            this.setGameState(GameManager.GameState.STARTING);
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
        for (SpawnPoint sp : this.spawnManager.getSpawnPoints()) {
            User user = this.getPlayers().get(sp.getOccupiedBy());
            if (user != null) {
                if (!user.isSpectator()) {
                    user.heal();
                    user.getPlayer().teleport(sp.getLocation());
                }
            }
        }
        sg.getGameManager().getChestManager().fillChests();
        this.deathMatch = true;
        return true;
    }

    public boolean isInGame() {
        return state.equals(GameState.INGAME);
    }

    public boolean isRestarting() {
        return this.stats.equals(GameState.RESTARTING);
    }

    public User addPlayer(Player p, boolean spectator) {
        User user = new User(p.getName(), spectator);
        this.players.put(p.getName(), user);
        if (!spectator) {
            this.getSpawnManager().placePlayer(user);
        } else {
            p.teleport(this.getSpawnManager().getSpectatorLoc());
            sg.getGameManager().getGhostUtil().addPlayer(p);
        }
        return user;
    }


    public void removePlayer(Player p) {
        User user = this.players.get(p.getName());
        this.getSpawnManager().displacePlayer(user);
        this.players.remove(p.getName());
        sg.getGameManager().getGhostUtil().removePlayer(p);
    }

    public void end(int activePlayers) {

        if (activePlayers > 1) {
            Bukkit.broadcastMessage(
                    MsgManager.msg(
                            ChatColor.RED +
                                    "Gra nie zostala zakonczona! Gracze zostaja ukarani ujemnymi punktami expa za przedluzanie rozgrywki!"));
        } else if (activePlayers == 1) {
            Player winner = sg.getGameManager().getStats().getLastActivePlayer();
            if (winner != null) {
                winner.sendMessage(MsgManager.msg("Wygrales mecz na survival games!"));
            }
            Bukkit.broadcastMessage(MsgManager.msg("Rozgrywka zostala zakonczona! Arena restartuje sie za 5 sekund!"));
        }
        Bukkit.getScheduler().runTaskLater(sg, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    BungeeUtil.changeServer(sg, p, BungeeUtil.lobbyServer);
                    sg.resetPlugin();
                }
            }
        }, 5 * 20l);

    }


    public void checkRequirementToStart() {
        int active = this.stats.getActivePlayers();
        if (active >= this.minReqPlayers) {
            this.startGame();
        }
    }

    public void checkRequirementToStop() {
        int active = this.stats.getActivePlayers();
        if (active <= this.minReqPlayers) {
            Bukkit.getScheduler().cancelTasks(sg);
            this.getGhostUtil().createTask(sg);//Restart task
            for (Player p : Bukkit.getOnlinePlayers()) {
                ScoreboardUtil sb = new ScoreboardUtil(p, false);
                sb.setDisplayName(TimeUtil.formatHHMMSS(0) + sb.getMinigamePrefix() + stats.getMinMaxPlayers(false));
            }
            sg.getGameManager().setGameState(GameState.WAITING);
        }
    }


}
