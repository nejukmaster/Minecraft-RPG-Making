package org.minecraftrpg;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerEnterAreaEvent extends Event implements Cancellable {
    public static  HandlerList handlers = new HandlerList();
    Player player;
    Area to;
    Area from;
    public Cancellable event;

    public PlayerEnterAreaEvent(Player player, Area to, Area from, Cancellable cancellable){
        this.player = player;
        this.to = to;
        this.from = from;
        this.event = cancellable;
    }
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    @Override
    public void setCancelled(boolean b) {
        event.setCancelled(b);
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
    public Player getPlayer(){
        return player;
    }
    public Area getTo(){
        return to;
    }
    public Area getFrom(){return from;}
}
