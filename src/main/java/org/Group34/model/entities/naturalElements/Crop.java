package org.Group34.model.entities.naturalElements;

import org.Group34.model.entities.Entity;
import org.Group34.model.enums.Season;
import org.Group34.model.items.Fertilizer;
import org.Group34.model.items.foods.FarmingProduct;
import org.Group34.model.items.foods.Fruit;
import org.Group34.model.items.PlantingSource;

import java.util.ArrayList;
import java.util.Random;

public class Crop implements Entity, PlantAble {
    private String name;
    private PlantingSource source;
    public int[] stages;
    private int totalHarvestTime;
    private boolean isOneTime;
    private int regrowthTime;
    private FarmingProduct farmingProduct;
    private ArrayList<Season> seasons;
    private boolean canBecomeGiant;

    private boolean isGiant = false;
    private int age = 0;
    private int growthLevel = 0;
    private boolean harvested = false;
    private int maxLevel;
    private boolean isAttackedByCrow = false;
    private boolean isGivenFertilizer = false;
    private Fertilizer fertilizer = null;

    private boolean needWater = true;

    public Crop(String name, PlantingSource source, int[] stage, int totalHarvestTime, boolean isOneTime,
                int regrowthTime, FarmingProduct farmingProduct, String[] seasons, boolean canBecomeGiant) {
        this.name = name;
        this.source = source;
        this.stages = stage;
        this.totalHarvestTime = totalHarvestTime;
        this.isOneTime = isOneTime;
        this.regrowthTime = regrowthTime;
        this.farmingProduct = farmingProduct;
        this.canBecomeGiant = canBecomeGiant;

        this.maxLevel = stage.length;

        for (String season : seasons) {
            if (season.equals("Spring")) {
                this.seasons.add(Season.SPRING);
            } else if (season.equals("Summer")) {
                this.seasons.add(Season.SUMMER);
            } else if (season.equals("Autumn")) {
                this.seasons.add(Season.AUTUMN);
            } else if (season.equals("Winter")) {
                this.seasons.add(Season.WINTER);
            }
        }
    }

    // ----- getters & setters -----
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

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }
    public void setTotalHarvestTime(int totalHarvestTime) {
        this.totalHarvestTime = totalHarvestTime;
    }

    public boolean isOneTime() {
        return isOneTime;
    }
    public void setOneTime(boolean oneTime) {
        isOneTime = oneTime;
    }

    public int getRegrowthTime() {
        return regrowthTime;
    }
    public void setRegrowthTime(int regrowthTime) {
        this.regrowthTime = regrowthTime;
    }

    public FarmingProduct getFarmingProduct(){return farmingProduct;}

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public boolean isCanBecomeGiant() {
        return canBecomeGiant;
    }
    public void setCanBecomeGiant(boolean canBecomeGiant) {
        this.canBecomeGiant = canBecomeGiant;
    }

    public boolean getNeedWater() {
        return needWater;
    }
    public void setNeedWater(boolean needWater) {
        this.needWater = needWater;
    }

    public boolean isGiant() {
        return isGiant;
    }
    public void setGiant(boolean isGiant) {
        this.isGiant = isGiant;
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
    // -----------------------------

    public String getStructuralInformation() {
        StringBuilder result = new StringBuilder();

        result
                .append("Name: " + name + "\n")
                .append("Source: " + source.getName() + "\n")
                .append("Stages: ");

        for (int i : stages) {
            result.append(1 + "-");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("\n");

        result
                .append("Total Harvest Time: " + totalHarvestTime + "\n")
                .append("One Time: " + isOneTime + "\n")
                .append("Regrowth Time: ");

        if (isOneTime == false) {
            result.append(regrowthTime);
        }
        result.append("\n");

        result
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

        result
                .append("Can Become Giant: " + canBecomeGiant);


        return result.toString();
    }

    public void startANewDay() {
        Random rand = new Random();

        age++;
        checkAgeAndGrow();
        isAttackedByCrow = false;

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
            if (age == regrowthTime) {
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

                    if (age == levelUpTime) {
                        growthLevel++;
                    }
                }
            }
        }
    }

    public void crowInvasion() {
        isAttackedByCrow = true;
    }

    public String getInformation() {
        StringBuilder result = new StringBuilder();

        result
                .append("Name: " + name + "\n")
                .append("Time Remaining Until Bears Fruit: " );

        if (harvested) {
            if (age >= regrowthTime) {
                result.append(0 + " days\n");
            } else {
                result.append(regrowthTime - age).append(" days\n");
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
                .append("Product Quality: " + "\n")
                .append("Has Been Given Fertilizer: " + isGivenFertilizer + "\n");

        return result.toString();
    }

    public void useFertilizer(Fertilizer fertilizer) {
        isGivenFertilizer = true;
        this.fertilizer = fertilizer;
    }
}
