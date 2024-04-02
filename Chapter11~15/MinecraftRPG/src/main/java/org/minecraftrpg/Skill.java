package org.minecraftrpg;

import org.bukkit.entity.LivingEntity;

public interface Skill{
    public void cast(LivingEntity cater);
    public String getName();
}
