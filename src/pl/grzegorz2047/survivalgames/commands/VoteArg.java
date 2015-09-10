package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;

import java.util.Calendar;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class VoteArg extends Arg {

    private final SurvivalGames sg;

    public VoteArg(SurvivalGames sg) {
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender, String args[]) {
        Calendar calendar = Calendar.getInstance();
        int minPlayers = 7;
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        if(hours <14){
            minPlayers = 7;
        }else if(hours > 14){
            minPlayers = 11;
        }

        if (Bukkit.getOnlinePlayers().size() >= minPlayers) {
            sg.getGameManager().startGame();
        }else{
            sender.sendMessage(MsgManager.msg("Potrzeba minimium "+minPlayers+" osob do wystartowania areny!"));
        }
    }
}
