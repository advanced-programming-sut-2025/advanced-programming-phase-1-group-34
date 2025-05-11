package org.Group34.model.items.tools;

import org.Group34.model.items.Item;

import java.util.HashMap;

public class FishingPole implements Item {
    private static HashMap<Integer, String> material;
    private static HashMap<Integer, Integer> energy;
    private static HashMap<Integer, Double> qualityModifier;

    static {
        material = new HashMap<>();
        energy = new HashMap<>();
        qualityModifier = new HashMap<>();

        material.put(0, "Training");
        material.put(1, "Bamboo");
        material.put(2, "Fiberglass");
        material.put(3, "Iridium");

        energy.put(0, 5);
        energy.put(1, 4);
        energy.put(2, 3);
        energy.put(3, 2);

        qualityModifier.put(0, 0.1);
        qualityModifier.put(1, 0.5);
        qualityModifier.put(2, 0.9);
        qualityModifier.put(3, 1.2);
    }

    private int level;

    public FishingPole(int level) {
        this.level = level;
    }

    // ----- getter & setter -----
    public String getName() {
        return "Fishing Pole";
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public String getMaterial() {
        return material.get(level);
    }

    public int getEnergy() {
        return energy.get(level);
    }

    public double getQualityModifier() {
        return qualityModifier.get(level);
    }
    // ---------------------------
}

