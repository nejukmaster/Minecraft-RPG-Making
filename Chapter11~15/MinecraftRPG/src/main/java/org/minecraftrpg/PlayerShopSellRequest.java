package org.minecraftrpg;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerShopSellRequest extends Event{
    public static  HandlerList handlers = new HandlerList();
    Player player;
    Material itemType;
    int price;
    public PlayerShopSellRequest(Player player, Material itemType, int price){
        this.player = player;
        this.itemType = itemType;
        this.price = price;
    }

    public Player getPlayer() {
        return player;
    }
    public Material getItemType() {
        return itemType;
    }
    public int getPrice() {
        return price;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList(){
        return handlers;
    }
}
