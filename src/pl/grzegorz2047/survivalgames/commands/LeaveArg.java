package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.utils.BungeeUtil;

/**
 * Created by Grzegorz2047. 09.09.2015.
 */
public class LeaveArg extends Arg {

    private SurvivalGames sg;

    public LeaveArg(SurvivalGames sg) {
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        BungeeUtil.changeServer(sg, (Player)sender, BungeeUtil.lobbyServer);
    }
}
