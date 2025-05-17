package org.Group34.model.items.tools;

import org.Group34.model.enums.animals.AnimalType;

public class MilkPail extends Tool {
    private final int ENERGY_COST = 4;
    private final int price = 1000;
    private final String description = "Gather milk from your animals.";

    public String getName() {
        return "Milk Pail";
    }

    public int getEnergy() {
        return ENERGY_COST;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
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
