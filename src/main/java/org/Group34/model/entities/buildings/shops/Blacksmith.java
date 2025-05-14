package org.Group34.model.entities.buildings.shops;

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
    private static final HashMap<Item, Integer> stocksLimit = new HashMap<>();
    private static final HashMap<Item, Integer> upgradeToolsLimit = new HashMap<>();
    static {
        stocks.add(Ingredient.COPPER_ORE);
        stocks.add(Ingredient.IRON_ORE);
        stocks.add(Ingredient.COAL);
        stocks.add(Ingredient.GOLD_ORE);

        stocksLimit.put(stocks.get(0), -11);
        stocksLimit.put(stocks.get(1), -11);
        stocksLimit.put(stocks.get(2), -11);
        stocksLimit.put(stocks.get(3), -11);


        upgradeTools.add(UpgradeTools.COPPER_TOOL);
        upgradeTools.add(UpgradeTools.STEEL_TOOL);
        upgradeTools.add(UpgradeTools.GOLD_TOOL);
        upgradeTools.add(UpgradeTools.IRIDIUM_TOOL);
        upgradeTools.add(UpgradeTools.COPPER_TRASH_CAN);
        upgradeTools.add(UpgradeTools.STEEL_TRASH_CAN);
        upgradeTools.add(UpgradeTools.GOLD_TRASH_CAN);
        upgradeTools.add(UpgradeTools.IRIDIUM_TRASH_CAN);

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

    public static HashMap<Item, Integer> getStocksLimit() {
        return stocksLimit;
    }

    public static HashMap<Item, Integer> getUpgradeToolsLimit() {
        return upgradeToolsLimit;
    }
    // -----------------------------

    public static int getStockLimit(Item stoke) {
        return stocksLimit.get(stoke);
    }

    public static int getUpgradeToolLimit(Item upgradeTool) {
        return upgradeToolsLimit.get(upgradeTool);
    }
}
