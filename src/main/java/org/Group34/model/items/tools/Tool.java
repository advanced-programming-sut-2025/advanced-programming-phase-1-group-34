package org.Group34.model.items.tools;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.items.Item;

public class Tool implements Item {
    public String getName() {
        return "Tool";
    }

    @Override
    public Texture getTexture() {
        return null;
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
