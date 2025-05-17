package org.Group34.model.items;

import org.Group34.model.items.crafting.Craft;
import org.Group34.model.items.crafting.PlacingCraft;
import org.Group34.model.items.crafting.ProcessorCraft;
import org.Group34.model.items.foods.CookedFood;

/**
 * Enum of all craftable recipes, with methods to access associated crafting items.
 */
public enum Recipe implements Item {
    // Placing crafts
    SPRINKLER("Sprinkler", 0),
    QUALITY_SPRINKLER("Quality Sprinkler", 0),
    IRIDIUM_SPRINKLER("Iridium Sprinkler", 0),
    SCARECROW("Scarecrow", 0),
    DELUXE_SCARECROW("Deluxe Scarecrow", 0),
    CHERRY_BOMB("Cherry Bomb", 50),
    BOMB("Bomb", 50),
    MEGA_BOMB("Mega Bomb", 50),
    GRASS_STARTER("Grass Starter", 1000),
    MYSTIC_TREE_SEED("Mystic Tree Seed", 100),

    // Processor crafts
    CHARCOAL_KILN("Charcoal Kiln", 0),
    FURNACE("Furnace", 0),
    BEE_HOUSE("Bee House", 0),
    CHEESE_PRESS("Cheese Press", 0),
    KEG("Keg", 0),
    LOOM("Loom", 0),
    MAYONNAISE_MACHINE("Mayonnaise Machine", 0),
    OIL_MAKER("Oil Maker", 0),
    PRESERVES_JAR("Preserves Jar", 0),
    DEHYDRATOR("Dehydrator", 10000),
    FISH_SMOKER("Fish Smoker", 10000),

    // Cooked food recipes
    FRIED_EGG("Fried Egg", 0),
    BAKED_FISH("Baked Fish", 0),
    SALAD("Salad", 0),
    OMELET("Omelet", 0),
    PUMPKIN_PIE("Pumpkin Pie", 0),
    SPAGHETTI("Spaghetti", 0),
    PIZZA("Pizza", 0),
    TORTILLA("Tortilla", 0),
    MAKI_ROLL("Maki Roll", 0),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", 0),
    COOKIE("Cookie", 0),
    HASH_BROWNS("Hash Browns", 0),
    PANCAKES("Pancakes", 0),
    FRUIT_SALAD("Fruit Salad", 0),
    RED_PLATE("Red Plate", 0),
    BREAD("Bread", 0),
    SALMON_DINNER("Salmon Dinner", 0),
    VEGETABLE_MEDLEY("Vegetable Medley", 0),
    FARMERS_LUNCH("Farmer's Lunch", 0),
    SURVIVAL_BURGER("Survival Burger", 0),
    DISH_O_THE_SEA("Dish O' the Sea", 0),
    SEAFOAM_PUDDING("Seafoam Pudding", 0),
    MINERS_TREAT("Miner's Treat", 0);

    private final String name;
    private final int price;

    Recipe(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Craft getCraft() {
        switch (this) {
            // Placing crafts
            case SPRINKLER: return PlacingCraft.SPRINKLER;
            case QUALITY_SPRINKLER: return PlacingCraft.QUALITY_SPRINKLER;
            case IRIDIUM_SPRINKLER: return PlacingCraft.IRIDIUM_SPRINKLER;
            case SCARECROW: return PlacingCraft.SCARECROW;
            case DELUXE_SCARECROW: return PlacingCraft.DELUXE_SCARECROW;
            case CHERRY_BOMB: return PlacingCraft.CHERRY_BOMB;
            case BOMB: return PlacingCraft.BOMB;
            case MEGA_BOMB: return PlacingCraft.MEGA_BOMB;
            case GRASS_STARTER: return PlacingCraft.GRASS_STARTER;
            case MYSTIC_TREE_SEED: return PlacingCraft.MYSTIC_TREE_SEED;

            // Processor crafts
            case CHARCOAL_KILN: return ProcessorCraft.CHARCOAL_KILN;
            case FURNACE: return ProcessorCraft.FURNACE;
            case BEE_HOUSE: return ProcessorCraft.BEE_HOUSE;
            case CHEESE_PRESS: return ProcessorCraft.CHEESE_PRESS;
            case KEG: return ProcessorCraft.KEG;
            case LOOM: return ProcessorCraft.LOOM;
            case MAYONNAISE_MACHINE: return ProcessorCraft.MAYONNAISE_MACHINE;
            case OIL_MAKER: return ProcessorCraft.OIL_MAKER;
            case PRESERVES_JAR: return ProcessorCraft.PRESERVES_JAR;
            case DEHYDRATOR: return ProcessorCraft.DEHYDRATOR;
            case FISH_SMOKER: return ProcessorCraft.FISH_SMOKER;

            // Cooked food recipes
            case FRIED_EGG: return CookedFood.FRIED_EGG;
            case BAKED_FISH: return CookedFood.BAKED_FISH;
            case SALAD: return CookedFood.SALAD;
            case OMELET: return CookedFood.OMELET;
            case PUMPKIN_PIE: return CookedFood.PUMPKIN_PIE;
            case SPAGHETTI: return CookedFood.SPAGHETTI;
            case PIZZA: return CookedFood.PIZZA;
            case TORTILLA: return CookedFood.TORTILLA;
            case MAKI_ROLL: return CookedFood.MAKI_ROLL;
            case TRIPLE_SHOT_ESPRESSO: return CookedFood.TRIPLE_SHOT_ESPRESSO;
            case COOKIE: return CookedFood.COOKIE;
            case HASH_BROWNS: return CookedFood.HASH_BROWNS;
            case PANCAKES: return CookedFood.PANCAKES;
            case FRUIT_SALAD: return CookedFood.FRUIT_SALAD;
            case RED_PLATE: return CookedFood.RED_PLATE;
            case BREAD: return CookedFood.BREAD;
            case SALMON_DINNER: return CookedFood.SALMON_DINNER;
            case VEGETABLE_MEDLEY: return CookedFood.VEGETABLE_MEDLEY;
            case FARMERS_LUNCH: return CookedFood.FARMERS_LUNCH;
            case SURVIVAL_BURGER: return CookedFood.SURVIVAL_BURGER;
            case DISH_O_THE_SEA: return CookedFood.DISH_O_THE_SEA;
            case SEAFOAM_PUDDING: return CookedFood.SEAFOAM_PUDDING;
            case MINERS_TREAT: return CookedFood.MINERS_TREAT;

            default: throw new IllegalArgumentException("No craft associated with this recipe");
        }
    }
}