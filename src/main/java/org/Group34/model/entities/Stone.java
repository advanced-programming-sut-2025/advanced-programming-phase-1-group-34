package org.Group34.model.entities;

import org.Group34.model.enums.Color;
import org.Group34.model.map.Space;

public class Stone implements Entity{
    @Override
    public String toString() {
        return Color.GRAY + "S" + Color.RESET;
    }
}
