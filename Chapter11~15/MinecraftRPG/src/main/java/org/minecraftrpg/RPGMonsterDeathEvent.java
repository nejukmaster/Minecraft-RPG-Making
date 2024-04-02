package org.minecraftrpg;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RPGMonsterDeathEvent extends Event {
    public static  HandlerList handlers = new HandlerList();
    LivingEntity entity;
    Monster monsterClass;
    public RPGMonsterDeathEvent(LivingEntity entity, LivingEntity murder, Monster monsterClass){
        this.entity = entity;
        this.monsterClass = monsterClass;
    }
    public LivingEntity getEntity(){
        return entity;
    }
    public Monster getMonsterClass() {
        return monsterClass;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
