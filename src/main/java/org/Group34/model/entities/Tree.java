package org.Group34.model.entities;

import org.Group34.model.enums.Color;
import org.Group34.model.map.Space;

public class Tree extends Entity{
    public static final String RESET  = "\u001B[0m";
    public static final String GREEN  = "\u001B[32m";

    public Tree(int[] initialLocation) {
        super(initialLocation, true);
    }

    @Override
    public String toString() {
        return Color.GREEN + "T" + Color.RESET;
    }
}
