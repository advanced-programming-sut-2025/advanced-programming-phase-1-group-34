package org.Group34.model.entities.buildings;

import org.Group34.model.enums.Color;
import org.Group34.model.map.Space;

public class GreenHouse extends Building{
    @Override
    public String toString() {
        return Color.YELLOW + "G" + Color.RESET;
    }
}
