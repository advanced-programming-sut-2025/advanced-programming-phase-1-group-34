package org.Group34.controller;

import org.Group34.model.enums.*;
import org.Group34.model.enums.fishing.FishType;
import org.Group34.model.enums.fishing.FishingRodType;

import java.util.*;

public class FishingController {
    private static final int MAX_FISH_PER_CAST = 6;
    private static final Random random = new Random();

    public List<FishResult> startFishing(int playerSkill, Season currentSeason,
                                         WeatherCondition weather,
                                         FishingRodType rod) {
        List<FishResult> results = new ArrayList<>();

        if (!hasFishingRod(rod)) {
            return Collections.emptyList();
        }

        int fishCount = calculateFishCount(playerSkill, weather);
        for (int i = 0; i < fishCount; i++) {
            FishType fish = getRandomFish(currentSeason, playerSkill);
            Quality quality = calculateQuality(playerSkill, rod);
            results.add(new FishResult(fish, quality));
        }

        return results;
    }

    private int calculateFishCount(int skill, WeatherCondition weather) {
        double weatherModifier = getWeatherModifier(weather);
        double r = random.nextDouble();
        int count = (int) Math.ceil(r * (2 + skill * weatherModifier));
        return Math.min(count, MAX_FISH_PER_CAST);
    }

    private double getWeatherModifier(WeatherCondition weather) {
        return switch (weather) {
            case SUNNY -> 0.5;
            case RAIN -> 1.2;
            case STORM -> 1.5;
            default -> 1.0;
        };
    }

    private FishType getRandomFish(Season season, int playerSkill) {
        List<FishType> availableFish = new ArrayList<>();

        // Add regular fish
        Arrays.stream(FishType.values())
                .filter(f -> !f.isLegendary)
                .filter(f -> f.season == season)
                .forEach(availableFish::add);

        // Add legendary fish if max skill
        if (playerSkill >= 10) {
            Arrays.stream(FishType.values())
                    .filter(FishType::isLegendary)
                    .filter(f -> f.season == season)
                    .forEach(availableFish::add);
        }

        return availableFish.get(random.nextInt(availableFish.size()));
    }

    private Quality calculateQuality(int skill, FishingRodType rod) {
        double qualityScore = random.nextDouble() * (skill + 2) * rod.qualityModifier;

        if (qualityScore > 9.0) return Quality.IRIDIUM;
        if (qualityScore > 7.0) return Quality.GOLD;
        if (qualityScore > 5.0) return Quality.SILVER;
        return Quality.REGULAR;
    }

    private boolean hasFishingRod(FishingRodType rod) {
        // Implementation depends on your inventory system
        return true; // Placeholder
    }

    public record FishResult(FishType fish, Quality quality) {
        public int getValue() {
            return (int) (fish.basePrice * quality.multiplier);
        }
    }
}