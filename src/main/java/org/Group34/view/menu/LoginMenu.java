package org.Group34.view.menu;

import org.Group34.controller.menu.LoginMenuController;
import org.Group34.controller.menu.RegisterMenuController;
import org.Group34.model.enums.command.menu.LoginMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private LoginMenuController controller = new LoginMenuController();

    public void check(Scanner scanner) {
        String command = scanner.nextLine();
        Matcher matcher;
        if ((matcher = LoginMenuCommand.Login.getMatcher(command)) != null) {
            System.out.println(controller.login(matcher.group("username").trim(), matcher.group("password").trim()));
        } else if ((matcher = LoginMenuCommand.LoginWithSave.getMatcher(command)) != null) {
            System.out.println(controller.loginWithSave(matcher.group("username").trim(), matcher.group("password").trim()));
        } else if ((matcher = LoginMenuCommand.ForgetPassword.getMatcher(command)) != null) {
            System.out.println(controller.forgetPassword(scanner, matcher.group("username").trim()));
        } else {
            System.out.println("invalid command");
        }
    }
}
