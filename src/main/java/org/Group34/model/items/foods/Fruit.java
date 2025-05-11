package org.Group34.model.items.foods;

public enum Fruit implements FarmingProduct {
    APRICOT("Apricot", 59, true, 38, 17),
    CHERRY("Cherry", 80, true, 38, 17),
    BANANA("Banana", 150, true, 75, 33),
    MANGO("Mango", 130, true, 100, 45),
    ORANGE("Orange", 100, true, 38, 17),
    PEACH("Peach", 140, true, 38, 17),
    APPLE("Apple", 100, true, 38, 17),
    POMEGRANATE("Pomegranate", 140, true, 38, 17),
    STRAWBERRY("Strawberry", 120, true, 50, 22),
    BLUEBERRY("Blueberry", 50, true, 25, 11),
    CRANBERRIES("Cranberries", 75, true, 38, 17),
    GRAPE("Grape", 80, true, 38, 17),
    MELON("Melon", 250, true, 113, 50),
    POWDERMELON("Powdermelon", 60, true, 63, 28),
    STARFRUIT("Starfruit", 750, true, 125, 56),
    ANCIENT_FRUIT("Ancient Fruit", 550, false, 0, 0),
    SWEET_GEM_BERRY("Sweet Gem Berry", 3000, false, 0, 0);

    private String name;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy, health;

    Fruit(String name, int price, boolean edible, int energy, int health) {
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
