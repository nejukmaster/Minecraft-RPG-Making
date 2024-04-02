package org.escapemap;


import org.bukkit.Location;

public class Stage {
    String name;
    Location sp;
    Location ep;
    boolean passed;
    public Stage(String name, Location sp, Location ep){
        this.name = name;
        this.sp = new Location(sp.getWorld(),Math.min(sp.getX(),ep.getX()),Math.min(sp.getY(),ep.getY()),Math.min(sp.getZ(),ep.getZ()));
        this.ep = new Location(sp.getWorld(),Math.max(sp.getX(),ep.getX()),Math.max(sp.getY(),ep.getY()),Math.max(sp.getZ(),ep.getZ()));
    }
    public boolean isInner(Location p){
        if(sp.getX() <= p.getX() && p.getX() <= ep.getX()
                && sp.getY() <= p.getY() && p.getY() <= ep.getY()
                && sp.getZ() <= p.getZ() && p.getZ() <= ep.getZ()){
            return true;
        }
        else return false;
    }
    public String getName(){
        return name;
    }
    public boolean isPassed(){
        return passed;
    }
    public void setPassed(boolean p_bool){
        passed = p_bool;
    }
}
