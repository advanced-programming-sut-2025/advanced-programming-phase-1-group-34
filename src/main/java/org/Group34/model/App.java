package main.java.org.Group34.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the game data
 * Only essential fields for Main menu and Profile menu are added
 * Make sure to complete it
 */

public class App {
    private static List<User> users = new ArrayList<>();

    public void addUsers(List<User> users) {
        App.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
