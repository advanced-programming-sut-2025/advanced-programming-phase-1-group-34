package org.Group34.model.enums.creatorOfNaturalElements;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.naturalElements.ForagingCrop;
import org.Group34.model.gameAssetManagers.ForagingPlantAssetManager;
import org.Group34.model.items.foods.CropProduct;

public enum ForagingCropCreator {
    COMMON_MUSHROOM("Common Mushroom", new String[]{"Spring", "Summer", "Autumn", "Winter"}, CropProduct.COMMON_MUSHROOM, ForagingPlantAssetManager.getCommonMushroom()),

    DAFFODIL("Daffodil", new String[]{"Spring"}, CropProduct.DAFFODIL, ForagingPlantAssetManager.getDaffodil()),
    DANDELION("Dandelion", new String[]{"Spring"}, CropProduct.DANDELION, ForagingPlantAssetManager.getDandelion()),
    LEEK("Leek", new String[]{"Spring"}, CropProduct.LEEK, ForagingPlantAssetManager.getLeek()),
    MOREL("Morel", new String[]{"Spring"}, CropProduct.MOREL, ForagingPlantAssetManager.getMorel()),
    SALMONBERRY("Salmonberry", new String[]{"Spring"}, CropProduct.SALMONBERRY, ForagingPlantAssetManager.getSalmonberry()),
    SPRING_ONION("Spring Onion", new String[]{"Spring"}, CropProduct.SPRING_ONION, ForagingPlantAssetManager.getSpringOnion()),
    WILD_HORSERADISH("Wild Horseradish", new String[]{"Spring"}, CropProduct.WILD_HORSERADISH, ForagingPlantAssetManager.getWildHorseradish()),

    FIDDLEHEAD_FERN("Fiddlehead Fern", new String[]{"Summer"}, CropProduct.FIDDLEHEAD_FERN, ForagingPlantAssetManager.getFiddleheadFern()),
    GRAPE("Grape", new String[]{"Summer"}, CropProduct.GRAPE, ForagingPlantAssetManager.getGrape()),
    RED_MUSHROOM("Red Mushroom", new String[]{"Summer"}, CropProduct.RED_MUSHROOM, ForagingPlantAssetManager.getRedMushroom()),
    SPICE_BERRY("Spice Berry", new String[]{"Summer"}, CropProduct.SPICE_BERRY, ForagingPlantAssetManager.getSpiceBerry()),
    SWEET_PEA("Sweet Pea", new String[]{"Summer"}, CropProduct.SWEET_PEA, ForagingPlantAssetManager.getSweetPea()),

    BLACKBERRY("Blackberry", new String[]{"Autumn"}, CropProduct.BLACKBERRY, ForagingPlantAssetManager.getBlackberry()),
    CHANTERELLE("Chanterelle", new String[]{"Autumn"}, CropProduct.CHANTERELLE, ForagingPlantAssetManager.getChanterelle()),
    HAZELNUT("Hazelnut", new String[]{"Autumn"}, CropProduct.HAZELNUT, ForagingPlantAssetManager.getHazelnut()),
    PURPLE_MUSHROOM("Purple Mushroom", new String[]{"Autumn"}, CropProduct.PURPLE_MUSHROOM, ForagingPlantAssetManager.getPurpleMushroom()),
    WILD_PLUM("Wild Plum", new String[]{"Autumn"}, CropProduct.WILD_PLUM, ForagingPlantAssetManager.getWildPlum()),

    CROCUS("Crocus", new String[]{"Winter"}, CropProduct.CROCUS, ForagingPlantAssetManager.getCrocus()),
    CRYSTAL_FRUIT("Crystal Fruit", new String[]{"Winter"}, CropProduct.CRYSTAL_FRUIT, ForagingPlantAssetManager.getCrystalFruit()),
    HOLLY("Holly", new String[]{"Winter"}, CropProduct.HOLLY, ForagingPlantAssetManager.getHolly()),
    SNOW_YAM("Snow Yam", new String[]{"Winter"}, CropProduct.SNOW_YAM, ForagingPlantAssetManager.getSnowYam()),
    WINTER_ROOT("Winter Root", new String[]{"Winter"}, CropProduct.WINTER_ROOT, ForagingPlantAssetManager.getWinterRoot());

    private final String name;
    private final String[] seasons;
    private final CropProduct product;
    private final Texture texture;

    ForagingCropCreator(String name, String[] seasons, CropProduct product, Texture texture) {
        this.name = name;
        this.seasons = seasons;
        this.product = product;
        this.texture = texture;
    }

    public ForagingCrop createInstance() {
        return new ForagingCrop(name, seasons, product, 100, 5, texture);
    }

    public CropProduct getProduct() {
        return product;
    }

    public String getName() {
        return name;
    }

    public String[] getSeasons() {
        return seasons;
    }

    public Texture getTexture() {
        return texture;
    }
}
