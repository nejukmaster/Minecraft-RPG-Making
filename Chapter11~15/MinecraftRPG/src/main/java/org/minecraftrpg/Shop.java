package org.minecraftrpg;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import java.util.HashMap;

public class Shop {
    Inventory inventory;
    boolean isSell;
    HashMap<Material, Integer> sell = new HashMap<Material,Integer>(){{
       put(Material.IRON_INGOT,100);
       put(Material.GOLD_INGOT,500);
       put(Material.DIAMOND,1000);
       put(Material.EMERALD,1500);
       put(Material.COAL,20);
    }};
    HashMap<Material,Integer> buy = new HashMap<Material,Integer>(){{
        put(Material.IRON_SWORD,1500);
        put(Material.DIAMOND_SWORD,2000);
        put(Material.NETHERITE_SWORD,3000);
    }};
    public Shop(){
        this.inventory = Bukkit.createInventory(null,9,"Shop");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getBuy(Material mat) {
        if(!buy.containsKey(mat)) return -1;
        return buy.get(mat).intValue();
    }
    public int getSell(Material mat) {
        if(!sell.containsKey(mat)) return -1;
        return sell.get(mat).intValue();
    }
    public void initInventoryToSell(){
        isSell = true;
        inventory.clear();
        int _i = 0;
        for(Material m : sell.keySet()){
            inventory.setItem(_i,MinecraftRPG.createCustomItem(m,sell.get(m).toString(),1));
            _i ++;
        }
    }
    public void initInventoryToBuy(){
        isSell = false;
        inventory.clear();
        int _i = 0;
        for(Material m : buy.keySet()){
            inventory.setItem(_i,MinecraftRPG.createCustomItem(m,buy.get(m).toString(),1));
            _i ++;
        }
    }
}
