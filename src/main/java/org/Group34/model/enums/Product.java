package org.Group34.model.enums;

public enum Product {
    // Chicken
    EGG("Egg", 50, 0, AnimalType.CHICKEN),
    LARGE_EGG("Large Egg", 95, 200, AnimalType.CHICKEN),

    // Duck
    DUCK_EGG("Duck Egg", 95, 0, AnimalType.DUCK),
    DUCK_FEATHER("Duck Feather", 250, 300, AnimalType.DUCK),

    // Cow
    MILK("Milk", 125, 0, AnimalType.COW),
    LARGE_MILK("Large Milk", 190, 300, AnimalType.COW),

    // Goat
    GOAT_MILK("Goat Milk", 225, 0, AnimalType.GOAT),
    LARGE_GOAT_MILK("Large Goat Milk", 345, 400, AnimalType.GOAT),

    // Sheep
    WOOL("Wool", 340, 0, AnimalType.SHEEP),

    // Rabbit
    RABBITS_FOOT("Rabbit's Foot", 565, 600, AnimalType.RABBIT),
    RABBIT_WOOL("Wool", 340, 0, AnimalType.RABBIT),

    // Pig
    TRUFFLE("Truffle", 625, 500, AnimalType.PIG);

    private final String name;
    private final int price;
    private final int reqFriendship;
    private final AnimalType type;

    Product(String name, int price, int reqFriendship, AnimalType type) {
        this.name = name;
        this.price = price;
        this.reqFriendship = reqFriendship;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getReqFriendship() {
        return reqFriendship;
    }

    public AnimalType getType() {
        return type;
    }
}

