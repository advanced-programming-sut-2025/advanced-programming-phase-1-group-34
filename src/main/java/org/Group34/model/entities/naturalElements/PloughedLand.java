package org.Group34.model.entities.naturalElements;

import org.Group34.model.entities.Entity;
import org.Group34.model.entities.WalkAble;
import org.Group34.model.enums.Color;

public class PloughedLand implements Entity, WalkAble {
    @Override
    public String toString() {
        return Color.BROWN + "P" + Color.RESET;
    }
}
