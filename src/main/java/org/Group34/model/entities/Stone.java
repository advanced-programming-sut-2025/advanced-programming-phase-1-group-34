package org.Group34.model.entities;

import org.Group34.model.enums.Color;
import org.Group34.model.map.Space;

public class Stone extends Entity{
    final String GRAY  = "\u001B[90m";
    final String RESET = "\u001B[0m";

    public Stone(int[] initialLocation) {
        super(initialLocation, true);
    }

    @Override
    public String toString() {
        return Color.GRAY + "T" + Color.RESET;
    }
}
