package org.Group34.model.enums.animals;

public enum AnimalType {
    CHICKEN(800, "Coop", 4),
    DUCK(1200, "Big Coop", 8),
    RABBIT(8000, "Deluxe Coop", 12),
    DINOSAUR(14000, "Big Coop", 8),
    COW(1500, "Barn", 4),
    GOAT(4000, "Big Barn", 8),
    SHEEP(8000, "Deluxe Barn", 12),
    PIG(16000, "Deluxe Barn", 12);

    public final int price;
    public final String requiredBuilding;
    public final int buildingCapacity;

    AnimalType(int price, String building, int capacity) {
        this.price = price;
        this.requiredBuilding = building;
        this.buildingCapacity = capacity;
    }
}