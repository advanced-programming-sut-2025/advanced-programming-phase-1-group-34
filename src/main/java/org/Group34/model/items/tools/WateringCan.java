package org.Group34.model.items.tools;

import java.util.HashMap;

public class WateringCan {
    private static HashMap<Integer, String> material;
    private static HashMap<Integer, Integer> capacity;
    private static HashMap<Integer, Integer> energy;
    static {
        material.put(0, "Plastic");
        material.put(1, "Copper");
        material.put(2, "Iron");
        material.put(3, "Gold");
        material.put(4, "Iridium");

        capacity.put(0, 40);
        capacity.put(1, 55);
        capacity.put(2, 70);
        capacity.put(3, 85);
        capacity.put(4, 100);

        energy.put(0, 5);
        energy.put(1, 4);
        energy.put(2, 3);
        energy.put(3, 2);
        energy.put(4, 1);
    }


    private int level;
    private int amountOfWater = 0;

    public WateringCan(int level) {
        this.level = level;
    }

    // ----- getter & setter -----
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public String getMaterial() {
        return material.get(level);
    }

    public int getCapacity() {
        return capacity.get(level);
    }

    public int getEnergy() {
        return energy.get(level);
    }

    public int getAmountOfWater() {
        return amountOfWater;
    }
    public void setAmountOfWater(int amount) {
        this.amountOfWater = amount;
    }
    // ---------------------------

}
