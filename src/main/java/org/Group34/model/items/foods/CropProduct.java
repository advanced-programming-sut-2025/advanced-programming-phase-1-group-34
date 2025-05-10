package org.Group34.model.items.foods;

public enum CropProduct implements FarmingProduct{
    // Crops
    COFFEE_BEAN("Coffee Bean", 15, false, 0, 0),
    HOPS("Hops", 25, true, 45, 20),
    UNMILLED_RICE("Unmilled Rice", 30, true, 3, 1),
    WHEAT("Wheat", 25, false, 0, 0);

    private String name;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy;
    private int health;

    CropProduct(String name, int price, boolean isEdible, int energy, int health) {
        this.name = name;
        this.baseSellPrice = price;
        this.isEdible = isEdible;
        this.energy = energy;
        this.health = health;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public void setBaseSellPrice(int price) {
        this.baseSellPrice = price;
    }

    @Override
    public boolean isEdible() {
        return isEdible;
    }

    public void setEdible(boolean edible) {
        this.isEdible = edible;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public int getHealth() {
        return health;
    }
}
