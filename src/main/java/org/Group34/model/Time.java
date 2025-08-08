package org.Group34.model;

import org.Group34.controller.StartANewDayController;
import org.Group34.model.entities.Player;
import org.Group34.model.enums.DayOfWeek;
import org.Group34.model.enums.Season;


/**
 * This class is responsible for managing in-game time.
 * Time progresses from 9:00 to 22:00 (14 hours a day)
 * Each season has 28 days, and days wrap across weeks and seasons
 */
public class Time implements  Comparable<Time> {
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
        if (hours < 0) throw new IllegalArgumentException("Cannot add negative hours");

        int totalHours = (hour - 9) + hours;
        int daysToAdd = totalHours / 14;
        this.hour = 9 + (totalHours % 14);

        if (daysToAdd > 0) {
            this.hour = 9;
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

    public void setSeason(Season season) {
        this.season = season;
    }

    public Result cheatAdvanceTime(Integer hours) {
        try {
            Integer h = hours;
            if (h == null) return new Result(false, "you should give a number as hours argument");
            this.addHours(h);
            return new Result(true, "Cheat Code Activated: (" + this + ")");
        }
        catch (Exception IllegalArgumentException){
            return new Result(false, "input an positive number as hours argument");
        }
    }

    public Result cheatAdvanceDate(Integer d, MyGame myGame, Player player) {
        StartANewDayController startANewDayController = new StartANewDayController(myGame, myGame.map().getSpaces(), myGame.time());
        startANewDayController.ManageAllTasks(player);
        try {
            if (d == null) return new Result(false, "you should give a number as days argument");
            this.addDays(d);
            myGame.weatherSystem().initializeWeather(this);
            myGame.weatherSystem().advanceWeather(this);
            return new Result(true, "Cheat Code Activated: (" + this + ")");
        }
        catch (Exception IllegalArgumentException){
            return new Result(false, "input an positive number as days argument");
        }
    }

    @Override
    public String toString() {
        return season.getName() + " " + date + " " + dayOfWeek.getName() + " " + hour + ":00";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hour == time.hour && date == time.date && dayOfWeek == time.dayOfWeek && season == time.season;
    }

    public Time copy() {
        Time copy = new Time();
        copy.hour = this.hour;
        copy.date = this.date;
        copy.dayOfWeek = this.dayOfWeek;
        copy.season = this.season;
        return copy;
    }

    /**
     * Compares this Time to another Time instance.
     * Returns:
     *   - negative if this < other
     *   - zero if this == other
     *   - positive if this > other
     */
    @Override
    public int compareTo(Time other) {
        int seasonCompare = Integer.compare(this.season.ordinal(), other.season.ordinal());
        if (seasonCompare != 0) return seasonCompare;

        int dateCompare = Integer.compare(this.date, other.date);
        if (dateCompare != 0) return dateCompare;

        return Integer.compare(this.hour, other.hour);
    }

    public Result displayTime(String type) {
        String message = "";
        switch (type){
            case "time": message = this.getHour() + ":00" ; break;
            case "date": message = this.getSeason().getName() + " " + this.getDate(); break;
            case "datetime": message = this.getSeason().getName() + " " + this.getDate() + " "
                    + this.getHour() + ":00"; break;
            case "day of week": message = this.getDayOfWeek().getName(); break;
            case "season": message = this.getSeason().getName(); break;
        }
        return new Result(true, message);
    }
}
