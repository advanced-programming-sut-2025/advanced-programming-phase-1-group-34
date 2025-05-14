package org.Group34.model.items.foods;

public enum CropProduct implements FarmingProduct {
    // Original crops
    COFFEE_BEAN("Coffee Bean", 15, false, 0, 0),
    HOPS("Hops", 25, true, 45, 20),
    UNMILLED_RICE("Unmilled Rice", 30, true, 3, 1),
    WHEAT("Wheat", 25, false, 0, 0),

    // Foraging crops
    COMMON_MUSHROOM("Common Mushroom", 40, true, 38, 19),
    DAFFODIL("Daffodil", 30, false, 0, 0),
    DANDELION("Dandelion", 40, true, 25, 12),
    LEEK("Leek", 60, true, 40, 20),
    MOREL("Morel", 150, true, 20, 10),
    SALMONBERRY("Salmonberry", 5, true, 25, 12),
    SPRING_ONION("Spring Onion", 8, true, 13, 6),
    WILD_HORSERADISH("Wild Horseradish", 50, true, 13, 6),

    FIDDLEHEAD_FERN("Fiddlehead Fern", 90, true, 25, 12),
    GRAPE("Grape", 80, true, 38, 19),
    RED_MUSHROOM("Red Mushroom", 75, false, -50, -25),
    SPICE_BERRY("Spice Berry", 80, true, 25, 12),
    SWEET_PEA("Sweet Pea", 50, false, 0, 0),

    BLACKBERRY("Blackberry", 25, true, 25, 12),
    CHANTERELLE("Chanterelle", 160, true, 75, 37),
    HAZELNUT("Hazelnut", 40, true, 38, 19),
    PURPLE_MUSHROOM("Purple Mushroom", 90, true, 30, 15),
    WILD_PLUM("Wild Plum", 80, true, 25, 12),

    CROCUS("Crocus", 60, false, 0, 0),
    CRYSTAL_FRUIT("Crystal Fruit", 150, true, 63, 31),
    HOLLY("Holly", 80, false, -37, -18),
    SNOW_YAM("Snow Yam", 100, true, 30, 15),
    WINTER_ROOT("Winter Root", 70, true, 25, 12);

    private String name;
    private int baseSellPrice;
    private boolean isEdible;
    private int energy;
    private int health;

    CropProduct(String name, int price, boolean isEdible, int energy, int health) {
        this.name = name;
        this.baseSellPrice = price;
        this.isEdible = isEdible;
        this.energy = energy;
        this.health = health;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getBaseSellPrice() {
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
}
