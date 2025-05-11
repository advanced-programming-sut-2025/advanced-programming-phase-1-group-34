package org.Group34.model.items.foods;

public enum Vegetable implements FarmingProduct {
    CARROT("Carrot", 35, true, 75, 33),
    CAULIFLOWER("Cauliflower", 175, true, 75, 33),
    GREEN_BEAN("Green Bean", 40, true, 25, 11),
    KALE("Kale", 110, true, 50, 22),
    PARSNIP("Parsnip", 35, true, 25, 11),
    GARLIC("Garlic", 60, true, 20, 9),
    TOMATO("Tomato", 60, true, 20, 9),
    PUMPKIN("Pumpkin", 320, false, 0, 0),
    RADISH("Radish", 90, true, 45, 20),

    POTATO("Potato", 80, true, 25, 11),
    RED_CABBAGE("Red Cabbage", 260, true, 75, 33),
    CORN("Corn", 50, true, 25, 11),
    YAM("Yam", 160, true, 45, 20),
    AMARANTH("Amaranth", 150, true, 50, 22),
    ARTICHOKE("Artichoke", 160, true, 30, 13),
    BEET("Beet", 100, true, 30, 13),
    BOK_CHOY("Bok Choy", 80, true, 25, 11),
    BROCCOLI("Broccoli", 70, true, 63, 28),
    HOT_PEPPER("Hot Pepper", 40, true, 13, 5),
    EGGPLANT("Eggplant", 60, true, 20, 9),
    SUMMER_SQUASH("Summer Squash", 45, true, 63, 28);

    private String name;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy, health;

    Vegetable(String name, int price, boolean edible, int energy, int health) {
        this.name = name;
        this.baseSellPrice = price;
        this.isEdible = edible;
        this.energy = energy;
        this.health = health;
    }

    public String getName() { return name; }
    public int getBaseSellPrice() { return baseSellPrice; }
    public boolean isEdible() { return isEdible; }
    public int getEnergy() { return energy; }
    public int getHealth() { return health; }
}
