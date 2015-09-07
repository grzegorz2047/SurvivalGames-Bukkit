package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class StopArg extends Arg {

    SurvivalGames sg;

    public StopArg(SurvivalGames sg) {
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender, String args[]) {
        if (!sender.isOp()){
            ((Player)sender).sendMessage(MsgManager.msg("Komenda jedynie dla administracji!"));
            return;
        }
        MsgManager.debug("Arena zakonczyla sie");
        sender.sendMessage("Koncze arene!");
        sg.resetPlugin();
    }
}
