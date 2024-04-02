package org.minecraftrpg;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RPGPlayerExpChangeEvent extends Event implements Cancellable {
    public static  HandlerList handlers = new HandlerList();
    RPGPlayer player;
    int prevExp;
    int nextExp;
    boolean cancelled = false;
    public RPGPlayerExpChangeEvent(RPGPlayer player, int prevExp, int nextExp){
        this.player = player;
        this.prevExp = prevExp;
        this.nextExp = nextExp;
    }
    public RPGPlayer getPlayer(){
        return player;
    }
    public int getPrevExp(){
        return prevExp;
    }
    public int getNextExp(){
        return nextExp;
    }
    @Override
    public boolean isCancelled() {
        return cancelled;
    }
    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
