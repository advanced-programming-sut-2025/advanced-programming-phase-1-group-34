package org.Group34.model.entities.buildings.shops;

import org.Group34.model.entities.buildings.Building;
import org.Group34.model.entities.buildings.shops.products.ShippingBin;

import java.util.ArrayList;

public class SalePlace extends Building {
    private static int shippingBinCount = 0;

    public static void increaseShippingBinCount(int amount) {
        shippingBinCount += amount;
    }

}
