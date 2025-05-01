package org.Group34.model.enums;

public enum FarmType {
    STANDARD_FARM(new int[]{70, 5}, new int[]{40, 5}, new int[]{50, 80}, new int[]{0, 0},
            0.3f, 0.3f, 0.3f),
    FOREST_FARM(new int[]{70, 5}, new int[]{40, 5}, new int[]{50, 80}, new int[]{0, 0},
            0.5f, 0.1f, 0.3f);

    private final int[] houseLocation;
    private final int[] greenHouseLocation;
    private final int[] lakeLocation;
    private final int[] quarryLocation;
    private final float treeSpawnChance;
    private final float stoneSpawnChance;
    private final float foragingSpawnChance;


    FarmType(int[] houseLocation, int[] greenHouseLocation, int[] lakeLocation, int[] quarryLocation, float treeSpawnChance, float stoneSpawnChance, float foragingSpawnChance) {
        this.houseLocation = houseLocation;
        this.greenHouseLocation = greenHouseLocation;
        this.lakeLocation = lakeLocation;
        this.quarryLocation = quarryLocation;
        this.treeSpawnChance = treeSpawnChance;
        this.stoneSpawnChance = stoneSpawnChance;
        this.foragingSpawnChance = foragingSpawnChance;
    }



    public static FarmType getFarm(Integer farmNumber) {
        switch (farmNumber){
            case 1: return FarmType.STANDARD_FARM;
            case 2: return FarmType.FOREST_FARM;
        }

        return null;
    }

    public int[] getHouseLocation() {
        return houseLocation;
    }

    public int[] getGreenHouseLocation() {
        return greenHouseLocation;
    }

    public int[] getLakeLocation() {
        return lakeLocation;
    }

    public int[] getQuarryLocation() {
        return quarryLocation;
    }

    public float getTreeSpawnChance() {
        return treeSpawnChance;
    }

    public float getStoneSpawnChance() {
        return stoneSpawnChance;
    }

    public float getForagingSpawnChance() {
        return foragingSpawnChance;
    }
}
