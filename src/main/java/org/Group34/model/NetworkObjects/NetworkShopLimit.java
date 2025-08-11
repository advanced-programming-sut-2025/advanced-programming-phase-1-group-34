package org.Group34.model.NetworkObjects;

import java.io.Serializable;

public class NetworkShopLimit implements Serializable {
    private String item;
    private int amount;

    public NetworkShopLimit(String item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
