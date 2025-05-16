package org.Group34.model.items.tools;

import org.Group34.model.items.Item;

public class Backpack implements Item { // TODO This class must be filled.
    private ToolType type;

    public Backpack(ToolType type) {
        this.type = type;
    }

    // ----- getter & setter -----
    public String getName() {
        return type.getName();
    }

    public int getCapacity() {
        return type.getCapacity();
    }

    public ToolType getType() {
        return type;
    }

    public void setType(ToolType type) {
        this.type = type;
    }
    // ---------------------------
}
