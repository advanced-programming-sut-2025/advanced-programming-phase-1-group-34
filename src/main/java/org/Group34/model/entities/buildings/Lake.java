package org.Group34.model.entities.buildings;

import org.Group34.model.enums.Color;
import org.Group34.model.map.Space;

public class Lake extends Building {
    @Override
    public String toString() {
        return Color.CYAN + "L" + Color.RESET;
    }
}
