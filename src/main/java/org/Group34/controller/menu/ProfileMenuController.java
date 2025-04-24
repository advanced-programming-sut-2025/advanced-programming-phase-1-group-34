package main.java.org.Group34.controller.menu;

import main.java.org.Group34.model.enums.Menu;
import main.java.org.Group34.model.enums.command.menu.ProfileMenuCommand;
import main.java.org.Group34.view.menu.AppMenu;

import java.util.Scanner;

public class ProfileMenuController extends AppMenu {
    private ProfileMenuController controller;

    public ProfileMenuController() {
        currentMenu = Menu.PROFILE_MENU;
        controller = new ProfileMenuController();
    }

    @Override
    public void run(Scanner scanner) {
        while (currentMenu == Menu.PROFILE_MENU) {
            String command = scanner.nextLine().trim();

            if (command.matches(ProfileMenuCommand.CHANGE_USERNAME.getRegex())) {

            }
            else if (command.matches(ProfileMenuCommand.CHANGE_PASSWORD.getRegex())) {

            }
            else if (command.matches(ProfileMenuCommand.CHANGE_NICKNAME.getRegex())) {

            }
            else if (command.matches(ProfileMenuCommand.CHANGE_EMAIL.getRegex())) {

            }
            else if (command.matches(ProfileMenuCommand.SHOW_INFO.getRegex())) {

            }
            else if (command.matches(ProfileMenuCommand.SHOW_MENU.getRegex())) {
                showMessage("You are currently in Profile Menu.");
            }
            else {
                showMessage("Invalid command.");
            }
        }
    }
}
