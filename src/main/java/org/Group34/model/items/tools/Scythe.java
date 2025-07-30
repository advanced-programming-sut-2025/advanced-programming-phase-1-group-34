package org.Group34.model.items.tools;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.gameAssetManagers.ToolAssetManager;

public class Scythe extends Tool {

    // ----- getter & setter -----
    public String getName() {
        return "Scythe";
    }

    public int getEnergy() {
        return 2;
    }

    @Override
    public Texture getTexture() {
        return ToolAssetManager.getScythe();
    }
    // ---------------------------

}
