//package org.Group34.view.graphic;
//
//import com.badlogic.gdx.Game;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import org.Group34.view.graphic.menuScreen.RegisterScreen;
//
//public class GraphicAppView extends Game {
//
//    @Override
//    public void create() {
//        System.out.println("Graphic view started.");
//
//        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
//
//        this.setScreen(new RegisterScreen(skin, this));
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

package org.Group34.view.graphic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.Group34.controller.GameController;
import org.Group34.controller.WeatherSystem;
import org.Group34.model.App;
import org.Group34.model.MyGame;
import org.Group34.model.Time;
import org.Group34.model.User;
import org.Group34.model.entities.Player;
import org.Group34.model.enums.FarmType;
import org.Group34.model.map.Map;
import org.Group34.model.map.MapBuilder;
import org.Group34.view.graphic.mapScreen.GameScreen;

import java.util.HashMap;

public class GraphicAppView extends Game {
    private SpriteBatch batch;
    private Skin skin;

    @Override
    public void create() {
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        MyGame currentGame = App.getCurrentGame();
        if (currentGame == null) {
            // Handle case where game is null - perhaps create a new one
            User defaultUser = createDefaultUser();
            currentGame = createDefaultGame(defaultUser);
            App.setCurrentGame(currentGame);
        }

        setScreen(new GameScreen(skin, this, currentGame,
                new GameController(currentGame)));
    }

    @Override
    public void dispose() {
        batch.dispose();
        skin.dispose();
    }

    private static User createDefaultUser() {
        User user = new User("player1", "1234", "Farmer", "farmer@stardew.com", "male");
        user.setHighestMoney(500);
        return user;
    }

    private static MyGame createDefaultGame(User user) {
        Time time = new Time();

        WeatherSystem weatherSystem = new WeatherSystem();
        weatherSystem.initializeWeather(time);

        Player player = new Player(new int[]{72, 10});
        player.setMoney(500);

        HashMap<User, Player> players = new HashMap<>();
        players.put(user, player);

        MapBuilder mapBuilder = new MapBuilder();
        mapBuilder.setPlayers(new Player[]{player});
        mapBuilder.setFarms(new FarmType[]{FarmType.STANDARD_FARM});

        Map map = mapBuilder.generate();

        return new MyGame(user, players, map, time, weatherSystem);
    }
}
