package org.Group34.model.entities.buildings.shops.products;

import org.Group34.model.items.Item;

public class ShippingBin implements Item {
    private static String description = "Items placed in it will be included in the nightly shipment.";
    private static int price = 1000;
    private static int woodCost = 150;
    private static int stoneCost = 0;
    private Item item;
    private int amountOfItem;

    public ShippingBin(Item item, int amountOfItem) {
        this.item = item;
        this.amountOfItem = amountOfItem;
    }

    public String getName() {
        return "Shipping Bin";
    }

    public static String getDescription() {
        return description;
    }

    public static int getPrice() {
        return price;
    }

    public static int getWoodCost() {
        return woodCost;
    }

    public static int getStoneCost() {
        return stoneCost;
    }

    public Item getItem() {
        return item;
    }

    public int getAmountOfItem() {
        return amountOfItem;
    }
}
