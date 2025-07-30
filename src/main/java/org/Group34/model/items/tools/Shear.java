package org.Group34.model.items.tools;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.gameAssetManagers.ToolAssetManager;

public class Shear extends Tool { // TODO This class must be filled.
    private static final int price = 1000;
    private static final String description = "Use this to collect wool from sheep";

    public String getName() {
        return "Shear";
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public Texture getTexture() {
        return ToolAssetManager.getShear();
    }
}
