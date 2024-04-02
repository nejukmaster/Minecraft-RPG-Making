package org.nejukmaster;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class Cmds implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player)commandSender;
            if(strings.length > 0){
                if(strings[0].equalsIgnoreCase("start")){
                    player.sendTitle("Game Start","제한시간동안 몹을 피해 살아남으세요!",10,70,10);
                    Bukkit.getBossBar(NamespacedKey.minecraft("random_mob_defence")).addPlayer(player);
                    Bukkit.getBossBar(NamespacedKey.minecraft("random_mob_defence")).setVisible(true);

                    RandomMobDefence.instance.gamePlayer = player;
                    RandomMobDefence.instance.onGame = true;
                    RandomMobDefence.instance.timer = RandomMobDefence.instance.MAX_TIMER;
                    player.getWorld().setTime(13000);   //밤으로 설정
                }
            }
        }
        return false;
    }
}
