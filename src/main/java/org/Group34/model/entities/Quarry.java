package org.Group34.model.entities;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.enums.Color;
import org.Group34.model.items.Mineral;

import java.util.ArrayList;
import java.util.HashMap;

public class Quarry implements WalkAble {
    @Override
    public String toString() {
        return Color.GRAY + "Q" + Color.RESET;
    }

    private HashMap<Mineral, Integer> minerals = new HashMap<>();
    private int numOfMinerals = -1;

    public HashMap<Mineral, Integer> getMinerals() {
        return minerals;
    }

    public void addItem(Mineral mineral, int amount) {
        if (minerals.containsKey(mineral)) {
            minerals.replace(mineral, minerals.get(mineral) + amount);
        } else {
            minerals.put(mineral, amount);
        }
    }

    public Texture getTexture() {
        numOfMinerals++;
        numOfMinerals %= 81;
        ArrayList<Mineral> stones = new ArrayList<>(minerals.keySet());
        if (numOfMinerals < minerals.size()) {
            return stones.get(numOfMinerals).getTexture();
        }
        return new Texture("rock/Quarry_Boulder.png");
    }
}
