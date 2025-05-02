package org.Group34.model.entities.buildings;

import org.Group34.model.enums.Color;
import org.Group34.model.map.Space;

public class Lake extends Building{
    public Lake(int[] initialLocation) {
        super(initialLocation);
    }


    @Override
    public String toString() {
        return Color.CYAN + "L" + Color.RESET;
    }
}
