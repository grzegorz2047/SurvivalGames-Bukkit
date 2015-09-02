package pl.grzegorz2047.survivalgames.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.permission.Permission;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class PlayerLoginListener implements Listener {

    private SurvivalGames sg;

    public PlayerLoginListener(SurvivalGames sg) {
        this.sg = sg;
    }

    @EventHandler
    void onPlayerLogin(PlayerLoginEvent e) {
        if(sg.getGameManager().isInGame()){
            if (!(e.getPlayer().hasPermission(Permission.PERMISSIONS_VIP) || e.getPlayer().isOp()) && !sg.isDebugMode()) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, MsgManager.msg(ChatColor.RED + "Nie mozesz obserwowac bez rangi VIP!"));
            }
        }else if(sg.getGameManager().isRestarting()){
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, MsgManager.msg(ChatColor.RED + "Arena restartuje sie"));
        }

    }

}
