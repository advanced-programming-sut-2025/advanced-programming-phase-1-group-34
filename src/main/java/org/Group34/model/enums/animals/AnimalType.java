package org.Group34.model.enums.animals;


import java.util.List;

public enum AnimalType {
    CHICKEN(BarnType.COOP_BASIC, 1, List.of(Product.EGG, Product.LARGE_EGG)),
    DUCK(BarnType.COOP_BIG, 2, List.of(Product.DUCK_EGG, Product.DUCK_FEATHER)),
    RABBIT(BarnType.COOP_DELUXE, 4, List.of(Product.RABBIT_WOOL, Product.RABBIT_FOOT)),
    DINOSAUR(BarnType.COOP_BIG, 7, List.of(Product.DINOSAUR_EGG)),
    COW(BarnType.BARN_BASIC, 1, List.of(Product.MILK, Product.LARGE_MILK)),
    GOAT(BarnType.BARN_BIG, 2, List.of(Product.GOAT_MILK, Product.LARGE_GOAT_MILK)),
    SHEEP(BarnType.BARN_DELUXE, 3, List.of(Product.SHEEP_WOOL)),
    PIG(BarnType.BARN_DELUXE, 1, List.of(Product.TRUFFLE));

    private final BarnType requiredBuilding;
    private final int requiredDays;
    private final List<Product> possibleProducts;

    AnimalType(BarnType requiredBuilding, int requiredDays, List<Product> possibleProducts) {
        this.requiredBuilding = requiredBuilding;
        this.requiredDays = requiredDays;
        this.possibleProducts = possibleProducts;
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
}