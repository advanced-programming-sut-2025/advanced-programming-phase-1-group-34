package org.Group34.model.entities.animals;

import org.Group34.model.enums.AnimalType;
import java.util.*;

public class Animal {
    private final String name;
    private final AnimalType type;
    private int friendship = 0;
    private boolean isFed = false;
    private int daysSinceLastProduce = 0;
    private boolean isOutside = false;
    private final List<Product> possibleProducts = new ArrayList<>();

    public Animal(String name, AnimalType type) {
        this.name = name;
        this.type = type;
        initializeProducts();
    }

    private void initializeProducts() {
        switch (type) {
            case CHICKEN -> {
                addProduct("Egg", 50, 0);
                addProduct("Large Egg", 95, 200);
            }
            case DUCK -> {
                addProduct("Duck Egg", 95, 0);
                addProduct("Duck Feather", 250, 300);
            }
            case COW -> {
                addProduct("Milk", 125, 0);
                addProduct("Large Milk", 190, 300);
            }
            case GOAT -> {
                addProduct("Goat Milk", 225, 0);
                addProduct("Large Goat Milk", 345, 400);
            }
            case SHEEP -> {
                addProduct("Wool", 340, 0);
            }
            case RABBIT -> {
                addProduct("Wool", 340, 0);
                addProduct("Rabbit's Foot", 565, 600);
            }
            case PIG -> {
                addProduct("Truffle", 625, 500);
            }
        }
    }

    private void addProduct(String name, int price, int reqFriendship) {
        possibleProducts.add(new Product(name, price, reqFriendship));
    }

    public boolean isFed() {
        return isFed;
    }

    public void feed() {
        isFed = true;
    }

    public int getFriendship() {
        return friendship;
    }

    public void increaseFriendship(int amount) {
        friendship = Math.min(1000, friendship + amount);
    }

    public void decreaseFriendship(int amount) {
        friendship = Math.max(0, friendship - amount);
    }

    public String getName() {
        return name;
    }

    public AnimalType getType() {
        return type;
    }

    public Product collectProduct() {
        if (!isFed) return null;
        isFed = false;
        daysSinceLastProduce = 0;
        // Products are sorted by required friendship descending

        return possibleProducts.stream()
                .filter(p -> p.reqFriendship() <= friendship)
                .max(Comparator.comparingInt(Product::reqFriendship))
                .orElse(null);
    }
}
