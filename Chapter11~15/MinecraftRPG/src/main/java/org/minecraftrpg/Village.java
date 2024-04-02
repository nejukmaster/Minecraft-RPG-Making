package org.minecraftrpg;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Village implements Area{

    String name;
    Location sp;
    Location ep;
    AreaType type = AreaType.VILLAGE;

    public Village(String name, Location sp, Location ep){
        this.name = name;
        this.sp = new Location(sp.getWorld(),Math.min(sp.getX(),ep.getX()),Math.min(sp.getY(),ep.getY()),Math.min(sp.getZ(),ep.getZ()));
        this.ep = new Location(sp.getWorld(),Math.max(sp.getX(),ep.getX()),Math.max(sp.getY(),ep.getY()),Math.max(sp.getZ(),ep.getZ()));
    }
    @Override
    public void onPlayerEnter(Player player) {
        player.sendTitle(name,"",10,70,10);
    }

    @Override
    public void onPlayerExit(Player player) {

    }

    @Override
    public boolean isInArea(Location target) {
        if(sp.getX() <= target.getX() && target.getX() <= ep.getX()
                && sp.getY() <= target.getY() && target.getY() <= ep.getY()
                && sp.getZ() <= target.getZ() && target.getZ() <= ep.getZ()){
            return true;
        }
        else return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AreaType getType() {
        return type;
    }
}
