package org.oxgame;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmd implements CommandExecutor {
    int question_num = 0;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(strings[0].equalsIgnoreCase("start")){
                player.sendMessage(OXGame.instance.questions[question_num]);
            }
            else if(strings[0].equalsIgnoreCase("check")){
                Location p_loc = player.getLocation();
                if(p_loc.getX() > Math.min(OXGame.instance.oArea[0].getX(),OXGame.instance.oArea[1].getX())
                        && p_loc.getX() < Math.max(OXGame.instance.oArea[0].getX(),OXGame.instance.oArea[1].getX())
                        && p_loc.getZ() > Math.min(OXGame.instance.oArea[0].getZ(),OXGame.instance.oArea[1].getZ())
                        && p_loc.getZ() < Math.max(OXGame.instance.oArea[0].getZ(),OXGame.instance.oArea[1].getZ())){
                    if(OXGame.instance.answer[question_num]){
                        player.sendMessage("정답!");
                    } else {
                        player.sendMessage(("오답..."));
                    }
                    player.sendMessage(OXGame.instance.descriptions[question_num]);
                    question_num += 1;
                    question_num = question_num%OXGame.instance.questions.length;
                }
                else if(p_loc.getX() > Math.min(OXGame.instance.xArea[0].getX(),OXGame.instance.xArea[1].getX())
                        && p_loc.getX() < Math.max(OXGame.instance.xArea[0].getX(),OXGame.instance.xArea[1].getX())
                        && p_loc.getZ() > Math.min(OXGame.instance.xArea[0].getZ(),OXGame.instance.xArea[1].getZ())
                        && p_loc.getZ() < Math.max(OXGame.instance.xArea[0].getZ(),OXGame.instance.xArea[1].getZ())){
                    if(OXGame.instance.answer[question_num]){
                        player.sendMessage("오답...");
                    }
                    else{
                        player.sendMessage("정답!");
                    }
                    player.sendMessage(OXGame.instance.descriptions[question_num]);
                    question_num += 1;
                    question_num = question_num%OXGame.instance.questions.length;
                }
            }
        }
        return false;
    }
}
