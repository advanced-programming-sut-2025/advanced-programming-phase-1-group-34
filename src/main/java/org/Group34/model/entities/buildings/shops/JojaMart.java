package org.Group34.model.entities.buildings.shops;

import org.Group34.model.entities.buildings.shops.products.UpgradeTools;
import org.Group34.model.enums.Season;
import org.Group34.model.items.Item;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.crafting.Ingredient;
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
    private HashMap<Item, Integer> permanentStockLimit = new HashMap<>();
    private HashMap<Item, Integer> springStockLimit = new HashMap<>();
    private HashMap<Item, Integer> summerStockLimit = new HashMap<>();
    private HashMap<Item, Integer> fallStockLimit = new HashMap<>();
    private HashMap<Item, Integer> winterStockLimit = new HashMap<>();
    static {
        permanentStock.add(PlantingSource.ANCIENT_SEEDS);
        permanentStock.add(PlacingCraft.GRASS_STARTER);

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

        winterStock.add(PlantingSource.POWDERMELON_SEEDS);
    }
    {
        permanentStockLimit.put(permanentStock.get(1), 1);
        permanentStockLimit.put(permanentStock.get(2), -11);

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

    public HashMap<Item, Integer> getPermanentStockLimit() {
        return permanentStockLimit;
    }

    public HashMap<Item, Integer> getSpringStockLimit() {
        return springStockLimit;
    }

    public HashMap<Item, Integer> getSummerStockLimit() {
        return summerStockLimit;
    }

    public HashMap<Item, Integer> getFallStockLimit() {
        return fallStockLimit;
    }

    public HashMap<Item, Integer> getWinterStockLimit() {
        return winterStockLimit;
    }
    // -----------------------------

    public int getPermanentStockLimit(Item stock) {
        return permanentStockLimit.get(stock);
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

    public int getWinterStockLimit(Item stock) {
        return winterStockLimit.get(stock);
    }

    public String showAllProducts() {
        StringBuilder result = new StringBuilder();

        result.append("----- Blacksmith -----\n");
        result.append("\n* Permanent Stock:\n");
        for (Item item : permanentStock) {
            if (item instanceof PlantingSource stock) {
                result
                        .append("Name: " + stock.getName() + "\n")
                        .append("Price: " + stock.getPrice() + "\n")
                        .append("Description: " + stock.getDescription() + "\n")
                        .append("Daily Limit: ");
                if (getPermanentStockLimit(stock) >= 0) {
                    result.append(getPermanentStockLimit(stock) + "\n");
                } else {
                    result.append("unlimited\n");
                }
                result.append("----------------------\n");
            }
            else if (item instanceof PlacingCraft stock) {
                result
                        .append("Name: " + stock.getName() + "\n")
                        .append("Price: " + stock.getPrice() + "\n")
                        .append("Description: " + stock.getDescription() + "\n")
                        .append("Daily Limit: ");
                if (getPermanentStockLimit(stock) >= 0) {
                    result.append(getPermanentStockLimit(stock) + "\n");
                } else {
                    result.append("unlimited\n");
                }
                result.append("----------------------\n");
            }
        }

        result.append("\n* Spring Stock:\n");
        for (Item item : springStock) {
            PlantingSource stock = (PlantingSource) item;
            result
                    .append("Name: " + stock.getName() + "\n")
                    .append("Price: " + stock.getPrice() + "\n")
                    .append("Description: " + stock.getDescription() + "\n")
                    .append("Daily Limit: ");
            if (getSpringStockLimit(stock) >= 0) {
                result.append(getSpringStockLimit(stock) + "\n");
            } else {
                result.append("unlimited\n");
            }
            result.append("----------------------\n");
        }

        result.append("\n* Summer Stock:\n");
        for (Item item : summerStock) {
            PlantingSource stock = (PlantingSource) item;
            result
                    .append("Name: " + stock.getName() + "\n")
                    .append("Price: " + stock.getPrice() + "\n")
                    .append("Description: " + stock.getDescription() + "\n")
                    .append("Daily Limit: ");
            if (getSummerStockLimit(stock) >= 0) {
                result.append(getSummerStockLimit(stock) + "\n");
            } else {
                result.append("unlimited\n");
            }
            result.append("----------------------\n");
        }

        result.append("\n* Fall Stock:\n");
        for (Item item : fallStock) {
            PlantingSource stock = (PlantingSource) item;
            result
                    .append("Name: " + stock.getName() + "\n")
                    .append("Price: " + stock.getPrice() + "\n")
                    .append("Description: " + stock.getDescription() + "\n")
                    .append("Daily Limit: ");
            if (getFallStockLimit(stock) >= 0) {
                result.append(getFallStockLimit(stock) + "\n");
            } else {
                result.append("unlimited\n");
            }
            result.append("----------------------\n");
        }

        result.append("\n* Winter Stock:\n");
        for (Item item : winterStock) {
            PlantingSource stock = (PlantingSource) item;
            result
                    .append("Name: " + stock.getName() + "\n")
                    .append("Price: " + stock.getPrice() + "\n")
                    .append("Description: " + stock.getDescription() + "\n")
                    .append("Daily Limit: ");
            if (getWinterStockLimit(stock) >= 0) {
                result.append(getWinterStockLimit(stock) + "\n");
            } else {
                result.append("unlimited\n");
            }
            result.append("----------------------\n");
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public String showAvailableProducts(Season season) {
        StringBuilder result = new StringBuilder();

        result.append("----- Blacksmith -----\n");
        result.append("\n* Permanent Stock:\n");
        for (Item item : permanentStock) {
            if (getPermanentStockLimit(item) > 0 || getPermanentStockLimit(item) == -11) {
                if (item instanceof PlantingSource stock) {
                    result
                            .append("Name: " + stock.getName() + "\n")
                            .append("Price: " + stock.getPrice() + "\n")
                            .append("Description: " + stock.getDescription() + "\n")
                            .append("Daily Limit: ");
                    if (getPermanentStockLimit(stock) >= 0) {
                        result.append(getPermanentStockLimit(stock) + "\n");
                    } else {
                        result.append("unlimited\n");
                    }
                    result.append("----------------------\n");
                }
                else if (item instanceof PlacingCraft stock) {
                    result
                            .append("Name: " + stock.getName() + "\n")
                            .append("Price: " + stock.getPrice() + "\n")
                            .append("Description: " + stock.getDescription() + "\n")
                            .append("Daily Limit: ");
                    if (getPermanentStockLimit(stock) >= 0) {
                        result.append(getPermanentStockLimit(stock) + "\n");
                    } else {
                        result.append("unlimited\n");
                    }
                    result.append("----------------------\n");
                }
            }
        }

        if (season == Season.SPRING) {
            result.append("\n* Spring Stock:\n");
            for (Item item : springStock) {
                if (getSpringStockLimit(item) > 0 || getSpringStockLimit(item) == -11) {
                    PlantingSource stock = (PlantingSource) item;
                    result
                            .append("Name: " + stock.getName() + "\n")
                            .append("Price: " + stock.getPrice() + "\n")
                            .append("Description: " + stock.getDescription() + "\n")
                            .append("Daily Limit: ");
                    if (getSpringStockLimit(stock) >= 0) {
                        result.append(getSpringStockLimit(stock) + "\n");
                    } else {
                        result.append("unlimited\n");
                    }
                    result.append("----------------------\n");
                }
            }
        } else if (season == Season.SUMMER) {
            result.append("\n* Summer Stock:\n");
            for (Item item : summerStock) {
                if (getSummerStockLimit(item) > 0 || getSummerStockLimit(item) == -11) {
                    PlantingSource stock = (PlantingSource) item;
                    result
                            .append("Name: " + stock.getName() + "\n")
                            .append("Price: " + stock.getPrice() + "\n")
                            .append("Description: " + stock.getDescription() + "\n")
                            .append("Daily Limit: ");
                    if (getSummerStockLimit(stock) >= 0) {
                        result.append(getSummerStockLimit(stock) + "\n");
                    } else {
                        result.append("unlimited\n");
                    }
                    result.append("----------------------\n");
                }
            }
        } else if (season == Season.FALL) {
            result.append("\n* Fall Stock:\n");
            for (Item item : fallStock) {
                if (getFallStockLimit(item) > 0 || getFallStockLimit(item) == -11) {
                    PlantingSource stock = (PlantingSource) item;
                    result
                            .append("Name: " + stock.getName() + "\n")
                            .append("Price: " + stock.getPrice() + "\n")
                            .append("Description: " + stock.getDescription() + "\n")
                            .append("Daily Limit: ");
                    if (getFallStockLimit(stock) >= 0) {
                        result.append(getFallStockLimit(stock) + "\n");
                    } else {
                        result.append("unlimited\n");
                    }
                    result.append("----------------------\n");
                }
            }
        } else if (season == Season.WINTER) {
            result.append("\n* Winter Stock:\n");
            for (Item item : winterStock) {
                if (getWinterStockLimit(item) > 0 || getWinterStockLimit(item) == -11) {
                    PlantingSource stock = (PlantingSource) item;
                    result
                            .append("Name: " + stock.getName() + "\n")
                            .append("Price: " + stock.getPrice() + "\n")
                            .append("Description: " + stock.getDescription() + "\n")
                            .append("Daily Limit: ");
                    if (getWinterStockLimit(stock) >= 0) {
                        result.append(getWinterStockLimit(stock) + "\n");
                    } else {
                        result.append("unlimited\n");
                    }
                    result.append("----------------------\n");
                }
            }
        }


        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public Item getProductByName(String name) {
        for (Item item : permanentStock) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        for (Item item : springStock) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        for (Item item : summerStock) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        for (Item item : fallStock) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        for (Item item : winterStock) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void buy(Item product, int amount) {
        if (permanentStock.contains(product)) {
            permanentStockLimit.replace(product, permanentStockLimit.get(product) - amount);
        }
        else if (springStock.contains(product)) {
            springStockLimit.replace(product, springStockLimit.get(product) - amount);
        }
        else if (summerStock.contains(product)) {
            summerStockLimit.replace(product, summerStockLimit.get(product) - amount);
        }
        else if (fallStock.contains(product)) {
            fallStockLimit.replace(product, fallStockLimit.get(product) - amount);
        }
        else if (winterStock.contains(product)) {
            winterStockLimit.replace(product, winterStockLimit.get(product) - amount);
        }
    }

    public boolean isAvailable(Item product, int amount, Season season) {
        if (permanentStock.contains(product)) {
            if (getPermanentStockLimit(product) < amount && getPermanentStockLimit(product) != -11) {
                return false;
            }
            return true;
        }
        else if (springStock.contains(product)) {
            if (getSpringStockLimit(product) < amount && getSpringStockLimit(product) != -11) {
                return false;
            } else if (season != Season.SPRING) {
                return false;
            }
            return true;
        }
        else if (summerStock.contains(product)) {
            if (getSummerStockLimit(product) < amount && getSummerStockLimit(product) != -11) {
                return false;
            } else if (season != Season.SUMMER) {
                return false;
            }
            return true;
        }
        else if (fallStock.contains(product)) {
            if (getFallStockLimit(product) < amount && getFallStockLimit(product) != -11) {
                return false;
            } else if (season != Season.FALL) {
                return false;
            }
            return true;
        }
        else if (winterStock.contains(product)) {
            if (getWinterStockLimit(product) < amount && getWinterStockLimit(product) != -11) {
                return false;
            } else if (season != Season.WINTER) {
                return false;
            }
            return true;
        }
        return false;
    }
}























