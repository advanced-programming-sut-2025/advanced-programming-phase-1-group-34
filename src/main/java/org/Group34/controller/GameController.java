package org.Group34.controller;

import org.Group34.model.App;
import org.Group34.model.Game;
import org.Group34.model.Result;
import org.Group34.model.User;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.enums.Color;
import org.Group34.model.map.MapBuilder;
import org.Group34.view.menu.GameMenu;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final Game game;
    private final List<User> orderOfPlay = new ArrayList<>();
    private final int mainUser = 0;
    private int currentUser = 0;
    private final ArrayList<Boolean> forceTerminating = new ArrayList<>();
    private final AnimalController animalController = new AnimalController();

    public GameController(Game game){
        this.game = game;
        setOrderOfPlay();
        game.weatherSystem().initializeWeather(game.time());
    }

    private void setOrderOfPlay() {
        // First User that adds to list is Main User(User that loads the game)
        orderOfPlay.add(App.getCurrentUser());

        for (User user: game.players().keySet())
            if (!user.equals(orderOfPlay.get(mainUser)))
                orderOfPlay.add(user);
    }


    public Result exitGame() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Error: Force-terminate vote in progress; you can only vote now");

        User current = orderOfPlay.get(currentUser);
        if (!current.equals(orderOfPlay.get(mainUser)))
            return new Result(false, "Error: Only person that loaded game can exit (and only on their turn)");

        game.save();
        App.setCurrentGame(null);
        App.setAppMenu(new GameMenu());
        return new Result(true, "Game saved. Returning to menu.");
    }

    public Result deleteGame() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Error: Force-terminate vote in progress; you can only vote now");

        forceTerminating.add(true);
        nextUser();

        return new Result(true,  "Force-terminate has been started. Do you want to delete game?(yes|no)");
    }

    public Result vote(String vote){
        if (forceTerminating.isEmpty())
            return new Result(false, "Error: NO Force-terminate vote is in progress.");

        forceTerminating.add(vote.equals("yes"));
        int votedUser = currentUser;
        nextUser();

        if (forceTerminating.size() >= orderOfPlay.size()){
            if (forceTerminating.contains(false))
                return new Result(false, "Someone disagrees to deleting game. User " + orderOfPlay.get(currentUser)+
                        " is playing now.");
            else {
                game.delete();
                App.setCurrentGame(null);
                App.setAppMenu(new GameMenu());
            }
        }

        return new Result(true, "User " + orderOfPlay.get(votedUser) + " voted "
                    + forceTerminating.get(votedUser) + "\nForce-terminate has been started. Do you want to delete game?(yes|no)" );

    }

    public Result nextTurn() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Error: Force-terminate vote in progress; you can only vote now");

        nextUser();
        if (currentUser == 0) game.time().addHours(1);

        // TODO from here we should call StartANewDayController
        // TODO remember when all players passedOut we have to jump to next day

        while (game.players().get(orderOfPlay.get(currentUser)).isPassedOut())
            nextUser();

        return new Result(true, orderOfPlay.get(currentUser) + " turn.");
    }

    private void nextUser(){
        currentUser++;
        if (currentUser >= orderOfPlay.size()){
            currentUser = 0;
        }
    }

    public Result cheatAdvanceTime(String hours) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Error: Force-terminate vote in progress; you can only vote now");

        try {
            Integer h = getInt(hours);
            if (h == null) return new Result(false, "Error: you should give a number as hours argument");
            game.time().addHours(h);
            return new Result(true, "Cheat Code Activated: (" + game.time() + ")");
        }
        catch (Exception IllegalArgumentException){
            return new Result(false, "Error: input an positive number as hours argument");
        }

    }

    public Result cheatAdvanceDate(String days) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Error: Force-terminate vote in progress; you can only vote now");

        try {
            Integer d = getInt(days);
            if (d == null) return new Result(false, "Error: you should give a number as days argument");
            game.time().addDays(d);
            return new Result(true, "Cheat Code Activated: (" + game.time() + ")");
        }
        catch (Exception IllegalArgumentException){
            return new Result(false, "Error: input an positive number as days argument");
        }
    }

    public Result displayTime(String type){
        if (!forceTerminating.isEmpty())
            return new Result(false, "Error: Force-terminate vote in progress; you can only vote now");

        String message = "";
        switch (type){
            case "time": message = game.time().getHour() + ":00" ;
            case "date": message = game.time().getSeason().getName() + " " + game.time().getDate();
            case "datetime": message = game.time().getSeason().getName() + " " + game.time().getDate() + " "
                    + game.time().getHour() + ":00";
            case "day of week": message = game.time().getDayOfWeek().getName();
            case "season": message = game.time().getSeason().getName();
        }
        return new Result(true, message);
    }

    public Result displayWeather(String type){
        if (!forceTerminating.isEmpty())
            return new Result(false, "Error: Force-terminate vote in progress; you can only vote now");

        String message = "";
        switch (type){
            case "today weather": message = game.weatherSystem().getTodayCondition().toString();
            case "tomorrow weather": message = game.weatherSystem().getTomorrowCondition().toString();
        }

        return new Result(true, message);
    }

    public Result walk(String x, String y) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Error: Force-terminate vote in progress; you can only vote now");

        Integer targetX = getInt(x);
        Integer targetY = getInt(y);
        Player player = getPlayer();

        if (targetX == null || targetY == null)
            return new Result(false, "Error: target location should be number format");
        if (player.getCurrentSpace().getEntityByLocation(targetX, targetY) != null)
            return new Result(false, "Error: only can go to empty tiles of map");

        int distance = game.map().findPath(player, targetX, targetY);
        if (distance == 0)
            return new Result(false, "Error: there is no path to target location");

        int energy = distance / 20;
        if (player.decreaseEnergy(energy)){
            game.map().movePlayer(player, targetX, targetY);
            return new Result(true, "Your character have been moved to: " + "<" + targetX + " ," + targetY + ">");
        }
        else{
            if (Player.passedOutUsers() >= orderOfPlay.size())
                game.time().addDays(1);
            return new Result(false, "Your character have been passed out.");
        }

    }

    private Player getPlayer() {
        return game.players().get(orderOfPlay.get(currentUser));
    }

    public Result printMap(String x, String y, String sz) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Error: Force-terminate vote in progress; you can only vote now");

        Integer beginX = getInt(x);
        Integer beginY = getInt(y);
        Integer size = getInt(sz);
        Entity[][] entities = getPlayer().getCurrentSpace().entities();
        StringBuilder message = new StringBuilder();

        if (beginX == null || beginY == null || size == null)
            return new Result(false, "Error: size or center location should be number format");

        int endX = Math.min(MapBuilder.SPACE_WIDTH - 1, beginX + size);
        int endY = Math.max(MapBuilder.SPACE_HEIGHT - 1, beginY + size);

        for (int i = beginX; i < endX; i++) {
            for (int j = beginY; j < endY; j++) {
                message.append(entities[i][j]).append(" ");
            }
            message.append("\n");
        }

        return new Result(true, message.toString());
    }

    public Result helpReadingMap() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Error: Force-terminate vote in progress; you can only vote now");

        return new Result(true, "player:      P\n" +
                        "house: " + Color.BROWN + "       H" + Color.RESET + "\n" +
                        "green house: " + Color.YELLOW + "G" + Color.RESET + "\n" +
                        "lake: " + Color.CYAN + "        L" + Color.RESET + "\n" +
                        "quarry: " + Color.GRAY + "      Q" + Color.RESET + "\n" +
                        "foraging: " + Color.RED + "    F" + Color.RESET + "\n" +
                        "stone: " + Color.GRAY + "       S" + Color.RESET + "\n" +
                        "tree: " + Color.GREEN + "        T" + Color.RESET);
    }

    private static Integer getInt(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
