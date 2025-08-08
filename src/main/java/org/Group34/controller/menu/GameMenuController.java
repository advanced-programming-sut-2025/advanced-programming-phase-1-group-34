package org.Group34.controller.menu;

import org.Group34.controller.WeatherSystem;
import org.Group34.model.*;
import org.Group34.model.Time;
import org.Group34.model.map.Map;
import org.Group34.model.map.MapBuilder;
import org.Group34.model.entities.Player;
import org.Group34.model.enums.FarmType;
import org.Group34.model.enums.Menu;
import org.Group34.view.GameView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class GameMenuController {
    private int usersChoosingMap = 0;
    private ArrayList<User> users = new ArrayList<>();
    private final ArrayList<FarmType> farmTypes = new ArrayList<>();
    private static final int MAX_PLAYERS = 3;
    private static final int[] PLAYER_INITIAL_LOCATION = new int[]{72, 10};

    public Result gameNew(String[] usernames) {
        if (App.getCurrentUser().getGame() != null)
            return new Result(false, "You're already in another game");
        if (usernames == null || usernames.length == 0)
            return new Result(false, "At least one username must be provided after '-u'.");
        if (usernames.length > MAX_PLAYERS)
            return new Result(false, "A maximum of " + MAX_PLAYERS + " usernames is allowed.");

        for (String u : usernames) {
            User user = getUser(u);

            if (user == null) {
                users = new ArrayList<>();
                return new Result(false, "Invalid username → '" + u + "'.");
            }
            if (user.getGame() != null) {
                users = new ArrayList<>();
                return new Result(false, "User already in another game → '" + u + "'.");
            }

            users.add(user);
        }

        return new Result(true, "New game created with users: " + String.join(", ", usernames));
    }

    public Result gameMap(String[] maps) {
        if (users.isEmpty())
            return new Result(false, "No players in game - create game first");
        if (maps.length != users.size())
            return new Result(false, "Number of maps must match number of players");

        farmTypes.clear();
        for (String map : maps) {
            Integer farmNumber = getInt(map);
            if (farmNumber == null)
                return new Result(false, "Map should be a number");

            FarmType farmType = FarmType.getFarm(farmNumber);
            if (farmType == null)
                return new Result(false, "No map found with number " + farmNumber);

            farmTypes.add(farmType);
        }

        MyGame myGame = generateGame();
        for (User u: users)
            u.setGame(myGame);

        return new Result(true, "Game started with selected maps!");
    }

    public Result loadGame() {
        if (usersChoosingMap > 0) {
            return new Result(false, "Each player must choose a map first!");
        }

        MyGame myGame = App.getCurrentUser().getGame();
        if (myGame == null)
            return new Result(false, "You don't have an active myGame.");

        App.setCurrentMenu(Menu.GAME);
        App.setCurrentGame(myGame);
        App.setAppMenu(new GameView(myGame));

        return new Result(true, "MyGame loaded successfully.");
    }

    public Result showMenu() {
        if (usersChoosingMap > 0) {
            return new Result(false, "Each player must choose a map first!");
        }
        return new Result(true, "You are currently in Profile Menu.");
    }

    private static User getUser(String username) {
        for (User user: App.getUsers())
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    private MyGame generateGame(){
        HashMap<User, Player> players = new HashMap<>();

        for (User user: users)
            players.put(user, new Player(PLAYER_INITIAL_LOCATION));

        for (User user : players.keySet()) {
            players.get(user).setInteractions(players);
        }

        return new MyGame(App.getCurrentUser(), players, generateMap(players.values()), new Time(), new WeatherSystem());
    }

    private Map generateMap(Collection<Player> players){
        MapBuilder mapBuilder = new MapBuilder();
        mapBuilder.setFarms(farmTypes.toArray(new FarmType[0]));
        mapBuilder.setPlayers(players.toArray(new Player[0]));
        return mapBuilder.generate();
    }

    private static Integer getInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
