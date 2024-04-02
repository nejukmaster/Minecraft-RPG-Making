package org.nejukmaster;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Events implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        if(RandomMobDefence.instance.onGame){
            player.sendTitle("Game Over","몬스터에게 죽었습니다.",10,70,10);
            RandomMobDefence.instance.onGame = false;
            Bukkit.getBossBar(NamespacedKey.minecraft("random_mob_defence")).setVisible(false);
        }
    }
}
