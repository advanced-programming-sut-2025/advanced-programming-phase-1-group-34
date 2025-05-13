package org.Group34.model.enums;

public enum Season {
    SPRING("Spring"),
    SUMMER("Summer"),
    FALL("Fall"),
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
        int index = Math.floorMod(this.ordinal() + count, 4);
        return values()[index];
    }

    @Override
    public String toString() {
        return name;
    }
}
