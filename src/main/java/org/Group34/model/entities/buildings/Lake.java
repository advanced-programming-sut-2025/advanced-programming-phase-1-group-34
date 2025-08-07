package org.Group34.model.entities.buildings;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.enums.Color;
import org.Group34.model.gameAssetManagers.FlooringAssetManager;
import org.Group34.model.map.Space;

public class Lake implements Building {
    @Override
    public String toString() {
        return Color.CYAN + "L" + Color.RESET;
    }

    @Override
    public Texture getTexture() {
        return FlooringAssetManager.getLake();
    }
}
