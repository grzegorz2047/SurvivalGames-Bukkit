package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.permission.Permission;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class StartArg extends Arg {

    SurvivalGames sg;

    public StartArg(SurvivalGames sg) {
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender, String args[]) {
        if (!sender.isOp() && !sender.hasPermission(Permission.PERMISSIONS_EKIPA)){
            ((Player)sender).sendMessage(MsgManager.msg("Komenda jedynie dla administracji!"));
            return;
        }
        if (!sg.getGameManager().isInGame()) {
            sg.getGameManager().startGame();
        } else {
            String answer = "Arena juz odlicza czas badz jest podczas gry!";
            if (sender instanceof Player) {
                Player p = (Player) sender;
                p.sendMessage(MsgManager.msg(ChatColor.RED + answer));
            }else{
                sender.sendMessage(answer);
            }

        }

    }
}
