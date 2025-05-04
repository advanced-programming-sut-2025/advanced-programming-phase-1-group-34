package org.Group34.model.features.TimeSystem;

import org.Group34.model.enums.DayOfWeek;
import org.Group34.model.enums.Season;

/**
 * This class is responsible for managing in-game time
 * Time progresses from 9:00 to 22:00 (14 hours a day)
 * Each season has 28 days, and days wrap across weeks and seasons
 */
public class Time {
    private int hour;
    private int date;
    private DayOfWeek dayOfWeek;
    private Season season;

    public Time() {
        this.hour = 9;
        this.date = 1;
        this.dayOfWeek = DayOfWeek.SUNDAY;
        this.season = Season.SPRING;
    }

    public void addHour(int hours) {
        if (hours < 0) throw new IllegalArgumentException("Cannot add negative hours");

        int totalHours = (hour - 9) + hours;
        int daysToAdd = totalHours / 14;
        this.hour = 9 + (totalHours % 14);

        if (daysToAdd > 0) {
            addDays(daysToAdd);
        }
    }

    public void addDays(int days) {
        if (days < 0) throw new IllegalArgumentException("Cannot add negative days");

        int newDate = this.date + days;
        int seasonsToAdd = (newDate - 1) / 28;
        this.date = (newDate - 1) % 28 + 1;

        this.dayOfWeek = dayOfWeek.next(days);
        this.season = season.next(seasonsToAdd);
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

    @Override
    public String toString() {
        return season.getName() + " " + date + " " + dayOfWeek.getName() + " " + hour + ":00";
    }
}
