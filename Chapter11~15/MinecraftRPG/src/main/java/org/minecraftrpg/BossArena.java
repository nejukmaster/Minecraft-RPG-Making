package org.minecraftrpg;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.UUID;

public class BossArena implements Area{
    AreaType type = AreaType.BOSS_ARENA;
    BossMonster boss;
    UUID bossUUID;
    Location bossSpawnLocation;
    String name;
    Location sp;
    Location ep;
    public BossArena(String name, Location sp, Location ep, BossMonster boss, Location bossSpawnLocation){
        this.boss = boss;
        this.bossSpawnLocation = bossSpawnLocation;
        this.name = name;
        this.sp = new Location(sp.getWorld(),Math.min(sp.getX(),ep.getX()),Math.min(sp.getY(),ep.getY()),Math.min(sp.getZ(),ep.getZ()));
        this.ep = new Location(sp.getWorld(),Math.max(sp.getX(),ep.getX()),Math.max(sp.getY(),ep.getY()),Math.max(sp.getZ(),ep.getZ()));
    }
    @Override
    public void onPlayerEnter(Player player) {
        if(boss.isDefeated) return;
        bossUUID = boss.spawn(bossSpawnLocation);
        MinecraftRPG.instance.monsterManager.put(bossUUID, boss);
        boss.bossBar.addPlayer(player);
    }
    @Override
    public void onPlayerExit(Player player) {
        boss.bossBar.removePlayer(player);
        boss.despawn();
        MinecraftRPG.instance.monsterManager.remove(bossUUID);
    }
    @Override
    public boolean isInArea(Location target) {
        if(sp.getX() <= target.getX() && target.getX() <= ep.getX()
                && sp.getY() <= target.getY() && target.getY() <= ep.getY()
                && sp.getZ() <= target.getZ() && target.getZ() <= ep.getZ()){
            return true;
        }
        else return false;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public AreaType getType() {
        return type;
    }
}
