package org.Group34.model.features;

import org.Group34.model.enums.Season;
import org.Group34.model.enums.WeatherCondition;
import org.Group34.model.features.TimeSystem.Time;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class simulates the daily weather in the game world
 * It includes today's weather condition, randomly selected based on the season,
 * and simulates possible lightning strikes during stormy weather
 */
public class WeatherSystem {
    private WeatherCondition todayCondition;
    private final Map<String, Boolean> lightningStrikeMap = new HashMap<>();

    /**
     * Generates the weather condition for the current day based on the current season
     * Also initializes lightning strikes if the weather supports it (e.g., STORM)
     *
     * @param time the current game time which provides the season
     */
    public void generateDailyWeather(Time time) {
        this.todayCondition = WeatherCondition.random(time.getSeason());
        lightningStrikeMap.clear();

        if (todayCondition.canHaveLightning()) {
            generateLightningStrikes();
        }
    }

    /**
     * Randomly selects 3 coordinates to simulate lightning strikes during a storm
     * The coordinates are saved in a map for later lookup
     * NOTE: The bounds (0-99) are placeholders and should be adjusted based on the map and its entities
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
     * Checks if the given coordinates were struck by lightning today
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return true if the location was hit by lightning; false otherwise
     */
    public boolean isStruckByLightning(int x, int y) {
        return lightningStrikeMap.containsKey(x + "," + y);
    }

    /**
     * Returns today's weather condition.
     *
     * @return today's WeatherCondition
     */
    public WeatherCondition getTodayCondition() {
        return todayCondition;
    }

    /**
     * Checks if today's weather allows free irrigation (e.g., rain or storm)
     *
     * @return true if irrigation is free; false otherwise
     */
    public boolean isIrrigationFree() {
        return todayCondition.isIrrigationFree();
    }

    /**
     * Gets the energy multiplier for today based on the current season
     *
     * @param season the current season
     * @return a multiplier value (e.g., 1.5x in storm, 2.0x in winter snow, etc.)
     */
    public double getEnergyMultiplier(Season season) {
        return todayCondition.getEnergyMultiplier(season);
    }
}
