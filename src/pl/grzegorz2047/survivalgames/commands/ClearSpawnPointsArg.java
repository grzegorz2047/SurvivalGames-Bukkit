package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 30.08.2015.
 */
public class ClearSpawnPointsArg extends Arg {

    private final SurvivalGames sg;

    public ClearSpawnPointsArg(SurvivalGames sg){
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender) {
        sg.getGameManager().getSpawnManager().getSpawnPoints().clear();
        MsgManager.msg(ChatColor.GREEN+"Pomyslnie wyczyszczono SpawnPointy z pamieci!");
    }
}
