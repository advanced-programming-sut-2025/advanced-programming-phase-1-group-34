package org.Group34.model.entities.buildings.shops;

import org.Group34.model.entities.buildings.shops.products.UpgradeTools;
import org.Group34.model.enums.Color;
import org.Group34.model.items.Item;
import org.Group34.model.items.Recipe;

import java.util.ArrayList;
import java.util.HashMap;

public class FishShop extends Shop {
    private static final String name = "Fish Shop";
    private static final String ownerName = "Willy";
    private static final int openingHour = 9;
    private static final int closingHour = 17;
    private static final ArrayList<Item> stock = new ArrayList<>();
    private HashMap<Item, Integer> stockLimit = new HashMap<>();
    static {
        stock.add(Recipe.FISH_SMOKER);
        stock.add(UpgradeTools.TRAINING_FISHING_POLE);
        stock.add(UpgradeTools.BAMBOO_FISHING_POLE);
        stock.add(UpgradeTools.FIBERGLASS_FISHING_POLE);
        stock.add(UpgradeTools.IRIDIUM_FISHING_POLE);
    }
    {
        stockLimit.put(stock.get(0), 1);
        stockLimit.put(stock.get(2), 1);
        stockLimit.put(stock.get(3), 1);
        stockLimit.put(stock.get(4), 1);
        stockLimit.put(stock.get(5), 1);
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

    public static ArrayList<Item> getStock() {
        return stock;
    }

    public HashMap<Item, Integer> getStockLimit() {
        return stockLimit;
    }
    // -----------------------------


    public int getStockLimit(Item stock) {
        return stockLimit.get(stock);
    }

    public String showAllProducts() {
        StringBuilder result = new StringBuilder();

        result.append("----- Fish Shop -----\n");
        result.append("\n* Stock:\n");
        for (Item item : stock) {
            if (item instanceof Recipe stock) {
                result
                        .append("Name: " + stock.getName() + "\n")
                        .append("Price: " + stock.getPrice() + "\n")
                        .append("Daily Limit: ");
                if (getStockLimit(stock) >= 0) {
                    result.append(getStockLimit(stock) + "\n");
                } else {
                    result.append("unlimited\n");
                }
                result.append("----------------------\n");
            }
            else if (item instanceof UpgradeTools stock) {
                result
                        .append("Name: " + stock.getName() + "\n")
                        .append("Price: " + stock.getPrice() + "\n")
                        .append("Daily Limit: ");
                if (getStockLimit(stock) >= 0) {
                    result.append(getStockLimit(stock) + "\n");
                } else {
                    result.append("unlimited\n");
                }
                result.append("----------------------\n");
            }
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public String shopAvailableProducts() {
        StringBuilder result = new StringBuilder();

        result.append("----- Fish Shop -----\n");
        result.append("\n* Stock:\n");
        for (Item item : stock) {
            if (getStockLimit(item) > 0 || getStockLimit(item) == -11) {
                if (item instanceof Recipe stock) {
                    result
                            .append("Name: " + stock.getName() + "\n")
                            .append("Price: " + stock.getPrice() + "\n")
                            .append("Daily Limit: ");
                    if (getStockLimit(stock) >= 0) {
                        result.append(getStockLimit(stock) + "\n");
                    } else {
                        result.append("unlimited\n");
                    }
                    result.append("----------------------\n");
                }
                else if (item instanceof UpgradeTools stock) {
                    result
                            .append("Name: " + stock.getName() + "\n")
                            .append("Price: " + stock.getPrice() + "\n")
                            .append("Daily Limit: ");
                    if (getStockLimit(stock) >= 0) {
                        result.append(getStockLimit(stock) + "\n");
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
        for (Item item : stock) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void buy(Item product, int amount) {
        if (stock.contains(product)) {
            stockLimit.remove(product, stockLimit.get(product) - amount);
        }
    }

    @Override
    public String toString() {
        return Color.YELLOW + "F" + Color.RESET;
    }
}
