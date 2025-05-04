package org.Group34.model.features;

import org.Group34.model.enums.Season;
import org.Group34.model.enums.WeatherCondition;
import org.Group34.model.features.TimeSystem.Time;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Simulates weather in the game world, maintaining both today's and tomorrow's weather condition
 * Supports lightning strikes during storms and allows querying weather-related game effects
 */
public class WeatherSystem {
    private WeatherCondition todayCondition;
    private WeatherCondition tomorrowCondition;
    private final Map<String, Boolean> lightningStrikeMap = new HashMap<>();

    /**
     * Initializes the weather system by generating today's and tomorrow's weather based on current time
     * Should be called at the start of the game
     *
     * @param time the current game time
     */
    public void initializeWeather(Time time) {
        this.todayCondition = WeatherCondition.random(time.getSeason());
        this.tomorrowCondition = WeatherCondition.random(time.getSeason());
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
    public void advanceDay(Time time) {
        this.todayCondition = this.tomorrowCondition;
        this.tomorrowCondition = WeatherCondition.random(time.getSeason());
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
    private void generateLightningStrikes() {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int x = random.nextInt(100); // TODO: Adjust according to the map and its entities
            int y = random.nextInt(100);
            String key = x + "," + y;
            lightningStrikeMap.put(key, true);
        }
    }

    /**
     * Checks if a specific coordinate has been struck by lightning today.
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if struck by lightning; false otherwise
     */
    public boolean isStruckByLightning(int x, int y) {
        return lightningStrikeMap.containsKey(x + "," + y);
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
}
