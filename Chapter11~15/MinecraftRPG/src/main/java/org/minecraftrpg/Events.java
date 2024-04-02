package org.minecraftrpg;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e) {
        MinecraftRPG.instance.player = new RPGPlayer(e.getPlayer(), new PlayerStatus());
    }

    @EventHandler
    void onRPGPlayerLevelChange(RPGPlayerLevelChangeEvent e) {
        e.getRPGPlayer().playerStatus.Sp += 1;
    }

    @EventHandler
    void onPlayerClickInventory(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Inventory inventory = e.getClickedInventory();
        ItemStack item = e.getCurrentItem();
        if (inventory == MinecraftRPG.instance.playerStatusGUI) {
            e.setCancelled(true);
            if (item != null && MinecraftRPG.instance.player.getStatus().Sp >= 1) {
                switch (item.getType()) {
                    case DIAMOND_SWORD:
                        MinecraftRPG.instance.player.getStatus().Str += 1;
                        MinecraftRPG.instance.player.getStatus().Sp -= 1;
                        break;
                    case FEATHER:
                        MinecraftRPG.instance.player.getStatus().Agile += 1;
                        MinecraftRPG.instance.player.getStatus().Sp -= 1;
                        break;
                    case SHIELD:
                        MinecraftRPG.instance.player.getStatus().Def += 1;
                        MinecraftRPG.instance.player.getStatus().Sp -= 1;
                        break;
                }
                MinecraftRPG.instance.updateStatusGUI();
            }
        }
    }

    @EventHandler
    void onPlayerEnterArea(PlayerEnterAreaEvent e) {
        if (e.getTo() != null) e.getTo().onPlayerEnter(e.getPlayer());
        if (e.getFrom() != null) e.getFrom().onPlayerExit(e.getPlayer());
    }
    @EventHandler
    void onPlayerInteract(PlayerInteractEvent e){
        Block block = e.getClickedBlock();
        Player player = e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Action action = e.getAction();
        if(item.getType().equals(Material.BLAZE_ROD)) {
            e.setCancelled(true);
            if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                MinecraftRPG.instance.ep = block.getLocation();
                player.sendMessage("두번째 좌표가 설정되었습니다.");
            } else if (action.equals(Action.LEFT_CLICK_BLOCK)) {
                MinecraftRPG.instance.sp = block.getLocation();
                player.sendMessage("첫번째 좌표가 설정되었습니다.");
            }
        }
        if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)){
            if(MinecraftRPG.instance.skillItemManager.containsKey(item)){
                MinecraftRPG.instance.skillItemManager.get(item).use(player);
            }
        }
    }
    @EventHandler
    void onPlayerInteractNPC(RPGPlayerNPCInteractEvent e){
        Player player = e.getPlayer();
        NPC npc = e.getNpc();
        player.spigot().sendMessage(npc.getScript());
    }
    @EventHandler
    void onPlayerSellRequest(PlayerShopSellRequest e){
        int idx = -1;
        for(int i = 0; i < e.getPlayer().getInventory().getSize(); i ++){
            ItemStack item = e.getPlayer().getInventory().getItem(i);
            if(item != null && item.getType().equals(e.getItemType())){
                idx = i;
                break;
            }
        }
        if(idx != -1){
            e.getPlayer().getInventory().getItem(idx).setAmount(e.getPlayer().getInventory().getItem(idx).getAmount()-1);
            MinecraftRPG.instance.player.money+=e.price;
        }
    }
    @EventHandler
    void onPlayerBuyRequest(PlayerShopBuyRequest e){
        int money = MinecraftRPG.instance.player.money;
        if(money >= e.getPrice()){
            MinecraftRPG.instance.player.getPlayer().getInventory().addItem(new ItemStack(e.getItemType(),1));
            MinecraftRPG.instance.player.money -= e.getPrice();
        }
    }
}
