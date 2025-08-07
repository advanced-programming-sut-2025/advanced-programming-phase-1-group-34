package org.Group34.model.items.foods;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.gameAssetManagers.CropAssetManager;
import org.Group34.model.gameAssetManagers.TreeAssetManager;

/**
 * An enum representing all remaining farm products that are neither Fruit nor Vegetable.
 */
public enum OtherFarmingProduct implements FarmingProduct {
    // Flowers
    BLUE_JAZZ("Blue Jazz", 50, true, 45, 20, CropAssetManager.getBlueJazz()),
    FAIRY_ROSE("Fairy Rose", 290, true, 45, 20, CropAssetManager.getFairyRose()),
    POPPY("Poppy", 140, true, 45, 20, CropAssetManager.getPoppy()),
    SUMMER_SPANGLE("Summer Spangle", 90, true, 45, 20, CropAssetManager.getSummerSpangle()),
    SUNFLOWER("Sunflower", 80, true, 45, 20, CropAssetManager.getSunflower()),
    TULIP("Tulip", 30, true, 45, 20, CropAssetManager.getTulip()),

    // Tree products
    MAPLE_SYRUP("Maple Syrup", 200, false, 0, 0, TreeAssetManager.getMapleSyrup()),
    MYSTIC_SYRUP("Mystic Syrup", 1000, true, 500, 225, TreeAssetManager.getMysticSyrup()),
    OAK_RESIN("Oak Resin", 150, false, 0, 0, TreeAssetManager.getOakResin()),
    PINE_TAR("Pine Tar", 100, false, 0, 0, TreeAssetManager.getPineTar()),
    SAP("Sap", 2, true, -2, 0, TreeAssetManager.getSap());

    private String name;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy;
    private int health;
    private Texture texture;

    OtherFarmingProduct(String name, int price, boolean isEdible, int energy, int health, Texture texture) {
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
