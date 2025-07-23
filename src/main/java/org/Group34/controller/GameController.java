package org.Group34.controller;

import org.Group34.model.*;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.GreenHouse;
import org.Group34.model.entities.npcs.NPC;
import org.Group34.model.entities.npcs.quests.Quest;
import org.Group34.model.entities.npcs.quests.QuestLoader;
import org.Group34.model.enums.FishType;
import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.items.*;
import org.Group34.model.items.crafting.Craft;
import org.Group34.model.items.crafting.Ingredient;
import org.Group34.model.items.crafting.PlacingCraft;
import org.Group34.model.items.crafting.ProcessorCraft;
import org.Group34.model.items.foods.*;
import org.Group34.model.items.tools.*;
import org.Group34.view.menu.GameMenu;
import org.Group34.view.menu.MainMenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final MyGame myGame;
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
    private final HouseMenuController houseMenuController = new HouseMenuController();
    private final ArtisanController artisanController = new ArtisanController();
    private final InteractionController interactionController = new InteractionController();

    public boolean isThirdLevel = false;
    public boolean flag = false;

    public FarmingController getFarmingController() {
        return farmingController;
    }

    public ToolsController getToolsController() {
        return toolsController;
    }

    public ShopController getShopController() {
        return shopController;
    }

    public GameController(MyGame myGame){
        this.myGame = myGame;
        this.startANewDayController = new StartANewDayController(myGame, myGame.map().getSpaces(), myGame.time());
        setOrderOfPlay();
        myGame.weatherSystem().initializeWeather(myGame.time());
    }

    private List<NPC> npcLoader() {
        List<NPC> temp;
        try {
            temp = new QuestLoader().loadNPCs("src/main/resources/NPCQuests.json");
        } catch (IOException e) {
            e.printStackTrace();
            temp = new ArrayList<>();
        }
        return temp;
    }

    private void setOrderOfPlay() {
        // First User that adds to list is Main User(User that loads the myGame)
        orderOfPlay.add(App.getCurrentUser());

        for (User user: myGame.players().keySet())
            if (!user.equals(orderOfPlay.get(mainUser)))
                orderOfPlay.add(user);
    }


    public Result exitGame() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        User current = orderOfPlay.get(currentUser);
        if (!current.equals(orderOfPlay.get(mainUser)))
            return new Result(false, "Only person that loaded myGame can exit (and only on their turn)");

        myGame.save();
        App.setCurrentGame(null);
        App.setAppMenu(new MainMenu());
        return new Result(true, "MyGame saved. Returning to menu.");
    }

    public Result deleteGame() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        forceTerminating.add(true);
        nextUser();

        return new Result(true,  "Force-terminate has been started. Do you want to delete myGame?(yes|no)");
    }

    public Result vote(String vote){
        if (forceTerminating.isEmpty())
            return new Result(false, "NO Force-terminate vote is in progress.");

        forceTerminating.add(vote.equals("yes"));
        int votedUser = currentUser;
        nextUser();

        if (forceTerminating.size() >= orderOfPlay.size()){
            if (forceTerminating.contains(false))
                return new Result(false, "Someone disagrees to deleting myGame. User " + orderOfPlay.get(currentUser)+
                        " is playing now.");
            else {
                myGame.delete();
                App.setCurrentGame(null);
                App.setAppMenu(new GameMenu());
            }
        }

        return new Result(true, "User " + orderOfPlay.get(votedUser) + " voted "
                    + forceTerminating.get(votedUser) + "\nForce-terminate has been started. Do you want to delete myGame?(yes|no)" );

    }

    public Result nextTurn() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        nextUser();
        if (currentUser == 0){
            myGame.time().addHours(1);

            if (myGame.time().getHour() == 9){
                startANewDayController.ManageAllTasks();
                currentUser = 0;
                return new Result(true, "New day have been started. " + orderOfPlay.get(currentUser).getNickname() + " turn.");
            }
        }

        int placeHolder = currentUser;
        while (myGame.players().get(orderOfPlay.get(currentUser)).isPassedOut()){
            nextUser();
            if (currentUser == placeHolder){
                myGame.time().addHours(23 - myGame.time().getHour());
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

       return myGame.time().cheatAdvanceTime(getInt(hours));
    }

    public Result cheatAdvanceDate(String days) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        for (Animal animal : animalController.getAllAnimals()) {
            if (!animal.isCollected() || !animal.isFed() || !animal.isHasPet() || animal.isOutside()) {
                animal.decreaseFriendship(15);
            }

            animal.setHasPet(false);
            animal.setFed(false);
        }

        if (isThirdLevel) {
            System.out.println("Sebastian has sent you a Gift!");
        }

        return myGame.time().cheatAdvanceTime(getInt(days) * 14);
    }

    public Result cheatChangeWeather(String weather) {
        return myGame.weatherSystem().cheatChangeWeather(weather);
    }

    public Result cheatAddMoney(String amount) {
        Player player = myGame.players().get(orderOfPlay.get(currentUser));
        player.addMoney(Integer.parseInt(amount));
        return new Result(true, "Cheat Code Activated: (" + player.getMoney() + ")");
    }

    public Result displayTime(String type){
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return myGame.time().displayTime(type);
    }

    public Result displayWeather(String type){
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return myGame.weatherSystem().displayWeather(type);
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

        int distance = myGame.map().findPath(player, targetX, targetY);
        if (distance == 0)
            return new Result(false, "there is no path to target location");

        int energy = distance / 20;
        if (player.decreaseEnergy(energy)){
            myGame.map().movePlayer(player, targetX, targetY);
            return new Result(true, "Your character have been moved to: " + "<" + targetX + " ," + targetY + ">");
        }
        else{
            if (Player.passedOutUsers() >= orderOfPlay.size())
                myGame.time().addDays(1);
            return new Result(false, "Your character have been passed out.");
        }

    }

    private Player getPlayer() {
        return myGame.players().get(orderOfPlay.get(currentUser));
    }

    public Result printMap(String x, String y, String sz) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return myGame.map().printMap(getInt(x), getInt(y), getInt(sz), getPlayer().getCurrentSpace().entities());
    }

    public Result helpReadingMap() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        return myGame.map().helpMap();
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

        return greenHouseController.repairGreenhouse(myGame.players().get(orderOfPlay.get(currentUser)));
    }

    // ==================== Animals ===================
    public Result buildAnimalPlacement() {
        animalController.setBarn(animalController.getBarn() + 1);
        return new Result(true, "");
    }

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

        Player player = myGame.players().get(orderOfPlay.get(currentUser));
        return fishingController.startFishing(player, myGame.time().getSeason(),
                myGame.weatherSystem().getTodayCondition(),
                (FishingPole) player.getItemFromInventoryByName(fishingPole));
    }

    // ==================== NPCs ===================
    public Result meetNPC(String name) {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        for (NPC npc : npcs) {
            if (npc.getName().equals(name)) {
                npc.increaseFriendship(20);
                return new Result(true, npc.getDialogueBySeason(myGame.weatherSystem().getSeason()));
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

    private Item getItemByName(String itemName) {

       for (Item item: ProcessorCraft.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: PlacingCraft.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: PlantingSource.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: CookedFood.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: CropProduct.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: Fruit.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: Fungi.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: OtherFarmingProduct.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: Vegetable.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: ProcessorCraft.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: Mineral.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: Product.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: Recipe.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: Ingredient.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: AnimalType.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: BarnType.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: Fertilizer.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: FishType.values())
            if (item.getName().equals(itemName))
                return item;
        for (Item item: Recipe.values())
            if (item.getName().equals(itemName))
                return item;

        return null;
    }

    public Result listAvailableQuests() {
        if (!forceTerminating.isEmpty())
            return new Result(false, "Force-terminate vote in progress; you can only vote now");

        StringBuilder result = new StringBuilder();
        for (NPC npc : npcs) {
            for (Quest quest : npc.getQuests()) {
                if (npc.isQuestAvailable(quest, myGame.time())) {
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
                            Player player = myGame.players().get(orderOfPlay.get(currentUser));
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

        return farmingController.plant(seedName, direction, getPlayer(), myGame.time());
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

        return toolsController.toolUse(direction, farmingController, fishingController, animalController, getPlayer(), myGame.time(), myGame.weatherSystem(), levelUpController);
    }

    // ==========================================================

    // ==================== Shop Controller ====================
    public Result showAllProducts() {
        return shopController.showAllProducts(getPlayer());
    }
    public Result showAvailableProducts() {
        return shopController.showAvailableProducts(getPlayer(), myGame.time());
    }
    public Result purchase(String productName, int count) {
        return shopController.purchase(productName, count, getPlayer(), myGame.time());
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
        Item item = getItemByName(itemName);
        return inventoryController.cheatAddItem(getPlayer(), item, count);
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

    // ==================== Crafting Controller ====================
//    public Result showRecipes() {
//        return houseMenuController.showRecipes(getPlayer());
//    }

    public Result craftItem(String itemName) {
        Item item = this.getItemByName(itemName);
        if (item instanceof Craft)
            return houseMenuController.craftItem(getPlayer(), (Craft) item);
        if (item == null)
            return new Result(false, "This Item doesn't exist.");
        return new Result(false, "This Item can not be crafted");
    }

    // =========================================================

    // ==================== Artisan Controller ====================

    public Result artisanUse(String itemName) {
        Item item = getItemByName(itemName);
        if (item instanceof ProcessorCraft)
            return new Result(false, "This Item can not be used like that");
        if (item == null)
            return new Result(false, "This Item doesn't exist.");
        return artisanController.getArtisan((ProcessorCraft) item, getPlayer(), myGame.time());
    }

    public Result artisanGet(String itemName, String item_1, String item_2) {
        Item item = getItemByName(itemName);
        if (item instanceof ProcessorCraft)
            return new Result(false, "This Item can not be used like that");
        if (item == null)
            return new Result(false, "This Item doesn't exist.");
        return artisanController.useArtisan((ProcessorCraft) item, getPlayer(), myGame.time(), getItemByName(item_1), getItemByName(item_2));
    }

    // ----- Interaction -----
    public Result showFriendships() {
        return new Result(true, getPlayer().showFriendships());
    }
    public Result talk(String username, String message) {
        return interactionController.talk(username, message, getPlayer());
    }
    public Result talkHistory(String username) {
        return interactionController.talkHistory(username, getPlayer());
    }
    public Result gift(String username, String item, int amount) {
        return interactionController.gift(username, item, amount, getPlayer());
    }
    public Result giftList() {
        return interactionController.giftList(getPlayer());
    }
    public Result giftRate(int giftNumber, int rate) {
        return interactionController.giftRate(giftNumber, rate, getPlayer());
    }
    public Result giftHistory(String username) {
        return interactionController.giftHistory(username, getPlayer());
    }
    public Result hug(String username) {
        return interactionController.hug(username, getPlayer());
    }
    public Result flower(String username) {
        return interactionController.flower(username, getPlayer());
    }
    public Result askMarriage(String username, String ring) {
        return interactionController.askMarriage(username, ring, getPlayer());
    }


    public Result test() {
        return new Result(true, getPlayer().getName());
    }
}
