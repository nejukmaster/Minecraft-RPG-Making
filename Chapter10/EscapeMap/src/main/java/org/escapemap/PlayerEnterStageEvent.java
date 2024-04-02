package org.escapemap;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerEnterStageEvent extends Event implements Cancellable {
    public static  HandlerList handlers = new HandlerList();
    Player player;
    Stage to;
    Stage from;
    public Cancellable event;

    public PlayerEnterStageEvent(Player player, Stage to, Stage from, Cancellable cancellable){
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
    public Stage getTo(){
        return to;
    }
    public Stage getFrom(){return from;}
}
