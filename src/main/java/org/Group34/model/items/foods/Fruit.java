package org.Group34.model.items.foods;

import java.util.Optional;

public enum Fruit implements FarmingProduct {
    APRICOT("Apricot", 59, true, 38, 17, ProcessedFood.DRIED_APRICOT, ProcessedFood.APRICOT_JELLY, ProcessedFood.APRICOT_WINE),
    CHERRY("Cherry", 80, true, 38, 17, ProcessedFood.DRIED_CHERRY, ProcessedFood.CHERRY_JELLY, ProcessedFood.CHERRY_WINE),
    BANANA("Banana", 150, true, 75, 33, ProcessedFood.DRIED_BANANA, ProcessedFood.BANANA_JELLY, ProcessedFood.BANANA_WINE),
    MANGO("Mango", 130, true, 100, 45, ProcessedFood.DRIED_MANGO, ProcessedFood.MANGO_JELLY, ProcessedFood.MANGO_WINE),
    ORANGE("Orange", 100, true, 38, 17, ProcessedFood.DRIED_ORANGE, ProcessedFood.ORANGE_JELLY, ProcessedFood.ORANGE_WINE),
    PEACH("Peach", 140, true, 38, 17, ProcessedFood.DRIED_PEACH, ProcessedFood.PEACH_JELLY, ProcessedFood.PEACH_WINE),
    APPLE("Apple", 100, true, 38, 17, ProcessedFood.DRIED_APPLE, ProcessedFood.APPLE_JELLY, ProcessedFood.APPLE_WINE),
    POMEGRANATE("Pomegranate", 140, true, 38, 17, ProcessedFood.DRIED_POMEGRANATE, ProcessedFood.POMEGRANATE_JELLY, ProcessedFood.POMEGRANATE_WINE),
    STRAWBERRY("Strawberry", 120, true, 50, 22, ProcessedFood.DRIED_STRAWBERRY, ProcessedFood.STRAWBERRY_JELLY, ProcessedFood.STRAWBERRY_WINE),
    BLUEBERRY("Blueberry", 50, true, 25, 11, ProcessedFood.DRIED_BLUEBERRY, ProcessedFood.BLUEBERRY_JELLY, ProcessedFood.BLUEBERRY_WINE),
    CRANBERRIES("Cranberries", 75, true, 38, 17, ProcessedFood.DRIED_CRANBERRIES, ProcessedFood.CRANBERRIES_JELLY, ProcessedFood.CRANBERRIES_WINE),
    GRAPE("Grape", 80, true, 38, 17, ProcessedFood.RAISINS, ProcessedFood.GRAPE_JELLY, ProcessedFood.GRAPE_WINE),
    MELON("Melon", 250, true, 113, 50, ProcessedFood.DRIED_MELON, ProcessedFood.MELON_JELLY, ProcessedFood.MELON_WINE),
    POWDERMELON("Powdermelon", 60, true, 63, 28, ProcessedFood.DRIED_POWDERMELON, ProcessedFood.POWDERMELON_JELLY, ProcessedFood.POWDERMELON_WINE),
    STARFRUIT("Starfruit", 750, true, 125, 56, ProcessedFood.DRIED_STARFRUIT, ProcessedFood.STARFRUIT_JELLY, ProcessedFood.STARFRUIT_WINE),
    ANCIENT_FRUIT("Ancient Fruit", 550, false, 0, 0, ProcessedFood.DRIED_ANCIENT_FRUIT, ProcessedFood.ANCIENT_FRUIT_JELLY, ProcessedFood.ANCIENT_FRUIT_WINE),
    SWEET_GEM_BERRY("Sweet Gem Berry", 3000, false, 0, 0, ProcessedFood.DRIED_SWEET_GEM_BERRY, ProcessedFood.SWEET_GEM_BERRY_JELLY, ProcessedFood.SWEET_GEM_BERRY_WINE);

    private final String name;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private final int health;
    private final ProcessedFood driedForm;
    private final ProcessedFood jellyForm;
    private final ProcessedFood wineForm;

    Fruit(String name, int price, boolean edible, int energy, int health,
          ProcessedFood driedForm, ProcessedFood jellyForm, ProcessedFood wineForm) {
        this.name = name;
        this.baseSellPrice = price;
        this.isEdible = edible;
        this.energy = energy;
        this.health = health;
        this.driedForm = driedForm;
        this.jellyForm = jellyForm;
        this.wineForm = wineForm;
    }

    public String getName() { return name; }
    public int getBaseSellPrice() { return baseSellPrice; }
    public boolean isEdible() { return isEdible; }
    public int getEnergy() { return energy; }
    public int getHealth() { return health; }

    public ProcessedFood getDriedForm() {
        return this.driedForm;
    }

    public ProcessedFood getJellyForm() {
        return this.jellyForm;
    }

    public ProcessedFood getWineForm() {
        return this.wineForm;
    }
}
