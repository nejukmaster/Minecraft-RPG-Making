package org.minecraftrpg;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Cmds implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(strings.length >0) {
                if(strings[0].equalsIgnoreCase("status")){
                    MinecraftRPG.instance.updateStatusGUI();
                    player.openInventory(MinecraftRPG.instance.playerStatusGUI);
                }
                if(strings[0].equalsIgnoreCase("village")){
                    if(strings.length < 2){
                        player.sendMessage("/rpg village [마을 이름]");
                    }
                    else{
                        if(AreaManager.instance.addStage(new Village(strings[1],MinecraftRPG.instance.sp, MinecraftRPG.instance.ep))){
                            player.sendMessage("마을 "+strings[1]+"을(를) 성공적으로 생성하였습니다.");
                        }
                        else{
                            player.sendMessage(ChatColor.RED + "같은 이름의 마을이 이미 있습니다!");
                        }
                    }
                }
                if(strings[0].equalsIgnoreCase("warrior")){
                    TextComponent tc_1 = new TextComponent("\n\n\n\n\n\n\n\nWarrior로 전직하시겠습니까?\n");
                    TextComponent tc_2 = new TextComponent("Yes");
                    TextComponent tc_3 = new TextComponent("No");
                    tc_2.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                    tc_2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/rpg former _warrior"));
                    tc_3.setColor(net.md_5.bungee.api.ChatColor.RED);
                    tc_3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg former _decline"));
                    NPC npc = new NPC(new TextComponent[]{tc_1, new TextComponent("["),
                                                        tc_2,new TextComponent("/"), tc_3,
                                                        new TextComponent("]")},"former_warrior");
                    MinecraftRPG.instance.npcManager.put(npc.spawn(player.getLocation()),npc);
                }
                if(strings[0].equalsIgnoreCase("former")){
                    if(strings[1].equalsIgnoreCase("_warrior")){
                        PlayerStatus status = MinecraftRPG.instance.player.getStatus();
                        if(status.Str >= 6 && MinecraftRPG.instance.player.job == Job.ADVENTURER){
                            MinecraftRPG.instance.player.getPlayer().sendMessage("\n\n\n\n\n\n\n\n\nWarrior로 전직을 성공했습니다!");
                            MinecraftRPG.instance.player.setJob(Job.WARRIOR);
                            player.getInventory().addItem(MinecraftRPG.instance.skillManager.get("test").getItem());
                        }
                        else{
                            MinecraftRPG.instance.player.getPlayer().sendMessage("\n\n\n\n\n\n\n\n\n전직할 수 없습니다.");
                        }
                    }
                }
                if(strings[0].equalsIgnoreCase("spawn")){
                    if(strings.length > 1){
                        if(strings[1].equalsIgnoreCase("zombie")){
                            PlayerStatus mobstatus = new PlayerStatus();
                            mobstatus.Str = 2;
                            mobstatus.Def = 2;
                            Monster monster = new Monster(EntityType.ZOMBIE,
                                                            mobstatus,
                                                            new ItemStack[]{new ItemStack(Material.COAL, 2)},
                                                            10);
                            MinecraftRPG.instance.monsterManager.put(monster.spawn(player.getLocation()),monster);
                        }
                        else if(strings[1].equalsIgnoreCase("spider")){
                            PlayerStatus mobstatus = new PlayerStatus();
                            mobstatus.Str = 5;
                            Monster monster = new Monster(EntityType.SPIDER,
                                                            mobstatus,
                                                            new ItemStack[]{new ItemStack(Material.IRON_INGOT,1)},
                                                            20);
                            MinecraftRPG.instance.monsterManager.put(monster.spawn(player.getLocation()),monster);
                        }
                        else if(strings[1].equalsIgnoreCase("drowned")){
                            PlayerStatus mobstatus = new PlayerStatus();
                            mobstatus.Str = 5;
                            mobstatus.Def = 4;
                            Monster monster = new Monster(EntityType.DROWNED,
                                                            mobstatus,
                                                            new ItemStack[]{new ItemStack(Material.IRON_INGOT,2)},
                                                            45);
                            MinecraftRPG.instance.monsterManager.put(monster.spawn(player.getLocation()),monster);
                        }
                    }
                }
                if(strings[0].equalsIgnoreCase("npc")){
                    if(strings.length > 2){
                        String script = "";
                        for(int i = 2; i < strings.length; i ++){
                            script += strings[i];
                        }
                        NPC npc = new NPC(new TextComponent[]{new TextComponent(script)},
                                            strings[1]);
                        MinecraftRPG.instance.npcManager.put(npc.spawn(player.getLocation()),npc);
                    }
                    else{
                        player.sendMessage("/rpg npc [name] [script]");
                    }
                }
                if(strings[0].equalsIgnoreCase("boss")){
                    if(strings.length > 1){
                        if(strings[1].equalsIgnoreCase("ZombieKing")){
                            PlayerStatus mobstatus = new PlayerStatus();
                            mobstatus.Str = 15;
                            mobstatus.Def = 25;
                            if(AreaManager.instance.addStage(new BossArena("ZombieKing_Arena",
                                    MinecraftRPG.instance.sp,
                                    MinecraftRPG.instance.ep,
                                    new BossMonster(EntityType.ZOMBIE,
                                            mobstatus,
                                            new ItemStack[]{new ItemStack(Material.EMERALD,10)},
                                            20,
                                            "ZombieKing"),
                                    new Location(player.getWorld(),
                                            (MinecraftRPG.instance.sp.getX()+MinecraftRPG.instance.ep.getX())/2,
                                            (MinecraftRPG.instance.sp.getY()+MinecraftRPG.instance.ep.getY())/2,
                                            (MinecraftRPG.instance.sp.getZ()+MinecraftRPG.instance.ep.getZ())/2)))) {
                                player.sendMessage("\"Zombie King\" 아레나를 성공적으로 생성했습니다.");
                            }
                        }
                    }
                }
                if(strings[0].equalsIgnoreCase("merchant")){
                    if(strings.length == 1) {
                        TextComponent tc_1 = new TextComponent("\n\n\n\n\n\n\n\n무엇을 하시겠습니까?\n");
                        TextComponent tc_2 = new TextComponent("구매");
                        TextComponent tc_3 = new TextComponent("판매");
                        tc_2.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                        tc_2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg merchant _buy"));
                        tc_3.setColor(net.md_5.bungee.api.ChatColor.RED);
                        tc_3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rpg merchant _sell"));
                        NPC npc = new NPC(new TextComponent[]{tc_1, new TextComponent("["),
                                tc_2, new TextComponent("/"), tc_3,
                                new TextComponent("]")}, "merchant");
                        MinecraftRPG.instance.npcManager.put(npc.spawn(player.getLocation()), npc);
                    }
                    if(strings.length > 1){
                        if(strings[1].equalsIgnoreCase("_buy")){
                            MinecraftRPG.instance.shop.initInventoryToBuy();
                            MinecraftRPG.instance.player.getPlayer().openInventory(MinecraftRPG.instance.shop.getInventory());
                        }
                        else if(strings[1].equalsIgnoreCase("_sell")){
                            MinecraftRPG.instance.shop.initInventoryToSell();
                            MinecraftRPG.instance.player.getPlayer().openInventory(MinecraftRPG.instance.shop.getInventory());
                        }
                    }
                }
            }
        }
        return false;
    }
}
