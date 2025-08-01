package org.Group34.model.gameAssetManagers;

import com.badlogic.gdx.graphics.Texture;

public class FlooringAssetManager {
    private final static Texture ploughedLand = new Texture("flooring/Flooring_64.png");

    // ----- getters -----

    public static Texture getPloughedLand() {
        return ploughedLand;
    }

    // -------------------
}
