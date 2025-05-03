package org.Group34.model.enums.creatorOfNaturalElements;

import org.Group34.model.entities.Crop;

public enum CropCreator {
    BLUE_JAZZ("Blue Jazz", "Jazz Seeds", new int[]{1, 2, 2, 2}, 7, true, 0, 50, true, 45, 20, new String[]{"Spring"}, false),
    CARROT("Carrot", "Carrot Seeds", new int[]{1, 1, 1}, 3, true, 0, 35, true, 75, 33, new String[]{"Spring"}, false),
    CAULIFLOWER("Cauliflower", "Cauliflower Seeds", new int[]{1, 2, 4, 4, 1}, 12, true, 0, 175, true, 75, 33, new String[]{"Spring"}, true),
    COFFEE_BEAN("Coffee Bean", "Coffee Bean", new int[]{1, 2, 2, 3, 2}, 10, false, 2, 15, false, 0, 0, new String[]{"Spring", "Summer"}, false),
    GARLIC("Garlic", "Garlic Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 60, true, 20, 9, new String[]{"Spring"}, false),
    GREEN_BEAN("Green Bean", "Bean Starter", new int[]{1, 1, 1, 3, 4}, 10, false, 3, 40, true, 25, 11, new String[]{"Spring"}, false),
    KALE("Kale", "Kale Seeds", new int[]{1, 2, 2, 1}, 6, true, 0, 110, true, 50, 22, new String[]{"Spring"}, false),
    PARSNIP("Parsnip", "Parsnip Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 35, true, 25, 11, new String[]{"Spring"}, false),
    POTATO("Potato", "Potato Seeds", new int[]{1, 1, 1, 2, 1}, 6, true, 0, 80, true, 25, 11, new String[]{"Spring"}, false),
    RHUBARB("Rhubarb", "Rhubarb Seeds", new int[]{2, 2, 2, 3, 4}, 13, true, 0, 220, false, 0, 0, new String[]{"Spring"}, false),
    STRAWBERRY("Strawberry", "Strawberry Seeds", new int[]{1, 1, 2, 2, 2}, 8, false, 4, 120, true, 50, 22, new String[]{"Spring"}, false),
    TULIP("Tulip", "Tulip Bulb", new int[]{1, 1, 2, 2}, 6, true, 0, 30, true, 45, 20, new String[]{"Spring"}, false),
    UNMILLED_RICE("Unmilled Rice", "Rice Shoot", new int[]{1, 2, 2, 3}, 8, true, 0, 30, true, 3, 1, new String[]{"Spring"}, false),
    BLUEBERRY("Blueberry", "Blueberry Seeds", new int[]{1, 3, 3, 4, 2}, 13, false, 4, 50, true, 25, 11, new String[]{"Summer"}, false),
    CORN("Corn", "Corn Seeds", new int[]{2, 3, 3, 3, 3}, 14, false, 4, 50, true, 25, 11, new String[]{"Summer", "Autumn"}, false),
    HOPS("Hops", "Hops Starter", new int[]{1, 1, 2, 3, 4}, 11, false, 1, 25, true, 45, 20, new String[]{"Summer"}, false),
    HOT_PEPPER("Hot Pepper", "Pepper Seeds", new int[]{1, 1, 1, 1, 1}, 5, false, 3, 40, true, 13, 5, new String[]{"Summer"}, false),
    MELON("Melon", "Melon Seeds", new int[]{1, 2, 3, 3, 3}, 12, true, 0, 250, true, 113, 50, new String[]{"Summer"}, true),
    POPPY("Poppy", "Poppy Seeds", new int[]{1, 2, 2, 2}, 7, true, 0, 140, true, 45, 20, new String[]{"Summer"}, false),
    RADISH("Radish", "Radish Seeds", new int[]{2, 1, 2, 1}, 6, true, 0, 90, true, 45, 20, new String[]{"Summer"}, false),
    RED_CABBAGE("Red Cabbage", "Red Cabbage Seeds", new int[]{2, 1, 2, 2, 2}, 9, true, 0, 260, true, 75, 33, new String[]{"Summer"}, false),
    STARFRUIT("Starfruit", "Starfruit Seeds", new int[]{2, 3, 2, 3, 3}, 13, true, 0, 750, true, 125, 56, new String[]{"Summer"}, false),
    SUMMER_SPANGLE("Summer Spangle", "Spangle Seeds", new int[]{1, 2, 3, 1}, 8, true, 0, 90, true, 45, 20, new String[]{"Summer"}, false),
    SUMMER_SQUASH("Summer Squash", "Summer Squash Seeds", new int[]{1, 1, 1, 2, 1}, 6, false, 3, 45, true, 63, 28, new String[]{"Summer"}, false),
    SUNFLOWER("Sunflower", "Sunflower Seeds", new int[]{1, 2, 3, 2}, 8, true, 0, 80, true, 45, 20, new String[]{"Summer", "Autumn"}, false),
    TOMATO("Tomato", "Tomato Seeds", new int[]{2, 2, 2, 2, 3}, 11, false, 4, 60, true, 20, 9, new String[]{"Summer"}, false),
    WHEAT("Wheat", "Wheat Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 25, false, 0, 0, new String[]{"Summer", "Autumn"}, false),
    AMARANTH("Amaranth", "Amaranth Seeds", new int[]{1, 2, 2, 2}, 7, true, 0, 150, true, 50, 22, new String[]{"Autumn"}, false),
    ARTICHOKE("Artichoke", "Artichoke Seeds", new int[]{2, 2, 1, 2, 1}, 8, true, 0, 160, true, 30, 13, new String[]{"Autumn"}, false),
    BEET("Beet", "Beet Seeds", new int[]{1, 1, 2, 2}, 6, true, 0, 100, true, 30, 13, new String[]{"Autumn"}, false),
    BOK_CHOY("Bok Choy", "Bok Choy Seeds", new int[]{1, 1, 1, 1}, 4, true, 0, 80, true, 25, 11, new String[]{"Autumn"}, false),
    BROCCOLI("Broccoli", "Broccoli Seeds", new int[]{2, 2, 2, 2}, 8, false, 4, 70, true, 63, 28, new String[]{"Autumn"}, false),
    CRANBERRIES("Cranberries", "Cranberry Seeds", new int[]{1, 2, 1, 1, 2}, 7, false, 5, 75, true, 38, 17, new String[]{"Autumn"}, false),
    EGGPLANT("Eggplant", "Eggplant Seeds", new int[]{1, 1, 1, 1}, 5, false, 5, 60, true, 20, 9, new String[]{"Autumn"}, false),
    FAIRY_ROSE("Fairy Rose", "Fairy Seeds", new int[]{1, 4, 4, 3}, 12, true, 0, 290, true, 45, 20, new String[]{"Autumn"}, false),
    GRAPE("Grape", "Grape Starter", new int[]{1, 1, 2, 3, 3}, 10, false, 3, 80, true, 38, 17, new String[]{"Autumn"}, false),
    PUMPKIN("Pumpkin", "Pumpkin Seeds", new int[]{1, 2, 3, 4, 3}, 13, true, 0, 320, false, 0, 0, new String[]{"Autumn"}, true),
    YAM("Yam", "Yam Seeds", new int[]{1, 3, 3, 3}, 10, true, 0, 160, true, 45, 20, new String[]{"Autumn"}, false),
    SWEET_GEM_BERRY("Sweet Gem Berry", "Rare Seed", new int[]{2, 4, 6, 6, 6}, 24, true, 0, 3000, false, 0, 0, new String[]{"Autumn"}, false),
    POWDERMELON("Powdermelon", "Powdermelon Seeds", new int[]{1, 2, 1, 2, 1}, 7, true, 0, 60, true, 63, 28, new String[]{"Winter"}, true),
    ANCIENT_FRUIT("Ancient Fruit", "Ancient Seeds", new int[]{2, 7, 7, 7, 5}, 28, false, 7, 550, false, 0, 0, new String[]{"Spring", "Summer", "Autumn"}, false);

    private final String name;
    private final String source;
    private final int[] stage;
    private final int totalHarvestTime;
    private final boolean isOneTime;
    private final int regrowthTime;
    private final int price;
    private final boolean isEdible;
    private final int baseEnergy;
    private final int baseHealth;
    private final String[] seasons;
    private final boolean canBecomeGiant;

    CropCreator(String name, String source, int[] stage, int totalHarvestTime, boolean isOneTime,
                int regrowthTime, int price, boolean isEdible, int baseEnergy, int baseHealth, String[] seasons, boolean canBecomeGiant) {
        this.name = name;
        this.source = source;
        this.stage = stage;
        this.totalHarvestTime = totalHarvestTime;
        this.isOneTime = isOneTime;
        this.regrowthTime = regrowthTime;
        this.price = price;
        this.isEdible = isEdible;
        this.baseEnergy = baseEnergy;
        this.baseHealth = baseHealth;
        this.seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
    }

    public Crop createInstance() {
        return new Crop(name, source, stage, totalHarvestTime, isOneTime, regrowthTime, price, isEdible, baseEnergy, baseHealth, seasons, canBecomeGiant);
    }
}