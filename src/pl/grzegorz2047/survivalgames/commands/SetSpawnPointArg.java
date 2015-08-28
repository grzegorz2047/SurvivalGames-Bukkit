package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.spawn.SpawnPoint;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class SetSpawnPointArg extends Arg {

    SurvivalGames sg;

    public SetSpawnPointArg(SurvivalGames sg) {
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            String worldName = player.getLocation().getWorld().getName();
            SpawnPoint spawnPoint = new SpawnPoint(x, y, z, worldName);

            sg.getGame().getSpawn().getSpawnPoints().add(spawnPoint);

            player.sendMessage(ChatColor.GREEN+"Pomyslnie utworzono spawn gracza");

        }
    }
}
