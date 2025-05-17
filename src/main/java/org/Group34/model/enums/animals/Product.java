package org.Group34.model.enums.animals;

import org.Group34.model.items.Item;

public enum Product implements Item {
    // Chicken
    EGG(AnimalType.CHICKEN, 50, 0),
    LARGE_EGG(AnimalType.CHICKEN, 95, 100),

    // Duck
    DUCK_EGG(AnimalType.DUCK, 95, 0),
    DUCK_FEATHER(AnimalType.DUCK, 250, 100),

    // Rabbit
    RABBIT_WOOL(AnimalType.RABBIT, 340, 0),
    RABBIT_FOOT(AnimalType.RABBIT, 565, 100),

    // Dinosaur
    DINOSAUR_EGG(AnimalType.DINOSAUR, 350, 0),

    // Cow
    MILK(AnimalType.COW, 125, 0),
    LARGE_MILK(AnimalType.COW, 190, 100),

    // Goat
    GOAT_MILK(AnimalType.GOAT, 225, 0),
    LARGE_GOAT_MILK(AnimalType.GOAT, 345, 100),

    // Sheep
    SHEEP_WOOL(AnimalType.SHEEP, 340, 0),

    // Pig
    TRUFFLE(AnimalType.PIG, 625, 0);

    private final AnimalType type;
    private final int basePrice;
    private final int requiredFriendship;

    Product(AnimalType type, int basePrice, int requiredFriendship) {
        this.type = type;
        this.basePrice = basePrice;
        this.requiredFriendship = requiredFriendship;
    }

    public AnimalType getType() {
        return type;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getRequiredFriendship() {
        return requiredFriendship;
    }

    @Override
    public String getName() {
        return null;
    }
}