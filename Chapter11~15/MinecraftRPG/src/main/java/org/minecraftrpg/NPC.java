package org.minecraftrpg;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class NPC {

    TextComponent[] script;
    String name;

    public NPC(TextComponent[] script, String name){
        this.script = script;
        this.name = name;
    }

    public UUID spawn(Location loc){
        LivingEntity entity = (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        entity.setAI(false);
        entity.setInvulnerable(true);
        return entity.getUniqueId();
    }
    public TextComponent[] getScript(){
        return script;
    }
    public String getName(){
        return name;
    }
}
