package org.Group34.model.items.tools;

import com.badlogic.gdx.graphics.Texture;

public class Axe extends Tool {
    private ToolType type;

    public Axe(ToolType type) {
        this.type = type;
    }

    // ----- getter & setter -----
    public String getName() {
        return "Axe";
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
