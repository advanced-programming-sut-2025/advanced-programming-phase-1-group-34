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
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.Group34.model.App;
import org.Group34.model.Result;
import org.Group34.model.User;
import org.Group34.network.client.GameClient;
import org.Group34.view.graphic.GraphicAppView;

import java.util.*;
import java.util.List;

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
    private Table joinedLobbiesTable;
    private TextField searchField;
    private final List<LobbyInfo> lobbyList = new ArrayList<>();
    private final List<LobbyInfo> joinedLobbies = new ArrayList<>();
    private final List<String> joinedLobbyIds = new ArrayList<>(); // Track joined lobby IDs
    private Timer refreshTimer;
    private User currentUser;
    private Texture lockTexture;
    private Dialog playerListDialog;
    private Dialog allPlayersDialog;

    public LobbyMenuScreen(Skin skin, GraphicAppView app, GameClient client, Game game) {
        this.skin = skin;
        this.app = app;
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        backgroundTexture = new Texture(Gdx.files.internal("menuBackgrounds/background-lobby.png"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        this.client = client;
        this.currentUser = App.getCurrentUser();
        lockTexture = new Texture(Gdx.files.internal("menuIcons/lockIcon.png")); // Load lock icon
        Gdx.input.setInputProcessor(stage);
        createUI();
        // Send current user to server if not already sent
        if (currentUser != null) {
            client.sendUser(currentUser);
        }
        startAutoRefresh();
    }

    private void createUI() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.pad(20); // Add padding to the main table

        // Left section - Search and Join Lobbies
        Table leftSection = new Table();
        leftSection.pad(15);

        // Search section (without search button)
        Label searchLabel = new Label("Search Lobbies:", skin);
        searchLabel.setFontScale(1.1f);
        searchField = new TextField("", skin);
        searchField.setMessageText("Enter lobby ID or name...");

        // Add enter key listener to search field
        searchField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                if (c == '\r' || c == '\n') { // Enter key
                    String searchTerm = searchField.getText().trim();
                    if (!searchTerm.isEmpty()) {
                        client.send("SEARCH_LOBBY " + searchTerm);
                    } else {
                        client.send("GET_LOBBIES");
                    }
                }
            }
        });

        Table searchRow = new Table();
        searchRow.add(searchLabel).padRight(10);
        searchRow.add(searchField).width(300).growX(); // Make search field wider
        leftSection.add(searchRow).padBottom(15).row();

        // Lobby list title
        Label lobbyListLabel = new Label("Available Lobbies", skin);
        lobbyListLabel.setFontScale(1.2f);
        leftSection.add(lobbyListLabel).left().padBottom(10).row();

        // Lobby list
        ScrollPane scrollPane = new ScrollPane(createLobbyListTable(), skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false); // Allow vertical scrolling only
        leftSection.add(scrollPane).grow().row();

        // Refresh button in left section now
        Table buttonsRow = new Table();
        TextButton refreshButton = new TextButton("Refresh", skin);
        refreshButton.pad(0, 15, 8, 15);
        buttonsRow.add(refreshButton).width(250);
        leftSection.add(buttonsRow).padTop(15).row();

        // Add Player's List button
        TextButton playersListButton = new TextButton("Player's List", skin);
        playersListButton.pad(0, 15, 8, 15);
        buttonsRow.add(playersListButton).width(120);

        leftSection.add(buttonsRow).padTop(15).row();

        // Right section - Joined Lobbies
        Table rightSection = new Table();
        rightSection.pad(15);

        // Create Lobby Button
        TextButton createLobbyButton = new TextButton("Create New Lobby", skin);
        createLobbyButton.pad(0, 15, 10, 15);
        rightSection.add(createLobbyButton).width(500).padBottom(15).row();

        // Joined Lobbies title
        Label joinedLobbiesLabel = new Label("Your Lobbies", skin);
        joinedLobbiesLabel.setFontScale(1.2f);
        rightSection.add(joinedLobbiesLabel).left().padBottom(10).row();

        // Joined Lobbies list
        ScrollPane joinedScrollPane = new ScrollPane(createJoinedLobbiesTable(), skin);
        joinedScrollPane.setFadeScrollBars(false);
        joinedScrollPane.setScrollingDisabled(true, false); // Allow vertical scrolling only
        rightSection.add(joinedScrollPane).grow().row();

        // Back to Main Menu button - moved here
        TextButton backButton = new TextButton("Back to Main Menu", skin);
        backButton.pad(0, 15, 8, 15);
        rightSection.add(backButton).width(500).padTop(15);

        // Add sections to main table with spacing
        mainTable.add(leftSection).width(Gdx.graphics.getWidth() * 0.6f).height(Gdx.graphics.getHeight() * 0.8f).padRight(15);
        mainTable.add(rightSection).width(Gdx.graphics.getWidth() * 0.4f).height(Gdx.graphics.getHeight() * 0.8f);

        // Add everything to stage
        stage.addActor(backgroundImage);
        stage.addActor(mainTable);

        // Status label - positioned at the bottom center of the screen
        Table statusTable = new Table();
        statusTable.setFillParent(true);
        statusLabel = new Label("", skin);
        statusLabel.setFontScale(1.1f);
        statusLabel.setColor(Color.RED);
        statusTable.add().expand().row();
        statusTable.add(statusLabel).padBottom(20);

        // Add status table separately to ensure it's on top
        stage.addActor(statusTable);

        // Button listeners
        refreshButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                client.send("GET_LOBBIES");
                showStatus(new Result(true, "Lobby list refreshed"));
            }
        });

        playersListButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                client.send("GET_ALL_PLAYERS");
            }
        });

        createLobbyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showCreateLobbyDialog();
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new RegisterScreen(skin, game, app, client));
                dispose();
            }
        });
    }

    private void showCreateLobbyDialog() {
        Dialog createDialog = new Dialog("Create New Lobby", skin);
        createDialog.pad(20);

        // Lobby Name
        Label nameLabel = new Label("Lobby Name:", skin);
        nameLabel.setFontScale(1.1f);
        TextField nameField = new TextField("", skin);
        nameField.setMessageText("Enter lobby name");
        Table nameRow = new Table();
        nameRow.add(nameLabel).padRight(15);
        nameRow.add(nameField).width(250);
        createDialog.getContentTable().add(nameRow).pad(15).row();

        // Private Lobby
        CheckBox privateField = new CheckBox("Private Lobby", skin);
        privateField.setChecked(false);
        privateField.getImageCell().padRight(8);
        createDialog.getContentTable().add(privateField).left().pad(10).row();

        // Visible Lobby
        CheckBox visibleField = new CheckBox("Visible Lobby", skin);
        visibleField.setChecked(true);
        visibleField.getImageCell().padRight(8);
        createDialog.getContentTable().add(visibleField).left().pad(10).row();

        // Password (initially hidden)
        Label passwordLabel = new Label("Password:", skin);
        passwordLabel.setFontScale(1.1f);
        TextField passwordField = new TextField("", skin);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);
        passwordField.setVisible(false);
        Table passwordRow = new Table();
        passwordRow.add(passwordLabel).padRight(15);
        passwordRow.add(passwordField).width(250);
        createDialog.getContentTable().add(passwordRow).pad(15).row();

        // Show/hide password field based on private checkbox
        privateField.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                passwordField.setVisible(privateField.isChecked());
            }
        });

        // Buttons
        TextButton createButton = new TextButton("Create", skin);
        createButton.pad(10, 20, 10, 20);
        TextButton cancelButton = new TextButton("Cancel", skin);
        cancelButton.pad(10, 20, 10, 20);

        createButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String lobbyName = nameField.getText().trim();
                if (lobbyName.isEmpty()) {
                    showStatus(new Result(false, "Please enter a lobby name"));
                    return;
                }
                boolean isPrivate = privateField.isChecked();
                boolean isVisible = visibleField.isChecked();
                String password = isPrivate ? passwordField.getText().trim() : "";
                if (isPrivate && password.isEmpty()) {
                    showStatus(new Result(false, "Please enter a password for private lobby"));
                    return;
                }
                client.send("CREATE_LOBBY " + lobbyName + " " + isPrivate + " " + isVisible + " " + password);
                createDialog.hide();
            }
        });

        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                createDialog.hide();
            }
        });

        createDialog.getButtonTable().add(cancelButton).padRight(15);
        createDialog.getButtonTable().add(createButton);
        createDialog.show(stage);
    }

    private Table createLobbyListTable() {
        lobbyListTable = new Table();
        lobbyListTable.top();

        // Header row with lock icon
        lobbyListTable.add().width(25); // Space for lock icon
        lobbyListTable.add(new Label("ID", skin)).width(125).pad(10);
        lobbyListTable.add(new Label("Name", skin)).width(220).pad(5);
        lobbyListTable.add(new Label("Players", skin)).width(150).pad(5);
        lobbyListTable.add(new Label("Action", skin)).width(200).pad(5);
        lobbyListTable.row();

        // Add separator with better styling
        lobbyListTable.add(new Label("", skin)).width(25).pad(5);
        lobbyListTable.add(new Label("------", skin)).width(125).pad(10);
        lobbyListTable.add(new Label("----------------", skin)).width(220).pad(5);
        lobbyListTable.add(new Label("---------", skin)).width(150).pad(5);
        lobbyListTable.add(new Label("-----------", skin)).width(200).pad(5);
        lobbyListTable.row();

        // Add some space after header
        lobbyListTable.add().colspan(5).height(10);
        lobbyListTable.row();

        // Populate with current lobby list
        updateLobbyList();

        return lobbyListTable;
    }

    private Table createJoinedLobbiesTable() {
        joinedLobbiesTable = new Table();
        joinedLobbiesTable.top();

        // Header row with lock icon
        joinedLobbiesTable.add().width(25); // Space for lock icon
        joinedLobbiesTable.add(new Label("ID", skin)).width(100).pad(5);
        joinedLobbiesTable.add(new Label("Name", skin)).width(160).pad(5);
        joinedLobbiesTable.add(new Label("Players", skin)).width(100).pad(5);
        joinedLobbiesTable.add(new Label("Action", skin)).width(120).pad(5);
        joinedLobbiesTable.row();

        // Add separator with better styling
        joinedLobbiesTable.add(new Label("", skin)).width(25).pad(5);
        joinedLobbiesTable.add(new Label("---", skin)).width(70).pad(5);
        joinedLobbiesTable.add(new Label("-------------", skin)).width(160).pad(5);
        joinedLobbiesTable.add(new Label("---------", skin)).width(100).pad(5);
        joinedLobbiesTable.add(new Label("--------", skin)).width(120).pad(5);
        joinedLobbiesTable.row();

        // Add some space after header
        joinedLobbiesTable.add().colspan(5).height(10);
        joinedLobbiesTable.row();

        // Populate with joined lobbies
        updateJoinedLobbiesList();
        return joinedLobbiesTable;
    }

    private void updateLobbyList() {
        // Clear all cells except the header (first 3 rows)
        for (int i = lobbyListTable.getCells().size - 1; i >= 15; i--) {
            lobbyListTable.removeActor(lobbyListTable.getCells().get(i).getActor());
            lobbyListTable.getCells().removeIndex(i);
        }

        // Check if we're in search mode
        String searchTerm = searchField.getText().trim();
        boolean isSearchMode = !searchTerm.isEmpty();

        // Add lobby rows
        for (LobbyInfo lobby : lobbyList) {
            // Only show visible lobbies OR invisible lobbies with exact name match
            if (!lobby.isVisible && !isSearchMode) {
                continue; // Skip invisible lobbies when not searching
            }
            if (!lobby.isVisible && isSearchMode && !lobby.name.equalsIgnoreCase(searchTerm)) {
                continue; // Skip invisible lobbies that don't match search term exactly
            }

            // Add some space between rows
            lobbyListTable.add().colspan(5).height(5);
            lobbyListTable.row();

            // Lock icon for private lobbies
            if (lobby.isPrivate) {
                Image lockImage = new Image(lockTexture);
                lockImage.setScaling(Scaling.fit);
                lobbyListTable.add(lockImage).size(18, 18).padRight(5);
            } else {
                lobbyListTable.add().width(25); // Empty space for alignment
            }

            // ID with better styling
            Label idLabel = new Label(lobby.id, skin);
            idLabel.setFontScale(0.95f);
            lobbyListTable.add(idLabel).width(125).pad(10);

            // Lobby name with color based on visibility
            Label nameLabel = new Label(lobby.name, skin);
            nameLabel.setFontScale(1.0f);
            nameLabel.setColor(lobby.isVisible ? Color.WHITE : Color.RED);
            lobbyListTable.add(nameLabel).width(220).pad(5);

            // Player count button
            TextButton playerButton = new TextButton(lobby.players + "/" + lobby.maxPlayers, skin);
            playerButton.pad(8, 15, 8, 15);
            playerButton.setUserObject(lobby.id); // Store lobby ID for later use
            playerButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    client.send("GET_PLAYERS " + lobby.id);
                }
            });
            lobbyListTable.add(playerButton).width(120).pad(5);

            // Action button - Join or Leave based on whether user is in lobby
            final String lobbyId = lobby.id;
            boolean isJoined = joinedLobbyIds.contains(lobbyId);

            if (isJoined) {
                // User is in this lobby - show Leave button
                TextButton leaveButton = new TextButton("Leave", skin);
                leaveButton.pad(8, 15, 8, 15);
                leaveButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (currentUser == null) {
                            showStatus(new Result(false, "User not logged in"));
                            return;
                        }
                        client.send("LEAVE_LOBBY " + lobbyId);
                    }
                });
                lobbyListTable.add(leaveButton).width(170).pad(5);
            } else {
                // User is not in this lobby - show Join button
                TextButton joinButton = new TextButton("Join", skin);
                joinButton.pad(8, 15, 8, 15);
                joinButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (currentUser == null) {
                            showStatus(new Result(false, "User not logged in"));
                            return;
                        }
                        if (lobby.isPrivate) {
                            showPasswordDialog(lobbyId);
                        } else {
                            client.send("JOIN_LOBBY " + lobbyId);
                        }
                    }
                });
                lobbyListTable.add(joinButton).width(110).pad(5);
            }

            lobbyListTable.row();
        }
    }

    private void updateJoinedLobbiesList() {
        // Clear all cells except the header (first 3 rows)
        for (int i = joinedLobbiesTable.getCells().size - 1; i >= 15; i--) {
            joinedLobbiesTable.removeActor(joinedLobbiesTable.getCells().get(i).getActor());
            joinedLobbiesTable.getCells().removeIndex(i);
        }

        // Add joined lobby rows
        for (LobbyInfo lobby : joinedLobbies) {
            // Add some space between rows
            joinedLobbiesTable.add().colspan(5).height(5);
            joinedLobbiesTable.row();

            // Lock icon for private lobbies
            if (lobby.isPrivate) {
                Image lockImage = new Image(lockTexture);
                lockImage.setScaling(Scaling.fit);
                joinedLobbiesTable.add(lockImage).size(18, 18).padRight(5);
            } else {
                joinedLobbiesTable.add().width(25); // Empty space for alignment
            }

            // ID with better styling
            Label idLabel = new Label(lobby.id, skin);
            idLabel.setFontScale(0.95f);
            joinedLobbiesTable.add(idLabel).width(70).pad(5); // Adjusted width

            // Lobby name with color based on visibility
            Label nameLabel = new Label(lobby.name, skin);
            nameLabel.setFontScale(1.0f);
            nameLabel.setColor(lobby.isVisible ? Color.WHITE : Color.RED);
            joinedLobbiesTable.add(nameLabel).width(160).pad(5);

            // Player count button
            TextButton playerButton = new TextButton(lobby.players + "/" + lobby.maxPlayers, skin);
            playerButton.pad(8, 15, 8, 15);
            playerButton.setUserObject(lobby.id); // Store lobby ID for later use
            playerButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    client.send("GET_PLAYERS " + lobby.id);
                }
            });
            joinedLobbiesTable.add(playerButton).width(90).pad(5);

            // Start button for admin, Leave button for others
            if (lobby.isAdmin) {
                TextButton startButton = new TextButton("Start", skin);
                startButton.pad(8, 15, 8, 15);
                startButton.setColor(Color.GREEN); // Make start button green
                final String lobbyId = lobby.id;
                startButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        client.send("START_GAME " + lobbyId);
                    }
                });
                joinedLobbiesTable.add(startButton).width(150).pad(5); // Adjusted width
            } else {
                TextButton leaveButton = new TextButton("Leave", skin);
                leaveButton.pad(8, 15, 8, 15);
                final String lobbyId = lobby.id;
                leaveButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        client.send("LEAVE_LOBBY " + lobbyId);
                    }
                });
                joinedLobbiesTable.add(leaveButton).width(150).pad(5); // Adjusted width
            }

            joinedLobbiesTable.row();
        }
    }

    private void showPasswordDialog(String lobbyId) {
        Dialog passwordDialog = new Dialog("Enter Password", skin);
        passwordDialog.pad(20);

        TextField passwordInput = new TextField("", skin);
        passwordInput.setPasswordCharacter('*');
        passwordInput.setPasswordMode(true);
        passwordDialog.getContentTable().add(passwordInput).width(250).pad(20);

        TextButton submitButton = new TextButton("Join", skin);
        submitButton.pad(10, 20, 10, 20);
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
        cancelButton.pad(10, 20, 10, 20);
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                passwordDialog.hide();
            }
        });

        passwordDialog.getButtonTable().add(cancelButton).padRight(15);
        passwordDialog.getButtonTable().add(submitButton);
        passwordDialog.show(stage);
    }

    private void showPlayerListDialog(String lobbyId, String adminUsername, List<String> players) {
        // Close existing dialog if open
        if (playerListDialog != null) {
            playerListDialog.hide();
        }

        playerListDialog = new Dialog("Players in Lobby " + lobbyId, skin);
        playerListDialog.pad(20);

        // Create table for player list
        Table playerTable = new Table();
        playerTable.top();

        // Get the default font from the skin instead of trying to get a specific named font
        Label.LabelStyle adminStyle = new Label.LabelStyle(skin.get(Label.LabelStyle.class));
        adminStyle.fontColor = Color.YELLOW; // Highlight admin

        // Add admin at the top with special style
        Label adminLabel = new Label(adminUsername + " (Admin)", adminStyle);
        playerTable.add(adminLabel).left().pad(5).row();

        // Add separator
        playerTable.add(new Label("------------------------", skin)).left().pad(5).row();

        // Add other players
        for (String player : players) {
            if (!player.equals(adminUsername)) { // Skip admin since already added
                Label playerLabel = new Label(player, skin);
                playerTable.add(playerLabel).left().pad(5).row();
            }
        }

        ScrollPane scrollPane = new ScrollPane(playerTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        playerListDialog.getContentTable().add(scrollPane).width(300).maxHeight(200);

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.pad(10, 20, 10, 20);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                playerListDialog.hide();
            }
        });

        playerListDialog.getButtonTable().add(closeButton).padTop(10);
        playerListDialog.show(stage);
    }

    private void showAllPlayersDialog(Map<String, String> playerLobbyMap) {
        if (allPlayersDialog != null) {
            allPlayersDialog.hide();
        }

        allPlayersDialog = new Dialog("All Online Players", skin);
        allPlayersDialog.pad(20);

        // Create table for player list
        Table playerTable = new Table();
        playerTable.top();

        // Header row
        playerTable.add(new Label("Player Name", skin)).width(200).pad(5);
        playerTable.add(new Label("Status", skin)).width(200).pad(5);
        playerTable.row();

        // Add separator
        playerTable.add(new Label("----------------", skin)).width(200).pad(5);
        playerTable.add(new Label("----------------", skin)).width(200).pad(5);
        playerTable.row();

        // Add players
        for (Map.Entry<String, String> entry : playerLobbyMap.entrySet()) {
            String playerName = entry.getKey();
            String lobbyId = entry.getValue();

            playerTable.add(new Label(playerName, skin)).left().pad(5);

            // Show lobby name if in a lobby, otherwise show "Not in lobby"
            String status = "Not in lobby";
            if (!lobbyId.equals("Not in lobby")) {
                // Find lobby name
                for (LobbyInfo lobby : lobbyList) {
                    if (lobby.id.equals(lobbyId)) {
                        status = "In lobby: " + lobby.name;
                        break;
                    }
                }
                // Also check joined lobbies in case it's not in the main list
                for (LobbyInfo lobby : joinedLobbies) {
                    if (lobby.id.equals(lobbyId)) {
                        status = "In lobby: " + lobby.name;
                        break;
                    }
                }
            }

            playerTable.add(new Label(status, skin)).left().pad(5);
            playerTable.row();
        }

        ScrollPane scrollPane = new ScrollPane(playerTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        allPlayersDialog.getContentTable().add(scrollPane).width(420).maxHeight(300);

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.pad(10, 20, 10, 20);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                allPlayersDialog.hide();
            }
        });

        allPlayersDialog.getButtonTable().add(closeButton).padTop(10);
        allPlayersDialog.show(stage);
    }

    public void handleServerMessage(String message) {
        Gdx.app.postRunnable(() -> {
            if (message.startsWith("LOBBY_LIST:")) {
                updateLobbyListFromServer(message.substring("LOBBY_LIST:".length()));
            }
            else if (message.startsWith("JOINED_LOBBY:")) {
                String lobbyId = message.substring("JOINED_LOBBY:".length());
                showStatus(new Result(true, "Joined lobby: " + lobbyId));

                // Find the lobby in the lobby list
                for (LobbyInfo lobby : lobbyList) {
                    if (lobby.id.equals(lobbyId)) {
                        // Check if current user is the creator
                        boolean isAdmin = currentUser != null && currentUser.getUsername().equals(lobby.adminUsername);
                        LobbyInfo joinedLobby = new LobbyInfo(
                                lobby.id, lobby.name, lobby.players, lobby.maxPlayers,
                                lobby.isPrivate, lobby.isVisible, lobby.adminUsername, isAdmin
                        );

                        // Add to joined lobbies if not already there
                        if (!joinedLobbyIds.contains(lobbyId)) {
                            joinedLobbies.add(joinedLobby);
                            joinedLobbyIds.add(lobbyId);
                        }

                        updateJoinedLobbiesList();
                        updateLobbyList();
                        break;
                    }
                }
            }
            else if (message.startsWith("LEFT_LOBBY:")) {
                String lobbyId = message.substring("LEFT_LOBBY:".length());
                showStatus(new Result(true, "Left lobby: " + lobbyId));

                // Remove from joined lobbies
                joinedLobbies.removeIf(lobby -> lobby.id.equals(lobbyId));
                joinedLobbyIds.remove(lobbyId);

                // Update both lists
                updateJoinedLobbiesList();
                updateLobbyList();

                // Refresh lobby list from server to get updated admin info
                client.send("GET_LOBBIES");
            }
            else if (message.startsWith("LOBBY_CREATED:")) {
                String lobbyId = message.substring("LOBBY_CREATED:".length());
                showStatus(new Result(true, "Lobby created with ID: " + lobbyId));

                // Don't create a hardcoded "New Lobby" entry
                // Instead, just add the lobby ID to joined lobbies and refresh
                if (!joinedLobbyIds.contains(lobbyId)) {
                    joinedLobbyIds.add(lobbyId);
                }

                // Request updated lobby list from server
                client.send("GET_LOBBIES");
            }
            else if (message.startsWith("GAME_STARTED:")) {
                String lobbyId = message.substring("GAME_STARTED:".length());
                showStatus(new Result(true, "Game starting in lobby: " + lobbyId));
                game.setScreen(new GameMenuScreen(skin, game, app, client));
            }
            else if (message.startsWith("PLAYER_LIST:")) {
                String[] parts = message.split(":", 3);
                if (parts.length >= 3) {
                    String lobbyId = parts[1];
                    String playerData = parts[2];
                    String[] playerArray = playerData.split(",");
                    if (playerArray.length > 0) {
                        String adminUsername = playerArray[0];
                        List<String> players = new ArrayList<>();
                        for (int i = 1; i < playerArray.length; i++) {
                            players.add(playerArray[i]);
                        }
                        showPlayerListDialog(lobbyId, adminUsername, players);
                    }
                }
            }
            else if (message.startsWith("ALL_PLAYERS:")) {
                // Handle the all players response
                String playersData = message.substring("ALL_PLAYERS:".length());
                if (!playersData.isEmpty()) {
                    Map<String, String> playerLobbyMap = new LinkedHashMap<>();
                    String[] playerEntries = playersData.split("\\|");
                    for (String entry : playerEntries) {
                        String[] parts = entry.split(",");
                        if (parts.length == 2) {
                            playerLobbyMap.put(parts[0], parts[1]);
                        }
                    }
                    showAllPlayersDialog(playerLobbyMap);
                } else {
                    showStatus(new Result(false, "No players online"));
                }
            }
            else if (message.startsWith("ERROR:")) {
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
            if (parts.length >= 7) { // Now includes admin username
                String id = parts[0];
                String name = parts[1];
                int currentPlayers = Integer.parseInt(parts[2]);
                int maxPlayers = Integer.parseInt(parts[3]);
                boolean isPrivate = Boolean.parseBoolean(parts[4]);
                boolean isVisible = Boolean.parseBoolean(parts[5]);
                String adminUsername = parts[6];
                // Create lobby info
                LobbyInfo lobbyInfo = new LobbyInfo(id, name, currentPlayers, maxPlayers, isPrivate, isVisible, adminUsername);
                lobbyList.add(lobbyInfo);
                // If this lobby is in joinedLobbyIds, update or add it to joinedLobbies
                if (joinedLobbyIds.contains(id)) {
                    boolean isAdmin = currentUser != null && currentUser.getUsername().equals(adminUsername);
                    // Check if lobby already exists in joinedLobbies
                    boolean found = false;
                    for (LobbyInfo joinedLobby : joinedLobbies) {
                        if (joinedLobby.id.equals(id)) {
                            // Update existing lobby
                            joinedLobby.name = name;
                            joinedLobby.players = currentPlayers;
                            joinedLobby.maxPlayers = maxPlayers;
                            joinedLobby.isPrivate = isPrivate;
                            joinedLobby.isVisible = isVisible;
                            joinedLobby.adminUsername = adminUsername;
                            joinedLobby.isAdmin = isAdmin;
                            found = true;
                            break;
                        }
                    }
                    // If not found, add new entry
                    if (!found) {
                        joinedLobbies.add(new LobbyInfo(id, name, currentPlayers, maxPlayers, isPrivate, isVisible, adminUsername, isAdmin));
                    }
                }
            }
        }

        // Remove lobbies from joinedLobbies that are no longer in the server lobby list
        // This handles the case where the server has removed a lobby (e.g., single-player inactive lobby)
        Iterator<LobbyInfo> iterator = joinedLobbies.iterator();
        while (iterator.hasNext()) {
            LobbyInfo joinedLobby = iterator.next();
            boolean foundInServerList = false;
            for (LobbyInfo serverLobby : lobbyList) {
                if (joinedLobby.id.equals(serverLobby.id)) {
                    foundInServerList = true;
                    break;
                }
            }
            if (!foundInServerList) {
                // Remove from joinedLobbyIds as well
                joinedLobbyIds.remove(joinedLobby.id);
                iterator.remove();
            }
        }

        // Also remove any lobbies that have 0 players or only 1 player (for safety)
        checkForEmptyLobbies();

        updateLobbyList();
        updateJoinedLobbiesList();
    }

    private void checkForEmptyLobbies() {
        // Check for empty lobbies in joinedLobbies (only remove if 0 players)
        Iterator<LobbyInfo> iterator = joinedLobbies.iterator();
        while (iterator.hasNext()) {
            LobbyInfo lobby = iterator.next();
            if (lobby.players <= 0) {  // Only remove if no players
                // Remove from joinedLobbyIds as well
                joinedLobbyIds.remove(lobby.id);
                iterator.remove();
            }
        }

        // Check for empty lobbies in lobbyList (only remove if 0 players)
        iterator = lobbyList.iterator();
        while (iterator.hasNext()) {
            LobbyInfo lobby = iterator.next();
            if (lobby.players <= 0) {  // Only remove if no players
                iterator.remove();
            }
        }
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
        }, 10000, 10000);
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
    public void dispose() {
        if (refreshTimer != null) {
            refreshTimer.cancel();
        }
        if (client != null) {
            client.close();
        }
        if (lockTexture != null) {
            lockTexture.dispose();
        }
        stage.dispose();
        backgroundTexture.dispose();
    }

    private static class LobbyInfo {
        final String id;
        String name;
        int players;
        int maxPlayers;
        boolean isPrivate;
        boolean isVisible;
        String adminUsername;
        boolean isAdmin;

        // Constructor for lobbies from server
        LobbyInfo(String id, String name, int players, int maxPlayers, boolean isPrivate, boolean isVisible, String adminUsername) {
            this.id = id;
            this.name = name;
            this.players = players;
            this.maxPlayers = maxPlayers;
            this.isPrivate = isPrivate;
            this.isVisible = isVisible;
            this.adminUsername = adminUsername;
            this.isAdmin = false; // Will be set when adding to joined lobbies
        }

        // Constructor for joined lobbies
        LobbyInfo(String id, String name, int players, int maxPlayers, boolean isPrivate, boolean isVisible, String adminUsername, boolean isAdmin) {
            this.id = id;
            this.name = name;
            this.players = players;
            this.maxPlayers = maxPlayers;
            this.isPrivate = isPrivate;
            this.isVisible = isVisible;
            this.adminUsername = adminUsername;
            this.isAdmin = isAdmin;
        }
    }
}