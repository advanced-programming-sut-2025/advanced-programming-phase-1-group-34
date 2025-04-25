package org.Group34.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the game data
 * Only essential fields for Main menu and Profile menu are added
 * Make sure to complete it
 *
 * Make everything static so they can be accessed easier
 */

public class App {
    private static List<User> users = new ArrayList<>();

    private static User currentUser;

    public static void addUsers(List<User> users) {
        App.users = users;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
