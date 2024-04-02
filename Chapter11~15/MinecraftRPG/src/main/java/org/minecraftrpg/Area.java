package org.minecraftrpg;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Area {
    public void onPlayerEnter(Player player);
    public void onPlayerExit(Player player);
    public boolean isInArea(Location target);
    public String getName();
    public AreaType getType();
}
