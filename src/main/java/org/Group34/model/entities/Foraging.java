package org.Group34.model.entities;

import org.Group34.model.enums.Color;

public class Foraging extends Entity{

    public Foraging(int[] initialLocation) {
        super(initialLocation, true);
    }


    @Override
    public String toString() {
            return Color.RED + "F" + Color.RESET;
    }
}
