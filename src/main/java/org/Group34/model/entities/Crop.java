package org.Group34.model.entities;

import org.Group34.model.enums.Season;
import org.Group34.model.features.*;

import java.util.ArrayList;

public class Crop { //TODO crop can be an entity
    private String name;
    private String source;
    public int[] stage;
    private int totalHarvestTime;
    private boolean isOneTime;
    private int regrowthTime;
    private Price baseSellPrice;
    private boolean isEdible;
    private Energy baseEnergy;
    private Health baseHealth;
    private ArrayList<Season> seasons;
    private boolean canBecomeGiant;

    private int regrowthLevel = 1;

    public Crop(String name, String source, int[] stage, int totalHarvestTime, boolean isOneTime,
                int regrowthTime, int price, boolean isEdible, int baseEnergy, int baseHealth, String[] seasons, boolean canBecomeGiant) {
        this.name = name;
        this.source = source;
        this.stage = stage;
        this.totalHarvestTime = totalHarvestTime;
        this.isOneTime = isOneTime;
        this.regrowthTime = regrowthTime;
        this.baseSellPrice = new Price(price);
        this.isEdible = isEdible;
        this.baseEnergy = new Energy(baseEnergy);
        this.baseHealth = new Health(baseHealth);
        this.canBecomeGiant = canBecomeGiant;
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

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
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

    public Price getBaseSellPrice() {
        return baseSellPrice;
    }
    public void setBaseSellPrice(Price baseSellPrice) {
        this.baseSellPrice = baseSellPrice;
    }

    public boolean isEdible() {
        return isEdible;
    }
    public void setEdible(boolean edible) {
        isEdible = edible;
    }

    public Energy getBaseEnergy() {
        return baseEnergy;
    }
    public void setBaseEnergy(Energy baseEnergy) {
        this.baseEnergy = baseEnergy;
    }

    public Health getBaseHealth() {
        return baseHealth;
    }
    public void setBaseHealth(Health baseHealth) {
        this.baseHealth = baseHealth;
    }

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
}
