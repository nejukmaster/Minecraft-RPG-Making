package org.randomweapon;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class RandomWeapon extends JavaPlugin {
    public static final int MAX_COOL = 20;
    public static RandomWeapon instance;

    public ItemStack Teleporter = createCustomItem(Material.COMPASS, "Teleporter", 1);
    public ItemStack Exploder = createCustomItem(Material.IRON_INGOT, "Exploder",1);
    public ItemStack Warrior = createCustomItem(Material.GOLD_INGOT, "Warrior", 1);

    public int[] cooldown = new int[3];
    public boolean onGame = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(onGame){
                    for(int i = 0; i < cooldown.length; i ++){
                        if(cooldown[i] > 0)
                            cooldown[i]--;
                    }
                }
            }
        };
        runnable.runTaskTimer(this,0,20);

        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginCommand("rdweapon").setExecutor(new Cmds());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ItemStack createCustomItem(Material type, String name, int customModelData){
        ItemStack _item = new ItemStack(type,1);
        ItemMeta _meta = _item.getItemMeta();
        _meta.setDisplayName(name);
        _meta.setCustomModelData(customModelData);
        _item.setItemMeta(_meta);
        return _item;
    }
}
