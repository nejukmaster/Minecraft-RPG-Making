package org.learnplugin.ch01_02.blockcrash;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class BlockCrash extends JavaPlugin {

    public long startTime = 0;
    public ArrayList<Material> blockList = new ArrayList<>();
    public static BlockCrash instance;
    public boolean onGame = false;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginCommand("blockcrash").setExecutor(new Cmds());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
