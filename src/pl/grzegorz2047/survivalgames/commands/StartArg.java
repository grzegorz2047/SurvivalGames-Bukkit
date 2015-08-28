package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class StartArg extends Arg {

    SurvivalGames sg;
    public StartArg(SurvivalGames sg){
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender) {
        sg.getGame().start();
    }
}
