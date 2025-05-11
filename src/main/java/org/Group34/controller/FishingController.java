package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.enums.*;
import org.Group34.model.items.tools.FishingPole;

import java.util.*;

public class FishingController {
    private static final int MAX_FISH_PER_CAST = 6;
    private static final Random random = new Random();

    public Result startFishing(int playerSkill, Season currentSeason,
                               WeatherCondition weather,
                               FishingPole rod) {
        if (!hasFishingRod(rod)) {
            return new Result(false, "You don't have a fishing rod.");
        }

        int fishCount = calculateFishCount(playerSkill, weather);
        List<FishResult> results = new ArrayList<>();

        for (int i = 0; i < fishCount; i++) {
            FishType fish = getRandomFish(currentSeason, playerSkill);
            Quality quality = calculateQuality(playerSkill, rod);
            results.add(new FishResult(fish, quality));
        }

        if (results.isEmpty())
            return new Result(false, "No fish caught.");

        StringBuilder resultMessage = new StringBuilder("Fishing Results:\n");
        for (FishResult res : results) {
            resultMessage.append(res.fish().name())
                    .append(" - ")
                    .append(res.quality().name())
                    .append(" (Value: ")
                    .append(res.getValue())
                    .append(")\n");
        }

        return new Result(true, resultMessage.toString().trim());
    }

    // ---------- Helper Methods ----------

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

        Arrays.stream(FishType.values())
                .filter(f -> !f.isLegendary)
                .filter(f -> f.season == season)
                .forEach(availableFish::add);

        if (playerSkill >= 10) {
            Arrays.stream(FishType.values())
                    .filter(FishType::isLegendary)
                    .filter(f -> f.season == season)
                    .forEach(availableFish::add);
        }

        return availableFish.get(random.nextInt(availableFish.size()));
    }

    private Quality calculateQuality(int skill, FishingPole rod) {
        double qualityScore = random.nextDouble() * (skill + 2) * rod.getQualityModifier();

        if (qualityScore > 9.0) return Quality.IRIDIUM;
        if (qualityScore > 7.0) return Quality.GOLD;
        if (qualityScore > 5.0) return Quality.SILVER;
        return Quality.REGULAR;
    }

    private boolean hasFishingRod(FishingPole rod) {
        return rod != null;
    }

    public record FishResult(FishType fish, Quality quality) {
        public int getValue() {
            return (int) (fish.basePrice * quality.multiplier);
        }
    }
}
