package org.Group34.view.menu;

import org.Group34.model.App;
import org.Group34.model.enums.Menu;
import org.Group34.model.enums.command.menu.MainMenuCommand;

import java.util.Scanner;

/**
 * This menu doesn't need controller
 * All operations are about navigating to other menus
 */

public class MainMenu extends AppMenu{
    @Override
    public void run(Scanner scanner) {
        while (App.getCurrentMenu() == Menu.MAIN_MENU) {
            String command = scanner.nextLine().trim();

            if(command.matches(MainMenuCommand.ENTER_PROFILE_MENU.getRegex())) {
                App.setCurrentMenu(Menu.PROFILE_MENU);
                showMessage("You are now in profile menu.");
            }
            else if (command.matches(MainMenuCommand.ENTER_GAME_MENU.getRegex())) {
                App.setCurrentMenu(Menu.GAME_MENU);
                showMessage("You are now in game menu.");
            }
            else if (command.matches(MainMenuCommand.LOGOUT.getRegex())) {
                App.setCurrentMenu(Menu.REGISTER_MENU);
                showMessage("You are now in register menu.");
            }
            else if (command.matches(MainMenuCommand.SHOW_MENU.getRegex())) {
                showMessage("You are currently in Main Menu.");
            }
            else if (command.matches(MainMenuCommand.LIST_MENUS.getRegex())) {
                showMessage("Profile menu\nGame menu");
            }
            else {
                showMessage("Invalid command.");
            }
        }
    }
}
