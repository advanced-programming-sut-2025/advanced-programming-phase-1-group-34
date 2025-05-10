package org.Group34.model.entities;

import org.Group34.model.enums.AnimalType;
import org.Group34.model.enums.Product;

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

        return Arrays.stream(Product.values())
                .filter(p -> p.getType() == this.type)
                .filter(p -> p.getReqFriendship() <= friendship)
                .max(Comparator.comparingInt(Product::getReqFriendship))
                .orElse(null);
    }

}
