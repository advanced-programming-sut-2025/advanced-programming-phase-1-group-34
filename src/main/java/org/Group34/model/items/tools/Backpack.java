package org.Group34.model.items.tools;

import org.Group34.model.items.Item;

import java.util.ArrayList;

public class Backpack extends Tool { // TODO This class must be filled.
    private ToolType type;
    private ArrayList<Item> items;

    public Backpack(ToolType type) {
        this.type = type;
    }

    // ----- getter & setter -----
    public String getName() {
        return "Backpack";
    }

    public int getCapacity() {
        return type.getCapacity();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ToolType getType() {
        return type;
    }

    public void setType(ToolType type) {
        this.type = type;
    }
    // ---------------------------
}
