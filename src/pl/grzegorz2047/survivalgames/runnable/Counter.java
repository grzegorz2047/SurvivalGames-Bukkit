package pl.grzegorz2047.survivalgames.runnable;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import pl.grzegorz2047.survivalgames.SurvivalGames;

/**
 * Created by Grzegorz2047. 27.08.2015.
 */
public class Counter extends BukkitRunnable {

    SurvivalGames sg;
    private int time;
    private int taskId;
    private boolean running = false;


    BukkitTask task;

    public Counter(SurvivalGames sg, int time) {
        this.time = time;
        this.sg = sg;
        task = this.runTaskTimerAsynchronously(sg, 0, 20l);
        this.taskId = task.getTaskId();
    }


    public void start() {
        if (!running) {
            running = true;
        }
    }

    public void stop() {
        this.cancel();
        this.running = false;

    }

    public void pause() {
        this.running = false;
    }


    public void reset() {
        stop();
        this.time = 0;
        task = this.runTaskTimerAsynchronously(sg, 0, 20l);

    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        if (running) {
            if (time >= 0) {
                this.stop();
            }
        }

    }
}
