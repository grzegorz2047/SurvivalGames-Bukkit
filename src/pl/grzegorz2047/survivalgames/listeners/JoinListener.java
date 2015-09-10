
package pl.grzegorz2047.survivalgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.scoreboard.ScoreboardUtil;
import pl.grzegorz2047.survivalgames.user.User;
import pl.grzegorz2047.survivalgames.utils.TimeUtil;
import pl.neksi.craftgames.game.ArenaStatus;

public class JoinListener implements Listener {

    SurvivalGames sg;

    public JoinListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
        //TODO Make sure that event is firing for PLAYER not for null! scheduler?
    void onJoin(PlayerJoinEvent e) {
        Player joinedPlayer = e.getPlayer();
        if (joinedPlayer == null) {
            return;
        }

        e.setJoinMessage(null);
        User user;
        if (!sg.getGameManager().isInGame()) {
            Bukkit.broadcastMessage(MsgManager.msg(joinedPlayer.getName() + " dolaczyl do areny jako " + sg.getGameManager().getStats().getMinMaxPlayers(true)));
            user = sg.getGameManager().addPlayer(joinedPlayer, false);
            sg.getGameManager().checkRequirementToStart();
            joinedPlayer.sendMessage(MsgManager.msg(ChatColor.BOLD + "" + ChatColor.AQUA + "Witaj na arenie z klasyczna wersja Survival Games w wersji BETA!"));
            joinedPlayer.sendMessage(MsgManager.msg(ChatColor.BOLD + "" + ChatColor.RED + "Za wyjscie z gry podczas rozgrywki otrzymuje sie ujemne punkty EXP"));
            joinedPlayer.sendMessage(MsgManager.msg(ChatColor.BOLD + "" + ChatColor.AQUA + "Jezeli 2 osoby przezyja deathmatch to otrzymuja ujemne punkty EXP!"));
            ScoreboardUtil sc;
            for (Player player : Bukkit.getOnlinePlayers()) {
                sc = new ScoreboardUtil(player, false);
                sc.setDisplayName(TimeUtil.formatHHMMSS(0) + sc.getMinigamePrefix() + sg.getGameManager().getStats().getMinMaxPlayers(false));
            }
        } else {
            user = sg.getGameManager().addPlayer(joinedPlayer, true);
            sg.getGameManager().getGhostUtil().addPlayer(joinedPlayer);
        }
        ScoreboardUtil sc = new ScoreboardUtil(joinedPlayer, true);
        sc.setScore(sc.getObjective(), sc.getScKills(), user.getKills());
        //sc.setScore(sc.getObjective(), sc.getScWins(), user.getWins());
        sc.setDisplayName(TimeUtil.formatHHMMSS(0) + sc.getMinigamePrefix() + sg.getGameManager().getStats().getMinMaxPlayers(false));
        sg.getGameManager().getStats().updateStats();
        ArenaStatus.setPlayers(sg.getGameManager().getStats().getActivePlayers());
        if (sg.isDebugMode()) {
            joinedPlayer.sendMessage(MsgManager.msg(ChatColor.BOLD+"Arena jest w trybie debugowania!"));
        }
    }
}
