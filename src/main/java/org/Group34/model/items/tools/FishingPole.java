package org.Group34.model.items.tools;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.items.Item;

public class FishingPole extends Tool {
    private ToolType type;

    public FishingPole(ToolType type) {
        this.type = type;
    }

    // ----- getter & setter -----
    public String getName() {
        return "Fishing Pole";
    }

    public String getMaterial() {
        return type.getMaterial();
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    public double getQualityModifier() {
        return type.getQualityModifier();
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