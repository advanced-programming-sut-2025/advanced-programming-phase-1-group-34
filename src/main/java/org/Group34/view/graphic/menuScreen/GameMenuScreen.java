package org.Group34.view.graphic.menuScreen;

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
import org.Group34.controller.GameController;
import org.Group34.controller.menu.GameMenuController;
import com.badlogic.gdx.scenes.scene2d.Actor;
import org.Group34.model.App;
import org.Group34.model.MyGame;
import org.Group34.model.Result;
import org.Group34.network.client.GameClient;
import org.Group34.view.graphic.GraphicAppView;
import org.Group34.view.graphic.mapScreen.GameScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameMenuScreen extends ScreenAdapter {
    private final Stage stage;
    private final Skin skin;
    private final Game game;
    private final GameMenuController controller;
    private final Texture backgroundTexture;
    private final Image backgroundImage;
    private Label statusLabel;

    private final GraphicAppView app;
    private GameClient client;

    private String[] selectedPlayers;
    private List<Integer> playerMapChoices;
    private String selectedMap;
    private Dialog currentDialog; // Track current dialog

    public GameMenuScreen(Skin skin, Game game, GraphicAppView app, GameClient client) {
        this.skin = skin;
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.controller = new GameMenuController();

        this.app = app;
        this.client = client;

        backgroundTexture = new Texture(Gdx.files.internal("menuBackgrounds/background-gamemenu.png"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);

        Gdx.input.setInputProcessor(stage);
        createUI();
    }

    private void createUI() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        // Player input fields
        TextField player1Field = new TextField("", skin);
        player1Field.setMessageText("Player 1 (required)");
        TextField player2Field = new TextField("", skin);
        player2Field.setMessageText("Player 2 (optional)");
        TextField player3Field = new TextField("", skin);
        player3Field.setMessageText("Player 3 (optional)");

        // Layout for player inputs
        Table playersRow = new Table();
        playersRow.add(new Label("Players:", skin)).padRight(10);
        playersRow.add(player1Field).width(250).padRight(10);
        playersRow.add(player2Field).width(250).padRight(10);
        playersRow.add(player3Field).width(250);
        mainTable.add(playersRow).padTop(150).row();

        // Action buttons
        TextButton newGameButton = new TextButton("Start New Game", skin);
        TextButton loadGameButton = new TextButton("Load Game", skin);
        TextButton backButton = new TextButton("Back to Main Menu", skin);

        Table buttonTable = new Table();
        buttonTable.add(newGameButton).padRight(30);
        buttonTable.add(loadGameButton).padRight(30);
        buttonTable.add(backButton);
        mainTable.add(buttonTable).padTop(50).row();

        // Status label
        statusLabel = new Label("", skin);
        statusLabel.setColor(Color.RED);
        mainTable.add(statusLabel).padTop(20);

        // Add everything to stage
        stage.addActor(backgroundImage);
        stage.addActor(mainTable);

        // Button listeners
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                List<String> usernamesList = new ArrayList<>();
                if (!player1Field.getText().trim().isEmpty()) usernamesList.add(player1Field.getText().trim());
                if (!player2Field.getText().trim().isEmpty()) usernamesList.add(player2Field.getText().trim());
                if (!player3Field.getText().trim().isEmpty()) usernamesList.add(player3Field.getText().trim());

                if (usernamesList.isEmpty()) {
                    showStatus(new Result(false, "Please enter at least one username."));
                    return;
                }

                selectedPlayers = usernamesList.toArray(new String[0]);
                playerMapChoices = new ArrayList<>(Collections.nCopies(selectedPlayers.length, -1));
                showPlayerMapSelectionWindow();
            }
        });

        loadGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.loadGame();
                showStatus(result);
                if (result.success()) {
                    // Transition to game screen would go here
                }
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(skin, game, app, client));
                dispose();
            }
        });
    }

    private void showPlayerMapSelectionWindow() {
        if (currentDialog != null) currentDialog.hide();

        Dialog selectionDialog = new Dialog("", skin);
        currentDialog = selectionDialog;
        selectionDialog.getContentTable().pad(20);

        // Add title with colspan
        Table contentTable = selectionDialog.getContentTable();
        Label titleLabel = new Label("Each player choose their preferred map:", skin);
        contentTable.add(titleLabel).colspan(3).row();

        // Create map selection for each player
        for (int i = 0; i < selectedPlayers.length; i++) {
            final int playerIndex = i;
            Label playerLabel = new Label(selectedPlayers[i] + ":", skin);

            // Map 1 button
            TextButton map1Button = new TextButton("Map 1", skin);
            map1Button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    playerMapChoices.set(playerIndex, 1);
                    updateButtonStates(selectionDialog);
                }
            });

            // Map 2 button
            TextButton map2Button = new TextButton("Map 2", skin);
            map2Button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    playerMapChoices.set(playerIndex, 2);
                    updateButtonStates(selectionDialog);
                }
            });

            contentTable.add(playerLabel).padRight(10);
            contentTable.add(map1Button).padRight(20);
            contentTable.add(map2Button).row();
        }

        // Build Map button
        TextButton buildMapButton = new TextButton("Build Map", skin);
        buildMapButton.setDisabled(true);
        buildMapButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectionDialog.hide();
                showCombinedMapPreview();
            }
        });

        // Cancel button
        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                selectionDialog.hide();
                currentDialog = null;
            }
        });

        selectionDialog.getButtonTable().add(cancelButton).padRight(20);
        selectionDialog.getButtonTable().add(buildMapButton).colspan(3).padTop(20);
        selectionDialog.show(stage);
    }

    private void updateButtonStates(Dialog dialog) {
        boolean allSelected = !playerMapChoices.contains(-1);

        // Find the build button (last button in the table)
        Table buttonTable = dialog.getButtonTable();
        TextButton buildButton = (TextButton) buttonTable.getCells().get(buttonTable.getCells().size - 1).getActor();
        buildButton.setDisabled(!allSelected);

        Table contentTable = dialog.getContentTable();
        // Skip the first row (title label)
        for (int i = 1; i < contentTable.getCells().size; i += 3) {
            // Get the buttons (cells at i+1 and i+2 positions)
            if (i+2 >= contentTable.getCells().size) break;

            Actor map1Actor = contentTable.getCells().get(i+1).getActor();
            Actor map2Actor = contentTable.getCells().get(i+2).getActor();

            if (map1Actor instanceof TextButton && map2Actor instanceof TextButton) {
                TextButton map1Btn = (TextButton) map1Actor;
                TextButton map2Btn = (TextButton) map2Actor;

                int playerIndex = (i - 1) / 3; // Calculate player index
                if (playerMapChoices.get(playerIndex) == 1) {
                    map1Btn.setColor(Color.GRAY);
                    map2Btn.setColor(Color.WHITE);
                } else if (playerMapChoices.get(playerIndex) == 2) {
                    map1Btn.setColor(Color.WHITE);
                    map2Btn.setColor(Color.GRAY);
                }
            }
        }
    }

    private void showCombinedMapPreview() {
        if (currentDialog != null) currentDialog.hide();

        Dialog previewDialog = new Dialog("", skin);
        currentDialog = previewDialog;
        previewDialog.getContentTable().pad(20);

        // Add title with colspan
        Table contentTable = previewDialog.getContentTable();
        contentTable.add(new Label("Map Composition:", skin)).colspan(selectedPlayers.length).row();

        // Horizontal layout for maps
        Table mapsTable = new Table();
        for (int i = 0; i < selectedPlayers.length; i++) {
            Texture mapTexture = new Texture(Gdx.files.internal("mapIcons/map" + playerMapChoices.get(i) + ".png"));
            Image mapImage = new Image(mapTexture);

            // Smaller map previews (150x150) arranged horizontally
            mapsTable.add(new Label(selectedPlayers[i], skin)).padRight(10);
            mapsTable.add(mapImage).size(150, 150).padRight(20);
        }

        contentTable.add(mapsTable).colspan(selectedPlayers.length).row();

        // Action buttons
        TextButton startGameButton = new TextButton("Start Game", skin);
        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                previewDialog.hide();
                currentDialog = null;
                startNewGameWithSelectedMaps();
            }
        });

        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                previewDialog.hide();
                currentDialog = null;
                showPlayerMapSelectionWindow();
            }
        });

        previewDialog.getButtonTable().add(cancelButton).padRight(20);
        previewDialog.getButtonTable().add(startGameButton);
        previewDialog.show(stage);
    }

    private void startNewGameWithSelectedMaps() {
        // Convert map choices to string array
        String[] maps = new String[playerMapChoices.size()];
        for (int i = 0; i < playerMapChoices.size(); i++) {
            maps[i] = String.valueOf(playerMapChoices.get(i));
        }

        // First create the game with players
        Result result = controller.gameNew(selectedPlayers);
        showStatus(result);
        if (!result.success()) return;

        // Then set all maps at once
        result = controller.gameMap(maps);
        showStatus(result);

        if (result.success()) {
            // Get the created game instance
            MyGame myGame = App.getCurrentUser().getGame();
            if (myGame != null) {
                // Transition to the actual game screen
                game.setScreen(new GameScreen(skin, game, myGame, new GameController(myGame), app, client));
                dispose();
            } else {
                showStatus(new Result(false, "Failed to start game"));
            }
        }
    }

    private void showStatus(Result result) {
        statusLabel.setText(result.message());
        statusLabel.clearActions();
        statusLabel.addAction(Actions.sequence(
                Actions.delay(3f),
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