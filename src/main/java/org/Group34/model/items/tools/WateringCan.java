package org.Group34.model.items.tools;

import com.badlogic.gdx.graphics.Texture;

public class WateringCan extends Tool {
    private ToolType type;
    private int amountOfWater = 0;

    public WateringCan(ToolType type) {
        this.type = type;
    }

    // ----- getter & setter -----
    public String getName() {
        return "Watering Can";
    }

    public String getMaterial() {
        return type.getMaterial();
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    public int getCapacity() {
        return type.getCapacity();
    }

    public int getAmountOfWater() {
        return amountOfWater;
    }

    public void setAmountOfWater(int amountOfWater) {
        this.amountOfWater = amountOfWater;
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

    public void fillIt() {
        amountOfWater = getCapacity();
    }
}
