package org.Group34.model.entities;

import org.Group34.model.features.Energy;
import org.Group34.model.features.Health;
import org.Group34.model.features.Price;

public class Fruit implements Entity {
    private String name;
    private Price baseSellPrice;
    private boolean isEdible;
    private Energy energy;
    private Health health;

    public Fruit(String name, int price, boolean isEdible, int energy, int health) {
        this.name = name;
        this.baseSellPrice = new Price(price);
        this.isEdible = isEdible;
        this.energy = new Energy(energy);
        this.health = new Health(health);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Price getBaseSellPrice() {
        return baseSellPrice;
    }
    public void setBaseSellPrice(Price price) {
        this.baseSellPrice = price;
    }

    public boolean isEdible() {
        return isEdible;
    }
    public void setEdible(boolean isEdible) {
        this.isEdible = isEdible;
    }

    public Energy getEnergy() {
        return energy;
    }
    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public Health getHealth() {
        return health;
    }
    public void setHealth(Health health) {
        this.health = health;
    }
}
