package pl.grzegorz2047.survivalgames.runnable;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import pl.grzegorz2047.survivalgames.MsgManager;
import pl.grzegorz2047.survivalgames.SurvivalGames;
import pl.grzegorz2047.survivalgames.events.CountdownSecondEvent;
import pl.grzegorz2047.survivalgames.events.CounterEndEvent;

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
    }


    public void start() {
        if (running) {
            MsgManager.debug("Nie mozesz uruchomic countera po raz drugi");
            return;
        }
        this.createTask();
        running = true;

    }

    public void stop() {
        this.cancel();
        this.running = false;
    }

    public void pause() {
        this.running = false;
    }


    private void createTask() {
        task = this.runTaskTimer(sg, 0, 20l);// async or a sync
        this.taskId = task.getTaskId();
    }


    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        if (running) {
            this.time--;
            CountdownSecondEvent secondCountEvent = new CountdownSecondEvent(time);
            Bukkit.getPluginManager().callEvent(secondCountEvent);//Fires an event and triggers CounterEndListener
            if (time <= 0) {
                CounterEndEvent event = new CounterEndEvent();
                Bukkit.getPluginManager().callEvent(event);//Fires an event and triggers CounterEndListener

                this.stop();

            }
        }

    }
}
