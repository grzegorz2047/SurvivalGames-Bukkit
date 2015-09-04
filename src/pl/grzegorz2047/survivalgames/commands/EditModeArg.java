package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.MsgManager;
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
    protected void execute(CommandSender sender, String args[]) {
        if(!(sender instanceof Player)){
            return;
        }
        Player p = (Player) sender;
        if(args.length >= 2){
            boolean editMode = Boolean.parseBoolean(args[1]);
            sg.getGameManager().getChestManager().setEditMode(editMode);
            if(editMode){
                p.sendMessage(MsgManager.msg(ChatColor.GREEN + "Wlaczono tryb edycji skrzynek!"));
                p.sendMessage(MsgManager.msg("Aby zapisac wspolrzedne skrzynki kliknij na nia lewym klawiszem!"));
                p.sendMessage(MsgManager.msg("Kiedy skonczysz wylacz tryb edycji i zapisz wspolrzedne skrzynek poprzez /sg editmode false"));
            }else{
                sg.getGameManager().getChestManager().saveChestsLocToFile();
                sg.getGameManager().getChestManager().saveDoubleChestsLocToFile();
                p.sendMessage(MsgManager.msg("zapisano skrzynki!"));

            }

        }else{
            p.sendMessage(MsgManager.msg("Poprawne uzycie: /sg editmode true/false"));
        }

    }
}
