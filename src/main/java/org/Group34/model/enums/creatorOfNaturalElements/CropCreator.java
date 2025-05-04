package org.Group34.model.enums.creatorOfNaturalElements;

import org.Group34.model.entities.naturalElements.Crop;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.Fruit;

/**
 * Enum for creating crops, now using PlantingSource instead of raw source strings.
 */
public enum CropCreator {
    BLUE_JAZZ("Blue Jazz", PlantingSource.JAZZ_SEEDS, new int[]{1,2,2,2}, 7, true, 0, Fruit.BLUE_JAZZ, new String[]{"Spring"}, false),
    CARROT("Carrot", PlantingSource.CARROT_SEEDS, new int[]{1,1,1}, 3, true, 0, Fruit.CARROT, new String[]{"Spring"}, false),
    CAULIFLOWER("Cauliflower", PlantingSource.CAULIFLOWER_SEEDS, new int[]{1,2,4,4,1}, 12, true, 0, Fruit.CAULIFLOWER, new String[]{"Spring"}, true),
    COFFEE_BEAN("Coffee Bean", PlantingSource.COFFEE_BEAN, new int[]{1,2,2,3,2}, 10, false, 2, Fruit.COFFEE_BEAN, new String[]{"Spring","Summer"}, false),
    GARLIC("Garlic", PlantingSource.GARLIC_SEEDS, new int[]{1,1,1,1}, 4, true, 0, Fruit.GARLIC, new String[]{"Spring"}, false),
    GREEN_BEAN("Green Bean", PlantingSource.BEAN_STARTER, new int[]{1,1,1,3,4}, 10, false, 3, Fruit.GREEN_BEAN, new String[]{"Spring"}, false),
    KALE("Kale", PlantingSource.KALE_SEEDS, new int[]{1,2,2,1}, 6, true, 0, Fruit.KALE, new String[]{"Spring"}, false),
    PARSNIP("Parsnip", PlantingSource.PARSNIP_SEEDS, new int[]{1,1,1,1}, 4, true, 0, Fruit.PARSNIP, new String[]{"Spring"}, false),
    POTATO("Potato", PlantingSource.POTATO_SEEDS, new int[]{1,1,1,2,1}, 6, true, 0, Fruit.POTATO, new String[]{"Spring"}, false),
    RHUBARB("Rhubarb", PlantingSource.RHUBARB_SEEDS, new int[]{2,2,2,3,4}, 13, true, 0, Fruit.RHUBARB, new String[]{"Spring"}, false),
    STRAWBERRY("Strawberry", PlantingSource.STRAWBERRY_SEEDS, new int[]{1,1,2,2,2}, 8, false, 4, Fruit.STRAWBERRY, new String[]{"Spring"}, false),
    TULIP("Tulip", PlantingSource.TULIP_BULB, new int[]{1,1,2,2}, 6, true, 0, Fruit.TULIP, new String[]{"Spring"}, false),
    UNMILLED_RICE("Unmilled Rice", PlantingSource.RICE_SHOOT, new int[]{1,2,2,3}, 8, true, 0, Fruit.UNMILLED_RICE, new String[]{"Spring"}, false),
    BLUEBERRY("Blueberry", PlantingSource.BLUEBERRY_SEEDS, new int[]{1,3,3,4,2}, 13, false, 4, Fruit.BLUEBERRY, new String[]{"Summer"}, false),
    CORN("Corn", PlantingSource.CORN_SEEDS, new int[]{2,3,3,3,3}, 14, false, 4, Fruit.CORN, new String[]{"Summer","Autumn"}, false),
    HOPS("Hops", PlantingSource.HOPS_STARTER, new int[]{1,1,2,3,4}, 11, false, 1, Fruit.HOPS, new String[]{"Summer"}, false),
    HOT_PEPPER("Hot Pepper", PlantingSource.PEPPER_SEEDS, new int[]{1,1,1,1,1}, 5, false, 3, Fruit.HOT_PEPPER, new String[]{"Summer"}, false),
    MELON("Melon", PlantingSource.MELON_SEEDS, new int[]{1,2,3,3,3}, 12, true, 0, Fruit.MELON, new String[]{"Summer"}, true),
    POPPY("Poppy", PlantingSource.POPPY_SEEDS, new int[]{1,2,2,2}, 7, true, 0, Fruit.POPPY, new String[]{"Summer"}, false),
    RADISH("Radish", PlantingSource.RADISH_SEEDS, new int[]{2,1,2,1}, 6, true, 0, Fruit.RADISH, new String[]{"Summer"}, false),
    RED_CABBAGE("Red Cabbage", PlantingSource.RED_CABBAGE_SEEDS, new int[]{2,1,2,2,2}, 9, true, 0, Fruit.RED_CABBAGE, new String[]{"Summer"}, false),
    STARFRUIT("Starfruit", PlantingSource.STARFRUIT_SEEDS, new int[]{2,3,2,3,3}, 13, true, 0, Fruit.STARFRUIT, new String[]{"Summer"}, false),
    SUMMER_SPANGLE("Summer Spangle", PlantingSource.SPANGLE_SEEDS, new int[]{1,2,3,1}, 8, true, 0, Fruit.SUMMER_SPANGLE, new String[]{"Summer"}, false),
    SUMMER_SQUASH("Summer Squash", PlantingSource.SUMMER_SQUASH_SEEDS, new int[]{1,1,1,2,1}, 6, false, 3, Fruit.SUMMER_SQUASH, new String[]{"Summer"}, false),
    SUNFLOWER("Sunflower", PlantingSource.SUNFLOWER_SEEDS, new int[]{1,2,3,2}, 8, true, 0, Fruit.SUNFLOWER, new String[]{"Summer","Autumn"}, false),
    TOMATO("Tomato", PlantingSource.TOMATO_SEEDS, new int[]{2,2,2,2,3}, 11, false, 4, Fruit.TOMATO, new String[]{"Summer"}, false),
    WHEAT("Wheat", PlantingSource.WHEAT_SEEDS, new int[]{1,1,1,1}, 4, true, 0, Fruit.WHEAT, new String[]{"Summer","Autumn"}, false),
    AMARANTH("Amaranth", PlantingSource.AMARANTH_SEEDS, new int[]{1,2,2,2}, 7, true, 0, Fruit.AMARANTH, new String[]{"Autumn"}, false),
    ARTICHOKE("Artichoke", PlantingSource.ARTICHOKE_SEEDS, new int[]{2,2,1,2,1}, 8, true, 0, Fruit.ARTICHOKE, new String[]{"Autumn"}, false),
    BEET("Beet", PlantingSource.BEET_SEEDS, new int[]{1,1,2,2}, 6, true, 0, Fruit.BEET, new String[]{"Autumn"}, false),
    BOK_CHOY("Bok Choy", PlantingSource.BOK_CHOY_SEEDS, new int[]{1,1,1,1}, 4, true, 0, Fruit.BOK_CHOY, new String[]{"Autumn"}, false),
    BROCCOLI("Broccoli", PlantingSource.BROCCOLI_SEEDS, new int[]{2,2,2,2}, 8, false, 4, Fruit.BROCCOLI, new String[]{"Autumn"}, false),
    CRANBERRIES("Cranberries", PlantingSource.CRANBERRY_SEEDS, new int[]{1,2,1,1,2}, 7, false, 5, Fruit.CRANBERRIES, new String[]{"Autumn"}, false),
    EGGPLANT("Eggplant", PlantingSource.EGGPLANT_SEEDS, new int[]{1,1,1,1}, 5, false, 5, Fruit.EGGPLANT, new String[]{"Autumn"}, false),
    FAIRY_ROSE("Fairy Rose", PlantingSource.FAIRY_SEEDS, new int[]{1,4,4,3}, 12, true, 0, Fruit.FAIRY_ROSE, new String[]{"Autumn"}, false),
    GRAPE("Grape", PlantingSource.GRAPE_STARTER, new int[]{1,1,2,3,3}, 10, false, 3, Fruit.GRAPE, new String[]{"Autumn"}, false),
    PUMPKIN("Pumpkin", PlantingSource.PUMPKIN_SEEDS, new int[]{1,2,3,4,3}, 13, true, 0, Fruit.PUMPKIN, new String[]{"Autumn"}, true),
    YAM("Yam", PlantingSource.YAM_SEEDS, new int[]{1,3,3,3}, 10, true, 0, Fruit.YAM, new String[]{"Autumn"}, false),
    SWEET_GEM_BERRY("Sweet Gem Berry", PlantingSource.RARE_SEED, new int[]{2,4,6,6,6}, 24, true, 0, Fruit.SWEET_GEM_BERRY, new String[]{"Autumn"}, false),
    POWDERMELON("Powdermelon", PlantingSource.POWDERMELON_SEEDS, new int[]{1,2,1,2,1}, 7, true, 0, Fruit.POWDERMELON, new String[]{"Winter"}, true),
    ANCIENT_FRUIT("Ancient Fruit", PlantingSource.ANCIENT_SEEDS, new int[]{2,7,7,7,5}, 28, false, 7, Fruit.ANCIENT_FRUIT, new String[]{"Spring","Summer","Autumn"}, false);

    private final String name;
    private final PlantingSource source;
    private final int[] stage;
    private final int totalHarvestTime;
    private final boolean isOneTime;
    private final int regrowthTime;
    private final Fruit fruit;
    private final String[] seasons;
    private final boolean canBecomeGiant;

    CropCreator(String name, PlantingSource source, int[] stage, int totalHarvestTime,
                boolean isOneTime, int regrowthTime, Fruit fruit,
                String[] seasons, boolean canBecomeGiant) {
        this.name = name;
        this.source = source;
        this.stage = stage;
        this.totalHarvestTime = totalHarvestTime;
        this.isOneTime = isOneTime;
        this.regrowthTime = regrowthTime;
        this.fruit = fruit;
        this.seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
    }

    public Crop createInstance() {
        return new Crop(name, source, stage, totalHarvestTime,
                isOneTime, regrowthTime,
                fruit, seasons, canBecomeGiant);
    }
}
