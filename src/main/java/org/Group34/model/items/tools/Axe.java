package org.Group34.model.items.tools;

import org.Group34.model.items.Item;

public class Axe implements Item {
    private ToolType type;

    public Axe(ToolType type) {
        this.type = type;
    }

    // ----- getter & setter -----
    public String getName() {
        return type.getName();
    }

    public String getMaterial() {
        return type.getMaterial();
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    public ToolType getType() {
        return type;
    }

    public void setType(ToolType type) {
        this.type = type;
    }
    // ---------------------------
}
