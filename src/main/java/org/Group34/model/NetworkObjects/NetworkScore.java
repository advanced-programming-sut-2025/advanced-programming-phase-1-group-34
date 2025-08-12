package org.Group34.model.NetworkObjects;

import java.io.Serializable;

public class NetworkScore implements Serializable {
    private String player;
    private int money;
    private int skill;

    public NetworkScore(String player, int money, int skill) {
        this.player = player;
        this.money = money;
        this.skill = skill;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }
}
