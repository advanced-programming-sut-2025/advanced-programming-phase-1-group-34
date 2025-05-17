package org.Group34.model.items.tools;

public class TrashCan extends Tool { // TODO This class must be filled.
    private ToolType type;

    public TrashCan(ToolType type) {
        this.type = type;
    }

    // ----- getter & setter -----
    public String getName() {
        return "Trash Can";
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
