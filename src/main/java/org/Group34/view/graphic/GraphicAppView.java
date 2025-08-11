//package org.Group34.view.graphic;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import org.Group34.network.client.GameClient;
//import org.Group34.view.graphic.menuScreen.LobbyMenuScreen;
//import org.Group34.view.graphic.menuScreen.RegisterScreen;
//
//import java.io.IOException;
//
//public class GraphicAppView extends Game {
//    private GameClient client;
//
//    @Override
//    public void create() {
//        System.out.println("Graphic view started.");
//
//        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
//
//        // Initialize network client
//        try {
//            client = new GameClient("localhost", 12345, this::handleNetworkMessage);
//        } catch (IOException e) {
//            System.err.println("Failed to connect to server: " + e.getMessage());
//            Gdx.app.postRunnable(() -> {
//                Gdx.app.exit();
//            });
//            return;
//        }
//
//        this.setScreen(new RegisterScreen(skin, this, this, client));
//    }
//
//    private void handleNetworkMessage(String message) {
//        Gdx.app.postRunnable(() -> {
//            System.out.println("Received: " + message);
//            // Handle network messages in the main thread
//            if (getScreen() instanceof LobbyMenuScreen) {
//                ((LobbyMenuScreen) getScreen()).handleServerMessage(message);
//            }
//        });
//    }
//
//    @Override
//    public void render() {
//        super.render();
//
//        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
//            Gdx.app.exit();
//        }
//    }
//
//    @Override
//    public void dispose() {
//        System.out.println("Graphic view closed.");
//        super.dispose();
//    }
//}

// ---------------------------------------------------------------------------------------------------------------------

//package org.Group34.view.graphic;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import org.Group34.controller.GameController;
//import org.Group34.controller.WeatherSystem;
//import org.Group34.model.App;
//import org.Group34.model.MyGame;
//import org.Group34.model.Time;
//import org.Group34.model.User;
//import org.Group34.model.entities.Player;
//import org.Group34.model.enums.FarmType;
//import org.Group34.model.map.Map;
//import org.Group34.model.map.MapBuilder;
//import org.Group34.network.client.GameClient;
//import org.Group34.view.graphic.mapScreen.GameScreen;
//import org.Group34.view.graphic.menuScreen.LobbyMenuScreen;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//public class GraphicAppView extends Game {
//    private SpriteBatch batch;
//    private Skin skin;
//
//    @Override
//    public void create() {
//        batch = new SpriteBatch();
//        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
//
//        MyGame currentGame = App.getCurrentGame();
//        if (currentGame == null) {
//            // Handle case where game is null - perhaps create a new one
//            User defaultUser = createDefaultUser();
//            currentGame = createDefaultGame(defaultUser);
//            App.setCurrentGame(currentGame);
//        }
//
//        try {
//            setScreen(new GameScreen(skin, this, currentGame, new GameController(currentGame, new GameClient("localhost", 12345, this::handleNetworkMessage)), this, new GameClient("localhost", 12345, this::handleNetworkMessage)));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void handleNetworkMessage(Object message) {
//        Gdx.app.postRunnable(() -> {
//            System.out.println("Received: " + message);
//            // Handle network messages in the main thread
//            if (getScreen() instanceof LobbyMenuScreen) {
//                ((LobbyMenuScreen) getScreen()).handleServerMessage(message.toString());
//            }
//        });
//    }
//
//    @Override
//    public void dispose() {
//        batch.dispose();
//        skin.dispose();
//    }
//
//    private static User createDefaultUser() {
//        User user = new User("player1", "1234", "Farmer", "farmer@stardew.com", "male");
//        user.setHighestMoney(500);
//        return user;
//    }
//
//    private static MyGame createDefaultGame(User user) {
//        Time time = new Time();
//
//        WeatherSystem weatherSystem = new WeatherSystem();
//        weatherSystem.initializeWeather(time);
//
//        Player player = new Player(new int[]{72, 10});
//        player.setMoney(500);
//
//        HashMap<User, Player> players = new HashMap<>();
//        players.put(user, player);
//
//        MapBuilder mapBuilder = new MapBuilder();
//        mapBuilder.setPlayers(new Player[]{player});
//        mapBuilder.setFarms(new FarmType[]{FarmType.STANDARD_FARM, FarmType.STANDARD_FARM});
//
//        Map map = mapBuilder.generate();
//
//        return new MyGame(user, players, map, time, weatherSystem);
//    }
//}

// ---------------------------------------------------------------------------------------------------------------------

package org.Group34.view.graphic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.Group34.model.App;
import org.Group34.model.User;
import org.Group34.network.client.GameClient;
import org.Group34.view.graphic.mapScreen.GameScreen;
import org.Group34.view.graphic.menuScreen.LobbyMenuScreen;
import org.Group34.view.graphic.menuScreen.RegisterScreen;

import java.io.IOException;

public class GraphicAppView extends Game {
    private Skin skin;
    private GameClient client;
    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        System.out.println("Graphic view started.");

        batch = new SpriteBatch();
        font = new BitmapFont();

        // Load the skin
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        // Initialize network client
        try {
            client = new GameClient("localhost", 12345, this::handleNetworkMessage);
        } catch (IOException e) {
            System.err.println("Failed to connect to server: " + e.getMessage());
            Gdx.app.postRunnable(() -> {
                Gdx.app.exit();
            });
            return;
        }

        // Start with register screen
        setScreen(new RegisterScreen(skin, this, this, client));
        User currentUser = App.getCurrentUser();
        if (currentUser != null) {
            client.sendUser(currentUser);
        }
        //setScreen(new LobbyMenuScreen(skin, this, client,this));
    }

    public void switchToLobbyMenu() {
        setScreen(new LobbyMenuScreen(skin, this, client, this));
    }

    private void handleNetworkMessage(Object message) {
        Gdx.app.postRunnable(() -> {
            if (message != null) {
                System.out.println("Received: " + message.toString());
                // Handle network messages in the main thread
                if (getScreen() instanceof LobbyMenuScreen) {
                    ((LobbyMenuScreen) getScreen()).handleServerMessage((String) message);
                } else if (getScreen() instanceof GameScreen screen) {
                    screen.handleServerInputs(message);
                }
            }
        });
    }

    @Override
    public void render() {
        super.render();
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
        System.out.println("Graphic view closed.");
        if (client != null) {
            client.close();
        }
        if (skin != null) {
            skin.dispose();
        }
        if (batch != null) {
            batch.dispose();
        }
        if (font != null) {
            font.dispose();
        }
        super.dispose();
    }

    // Getters for batch and font
    public SpriteBatch getBatch() {
        return batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    // Getter for skin
    public Skin getSkin() {
        return skin;
    }
}