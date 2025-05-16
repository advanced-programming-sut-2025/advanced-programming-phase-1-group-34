package org.Group34.model.enums.animals;

import org.Group34.model.items.Item;

public enum BarnType implements Item {
    BARN_BASIC("Basic Barn", 4, "Houses 4 barn-dwelling animals.", 6000, 350, 150, 7, 4),
    BARN_BIG("Big Barn", 8, "Houses 8 barn-dwelling animals. Unlocks goats.", 12000, 450, 200, 7, 4),
    BARN_DELUXE("Deluxe Barn", 12, "Houses 12 barn-dwelling animals. Unlocks sheep and pigs.", 25000, 550, 300, 7, 4),

    COOP_BASIC("Basic Coop", 4, "Houses 4 coop-dwelling animals.", 4000, 300, 100, 6, 3),
    COOP_BIG("Big Coop", 8, "Houses 8 coop-dwelling animals. Unlocks ducks.", 10000, 400, 150, 6, 3),
    COOP_DELUXE("Deluxe Coop", 12, "Houses 12 coop-dwelling animals. Unlocks rabbits.", 20000, 500, 200, 6, 3),;

    private final String name;
    private final int capacity;
    private final String description;
    private final int price;
    private final int woodCost;
    private final int stoneCost;
    private final int sizeX;
    private final int sizeY;

    BarnType(String name, int capacity, String description, int price, int woodCost, int stoneCost, int sizeX, int sizeY) {
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.price = price;
        this.woodCost = woodCost;
        this.stoneCost = stoneCost;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getWoodCost() {
        return woodCost;
    }

    public int getStoneCost() {
        return stoneCost;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
