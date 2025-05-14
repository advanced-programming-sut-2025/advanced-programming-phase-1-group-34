package org.Group34.model.entities.npcs.quests;

public class RewardItem {
    private String name;
    private int quantity;

    // Constructor, Getters & Setters
    public RewardItem() {}
    public RewardItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
