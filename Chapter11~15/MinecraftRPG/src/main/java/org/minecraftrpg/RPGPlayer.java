package org.minecraftrpg;

import org.bukkit.entity.Player;

public class RPGPlayer {
    Player player;
    PlayerStatus playerStatus;
    Job job = Job.ADVENTURER;
    int money = 0;

    public RPGPlayer(Player player, PlayerStatus playerStatus){
        this.player = player;
        this.playerStatus = playerStatus;
    }

    public Player getPlayer(){
        return player;
    }

    public PlayerStatus getStatus(){
        return playerStatus;
    }

    public Job getJob(){return job;}
    public void setJob(Job newjob){job = newjob;}
}
