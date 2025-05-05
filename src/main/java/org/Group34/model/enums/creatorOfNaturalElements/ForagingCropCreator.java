package org.Group34.model.enums.creatorOfNaturalElements;

import org.Group34.model.entities.naturalElements.ForagingCrop;

public enum ForagingCropCreator {
    COMMON_MUSHROOM("Common Mushroom", new String[]{"Spring", "Summer", "Autumn", "Winter"}, 40, 38),

    DAFFODIL("Daffodil", new String[]{"Spring"}, 30, 0),
    DANDELION("Dandelion", new String[]{"Spring"}, 40, 25),
    LEEK("Leek", new String[]{"Spring"}, 60, 40),
    MOREL("Morel", new String[]{"Spring"}, 150, 20),
    SALMONBERRY("Salmonberry", new String[]{"Spring"}, 5, 25),
    SPRING_ONION("Spring Onion", new String[]{"Spring"}, 8, 13),
    WILD_HORSERADISH("Wild Horseradish", new String[]{"Spring"}, 50, 13),

    FIDDLEHEAD_FERN("Fiddlehead Fern", new String[]{"Summer"}, 90, 25),
    GRAPE("Grape", new String[]{"Summer"}, 80, 38),
    RED_MUSHROOM("Red Mushroom", new String[]{"Summer"}, 75, -50),
    SPICE_BERRY("Spice Berry", new String[]{"Summer"}, 80, 25),
    SWEET_PEA("Sweet Pea", new String[]{"Summer"}, 50, 0),

    BLACKBERRY("Blackberry", new String[]{"Autumn"}, 25, 25),
    CHANTERELLE("Chanterelle", new String[]{"Autumn"}, 160, 75),
    HAZELNUT("Hazelnut", new String[]{"Autumn"}, 40, 38),
    PURPLE_MUSHROOM("Purple Mushroom", new String[]{"Autumn"}, 90, 30),
    WILD_PLUM("Wild Plum", new String[]{"Autumn"}, 80, 25),

    CROCUS("Crocus", new String[]{"Winter"}, 60, 0),
    CRYSTAL_FRUIT("Crystal Fruit", new String[]{"Winter"}, 150, 63),
    HOLLY("Holly", new String[]{"Winter"}, 80, -37),
    SNOW_YAM("Snow Yam", new String[]{"Winter"}, 100, 30),
    WINTER_ROOT("Winter Root", new String[]{"Winter"}, 70, 25);

    private final String name;
    private final String[] seasons;
    private final int price;
    private final int energy;

    ForagingCropCreator(String name, String[] seasons, int price, int energy) {
        this.name = name;
        this.seasons = seasons;
        this.price = price;
        this.energy = energy;
    }

    public ForagingCrop createInstance() {
        return new ForagingCrop(name, seasons, price, energy);
    }
}