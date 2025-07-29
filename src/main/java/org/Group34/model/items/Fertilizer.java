package org.Group34.model.items;

import com.badlogic.gdx.graphics.Texture;

public enum Fertilizer implements Item {
    BASIC_RETAINING_SOIL("Basic Retaining Soil", "This soil has a chance of staying watered overnight. Mix into tilled soil.", 100),
    QUALITY_RETAINING_SOIL("Quality Retaining Soil", "This soil has a good chance of staying watered overnight. Mix into tilled soil.", 150),
    DELUXE_RETAINING_SOIL("Deluxe Retaining Soil", "This soil has a 100% chance of staying watered overnight. Mix into tilled soil.", 150),
    SPEED_GROW("Speed Grow", "Makes the plants grow 1 day earlier.", 100);

    private final String name;
    private final String description;
    private final int price;

    Fertilizer(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public Texture getTexture() {
        return null;
    }
}
