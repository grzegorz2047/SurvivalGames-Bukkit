package pl.grzegorz2047.survivalgames.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Grzegorz2047. 28.08.2015.
 */
public class CounterEndEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public CounterEndEvent() {

    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
