package org.Group34.model.entities.naturalElements;

import org.Group34.model.entities.Entity;
import org.Group34.model.enums.Color;
import org.Group34.model.enums.Season;
import org.Group34.model.items.Fertilizer;
import org.Group34.model.items.foods.FarmingProduct;
import org.Group34.model.items.PlantingSource;

import java.util.ArrayList;
import java.util.Random;

public class Tree implements Entity, PlantAble {
    @Override
    public String toString() {
        return Color.GREEN + "T" + Color.RESET;
    }

    private String name;
    private PlantingSource source;
    private int[] stages;
    private int totalHarvestTime;
    private FarmingProduct farmingProduct;
    private int fruitHarvestCycle;
    private ArrayList<Season> seasons;

    private int age = 0;
    private int growthLevel = 0;
    private boolean harvested = false;
    private int maxLevel;

    private boolean needWater;
    private int numberOfDaysNeedWater = 0;
    private boolean isBurned = false;
    private boolean isAttackedByCrow = false;
    private boolean isGivenFertilizer = false;
    private Fertilizer fertilizer = null;



    public Tree(String name, PlantingSource source, int[] stages, int totalHarvestTime, FarmingProduct farmingProduct, String[] seasons) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.needWater = true;
        this.maxLevel = stages.length;
        this.seasons = new ArrayList<>();

        for (String season : seasons) {
            if (season.equals("Spring")) {
                this.seasons.add(Season.SPRING);
            } else if (season.equals("Summer")) {
                this.seasons.add(Season.SUMMER);
            } else if (season.equals("Autumn")) {
                this.seasons.add(Season.FALL);
            } else if (season.equals("Winter")) {
                this.seasons.add(Season.WINTER);
            }
        }

        this.farmingProduct = farmingProduct;
    }

    // ----- getter & setter -----
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public PlantingSource getSource() {
        return source;
    }
    public void setSource(PlantingSource source) {
        this.source = source;
    }

    public int[] getStages() {
        return stages;
    }
    public void setStages(int[] stages) {
        this.stages = stages;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }
    public void setTotalHarvestTime(int totalHarvestTime) {
        this.totalHarvestTime = totalHarvestTime;
    }

    public FarmingProduct getFruit() {
        return farmingProduct;
    }
    public void setFruit(FarmingProduct farmingProduct) {
        this.farmingProduct = farmingProduct;
    }

    public int getFruitHarvestCycle() {
        return fruitHarvestCycle;
    }
    public void setFruitHarvestCycle(int fruitHarvestCycle) {
        this.fruitHarvestCycle = fruitHarvestCycle;
    }

    public ArrayList<Season> getSeason() {
        return seasons;
    }
    public void setSeason(ArrayList<Season> season) {
        this.seasons = season;
    }

    public boolean getNeedWater() {
        return needWater;
    }
    public void setNeedWater(boolean needWater) {
        this.needWater = needWater;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getGrowthLevel() {
        return growthLevel;
    }
    public void setGrowthLevel(int growthLevel) {
        this.growthLevel = growthLevel;
    }

    public boolean getHarvested() {
        return harvested;
    }
    public void setHarvested(boolean harvested) {
        this.harvested = harvested;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public boolean isBurned() {
        return isBurned;
    }
    public void setBurned(boolean isBurned) {
        this.isBurned = isBurned;
    }

    public boolean isAttackedByCrow() {
        return isAttackedByCrow;
    }
    public void setAttackedByCrow(boolean isAttackedByCrow) {
        this.isAttackedByCrow = isAttackedByCrow;
    }

    public boolean isGivenFertilizer() {
        return isGivenFertilizer;
    }
    public void setGivenFertilizer(boolean isGivenFertilizer) {
        this.isGivenFertilizer = isGivenFertilizer;
    }

    public Fertilizer getFertilizer() {
        return fertilizer;
    }
    public void setFertilizer(Fertilizer fertilizer) {
        this.fertilizer = fertilizer;
    }

    public int getNumberOfDaysNeedWater() {
        return numberOfDaysNeedWater;
    }
    public void setNumberOfDaysNeedWater(int number) {
        this.numberOfDaysNeedWater = number;
    }
    // ---------------------------

    public String getStructuralInformation() {
        StringBuilder result = new StringBuilder();

        result
                .append("Name: " + farmingProduct.getName() + "\n")
                .append("Source: " + source.getName() + "\n")
                .append("Stages: ");

        for (int i : stages) {
            result.append(1 + "-");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("\n");

        result
                .append("Total Harvest Time: " + totalHarvestTime + "\n")
                .append("Fruit Harvest Cycle: " + fruitHarvestCycle + "\n")
                .append("Base Sell Price: " + farmingProduct.getBaseSellPrice() + "\n")
                .append("Is Edible: " + farmingProduct.isEdible() + "\n")
                .append("Base Energy: " + farmingProduct.getEnergy() + "\n")
                .append("Base Health: " + farmingProduct.getHealth() + "\n")
                .append("Season: ");

        for (Season season : seasons) {
            result.append(season.getName() + ", ");
        }
        result.deleteCharAt(result.length() - 1);
        result.deleteCharAt(result.length() - 1);
        result.append("\n");

        return result.toString();
    }

    public String getInformation() {
        StringBuilder result = new StringBuilder();

        result
                .append("Name: " + name + "\n")
                .append("Time Remaining Until Bears Fruit: " );

        if (harvested) {
            if (age >= fruitHarvestCycle) {
                result.append(0 + " days\n");
            } else {
                result.append(fruitHarvestCycle - age).append(" days\n");
            }
        } else {
            if (age >= totalHarvestTime) {
                result.append(0 + " days\n");
            } else {
                result.append(totalHarvestTime - age).append(" days\n");
            }
        }

        result
                .append("Growth Level: " + growthLevel + "\n")
                .append("Need Water:" + needWater + "\n")
                .append("Product Quality: Good" + "\n")
                .append("Has Been Given Fertilizer: " + isGivenFertilizer + "\n");

        return result.toString();
    }

    public void startANewDay() {
        Random rand = new Random();

        age++;
        isAttackedByCrow = false;
        numberOfDaysNeedWater++;

        if (!needWater) {
            numberOfDaysNeedWater = 0;
            checkAgeAndGrow();
        }

        if (fertilizer == null) {
            needWater = true;
        } else if (fertilizer == Fertilizer.BASIC_RETAINING_SOIL && rand.nextInt(2) == 0) {
            needWater = true;
        } else if (fertilizer == Fertilizer.QUALITY_RETAINING_SOIL && rand.nextInt(4) == 0) {
            needWater = true;
        }
    }
    private void checkAgeAndGrow() {
        if (harvested) {
            if (age == fruitHarvestCycle && !isAttackedByCrow) {
                growthLevel = maxLevel;
            }
        }

        else {
            for (int i = 0; i < maxLevel; i++) {
                if (growthLevel == i) {
                    int levelUpTime = 0;
                    for (int j = 0; j <= growthLevel; j++) {
                        levelUpTime += stages[j];
                    }

                    if (age == levelUpTime && !isAttackedByCrow()) {
                        growthLevel++;
                    }
                }
            }
        }
    }

    public void lightningStrike() {
        isBurned = true;
    }
    public void crowInvasion() {
        isAttackedByCrow = true;
    }

    public void useFertilizer(Fertilizer fertilizer) {
        isGivenFertilizer = true;
        this.fertilizer = fertilizer;
    }

    public void harvest() {
        if (!harvested) {
            harvested = true;
        }
        age = 0;
        growthLevel = maxLevel - 1;
    }
}
