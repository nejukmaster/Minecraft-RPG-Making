package org.minecraftrpg;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class RegisterCustomEvents implements Listener {

    @EventHandler
    void onEntityDamaged(EntityDamageByEntityEvent e) {
        Entity victim = e.getEntity();
        Entity damager = e.getDamager();
        if (damager instanceof Player) {
            UUID uuid = victim.getUniqueId();
            if (MinecraftRPG.instance.monsterManager.containsKey(uuid)) {
                Monster monster = MinecraftRPG.instance.monsterManager.get(uuid);
                PlayerStatus v_status = monster.getStatus();
                PlayerStatus d_status = MinecraftRPG.instance.player.getStatus();
                e.setDamage(e.getDamage() + d_status.Str - v_status.Def);
            }
        } else if (victim instanceof Player && MinecraftRPG.instance.monsterManager.containsKey(damager.getUniqueId())) {
            UUID uuid = damager.getUniqueId();
            Monster monster = MinecraftRPG.instance.monsterManager.get(uuid);
            PlayerStatus v_status = monster.getStatus();
            PlayerStatus d_status = MinecraftRPG.instance.player.getStatus();
            e.setDamage(e.getDamage() + v_status.Str - d_status.Def);
        }
    }

    @EventHandler
    void onEntityDeath(EntityDeathEvent e) {
        Entity victim = e.getEntity();
        Entity killer = e.getEntity().getKiller();
        if (killer != null) {
            UUID uuid = victim.getUniqueId();
            if (MinecraftRPG.instance.monsterManager.containsKey(uuid)) {
                Monster monster = MinecraftRPG.instance.monsterManager.get(uuid);
                PlayerStatus p_status = MinecraftRPG.instance.player.getStatus();
                for (ItemStack i : monster.getDropItems()) {
                    victim.getLocation().getWorld().dropItem(victim.getLocation(), i);
                }
                RPGPlayerExpChangeEvent event = new RPGPlayerExpChangeEvent(MinecraftRPG.instance.player,
                        p_status.playerExp,
                        p_status.playerExp + monster.getDropExp());
                p_status.playerExp += monster.getDropExp();
                Bukkit.getPluginManager().callEvent(event);
                MinecraftRPG.instance.monsterManager.remove(uuid);

                if (monster instanceof BossMonster) {
                    ((BossMonster) monster).defeated();
                }
            }
        }
    }

    @EventHandler
    void onRPGPlayerExpChange(RPGPlayerExpChangeEvent e) {
        RPGPlayer player = e.getPlayer();
        if (player.playerStatus.playerExp >= player.playerStatus.playerExpMax) {
            RPGPlayerLevelChangeEvent event = new RPGPlayerLevelChangeEvent(player,
                    player.getStatus().playerLevel,
                    player.getStatus().playerLevel + 1);
            player.playerStatus.playerLevel += 1;
            player.getStatus().playerExp -= player.getStatus().playerExpMax;
            Bukkit.getPluginManager().callEvent(event);
        }
    }
    @EventHandler
    void onPlayerMove(PlayerMoveEvent e) {
        Location sp = e.getFrom();
        Location ep = e.getTo();
        Player player = e.getPlayer();
        AreaManager stageManager = AreaManager.instance;
        if (stageManager.getStage(sp) != stageManager.getStage(ep)) {
            PlayerEnterAreaEvent event = new PlayerEnterAreaEvent(player, stageManager.getStage(ep), stageManager.getStage(sp), e);
            Bukkit.getPluginManager().callEvent(event);
        }
    }
    @EventHandler
    void onPlayerInteractEntity(PlayerInteractEntityEvent e){
        Entity entity = e.getRightClicked();
        Player player = e.getPlayer();
        if(MinecraftRPG.instance.npcManager.containsKey(entity.getUniqueId())){
            RPGPlayerNPCInteractEvent event = new RPGPlayerNPCInteractEvent(e,MinecraftRPG.instance.npcManager.get(entity.getUniqueId()),player);
            Bukkit.getPluginManager().callEvent(event);
        }
    }
    @EventHandler
    void onPlayerClickInventory(InventoryClickEvent e){
        Player player = (Player)e.getWhoClicked();
        Inventory inv = e.getInventory();
        ItemStack item = e.getCurrentItem();
        if(inv == MinecraftRPG.instance.shop.getInventory()){
            e.setCancelled(true);
            if(item != null){
                if(MinecraftRPG.instance.shop.isSell){
                    PlayerShopSellRequest event = new PlayerShopSellRequest(player,item.getType(),MinecraftRPG.instance.shop.getSell(item.getType()));
                    Bukkit.getPluginManager().callEvent(event);
                }
                else{
                    PlayerShopBuyRequest event = new PlayerShopBuyRequest(player,item.getType(),MinecraftRPG.instance.shop.getBuy(item.getType()));
                    Bukkit.getPluginManager().callEvent(event);
                }
            }
        }
    }
}
