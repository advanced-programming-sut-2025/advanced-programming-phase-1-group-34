package org.Group34.model.enums.creatorOfNaturalElements;

import org.Group34.model.entities.naturalElements.ForagingCrop;
import org.Group34.model.items.foods.CropProduct;

public enum ForagingCropCreator {
    COMMON_MUSHROOM("Common Mushroom", new String[]{"Spring", "Summer", "Autumn", "Winter"}, CropProduct.COMMON_MUSHROOM),

    DAFFODIL("Daffodil", new String[]{"Spring"}, CropProduct.DAFFODIL),
    DANDELION("Dandelion", new String[]{"Spring"}, CropProduct.DANDELION),
    LEEK("Leek", new String[]{"Spring"}, CropProduct.LEEK),
    MOREL("Morel", new String[]{"Spring"}, CropProduct.MOREL),
    SALMONBERRY("Salmonberry", new String[]{"Spring"}, CropProduct.SALMONBERRY),
    SPRING_ONION("Spring Onion", new String[]{"Spring"}, CropProduct.SPRING_ONION),
    WILD_HORSERADISH("Wild Horseradish", new String[]{"Spring"}, CropProduct.WILD_HORSERADISH),

    FIDDLEHEAD_FERN("Fiddlehead Fern", new String[]{"Summer"}, CropProduct.FIDDLEHEAD_FERN),
    GRAPE("Grape", new String[]{"Summer"}, CropProduct.GRAPE),
    RED_MUSHROOM("Red Mushroom", new String[]{"Summer"}, CropProduct.RED_MUSHROOM),
    SPICE_BERRY("Spice Berry", new String[]{"Summer"}, CropProduct.SPICE_BERRY),
    SWEET_PEA("Sweet Pea", new String[]{"Summer"}, CropProduct.SWEET_PEA),

    BLACKBERRY("Blackberry", new String[]{"Autumn"}, CropProduct.BLACKBERRY),
    CHANTERELLE("Chanterelle", new String[]{"Autumn"}, CropProduct.CHANTERELLE),
    HAZELNUT("Hazelnut", new String[]{"Autumn"}, CropProduct.HAZELNUT),
    PURPLE_MUSHROOM("Purple Mushroom", new String[]{"Autumn"}, CropProduct.PURPLE_MUSHROOM),
    WILD_PLUM("Wild Plum", new String[]{"Autumn"}, CropProduct.WILD_PLUM),

    CROCUS("Crocus", new String[]{"Winter"}, CropProduct.CROCUS),
    CRYSTAL_FRUIT("Crystal Fruit", new String[]{"Winter"}, CropProduct.CRYSTAL_FRUIT),
    HOLLY("Holly", new String[]{"Winter"}, CropProduct.HOLLY),
    SNOW_YAM("Snow Yam", new String[]{"Winter"}, CropProduct.SNOW_YAM),
    WINTER_ROOT("Winter Root", new String[]{"Winter"}, CropProduct.WINTER_ROOT);

    private final String name;
    private final String[] seasons;
    private final CropProduct product;

    ForagingCropCreator(String name, String[] seasons, CropProduct product) {
        this.name = name;
        this.seasons = seasons;
        this.product = product;
    }

    public ForagingCrop createInstance() {
        return new ForagingCrop(name, seasons, product);
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
}
