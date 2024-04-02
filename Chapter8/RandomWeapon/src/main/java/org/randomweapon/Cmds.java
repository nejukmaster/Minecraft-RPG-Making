package org.randomweapon;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Cmds implements CommandExecutor {

    HashMap<String,Integer> map = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(strings[0].equalsIgnoreCase("give")){
                player.getInventory().addItem(RandomWeapon.instance.Teleporter);
                player.getInventory().addItem(RandomWeapon.instance.Exploder);
                player.getInventory().addItem(RandomWeapon.instance.Warrior);
            }
            else if(strings[0].equalsIgnoreCase("start")){
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.sendTitle("Game Start","",10 ,70,10);
                }
                RandomWeapon.instance.onGame = true;
            }
        }
        return false;
    }
}
