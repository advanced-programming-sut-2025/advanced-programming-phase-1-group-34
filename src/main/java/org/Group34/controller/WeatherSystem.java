package org.Group34.controller;

import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.naturalElements.Foraging;
import org.Group34.model.entities.naturalElements.Tree;
import org.Group34.model.enums.Season;
import org.Group34.model.enums.WeatherCondition;
import org.Group34.model.items.Time;
import org.Group34.model.map.Space;
import org.Group34.model.map.Map;

import java.util.*;

/**
 * Simulates weather in the game world, maintaining both today's and tomorrow's weather condition
 */
public class WeatherSystem {
    private WeatherCondition todayCondition;
    private WeatherCondition tomorrowCondition;

    /**
     * Initializes the weather system by generating today's and tomorrow's weather based on current time
     * Should be called at the start of the game
     *
     * @param time the current game time
     */
    public void initializeWeather(Time time, Map map) {
        this.todayCondition = WeatherCondition.random(time.getSeason());
        this.tomorrowCondition = WeatherCondition.random(time.getSeason());

        if (todayCondition.canHaveLightning()) {
            generateLightningStrikes(map);
        }
    }

    /**
     * Advances the weather system to the next day: sets tomorrow as today, and generates a new tomorrow
     * Also generates new lightning strikes if needed
     *
     * @param time the new game time (used to determine season for tomorrow's weather)
     */
    public void advanceWeather(Time time, Map map) {
        this.todayCondition = this.tomorrowCondition;
        this.tomorrowCondition = WeatherCondition.random(time.getSeason());

        if (todayCondition.canHaveLightning()) {
            generateLightningStrikes(map);
        }
    }

    /**
     * Randomly selects 3 coordinates to simulate lightning strikes during a storm.
     */
    private void generateLightningStrikes(Map map) {
        Random random = new Random();

        for (Player player : map.playerFarms().keySet()) {
            if (random.nextDouble() < 0.6) {
                Space farm = map.playerFarms().get(player);
                Set<String> generatedCoords = new HashSet<>();
                int strikes = 0;

                while (strikes < 3) {
                    int x = random.nextInt(farm.width());
                    int y = random.nextInt(farm.height());
                    String key = x + "," + y;

                    if (generatedCoords.add(key)) {
                        strikes++;
                        Entity entity = farm.getEntityByLocation(x, y);

                        if (entity instanceof Tree || entity instanceof Foraging) {
                            farm.placingEntity(x, y, null);
                        }
                    }
                }
            }
        }
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
     * Returns the energy multiplier based on today's weather and season.
     *
     * @param season current season
     * @return multiplier value (e.g., 1.0, 1.5, 2.0)
     */
    public double getEnergyMultiplier(Season season) {
        return todayCondition.getEnergyMultiplier(season);
    }
}
