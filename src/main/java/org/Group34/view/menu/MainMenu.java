package main.java.org.Group34.view.menu;

import main.java.org.Group34.model.enums.Menu;
import main.java.org.Group34.model.enums.command.menu.MainMenuCommand;

import java.util.Scanner;

/**
 * This menu doesn't need controller
 * All operations are about navigating to other menus
 */

public class MainMenu extends AppMenu{
    public MainMenu() {
        currentMenu = Menu.MAIN_MENU;
    }

    @Override
    public void run(Scanner scanner) {
        while (currentMenu == Menu.MAIN_MENU) {
            String command = scanner.nextLine().trim();

            if(command.matches(MainMenuCommand.ENTER_PROFILE_MENU.getRegex())) {
                setMenu(Menu.PROFILE_MENU);
            }
            else if (command.matches(MainMenuCommand.ENTER_GAME_MENU.getRegex())) {
                setMenu(Menu.GAME_MENU);
            }
            else if (command.matches(MainMenuCommand.LOGOUT.getRegex())) {
                setMenu(Menu.SIGNUP_MENU);
            }
            else if (command.matches(MainMenuCommand.SHOW_MENU.getRegex())) {
                showMessage("You are currently in Main Menu.");
            }
        }
    }
}
