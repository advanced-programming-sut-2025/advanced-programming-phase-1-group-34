package org.Group34.model.items;

import org.Group34.model.enums.DayOfWeek;
import org.Group34.model.enums.Season;

/**
 * This class is responsible for managing in-game time.
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

    public void addHours(int hours) {
        if (hours == 0) return;

        int totalRelativeHours = (this.hour - 9) + hours;

        int daysToAdd = Math.floorDiv(totalRelativeHours, 14);
        int remainingHours = Math.floorMod(totalRelativeHours, 14);

        this.hour = 9 + remainingHours;

        if (daysToAdd != 0) {
            addDays(daysToAdd);
        }
    }

    public void addDays(int days) {
        if (days == 0) return;

        int totalDays = this.date + days - 1;  // 0-based
        int seasonsToAdd = Math.floorDiv(totalDays, 28);

        this.date = Math.floorMod(totalDays, 28) + 1;
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
