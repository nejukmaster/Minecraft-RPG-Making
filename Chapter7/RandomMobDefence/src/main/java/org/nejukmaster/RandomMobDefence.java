package org.nejukmaster;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomMobDefence extends JavaPlugin {

    public static RandomMobDefence instance;

    public int MAX_TIMER = 120;
    public float ROUND = 7.0f;
    public int timer = 0;
    public Player gamePlayer;
    public boolean onGame = false;

    private int spawn_interval = 5;
    private EntityType[] mob_list = {EntityType.ZOMBIE,EntityType.SKELETON,EntityType.CREEPER,EntityType.WITHER_SKELETON,EntityType.ENDERMAN};
    @Override
    public void onEnable() {

        instance = this;

        getLogger().info("Random Mob Defece!");
        Bukkit.getPluginCommand("rmd").setExecutor(new Cmds());
        Bukkit.getPluginManager().registerEvents(new Events(),this);

        RepeatingTask repeatingTask = new RepeatingTask(){
          @Override
          public void run(){
              if(onGame) {
                  if (timer > 0) {
                      if (timer % spawn_interval == 0) {
                          Location p_loc = gamePlayer.getLocation();
                          gamePlayer.getWorld().spawnEntity(new Location(gamePlayer.getWorld(),
                                                                        p_loc.getX() + ROUND * Math.cos(Math.random() * Math.PI),
                                                                            p_loc.getY(),
                                                                        p_loc.getZ() + ROUND * Math.cos(Math.random() * Math.PI)),
                                                            mob_list[(int) (Math.random() * mob_list.length)]);
                      }
                      timer--;
                      Bukkit.getBossBar(NamespacedKey.minecraft("random_mob_defence")).setProgress(timer / (float) MAX_TIMER);
                  }
                  else if (timer == 0) {
                      gamePlayer.sendTitle("Game Clear!", "당신이 이겼습니다.", 10, 70, 10);
                      Bukkit.getBossBar(NamespacedKey.minecraft("random_mob_defence")).setVisible(false);
                      onGame = false;
                  }
              }
          }
        };
        repeatingTask.runTaskTimer(this, 0, 20);

        Bukkit.createBossBar(NamespacedKey.minecraft("random_mob_defence"),"남은시간", BarColor.RED, BarStyle.SEGMENTED_20);

    }

    @Override
    public void onDisable() {
        Bukkit.removeBossBar(NamespacedKey.minecraft("random_mob_defence"));
    }
}
