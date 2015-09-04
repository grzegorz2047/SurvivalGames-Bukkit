package pl.grzegorz2047.survivalgames.commands;

import com.sun.management.OperatingSystemMXBean;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.survivalgames.SurvivalGames;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * Created by Grzegorz2047. 02.09.2015.
 */
public class MemArg extends Arg {

    private SurvivalGames sg;

    public MemArg(SurvivalGames sg){
        this.sg = sg;
    }

    @Override
    protected void execute(CommandSender sender, String args[]) {
        Runtime runtime = Runtime.getRuntime();
        

        int maxMemory = (int) runtime.maxMemory()/ 1024/ 1024;
        int allocatedMemory = (int) runtime.totalMemory()/ 1024/ 1024;
        int freeMemory = (int) runtime.freeMemory()/ 1024/ 1024;
        double cpuLoad = getCpuLoad();

        if(!(sender instanceof Player)){
            sender.sendMessage("Wolna pamiec: " + freeMemory  );
            sender.sendMessage("Alkowana pamiec: " + allocatedMemory  );
            sender.sendMessage("Maksymalna pamiec: " + (maxMemory  ));
            sender.sendMessage("Ogolna wolna pamiec: " + (freeMemory + (maxMemory - allocatedMemory))  );
            sender.sendMessage("Obciazenie procesora: "+cpuLoad);
        }else{
            Player p = (Player) sender;
            p.sendMessage(ChatColor.GOLD+"==================================");
            p.sendMessage(ChatColor.GRAY+"Wolna pamiec: " +ChatColor.GREEN+ freeMemory +ChatColor.GRAY+" MB");
            p.sendMessage(ChatColor.GRAY+"Alokowana pamiec: " +ChatColor.GREEN+ allocatedMemory +ChatColor.GRAY+" MB");
            p.sendMessage(ChatColor.GRAY+"Maksymalna pamiec: " +ChatColor.GREEN+ maxMemory +ChatColor.GRAY+" MB");
            p.sendMessage(ChatColor.GRAY+"Ogolna wolna pamiec: " +ChatColor.GREEN+ (freeMemory + (maxMemory - allocatedMemory)) +ChatColor.GRAY+" MB");
            p.sendMessage(ChatColor.GRAY+"Obciazenie procesora: " +ChatColor.GREEN+ cpuLoad +ChatColor.GRAY+" %");
        }

    }
    double getCpuLoad() {
        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        int availableProcessors = operatingSystemMXBean.getAvailableProcessors();
        long prevUpTime = runtimeMXBean.getUptime();
        long prevProcessCpuTime = operatingSystemMXBean.getProcessCpuTime();
        double cpuUsage;
        try
        {
            Thread.sleep(500);
        }
        catch (Exception ignored) { }

        operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long upTime = runtimeMXBean.getUptime();
        long processCpuTime = operatingSystemMXBean.getProcessCpuTime();
        long elapsedCpu = processCpuTime - prevProcessCpuTime;
        long elapsedTime = upTime - prevUpTime;

        cpuUsage = Math.min(99F, elapsedCpu / (elapsedTime * 10000F * availableProcessors));
        //System.out.println("Java CPU: " + cpuUsage);
        return cpuUsage;
    }

}
