package org.Group34.model.items.crafting;

public enum Recipe {
    SPRINKLER("Sprinkler", "Waters 4 adjacent tiles.", 0),
    QUALITY_SPRINKLER("Quality Sprinkler", "Waters 8 adjacent tiles.", 0),
    IRIDIUM_SPRINKLER("Iridium Sprinkler", "Waters 24 adjacent tiles.", 0),
    SCARECROW("Scarecrow", "Protects an 8-tile radius from crows.", 0),
    DELUXE_SCARECROW("Deluxe Scarecrow", "Protects a 12-tile radius from crows.", 0),
    CHERRY_BOMB("Cherry Bomb", "Destroys everything in a 3-tile radius.", 0),
    BOMB("Bomb", "Destroys everything in a 5-tile radius.", 0),
    MEGA_BOMB("Mega Bomb", "Destroys everything in a 7-tile radius.", 0),
    GRASS_STARTER("Grass Starter", "Grows grass on the tile where it's placed.", 0),
    MYSTIC_TREE_SEED("Mystic Tree Seed", "Can be planted to grow a Mystic Tree.", 0),
    //ProcessorCraft
    CHARCOAL_KILN("Charcoal Kiln", "Turns 10 wood into 1 coal.", 0),
    FURNACE("Furnace", "Smelts ores and coal into bars.", 0),
    BEE_HOUSE("Bee House", "Produces honey when placed outside.", 0),
    CHEESE_PRESS("Cheese Press", "Turns milk into cheese.", 0),
    KEG("Keg", "Turns vegetables and fruit into beverages.", 0),
    LOOM("Loom", "Turns wool into cloth.", 0),
    MAYONNAISE_MACHINE("Mayonnaise Machine", "Turns eggs into mayonnaise.", 0),
    OIL_MAKER("Oil Maker", "Turns truffle into oil.", 0),
    PRESERVES_JAR("Preserves Jar", "Turns vegetables into pickles and fruit into jam.", 0),
    DEHYDRATOR("Dehydrator", "Dries fruit or mushrooms.", 0),
    FISH_SMOKER("Fish Smoker", "Turns any fish into smoked fish with preserved quality.", 0),;

    private final String name;
    private final String description;
    private final int price;

    Recipe(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
