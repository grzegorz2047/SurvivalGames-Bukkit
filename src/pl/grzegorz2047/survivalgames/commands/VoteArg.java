package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.command.CommandSender;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class VoteArg extends Arg {

    private final SurvivalGames sg;

    public VoteArg(SurvivalGames sg) {
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender) {

    }
}
