package org.escapemap;

import org.bukkit.Location;
import java.util.HashMap;

public class StageManager {
    public static StageManager instance;
    HashMap<String,Stage> stages = new HashMap<>();
    public StageManager(){
        instance = this;
    }

    public boolean addStage(Stage stage){
        if(stages.containsKey(stage.name)){
            return false;
        }
        else{
            stages.put(stage.name, stage);
            return true;
        }
    }
    public Stage getStage(String name){
        return stages.get(name);
    }
    public Stage getStage(Location loc){
        Stage result = null;
        for (Stage s: stages.values()) {
            if(s.isInner(loc)){
                result = s;
                break;
            }
        }
        return result;
    }
}
