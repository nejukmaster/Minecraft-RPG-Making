package org.minecraftrpg;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SkillItem {
    Skill skill;
    int max_cool;
    int cool;
    ItemStack item;
    BukkitRunnable runnable;
    public SkillItem(Material material, Skill skill, int max_cool) {
        this.item = MinecraftRPG.createCustomItem(material, skill.getName(), 1);
        this.skill = skill;
        this.max_cool = max_cool;
        this.cool = 0;
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(cool > 0){
                    cool --;
                }
            }
        };
        runnable.runTaskTimer(MinecraftRPG.instance, 0,1);
        MinecraftRPG.instance.skillManager.put("test", this);
        MinecraftRPG.instance.skillItemManager.put(item,this);
    }
    public Skill getSkill(){ return skill; }
    public ItemStack getItem(){ return item; }
    public int getMaxCool(){ return max_cool; }
    public void use(LivingEntity caster){
        if(cool == 0){
            skill.cast(caster);
            cool = max_cool;
        }
    }
}
