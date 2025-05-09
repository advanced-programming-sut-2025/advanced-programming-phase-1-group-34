package org.Group34.model.entities.naturalElements;

import org.Group34.model.entities.Entity;
import org.Group34.model.enums.Season;

import java.util.ArrayList;

public class ForagingTree implements Entity, Foraging {
    private String name;
    private ArrayList<Season> seasons;

    private boolean needWater;

    public ForagingTree(String name, String[] seasons) {
        this.name = name;
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

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
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
}
