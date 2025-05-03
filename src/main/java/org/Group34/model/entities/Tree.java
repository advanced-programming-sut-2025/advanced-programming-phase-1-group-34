package org.Group34.model.entities;

import org.Group34.model.enums.Color;
import org.Group34.model.enums.Season;

import java.util.ArrayList;

public class Tree implements Entity {
    public static final String RESET  = "\u001B[0m";
    public static final String GREEN  = "\u001B[32m";

    @Override
    public String toString() {
        return Color.GREEN + "T" + Color.RESET;
    }

    private String name;
    private String source;
    private int[] stages;
    private int totalHarvestTime;
    private Fruit fruit;
    private int fruitHarvestCycle;
    private ArrayList<Season> seasons;


    public Tree(String name, String source, int[] stages, int totalHarvestTime, String fruitName, int fruitHarvestCycle,
                int fruitBaseSellPrice, boolean isFruitEdible, int fruitEnergy, int fruitHealth, String[] seasons) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.fruitHarvestCycle = fruitHarvestCycle;

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

        this.fruit = new Fruit(fruitName, fruitBaseSellPrice, isFruitEdible, fruitEnergy, fruitHealth);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
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
}
