package pl.grzegorz2047.survivalgames.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Grzegorz2047. 31.08.2015.
 */
public class CountdownSecondEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();


    public CountdownSecondEvent(int currentTime) {
    this.currentTime = currentTime;
    }

    private int currentTime;

    public int getCurrentTime(){
        return currentTime;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }
}
