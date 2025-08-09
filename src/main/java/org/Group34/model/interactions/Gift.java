package org.Group34.model.interactions;

import org.Group34.model.entities.Player;
import org.Group34.model.items.Item;

public class Gift {
    private Item item;
    private int amount;
    private boolean received;
    private boolean isNew;
    private int rate;
    private int number = 0;
    private Player player;

    public Gift(Item item, int amount, boolean received, boolean isNew, Player player) {
        this.item = item;
        this.amount = amount;
        this.received = received;
        this.isNew = isNew;
        this.rate = -1;
        this.player = player;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isReceived() {
        return received;
    }

    public boolean isNew() {
        return isNew;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Player getPlayer() {
        return player;
    }
}
