package org.minecraftrpg;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RPGMonsterDamagedEvent extends Event implements Cancellable {
    public static HandlerList handlers = new HandlerList();

    LivingEntity entity;
    double damage;
    Cancellable event;
    Monster monsterClass;

    public RPGMonsterDamagedEvent(LivingEntity entity, double damage, Cancellable event, Monster monsterClass){
        this.entity = entity;
        this.damage = damage;
        this.event = event;
        this.monsterClass = monsterClass;
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
