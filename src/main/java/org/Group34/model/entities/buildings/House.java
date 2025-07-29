package org.Group34.model.entities.buildings;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.enums.Color;
import org.Group34.model.gameAssetManagers.BuildingsAssetManager;
import org.Group34.model.map.Space;

public class House implements Building{
    @Override
    public String toString() {
        return Color.BROWN + "H" + Color.RESET;
    }

    @Override
    public Texture getTexture() {
        return BuildingsAssetManager.house;
    }
}
