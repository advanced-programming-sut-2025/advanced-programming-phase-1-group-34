package org.Group34.model.items.tools;

import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.items.Item;

public class MilkPail implements Item {
    private final int ENERGY_COST = 4;

    public String getName() {
        return "Milk Pail";
    }

    public int getEnergy() {
        return ENERGY_COST;
    }

    public String getUsage() {
        return "Used to milk animals.";
    }

    public String getAcquisition() {
        return "Buy from Marnie's Ranch for 1000 gold.";
    }

    public boolean canMilk(AnimalType animalType) {
        return animalType == AnimalType.COW || animalType == AnimalType.GOAT;
    }
}
