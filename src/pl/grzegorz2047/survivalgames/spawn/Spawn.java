package pl.grzegorz2047.survivalgames.spawn;

import pl.grzegorz2047.survivalgames.SurvivalGames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class Spawn {

    private List<SpawnPoint> spawnPoints = new ArrayList<SpawnPoint>();

    private SurvivalGames sg;

    public Spawn(SurvivalGames sg) {
        this.sg = sg;
    }

    public void addSpawnPoint(SpawnPoint spawnPoint) {
        this.spawnPoints.add(spawnPoint);
    }

    public List<SpawnPoint> getSpawnPoints() {
        return this.spawnPoints;
    }
}
