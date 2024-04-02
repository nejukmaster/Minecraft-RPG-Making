package org.oxgame;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class OXGame extends JavaPlugin {

    public static OXGame instance;
    public String[] questions = {"우리나라의 국보 1호 이름은 숭례문 이다.",
                                    "6.25전쟁은 북한의 남침으로 발발되었다.",
                                    "기업 애플은 미국 소재의 기업이다.",
                                    "미국의 수도는 뉴욕이다.",
                                    "콰르텟은 3개의 독주악기로 연주하는 3중주를 의미하는 말이다."};
    public boolean[] answer = {true, true, true, false, false};
    public String[] descriptions = {"우리나라의 국보 1호는 숭례문이다.",
                                    "6.25전쟁은 북한의 남침으로 시작되었다.",
                                    "기업 애플은 미국 소재의 기업이다.",
                                    "미국의 수도는 뉴욕이 아닌 워싱턴 D.C이다.",
                                    "콰르텟은 4개의 4중주를 의미하는 말이며, 3중주는 트리오라고 한다."};
    public Location[] oArea = new Location[2];
    public Location[] xArea = new Location[2];
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Event(), this);
        getServer().getPluginCommand("oxgame").setExecutor(new Cmd());
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
