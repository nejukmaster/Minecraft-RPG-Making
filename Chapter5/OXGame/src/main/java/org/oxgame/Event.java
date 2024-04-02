package org.oxgame;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Event implements Listener {

    @EventHandler
    void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        if(block != null && !block.getType().equals(Material.AIR)){
            if(e.getItem() != null && e.getItem().getType().equals(Material.WOODEN_AXE)) {
                if (OXGame.instance.oArea[0] == null) {
                    OXGame.instance.oArea[0] = block.getLocation();
                    player.sendMessage("O영역의 시작점이 "+block.getLocation().toString()+"으로 설정되었습니다.");
                } else {
                    OXGame.instance.oArea[1] = block.getLocation();
                    player.sendMessage("O영역의 끝점이 "+block.getLocation().toString()+"으로 설정되었습니다.");
                }
            }
            else if(e.getItem() != null && e.getItem().getType().equals(Material.STONE_AXE)){
                if(OXGame.instance.xArea[0] == null){
                    OXGame.instance.xArea[0] = block.getLocation();
                    player.sendMessage("X영역의 시작점이 "+block.getLocation().toString()+"으로 설정되었습니다.");
                }
                else{
                    OXGame.instance.xArea[1] = block.getLocation();
                    player.sendMessage("X영역의 끝점이 "+block.getLocation().toString()+"으로 설정되었습니다.");
                }
            }
        }
    }
}
