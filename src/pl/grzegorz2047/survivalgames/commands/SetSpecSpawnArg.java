package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.spawn.SpawnPoint;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class SetSpecSpawnArg extends Arg {

    SurvivalGames sg;

    public SetSpecSpawnArg(SurvivalGames sg) {
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

            SpawnPoint sp = new SpawnPoint(x, y, z, pitch, yaw, worldName);
            sg.getGameManager().getSpawnManager().setSpectatorLoc(player.getLocation());


            StringBuilder sb = new StringBuilder();
            sb.append(sp.getX()).append(':').append(sp.getY()).append(':').append(sp.getZ()).append(':').append(sp.getPitch()).append(':').append(sp.getYaw()).append(':').append(sp.getWorldName());
            String cords = sb.toString();
            sg.getGameManager().getSpawnManager().getSpawnfileHandler().getConfig().set("spawns.spectator", cords);
            sg.getGameManager().getSpawnManager().getSpawnfileHandler().save();

            player.sendMessage(ChatColor.GREEN + "Pomyslnie utworzono spawn dla spectatora!");

        }
    }
}
