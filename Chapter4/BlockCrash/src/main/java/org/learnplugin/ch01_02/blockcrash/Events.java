package org.learnplugin.ch01_02.blockcrash;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Events implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(BlockCrash.instance.onGame){
            Player player = e.getPlayer();
            Block block = e.getBlock();
            if(block.getType() == BlockCrash.instance.blockList.get(0)){
                BlockCrash.instance.blockList.remove(0);
                player.sendMessage(BlockCrash.instance.blockList.toString());
                if(BlockCrash.instance.blockList.size() == 0){
                    player.sendMessage("Game Clear!");
                    player.sendMessage("걸린시간: "+String.valueOf(player.getWorld().getGameTime() - BlockCrash.instance.startTime));
                    BlockCrash.instance.onGame = false;
                }
            }
            else{
                player.sendMessage("이 블록이 아닙니다!");
            }
        }
    }
}
