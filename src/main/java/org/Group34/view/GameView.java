package org.Group34.view;

import org.Group34.controller.GameController;
import org.Group34.model.App;
import org.Group34.model.Game;
import org.Group34.model.Result;
import org.Group34.model.User;
import org.Group34.model.enums.Menu;
import org.Group34.model.enums.command.menu.GameCommands;
import org.Group34.view.menu.AppMenu;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameView extends AppMenu {
    private final GameController controller;

    public GameView(Game game) {
        this.controller = new GameController(game);
    }

    @Override
    public void run(Scanner scanner) {
        while (currentMenu == Menu.GAME) {
            String command = scanner.nextLine().trim();

            if (command.matches(GameCommands.EXIT_GAME.getRegex())) {
                Result result = controller.exitGame();
                showMessage(result.message());
            }
            else if (command.matches(GameCommands.DELETE_GAME.getRegex())) {
                Result result = controller.deleteGame();
                showMessage(result.message());
            }
            else if (command.matches(GameCommands.VOTE.getRegex())){
                Pattern pattern = Pattern.compile(GameCommands.VOTE.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String vote = matcher.group("vote");

                    Result result = controller.vote(vote);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.NEXT_TURN.getRegex())){
                Result result = controller.nextTurn();
                showMessage(result.message());
            }
            else if (command.matches(GameCommands.CHEAT_ADVANCE_TIME.getRegex())){
                Pattern pattern = Pattern.compile(GameCommands.CHEAT_ADVANCE_TIME.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String hours = matcher.group("hours");

                    Result result = controller.cheatAdvanceTime(hours);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.CHEAT_ADVANCE_DATE.getRegex())){
                Pattern pattern = Pattern.compile(GameCommands.CHEAT_ADVANCE_DATE.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String days = matcher.group("days");

                    Result result = controller.cheatAdvanceDate(days);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.DISPLAY_TIME.getRegex())){
                Pattern pattern = Pattern.compile(GameCommands.DISPLAY_TIME.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String type = matcher.group("type");

                    Result result = controller.displayTime(type);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.WALK.getRegex())){
                Pattern pattern = Pattern.compile(GameCommands.WALK.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String x = matcher.group("x");
                    String y = matcher.group("y");

                    Result result = controller.walk(x, y);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.PRINT_MAP.getRegex())){
                Pattern pattern = Pattern.compile(GameCommands.PRINT_MAP.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String x = matcher.group("x");
                    String y = matcher.group("y");
                    String size = matcher.group("size");

                    Result result = controller.printMap(x, y, size);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.HELP_READING_MAP.getRegex())) {
                Result result = controller.helpReadingMap();
                showMessage(result.message());
            }
            else {
                showMessage("Invalid command!");
            }
        }
    }
}
