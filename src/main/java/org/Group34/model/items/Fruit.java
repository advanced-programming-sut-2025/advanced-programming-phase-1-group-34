package org.Group34.model.items;

/**
 * An enum representing different types of fruits and natural drops with precise properties.
 */
public enum Fruit implements Item {
    APRICOT("Apricot", 59, true, 38, 17),
    CHERRY("Cherry", 80, true, 38, 17),
    BANANA("Banana", 150, true, 75, 33),
    MANGO("Mango", 130, true, 100, 45),
    ORANGE("Orange", 100, true, 38, 17),
    PEACH("Peach", 140, true, 38, 17),
    APPLE("Apple", 100, true, 38, 17),
    POMEGRANATE("Pomegranate", 140, true, 38, 17),
    OAK_RESIN("Oak Resin", 150, false, 0, 0),
    MAPLE_SYRUP("Maple Syrup", 200, false, 0, 0),
    PINE_TAR("Pine Tar", 100, false, 0, 0),
    SAP("Sap", 2, true, -2, 0),
    COMMON_MUSHROOM("Common Mushroom", 40, true, 38, 17),
    MYSTIC_SYRUP("Mystic Syrup", 1000, true, 500, 225),
    // Crop yields (unchanged)
    BLUE_JAZZ("Blue Jazz", 50, true, 45, 20),
    CARROT("Carrot", 35, true, 75, 33),
    CAULIFLOWER("Cauliflower", 175, true, 75, 33),
    COFFEE_BEAN("Coffee Bean", 15, false, 0, 0),
    GARLIC("Garlic", 60, true, 20, 9),
    GREEN_BEAN("Green Bean", 40, true, 25, 11),
    KALE("Kale", 110, true, 50, 22),
    PARSNIP("Parsnip", 35, true, 25, 11),
    POTATO("Potato", 80, true, 25, 11),
    RHUBARB("Rhubarb", 220, false, 0, 0),
    STRAWBERRY("Strawberry", 120, true, 50, 22),
    TULIP("Tulip", 30, true, 45, 20),
    UNMILLED_RICE("Unmilled Rice", 30, true, 3, 1),
    BLUEBERRY("Blueberry", 50, true, 25, 11),
    CORN("Corn", 50, true, 25, 11),
    HOPS("Hops", 25, true, 45, 20),
    HOT_PEPPER("Hot Pepper", 40, true, 13, 5),
    MELON("Melon", 250, true, 113, 50),
    POPPY("Poppy", 140, true, 45, 20),
    RADISH("Radish", 90, true, 45, 20),
    RED_CABBAGE("Red Cabbage", 260, true, 75, 33),
    STARFRUIT("Starfruit", 750, true, 125, 56),
    SUMMER_SPANGLE("Summer Spangle", 90, true, 45, 20),
    SUMMER_SQUASH("Summer Squash", 45, true, 63, 28),
    SUNFLOWER("Sunflower", 80, true, 45, 20),
    TOMATO("Tomato", 60, true, 20, 9),
    WHEAT("Wheat", 25, false, 0, 0),
    AMARANTH("Amaranth", 150, true, 50, 22),
    ARTICHOKE("Artichoke", 160, true, 30, 13),
    BEET("Beet", 100, true, 30, 13),
    BOK_CHOY("Bok Choy", 80, true, 25, 11),
    BROCCOLI("Broccoli", 70, true, 63, 28),
    CRANBERRIES("Cranberries", 75, true, 38, 17),
    EGGPLANT("Eggplant", 60, true, 20, 9),
    FAIRY_ROSE("Fairy Rose", 290, true, 45, 20),
    GRAPE("Grape", 80, true, 38, 17),
    PUMPKIN("Pumpkin", 320, false, 0, 0),
    YAM("Yam", 160, true, 45, 20),
    SWEET_GEM_BERRY("Sweet Gem Berry", 3000, false, 0, 0),
    POWDERMELON("Powdermelon", 60, true, 63, 28),
    ANCIENT_FRUIT("Ancient Fruit", 550, false, 0, 0);

    private String name;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy;
    private int health;

    Fruit(String name, int price, boolean isEdible, int energy, int health) {
        this.name = name;
        this.baseSellPrice = price;
        this.isEdible = isEdible;
        this.energy = energy;
        this.health = health;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }
    public void setBaseSellPrice(int price) {
        this.baseSellPrice = price;
    }

    public boolean isEdible() {
        return isEdible;
    }
    public void setEdible(boolean isEdible) {
        this.isEdible = isEdible;
    }

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
}
