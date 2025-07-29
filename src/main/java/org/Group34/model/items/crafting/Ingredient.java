package org.Group34.model.items.crafting;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.Entity;
import org.Group34.model.items.Item;

public enum Ingredient implements Item, Entity {
    COPPER_BAR("Copper Bar", 0, ""),
    IRON_BAR("Iron Bar", 0, ""),
    GOLD_BAR("Gold Bar", 0, ""),
    IRIDIUM_BAR("Iridium Bar", 0, ""),
    WOOD("Wood", 10, "A sturdy, yet flexible plant material with a wide variety of uses."),
    COAL("Coal", 150, "A combustible rock that is useful for crafting and smelting."),
    FIBER("Fiber", 0, ""),
    IRIDIUM_ORE("Iridium Ore", 0, ""),
    COPPER_ORE("Copper Ore", 75, "A common ore that can be smelted into bars."),
    IRON_ORE("Iron Ore", 150, "A fairly common ore that can be smelted into bars."),
    GOLD_ORE("Gold Ore", 400, "A precious ore that can be smelted into bars."),
    ACORN("Acorn", 0, ""),
    MAPLE_SEED("Maple Seed", 0, ""),
    PINE_CONE("Pine Cone", 0, ""),
    MAHOGANY_SEED("Mahogany Seed", 0, ""),
    STONE("Stone", 20, "A common material with many uses in crafting and building."),
    SHEEP_FABRIC("Sheep Fabric", 0, ""),
    RABBIT_FABRIC("Rabbit Fabric", 0, "");

    private final String name;
    private final int price;
    private final String description;

    Ingredient(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
