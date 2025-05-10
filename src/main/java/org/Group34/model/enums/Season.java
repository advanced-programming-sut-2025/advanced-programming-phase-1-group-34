package org.Group34.model.enums;

public enum Season {
    SPRING("Spring"),
    SUMMER("Summer"),
    AUTUMN("Autumn"),
    WINTER("Winter"),
    ALL("all");

    private final String name;

    Season(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Season next(int count) {
        return values()[(this.ordinal() + count) % values().length];
    }

    @Override
    public String toString() {
        return name;
    }
}
