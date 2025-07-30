package org.Group34;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.Group34.view.graphic.GraphicAppView;

public class AppLauncher {
    public static void main(String[] args) {
        boolean graphicMode = true;

        if (graphicMode) {
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setTitle("Stardew Valley - Graphic Mode");
            config.setWindowedMode(1600, 900);
            new Lwjgl3Application(new GraphicAppView(), config);
        }
        else {
            Main.main(null);
        }
    }
}

// ---------------------------------------------------------------------------------------------------------------------

//package org.Group34;
//
//import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
//import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
//import org.Group34.controller.WeatherSystem;
//import org.Group34.model.*;
//import org.Group34.model.entities.Player;
//import org.Group34.model.enums.FarmType;
//import org.Group34.model.map.Map;
//import org.Group34.model.map.MapBuilder;
//import org.Group34.view.graphic.GraphicAppView;
//
//import java.util.HashMap;
//
//public class AppLauncher {
//    public static void main(String[] args) {
//        startGameDirectly();
//    }
//
//    private static void startGameDirectly() {
//        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
//        config.setTitle("Stardew Valley");
//        config.setWindowedMode(1600, 900);
//
//        User defaultUser = createDefaultUser();
//        MyGame defaultGame = createDefaultGame(defaultUser);
//
//        // Set current game BEFORE creating the application
//        App.setCurrentUser(defaultUser);
//        App.setCurrentGame(defaultGame);
//
//        // Now create the view which will use the current game
//        new Lwjgl3Application(new GraphicAppView(), config);
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
//        mapBuilder.setFarms(new FarmType[]{FarmType.STANDARD_FARM});
//
//        Map map = mapBuilder.generate();
//
//        return new MyGame(user, players, map, time, weatherSystem);
//    }
//}
