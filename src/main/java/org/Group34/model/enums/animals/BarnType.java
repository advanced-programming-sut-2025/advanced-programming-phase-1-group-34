package org.Group34.model.enums.animals;

public enum BarnType {
    BARN_BASIC(4),
    BARN_BIG(8),
    BARN_DELUXE(12),

    COOP_BASIC(4),
    COOP_BIG(8),
    COOP_DELUXE(12),;

    public final int capacity;

    BarnType(int cap) {
        this.capacity = cap;
    }
}
