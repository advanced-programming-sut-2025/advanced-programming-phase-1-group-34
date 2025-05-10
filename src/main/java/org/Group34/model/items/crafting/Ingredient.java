package org.Group34.model.items.crafting;

import org.Group34.model.items.Item;

public enum Ingredient implements Item {
    COPPER_BAR("Copper Bar", 0),
    IRON_BAR("Iron Bar", 0),
    GOLD_BAR("Gold Bar", 0),
    IRIDIUM_BAR("Iridium Bar", 0),
    WOOD("Wood", 0),
    COAL("Coal", 0),
    FIBER("Fiber", 0),
    IRIDIUM_ORE("Iridium Ore", 0),
    COPPER_ORE("Copper Ore", 0),
    IRON_ORE("Iron Ore", 0),
    GOLD_ORE("Gold Ore", 0),
    ACORN("Acorn", 0),
    MAPLE_SEED("Maple Seed", 0),
    PINE_CONE("Pine Cone", 0),
    MAHOGANY_SEED("Mahogany Seed", 0),
    STONE("Stone", 0),
    SHEEP_FABRIC("Sheep Fabric", 0),
    RABBIT_FABRIC("Rabbit Fabric", 0);

    private final String name;
    private final int price;

    Ingredient(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
