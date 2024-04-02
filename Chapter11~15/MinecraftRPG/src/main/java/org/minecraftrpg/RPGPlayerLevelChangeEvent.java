package org.minecraftrpg;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RPGPlayerLevelChangeEvent extends Event implements Cancellable {
    public static  HandlerList handlers = new HandlerList();
    RPGPlayer player;
    int prevLV;
    int nextLV;
    public RPGPlayerLevelChangeEvent(RPGPlayer player, int prevLV, int nextLV){
        this.player = player;
        this.prevLV = prevLV;
        this.nextLV = nextLV;
    }
    public RPGPlayer getRPGPlayer(){
        return player;
    }
    public int getPrev(){
        return prevLV;
    }
    public int getNext(){
        return nextLV;
    }
    @Override
    public boolean isCancelled() {
        return false;
    }
    @Override
    public void setCancelled(boolean b) {
        if(b){
            player.playerStatus.playerLevel = prevLV;
        }
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
