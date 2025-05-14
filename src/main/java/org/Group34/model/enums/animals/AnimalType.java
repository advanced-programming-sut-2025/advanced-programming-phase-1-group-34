package org.Group34.model.enums.animals;


import org.Group34.model.items.Item;

import java.util.List;

public enum AnimalType implements Item {
    CHICKEN(BarnType.COOP_BASIC, 1, List.of(Product.EGG, Product.LARGE_EGG), "Chicken", 800, "Well cared-for chickens lay eggs every day. Lives in the coop."),
    DUCK(BarnType.COOP_BIG, 2, List.of(Product.DUCK_EGG, Product.DUCK_FEATHER), "Duck", 1200, "Happy lay duck eggs every other day. Lives in the coop."),
    RABBIT(BarnType.COOP_DELUXE, 4, List.of(Product.RABBIT_WOOL, Product.RABBIT_FOOT), "Rabbit", 8000, "These are wooly rabbits! They shed precious wool every few days. Lives in the coop."),
    DINOSAUR(BarnType.COOP_BIG, 7, List.of(Product.DINOSAUR_EGG), "Dinosaur", 14000, "The Dinosaur is a farm animal that lives in a Big Coop"),
    COW(BarnType.BARN_BASIC, 1, List.of(Product.MILK, Product.LARGE_MILK), "Cow", 1500, "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn."),
    GOAT(BarnType.BARN_BIG, 2, List.of(Product.GOAT_MILK, Product.LARGE_GOAT_MILK), "Goat", 4000, "Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn."),
    SHEEP(BarnType.BARN_DELUXE, 3, List.of(Product.SHEEP_WOOL), "Sheep", 8000, "Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn."),
    PIG(BarnType.BARN_DELUXE, 1, List.of(Product.TRUFFLE), "Pig", 16000, "These pigs are trained to find truffles! Lives in the barn.");

    private final BarnType requiredBuilding;
    private final int requiredDays;
    private final List<Product> possibleProducts;
    private final String name;
    private final int price;
    private final String description;

    AnimalType(BarnType requiredBuilding, int requiredDays, List<Product> possibleProducts, String name, int price, String description) {
        this.requiredBuilding = requiredBuilding;
        this.requiredDays = requiredDays;
        this.possibleProducts = possibleProducts;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public BarnType getRequiredBuilding() {
        return requiredBuilding;
    }

    public int getRequiredDays() {
        return requiredDays;
    }

    public List<Product> getPossibleProducts() {
        return possibleProducts;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}