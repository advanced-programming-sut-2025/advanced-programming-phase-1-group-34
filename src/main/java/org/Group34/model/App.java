package org.Group34.model;

import org.Group34.model.enums.Menu;
import org.Group34.view.menu.AppMenu;
import org.Group34.view.menu.RegisterMenu;
import org.Group34.database.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static User currentUser;
    private static Menu currentMenu = Menu.REGISTER_MENU;
    private static AppMenu appMenu = new RegisterMenu();
    private static MyGame currentMyGame = null;

    public static void addUser(User user) {
        DatabaseManager.addUser(user);
    }

    public static List<User> getUsers() {
        return DatabaseManager.getAllUsers();
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static User getUserByUsername(String username) {
        return DatabaseManager.getUserByUsername(username);
    }

    public static String getSecurityQuestionByNumber(int number) {
        return DatabaseManager.getSecurityQuestionByNumber(number);
    }

    public static ArrayList<String> getSecurityQuestions() {
        return new ArrayList<>(DatabaseManager.getSecurityQuestions());
    }

    public static User getLastUser() {
        List<User> users = DatabaseManager.getAllUsers();
        return users.isEmpty() ? null : users.get(users.size() - 1);
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static AppMenu getAppMenu() {
        return appMenu;
    }

    public static void setAppMenu(AppMenu appMenu) {
        App.appMenu = appMenu;
    }

    public static MyGame getCurrentGame() {
        return currentMyGame;
    }

    public static void setCurrentGame(MyGame currentMyGame) {
        App.currentMyGame = currentMyGame;
    }

    public static void shutdown() {
        DatabaseManager.closeAllConnections();
    }
}