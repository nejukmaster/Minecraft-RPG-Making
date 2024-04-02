package org.statusmanager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {

    @EventHandler
    void onPlayerLevelUp(PlayerLevelChangeEvent e){
        Player player = e.getPlayer();
        if(e.getNewLevel() > e.getOldLevel()){
            StatusManager.instance.sp += e.getNewLevel() - e.getOldLevel();
        }
    }

    @EventHandler
    void onPlayerClickInventory(InventoryClickEvent e){
        Player player = (Player)e.getWhoClicked();
        ItemStack _item = e.getCurrentItem();
        if(e.getInventory().equals(StatusManager.instance.statusGUI)){
            e.setCancelled(true);
            if(_item != null && StatusManager.instance.sp > 0){
                ItemStack _sp;
                switch (_item.getType()){
                    case REDSTONE:
                        int _health = StatusManager.instance.statusDic.get("health");
                        _health += 1;
                        StatusManager.instance.statusDic.put("health", _health);
                        StatusManager.instance.statusGUI.getItem(0).setAmount(_health);
                        StatusManager.instance.sp -= 1;
                        _sp = StatusManager.instance.sp_item;
                        _sp.setAmount(StatusManager.instance.sp);
                        e.getInventory().setItem(8, _sp);
                        break;
                    case IRON_INGOT:
                        int _strength = StatusManager.instance.statusDic.get("strength");
                        _strength += 1;
                        StatusManager.instance.statusDic.put("strength", _strength);
                        StatusManager.instance.statusGUI.getItem(1).setAmount(_strength);
                        StatusManager.instance.sp -= 1;
                        _sp = StatusManager.instance.sp_item;
                        _sp.setAmount(StatusManager.instance.sp);
                        e.getInventory().setItem(8, _sp);
                        break;
                    case FEATHER:
                        int _speed = StatusManager.instance.statusDic.get("speed");
                        _speed += 1;
                        StatusManager.instance.statusDic.put("speed", _speed);
                        StatusManager.instance.statusGUI.getItem(2).setAmount(_speed);
                        StatusManager.instance.sp -= 1;
                        _sp = StatusManager.instance.sp_item;
                        _sp.setAmount(StatusManager.instance.sp);
                        e.getInventory().setItem(8, _sp);
                        break;
                }
            }
        }
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e){
        StatusManager.instance.player = e.getPlayer();
    }
}
