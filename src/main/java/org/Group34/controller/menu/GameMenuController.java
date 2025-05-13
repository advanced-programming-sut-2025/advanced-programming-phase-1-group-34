package org.Group34.controller.menu;

import org.Group34.model.*;
import org.Group34.model.items.Time;
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
    private ArrayList<FarmType> farmTypes = new ArrayList<>();
    private static final int MAX_PLAYERS = 3;
    private static final int[] PLAYER_INITIAL_LOCATION = new int[]{72, 10};


    public Result gameNew(String[] usernames) {
        if (App.getCurrentUser().getGame() != null)
            return new Result(false, "Error: You're already in another game");
        if (usersChoosingMap > 0)
            return new Result(false, "Error: Each player must choose a map first!");
        if (usernames == null || usernames.length == 0)
            return new Result(false, "Error: At least one username must be provided after '-u'.");
        if (usernames.length > MAX_PLAYERS)
            return new Result(false, "Error: A maximum of " + MAX_PLAYERS + " usernames is allowed.");


        for (String u : usernames) {
            User user = getUser(u);

            if (user == null) {
                users = new ArrayList<>();
                return new Result(false, "Error: Invalid username → '" + u + "'.");
            }
            if (user.getGame() != null) {
                users = new ArrayList<>();
                return new Result(false, "Error: User already in another game → '" + u + "'.");
            }

            users.add(user);
        }


        usersChoosingMap = usernames.length;
        return new Result(true, "New game created with users: " + String.join(", ", usernames) + ". Please choose maps.");
    }

    public Result gameMap(String map) {
        Integer farmNumber = getInt(map);

        if (usersChoosingMap <= 0)
            return new Result(false, "Error: No new game in setup or all players have chosen a map.");
        if (map == null || map.trim().isEmpty())
            return new Result(false, "Error: Map cannot be empty.");
        if (farmNumber == null)
            return new Result(false, "Error: Map should be a number");

        FarmType farmType = FarmType.getFarm(farmNumber);
        if (farmType == null)
            return new Result(false, "Error: No map found with number " + farmNumber);

        farmTypes.add(farmType);
        usersChoosingMap--;

        if (usersChoosingMap > 0) {
            return new Result(true, "Map '" + map + "' selected. " + usersChoosingMap + " players still need to choose.");
        } else {
            Game game = generateGame();

            for (User u: users)
                u.setGame(game);

            return new Result(true, "Map '" + map + "' selected. All players have chosen. Game is starting!");
        }
    }

    public Result loadGame() {
        if (usersChoosingMap > 0) {
            return new Result(false, "Error: Each player must choose a map first!");
        }

        Game game = App.getCurrentUser().getGame();
        App.setCurrentMenu(Menu.GAME);
        App.setCurrentGame(game);
        App.setAppMenu(new GameView(game));

        return new Result(true, "Game loaded successfully.");
    }

    public Result showMenu() {
        if (usersChoosingMap > 0) {
            return new Result(false, "Error: Each player must choose a map first!");
        }
        return new Result(true, "You are currently in Profile Menu.");
    }

    private static User getUser(String username) {
        for (User user: App.getUsers())
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    private Game generateGame(){
        HashMap<User, Player> players = new HashMap<>();
        for (User user: users)
            players.put(user, new Player(PLAYER_INITIAL_LOCATION));

        return new Game(App.getCurrentUser(), players, generateMap(players.values()), new Time());
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
