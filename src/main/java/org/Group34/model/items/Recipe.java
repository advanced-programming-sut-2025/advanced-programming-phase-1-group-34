package org.Group34.model.items;

import org.Group34.model.items.crafting.PlacingCraft;
import org.Group34.model.items.crafting.ProcessorCraft;

/**
 * Enum of all craftable recipes, linking each recipe to its placeable or processable item.
 */
public enum Recipe {
    // Placing crafts
    SPRINKLER("Sprinkler", 0, PlacingCraft.SPRINKLER),
    QUALITY_SPRINKLER("Quality Sprinkler", 0, PlacingCraft.QUALITY_SPRINKLER),
    IRIDIUM_SPRINKLER("Iridium Sprinkler", 0, PlacingCraft.IRIDIUM_SPRINKLER),
    SCARECROW("Scarecrow", 0, PlacingCraft.SCARECROW),
    DELUXE_SCARECROW("Deluxe Scarecrow", 0, PlacingCraft.DELUXE_SCARECROW),
    CHERRY_BOMB("Cherry Bomb", 50, PlacingCraft.CHERRY_BOMB),
    BOMB("Bomb", 50, PlacingCraft.BOMB),
    MEGA_BOMB("Mega Bomb", 50, PlacingCraft.MEGA_BOMB),
    GRASS_STARTER("Grass Starter", 1000, PlacingCraft.GRASS_STARTER),
    MYSTIC_TREE_SEED("Mystic Tree Seed", 100, PlacingCraft.MYSTIC_TREE_SEED),

    // Processor crafts
    CHARCOAL_KILN("Charcoal Kiln", 0, ProcessorCraft.CHARCOAL_KILN),
    FURNACE("Furnace", 0, ProcessorCraft.FURNACE),
    BEE_HOUSE("Bee House", 0, PlacingCraft.BEE_HOUSE),
    CHEESE_PRESS("Cheese Press", 0, ProcessorCraft.CHEESE_PRESS),
    KEG("Keg", 0, ProcessorCraft.KEG),
    LOOM("Loom", 0, ProcessorCraft.LOOM),
    MAYONNAISE_MACHINE("Mayonnaise Machine", 0, ProcessorCraft.MAYONNAISE_MACHINE),
    OIL_MAKER("Oil Maker", 0, ProcessorCraft.OIL_MAKER),
    PRESERVES_JAR("Preserves Jar", 0, ProcessorCraft.PRESERVES_JAR),
    DEHYDRATOR("Dehydrator", 10000, ProcessorCraft.DEHYDRATOR),
    FISH_SMOKER("Fish Smoker", 10000, ProcessorCraft.FISH_SMOKER);

    private final String name;
    private final int price;
    private final Item product;

    Recipe(String name, int price, Item product) {
        this.name = name;
        this.price = price;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Item getProduct() {
        return product;
    }
}
