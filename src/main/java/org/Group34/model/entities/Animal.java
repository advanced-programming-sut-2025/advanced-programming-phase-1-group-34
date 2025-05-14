package org.Group34.model.entities;

import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.Product;

import java.util.List;

public class Animal {
    private final String name;
    private final AnimalType type;
    private int friendship = 0;
    private boolean isFed = false;
    private int daysSinceLastProduce = 0;
    private boolean isOutside = false;
    private final List<Product> possibleProducts;

    public Animal(String name, AnimalType type) {
        this.name = name;
        this.type = type;
        this.possibleProducts = type.getPossibleProducts();
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

    public AnimalType getAnimalType() {
        return type;
    }

    public void addDaysSinceLastProduce() {
        daysSinceLastProduce++;
    }

    public Product collectProduct() {
        if (!isFed) return null;
        if (daysSinceLastProduce < type.getRequiredDays()) return null;
        if (type == AnimalType.PIG && !isOutside) return null;

        isFed = false;
        daysSinceLastProduce = 0;

        Product selectedProduct = null;
        for (Product product : possibleProducts) {
            if (friendship >= product.getRequiredFriendship()) {
                if (selectedProduct == null || product.getRequiredFriendship() > selectedProduct.getRequiredFriendship()) {
                    selectedProduct = product;
                }
            }
        }

        if (selectedProduct == null) return null;

        if (selectedProduct.getRequiredFriendship() > 0) {
            double randomFactor = 0.5 + Math.random();
            double probability = (friendship + (150 * randomFactor)) / 1500;
            if (probability < 0.6) {
                Product lowerProduct = null;
                for (Product product : possibleProducts) {
                    if (product.getRequiredFriendship() < selectedProduct.getRequiredFriendship()) {
                        if (lowerProduct == null || product.getRequiredFriendship() < lowerProduct.getRequiredFriendship()) {
                            lowerProduct = product;
                        }
                    }
                }
                if (lowerProduct != null) {
                    selectedProduct = lowerProduct;
                }
            }
        }

        return selectedProduct;
    }

    public void setOutside(boolean outside) {
        isOutside = outside;
    }
}