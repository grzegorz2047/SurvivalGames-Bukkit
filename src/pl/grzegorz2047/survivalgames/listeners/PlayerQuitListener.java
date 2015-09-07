
package pl.grzegorz2047.survivalgames.listeners;

import dram.rankmod.main.RankMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.neksi.craftgames.game.ArenaStatus;

public class PlayerQuitListener implements Listener {

    SurvivalGames sg;

    public PlayerQuitListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(p == null){
            return;
        }
        ArenaStatus.setPlayers(Bukkit.getOnlinePlayers().size());
        e.setQuitMessage(null);

        if (sg.getGameManager().isInGame()) {
            if(!sg.getGameManager().getPlayers().get(p.getName()).isSpectator()){
                RankMain.addPlayerXp(p.getName(),sg.getGameManager().getExpForleaving());
                p.sendMessage(MsgManager.msg(ChatColor.RED+"Otrzymales ujemne punkty exp za opuszczenie rozgrywki! ("+sg.getGameManager().getExpForleaving()+")"));
            }
        }else{
            sg.getGameManager().checkRequirementToStop();
        }
        sg.
                getGameManager().
                removePlayer(p);
        Bukkit.broadcastMessage(MsgManager.msg(p.getName() + " opuscil serwer!"));
        sg.getGameManager().getStats().updateStats();
        int activePlayers = sg.getGameManager().getStats().getActivePlayers();
        MsgManager.debug("Ilosc aktywnych graczy przy playerquitevent " + activePlayers);
        if(activePlayers == 1 && sg.getGameManager().isInGame()) {
            sg.getGameManager().end(activePlayers);
        }
    }
}
