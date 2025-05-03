package org.Group34.model.enums.creatorOfNaturalElements;

import org.Group34.model.entities.Tree;

public enum TreeCreator {
    APRICOT_TREE("Apricot Tree", "Apricot Sapling", new int[]{7, 7, 7, 7}, 28, "Apricot", 1, 59, true, 38, 17, new String[]{"Spring"}),
    CHERRY_TREE("Cherry Tree", "Cherry Sapling", new int[]{7, 7, 7, 7}, 28, "Cherry", 1, 80, true, 38, 17, new String[]{"Spring"}),
    BANANA_TREE("Banana Tree", "Banana Sapling", new int[]{7, 7, 7, 7}, 28, "Banana", 1, 150, true, 75, 33, new String[]{"Summer"}),
    MANGO_TREE("Mango Tree", "Mango Sapling", new int[]{7, 7, 7, 7}, 28, "Mango", 1, 130, true, 100, 45, new String[]{"Summer"}),
    ORANGE_TREE("Orange Tree", "Orange Sapling", new int[]{7, 7, 7, 7}, 28, "Orange", 1, 100, true, 38, 17, new String[]{"Summer"}),
    PEACH_TREE("Peach Tree", "Peach Sapling", new int[]{7, 7, 7, 7}, 28, "Peach", 1, 140, true, 38, 17, new String[]{"Summer"}),
    APPLE_TREE("Apple Tree", "Apple Sapling", new int[]{7, 7, 7, 7}, 28, "Apple", 1, 100, true, 38, 17, new String[]{"Autumn"}),
    POMEGRANATE_TREE("Pomegranate Tree", "Pomegranate Sapling", new int[]{7, 7, 7, 7}, 28, "Pomegranate", 1, 140, true, 38, 17, new String[]{"Autumn"}),
    OAK_TREE("Oak Tree", "Acorns", new int[]{7, 7, 7, 7}, 28, "Oak Resin", 7, 150, false, 0, 0, new String[]{"Spring", "Summer", "Autumn", "Winter"}),
    MAPLE_TREE("Maple Tree", "Maple Seeds", new int[]{7, 7, 7, 7}, 28, "Maple Syrup", 9, 200, false, 0, 0, new String[]{"Spring", "Summer", "Autumn", "Winter"}),
    PINE_TREE("Pine Tree", "Pine Cones", new int[]{7, 7, 7, 7}, 28, "Pine Tar", 5, 100, false, 0, 0, new String[]{"Spring", "Summer", "Autumn", "Winter"}),
    MAHOGANY_TREE("Mahogany Tree", "Mahogany Seeds", new int[]{7, 7, 7, 7}, 28, "Sap", 1, 2, true, -2, 0, new String[]{"Spring", "Summer", "Autumn", "Winter"}),
    MUSHROOM_TREE("Mushroom Tree", "Mushroom Tree Seeds", new int[]{7, 7, 7, 7}, 28, "Common Mushroom", 1, 40, true, 38, 17, new String[]{"Spring", "Summer", "Autumn", "Winter"}),
    MYSTIC_TREE("Mystic Tree", "Mystic Tree Seeds", new int[]{7, 7, 7, 7}, 28, "Mystic Syrup", 7, 1000, true, 500, 225, new String[]{"Spring", "Summer", "Autumn", "Winter"});

    private final String name;
    private final String source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final String fruitName;
    private final int fruitHarvestCycle;
    private final int fruitBaseSellPrice;
    private final boolean isFruitEdible;
    private final int fruitEnergy;
    private final int fruitHealth;
    private final String[] seasons;

    TreeCreator(String name, String source, int[] stages, int totalHarvestTime, String fruitName, int fruitHarvestCycle,
                int fruitBaseSellPrice, boolean isFruitEdible, int fruitEnergy, int fruitHealth, String[] seasons) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruitName = fruitName;
        this.fruitHarvestCycle = fruitHarvestCycle;
        this.fruitBaseSellPrice = fruitBaseSellPrice;
        this.isFruitEdible = isFruitEdible;
        this.fruitEnergy = fruitEnergy;
        this.fruitHealth = fruitHealth;
        this.seasons = seasons;
    }

    public Tree createInstance() {
        return new Tree(name, source, stages, totalHarvestTime, fruitName, fruitHarvestCycle, fruitBaseSellPrice, isFruitEdible, fruitEnergy, fruitHealth, seasons);
    }
}
