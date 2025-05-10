package org.Group34.model.enums;

public enum CoopType {
    BASIC(4),
    BIG(8),
    DELUXE(12);

    public final int capacity;
    CoopType(int cap) { this.capacity = cap; }
}
