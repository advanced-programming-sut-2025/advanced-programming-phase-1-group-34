package org.Group34.model.entities.buildings.shops;

import org.Group34.model.entities.Entity;
import org.Group34.model.entities.buildings.shops.products.ShippingBin;
import org.Group34.model.entities.buildings.shops.products.UpgradeTools;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;

import java.lang.classfile.instruction.ReturnInstruction;
import java.util.ArrayList;
import java.util.HashMap;

public class CarpenterShop extends Shop {
    private static final String name = "Carpenter's Shop";
    private static final String ownerName = "Robin";
    private static final int openingHour = 9;
    private static final int closingHour = 20;
    private static final ArrayList<Item> permanentStock = new ArrayList<>();
    private static final ArrayList<Item> farmBuildings = new ArrayList<>();
    private HashMap<Item, Integer> permanentStockLimit = new HashMap<>();
    private HashMap<Item, Integer> farmBuildingsLimit = new HashMap<>();
    static {
        permanentStock.add(Ingredient.WOOD);
        permanentStock.add(Ingredient.STONE);

        farmBuildings.add(BarnType.BARN_BASIC);
        farmBuildings.add(BarnType.BARN_BIG);
        farmBuildings.add(BarnType.BARN_DELUXE);
        farmBuildings.add(BarnType.COOP_BASIC);
        farmBuildings.add(BarnType.COOP_BIG);
        farmBuildings.add(BarnType.COOP_DELUXE);
        farmBuildings.add(new ShippingBin());
    }
    {
        permanentStockLimit.put(permanentStock.get(0), -11);
        permanentStockLimit.put(permanentStock.get(1), -11);

        farmBuildingsLimit.put(farmBuildings.get(0), 1);
        farmBuildingsLimit.put(farmBuildings.get(1), 1);
        farmBuildingsLimit.put(farmBuildings.get(2), 1);
        farmBuildingsLimit.put(farmBuildings.get(3), 1);
        farmBuildingsLimit.put(farmBuildings.get(4), 1);
        farmBuildingsLimit.put(farmBuildings.get(5), 1);
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

    public static ArrayList<Item> getFarmBuildings() {
        return farmBuildings;
    }

    public HashMap<Item, Integer> getPermanentStockLimit() {
        return permanentStockLimit;
    }

    public HashMap<Item, Integer> getFarmBuildingsLimit() {
        return farmBuildingsLimit;
    }
    // -----------------------------


    public int getPermanentStockLimit(Item stock) {
        return permanentStockLimit.get(stock);
    }

    public int getFarmBuildingLimit(Item building) {
        return farmBuildingsLimit.get(building);
    }

    public String showAllProducts() {
        StringBuilder result = new StringBuilder();

        result.append("----- Blacksmith -----\n");
        result.append("\n* Permanent Stock:\n");
        for (Item item : permanentStock) {
            Ingredient stock = (Ingredient) item;
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

        result.append("\n* Farm Buildings:\n");
        for (Item building : farmBuildings) {
            if (building instanceof BarnType item) {
                result
                        .append("Name: " + item.getName() + "\n")
                        .append("Price: " + item.getPrice() + "\n")
                        .append("Description: " + item.getDescription() + "\n")
                        .append("Daily Limit: ");
                if (getFarmBuildingLimit(item) >= 0) {
                    result.append(getFarmBuildingLimit(item) + "\n");
                } else {
                    result.append("unlimited\n");
                }
                result.append("----------------------\n");
            }
            else if (building instanceof ShippingBin item) {
                result
                        .append("Name: " + item.getName() + "\n")
                        .append("Price: " + item.getPrice() + "\n")
                        .append("Description: " + item.getDescription() + "\n")
                        .append("Daily Limit: ");
                if (getFarmBuildingLimit(item) >= 0) {
                    result.append(getFarmBuildingLimit(item) + "\n");
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

        result.append("----- Blacksmith -----\n");
        result.append("\n* Permanent Stock:\n");
        for (Item item : permanentStock) {
            if (getPermanentStockLimit(item) > 0 || getPermanentStockLimit(item) == -11) {
                Ingredient stock = (Ingredient) item;
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

        result.append("\n* Farm Buildings:\n");
        for (Item building : farmBuildings) {
            if (getFarmBuildingLimit(building) > 0 || getFarmBuildingLimit(building) == -11) {
                if (building instanceof BarnType item) {
                    result
                            .append("Name: " + item.getName() + "\n")
                            .append("Price: " + item.getPrice() + "\n")
                            .append("Description: " + item.getDescription() + "\n")
                            .append("Daily Limit: ");
                    if (getFarmBuildingLimit(item) >= 0) {
                        result.append(getFarmBuildingLimit(item) + "\n");
                    } else {
                        result.append("unlimited\n");
                    }
                    result.append("----------------------\n");
                }
                else if (building instanceof ShippingBin item) {
                    result
                            .append("Name: " + item.getName() + "\n")
                            .append("Price: " + item.getPrice() + "\n")
                            .append("Description: " + item.getDescription() + "\n")
                            .append("Daily Limit: ");
                    if (getFarmBuildingLimit(item) >= 0) {
                        result.append(getFarmBuildingLimit(item) + "\n");
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
        for (Item item : farmBuildings) {
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
        else if (farmBuildings.contains(product)) {
            farmBuildingsLimit.replace(product, farmBuildingsLimit.get(product) - amount);
        }
    }
}
