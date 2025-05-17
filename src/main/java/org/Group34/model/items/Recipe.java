package org.Group34.model.items;

import org.Group34.model.items.crafting.Craft;
import org.Group34.model.items.crafting.PlacingCraft;
import org.Group34.model.items.crafting.ProcessorCraft;
import org.Group34.model.items.foods.CookedFood;
// import org.Group34.model.items.foods.CookedFood;

/**
 * Enum of all craftable recipes, linking each recipe to its placeable or processable item.
 */
public enum Recipe implements Item {
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
    BEE_HOUSE("Bee House", 0, ProcessorCraft.BEE_HOUSE),
    CHEESE_PRESS("Cheese Press", 0, ProcessorCraft.CHEESE_PRESS),
    KEG("Keg", 0, ProcessorCraft.KEG),
    LOOM("Loom", 0, ProcessorCraft.LOOM),
    MAYONNAISE_MACHINE("Mayonnaise Machine", 0, ProcessorCraft.MAYONNAISE_MACHINE),
    OIL_MAKER("Oil Maker", 0, ProcessorCraft.OIL_MAKER),
    PRESERVES_JAR("Preserves Jar", 0, ProcessorCraft.PRESERVES_JAR),
    DEHYDRATOR("Dehydrator", 10000, ProcessorCraft.DEHYDRATOR),
    FISH_SMOKER("Fish Smoker", 10000, ProcessorCraft.FISH_SMOKER),

    // Cooked food recipes
    FRIED_EGG("Fried Egg", 0, CookedFood.FRIED_EGG),
    BAKED_FISH("Baked Fish", 0, CookedFood.BAKED_FISH),
    SALAD("Salad", 0, CookedFood.SALAD),
    OMELET("Omelet", 0, CookedFood.OMELET),
    PUMPKIN_PIE("Pumpkin Pie", 0, CookedFood.PUMPKIN_PIE),
    SPAGHETTI("Spaghetti", 0, CookedFood.SPAGHETTI),
    PIZZA("Pizza", 0, CookedFood.PIZZA),
    TORTILLA("Tortilla", 0, CookedFood.TORTILLA),
    MAKI_ROLL("Maki Roll", 0, CookedFood.MAKI_ROLL),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", 0, CookedFood.TRIPLE_SHOT_ESPRESSO),
    COOKIE("Cookie", 0, CookedFood.COOKIE),
    HASH_BROWNS("Hash Browns", 0, CookedFood.HASH_BROWNS),
    PANCAKES("Pancakes", 0, CookedFood.PANCAKES),
    FRUIT_SALAD("Fruit Salad", 0, CookedFood.FRUIT_SALAD),
    RED_PLATE("Red Plate", 0, CookedFood.RED_PLATE),
    BREAD("Bread", 0, CookedFood.BREAD),
    SALMON_DINNER("Salmon Dinner", 0, CookedFood.SALMON_DINNER),
    VEGETABLE_MEDLEY("Vegetable Medley", 0, CookedFood.VEGETABLE_MEDLEY),
    FARMERS_LUNCH("Farmer's Lunch", 0, CookedFood.FARMERS_LUNCH),
    SURVIVAL_BURGER("Survival Burger", 0, CookedFood.SURVIVAL_BURGER),
    DISH_O_THE_SEA("Dish O' the Sea", 0, CookedFood.DISH_O_THE_SEA),
    SEAFOAM_PUDDING("Seafoam Pudding", 0,CookedFood.SEAFOAM_PUDDING),
    MINERS_TREAT("Miner's Treat", 0, CookedFood.MINERS_TREAT);

    private final String name;
    private final int price;
    private final Craft product;

    Recipe(String name, int price, Craft product) {
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
}
