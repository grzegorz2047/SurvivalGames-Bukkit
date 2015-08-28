package pl.grzegorz2047.survivalgames.spawn;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class SpawnPoint {

    private double x, y, z;
    private String worldName;

    public SpawnPoint(double x, double y, double z, String worldName){
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
    }

}
