package org.Group34.view.menu;

import org.Group34.controller.menu.RegisterMenuController;
import org.Group34.model.enums.command.menu.RegisterMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu {
    private RegisterMenuController controller = new RegisterMenuController();

    public void check(Scanner scanner) {
        String command = scanner.nextLine();
        Matcher matcher;
        if ((matcher = RegisterMenuCommand.Register.getMatcher(command)) != null) {
            System.out.print(controller.register(scanner, matcher.group("username").trim(), matcher.group("password").trim(), matcher.group("passwordConfirm").trim(), matcher.group("nickname").trim(), matcher.group("email").trim(), matcher.group("gender").trim()));
        } else if ((matcher = RegisterMenuCommand.RegisterWithRandomPassword.getMatcher(command)) != null) {
            System.out.print(controller.registerWithRandomPassword(scanner, matcher.group("username").trim(), matcher.group("nickname").trim(), matcher.group("email").trim(), matcher.group("gender").trim()));
        } else if ((matcher = RegisterMenuCommand.PickQuestion.getMatcher(command)) != null) {
            System.out.print(controller.pickQuestion(Integer.parseInt(matcher.group("questionNumber").trim()), matcher.group("answer").trim(), matcher.group("answerConfirm").trim()));
        } else {
            System.out.println("invalid command");
        }
    }
}
