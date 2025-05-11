package org.Group34.model.items.foods;

import org.Group34.model.items.Item;

public record ProcessedFood(ProcessedFoodType foodType, Item source) implements Food {

    @Override
    public String getName() {
        return source.getName() + " " + foodType.getName();
    }
}
