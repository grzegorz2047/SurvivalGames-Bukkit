
package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.grzegorz2047.survivalgames.SurvivalGames;

public class SGCommand implements CommandExecutor {
    public SGCommand(SurvivalGames sg) {
    }

    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("sg") && args.length > 0) {
            if(args[0].equalsIgnoreCase("start")) {
                Bukkit.broadcastMessage("Odliczanie zostalo rozpoczete!");
            }

            if(args[0].equalsIgnoreCase("stop")) {
                Bukkit.broadcastMessage("Gra zostala zakonczona!");
            }
        }

        return true;
    }
}
