package org.Group34.model.entities.buildings;

import org.Group34.model.enums.Color;
import org.Group34.model.map.Space;

public class House implements Building{
    @Override
    public String toString() {
        return Color.BROWN + "H" + Color.RESET;
    }
}
