package org.Group34.view.graphic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.Group34.controller.menu.GameMenuController;
import org.Group34.model.App;
import org.Group34.model.Result;
import org.Group34.view.graphic.menuScreen.MainMenuScreen;

public class GameMenuScreen extends ScreenAdapter {
    private final Stage stage;
    private final Skin skin;
    private final Game game;
    private final GameMenuController controller;
    private final Texture backgroundTexture;
    private final Image backgroundImage;
    private Label statusLabel;

    public GameMenuScreen(Skin skin, Game game) {
        this.skin = skin;
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.controller = new GameMenuController();

        backgroundTexture = new Texture(Gdx.files.internal("menuBackgrounds/background-gamemenu.png"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);

        Gdx.input.setInputProcessor(stage);
        createUI();
    }

    private void createUI() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        // === Players Row ===
        Label player1Label = new Label("Player 1:", skin);
        TextField player1Field = new TextField("", skin);

        Label player2Label = new Label("Player 2:", skin);
        TextField player2Field = new TextField("", skin);

        Label player3Label = new Label("Player 3:", skin);
        TextField player3Field = new TextField("", skin);

        Table playersRow = new Table();
        playersRow.add(player1Label).padRight(5);
        playersRow.add(player1Field).width(300).padRight(20);
        playersRow.add(player2Label).padRight(5);
        playersRow.add(player2Field).width(300).padRight(20);
        playersRow.add(player3Label).padRight(5);
        playersRow.add(player3Field).width(300);

        mainTable.add(playersRow).padTop(230).row();

        // === Map Row ===
        Label mapLabel = new Label("Map:", skin);
        Texture map1Texture = new Texture(Gdx.files.internal("images/map1.png"));
        Texture map2Texture = new Texture(Gdx.files.internal("images/map2.png"));

        ImageButton.ImageButtonStyle map1Style = new ImageButton.ImageButtonStyle();
        map1Style.imageUp = new Image(map1Texture).getDrawable();
        ImageButton map1Button = new ImageButton(map1Style);

        ImageButton.ImageButtonStyle map2Style = new ImageButton.ImageButtonStyle();
        map2Style.imageUp = new Image(map2Texture).getDrawable();
        ImageButton map2Button = new ImageButton(map2Style);

        Table mapRow = new Table();
        mapRow.add(mapLabel).padRight(20);
        mapRow.add(map1Button).size(120, 120).padRight(20);
        mapRow.add(map2Button).size(120, 120);

        mainTable.add(mapRow).padTop(30).row();

        // === Start New Game & Load Game ===
        TextButton newGameButton = new TextButton("Start New Game", skin);
        TextButton loadGameButton = new TextButton("Load Game", skin);

        Table gameButtonsRow = new Table();
        gameButtonsRow.add(newGameButton).padRight(50);
        gameButtonsRow.add(loadGameButton);

        mainTable.add(gameButtonsRow).padTop(50).row();

        // === Back to Main Menu (Bottom with Big Space) ===
        TextButton backToMainMenuButton = new TextButton("Back to Main Menu", skin);
        mainTable.add(backToMainMenuButton).padTop(100);

        // === Status Label Bottom ===
        statusLabel = new Label("", skin);
        statusLabel.setColor(Color.RED);
        mainTable.row();
        mainTable.add(statusLabel).padTop(20);

        // === Add to Stage ===
        stage.addActor(backgroundImage);
        stage.addActor(mainTable);

        // === Listeners ===
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                java.util.List<String> usernamesList = new java.util.ArrayList<>();
                if (!player1Field.getText().trim().isEmpty()) usernamesList.add(player1Field.getText().trim());
                if (!player2Field.getText().trim().isEmpty()) usernamesList.add(player2Field.getText().trim());
                if (!player3Field.getText().trim().isEmpty()) usernamesList.add(player3Field.getText().trim());

                if (usernamesList.isEmpty()) {
                    showStatus(new Result(false, "Please enter at least one username."));
                    return;
                }

                String[] usernames = usernamesList.toArray(new String[0]);
                Result result = controller.gameNew(usernames);
                showStatus(result);
            }
        });

        map1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.gameMap("1");
                showStatus(result);
            }
        });

        map2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.gameMap("2");
                showStatus(result);
            }
        });

        loadGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.loadGame();
                showStatus(result);
            }
        });

        backToMainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                App.setCurrentMenu(App.getCurrentMenu().MAIN_MENU);
                game.setScreen(new MainMenuScreen(skin, game));
                dispose();
            }
        });
    }

    private void showStatus(Result result) {
        statusLabel.setText(result.message());
        statusLabel.clearActions();
        statusLabel.addAction(Actions.sequence(
                Actions.delay(1.5f),
                Actions.run(() -> statusLabel.setText(""))
        ));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
    }
}