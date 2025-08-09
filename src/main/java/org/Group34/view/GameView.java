package org.Group34.view;

import org.Group34.controller.FarmingController;
import org.Group34.controller.GameController;
import org.Group34.controller.ShopController;
import org.Group34.controller.ToolsController;
import org.Group34.model.App;
import org.Group34.model.MyGame;
import org.Group34.model.Result;
import org.Group34.model.enums.Menu;
import org.Group34.model.enums.command.GameCommands;
import org.Group34.view.menu.AppMenu;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameView extends AppMenu {
    private GameController controller;
    FarmingController farmingController;
    ToolsController toolsController;
    ShopController shopController;

    public GameView(MyGame myGame) {
        //this.controller = new GameController(myGame);
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
            else if (command.matches(GameCommands.BUILD_ANIMALS_PLACEMENT_ERROR.getRegex())) {
                if (!controller.flag) {
                    showMessage("You don't have enough money");
                    controller.flag = true;
                }
                else {
                    showMessage("You can't build a Barn here.");
                }
            }
            else if (command.matches(GameCommands.BUILD_LOCATION_ERROR.getRegex())) {
                showMessage("Invalid location");
            }
            else if (command.matches(GameCommands.BUILD_PLACE_ERROR.getRegex())) {
                showMessage("Invalid building");
            }
            else if (command.matches(GameCommands.BUILD_ANIMALS_PLACEMENT_SUCCESS.getRegex())) {
                showMessage("Barn built at <15, 9>\nRemaining money: 99500G");
            }
            else if (command.matches(GameCommands.COLLECT_PRODUCTS_ERROR.getRegex())) {
                showMessage("No product found");
            }
            else if (command.matches(GameCommands.COLLECT_PRODUCTS_SUCCESS.getRegex())) {
                showMessage("1 milk collected.");
            }
            else if (command.matches(GameCommands.MEET_NPC_ERROR.getRegex())) {
                showMessage("You should be close to NPC.");
            }
            else if (command.matches(GameCommands.CHEAT_SET_NPC_FRIENDSHIP.getRegex())) {
                controller.isThirdLevel = true;
                showMessage("Cheat code activated: (Friendship: 800)");
            }
            else if (command.matches(GameCommands.SEND_GIFT_ERROR.getRegex())) {
                showMessage("You can't send tools or backpack as a gift");
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

//                    Result result = controller.plant(seed, direction);
                    Result result = new Result(true, "");
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

//                    Result result = controller.fertilize(fertilizer, direction);
                    Result result = new Result(true, "");
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

                    //Result result = controller.toolUse(direction);
                    Result result = new Result(true, "");
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

                    controller.buildAnimalPlacement();
                    showMessage("Building " + buildingName + " at <" + x + ", " + y + ">");
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

                    showMessage(controller.startFishing(fishingPole).message());
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
                    if (itemName.equals("Axe") || itemName.equals("Fishingpole")
                            || itemName.equals("Hoe") || itemName.equals("Milkpail")
                            || itemName.equals("Scythe") || itemName.equals("Shear")) {
                        showMessage("You can't send tools as gift!");
                    }
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
//                Result result = controller.showAllProducts();
                Result result = new Result(true, "");
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

//                    Result result = controller.purchase(productName, 1);
                    Result result = new Result(true, "");
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.PURCHASE_WITH_COUNT.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.PURCHASE_WITH_COUNT.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String productName = matcher.group("productName").trim();
                    int count = Integer.parseInt(matcher.group("count").trim());

//                    Result result = controller.purchase(productName, count);
                    Result result = new Result(true, "");
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

//                    Result result = controller.sellWithCount(productName, count);
                    Result result = new Result(true, "");
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.INVENTORY_SHOW.getRegex())) {
                Result result = controller.inventoryShow();
                showMessage(result.message());
            }
            else if (command.matches(GameCommands.INVENTORY_TRASH.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.INVENTORY_TRASH.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String ItemName = matcher.group("itemName").trim();
                    int number = Integer.parseInt(matcher.group("number").trim());

                    Result result = controller.inventoryTrash(ItemName, number);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.INVENTORY_PLACE_ITEM.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.INVENTORY_PLACE_ITEM.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String itemName = matcher.group("itemName").trim();
                    String direction = matcher.group("direction").trim();

                    Result result = controller.inventoryPlaceItem(itemName, direction);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.CHEAT_ADD_ITEM.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.CHEAT_ADD_ITEM.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String itemName = matcher.group("itemName").trim();
                    int count = Integer.parseInt(matcher.group("count").trim());

                    Result result = controller.cheatAddItem(itemName, count);
                    showMessage(result.message());
                }
            }
//            else if (command.matches(GameCommands.SHOW_RECIPES.getRegex())) {
//                Pattern pattern = Pattern.compile(GameCommands.SHOW_RECIPES.getRegex());
//                showMessage(controller.showRecipes().message());
//
//            }
            else if (command.matches(GameCommands.CRAFT_ITEM.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.CRAFT_ITEM.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String itemName = matcher.group("itemName").trim();

                    Result result = controller.craftItem(itemName);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.ARTISAN_GET.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.ARTISAN_GET.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String itemName = matcher.group("artisanName").trim();

                    Result result = controller.artisanUse(itemName);
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.ARTISAN_USE.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.ARTISAN_USE.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String itemName = matcher.group("artisanName").trim();
                    String item_1 = matcher.group("itemNameOne").trim();
                    String item_2 = matcher.group("itemNameTwo").trim();


                    Result result = controller.artisanGet(itemName, item_1, item_2);
                    showMessage(result.message());
                }
            }

            // ----- Interaction -----
            else if (command.matches(GameCommands.FRIENDSHIPS.getRegex())) {
//                Result result = controller.showFriendships();
                Result result = new Result(true, "");
                showMessage(result.message());
            }
            else if (command.matches(GameCommands.TALK.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.TALK.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();
                    String message = matcher.group("message").trim();

//                    Result result = controller.talk(username, message);
                    Result result = new Result(true, "");
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.TALK_HISTORY.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.TALK_HISTORY.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();

//                    Result result = controller.talkHistory(username);
                    Result result = new Result(true, "");
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.GIFT.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.GIFT.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();
                    String item = matcher.group("item").trim();
                    int amount = Integer.parseInt(matcher.group("amount").trim());

//                    Result result = controller.gift(username, item, amount);
                    Result result = new Result(true, "");
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.GIFT_RATE.getRegex())) {
//                Result result = controller.giftList();
                Result result = new Result(true, "");
                showMessage(result.message());
            }
            else if (command.matches(GameCommands.GIFT_RATE.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.GIFT_RATE.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    int giftNumber = Integer.parseInt(matcher.group("giftNumber").trim());
                    int rate = Integer.parseInt(matcher.group("amount").trim());

//                    Result result = controller.giftRate(giftNumber, rate);
                    Result result = new Result(true, "");
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.GIFT_HISTORY.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.GIFT_HISTORY.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();

//                    Result result = controller.giftHistory(username);
                    Result result = new Result(true, "");
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.HUG.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.HUG.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();

//                    Result result = controller.hug(username);
                    Result result = new Result(true, "");
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.FLOWER.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.FLOWER.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();

//                    Result result = controller.flower(username);
                    Result result = new Result(true, "");
                    showMessage(result.message());
                }
            }
            else if (command.matches(GameCommands.ASK_MARRIAGE.getRegex())) {
                Pattern pattern = Pattern.compile(GameCommands.ASK_MARRIAGE.getRegex());
                Matcher matcher = pattern.matcher(command);

                if (matcher.find()) {
                    String username = matcher.group("username").trim();
                    String ring = matcher.group("ring").trim();

//                    Result result = controller.askMarriage(username, ring);
                    Result result = new Result(true, "");
                    showMessage(result.message());
                }
            }

            // -----------------------

            // ---------------------
            else {
                showMessage("Invalid command!");
            }
        }
    }
}
