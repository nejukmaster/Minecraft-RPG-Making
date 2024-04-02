package org.statusmanager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Cmds implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(strings.length > 0){
                if(strings[0].equalsIgnoreCase("open")){
                    player.openInventory(StatusManager.instance.statusGUI);
                    ItemStack _sp = StatusManager.instance.sp_item;
                    _sp.setAmount(StatusManager.instance.sp);
                    StatusManager.instance.statusGUI.setItem(8, _sp);
                }
            }
        }
        return false;
    }
}
