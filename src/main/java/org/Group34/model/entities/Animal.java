package org.Group34.model.entities;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.Product;

import java.util.List;

public class Animal implements Entity {
    private final String name;
    private final AnimalType type;
    private int friendship = 0;
    private boolean hasPet = false;
    private boolean isFed = false;
    private int daysSinceLastProduce = 0;
    private boolean isOutside = false;
    private final List<Product> possibleProducts;

    // position
    private int x;
    private int y;


    public Animal(String name, AnimalType type) {
        this.name = name;
        this.type = type;
        this.possibleProducts = type.getPossibleProducts();
    }

    public void setFriendship(int friendship) {
        this.friendship = friendship;
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

    // position getters and setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

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
        double randomFactor = 0.5 + Math.random();
        if (selectedProduct.getRequiredFriendship() > 0) {
            double probability = (randomFactor + (150 * randomFactor)) / 1500;

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
        double Q = (randomFactor * 0.5) + (0.5 * (randomFactor / 1000));
        int quality = determineQuality(Q);
        int price = selectedProduct.getPrice() * quality;
        selectedProduct.setPrice(price);

        return selectedProduct;
    }

    public boolean setOutside() {
        if (this.getAnimalType() == AnimalType.PIG ||
                this.getAnimalType() == AnimalType.COW ||
                this.getAnimalType() == AnimalType.GOAT ||
                this.getAnimalType() == AnimalType.SHEEP) {
            this.isOutside = true;
            return true;
        }
        return false;
    }

    public boolean isCollected() {
        return daysSinceLastProduce >= type.getRequiredDays() || daysSinceLastProduce <= 0;
    }

    public boolean isHasPet() {
        return hasPet;
    }

    public void setHasPet(boolean hasPet) {
        this.hasPet = hasPet;
    }

    public boolean isOutside() {
        return isOutside;
    }

    public void setFed(boolean fed) {
        isFed = fed;
    }

    private int determineQuality(double Q) {
        if (Q < 0.5) return 1;
        if (Q < 0.7) return 2;
        if (Q < 0.9) return 3;
        return 4;
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}