package org.Group34.model.entities.buildings;

import org.Group34.model.enums.Color;
import org.Group34.model.items.Item;
import org.Group34.model.map.Space;

import java.util.ArrayList;
import java.util.HashMap;

public class Quarry extends Building{
    @Override
    public String toString() {
        return Color.GRAY + "Q" + Color.RESET;
    }

    private HashMap<Item, Integer> items = new HashMap<>();

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void addItem(Item item, int amount) {
        if (items.containsKey(item)) {
            items.replace(item, items.get(item) + amount);
        } else {
            items.put(item, amount);
        }
    }
}
