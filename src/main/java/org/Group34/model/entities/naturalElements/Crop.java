package org.Group34.model.entities.naturalElements;

import org.Group34.model.entities.Entity;
import org.Group34.model.enums.Season;
import org.Group34.model.items.Fertilizer;
import org.Group34.model.items.Fruit;
import org.Group34.model.items.PlantingSource;

import java.util.ArrayList;

public class Crop implements Entity, PlantAble {
    private String name;
    private PlantingSource source;
    public int[] stage;
    private int totalHarvestTime;
    private boolean isOneTime;
    private int regrowthTime;
    private Fruit fruit;
    private ArrayList<Season> seasons;
    private boolean canBecomeGiant;

    private int regrowthLevel = 1;
    private boolean needWater;

    public Crop(String name, PlantingSource source, int[] stage, int totalHarvestTime, boolean isOneTime,
                int regrowthTime, Fruit fruit, String[] seasons, boolean canBecomeGiant) {
        this.name = name;
        this.source = source;
        this.stage = stage;
        this.totalHarvestTime = totalHarvestTime;
        this.isOneTime = isOneTime;
        this.regrowthTime = regrowthTime;
        this.fruit = fruit;
        this.canBecomeGiant = canBecomeGiant;
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

    public Fruit getFruit(){return fruit;}

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

    public String getInformation() {
        return "";
    }

    public void useFertilizer(Fertilizer fertilizer) {

    }
}
