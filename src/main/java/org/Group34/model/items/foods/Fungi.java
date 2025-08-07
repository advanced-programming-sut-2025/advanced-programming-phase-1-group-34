package org.Group34.model.items.foods;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.gameAssetManagers.CropAssetManager;
import org.Group34.model.gameAssetManagers.TreeAssetManager;

public enum Fungi implements FarmingProduct{
    // Fungi
    COMMON_MUSHROOM("Common Mushroom", 40, true, 38, 17, TreeAssetManager.getCommonMushroom()),
    RHUBARB("Rhubarb", 220, false, 0, 0, CropAssetManager.getRhubarb());

    private String name;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy;
    private int health;
    private Texture texture;

    Fungi(String name, int price, boolean isEdible, int energy, int health, Texture texture) {
        this.name = name;
        this.baseSellPrice = price;
        this.isEdible = isEdible;
        this.energy = energy;
        this.health = health;
        this.texture = texture;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPrice() {
        return baseSellPrice;
    }

    public void setBaseSellPrice(int price) {
        this.baseSellPrice = price;
    }

    @Override
    public boolean isEdible() {
        return isEdible;
    }

    public void setEdible(boolean edible) {
        this.isEdible = edible;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }
}
