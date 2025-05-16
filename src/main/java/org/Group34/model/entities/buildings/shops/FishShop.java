package org.Group34.model.entities.buildings.shops;

import org.Group34.model.items.Item;

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
//        stock.add(Recipe.FISH_SMOKER);
//        stock.add(Trout Soup);
//        stock.add(Bamboo Pole);
//        stock.add(Training Rod);
//        stock.add(Fiberglass Rod);
//        stock.add(Iridium Rod);


    }
    {
        stockLimit.put(stock.get(0), 1);
        stockLimit.put(stock.get(1), 1);
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
}
