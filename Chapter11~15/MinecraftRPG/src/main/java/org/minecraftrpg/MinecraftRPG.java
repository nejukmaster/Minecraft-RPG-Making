package org.minecraftrpg;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class MinecraftRPG extends JavaPlugin {

    public static MinecraftRPG instance;

    public RPGPlayer player;
    public HashMap<UUID, Monster> monsterManager = new HashMap<>();
    public HashMap<String, SkillItem> skillManager = new HashMap<>();
    public HashMap<ItemStack, SkillItem> skillItemManager = new HashMap<>();
    public HashMap<UUID, NPC> npcManager = new HashMap<>();
    public Inventory playerStatusGUI;
    public Location sp,ep;
    public Shop shop;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Bukkit.getPluginCommand("rpg").setExecutor(new Cmds());
        Bukkit.getPluginManager().registerEvents(new RegisterCustomEvents(), this);
        Bukkit.getPluginManager().registerEvents(new Events(), this);

        playerStatusGUI = Bukkit.createInventory(null, 9, "Status");

        new AreaManager();
        new SkillItem(Material.DIAMOND_SWORD, new TestSkill(), 20);
        shop = new Shop();
    }
    public void updateStatusGUI(){
        playerStatusGUI.setItem(0,createCustomItem(Material.NETHER_STAR,
                                                    "LV : "+player.getStatus().playerLevel,
                                            1));
        playerStatusGUI.setItem(1,createCustomItem(Material.EMERALD,
                                                "LV : "+player.getStatus().playerExp + "/"+player.getStatus().playerExpMax,
                                        1));
        playerStatusGUI.setItem(2,createCustomItem(Material.BLACK_STAINED_GLASS_PANE,"", 0));
        playerStatusGUI.setItem(3,createCustomItem(Material.DIAMOND_SWORD,
                                                "STR : "+player.getStatus().Str,
                                        1));
        playerStatusGUI.setItem(4,createCustomItem(Material.FEATHER,
                                                    "AGILE : "+player.getStatus().Agile,
                                            1));
        playerStatusGUI.setItem(5,createCustomItem(Material.SHIELD,
                                                    "DEF : "+player.getStatus().Def,
                                                1));
        playerStatusGUI.setItem(6,createCustomItem(Material.BLACK_STAINED_GLASS_PANE,"", 0));
        playerStatusGUI.setItem(7,createCustomItem(Material.BLACK_STAINED_GLASS_PANE,"", 0));
        playerStatusGUI.setItem(8,createCustomItem(Material.EMERALD,"SP : "+player.getStatus().Sp+"\nMoney:"+player.money, 1));
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
