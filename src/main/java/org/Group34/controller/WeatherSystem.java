package org.Group34.controller;

import org.Group34.model.enums.Season;
import org.Group34.model.enums.WeatherCondition;
import org.Group34.model.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Simulates weather in the game world, maintaining both today's and tomorrow's weather condition
 * Supports lightning strikes during storms and allows querying weather-related game effects
 */
public class WeatherSystem {
    private static final Random RANDOM = new Random();
    private WeatherCondition todayCondition;
    private WeatherCondition tomorrowCondition;
    private final ArrayList<int[]> lightningStrikeMap = new ArrayList<>();

    /**
     * Initializes the weather system by generating today's and tomorrow's weather based on current time
     * Should be called at the start of the game
     *
     * @param time the current game time
     */
    public void initializeWeather(Time time) {
        this.todayCondition = weightedRandomWeather(time.getSeason(), null);
        this.tomorrowCondition = weightedRandomWeather(time.getSeason(), todayCondition);

        lightningStrikeMap.clear();
        if (todayCondition.canHaveLightning()) {
            generateLightningStrikes();
        }
    }

    /**
     * Advances the weather system to the next day: sets tomorrow as today, and generates a new tomorrow
     * Also generates new lightning strikes if needed
     *
     * @param time the new game time (used to determine season for tomorrow's weather)
     */
    public void advanceWeather(Time time) {
        this.todayCondition = weightedRandomWeather(time.getSeason(), null);
        this.tomorrowCondition = weightedRandomWeather(time.getSeason(), todayCondition);

        lightningStrikeMap.clear();
        if (todayCondition.canHaveLightning()) {
            generateLightningStrikes();
        }
    }

    /**
     * Randomly selects 3 coordinates to simulate lightning strikes during a storm.
     * Coordinates are saved in a map for lookup.
     * NOTE: The bounds (0-99) are placeholders and should match the map dimensions.
     */
    public ArrayList<int[]> generateLightningStrikes() {
        for (int i = 0; i < 3; i++) {
            int x = RANDOM.nextInt(100);
            int y = RANDOM.nextInt(100);
            lightningStrikeMap.add(new int[]{x, y});
        }
        return lightningStrikeMap;
    }

    /**
     * Checks if a specific coordinate has been struck by lightning today.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if struck by lightning; false otherwise
     */
    public boolean isStruckByLightning(int x, int y) {
        return lightningStrikeMap.contains(new int[]{x, y});
    }

    /**
     * @return today's weather condition
     */
    public WeatherCondition getTodayCondition() {
        return todayCondition;
    }

    /**
     * @return tomorrow's predicted weather condition
     */
    public WeatherCondition getTomorrowCondition() {
        return tomorrowCondition;
    }

    /**
     * @return true if today's weather provides free irrigation (RAIN or STORM)
     */
    public boolean isIrrigationFree() {
        return todayCondition.isIrrigationFree();
    }

    /**
     * Returns the energy multiplier based on today's weather and season.
     *
     * @param season current season
     * @return multiplier value (e.g., 1.0, 1.5, 2.0)
     */
    public double getEnergyMultiplier(Season season) {
        return todayCondition.getEnergyMultiplier(season);
    }

    private WeatherCondition weightedRandomWeather(Season season, WeatherCondition today) {
        List<WeatherCondition> weighted = new ArrayList<>();

        weighted.add(WeatherCondition.SUNNY);

        if (season != Season.WINTER) {
            weighted.add(WeatherCondition.RAIN);
            weighted.add(WeatherCondition.STORM);
        } else {
            weighted.add(WeatherCondition.SNOW);
        }

        if (today != null) {
            switch (today) {
                case SUNNY -> {
                    weighted.add(WeatherCondition.RAIN);
                    weighted.add(WeatherCondition.STORM);
                }
                case RAIN -> {
                    weighted.add(WeatherCondition.SUNNY);
                    weighted.add(WeatherCondition.STORM);
                }
                case STORM -> {
                    weighted.add(WeatherCondition.SUNNY);
                    weighted.add(WeatherCondition.RAIN);
                    weighted.add(WeatherCondition.SUNNY);
                }
                case SNOW -> {
                    weighted.add(WeatherCondition.SUNNY);
                    weighted.add(WeatherCondition.SUNNY);
                }
            }
        }

        return weighted.get(RANDOM.nextInt(weighted.size()));
    }


}
