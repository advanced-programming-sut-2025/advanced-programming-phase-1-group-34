package org.Group34.model.entities.buildings.shops;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.buildings.shops.products.UpgradeTools;
import org.Group34.model.enums.Color;
import org.Group34.model.gameAssetManagers.BuildingsAssetManager;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

public class Blacksmith extends Shop {
    private static final String name = "Blacksmith";
    private static final String ownerName = "Clint";
    private static final int openingHour = 9;
    private static final int closingHour = 16;
    private static final ArrayList<Item> stocks = new ArrayList<>();
    private static final ArrayList<Item> upgradeTools = new ArrayList<>();
    private HashMap<Item, Integer> stocksLimit = new HashMap<>();
    private HashMap<Item, Integer> upgradeToolsLimit = new HashMap<>();
    static {
        stocks.add(Ingredient.COPPER_ORE);
        stocks.add(Ingredient.IRON_ORE);
        stocks.add(Ingredient.COAL);
        stocks.add(Ingredient.GOLD_ORE);

        upgradeTools.add(UpgradeTools.COPPER_TOOL);
        upgradeTools.add(UpgradeTools.STEEL_TOOL);
        upgradeTools.add(UpgradeTools.GOLD_TOOL);
        upgradeTools.add(UpgradeTools.IRIDIUM_TOOL);
        upgradeTools.add(UpgradeTools.COPPER_TRASH_CAN);
        upgradeTools.add(UpgradeTools.STEEL_TRASH_CAN);
        upgradeTools.add(UpgradeTools.GOLD_TRASH_CAN);
        upgradeTools.add(UpgradeTools.IRIDIUM_TRASH_CAN);
    }
    {
        stocksLimit.put(stocks.get(0), -11);
        stocksLimit.put(stocks.get(1), -11);
        stocksLimit.put(stocks.get(2), -11);
        stocksLimit.put(stocks.get(3), -11);

        upgradeToolsLimit.put(upgradeTools.get(0), 1);
        upgradeToolsLimit.put(upgradeTools.get(1), 1);
        upgradeToolsLimit.put(upgradeTools.get(2), 1);
        upgradeToolsLimit.put(upgradeTools.get(3), 1);
        upgradeToolsLimit.put(upgradeTools.get(4), 1);
        upgradeToolsLimit.put(upgradeTools.get(5), 1);
        upgradeToolsLimit.put(upgradeTools.get(6), 1);
        upgradeToolsLimit.put(upgradeTools.get(7), 1);
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

    public static ArrayList<Item> getStocks() {
        return stocks;
    }

    public static ArrayList<Item> getUpgradeTools() {
        return upgradeTools;
    }

    public HashMap<Item, Integer> getStocksLimit() {
        return stocksLimit;
    }

    public HashMap<Item, Integer> getUpgradeToolsLimit() {
        return upgradeToolsLimit;
    }
    // -----------------------------

    public int getStockLimit(Item stoke) {
        return stocksLimit.get(stoke);
    }

    public int getUpgradeToolLimit(Item upgradeTool) {
        return upgradeToolsLimit.get(upgradeTool);
    }

    public String showAllProducts() {
        StringBuilder result = new StringBuilder();

        result.append("----- Blacksmith -----\n");
        result.append("\n* Stocks:\n");
        for (Item stock : stocks) {
            Ingredient item = (Ingredient) stock;
            result
                    .append("Name: " + item.getName() + "\n")
                    .append("Price: " + item.getPrice() + "\n")
                    .append("Description: " + item.getDescription() + "\n")
                    .append("Daily Limit: ");
            if (getStockLimit(item) >= 0) {
                result.append(getStockLimit(item) + "\n");
            } else {
                result.append("unlimited\n");
            }
            result.append("----------------------\n");
        }

        result.append("\n* Upgrade Tools:\n");
        for (Item tool : upgradeTools) {
            UpgradeTools item = (UpgradeTools) tool;
            result
                    .append("Name: " + item.getName() + "\n")
                    .append("Price: " + item.getPrice() + "\n")
                    .append("Ingredient: " + item.getIngredient().getName() + "\n")
                    .append("Daily Limit: ");
            if (getUpgradeToolLimit(item) >= 0) {
                result.append(getUpgradeToolLimit(item) + "\n");
            } else {
                result.append("unlimited\n");
            }
            result.append("----------------------\n");
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public String showAvailableProducts() {
        StringBuilder result = new StringBuilder();

        result.append("----- Blacksmith -----\n");
        result.append("\n* Stocks:\n");
        for (Item stock : stocks) {
            Ingredient item = (Ingredient) stock;
            if (getStockLimit(item) > 0 || getStockLimit(item) == -11) {
                result
                        .append("Name: " + item.getName() + "\n")
                        .append("Price: " + item.getPrice() + "\n")
                        .append("Description: " + item.getDescription() + "\n")
                        .append("Daily Limit: ");
                if (getStockLimit(item) >= 0) {
                    result.append(getStockLimit(item) + "\n");
                } else {
                    result.append("unlimited\n");
                }
                result.append("----------------------\n");
            }
        }

        result.append("\n* Upgrade Tools:\n");
        for (Item tool : upgradeTools) {
            UpgradeTools item = (UpgradeTools) tool;
            if (getStockLimit(item) > 0 || getStockLimit(item) == -11) {
                result
                        .append("Name: " + item.getName() + "\n")
                        .append("Price: " + item.getPrice() + "\n")
                        .append("Ingredient: " + item.getIngredient().getName() + "\n")
                        .append("Daily Limit: ");
                if (getStockLimit(item) >= 0) {
                    result.append(getStockLimit(item) + "\n");
                } else {
                    result.append("unlimited\n");
                }
                result.append("----------------------\n");
            }
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public Item getProductByName(String name) {
        for (Item stock : stocks) {
             if (stock.getName().equals(name)) {
                 return stock;
             }
        }
        for (Item tool : upgradeTools) {
            if (tool.getName().equals(name)) {
                return tool;
            }
        }
        return null;
    }

    public void buy(Item product, int amount) {
        if (stocks.contains(product)) {
            stocksLimit.replace(product, stocksLimit.get(product) - amount);
        }
        else if (upgradeTools.contains(product)) {
            upgradeToolsLimit.replace(product, upgradeToolsLimit.get(product) - amount);
        }
    }

    @Override
    public String toString() {
        return Color.YELLOW + "B" + Color.RESET;
    }

    @Override
    public Texture getTexture() {
        return BuildingsAssetManager.blacksmithShop;
    }
}
