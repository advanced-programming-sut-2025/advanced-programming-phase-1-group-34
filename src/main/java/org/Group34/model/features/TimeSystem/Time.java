package org.Group34.model.features.TimeSystem;

import org.Group34.model.enums.DayOfWeek;
import org.Group34.model.enums.Season;

/**
 * This class is responsible for managing in-game time
 * It tracks the current hour, date, day of the week, and season
 *
 * Each game instance should have a {@code Time} object to maintain consistent time progression
 * Time increases logically and automatically handles transitions between days and seasons
 */
public class Time {
    private int hour;
    private int date;
    private DayOfWeek dayOfWeek;
    private Season season;

    /**
     * Creates a new Time instance starting at 9:00 AM on day 1 of the week and in spring
     */
    public Time() {
        hour = 9;
        date = 1;
        dayOfWeek = DayOfWeek.SUNDAY;
        season = Season.SPRING;
    }

    /**
     * Adds the specified number of hours to the current time
     * If the total exceeds 12 (9AM to 9PM), the extra hours roll over to the next day(s)
     *
     * @param hours Number of hours to add (must be non-negative)
     */
    public void addHour(int hours) {
        if (hours < 0) throw new IllegalArgumentException("Cannot add negative hours");

        int totalHours = this.hour - 9 + hours;
        int daysToAdd = totalHours / 12;
        this.hour = 9 + (totalHours % 12);

        if (daysToAdd > 0) {
            addDays(daysToAdd);
        }
    }

    /**
     * Adds the specified number of days to the current date
     * Automatically updates the day of the week and season based on the number of days added
     *
     * Each season contains 28 days
     *
     * @param days Number of days to add (must be non-negative)
     */
    public void addDays(int days) {
        if (days < 0) throw new IllegalArgumentException("Cannot add negative days");

        int newDate = this.date + days;
        int seasonsToAdd = (newDate - 1) / 28;
        this.date = (newDate - 1) % 28 + 1;

        dayOfWeek = dayOfWeek.next(days);
        season = season.next(seasonsToAdd);
    }

    public int getHour() {
        return hour;
    }

    public int getDate() {
        return date;
    }

    public DayOfWeek getDayOfTheWeek() {
        return dayOfWeek;
    }

    public Season getSeason() {
        return season;
    }

    public TimeSnapshot snapshot() {
        return new TimeSnapshot(this);
    }

    @Override
    public String toString() {
        return season.getName() + " " + date + " " + dayOfWeek.getName() + " " + hour + ":00";
    }
}
