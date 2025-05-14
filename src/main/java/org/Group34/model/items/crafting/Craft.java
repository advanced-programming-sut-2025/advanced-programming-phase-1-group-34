package org.Group34.model.items.crafting;

import org.Group34.model.items.Item;
import org.Group34.model.items.Recipe;

import java.util.Map;

public interface Craft extends Item {
    Map<Item, Integer> getIngredients();

    Recipe getRecipe();

    String getDescription();

    int getPrice();

    String getSource();
}
