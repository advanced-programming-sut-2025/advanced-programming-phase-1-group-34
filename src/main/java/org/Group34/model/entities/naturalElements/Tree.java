package org.Group34.model.entities.naturalElements;

import org.Group34.model.entities.Entity;
import org.Group34.model.enums.Color;
import org.Group34.model.enums.Season;
import org.Group34.model.items.Fruit;
import org.Group34.model.items.PlantingSource;

import java.util.ArrayList;

public class Tree implements Entity, PlantAble {
    @Override
    public String toString() {
        return Color.GREEN + "T" + Color.RESET;
    }

    private String name;
    private PlantingSource source;
    private int[] stages;
    private int totalHarvestTime;
    private Fruit fruit;
    private int fruitHarvestCycle;
    private ArrayList<Season> seasons;

    private boolean needWater;


    public Tree(String name, PlantingSource source, int[] stages, int totalHarvestTime, Fruit fruit, String[] seasons) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.needWater = true;

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

        this.fruit = fruit;
    }

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

    public Fruit getFruit() {
        return fruit;
    }
    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
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
}
