package org.Group34.model.entities.naturalElements;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.Entity;
import org.Group34.model.enums.Color;
import org.Group34.model.enums.Season;
import org.Group34.model.items.foods.CropProduct;

import java.util.ArrayList;

public class ForagingCrop implements Entity, Foraging {
    private String name;
    private ArrayList<Season> seasons;
    private CropProduct product;
    private int baseSellPrice;
    private int energy;
    private Texture texture;

    public ForagingCrop(String name, String[] seasons, CropProduct product, int price, int energy, Texture texture) {
        this.name = name;
        this.baseSellPrice = price;
        this.product = product;
        this.energy = energy;
        this.seasons = new ArrayList<>();
        this.texture = texture;
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

    public int getBaseSellPrice() {
        return baseSellPrice;
    }
    public void setBaseSellPrice(int price) {
        this.baseSellPrice = price;
    }

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getInformation() {
        return "";
    }

    public Texture getTexture() {
        return texture;
    }

    public CropProduct getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return Color.GREEN + "C" + Color.RESET;
    }
}
