package org.statusmanager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public final class StatusManager extends JavaPlugin {

    public static StatusManager instance;

    public HashMap<String,Integer> statusDic = new HashMap<>();
    public Inventory statusGUI = Bukkit.createInventory(null,9,"Status Manager");
    public ItemStack statusHealth_item = createCustomItem(Material.REDSTONE,"Health",1);
    public ItemStack statusStrength_item = createCustomItem(Material.IRON_INGOT, "Strength", 1);
    public ItemStack statusSpeed_item = createCustomItem(Material.FEATHER, "Speed", 1);
    public ItemStack sp_item = createCustomItem(Material.EMERALD, "SP", 1);
    public ItemStack block_item = createCustomItem(Material.BLACK_STAINED_GLASS, "", 1);
    public int sp = 0;
    public Player player;


    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        statusDic.put("health",0);
        statusDic.put("strength",0);
        statusDic.put("speed",0);

        statusGUI.setItem(0,statusHealth_item);
        statusGUI.setItem(1,statusStrength_item);
        statusGUI.setItem(2,statusSpeed_item);
        statusGUI.setItem(3,block_item);
        statusGUI.setItem(4,block_item);
        statusGUI.setItem(5,block_item);
        statusGUI.setItem(6,block_item);
        statusGUI.setItem(7,block_item);

        ItemStack _sp = sp_item;
        _sp.setAmount(sp);
        statusGUI.setItem(8,_sp);

        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(player != null){
                    if(statusDic.get("speed") > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 21, statusDic.get("speed") / 3));
                    if(statusDic.get("health") > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 21, statusDic.get("health") / 4));
                    if(statusDic.get("strength") > 0) player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 21, statusDic.get("strength") / 3));
                }
            }
        };
        runnable.runTaskTimer(this,0,20);

        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginCommand("status").setExecutor(new Cmds());
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
