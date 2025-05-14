package org.Group34.model.entities.buildings.shops;

import org.Group34.model.items.Fertilizer;
import org.Group34.model.items.Item;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.Recipe;
import org.Group34.model.items.crafting.PlacingCraft;

import java.util.ArrayList;
import java.util.HashMap;

public class PierreGeneralStore extends Shop {
    private static final String name = "Pierre's General Store";
    private static final String ownerName = "Pierre";
    private static final int openingHour = 9;
    private static final int closingHour = 17;
    private static final ArrayList<Item> yearRoundStock = new ArrayList<>();
    private static final ArrayList<Item> springStock = new ArrayList<>();
    private static final ArrayList<Item> summerStock = new ArrayList<>();
    private static final ArrayList<Item> fallStock = new ArrayList<>();
    private static final HashMap<Item, Integer> yearRoundStockLimit = new HashMap<>();
    private static final HashMap<Item, Integer> springStockLimit = new HashMap<>();
    private static final HashMap<Item, Integer> summerStockLimit = new HashMap<>();
    private static final HashMap<Item, Integer> fallStockLimit = new HashMap<>();
    static {
//        yearRoundStock.add(Rice);
//        yearRoundStock.add(Wheat Flour);
//        yearRoundStock.add(Bouquet);
//        yearRoundStock.add(Wedding Ring);
        yearRoundStock.add(Recipe.DEHYDRATOR);
        yearRoundStock.add(Recipe.GRASS_STARTER);
//        yearRoundStock.add(Sugar);
//        yearRoundStock.add(ProcessedFoodType.OIL);
//        yearRoundStock.add(Vinegar);
        yearRoundStock.add(Fertilizer.DELUXE_RETAINING_SOIL);
        yearRoundStock.add(PlacingCraft.GRASS_STARTER);
//        yearRoundStock.add(Speed Gro);
        yearRoundStock.add(PlantingSource.APPLE_SAPLING);
        yearRoundStock.add(PlantingSource.APRICOT_SAPLING);
        yearRoundStock.add(PlantingSource.CHERRY_SAPLING);
        yearRoundStock.add(PlantingSource.ORANGE_SAPLING);
        yearRoundStock.add(PlantingSource.PEACH_SAPLING);
        yearRoundStock.add(PlantingSource.POWDERMELON_SEEDS);
        yearRoundStock.add(Fertilizer.BASIC_RETAINING_SOIL);
        yearRoundStock.add(Fertilizer.QUALITY_RETAINING_SOIL);
//        yearRoundStock.add(large Pack);
//        yearRoundStock.add(Delux Pack);

        yearRoundStockLimit.put(yearRoundStock.get(0), -11);
        yearRoundStockLimit.put(yearRoundStock.get(1), -11);
        yearRoundStockLimit.put(yearRoundStock.get(2), 2);
        yearRoundStockLimit.put(yearRoundStock.get(3), 2);
        yearRoundStockLimit.put(yearRoundStock.get(4), 1);
        yearRoundStockLimit.put(yearRoundStock.get(5), 1);
        yearRoundStockLimit.put(yearRoundStock.get(6), -11);
        yearRoundStockLimit.put(yearRoundStock.get(7), -11);
        yearRoundStockLimit.put(yearRoundStock.get(8), -11);
        yearRoundStockLimit.put(yearRoundStock.get(9), -11);
        yearRoundStockLimit.put(yearRoundStock.get(10), -11);
        yearRoundStockLimit.put(yearRoundStock.get(11), -11);
        yearRoundStockLimit.put(yearRoundStock.get(12), -11);
        yearRoundStockLimit.put(yearRoundStock.get(13), -11);
        yearRoundStockLimit.put(yearRoundStock.get(14), -11);
        yearRoundStockLimit.put(yearRoundStock.get(15), -11);
        yearRoundStockLimit.put(yearRoundStock.get(16), -11);
        yearRoundStockLimit.put(yearRoundStock.get(17), -11);
        yearRoundStockLimit.put(yearRoundStock.get(18), -11);
        yearRoundStockLimit.put(yearRoundStock.get(19), -11);
        yearRoundStockLimit.put(yearRoundStock.get(20), 1);
        yearRoundStockLimit.put(yearRoundStock.get(21), 1);


        springStock.add(PlantingSource.PARSNIP_SEEDS);
        springStock.add(PlantingSource.BEAN_STARTER);
        springStock.add(PlantingSource.CAULIFLOWER_SEEDS);
        springStock.add(PlantingSource.POTATO_SEEDS);
        springStock.add(PlantingSource.TULIP_BULB);
        springStock.add(PlantingSource.KALE_SEEDS);
        springStock.add(PlantingSource.JAZZ_SEEDS);
        springStock.add(PlantingSource.GARLIC_SEEDS);
        springStock.add(PlantingSource.RICE_SHOOT);

        springStockLimit.put(springStock.get(0), 5);
        springStockLimit.put(springStock.get(1), 5);
        springStockLimit.put(springStock.get(2), 5);
        springStockLimit.put(springStock.get(3), 5);
        springStockLimit.put(springStock.get(4), 5);
        springStockLimit.put(springStock.get(5), 5);
        springStockLimit.put(springStock.get(6), 5);
        springStockLimit.put(springStock.get(7), 5);
        springStockLimit.put(springStock.get(8), 5);


        summerStock.add(PlantingSource.MELON_SEEDS);
        summerStock.add(PlantingSource.TOMATO_SEEDS);
        summerStock.add(PlantingSource.BLUEBERRY_SEEDS);
        summerStock.add(PlantingSource.PEPPER_SEEDS);
        summerStock.add(PlantingSource.WHEAT_SEEDS);
        summerStock.add(PlantingSource.RADISH_SEEDS);
        summerStock.add(PlantingSource.POPPY_SEEDS);
        summerStock.add(PlantingSource.SPANGLE_SEEDS);
        summerStock.add(PlantingSource.HOPS_STARTER);
        summerStock.add(PlantingSource.CORN_SEEDS);
        summerStock.add(PlantingSource.SUNFLOWER_SEEDS);
        summerStock.add(PlantingSource.RED_CABBAGE_SEEDS);

        summerStockLimit.put(summerStock.get(0), 5);
        summerStockLimit.put(summerStock.get(1), 5);
        summerStockLimit.put(summerStock.get(2), 5);
        summerStockLimit.put(summerStock.get(3), 5);
        summerStockLimit.put(summerStock.get(4), 5);
        summerStockLimit.put(summerStock.get(5), 5);
        summerStockLimit.put(summerStock.get(6), 5);
        summerStockLimit.put(summerStock.get(7), 5);
        summerStockLimit.put(summerStock.get(8), 5);
        summerStockLimit.put(summerStock.get(9), 5);
        summerStockLimit.put(summerStock.get(10), 5);
        summerStockLimit.put(summerStock.get(11), 5);


        fallStock.add(PlantingSource.EGGPLANT_SEEDS);
        fallStock.add(PlantingSource.CORN_SEEDS);
        fallStock.add(PlantingSource.PUMPKIN_SEEDS);
        fallStock.add(PlantingSource.BOK_CHOY_SEEDS);
        fallStock.add(PlantingSource.YAM_SEEDS);
        fallStock.add(PlantingSource.CRANBERRY_SEEDS);
        fallStock.add(PlantingSource.SUNFLOWER_SEEDS);
        fallStock.add(PlantingSource.FAIRY_SEEDS);
        fallStock.add(PlantingSource.AMARANTH_SEEDS);
        fallStock.add(PlantingSource.GRAPE_STARTER);
        fallStock.add(PlantingSource.WHEAT_SEEDS);
        fallStock.add(PlantingSource.ARTICHOKE_SEEDS);

        fallStockLimit.put(fallStock.get(0), 5);
        fallStockLimit.put(fallStock.get(1), 5);
        fallStockLimit.put(fallStock.get(2), 5);
        fallStockLimit.put(fallStock.get(3), 5);
        fallStockLimit.put(fallStock.get(4), 5);
        fallStockLimit.put(fallStock.get(5), 5);
        fallStockLimit.put(fallStock.get(6), 5);
        fallStockLimit.put(fallStock.get(7), 5);
        fallStockLimit.put(fallStock.get(8), 5);
        fallStockLimit.put(fallStock.get(9), 5);
        fallStockLimit.put(fallStock.get(10), 5);
        fallStockLimit.put(fallStock.get(11), 5);
    }

    // ----- getters & setters -----
    public String getName() {
        return name;
    }

    public static String getOwnerName() {
        return ownerName;
    }

    public static int getOpeningHour() {
        return openingHour;
    }

    public static int getClosingHour() {
        return closingHour;
    }

    public static ArrayList<Item> getYearRoundStock() {
        return yearRoundStock;
    }

    public static ArrayList<Item> getSpringStock() {
        return springStock;
    }

    public static ArrayList<Item> getSummerStock() {
        return summerStock;
    }

    public static ArrayList<Item> getFallStock() {
        return fallStock;
    }

    public static HashMap<Item, Integer> getYearRoundStockLimit() {
        return yearRoundStockLimit;
    }

    public static HashMap<Item, Integer> getSpringStockLimit() {
        return springStockLimit;
    }

    public static HashMap<Item, Integer> getSummerStockLimit() {
        return summerStockLimit;
    }

    public static HashMap<Item, Integer> getFallStockLimit() {
        return fallStockLimit;
    }
    // -----------------------------


    public int getYearRoundStockLimit(Item stock) {
        return yearRoundStockLimit.get(stock);
    }

    public int getSpringStockLimit(Item stock) {
        return springStockLimit.get(stock);
    }

    public int getSummerStockLimit(Item stock) {
        return summerStockLimit.get(stock);
    }

    public int getFallStockLimit(Item stock) {
        return fallStockLimit.get(stock);
    }
}
