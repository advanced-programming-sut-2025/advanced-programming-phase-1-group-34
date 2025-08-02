package org.Group34.model.entities.naturalElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.WalkAble;
import org.Group34.model.enums.Color;
import org.Group34.model.gameAssetManagers.FlooringAssetManager;

public class PloughedLand implements Entity {
    @Override
    public String toString() {
        return Color.BROWN + "P" + Color.RESET;
    }

    public Texture getTexture() {
        return FlooringAssetManager.getPloughedLand();
    }
}
