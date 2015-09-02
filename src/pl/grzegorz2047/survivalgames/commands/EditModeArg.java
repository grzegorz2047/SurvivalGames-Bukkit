package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.command.CommandSender;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 01.09.2015.
 */
public class EditModeArg extends Arg {

    private SurvivalGames sg;

    public EditModeArg(SurvivalGames sg) {
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender) {

    }
}
