package org.minecraftrpg;

import org.bukkit.Location;
import java.util.HashMap;

public class AreaManager {
    public static AreaManager instance;
    HashMap<String,Area> areas = new HashMap<>();
    public AreaManager(){
        instance = this;
    }

    public boolean addStage(Area area){
        if(areas.containsKey(area.getName())){
            return false;
        }
        else{
            areas.put(area.getName(), area);
            return true;
        }
    }
    public Area getArea(String name){
        return areas.get(name);
    }
    public Area getStage(Location loc){
        Area result = null;
        for (Area a: areas.values()) {
            if(a.isInArea(loc)){
                result = a;
                break;
            }
        }
        return result;
    }
}
