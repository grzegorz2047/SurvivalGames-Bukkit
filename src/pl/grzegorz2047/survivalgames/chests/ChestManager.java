package pl.grzegorz2047.survivalgames.chests;

import org.bukkit.Location;
import pl.grzegorz2047.survivalgames.files.YmlFileHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz2047. 01.09.2015.
 */
public class ChestManager {


    YmlFileHandler mapfileHandler;

    private List<Location> chests = new ArrayList<Location>();
    private List<Location> doubleChests = new ArrayList<Location>();



    public ChestManager(){

    }

    public void addChest(Location loc){
        this.chests.add(loc);
    }
    public  void addDoubleChest(Location loc){
        this.doubleChests.add(loc);
    }


    public void saveChestsLocToFile(YmlFileHandler file){
        file.getConfig().set("numOfChests", this.chests.size());

        StringBuilder sb;
        int index = 0;
        for (Location loc : this.doubleChests) {
            sb = new StringBuilder();
            sb.append(loc.getBlockX()).append(':').append(loc.getBlockY()).append(':').append(loc.getBlockZ()).append(':').append(loc.getWorld().getName());
            String cords = sb.toString();
            file.getConfig().set("chests." + index, cords);
            index++;
        }
        file.save();
    }

    public void saveDoubleChestsLocToFile(YmlFileHandler file){
        file.getConfig().set("numOfDoubleChests", this.doubleChests.size());

        StringBuilder sb;
        int index = 0;
        for (Location loc : this.doubleChests) {
            sb = new StringBuilder();
            sb.append(loc.getBlockX()).append(':').append(loc.getBlockY()).append(':').append(loc.getBlockZ()).append(':').append(loc.getWorld().getName());
            String cords = sb.toString();
            file.getConfig().set("doublechests." + index, cords);
            index++;
        }
        file.save();
    }

}
