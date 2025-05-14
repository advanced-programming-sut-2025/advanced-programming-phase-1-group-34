package org.Group34.model.entities.buildings.shops;

import org.Group34.model.entities.Entity;
import org.Group34.model.entities.buildings.Barn;
import org.Group34.model.entities.buildings.Coop;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

public class CarpenterShop {
    private static final String name = "Carpenter's Shop";
    private static final String ownerName = "Robin";
    private static final int openingHour = 9;
    private static final int closingHour = 20;
    private static final ArrayList<Item> permanentStock = new ArrayList<>();
    private static final ArrayList<Entity> farmBuildings = new ArrayList<>();
    private static final HashMap<Item, Integer> permanentStockLimit = new HashMap<>();
    private static final HashMap<Entity, Integer> farmBuildingsLimit = new HashMap<>();
    static {
        permanentStock.add(Ingredient.WOOD);
        permanentStock.add(Ingredient.STONE);

        permanentStockLimit.put(permanentStock.get(0), -11);
        permanentStockLimit.put(permanentStock.get(1), -11);


        farmBuildings.add(new Barn(BarnType.BARN_BASIC));
        farmBuildings.add(new Barn(BarnType.BARN_BIG));
        farmBuildings.add(new Barn(BarnType.BARN_DELUXE));
        farmBuildings.add(new Coop(BarnType.COOP_BASIC));
        farmBuildings.add(new Coop(BarnType.COOP_BIG));
        farmBuildings.add(new Coop(BarnType.COOP_DELUXE));
//        farmBuildings.add(Well);
//        farmBuildings.add(ShippingBin);

        farmBuildingsLimit.put(farmBuildings.get(0), 1);
        farmBuildingsLimit.put(farmBuildings.get(1), 1);
        farmBuildingsLimit.put(farmBuildings.get(2), 1);
        farmBuildingsLimit.put(farmBuildings.get(3), 1);
        farmBuildingsLimit.put(farmBuildings.get(4), 1);
        farmBuildingsLimit.put(farmBuildings.get(5), 1);
        farmBuildingsLimit.put(farmBuildings.get(6), 1);
        farmBuildingsLimit.put(farmBuildings.get(7), -11);
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

    public static ArrayList<Entity> getFarmBuildings() {
        return farmBuildings;
    }

    public static HashMap<Item, Integer> getPermanentStockLimit() {
        return permanentStockLimit;
    }

    public static HashMap<Entity, Integer> getFarmBuildingsLimit() {
        return farmBuildingsLimit;
    }
    // -----------------------------


    public static int getPermanentStockLimit(Item stock) {
        return permanentStockLimit.get(stock);
    }

    public static int getFarmBuildingLimit(Entity building) {
        return farmBuildingsLimit.get(building);
    }
}
