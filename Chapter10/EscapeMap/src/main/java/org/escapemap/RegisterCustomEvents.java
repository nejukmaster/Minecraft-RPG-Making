package org.escapemap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class RegisterCustomEvents implements Listener {

    @EventHandler
    void onPlayerMove(PlayerMoveEvent e){
        Location sp = e.getFrom();
        Location ep = e.getTo();
        Player player = e.getPlayer();
        StageManager stageManager = StageManager.instance;
        if(stageManager.getStage(sp) != stageManager.getStage(ep)){
            PlayerEnterStageEvent event = new PlayerEnterStageEvent(player, stageManager.getStage(ep),stageManager.getStage(sp),e);
            Bukkit.getPluginManager().callEvent(event);
        }
    }
}
