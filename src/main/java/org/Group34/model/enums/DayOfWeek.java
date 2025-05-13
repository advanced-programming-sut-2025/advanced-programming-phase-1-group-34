package org.Group34.model.enums;

public enum DayOfWeek {
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");

    private final String name;

    DayOfWeek(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public DayOfWeek next(int days) {
        int index = Math.floorMod(this.ordinal() + days, 7);
        return values()[index];
    }

    @Override
    public String toString() {
        return name;
    }
}
