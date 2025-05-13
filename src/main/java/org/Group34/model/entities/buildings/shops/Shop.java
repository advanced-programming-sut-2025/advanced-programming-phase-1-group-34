package org.Group34.model.entities.buildings.shops;

import org.Group34.model.entities.Entity;
import org.Group34.model.items.Item;

public enum Shop implements Entity {
    BLACKSMITH("Blacksmith", "Clint", 9, 16, null),
    JOJA_MART("JojaMart", "Morris", 9, 23, null),
    PIERRE_GENERAL_STORE("Pierre's General Store", "Pierre", 9, 17, null),
    CARPENTER_SHOP("Carpenter's Shop", "Robin", 9, 20, null),
    FISH_SHOP("Fish Shop", "Willy", 9, 17, null),
    MARNIE_RANCH("Marnie's Ranch", "Marnie", 9, 16, null),
    THE_STARDROP_SALOON("The Stardrop Saloon", "Gus", 12, 0, null);

    private final String name;
    private final String ownerName;
    private final int openingHour;
    private final int closingHour;
    private final Item[] products;

    Shop(String name, String ownerName, int openingHour, int closingHour, Item[] products) {
        this.name = name;
        this.ownerName = ownerName;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.products = products;
    }

    public String getName() {
        return name;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public int getOpeningHour() {
        return openingHour;
    }
    public int getClosingHour() {
        return closingHour;
    }
    public Item[] getProducts() {
        return products;
    }
}
