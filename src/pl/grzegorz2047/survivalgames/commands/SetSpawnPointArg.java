package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.spawn.SpawnPoint;

import java.util.List;

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
            double pitch = player.getLocation().getPitch();
            double yaw = player.getLocation().getYaw();
            String worldName = player.getLocation().getWorld().getName();
            SpawnPoint spawnPoint = new SpawnPoint(x, y, z,pitch, yaw, worldName);

            List<SpawnPoint> spawns = sg.getGame().getSpawn().getSpawnPoints();
            spawns.add(spawnPoint);

            player.sendMessage(ChatColor.GREEN + "Pomyslnie utworzono spawn gracza o nr. " + spawns.size());

        }
    }
}
