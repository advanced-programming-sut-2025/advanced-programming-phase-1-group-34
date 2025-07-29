package org.Group34.model.entities;

import com.badlogic.gdx.graphics.Texture;

/**
* Anything that can be displayed on the map is an entity
 * Each Entity has a location related to its Space which is in it
 * Some Entities like Building are not removeAble
 */

public interface Entity {
    public Texture getTexture();
}
