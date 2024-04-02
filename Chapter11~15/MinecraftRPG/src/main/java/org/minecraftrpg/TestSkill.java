package org.minecraftrpg;

import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class TestSkill implements Skill{
    @Override
    public void cast(LivingEntity cater) {
        Vector dir = cater.getLocation().getDirection();
        cater.getWorld().createExplosion(cater.getLocation().clone().add(0,1,0).add(dir.multiply(2)),1,false,false);
    }

    @Override
    public String getName() {
        return "test";
    }
}
