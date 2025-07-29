package org.Group34.model.entities.naturalElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.WalkAble;
import org.Group34.model.enums.Color;

public class PloughedLand implements Entity, WalkAble {
    @Override
    public String toString() {
        return Color.BROWN + "P" + Color.RESET;
    }

    public Texture getTexture() {
        return new Texture(Gdx.files.internal("tiles/ploughedLand.png"));
    }
}
