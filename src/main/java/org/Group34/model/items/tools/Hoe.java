package org.Group34.model.items.tools;

import com.badlogic.gdx.graphics.Texture;

public class Hoe extends Tool {
    private ToolType type;

    public Hoe(ToolType type) {
        this.type = type;
    }

    // ----- getter & setter -----
    public String getName() {
        return "Hoe";
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

    @Override
    public Texture getTexture() {
        return type.getTexture();
    }

    public void setType(ToolType type) {
        this.type = type;
    }
    // ---------------------------
}
