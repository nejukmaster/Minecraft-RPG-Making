package org.escapemap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class EscapeMap extends JavaPlugin {
    public static EscapeMap instance;

    public Location sp;
    public Location ep;
    @Override
    public void onEnable() {
        instance = this;
        new StageManager();
        Bukkit.getPluginManager().registerEvents(new RegisterCustomEvents(), this);
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginCommand("escape").setExecutor(new Cmds());
    }

    @Override
    public void onDisable() {
    }
}
