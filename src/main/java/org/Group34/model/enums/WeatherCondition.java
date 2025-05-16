package org.Group34.model.enums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Enumeration of possible weather conditions in the game
 * Each condition can affect gameplay elements like irrigation and energy usage
 */
public enum WeatherCondition {
    SUNNY,
    RAIN,
    STORM,
    SNOW;

    private static final WeatherCondition[] VALUES = values();

    public static WeatherCondition shifted(WeatherCondition base, int shift) {
        int index = (base.ordinal() + shift) % VALUES.length;
        return VALUES[index];
    }

    /**
     * Determines whether irrigation is free under this weather condition
     *
     * @return true if the condition is RAIN or STORM; false otherwise
     */
    public boolean isIrrigationFree() {
        return this == STORM;
    }

    /**
     * Returns the energy multiplier associated with this weather condition,
     * potentially depending on the season
     *
     * @param season the current season
     * @return a multiplier for energy cost (e.g., 1.5x for RAIN/STORM)
     */
    public double getEnergyMultiplier(Season season) {
        return switch (this) {
            case RAIN, STORM -> 1.5;
            case SNOW -> season == Season.WINTER ? 2.0 : 1.0;
            default -> 1.0;
        };
    }

    /**
     * Checks if this weather condition supports lightning strikes
     *
     * @return true if the condition is STORM; false otherwise
     */
    public boolean canHaveLightning() {
        return this == STORM;
    }
}
