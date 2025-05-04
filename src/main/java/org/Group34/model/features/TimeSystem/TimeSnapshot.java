package org.Group34.model.features.TimeSystem;

import org.Group34.model.enums.DayOfWeek;
import org.Group34.model.enums.Season;

/**
 * Immutable snapshot of Time, used for comparisons
 */
public class TimeSnapshot {
    private final int hour;
    private final int date;
    private final DayOfWeek dayOfWeek;
    private final Season season;

    public TimeSnapshot(Time time) {
        this.hour = time.getHour();
        this.date = time.getDate();
        this.dayOfWeek = time.getDayOfTheWeek();
        this.season = time.getSeason();
    }

    public static int hoursBetween(TimeSnapshot from, TimeSnapshot to) {
        int totalFromHours = (from.season.ordinal() * 28 + from.date - 1) * 12 + (from.hour - 9);
        int totalToHours = (to.season.ordinal() * 28 + to.date - 1) * 12 + (to.hour - 9);
        return totalToHours - totalFromHours;
    }

    public int getHour() {
        return hour;
    }

    public int getDate() {
        return date;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public Season getSeason() {
        return season;
    }
}
