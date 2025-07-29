package org.Group34.model.enums.animals;


import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.items.Item;

import java.util.List;

public enum AnimalType implements Item {
    CHICKEN(BarnType.COOP_BASIC, 1, "Chicken", 800, "Well cared-for chickens lay eggs every day. Lives in the coop."),
    DUCK(BarnType.COOP_BIG, 2, "Duck", 1200, "Happy lay duck eggs every other day. Lives in the coop."),
    RABBIT(BarnType.COOP_DELUXE, 4, "Rabbit", 8000, "These are wooly rabbits! They shed precious wool every few days. Lives in the coop."),
    DINOSAUR(BarnType.COOP_BIG, 7, "Dinosaur", 14000, "The Dinosaur is a farm animal that lives in a Big Coop"),
    COW(BarnType.BARN_BASIC, 1, "Cow", 1500, "Can be milked daily. A milk pail is required to harvest the milk. Lives in the barn."),
    GOAT(BarnType.BARN_BIG, 2, "Goat", 4000, "Happy provide goat milk every other day. A milk pail is required to harvest the milk. Lives in the barn."),
    SHEEP(BarnType.BARN_DELUXE, 3, "Sheep", 8000, "Can be shorn for wool. A pair of shears is required to harvest the wool. Lives in the barn."),
    PIG(BarnType.BARN_DELUXE, 1, "Pig", 16000, "These pigs are trained to find truffles! Lives in the barn.");

    private final BarnType requiredBuilding;
    private final int requiredDays;
    private List<Product> possibleProducts;
    private final String name;
    private final int price;
    private final String description;

    static {
        CHICKEN.possibleProducts = List.of(Product.EGG, Product.LARGE_EGG);
        DUCK.possibleProducts = List.of(Product.DUCK_EGG, Product.DUCK_FEATHER);
        RABBIT.possibleProducts = List.of(Product.RABBIT_WOOL, Product.RABBIT_FOOT);
        DINOSAUR.possibleProducts = List.of(Product.DINOSAUR_EGG);
        COW.possibleProducts = List.of(Product.MILK, Product.LARGE_MILK);
        GOAT.possibleProducts = List.of(Product.GOAT_MILK, Product.LARGE_GOAT_MILK);
        SHEEP.possibleProducts = List.of(Product.SHEEP_WOOL);
        PIG.possibleProducts = List.of(Product.TRUFFLE);
    }


    AnimalType(BarnType requiredBuilding, int requiredDays, String name, int price, String description) {
        this.requiredBuilding = requiredBuilding;
        this.requiredDays = requiredDays;
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

    @Override
    public Texture getTexture() {
        return null;
    }
}