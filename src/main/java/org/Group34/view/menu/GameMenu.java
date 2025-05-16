package org.Group34.view.menu;

import org.Group34.controller.menu.GameMenuController;
import org.Group34.model.App;
import org.Group34.model.Result;
import org.Group34.model.enums.Menu;
import org.Group34.model.enums.command.menu.GameMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu extends AppMenu{

    private final GameMenuController controller;

    public GameMenu() {
        controller = new GameMenuController();
    }

    @Override
    public void run(Scanner scanner) {
        while (App.getCurrentMenu() == Menu.GAME_MENU){
            String command = scanner.nextLine().trim();

            if (command.matches(GameMenuCommands.GAME_NEW.getRegex())) {
                Pattern pattern = Pattern.compile(GameMenuCommands.GAME_NEW.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String usernamesPart = matcher.group("usernames");
                    String[] usernames = usernamesPart.trim().split("\\s+");

                    Result result = controller.gameNew(usernames);
                    showMessage(result.message());
                }
            }

            else if (command.matches(GameMenuCommands.GAME_MAP.getRegex())) {
                Pattern pattern = Pattern.compile(GameMenuCommands.GAME_MAP.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String map = matcher.group("map_number");

                    Result result = controller.gameMap(map);
                    showMessage(result.message());
                }
            }

            else if (command.matches(GameMenuCommands.LOAD_GAME.getRegex())) {
                Result result = controller.loadGame();
                showMessage(result.message());
            }

            else if (command.matches(GameMenuCommands.SHOW_MENU.getRegex())) {
                Result result = controller.showMenu();
                showMessage(result.message());
            }

            else if (command.matches(GameMenuCommands.ENTER_MAIN_MENU.getRegex())) {
                App.setCurrentMenu(Menu.MAIN_MENU);
                showMessage("You are now in main menu.");
            }

            else {
                showMessage("Invalid command.");
            }
        }
    }
}
