
package pl.grzegorz2047.survivalgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.grzegorz2047.survivalgames.SurvivalGames;

import java.util.HashMap;
import java.util.Map;

public class SGCommand implements CommandExecutor {
    SurvivalGames sg;

    public SGCommand(SurvivalGames sg) {
        this.sg = sg;
        this.commands.put("start", new StartArg(sg));
        this.commands.put("stop", new StopArg(sg));
        this.commands.put("setspawnpoint", new SetSpawnPointArg(sg));
    }

    private final Map<String, Arg> commands = new HashMap<String, Arg>();


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 0) {
            if (cmd.getName().equalsIgnoreCase("sg")) {
                String subCommand = args[0].toLowerCase();//lower case to ensure that all commands are correct key
                if (commands.get(subCommand) != null) {
                    this.commands.get(subCommand).execute(sender);
                }
            }
        }


        return true;
    }
}
