package org.Group34.model.items.foods;

import com.badlogic.gdx.graphics.Texture;

public interface FarmingProduct extends Food{
    int getBaseSellPrice();
    boolean isEdible();
    int getEnergy();
    int getHealth();
    Texture getTexture();
}
