package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.enums.*;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;
import org.Group34.model.items.tools.FishingPole;

import java.util.*;

public class FishingController {
    private static final int MAX_FISH_PER_CAST = 6;
    private static final Random random = new Random();

    public Result startFishing(Player player, Season currentSeason,
                               WeatherCondition weather,
                               FishingPole rod) {
        if (rod == null) {
            return new Result(false, "You don't have a fishing rod.");
        }

        int fishCount = calculateFishCount(player.getLevel(LevelType.FISHING_LEVEL), weather);
        List<FishResult> results = new ArrayList<>();

        for (int i = 0; i < fishCount; i++) {
            FishType fish = getRandomFish(currentSeason, player.getLevel(LevelType.FISHING_LEVEL));
            Quality quality = calculateQuality(player.getLevel(LevelType.FISHING_LEVEL), rod);
            results.add(new FishResult(fish, quality));
        }

        player.decreaseEnergy(calculateEnergy(rod, player));

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

        player.levelUp(LevelType.FISHING_LEVEL, 5);
        return new Result(true, resultMessage.toString().trim());
    }

    // ---------- Helper Methods ----------

    private int calculateFishCount(int skill, WeatherCondition weather) {
        double weatherModifier = getWeatherModifier(weather);
        double r = random.nextDouble();
        int count = (int) Math.ceil(r * (2 + skill) * weatherModifier);
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

        if (playerSkill >= 450) {
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

    private int calculateEnergy(FishingPole rod, Player player) {
        if (rod.getMaterial().equals("Training") || rod.getMaterial().equals("Bamboo")) {
            return 8 + (player.getLevel(LevelType.FISHING_LEVEL) >= 450 ? -1 : 0);
        }
        else if (rod.getMaterial().equals("Fiberglass")) {
            return 6 + (player.getLevel(LevelType.FISHING_LEVEL) >= 450 ? -1 : 0);
        }
        else {
            return 4 + (player.getLevel(LevelType.FISHING_LEVEL) >= 450 ? -1 : 0);
        }
    }

    public record FishResult(FishType fish, Quality quality) {
        public int getValue() {
            return (int) (fish.basePrice * quality.multiplier);
        }
    }


}
