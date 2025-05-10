package org.Group34.model.enums.animals;

public enum BarnType {
    BASIC(4),
    BIG(8),
    DELUXE(12);

    public final int capacity;

    BarnType(int cap) {
        this.capacity = cap;
    }
}
