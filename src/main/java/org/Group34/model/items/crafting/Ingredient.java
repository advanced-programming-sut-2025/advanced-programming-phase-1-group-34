package org.Group34.model.items.crafting;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.Entity;
import org.Group34.model.gameAssetManagers.IngredientAssetManager;
import org.Group34.model.items.Item;

public enum Ingredient implements Item, Entity {
    COPPER_BAR("Copper Bar", 0, "", IngredientAssetManager.copperBar),
    IRON_BAR("Iron Bar", 0, "", IngredientAssetManager.ironBar),
    GOLD_BAR("Gold Bar", 0, "", IngredientAssetManager.goldBar),
    IRIDIUM_BAR("Iridium Bar", 0, "", IngredientAssetManager.iridiumBar),
    WOOD("Wood", 10, "A sturdy, yet flexible plant material with a wide variety of uses.", IngredientAssetManager.wood),
    COAL("Coal", 150, "A combustible rock that is useful for crafting and smelting.", IngredientAssetManager.coal),
    FIBER("Fiber", 0, "", IngredientAssetManager.fiber),
    IRIDIUM_ORE("Iridium Ore", 0, "", IngredientAssetManager.iridiumOre),
    COPPER_ORE("Copper Ore", 75, "A common ore that can be smelted into bars.", IngredientAssetManager.copperOre),
    IRON_ORE("Iron Ore", 150, "A fairly common ore that can be smelted into bars.", IngredientAssetManager.ironOre),
    GOLD_ORE("Gold Ore", 400, "A precious ore that can be smelted into bars.", IngredientAssetManager.goldOre),
    ACORN("Acorn", 0, "", IngredientAssetManager.acorn),
    MAPLE_SEED("Maple Seed", 0, "", IngredientAssetManager.mapleSeed),
    PINE_CONE("Pine Cone", 0, "", IngredientAssetManager.pineCone),
    MAHOGANY_SEED("Mahogany Seed", 0, "", IngredientAssetManager.mahoganySeed),
    STONE("Stone", 20, "A common material with many uses in crafting and building.", IngredientAssetManager.stone),
    SHEEP_FABRIC("Sheep Fabric", 0, "", null),
    RABBIT_FABRIC("Rabbit Fabric", 0, "", null);

    private final String name;
    private final int price;
    private final String description;
    private final Texture texture;

    Ingredient(String name, int price, String description, Texture texture) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.texture = texture;
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
        return texture;
    }
}
