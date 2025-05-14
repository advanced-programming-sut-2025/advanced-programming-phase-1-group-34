package org.Group34.model.entities.buildings.shops;

import org.Group34.model.items.Item;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.crafting.PlacingCraft;

import java.util.ArrayList;
import java.util.HashMap;

public class JojaMart extends Shop {
    private static final String name = "JojaMart";
    private static final String ownerName = "Morris";
    private static final int openingHour = 9;
    private static final int closingHour = 23;
    private static final ArrayList<Item> permanentStock = new ArrayList<>();
    private static final ArrayList<Item> springStock = new ArrayList<>();
    private static final ArrayList<Item> summerStock = new ArrayList<>();
    private static final ArrayList<Item> fallStock = new ArrayList<>();
    private static final ArrayList<Item> winterStock = new ArrayList<>();
    private static final HashMap<Item, Integer> permanentStockLimit = new HashMap<>();
    private static final HashMap<Item, Integer> springStockLimit = new HashMap<>();
    private static final HashMap<Item, Integer> summerStockLimit = new HashMap<>();
    private static final HashMap<Item, Integer> fallStockLimit = new HashMap<>();
    private static final HashMap<Item, Integer> winterStockLimit = new HashMap<>();
    static {
//        permanentStock.add(Joja Cola);
        permanentStock.add(PlantingSource.ANCIENT_SEEDS);
        permanentStock.add(PlacingCraft.GRASS_STARTER);
//        permanentStock.add(Sugar);
//        permanentStock.add(Wheat Flour);
//        permanentStock.add(Rice);

        permanentStockLimit.put(permanentStock.get(0), -11);
        permanentStockLimit.put(permanentStock.get(1), 1);
        permanentStockLimit.put(permanentStock.get(2), -11);
        permanentStockLimit.put(permanentStock.get(3), -11);
        permanentStockLimit.put(permanentStock.get(4), -11);
        permanentStockLimit.put(permanentStock.get(5), -11);


        springStock.add(PlantingSource.PARSNIP_SEEDS);
        springStock.add(PlantingSource.BEAN_STARTER);
        springStock.add(PlantingSource.CAULIFLOWER_SEEDS);
        springStock.add(PlantingSource.POTATO_SEEDS);
        springStock.add(PlantingSource.STRAWBERRY_SEEDS);
        springStock.add(PlantingSource.TULIP_BULB);
        springStock.add(PlantingSource.KALE_SEEDS);
        springStock.add(PlantingSource.COFFEE_BEAN);
        springStock.add(PlantingSource.CARROT_SEEDS);
        springStock.add(PlantingSource.RHUBARB_SEEDS);
        springStock.add(PlantingSource.JAZZ_SEEDS);

        springStockLimit.put(springStock.get(0), 5);
        springStockLimit.put(springStock.get(1), 5);
        springStockLimit.put(springStock.get(2), 5);
        springStockLimit.put(springStock.get(3), 5);
        springStockLimit.put(springStock.get(4), 5);
        springStockLimit.put(springStock.get(5), 5);
        springStockLimit.put(springStock.get(6), 5);
        springStockLimit.put(springStock.get(7), 1);
        springStockLimit.put(springStock.get(8), 10);
        springStockLimit.put(springStock.get(9), 5);
        springStockLimit.put(springStock.get(10), 5);


        summerStock.add(PlantingSource.TOMATO_SEEDS);
        summerStock.add(PlantingSource.PEPPER_SEEDS);
        summerStock.add(PlantingSource.WHEAT_SEEDS);
        summerStock.add(PlantingSource.SUMMER_SQUASH_SEEDS);
        summerStock.add(PlantingSource.RADISH_SEEDS);
        summerStock.add(PlantingSource.MELON_SEEDS);
        summerStock.add(PlantingSource.HOPS_STARTER);
        summerStock.add(PlantingSource.POPPY_SEEDS);
        summerStock.add(PlantingSource.SPANGLE_SEEDS);
        summerStock.add(PlantingSource.STARFRUIT_SEEDS);
        summerStock.add(PlantingSource.COFFEE_BEAN);
        summerStock.add(PlantingSource.SUNFLOWER_SEEDS);

        summerStockLimit.put(summerStock.get(0), 5);
        summerStockLimit.put(summerStock.get(1), 5);
        summerStockLimit.put(summerStock.get(2), 10);
        summerStockLimit.put(summerStock.get(3), 10);
        summerStockLimit.put(summerStock.get(4), 5);
        summerStockLimit.put(summerStock.get(5), 5);
        summerStockLimit.put(summerStock.get(6), 5);
        summerStockLimit.put(summerStock.get(7), 5);
        summerStockLimit.put(summerStock.get(8), 5);
        summerStockLimit.put(summerStock.get(9), 5);
        summerStockLimit.put(summerStock.get(10), 1);
        summerStockLimit.put(summerStock.get(11), 5);


        fallStock.add(PlantingSource.CORN_SEEDS);
        fallStock.add(PlantingSource.EGGPLANT_SEEDS);
        fallStock.add(PlantingSource.PUMPKIN_SEEDS);
        fallStock.add(PlantingSource.BROCCOLI_SEEDS);
        fallStock.add(PlantingSource.AMARANTH_SEEDS);
        fallStock.add(PlantingSource.GRAPE_STARTER);
        fallStock.add(PlantingSource.BEET_SEEDS);
        fallStock.add(PlantingSource.YAM_SEEDS);
        fallStock.add(PlantingSource.BOK_CHOY_SEEDS);
        fallStock.add(PlantingSource.CRANBERRY_SEEDS);
        fallStock.add(PlantingSource.SUNFLOWER_SEEDS);
        fallStock.add(PlantingSource.FAIRY_SEEDS);
        fallStock.add(PlantingSource.RARE_SEED);
        fallStock.add(PlantingSource.WHEAT_SEEDS);

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
        fallStockLimit.put(fallStock.get(12), 1);
        fallStockLimit.put(fallStock.get(13), 5);


        winterStock.add(PlantingSource.POWDERMELON_SEEDS);

        winterStockLimit.put(winterStock.get(0), 10);
    }

    // ----- getters & setters -----
    public static String getName() {
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

    public static ArrayList<Item> getPermanentStock() {
        return permanentStock;
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

    public static ArrayList<Item> getWinterStock() {
        return winterStock;
    }

    public static HashMap<Item, Integer> getPermanentStockLimit() {
        return permanentStockLimit;
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

    public static HashMap<Item, Integer> getWinterStockLimit() {
        return winterStockLimit;
    }
    // -----------------------------

    public static int getPermanentStockLimit(Item stock) {
        return permanentStockLimit.get(stock);
    }

    public static int getSpringStockLimit(Item stock) {
        return springStockLimit.get(stock);
    }

    public static int getSummerStockLimit(Item stock) {
        return summerStockLimit.get(stock);
    }

    public static int getFallStockLimit(Item stock) {
        return fallStockLimit.get(stock);
    }

    public static int getWinterStockLimit(Item stock) {
        return winterStockLimit.get(stock);
    }
}
