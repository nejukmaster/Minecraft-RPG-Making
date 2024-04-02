package org.minecraftrpg;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RPGPlayerNPCInteractEvent extends Event implements Cancellable {
    public static HandlerList handlers = new HandlerList();
    Cancellable event;
    NPC npc;
    Player player;
    public RPGPlayerNPCInteractEvent(Cancellable event, NPC npc, Player player){
        this.event = event;
        this.npc = npc;
        this.player = player;
    }
    public NPC getNpc() {
        return npc;
    }
    public Player getPlayer() {
        return player;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }
    @Override
    public void setCancelled(boolean b) {
        event.setCancelled(b);
    }
}
