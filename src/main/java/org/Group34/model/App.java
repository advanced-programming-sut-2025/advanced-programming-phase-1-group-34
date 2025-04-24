package main.java.org.Group34.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the game data
 * Only essential fields for Main menu and Profile menu are added
 * Make sure to complete it
 */

public class App {
    private List<User> users;

    public App() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
