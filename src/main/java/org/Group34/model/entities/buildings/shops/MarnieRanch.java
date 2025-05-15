package org.Group34.model.entities.buildings.shops;

import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.items.Item;
import org.Group34.model.items.tools.MilkPail;
import org.Group34.model.items.tools.Shear;

import java.util.ArrayList;
import java.util.HashMap;

public class MarnieRanch extends Shop {
    private static final String name = "Marnie's Ranch";
    private static final String ownerName = "Marnie";
    private static final int openingHour = 9;
    private static final int closingHour = 16;
    private static final ArrayList<Item> shopInventory = new ArrayList<>();
    private static final ArrayList<Item> livesTock = new ArrayList<>();
    private HashMap<Item, Integer> shopInventoryLimit = new HashMap<>();
    private HashMap<Item, Integer> livesTockLimit = new HashMap<>();
    static {
//        shopInventory.add(Hay); TODO
        shopInventory.add(new MilkPail());
        shopInventory.add(new Shear());

        livesTock.add(AnimalType.CHICKEN);
        livesTock.add(AnimalType.COW);
        livesTock.add(AnimalType.GOAT);
        livesTock.add(AnimalType.DUCK);
        livesTock.add(AnimalType.SHEEP);
        livesTock.add(AnimalType.RABBIT);
        livesTock.add(AnimalType.DINOSAUR);
        livesTock.add(AnimalType.PIG);
    }
    {
        shopInventoryLimit.put(shopInventory.get(0), -11);
        shopInventoryLimit.put(shopInventory.get(1), 1);
        shopInventoryLimit.put(shopInventory.get(2), 1);

        livesTockLimit.put(livesTock.get(0), 2);
        livesTockLimit.put(livesTock.get(1), 2);
        livesTockLimit.put(livesTock.get(2), 2);
        livesTockLimit.put(livesTock.get(3), 2);
        livesTockLimit.put(livesTock.get(4), 2);
        livesTockLimit.put(livesTock.get(5), 2);
        livesTockLimit.put(livesTock.get(6), 2);
        livesTockLimit.put(livesTock.get(7), 2);
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

    public static ArrayList<Item> getShopInventory() {
        return shopInventory;
    }

    public static ArrayList<Item> getLivesTock() {
        return livesTock;
    }

    public HashMap<Item, Integer> getShopInventoryLimit() {
        return shopInventoryLimit;
    }

    public HashMap<Item, Integer> getLivesTockLimit() {
        return livesTockLimit;
    }
    // -----------------------------

    public int getShopInventoryLimit(Item inventory) {
        return shopInventoryLimit.get(inventory);
    }

    public int getLiveTockLimit(Item liveTock) {
        return livesTockLimit.get(liveTock);
    }

    public String showAllProducts() {
        StringBuilder result = new StringBuilder();

        result.append("----- Blacksmith -----\n");
        result.append("\n* Shop Inventory:\n");
        for (Item item : shopInventory) {
            if (item instanceof MilkPail tool) {
                result
                        .append("Name: " + tool.getName() + "\n")
                        .append("Price: " + tool.getPrice() + "\n")
                        .append("Description: " + tool.getDescription() + "\n")
                        .append("Daily Limit: ");
                if (getShopInventoryLimit(item) >= 0) {
                    result.append(getShopInventoryLimit(item) + "\n");
                } else {
                    result.append("unlimited\n");
                }
            }
            else if (item instanceof Shear tool) {
                result
                        .append("Name: " + tool.getName() + "\n")
                        .append("Price: " + tool.getPrice() + "\n")
                        .append("Description: " + tool.getDescription() + "\n")
                        .append("Daily Limit: ");
                if (getShopInventoryLimit(item) >= 0) {
                    result.append(getShopInventoryLimit(item) + "\n");
                } else {
                    result.append("unlimited\n");
                }
            }
            result.append("----------------------\n");
        }

        result.append("\n* Lives Tock:\n");
        for (Item item : livesTock) {
            AnimalType tool = (AnimalType) item;
            result
                    .append("Name: " + tool.getName() + "\n")
                    .append("Price: " + tool.getPrice() + "\n")
                    .append("Description: " + tool.getDescription() + "\n")
                    .append("Daily Limit: ");
            if (getLiveTockLimit(item) >= 0) {
                result.append(getLiveTockLimit(item) + "\n");
            } else {
                result.append("unlimited\n");
            }
            result.append("----------------------\n");
        }

        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
