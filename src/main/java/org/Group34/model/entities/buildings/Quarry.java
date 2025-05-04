package org.Group34.model.entities.buildings;

import org.Group34.model.enums.Color;
import org.Group34.model.map.Space;

public class Quarry extends Building{
    @Override
    public String toString() {
        return Color.GRAY + "Q" + Color.RESET;
    }
}
