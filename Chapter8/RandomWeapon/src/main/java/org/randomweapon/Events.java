package org.randomweapon;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Events implements Listener {

    @EventHandler
    void OnPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Block block = e.getClickedBlock();
            if(item.equals(RandomWeapon.instance.Teleporter) && RandomWeapon.instance.cooldown[0] == 0){
                player.teleport(new Location(player.getWorld(),
                                                block.getLocation().getX(),
                                                block.getLocation().getY()+1,
                                                block.getLocation().getZ(),
                                                player.getLocation().getYaw(),
                                                player.getLocation().getPitch()));
                RandomWeapon.instance.cooldown[0] = RandomWeapon.MAX_COOL;
        }
            else if(item.equals(RandomWeapon.instance.Exploder) && RandomWeapon.instance.cooldown[1] == 0){
                player.getWorld().createExplosion(block.getLocation(), 5);
                RandomWeapon.instance.cooldown[1] = RandomWeapon.MAX_COOL;
            }
        }
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if(item.equals(RandomWeapon.instance.Warrior) && RandomWeapon.instance.cooldown[2] == 0){
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,200,2));
                RandomWeapon.instance.cooldown[2] = RandomWeapon.MAX_COOL;
            }
        }
    }
}
