package org.Group34.view.menu;

import org.Group34.controller.menu.ProfileMenuController;
import org.Group34.model.App;
import org.Group34.model.Result;
import org.Group34.model.enums.Menu;
import org.Group34.model.enums.command.menu.ProfileMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is the profile menu view.
 * It parses commands and sends data to its controller
 */

public class ProfileMenu extends AppMenu {
    private final ProfileMenuController controller;

    public ProfileMenu() {
        currentMenu = Menu.PROFILE_MENU;
        controller = new ProfileMenuController();
    }

    @Override
    public void run(Scanner scanner) {
        while (currentMenu == Menu.PROFILE_MENU) {
            String command = scanner.nextLine().trim();

            if (command.matches(ProfileMenuCommand.CHANGE_USERNAME.getRegex())) {
                Pattern pattern = Pattern.compile(ProfileMenuCommand.CHANGE_USERNAME.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String newUsername = matcher.group("username");

                    Result result = controller.changeUsername(newUsername, App.getCurrentUser());
                    showMessage(result.message());
                }
            }

            else if (command.matches(ProfileMenuCommand.CHANGE_PASSWORD.getRegex())) {
                Pattern pattern = Pattern.compile(ProfileMenuCommand.CHANGE_PASSWORD.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String newPassword = matcher.group("newPassword");
                    String oldPassword = matcher.group("oldPassword");

                    Result result = controller.changePassword(newPassword, oldPassword, App.getCurrentUser());
                    showMessage(result.message());
                }

            }

            else if (command.matches(ProfileMenuCommand.CHANGE_NICKNAME.getRegex())) {
                Pattern pattern = Pattern.compile(ProfileMenuCommand.CHANGE_NICKNAME.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String newNickname = matcher.group("nickname");

                    Result result = controller.changeNickname(newNickname, App.getCurrentUser());
                    showMessage(result.message());
                }
            }

            else if (command.matches(ProfileMenuCommand.CHANGE_EMAIL.getRegex())) {
                Pattern pattern = Pattern.compile(ProfileMenuCommand.CHANGE_EMAIL.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String newEmail = matcher.group("email");

                    Result result = controller.changeEmail(newEmail, App.getCurrentUser());
                    showMessage(result.message());
                }
            }

            else if (command.matches(ProfileMenuCommand.SHOW_INFO.getRegex())) {
                Result result = controller.showUserInfo(App.getCurrentUser());
                showMessage(result.message());
            }

            else if (command.matches(ProfileMenuCommand.SHOW_MENU.getRegex())) {
                showMessage("You are currently in Profile Menu.");
            }

            else if (command.matches(ProfileMenuCommand.ENTER_MAIN_MENU.getRegex())) {
                setMenu(Menu.MAIN_MENU);
            }

            else {
                showMessage("Invalid command.");
            }
        }
    }
}
