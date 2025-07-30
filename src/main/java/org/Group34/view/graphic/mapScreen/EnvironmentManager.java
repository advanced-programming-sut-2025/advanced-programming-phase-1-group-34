package org.Group34.view.graphic.mapScreen;

import com.badlogic.gdx.graphics.Color;
import org.Group34.model.MyGame;
import org.Group34.model.Result;
import org.Group34.controller.GameController;

public class EnvironmentManager {
    private final MyGame myGame;
    private final GameController gameController;
    private String currentSeason = "";
    private String currentWeather = "";
    private String currentDate = "";
    private String currentTime = "";
    private boolean shouldDarken = false;

    // Color tints for different environments
    private final Color normalTint = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    private final Color nightTint = new Color(0.7f, 0.7f, 0.8f, 1.0f);
    private final Color fallTint = new Color(1.0f, 0.9f, 0.7f, 1.0f);
    private final Color winterTint = new Color(0.95f, 0.95f, 1.0f, 1.0f); // Slightly blue-white tint
    private final Color rainTint = new Color(0.7f, 0.7f, 0.8f, 1.0f);
    private final Color stormTint = new Color(0.6f, 0.6f, 0.7f, 1.0f);

    public EnvironmentManager(MyGame myGame, GameController gameController) {
        this.myGame = myGame;
        this.gameController = gameController;
    }

    public void update() {
        // Update time and date
        Result timeResult = gameController.displayTime("datetime");
        if (timeResult.success()) {
            String[] timeParts = timeResult.message().split(" ");
            if (timeParts.length >= 3) {
                currentSeason = timeParts[0];
                currentDate = timeParts[1];
                currentTime = timeParts[2];

                try {
                    String timeStr = timeParts[2];
                    String[] hourMinute = timeStr.split(":");
                    int hour = Integer.parseInt(hourMinute[0]);
                    shouldDarken = (hour >= 17);
                } catch (Exception e) {
                    com.badlogic.gdx.Gdx.app.error("EnvironmentManager", "Error parsing time: " + e.getMessage());
                }
            }
        }

        // Update weather and season
        currentWeather = myGame.weatherSystem().getTodayCondition().toString();
        currentSeason = myGame.weatherSystem().getSeason().getName();
    }

    public Color getEnvironmentTint() {
        if (currentSeason.equalsIgnoreCase("WINTER")) {
            return winterTint; // Slightly blue-white tint for winter
        } else if (currentSeason.equalsIgnoreCase("FALL")) {
            return fallTint;
        } else if (currentWeather.equalsIgnoreCase("RAIN")) {
            return rainTint;
        } else if (currentWeather.equalsIgnoreCase("STORM")) {
            return stormTint;
        } else if (shouldDarken) {
            return nightTint;
        } else {
            return normalTint;
        }
    }

    public boolean isWeatherActive() {
        return currentWeather.equalsIgnoreCase("RAIN") ||
                currentWeather.equalsIgnoreCase("SNOW") ||
                currentWeather.equalsIgnoreCase("STORM");
    }

    // Getters for UI
    public String getCurrentSeason() { return currentSeason; }
    public String getCurrentWeather() { return currentWeather; }
    public String getCurrentDate() { return currentDate; }
    public String getCurrentTime() { return currentTime; }
}