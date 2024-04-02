package org.learnplugin.ch01_02.blockcrash;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmds implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player)commandSender;
            BlockCrash.instance.startTime = player.getWorld().getGameTime();

            BlockCrash.instance.blockList.add(Material.GRASS_BLOCK);
            BlockCrash.instance.blockList.add(Material.GRAY_WOOL);
            BlockCrash.instance.blockList.add(Material.STONE);
            BlockCrash.instance.blockList.add(Material.OAK_PLANKS);

            BlockCrash.instance.onGame = true;

            player.sendMessage("다음 블럭을 순서대로 부숴주세요.");
            player.sendMessage(BlockCrash.instance.blockList.toString());
        }
        return false;
    }
}
