package org.Group34.controller;

import org.Group34.model.*;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.GreenHouse;
import org.Group34.model.entities.npcs.NPC;
import org.Group34.model.entities.npcs.quests.Quest;
import org.Group34.model.entities.npcs.quests.QuestLoader;
import org.Group34.model.enums.Color;
import org.Group34.model.items.tools.*;
import org.Group34.model.map.MapBuilder;
import org.Group34.view.menu.GameMenu;
import org.Group34.view.menu.MainMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final Game game;
    private final List<User> orderOfPlay = new ArrayList<>();
    private final int mainUser = 0;
    private int currentUser = 0;
    private final ArrayList<Boolean> forceTerminating = new ArrayList<>();
    private final AnimalController animalController = new AnimalController();
    public final GreenHouse greenhouse = new GreenHouse();
    public final GreenHouseController greenHouseController = new GreenHouseController(greenhouse);
    private final FishingController fishingController = new FishingController();
    private final FarmingController farmingController = new FarmingController();
    private final ToolsController toolsController = new ToolsController();
    private final StartANewDayController startANewDayController;
    private final LevelUpController levelUpController = new LevelUpController();
    private final List<NPC> npcs = npcLoader();
    private final ShopController shopController = new ShopController();
    private final InventoryController inventoryController = new InventoryController();

    public FarmingController getFarmingController() {
        return farmingController;
    }

    public ToolsController getToolsController() {
        return toolsController;
    }

    public ShopController getShopController() {
        return shopController;
    }

    public GameController(Game game){
        this.game = game;
        this.startANewDayController = new StartANewDayController(game, game.map().getSpaces(),game.time());
        setOrderOfPlay();
        game.weatherSystem().initializeWeather(game.time());
    }

    private List<NPC> npcLoader() {
        List<NPC> temp;
        try {
            temp = new QuestLoader().loadNPCs("model/entities/npcs/quests/npcs.json");
        } catch (IOException e) {
            e.printStackTrace();
            temp = new ArrayList<>();
        }
        return temp;
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
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        User current = orderOfPlay.get(currentUser);
        if (!current.equals(orderOfPlay.get(mainUser)))
            return new Result(false, "Only person that loaded game can exit (and only on their turn)");

        game.save();
        App.setCurrentGame(null);
        App.setAppMenu(new MainMenu());
        return new Result(true, "Game saved. Returning to menu.");
    }

    public Result deleteGame() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        forceTerminating.add(true);
        nextUser();

        return new Result(true,  "Force-terminate has been started. Do you want to delete game?(yes|no)");
    }

    public Result vote(String vote){
        if (forceTerminating.isEmpty())
            return new Result(false, "NO Force-terminate vote is in progress.");

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
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        nextUser();
        if (currentUser == 0){
            game.time().addHours(1);

            if (game.time().getHour() == 9){
                startANewDayController.ManageAllTasks();
                currentUser = 0;
                return new Result(true, "New day have been started. " + orderOfPlay.get(currentUser).getNickname() + " turn.");
            }
        }

        int placeHolder = currentUser;
        while (game.players().get(orderOfPlay.get(currentUser)).isPassedOut()){
            nextUser();
            if (currentUser == placeHolder){
                game.time().addHours(23 - game.time().getHour());
                startANewDayController.ManageAllTasks();
                currentUser = 0;
                return new Result(true,
                        "All players passed out and new day have been started. " + orderOfPlay.get(currentUser).getNickname() + " turn.");
            }
        }



        return new Result(true, orderOfPlay.get(currentUser).getNickname() + " turn.");
    }

    private void nextUser(){
        currentUser++;
        if (currentUser >= orderOfPlay.size()){
            currentUser = 0;
        }
    }

    public Result cheatAdvanceTime(String hours) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

       return game.time().cheatAdvanceTime(getInt(hours));
    }

    public Result cheatAdvanceDate(String days) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return game.time().cheatAdvanceTime(getInt(days));
    }

    public Result cheatChangeWeather(String weather) {
        return game.weatherSystem().cheatChangeWeather(weather);
    }

    public Result cheatAddMoney(String amount) {
        Player player = game.players().get(orderOfPlay.get(currentUser));
        player.addMoney(Integer.parseInt(amount));
        return new Result(true, "Cheat Code Activated: (" + player.getMoney() + ")");
    }

    public Result displayTime(String type){
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return game.time().displayTime(type);
    }

    public Result displayWeather(String type){
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return game.weatherSystem().displayWeather(type);
    }

    public Result walk(String x, String y) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        Integer targetX = getInt(x);
        Integer targetY = getInt(y);
        Player player = getPlayer();

        if (targetX == null || targetY == null)
            return new Result(false, "target location should be number format");
        if (targetX >= 100 && targetY >= 100)
            return new Result(false, "You're out of bound");

        if (player.getCurrentSpace().getEntityByLocation(targetX, targetY) != null)
            return new Result(false, "only can go to empty tiles of map");

        int distance = game.map().findPath(player, targetX, targetY);
        if (distance == 0)
            return new Result(false, "there is no path to target location");

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
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        Integer beginX = getInt(x);
        Integer beginY = getInt(y);
        Integer size = getInt(sz);
        Entity[][] entities = getPlayer().getCurrentSpace().entities();
        StringBuilder message = new StringBuilder();

        if (beginX == null || beginY == null || size == null)
            return new Result(false, "size or center location should be number format");

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
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

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

    public Result buildGreenhouse() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return greenHouseController.repairGreenhouse(game.players().get(orderOfPlay.get(currentUser)));
    }

    // ==================== Animals ===================
    public Result petAnimal(String name) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return animalController.petAnimal(name);
    }

    public Result listAnimals() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return animalController.listAnimals();
    }

    public Result shepherdAnimal(String name, int x, int y) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return animalController.shepherdAnimal(name, x, y);
    }

    public Result feedAnimal(String name) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return animalController.feedAnimal(name, getPlayer());
    }

    public Result collectProduct(String name) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return animalController.collectProductNow(name, getPlayer());
    }

    public Result showProducts() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return animalController.showProducts();
    }

    public Result buyAnimal(String animal, String animalName) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return animalController.buyAnimalNow(animal, animalName, getPlayer());
    }

    public Result sellAnimal(String name) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return animalController.sellAnimalNow(name, getPlayer());
    }

    public Result cheatAnimalFriendship(String name, int amount) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return animalController.cheatAnimalFriendShip(name, amount);
    }

    public Result startFishing(String fishingPole) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        Player player = game.players().get(orderOfPlay.get(currentUser));
        return fishingController.startFishing(player, game.time().getSeason(),
                game.weatherSystem().getTodayCondition(),
                (FishingPole) player.getItemFromInventoryByName(fishingPole));
    }

    // ==================== NPCs ===================
    public Result meetNPC(String name) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        for (NPC npc : npcs) {
            if (npc.getName().equals(name)) {
                npc.increaseFriendship(20);
                return new Result(true, npc.getDialogueBySeason(game.weatherSystem().getSeason()));
            }
        }
        return new Result(false, "No NPC found");
    }

    public Result listNPCs() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        StringBuilder result = new StringBuilder();

        for (NPC npc : this.npcs) {
            result.append(npc.getName()).append(": ").append(npc.getFriendshipPoints()).append("\n");
        }

        return new Result(true, result.toString());
    }

    public Result sendGift(String npcName, String itemName) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        for (NPC npc : npcs) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                if (npc.getLikedItems().contains(itemName)) {
                    npc.increaseFriendship(200);
                    return new Result(true, "They liked this item!\nFriendship increased by 200!");
                }
                else {
                    npc.increaseFriendship(50);
                    return new Result(true, "They Friendship increased by 50!");
                }
            }
        }
        return new Result(false, "No NPC found");
    }

    public Result listAvailableQuests() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        StringBuilder result = new StringBuilder();
        for (NPC npc : npcs) {
            for (Quest quest : npc.getQuests()) {
                if (npc.isQuestAvailable(quest, game.time())) {
                    result.append(quest.getTitle()).append("\n");
                }
            }
        }
        return new Result(true, result.toString());
    }

    public Result completeQuest(String npcName, int questNumber) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        for (NPC npc : npcs) {
            if (npc.getName().equalsIgnoreCase(npcName)) {
                for (Quest quest : npc.getQuests()) {
                    if (quest.getLevel() == questNumber) {
                        if (quest.isCompleted()) {
                            return new Result(false, "Quest is already completed by another player!");
                        }
                        else {
                            quest.completeQuest();
                            Player player = game.players().get(orderOfPlay.get(currentUser));
                            player.addMoney(quest.getRewardGold());
                            return new Result(true, "Quest completed!\nYou received "
                            + quest.getRewardGold() + " Golds!");
                        }
                    }
                }
                return new Result(false, "Quest not available!");
            }
        }
        return new Result(false, "No NPC found");
    }

    // ================================================

    // ==================== Farming Controller ====================
    public Result showCraftInfo(String craftName) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return farmingController.showCraftInfo(craftName);
    }

    public Result plant(String seedName, String direction) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return farmingController.plant(seedName, direction, getPlayer(), game.time());
    }

    public Result showPlant(int x, int y) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return farmingController.showPlant(x, y, getPlayer());
    }

    public Result fertilize(String fertilizer, String direction) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return farmingController.fertilize(fertilizer, direction, getPlayer());
    }

    public Result showAmountOfWater() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return farmingController.showAmountOfWater(getPlayer());
    }
    // ============================================================

    // ==================== Tools Controller ====================
    public Result toolsEquip(String toolName) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return toolsController.toolsEquip(toolName, getPlayer());
    }
    public Result showCurrentTools() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return toolsController.showCurrentTools(getPlayer());
    }
    public Result showAvailableTools() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return toolsController.showAvailableTools(getPlayer());
    }
    public Result toolUse(String direction) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return toolsController.toolUse(direction, farmingController, fishingController, animalController, getPlayer(), game.time(), game.weatherSystem(), levelUpController);
    }

    // ==========================================================

    // ==================== Shop Controller ====================
    public Result showAllProducts() {
        return shopController.showAllProducts(getPlayer());
    }
    public Result showAvailableProducts() {
        return shopController.showAvailableProducts(getPlayer(), game.time());
    }
    public Result purchase(String productName, int count) {
        return shopController.purchase(productName, count, getPlayer(), game.time());
    }
    public Result cheatAddDollars(int count) {
        return shopController.cheatAddDollars(count, getPlayer());
    }
    public Result sell(String productName) {
        return shopController.sell(productName, getPlayer());
    }
    public Result sellWithCount(String productName, int count) {
        return shopController.sellWithCount(productName, count, getPlayer());
    }

    // ==================== Inventory Controller ====================
    public Result cheatAddItem(String itemName, int count) {
        return inventoryController.cheatAddItem(getPlayer(), getPlayer().getItemFromInventoryByName(itemName), count);
    }

    public Result inventoryPlaceItem(String itemName, String direction) {
        return inventoryController.placeItem(getPlayer(), getPlayer().getItemFromInventoryByName(itemName), getPlayer().getCurrentSpace(), direction);
    }

    public Result inventoryTrash(String itemName, int number) {
        return inventoryController.InventoryTrash(getPlayer(), getPlayer().getItemFromInventoryByName(itemName), number);
    }

    public Result inventoryShow() {
        return inventoryController.showInventory(getPlayer().getInventory());
    }
    // =========================================================
}
