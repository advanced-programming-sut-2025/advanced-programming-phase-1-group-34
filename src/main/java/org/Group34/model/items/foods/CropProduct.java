package org.Group34.model.items.foods;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.gameAssetManagers.CropAssetManager;
import org.Group34.model.gameAssetManagers.ForagingPlantAssetManager;

public enum CropProduct implements FarmingProduct {
    // Original crops
    COFFEE_BEAN("Coffee Bean", 15, false, 0, 0, CropAssetManager.getCoffeeBean()),
    HOPS("Hops", 25, true, 45, 20, CropAssetManager.getHops()),
    UNMILLED_RICE("Unmilled Rice", 30, true, 3, 1, CropAssetManager.getUnmilledRice()),
    WHEAT("Wheat", 25, false, 0, 0, CropAssetManager.getWheat()),

    // Foraging crops
    COMMON_MUSHROOM("Common Mushroom", 40, true, 38, 19, ForagingPlantAssetManager.getCommonMushroom()),
    DAFFODIL("Daffodil", 30, false, 0, 0, ForagingPlantAssetManager.getDaffodil()),
    DANDELION("Dandelion", 40, true, 25, 12, ForagingPlantAssetManager.getDandelion()),
    LEEK("Leek", 60, true, 40, 20, ForagingPlantAssetManager.getLeek()),
    MOREL("Morel", 150, true, 20, 10, ForagingPlantAssetManager.getMorel()),
    SALMONBERRY("Salmonberry", 5, true, 25, 12, ForagingPlantAssetManager.getSalmonberry()),
    SPRING_ONION("Spring Onion", 8, true, 13, 6, ForagingPlantAssetManager.getSpringOnion()),
    WILD_HORSERADISH("Wild Horseradish", 50, true, 13, 6, ForagingPlantAssetManager.getWildHorseradish()),

    FIDDLEHEAD_FERN("Fiddlehead Fern", 90, true, 25, 12, ForagingPlantAssetManager.getFiddleheadFern()),
    GRAPE("Grape", 80, true, 38, 19, ForagingPlantAssetManager.getGrape()),
    RED_MUSHROOM("Red Mushroom", 75, false, -50, -25, ForagingPlantAssetManager.getRedMushroom()),
    SPICE_BERRY("Spice Berry", 80, true, 25, 12, ForagingPlantAssetManager.getSpiceBerry()),
    SWEET_PEA("Sweet Pea", 50, false, 0, 0, ForagingPlantAssetManager.getSweetPea()),

    BLACKBERRY("Blackberry", 25, true, 25, 12, ForagingPlantAssetManager.getBlackberry()),
    CHANTERELLE("Chanterelle", 160, true, 75, 37, ForagingPlantAssetManager.getChanterelle()),
    HAZELNUT("Hazelnut", 40, true, 38, 19, ForagingPlantAssetManager.getHazelnut()),
    PURPLE_MUSHROOM("Purple Mushroom", 90, true, 30, 15, ForagingPlantAssetManager.getPurpleMushroom()),
    WILD_PLUM("Wild Plum", 80, true, 25, 12, ForagingPlantAssetManager.getWildPlum()),

    CROCUS("Crocus", 60, false, 0, 0, ForagingPlantAssetManager.getCrocus()),
    CRYSTAL_FRUIT("Crystal Fruit", 150, true, 63, 31, ForagingPlantAssetManager.getCrystalFruit()),
    HOLLY("Holly", 80, false, -37, -18, ForagingPlantAssetManager.getHolly()),
    SNOW_YAM("Snow Yam", 100, true, 30, 15, ForagingPlantAssetManager.getSnowYam()),
    WINTER_ROOT("Winter Root", 70, true, 25, 12, ForagingPlantAssetManager.getWinterRoot());

    private String name;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy;
    private int health;
    private Texture texture;

    CropProduct(String name, int price, boolean isEdible, int energy, int health, Texture texture) {
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
