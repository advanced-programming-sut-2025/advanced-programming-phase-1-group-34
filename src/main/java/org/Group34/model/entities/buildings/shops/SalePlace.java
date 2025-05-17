package org.Group34.model.entities.buildings.shops;

import org.Group34.model.entities.buildings.Building;
import org.Group34.model.entities.buildings.shops.products.ShippingBin;
import org.Group34.model.items.Item;

import java.util.ArrayList;

public class SalePlace extends Building {
    private int numberOfShippingBins = 0;
    private ArrayList<ShippingBin> shippingBins = new ArrayList<>();


    public ArrayList<ShippingBin> getShippingBins() {
        return shippingBins;
    }

    public int getNumberOfShippingBins() {
        return numberOfShippingBins;
    }

    public void addItemToSale(Item item, int count) {
        shippingBins.add(new ShippingBin(item, count));
    }

    public void increaseNumberOfShippingBins(int amount) {
        numberOfShippingBins += amount;
    }
}
