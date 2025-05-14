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
    private static final HashMap<Item, Integer> shopInventoryLimit = new HashMap<>();
    private static final HashMap<Item, Integer> livesTockLimit = new HashMap<>();
    static {
//        shopInventory.add(Hay); TODO
        shopInventory.add(new MilkPail());
        shopInventory.add(new Shear());

        shopInventoryLimit.put(shopInventory.get(0), -11);
        shopInventoryLimit.put(shopInventory.get(1), 1);
        shopInventoryLimit.put(shopInventory.get(2), 1);


        livesTock.add(AnimalType.CHICKEN);
        livesTock.add(AnimalType.COW);
        livesTock.add(AnimalType.GOAT);
        livesTock.add(AnimalType.DUCK);
        livesTock.add(AnimalType.SHEEP);
        livesTock.add(AnimalType.RABBIT);
        livesTock.add(AnimalType.DINOSAUR);
        livesTock.add(AnimalType.PIG);

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

    public static HashMap<Item, Integer> getShopInventoryLimit() {
        return shopInventoryLimit;
    }

    public static HashMap<Item, Integer> getLivesTockLimit() {
        return livesTockLimit;
    }
    // -----------------------------

    public static int getShopInventory(Item inventory) {
        return shopInventoryLimit.get(inventory);
    }

    public static int getLiveTock(Item liveTock) {
        return livesTockLimit.get(liveTock);
    }
}
