package org.Group34.view.menu;

import org.Group34.controller.menu.RegisterMenuController;
import org.Group34.model.App;
import org.Group34.model.Result;
import org.Group34.model.enums.Menu;
import org.Group34.model.enums.command.menu.RegisterMenuCommand;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenu extends AppMenu {
    private final RegisterMenuController controller;

    public RegisterMenu() {
        controller = new RegisterMenuController();
    }

    @Override
    public void run(Scanner scanner) {
        while (App.getCurrentMenu() == Menu.REGISTER_MENU) {
            String command = scanner.nextLine().trim();

            if (command.matches(RegisterMenuCommand.Register.getRegex())) {
                Pattern pattern = Pattern.compile(RegisterMenuCommand.Register.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();
                    String password = matcher.group("password").trim();
                    String passwordConfirm = matcher.group("passwordConfirm").trim();
                    String nickname = matcher.group("nickname").trim();
                    String email = matcher.group("email").trim();
                    String gender = matcher.group("gender").trim();

                    Result result = controller.register(scanner, username, password, passwordConfirm, nickname, email, gender);
                    showMessage(result.message());
                }
            }

            else if (command.matches(RegisterMenuCommand.RegisterWithRandomPassword.getRegex())) {
                Pattern pattern = Pattern.compile(RegisterMenuCommand.RegisterWithRandomPassword.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();
                    String nickname = matcher.group("nickname").trim();
                    String email = matcher.group("email").trim();
                    String gender = matcher.group("gender").trim();

                    Result result = controller.registerWithRandomPassword(scanner, username, nickname, email, gender);
                    showMessage(result.message());
                }
            }

            else if (command.matches(RegisterMenuCommand.PickQuestion.getRegex())) {
                Pattern pattern = Pattern.compile(RegisterMenuCommand.PickQuestion.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    int questionNumber = Integer.parseInt(matcher.group("questionNumber").trim());
                    String answer = matcher.group("answer").trim();
                    String answerConfirm = matcher.group("answerConfirm").trim();

                    Result result = controller.pickQuestion(questionNumber, answer, answerConfirm);
                    showMessage(result.message());
                    if (result.success()) {
                        App.setCurrentMenu(Menu.MAIN_MENU);
                    }
                }
            }

            else if (command.matches(RegisterMenuCommand.SHOW_MENU.getRegex())) {
                showMessage("You are currently in Register Menu.");
            }

            else if (command.matches(RegisterMenuCommand.GO_TO_LOGIN.getRegex())) {
                App.setCurrentMenu(Menu.LOGIN_MENU);
                showMessage("You are currently in Login Menu.");
            }

            else if(command.matches(RegisterMenuCommand.EXIT.getRegex())) {
                System.exit(0);
            }

            else {
                showMessage("Invalid command.");
            }
        }
    }
}
