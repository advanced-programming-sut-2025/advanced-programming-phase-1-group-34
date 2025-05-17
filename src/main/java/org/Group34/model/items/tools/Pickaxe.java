package org.Group34.model.items.tools;

public class Pickaxe extends Tool {
    private ToolType type;

    public Pickaxe(ToolType type) {
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
