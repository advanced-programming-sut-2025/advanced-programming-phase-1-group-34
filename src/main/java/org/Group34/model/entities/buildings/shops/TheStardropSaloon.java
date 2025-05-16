package org.Group34.model.entities.buildings.shops;

import org.Group34.model.items.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class TheStardropSaloon extends Shop {
    private static final String name = "The Stardrop Saloon";
    private static final String ownerName = "Gus";
    private static final int openingHour = 12;
    private static final int closingHour = 0;
    private ArrayList<Item> permanentStock = new ArrayList<>();
    private HashMap<Item, Integer> permanentStockLimit = new HashMap<>();
    static {
        // TODO
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


    public int getPermanentStock(Item stock) {
        return permanentStockLimit.get(stock);
    }
}
