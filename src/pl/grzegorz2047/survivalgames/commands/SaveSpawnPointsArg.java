package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.files.YmlFileHandler;
import pl.grzegorz2047.survivalgames.spawn.SpawnPoint;

import java.util.List;

/**
 * Created by Grzegorz2047. 30.08.2015.
 */
public class SaveSpawnPointsArg extends Arg{

    SurvivalGames sg;

    public SaveSpawnPointsArg(SurvivalGames sg) {
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender, String args[]) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            List<SpawnPoint> spawnPointLists = sg.getGameManager().getSpawnManager().getSpawnPoints();

            if(spawnPointLists.isEmpty()){
                player.sendMessage(MsgManager.msg(ChatColor.RED + "Nie zostal zdefiniowany zaden SpawnPoint"));
                return;
            }else{
                YmlFileHandler mapConfig = sg.getGameManager().getSpawnManager().getSpawnfileHandler();
                sg.getGameManager().getSpawnManager().saveSpawnToFile(mapConfig);
                player.sendMessage(MsgManager.msg(ChatColor.GREEN + "Pomyslnie zostaly zapisane SpawnPointy"));
            }





        }
    }
}
