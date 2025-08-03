package org.Group34.view.graphic.mapScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import org.Group34.model.entities.Player;

public class UIManager {
    private final Label dateLabel;
    private final Label timeLabel;
    private final Label weatherLabel;
    private final Label seasonLabel;
    private final Label moneyLabel;  // Added money label

    public UIManager(Skin skin, Game game, Stage stage) {
        // Create labels
        dateLabel = new Label("", skin);
        dateLabel.setPosition(20, stage.getViewport().getScreenHeight() - 30);
        stage.addActor(dateLabel);

        timeLabel = new Label("", skin);
        timeLabel.setPosition(20, stage.getViewport().getScreenHeight() - 60);
        stage.addActor(timeLabel);

        weatherLabel = new Label("", skin);
        weatherLabel.setPosition(20, stage.getViewport().getScreenHeight() - 90);
        stage.addActor(weatherLabel);

        // Added money label below weather
        moneyLabel = new Label("", skin);
        moneyLabel.setPosition(20, stage.getViewport().getScreenHeight() - 120);
        stage.addActor(moneyLabel);

        // Adjusted season label position
        seasonLabel = new Label("", skin);
        seasonLabel.setPosition(20, stage.getViewport().getScreenHeight() - 150);
        stage.addActor(seasonLabel);
    }

    public void update(EnvironmentManager environmentManager, Player player) {
        // Update labels with environment information
        seasonLabel.setText("Season: " + environmentManager.getCurrentSeason());
        dateLabel.setText("Date: " + environmentManager.getCurrentDate());
        timeLabel.setText("Time: " + environmentManager.getCurrentTime());
        weatherLabel.setText("Weather: " + environmentManager.getCurrentWeather().toLowerCase());

        // Update money label with player's money
        moneyLabel.setText("Money: " + player.getMoney());
    }
}