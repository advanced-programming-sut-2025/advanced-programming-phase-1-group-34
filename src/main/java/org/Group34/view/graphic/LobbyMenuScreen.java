package org.Group34.view.graphic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.Group34.network.client.GameClient;
import org.Group34.view.graphic.menuScreen.RegisterScreen;

public class LobbyMenuScreen implements Screen {
    private final Stage stage;
    private final Skin skin;
    private final Game game;
    private final GraphicAppView app;
    private final GameClient client;

    private Table lobbyTable;
    private TextField lobbyNameField;
    private CheckBox privateCheckbox;
    private Dialog passwordDialog;
    private TextField passwordField;

    public LobbyMenuScreen(Skin skin, GraphicAppView app, GameClient client, Game game) {
        this.skin = skin;
        this.app = app;
        this.game = game;
        this.client = client;
        this.stage = new Stage(new ScreenViewport());

        setupUI();
    }

    private void setupUI() {
        Table mainTable = new Table(skin);
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        // Create Lobby Section
        Label titleLabel = new Label("Lobby Menu", skin, "title");
        lobbyNameField = new TextField("", skin);
        privateCheckbox = new CheckBox(" Private Lobby", skin);
        TextButton createButton = new TextButton("Create Lobby", skin);
        TextButton refreshButton = new TextButton("Refresh List", skin);
        TextButton backButton = new TextButton("Back", skin);

        // Lobby list table
        lobbyTable = new Table(skin);
        ScrollPane scrollPane = new ScrollPane(lobbyTable, skin);

        // Layout
        mainTable.add(titleLabel).colspan(2).padBottom(20).row();

        // Layout
        mainTable.add(titleLabel).colspan(2).padBottom(20).row();
        mainTable.add("Lobby Name:").left().padRight(10);
        mainTable.add(lobbyNameField).width(200).row();
        mainTable.add(privateCheckbox).colspan(2).padBottom(20).row();
        mainTable.add(createButton).colspan(2).padBottom(40).row();
        mainTable.add(scrollPane).colspan(2).size(400, 300).padBottom(20).row();
        mainTable.add(refreshButton).padRight(10);
        mainTable.add(backButton);

        // Button listeners
        createButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createLobby();
            }
        });

        refreshButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                refreshLobbies();
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Return to previous screen
                // You might want to change this based on your navigation flow
                app.setScreen(new RegisterScreen(skin, game, app));
            }
        });

        // Password dialog setup
        passwordDialog = new Dialog("Enter Password", skin);
        passwordField = new TextField("", skin);
        passwordDialog.getContentTable().add(passwordField).width(200);
        passwordDialog.button("Join", true);
        passwordDialog.button("Cancel", false);

        // Initial refresh
        refreshLobbies();

        Gdx.input.setInputProcessor(stage);
    }

    private void createLobby() {
        String name = lobbyNameField.getText();
        if (name.isEmpty()) {
            showMessage("Error", "Please enter a lobby name");
            return;
        }

        String password = privateCheckbox.isChecked() ? "default123" : "";
        client.send("CREATE_LOBBY " + name + ";" + privateCheckbox.isChecked() + ";" + password);
    }

    private void refreshLobbies() {
        lobbyTable.clear();
        client.send("LIST_LOBBIES");

        // Note: In a real app, you'd wait for server response to update this
        // This is just a placeholder with sample data
        addSampleLobby("Farmers United", "public", 2);
        addSampleLobby("Private Farm", "private", 1);
    }

    private void addSampleLobby(String name, String type, int players) {
        TextButton lobbyButton = new TextButton(name + " (" + players + "/4) - " + type, skin);
        lobbyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                joinLobby("sample123", type.equals("private"));
            }
        });
        lobbyTable.add(lobbyButton).width(380).padBottom(5).row();
    }

    private void joinLobby(String lobbyId, boolean isPrivate) {
        if (isPrivate) {
            passwordField.setText(""); // Reset password field
            passwordDialog.clearListeners();
            passwordDialog.getButtonTable().clearChildren();

            // Add buttons with proper handling
            TextButton joinButton = new TextButton("Join", skin);
            joinButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    String password = passwordField.getText();
                    client.send("JOIN_LOBBY " + lobbyId + ";" + password);
                    passwordDialog.hide();
                }
            });

            TextButton cancelButton = new TextButton("Cancel", skin);
            cancelButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    passwordDialog.hide();
                }
            });

            passwordDialog.getButtonTable().add(joinButton).padRight(10);
            passwordDialog.getButtonTable().add(cancelButton);

            passwordDialog.show(stage);
        } else {
            client.send("JOIN_LOBBY " + lobbyId);
        }
    }

    private void showMessage(String title, String message) {
        Dialog dialog = new Dialog(title, skin);
        dialog.text(message);
        dialog.button("OK");
        dialog.show(stage);
    }

    public void handleServerMessage(String message) {
        // Parse and handle server messages here
        System.out.println("Received from server: " + message);

        String[] parts = message.split(";");
        switch (parts[0]) {
            case "LOBBY_CREATED":
                showMessage("Success", "Lobby created with ID: " + parts[1]);
                break;
            case "JOIN_SUCCESS":
                showMessage("Success", "Joined lobby: " + parts[1]);
                break;
            case "LOBBIES_LIST":
                updateLobbyList(parts);
                break;
            // Add more cases as needed
        }
    }

    private void updateLobbyList(String[] lobbyData) {
        lobbyTable.clear();
        for (int i = 1; i < lobbyData.length; i++) {
            String[] lobbyInfo = lobbyData[i].split(",");
            if (lobbyInfo.length >= 3) {
                addSampleLobby(lobbyInfo[1], lobbyInfo[2], Integer.parseInt(lobbyInfo[3]));
            }
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}