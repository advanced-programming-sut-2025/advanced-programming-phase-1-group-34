package org.Group34.model.entities;

import org.Group34.model.enums.Season;
import org.Group34.model.features.Energy;
import org.Group34.model.features.Price;

import java.util.ArrayList;

public class ForagingCrop {
    private String name;
    private ArrayList<Season> seasons;
    private Price baseSellPrice;
    private Energy energy;

    public ForagingCrop(String name, String[] seasons, int price, int energy) {
        this.name = name;
        this.baseSellPrice = new Price(price);
        this.energy = new Energy(energy);
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

    public Price getBaseSellPrice() {
        return baseSellPrice;
    }
    public void setBaseSellPrice(Price price) {
        this.baseSellPrice = price;
    }

    public Energy getEnergy() {
        return energy;
    }
    public void setEnergy(Energy energy) {
        this.energy = energy;
    }
}
