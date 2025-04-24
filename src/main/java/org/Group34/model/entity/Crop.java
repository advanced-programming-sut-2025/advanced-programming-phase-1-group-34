package main.java.org.Group34.model.entity;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Crop {
    private String name;
    private String source;
    public ArrayList<Integer> stage;
    private Time totalHarvestTime;
    private boolean isOneTime;
    private Time regrowthTime;
    private Price baseSellPrice;
    private boolean isEdible;
    private Energy baseEnergy;
    private Health baseHealth;
    private Season Season;
    private boolean canBecomeGiant;

    private int regrowthLevel = 1;

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

    public Time getTotalHarvestTime() {
        return totalHarvestTime;
    }
    public void setTotalHarvestTime(Time totalHarvestTime) {
        this.totalHarvestTime = totalHarvestTime;
    }

    public boolean isOneTime() {
        return isOneTime;
    }
    public void setOneTime(boolean oneTime) {
        isOneTime = oneTime;
    }

    public Time getRegrowthTime() {
        return regrowthTime;
    }
    public void setRegrowthTime(Time regrowthTime) {
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

    public Season getSeason() {
        return Season;
    }
    public void setSeason(Season season) {
        Season = season;
    }

    public boolean isCanBecomeGiant() {
        return canBecomeGiant;
    }
    public void setCanBecomeGiant(boolean canBecomeGiant) {
        this.canBecomeGiant = canBecomeGiant;
    }

    public void growing () {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        ArrayList<Integer> delays = new ArrayList<>(stage);

        Runnable growing = new Runnable() {
            private int index = 1;

            @Override
            public void run() {
                if (index < delays.size()) {
                    regrowthLevel++;

                    scheduler.schedule(this, delays.get(index), TimeUnit.SECONDS);

                    index++;
                }
            }
        };

        scheduler.schedule(growing, delays.get(0), TimeUnit.SECONDS);
    }
    public void increaseRegrowthLevel() {
        regrowthLevel++;
    }
    public void resetRegrowthLevel() {
        regrowthLevel = 1;
    }
    public void Harvest() {
        if (isOneTime) {
            // delete from the floor
        } else {
            resetRegrowthLevel();
        }
    }
    public void calculatePrice() {

    }
    public void eat() {
        if (isEdible) {
            // increase energy
            // delete it
        }
    }
}
