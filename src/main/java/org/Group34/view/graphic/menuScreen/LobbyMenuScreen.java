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
import org.Group34.model.Result;
import org.Group34.network.client.GameClient;
import org.Group34.view.graphic.GraphicAppView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LobbyMenuScreen extends ScreenAdapter {
    private final Stage stage;
    private final Skin skin;
    private final Game game;
    private final Texture backgroundTexture;
    private final Image backgroundImage;
    private Label statusLabel;
    private final GraphicAppView app;
    private GameClient client;
    private Table lobbyListTable;
    private TextField searchField;
    private TextField lobbyNameField;
    private TextField passwordField;
    private CheckBox privateCheckBox;
    private final List<LobbyInfo> lobbyList = new ArrayList<>();
    private Timer refreshTimer;

    public LobbyMenuScreen(Skin skin, Game game, GraphicAppView app) {
        this.skin = skin;
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.app = app;
        backgroundTexture = new Texture(Gdx.files.internal("menuBackgrounds/background-lobby.png"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        Gdx.input.setInputProcessor(stage);

        // Initialize client connection
        try {
            client = new GameClient("localhost", 12345, this::handleServerMessage);
            client.send("GET_LOBBIES");
        } catch (Exception e) {
            System.err.println("Failed to connect to server: " + e.getMessage());
        }

        createUI();
        startAutoRefresh();
    }

    private void createUI() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        // Left section - Search and Join Lobbies
        Table leftSection = new Table();
        leftSection.pad(20);

        Label searchLabel = new Label("Search Lobbies:", skin);
        searchField = new TextField("", skin);
        searchField.setMessageText("Enter lobby ID or name...");

        TextButton searchButton = new TextButton("Search", skin);
        TextButton refreshButton = new TextButton("Refresh", skin);

        Table searchRow = new Table();
        searchRow.add(searchLabel).padRight(10);
        searchRow.add(searchField).width(200).padRight(10);
        searchRow.add(searchButton).padRight(10);
        searchRow.add(refreshButton);

        leftSection.add(searchRow).padBottom(20).row();

        // Lobby list
        Label lobbyListLabel = new Label("Available Lobbies:", skin);
        leftSection.add(lobbyListLabel).left().padBottom(10).row();

        ScrollPane scrollPane = new ScrollPane(createLobbyListTable(), skin);
        scrollPane.setFadeScrollBars(false);
        leftSection.add(scrollPane).grow().row();

        // Right section - Create New Lobby
        Table rightSection = new Table();
        rightSection.pad(20);

        Label createLabel = new Label("Create New Lobby", skin);
        createLabel.setFontScale(1.2f);
        rightSection.add(createLabel).padBottom(20).row();

        Label nameLabel = new Label("Lobby Name:", skin);
        lobbyNameField = new TextField("", skin);
        lobbyNameField.setMessageText("Enter lobby name");

        Table nameRow = new Table();
        nameRow.add(nameLabel).padRight(10);
        nameRow.add(lobbyNameField).width(250);
        rightSection.add(nameRow).padBottom(15).row();

        privateCheckBox = new CheckBox("Private Lobby", skin);
        rightSection.add(privateCheckBox).left().padBottom(10).row();

        Label passwordLabel = new Label("Password:", skin);
        passwordField = new TextField("", skin);
        passwordField.setMessageText("Enter password");
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        passwordField.setVisible(false);

        Table passwordRow = new Table();
        passwordRow.add(passwordLabel).padRight(10);
        passwordRow.add(passwordField).width(250);
        rightSection.add(passwordRow).padBottom(20).row();

        TextButton createButton = new TextButton("Create Lobby", skin);
        rightSection.add(createButton).width(150).padBottom(20).row();

        // Back button
        TextButton backButton = new TextButton("Back to Main Menu", skin);
        rightSection.add(backButton).padTop(50);

        // Add sections to main table
        mainTable.add(leftSection).width(Gdx.graphics.getWidth() * 0.6f).height(Gdx.graphics.getHeight() * 0.8f);
        mainTable.add(rightSection).width(Gdx.graphics.getWidth() * 0.4f).height(Gdx.graphics.getHeight() * 0.8f);

        // Status label
        statusLabel = new Label("", skin);
        statusLabel.setColor(Color.RED);
        mainTable.add(statusLabel).colspan(2).padTop(20);

        // Add everything to stage
        stage.addActor(backgroundImage);
        stage.addActor(mainTable);

        // Button listeners
        searchButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String searchTerm = searchField.getText().trim();
                if (!searchTerm.isEmpty()) {
                    client.send("SEARCH_LOBBY " + searchTerm);
                } else {
                    client.send("GET_LOBBIES");
                }
            }
        });

        refreshButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                client.send("GET_LOBBIES");
                showStatus(new Result(true, "Lobby list refreshed"));
            }
        });

        privateCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                passwordField.setVisible(privateCheckBox.isChecked());
            }
        });

        createButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String lobbyName = lobbyNameField.getText().trim();
                if (lobbyName.isEmpty()) {
                    showStatus(new Result(false, "Please enter a lobby name"));
                    return;
                }

                boolean isPrivate = privateCheckBox.isChecked();
                String password = isPrivate ? passwordField.getText().trim() : "";

                if (isPrivate && password.isEmpty()) {
                    showStatus(new Result(false, "Please enter a password for private lobby"));
                    return;
                }

                client.send("CREATE_LOBBY " + lobbyName + " " + isPrivate + " " + password);
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(skin, game, app));
                dispose();
            }
        });
    }

    private Table createLobbyListTable() {
        lobbyListTable = new Table();
        lobbyListTable.top();

        // Header row
        lobbyListTable.add(new Label("ID", skin)).width(80).pad(5);
        lobbyListTable.add(new Label("Name", skin)).width(150).pad(5);
        lobbyListTable.add(new Label("Players", skin)).width(100).pad(5);
        lobbyListTable.add(new Label("Type", skin)).width(100).pad(5);
        lobbyListTable.add(new Label("Action", skin)).width(100).pad(5);
        lobbyListTable.row();

        // Add separator
        lobbyListTable.add(new Label("----------", skin)).width(80).pad(5);
        lobbyListTable.add(new Label("----------------", skin)).width(150).pad(5);
        lobbyListTable.add(new Label("----------", skin)).width(100).pad(5);
        lobbyListTable.add(new Label("----------", skin)).width(100).pad(5);
        lobbyListTable.add(new Label("----------", skin)).width(100).pad(5);
        lobbyListTable.row();

        // Populate with current lobby list
        updateLobbyList();

        return lobbyListTable;
    }

    private void updateLobbyList() {
        // Clear existing cells (except header)
        if (lobbyListTable.getCells().size > 10) { // Header has 10 cells (5 columns * 2 rows)
            // Remove all cells except the header
            for (int i = lobbyListTable.getCells().size - 1; i >= 10; i--) {
                lobbyListTable.removeActor(lobbyListTable.getCells().get(i).getActor());
                lobbyListTable.getCells().removeIndex(i);
            }
        }

        // Add lobby rows
        for (LobbyInfo lobby : lobbyList) {
            lobbyListTable.add(new Label(lobby.id, skin)).width(80).pad(5);
            lobbyListTable.add(new Label(lobby.name, skin)).width(150).pad(5);
            lobbyListTable.add(new Label(lobby.players + "/" + lobby.maxPlayers, skin)).width(100).pad(5);
            lobbyListTable.add(new Label(lobby.isPrivate ? "Private" : "Public", skin)).width(100).pad(5);

            TextButton joinButton = new TextButton("Join", skin);
            final String lobbyId = lobby.id; // Create a final copy for the listener
            joinButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (lobby.isPrivate) {
                        showPasswordDialog(lobbyId);
                    } else {
                        client.send("JOIN_LOBBY " + lobbyId);
                    }
                }
            });

            lobbyListTable.add(joinButton).width(100).pad(5);
            lobbyListTable.row();
        }
    }

    private void showPasswordDialog(String lobbyId) {
        Dialog passwordDialog = new Dialog("Enter Password", skin);
        TextField passwordInput = new TextField("", skin);
        passwordInput.setPasswordCharacter('*');
        passwordInput.setPasswordMode(true);

        passwordDialog.getContentTable().add(passwordInput).width(200).pad(20);

        TextButton submitButton = new TextButton("Join", skin);
        submitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String password = passwordInput.getText().trim();
                if (!password.isEmpty()) {
                    client.send("JOIN_LOBBY " + lobbyId + " " + password);
                    passwordDialog.hide();
                }
            }
        });

        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                passwordDialog.hide();
            }
        });

        passwordDialog.getButtonTable().add(cancelButton).padRight(10);
        passwordDialog.getButtonTable().add(submitButton);
        passwordDialog.show(stage);
    }

    private void handleServerMessage(String message) {
        Gdx.app.postRunnable(() -> {
            if (message.startsWith("LOBBY_LIST:")) {
                updateLobbyListFromServer(message.substring("LOBBY_LIST:".length()));
            } else if (message.startsWith("LOBBY_CREATED:")) {
                String lobbyId = message.substring("LOBBY_CREATED:".length());
                showStatus(new Result(true, "Lobby created with ID: " + lobbyId));
                client.send("GET_LOBBIES");
            } else if (message.startsWith("JOINED_LOBBY:")) {
                String lobbyId = message.substring("JOINED_LOBBY:".length());
                showStatus(new Result(true, "Joined lobby: " + lobbyId));
                // Transition to game lobby screen would go here
            } else if (message.startsWith("ERROR:")) {
                String errorMsg = message.substring("ERROR:".length());
                showStatus(new Result(false, errorMsg));
            }
        });
    }

    private void updateLobbyListFromServer(String lobbyData) {
        lobbyList.clear();
        String[] lobbies = lobbyData.split("\\|");

        for (String lobby : lobbies) {
            if (lobby.isEmpty()) continue;

            String[] parts = lobby.split(",");
            if (parts.length >= 5) {
                String id = parts[0];
                String name = parts[1];
                int currentPlayers = Integer.parseInt(parts[2]);
                int maxPlayers = Integer.parseInt(parts[3]);
                boolean isPrivate = Boolean.parseBoolean(parts[4]);

                lobbyList.add(new LobbyInfo(id, name, currentPlayers, maxPlayers, isPrivate));
            }
        }

        updateLobbyList();
    }

    private void startAutoRefresh() {
        refreshTimer = new Timer();
        refreshTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (client != null) {
                    client.send("GET_LOBBIES");
                }
            }
        }, 10000, 10000); // Refresh every 10 seconds
    }

    private void showStatus(Result result) {
        statusLabel.setText(result.message());
        statusLabel.setColor(result.success() ? Color.GREEN : Color.RED);
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
        if (refreshTimer != null) {
            refreshTimer.cancel();
        }
        if (client != null) {
            client.close();
        }
        stage.dispose();
        backgroundTexture.dispose();
    }

    private static class LobbyInfo {
        final String id;
        final String name;
        final int players;
        final int maxPlayers;
        final boolean isPrivate;

        LobbyInfo(String id, String name, int players, int maxPlayers, boolean isPrivate) {
            this.id = id;
            this.name = name;
            this.players = players;
            this.maxPlayers = maxPlayers;
            this.isPrivate = isPrivate;
        }
    }
}