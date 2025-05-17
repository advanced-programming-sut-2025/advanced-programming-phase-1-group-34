package org.Group34.model.entities.buildings.shops;

import org.Group34.model.items.Item;
import org.Group34.model.items.Recipe;
import org.Group34.model.items.foods.CookedFood;
import org.Group34.model.items.foods.ProcessedFood;

import java.util.ArrayList;
import java.util.HashMap;

public class TheStardropSaloon extends Shop {
    private static final String name = "The Stardrop Saloon";
    private static final String ownerName = "Gus";
    private static final int openingHour = 12;
    private static final int closingHour = 0;
    private static final ArrayList<Item> permanentStock = new ArrayList<>();
    private HashMap<Item, Integer> permanentStockLimit = new HashMap<>();
    static {
        permanentStock.add(ProcessedFood.BEER);
        permanentStock.add(CookedFood.SALAD);
        permanentStock.add(CookedFood.BREAD);
        permanentStock.add(CookedFood.SPAGHETTI);
        permanentStock.add(CookedFood.PIZZA);
        permanentStock.add(ProcessedFood.COFFEE);
        permanentStock.add(Recipe.HASH_BROWNS);
        permanentStock.add(Recipe.OMELET);
        permanentStock.add(Recipe.PANCAKES);
        permanentStock.add(Recipe.BREAD);
        permanentStock.add(Recipe.TORTILLA);
        permanentStock.add(Recipe.PIZZA);
        permanentStock.add(Recipe.MAKI_ROLL);
        permanentStock.add(Recipe.TRIPLE_SHOT_ESPRESSO);
        permanentStock.add(Recipe.COOKIE);
    }
    {
        permanentStockLimit.put(permanentStock.get(0), -11);
        permanentStockLimit.put(permanentStock.get(1), -11);
        permanentStockLimit.put(permanentStock.get(2), -11);
        permanentStockLimit.put(permanentStock.get(3), -11);
        permanentStockLimit.put(permanentStock.get(4), -11);
        permanentStockLimit.put(permanentStock.get(5), -11);
        permanentStockLimit.put(permanentStock.get(6), 1);
        permanentStockLimit.put(permanentStock.get(7), 1);
        permanentStockLimit.put(permanentStock.get(8), 1);
        permanentStockLimit.put(permanentStock.get(9), 1);
        permanentStockLimit.put(permanentStock.get(10), 1);
        permanentStockLimit.put(permanentStock.get(11), 1);
        permanentStockLimit.put(permanentStock.get(12), 1);
        permanentStockLimit.put(permanentStock.get(13), 1);
        permanentStockLimit.put(permanentStock.get(14), 1);
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

    public ArrayList<Item> getPermanentStock() {
        return permanentStock;
    }

    public HashMap<Item, Integer> getPermanentStockLimit() {
        return permanentStockLimit;
    }
    // -----------------------------


    public int getPermanentStockLimit(Item stock) {
        return permanentStockLimit.get(stock);
    }

    public String showAllProducts() {
        StringBuilder result = new StringBuilder();

        result.append("----- The Stardrop Saloon -----\n");
        result.append("\n* Permanent Stocks:\n");
        for (Item item : permanentStock) {
            if (item instanceof ProcessedFood stock) {
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
            else if (item instanceof CookedFood stock) {
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
            else if (item instanceof Recipe stock) {
                result
                        .append("Name: " + stock.getName() + "\n")
                        .append("Price: " + stock.getPrice() + "\n")
                        .append("Daily Limit: ");
                if (getPermanentStockLimit(stock) >= 0) {
                    result.append(getPermanentStockLimit(stock) + "\n");
                } else {
                    result.append("unlimited\n");
                }
                result.append("----------------------\n");
            }
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public String showAvailableProducts() {
        StringBuilder result = new StringBuilder();

        result.append("----- The Stardrop Saloon -----\n");
        result.append("\n* Permanent Stocks:\n");
        for (Item item : permanentStock) {
            if (getPermanentStockLimit(item) > 0 || getPermanentStockLimit(item) == -11) {
                if (item instanceof ProcessedFood stock) {
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
                else if (item instanceof CookedFood stock) {
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
                else if (item instanceof Recipe stock) {
                    result
                            .append("Name: " + stock.getName() + "\n")
                            .append("Price: " + stock.getPrice() + "\n")
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

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public Item getProductByName(String name) {
        for (Item item : permanentStock) {
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
    }
}
