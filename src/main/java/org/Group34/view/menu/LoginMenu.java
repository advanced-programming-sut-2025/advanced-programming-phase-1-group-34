package org.Group34.view.menu;

import org.Group34.controller.menu.LoginMenuController;
import org.Group34.model.Result;
import org.Group34.model.enums.Menu;
import org.Group34.model.enums.command.menu.LoginMenuCommand;
import org.Group34.model.enums.command.menu.MainMenuCommand;
import org.Group34.model.enums.command.menu.RegisterMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginMenu extends AppMenu {
    private final LoginMenuController controller;

    public LoginMenu() {
        currentMenu = Menu.LOGIN_MENU;
        controller = new LoginMenuController();
    }

    @Override
    public void run(Scanner scanner) {
        while (currentMenu == Menu.LOGIN_MENU) {
            String command = scanner.nextLine().trim();

            if (command.matches(LoginMenuCommand.Login.getRegex())) {
                Pattern pattern = Pattern.compile(LoginMenuCommand.Login.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();
                    String password = matcher.group("password").trim();

                    Result result = controller.login(username, password);
                    showMessage(result.message());
                }
            }

            else if (command.matches(LoginMenuCommand.LoginWithSave.getRegex())) {
                Pattern pattern = Pattern.compile(LoginMenuCommand.LoginWithSave.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();
                    String password = matcher.group("password").trim();

                    Result result = controller.loginWithSave(username, password);
                    showMessage(result.message());
                }
            }

            else if (command.matches(LoginMenuCommand.ForgetPassword.getRegex())) {
                Pattern pattern = Pattern.compile(LoginMenuCommand.ForgetPassword.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();

                    Result result = controller.forgetPassword(scanner, username);
                    showMessage(result.message());
                }
            }
            else if (command.matches(LoginMenuCommand.SHOW_MENU.getRegex())) {
                showMessage("You are currently in Login Menu.");
            }

            else if(command.matches(LoginMenuCommand.EXIT.getRegex())) {
                System.exit(0);
            }

            else {
                showMessage("Invalid command.");
            }
        }
    }
}


