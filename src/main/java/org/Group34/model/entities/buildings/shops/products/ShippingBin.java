package org.Group34.model.entities.buildings.shops.products;

import org.Group34.model.items.Item;

public class ShippingBin implements Item {
    private String description = "Items placed in it will be included in the nightly shipment.";
    private int price = 1000;
    private int woodCost = 150;
    private int stoneCost = 0;


    public String getName() {
        return "Shipping Bin";
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getWoodCost() {
        return woodCost;
    }

    public int getStoneCost() {
        return stoneCost;
    }
}
