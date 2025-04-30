package org.Group34.controller.menu;

import org.Group34.model.*;
import org.Group34.model.enums.Farm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GameMenuController {
    private int usersChoosingMap = 0;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Farm> farms = new ArrayList<>();
    private static final int MAX_PLAYERS = 3;


    public Result gameNew(String[] usernames) {
        if (usersChoosingMap > 0) {
            return new Result(false, "Error: Each player must choose a map first!");
        }
        if (usernames == null || usernames.length == 0) {
            return new Result(false, "Error: At least one username must be provided after '-u'.");
        }
        if (usernames.length > MAX_PLAYERS) {
            return new Result(false, "Error: A maximum of " + MAX_PLAYERS + " usernames is allowed.");
        }
        for (String u : usernames) {
            User user = getUser(u);

            if (user == null) {
                return new Result(false, "Error: Invalid username → '" + u + "'.");
            }
            if (user.getGame() != null) {
                return new Result(false, "Error: User already in another game → '" + u + "'.");
            }

            users.add(user);
        }
        usersChoosingMap = usernames.length;
        return new Result(true, "New game created with users: " + String.join(", ", usernames) + ". Please choose maps.");
    }

    public Result gameMap(String map) {
        if (usersChoosingMap <= 0) {
            return new Result(false, "Error: No new game in setup or all players have chosen a map.");
        }
        if (map == null || map.trim().isEmpty()) {
            return new Result(false, "Error: Map name cannot be empty.");
        }

        int farmNumber =

        farms.add(farm);
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
        return new Game(users.toArray(new User[0]), generateMap());
    }

    private Map generateMap(){
        return new Map(farms.toArray(new Farm[0]));
    }
}
