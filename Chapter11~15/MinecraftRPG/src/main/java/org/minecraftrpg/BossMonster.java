package org.minecraftrpg;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class BossMonster extends Monster{
    BossBar bossBar;
    String name;
    BukkitRunnable runnable;
    LivingEntity entity;
    boolean isDefeated;
    public BossMonster(EntityType type, PlayerStatus status, ItemStack[] dropItems, int dropExp, String name) {
        super(type, status, dropItems, dropExp);
        this.name = name;
        bossBar = Bukkit.createBossBar( NamespacedKey.minecraft("boss_"+name.toLowerCase()),name, BarColor.PURPLE, BarStyle.SOLID);
    }
    public UUID spawn(Location location){
        if(location.getWorld() == null) return null;
        entity = (LivingEntity) location.getWorld().spawnEntity(location,type);
        runnable = new BukkitRunnable(){
            @Override
            public void run() {
                bossBar.setProgress(entity.getHealth()/entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            }
        };
        runnable.runTaskTimer(MinecraftRPG.instance,0,1);
        return entity.getUniqueId();
    }
    public void despawn(){
        runnable.cancel();
        entity.remove();
    }
    public void defeated(){
        runnable.cancel();
        isDefeated = true;
    }
}
