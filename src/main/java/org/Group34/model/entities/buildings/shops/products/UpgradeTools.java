package org.Group34.model.entities.buildings.shops.products;

import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;

public enum UpgradeTools implements Item {
    COPPER_TOOL("Copper Tool", Ingredient.COPPER_BAR, 2000),
    STEEL_TOOL("Steel Tool", Ingredient.IRON_BAR, 5000),
    GOLD_TOOL("Gold Tool", Ingredient.GOLD_BAR, 10000),
    IRIDIUM_TOOL("Iridium Tool", Ingredient.IRIDIUM_BAR, 25000),

    COPPER_TRASH_CAN("Copper Trash Can", Ingredient.COPPER_BAR, 1000),
    STEEL_TRASH_CAN("Steel Trash Can", Ingredient.IRON_BAR, 2500),
    GOLD_TRASH_CAN("Gold Trash Can", Ingredient.GOLD_BAR, 5000),
    IRIDIUM_TRASH_CAN("Iridium Trash Can", Ingredient.IRIDIUM_BAR, 12500),

    BIG_BACKPACK("Big Backpack", null, 2000),
    DELUXE_BACKPACK("Deluxe Backpack", null, 10000),

    TRAINING_FISHING_POLE("Training Fishing Pole", null, 25),
    BAMBOO_FISHING_POLE("Bamboo Fishing Pole", null, 500),
    FIBERGLASS_FISHING_POLE("Fiberglass Fishing Pole", null, 1800),
    IRIDIUM_FISHING_POLE("Iridium Fishing Pole", null, 7500);



    private final String name;
    private final Ingredient ingredient;
    private final int price;

    UpgradeTools(String name, Ingredient ingredient, int price) {
        this.name = name;
        this.ingredient = ingredient;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getPrice() {
        return price;
    }
}
