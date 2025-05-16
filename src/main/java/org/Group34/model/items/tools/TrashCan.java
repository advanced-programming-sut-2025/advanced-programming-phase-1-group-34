package org.Group34.model.items.tools;

import org.Group34.model.items.Item;

public class TrashCan implements Item { // TODO This class must be filled.
    private ToolType type;

    public TrashCan(ToolType type) {
        this.type = type;
    }

    // ----- getter & setter -----
    public String getName() {
        return type.getName();
    }

    public String getMaterial() {
        return type.getMaterial();
    }

    public int getReturnPercentage() {
        return type.getReturnPercentage();
    }

    public ToolType getType() {
        return type;
    }

    public void setType(ToolType type) {
        this.type = type;
    }
    // ---------------------------
}
