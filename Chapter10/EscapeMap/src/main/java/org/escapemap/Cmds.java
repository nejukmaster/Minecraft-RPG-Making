package org.escapemap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmds implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(strings[0].equalsIgnoreCase("create")){
                if(strings.length < 2){
                    player.sendMessage("/escape create [스테이지 이름]");
                }
                else{
                    if(StageManager.instance.addStage(new Stage(strings[1],EscapeMap.instance.sp, EscapeMap.instance.ep))){
                        player.sendMessage("스테이지 "+strings[1]+"을(를) 성공적으로 생성하였습니다.");
                    }
                    else{
                        player.sendMessage(ChatColor.RED + "같은 이름의 스테이지가 이미 있습니다!");
                    }
                }
            }
        }
        return false;
    }
}
