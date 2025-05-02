package org.Group34.model;

import org.Group34.model.enums.Menu;
import org.Group34.view.menu.AppMenu;
import org.Group34.view.menu.GameMenu;
import org.Group34.view.menu.RegisterMenu;

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
    private static ArrayList<User> users = new ArrayList<>();

    private static User currentUser;

    private static Menu currentMenu = Menu.REGISTER_MENU;
    private static AppMenu appMenu = new RegisterMenu();  //TODO first menu should be register menu

    private static ArrayList<String> securityQuestions = new ArrayList<>();

    static {
        securityQuestions.add("What was the name of your elementary school?");
        securityQuestions.add("What is the name of the city where you were born?");
        securityQuestions.add("What was the name of your first teacher?");
        securityQuestions.add("What is the name of your first pet?");
        securityQuestions.add("What is your favorite movie?");
        securityQuestions.add("In what city did your parents meet?");
        securityQuestions.add("What was the name of the hospital where you were born?");
    }

    public static void addUser(User user) {
        users.add(user);
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

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static String getSecurityQuestionByNumber(int number) {
        number--;
        return securityQuestions.get(number);
    }

    public static ArrayList<String> getSecurityQuestions() {
        return securityQuestions;
    }

    public static User getLastUser() {
        return users.get(users.size() - 1);
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
}