package org.Group34.view;

import org.Group34.controller.FarmingController;
import org.Group34.controller.GameController;
import org.Group34.controller.ShopController;
import org.Group34.controller.ToolsController;
import org.Group34.model.App;
import org.Group34.model.Game;
import org.Group34.model.Result;
import org.Group34.model.enums.Menu;
import org.Group34.model.enums.command.GameCommands;
import org.Group34.view.menu.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameView extends AppMenu {
    private final GameController controller;
    FarmingController farmingController;
    ToolsController toolsController;
    ShopController shopController;

    public GameView(Game game) {
        this.controller = new GameController(game);
        this.farmingController =  controller.getFarmingController();
        this.toolsController = controller.getToolsController();
        this.shopController = controller.getShopController();
    }

    @Override
    public void run(Scanner scanner) {
        while (App.getCurrentMenu() == Menu.GAME) {
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
                App.getAppMenu().run(scanner);
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
            else if (command.matches(GameCommands.CHEAT_CHANGE_WEATHER.getRegex())){
                Pattern pattern = Pattern.compile(GameCommands.CHEAT_CHANGE_WEATHER.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String weather = matcher.group("weather");
                    Result result = controller.cheatChangeWeather(weather);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.CHEAT_ADD_MONEY.getRegex())){
                Pattern pattern = Pattern.compile(GameCommands.CHEAT_ADD_MONEY.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String amount = matcher.group("amount");
                    showMessage(controller.cheatAddMoney(amount).message());
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

            // ----- Farming View -----
            else if (command.matches(GameCommands.CRAFT_INFO.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.CRAFT_INFO.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String craftName = matcher.group("craftName").trim();

                    Result result = controller.showCraftInfo(craftName);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.PLANT.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.PLANT.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String seed = matcher.group("seed").trim();
                    String direction = matcher.group("direction").trim();

                    Result result = controller.plant(seed, direction);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.SHOW_PLANT.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.SHOW_PLANT.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    int x = Integer.parseInt(matcher.group("x").trim());
                    int y = Integer.parseInt(matcher.group("y").trim());

                    Result result = controller.showPlant(x, y);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.FERTILIZE.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.FERTILIZE.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String fertilizer = matcher.group("fertilizer").trim();
                    String direction = matcher.group("direction").trim();

                    Result result = controller.fertilize(fertilizer, direction);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.HOW_MUCH_WATER.getRegex())) {
                Result result = controller.showAmountOfWater();
                showMessage(result.message());
            }
            // ------------------------

            // ----- Tools View -----
            else if (command.matches(GameCommands.TOOLS_EQUIP.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.TOOLS_EQUIP.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String toolName = matcher.group("toolName").trim();

                    Result result = controller.toolsEquip(toolName);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.SHOW_CURRENT_TOOLS.getRegex())) {
                Result result = controller.showCurrentTools();
                showMessage(result.message());
            }
            else if (command.matches(GameCommands.SHOW_AVAILABLE_TOOLS.getRegex())) {
                Result result = controller.showAvailableTools();
                showMessage(result.message());
            }
            else if (command.matches(GameCommands.TOOLS_USE.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.TOOLS_USE.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String direction = matcher.group("direction").trim();

                    Result result = controller.toolUse(direction);
                    showMessage(result.message());
                }
            }

            // ===== Time Commands ====
            else if (command.matches(GameCommands.SHOW_TIME.getRegex())) {
                showMessage(controller.displayTime("time").message());
            }
            else if (command.matches(GameCommands.SHOW_DATE.getRegex())) {
                showMessage(controller.displayTime("date").message());
            }
            else if (command.matches(GameCommands.SHOW_DATETIME.getRegex())) {
                showMessage(controller.displayTime("datetime").message());
            }
            else if (command.matches(GameCommands.SHOW_WEEKDAY.getRegex())) {
                showMessage(controller.displayTime("day of week").message());
            }

            // ===== Season Commands ====
            else if (command.matches(GameCommands.SHOW_SEASON.getRegex())) {
                showMessage(controller.displayTime("season").message());
            }

            // ===== Weather Commands ====
            else if (command.matches(GameCommands.SHOW_TODAY_WEATHER.getRegex())) {
                showMessage(controller.displayWeather("today weather").message());
            }
            else if (command.matches(GameCommands.SHOW_TOMORROW_WEATHER.getRegex())) {
                showMessage(controller.displayWeather("tomorrow weather").message());
            }

            // ===== Greenhouse Commands ====
            else if (command.matches(GameCommands.ENTER_GREENHOUSE.getRegex())) {
                if (controller.greenhouse.isRepaired()) {
                    showMessage("You entered the greenhouse.");
                }
                else {
                    showMessage("Greenhouse is not repaired yet.");
                }
            }
            else if (command.matches(GameCommands.BUILD_GREENHOUSE.getRegex())) {
                showMessage(controller.buildGreenhouse().message());
            }

            // ===== Animals Commands =======
            else if (command.matches(GameCommands.BUILD_ANIMALS_PLACEMENT.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.BUILD_ANIMALS_PLACEMENT.getRegex()).matcher(command);
                if (matcher.matches()) {
                    String buildingName = matcher.group("buildingName");
                    int x = Integer.parseInt(matcher.group("x"));
                    int y = Integer.parseInt(matcher.group("y"));
                    showMessage("Building " + buildingName + " at " + x + ", " + y + "built.");
                }
            }
            else if (command.matches(GameCommands.BUY_ANIMAL.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.BUY_ANIMAL.getRegex()).matcher(command);
                if (matcher.matches()) {
                    String animal = matcher.group("animal");
                    String name = matcher.group("name");

                    showMessage(controller.buyAnimal(animal, name).message());
                }
            }
            else if (command.matches(GameCommands.PET_ANIMAL.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.PET_ANIMAL.getRegex()).matcher(command);
                if (matcher.matches()) {
                    String name = matcher.group("name");
                    showMessage(controller.petAnimal(name).message());
                }
            }
            else if(command.matches(GameCommands.CHEAT_SET_FRIENDSHIP.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.CHEAT_SET_FRIENDSHIP.getRegex()).matcher(command);

                if (matcher.matches()) {
                    String animalName = matcher.group("animalName");
                    int amount = Integer.parseInt(matcher.group("amount"));

                    showMessage(controller.cheatAnimalFriendship(animalName, amount).message());
                }
            }
            else if (command.matches(GameCommands.LIST_ANIMALS.getRegex())) {
                    showMessage(controller.listAnimals().message());
            }
            else if (command.matches(GameCommands.SHEPHERD_ANIMAL.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.SHEPHERD_ANIMAL.getRegex()).matcher(command);
                if (matcher.matches()) {
                    String animalName = matcher.group("animalName");
                    int x = Integer.parseInt(matcher.group("x"));
                    int y = Integer.parseInt(matcher.group("y"));
                    showMessage(controller.shepherdAnimal(animalName, x, y).message());
                }
            }
            else if (command.matches(GameCommands.FEED_ANIMAL.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.FEED_ANIMAL.getRegex()).matcher(command);
                if (matcher.matches()) {
                    String animalName = matcher.group("animalName");
                    showMessage(controller.feedAnimal(animalName).message());
                }
            }
            else if (command.matches(GameCommands.SHOW_PRODUCTS.getRegex())) {
                showMessage(controller.showProducts().message());
            }
            else if (command.matches(GameCommands.COLLECT_PRODUCTS.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.COLLECT_PRODUCTS.getRegex()).matcher(command);
                if (matcher.matches()) {
                    String animalName = matcher.group("animalName");
                    showMessage(controller.collectProduct(animalName).message());
                }
            }
            else if (command.matches(GameCommands.SELL_ANIMAL.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.SELL_ANIMAL.getRegex()).matcher(command);
                if (matcher.matches()) {
                    String animalName = matcher.group("animalName");
                    showMessage(controller.sellAnimal(animalName).message());
                }
            }

            // ===== Fishing Commands =====
            else if (command.matches(GameCommands.START_FISHING.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.START_FISHING.getRegex()).matcher(command);
                if (matcher.matches()) {
                    String fishingPole = matcher.group("fishingPole");

                }
            }

            // ===== NPC Commands =========
            else if (command.matches(GameCommands.MEET_NPC.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.MEET_NPC.getRegex()).matcher(command);
                if (matcher.matches()) {
                    String npcName = matcher.group("npcName");

                    showMessage(controller.meetNPC(npcName).message());
                }
            }
            else if (command.matches(GameCommands.LIST_AVAILABLE_QUESTS.getRegex())) {
                showMessage(controller.listAvailableQuests().message());
            }
            else if (command.matches(GameCommands.SEND_GIFT.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.SEND_GIFT.getRegex()).matcher(command);
                if (matcher.matches()) {
                    String npcName = matcher.group("npcName");
                    String itemName = matcher.group("itemName");
                    showMessage(controller.sendGift(npcName, itemName).message());
                }
            }
            else if (command.matches(GameCommands.LIST_NPC_FRIENDSHIP.getRegex())) {
                showMessage(controller.listNPCs().message());
            }
            else if (command.matches(GameCommands.COMPLETE_QUEST.getRegex())) {
                Matcher matcher = Pattern.compile(GameCommands.COMPLETE_QUEST.getRegex()).matcher(command);

                if (matcher.matches()) {
                    String questName = matcher.group("questName");
                    int questNumber = Integer.parseInt(matcher.group("questNumber"));

                    showMessage(controller.completeQuest(questName, questNumber).message());
                }
            }

            // ----------------------

            // ----- Shop View -----
            else if (command.matches(GameCommands.SHOW_ALL_PRODUCTS.getRegex())) {
                Result result = controller.showAllProducts();
                showMessage(result.message());
            }
            else if (command.matches(GameCommands.SHOW_AVAILABLE_PRODUCTS.getRegex())) {
                Result result = controller.showAvailableProducts();
                showMessage(result.message());
            }
            else if (command.matches(GameCommands.PURCHASE.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.PURCHASE.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String productName = matcher.group("productName").trim();

                    Result result = controller.purchase(productName, 1);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.PURCHASE_WITH_COUNT.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.PURCHASE_WITH_COUNT.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String productName = matcher.group("productName").trim();
                    int count = Integer.parseInt(matcher.group("count").trim());

                    Result result = controller.purchase(productName, count);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.CHEAT_ADD_DOLLARS.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.CHEAT_ADD_DOLLARS.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    int count = Integer.parseInt(matcher.group("count").trim());

                    Result result = controller.cheatAddDollars(count);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.SELL.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.SELL.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String productName = matcher.group("productName").trim();

                    Result result = controller.sell(productName);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.SELL_WITH_COUNT.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.SELL_WITH_COUNT.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String productName = matcher.group("productName").trim();
                    int count = Integer.parseInt(matcher.group("count").trim());

                    Result result = controller.sellWithCount(productName, count);
                    showMessage(result.message());
                }
            }
            // ---------------------

            else {
                showMessage("Invalid command!");
            }
        }
    }
}
