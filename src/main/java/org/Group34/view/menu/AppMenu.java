package main.java.org.Group34.view.menu;

import main.java.org.Group34.model.enums.Menu;

import java.util.Scanner;

/**
 * This abstract class should be extended by all menu classes in the game.
 * It defines the common structure and behavior shared across all menus,
 * such as showing messages and managing the current menu state.
 */

public abstract class AppMenu {
    protected Menu currentMenu;

    /**
     * Starts the logic of the current menu
     * Should be implemented for each menu separately
     */
    public abstract void run(Scanner scanner);

    /**
     * Sets the current menu
     *
     * @param currentMenu The new menu to set
     */
    public void setMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    /**
     * Returns the current menu
     */
    public Menu getMenu() {
        return currentMenu;
    }

    /**
     * Displays a message to the user
     *
     * @param message The message to show
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
