package org.Group34.model.items.tools;

import org.Group34.model.items.Item;

import java.util.HashMap;

public class Hoe implements Item {
    private static HashMap<Integer, String> material;
    private static HashMap<Integer, Integer> energy;
    static {
        material.put(0, "Plastic");
        material.put(1, "Copper");
        material.put(2, "Iron");
        material.put(3, "Gold");
        material.put(4, "Iridium");

        energy.put(0, 5);
        energy.put(1, 4);
        energy.put(2, 3);
        energy.put(3, 2);
        energy.put(4, 1);
    }


    private int level;

    public Hoe(int level) {
        this.level = level;
    }

    // ----- getter & setter -----
    public String getName() {
        return "Hoe";
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
    // ---------------------------

}
