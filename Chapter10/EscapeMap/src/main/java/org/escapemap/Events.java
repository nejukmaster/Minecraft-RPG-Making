package org.escapemap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {
    @EventHandler
    void onPlayerEnterStage(PlayerEnterStageEvent e){
        if(e.getFrom() == null || e.getFrom().isPassed()){
            if(e.getTo() != null)
                e.getPlayer().sendMessage(e.getTo().getName()+"에 입장하셨습니다.");
        }
        else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    void onPlayerInteract(PlayerInteractEvent e){
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Action action = e.getAction();
        if(item.getType().equals(Material.BLAZE_ROD)) {
            e.setCancelled(true);
            if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                EscapeMap.instance.ep = block.getLocation();
                player.sendMessage("두번째 좌표가 설정되었습니다.");
            } else if (action.equals(Action.LEFT_CLICK_BLOCK)) {
                EscapeMap.instance.sp = block.getLocation();
                player.sendMessage("첫번째 좌표가 설정되었습니다.");
            }
        }
    }

    @EventHandler
    void onPlayerBreakBlock(BlockBreakEvent e){
        Player player = e.getPlayer();
        Block block = e.getBlock();
        if(StageManager.instance.getStage(player.getLocation()) != null){
            Stage stage = StageManager.instance.getStage(player.getLocation());
            if(stage.getName().equals("stage1") && block.getType().equals(Material.EMERALD_BLOCK)){
                stage.setPassed(true);
            }
        }
    }
    int skeleton_killcount = 0;
    @EventHandler
    void onEntityDeath(EntityDeathEvent e){
        LivingEntity entity = e.getEntity();
        Player murderer = entity.getKiller();
        if(entity.getType() == EntityType.SKELETON && murderer != null){
            Stage stage = StageManager.instance.getStage(murderer.getLocation());
            if(stage != null && stage.getName().equals("stage2")){
                skeleton_killcount ++;
                if(skeleton_killcount >= 5){
                    stage.setPassed(true);
                }
            }
        }
    }
    @EventHandler
    void onPlayerMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        if(StageManager.instance.getStage(player.getLocation()) != null){
            Stage stage = StageManager.instance.getStage(player.getLocation());
            if(stage.getName().equals("stage3")){
                if(e.getTo() == null) return;
                Location loc = new Location(player.getWorld(),
                                            player.getLocation().getX(),
                                            player.getLocation().getY()-1.,
                                            player.getLocation().getZ());
                if(loc.getBlock().getType().equals(Material.GOLD_BLOCK)){
                    stage.setPassed(true);
                }
            }
        }
    }
}
