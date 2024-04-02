package org.minecraftrpg;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Monster {
    PlayerStatus status;
    EntityType type;
    ItemStack[] dropItems;
    int dropExp;
    public Monster(EntityType type, PlayerStatus status, ItemStack[] dropItems, int dropExp){
        this.type = type;
        this.status = status;
        this.dropItems = dropItems;
        this.dropExp = dropExp;
    }
    public PlayerStatus getStatus(){
        return status;
    }
    public EntityType getType(){
        return type;
    }
    public ItemStack[] getDropItems(){
        return dropItems;
    }
    public int getDropExp(){
        return dropExp;
    }
    public UUID spawn(Location location){
        if(location.getWorld() == null) return null;
        Entity entity = location.getWorld().spawnEntity(location,type);
        return entity.getUniqueId();
    }
}
