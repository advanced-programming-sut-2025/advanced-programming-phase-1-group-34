package org.Group34.model.enums;

public enum Quality {
    REGULAR(1.0),
    SILVER(1.25),
    GOLD(1.5),
    IRIDIUM(2.0);

    public final double multiplier;
    Quality(double multi) {
        this.multiplier = multi;
    }
}