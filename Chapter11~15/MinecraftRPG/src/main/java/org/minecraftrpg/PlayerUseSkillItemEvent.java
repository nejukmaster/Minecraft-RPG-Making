package org.minecraftrpg;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerUseSkillItemEvent extends Event implements Cancellable {
    public static HandlerList handlers = new HandlerList();

    Cancellable event;
    SkillItem item;
    Player player;

    public PlayerUseSkillItemEvent(SkillItem item, Player player, Cancellable event){
        this.item = item;
        this.player = player;
        this.event = event;
    }

    public SkillItem getItem(){
        return item;
    }
    public Player getPlayer(){
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
