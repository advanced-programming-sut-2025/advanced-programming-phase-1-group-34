package org.Group34.model.entities;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.User;
import org.Group34.model.enums.LevelType;
import org.Group34.model.gameAssetManagers.PlayerAvatarManager;
import org.Group34.model.interactions.Gift;
import org.Group34.model.interactions.Interaction;
import org.Group34.model.items.Fertilizer;
import org.Group34.model.items.Item;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.Recipe;
import org.Group34.model.items.foods.Fruit;
import org.Group34.model.items.foods.OtherFarmingProduct;
import org.Group34.model.items.foods.Vegetable;
import org.Group34.model.items.tools.*;
import org.Group34.model.map.Space;

import java.util.ArrayList;
import java.util.HashMap;

public class  Player implements Entity {
    private static int passedOutUsers = 0;

    private String name;
    private int money;
    private int[] location;
    private Space currentSpace;
    private int energy = 500;
    private boolean passedOut = false;
    private final HashMap<Item, Integer> inventory = new HashMap<>();
    private final ArrayList<Recipe> learnedRecipes = new ArrayList<>();
    private HashMap<LevelType, Integer> levelUnit = createInitialLevelMap();
    private HashMap<LevelType, Integer> level = createInitialLevelMap();

    private HashMap<Player, Interaction> interactions = new HashMap<>();

    private Item currentTool = null;
    private String currentGameMenu = null;
    private Item currentItem = null;

    {
        inventory.put(new Hoe(ToolType.BASIC_HOE), 1);
        inventory.put(new Pickaxe(ToolType.BASIC_PICKAXE), 1);
        inventory.put(new Axe(ToolType.BASIC_AXE), 1);
        inventory.put(new WateringCan(ToolType.BASIC_WATERING_CAN), 1);
        inventory.put(new Scythe(), 1);
        inventory.put(new Backpack(ToolType.BASIC_BACKPACK), 1);
        inventory.put(new TrashCan(ToolType.IRIDIUM_TRASH_CAN), 1);
        inventory.put(PlantingSource.JAZZ_SEEDS, 20);
//        inventory.put(Fertilizer.SPEED_GROW, 10);
        inventory.put(new MilkPail(), 1);
        inventory.put(new FishingPole(ToolType.TRAINING_FISHING_POLE), 1);
        inventory.put(new Shear(), 1);
        inventory.put(Fruit.APPLE, 20);
        inventory.put(Fruit.ANCIENT_FRUIT, 10);
        inventory.put(Fruit.APRICOT, 20);
        inventory.put(Fruit.BLUEBERRY, 20);
        inventory.put(Fruit.BANANA, 20);
        inventory.put(Fruit.CRANBERRIES, 20);
        inventory.put(Fruit.CHERRY, 20);
        inventory.put(Fruit.POWDERMELON, 20);
        inventory.put(Fruit.MELON, 20);
        inventory.put(Fruit.MANGO, 20);
        inventory.put(Fruit.ORANGE, 20);
        inventory.put(Fruit.STARFRUIT, 20);
        inventory.put(Fruit.SWEET_GEM_BERRY, 20);
        inventory.put(Fruit.GRAPE, 20);
        inventory.put(Fruit.PEACH, 20);
        inventory.put(Vegetable.AMARANTH, 20);
        inventory.put(Vegetable.ARTICHOKE, 20);
        inventory.put(Vegetable.CARROT, 20);
        inventory.put(Vegetable.CAULIFLOWER, 20);
        inventory.put(Vegetable.KALE, 20);
        inventory.put(Vegetable.GARLIC, 20);
        inventory.put(Vegetable.EGGPLANT, 20);
        inventory.put(Vegetable.GREEN_BEAN, 20);
        inventory.put(Vegetable.PARSNIP, 20);
        inventory.put(Vegetable.POTATO, 20);
        inventory.put(Vegetable.RADISH, 20);
        inventory.put(Vegetable.RED_CABBAGE, 20);
        inventory.put(Vegetable.SUMMER_SQUASH, 20);
        inventory.put(Vegetable.TOMATO, 20);
        inventory.put(Vegetable.YAM, 20);
        inventory.put(Vegetable.BEET, 20);
        inventory.put(Vegetable.BROCCOLI, 20);
        inventory.put(Vegetable.BOK_CHOY, 20);
        inventory.put(Vegetable.CORN, 20);
        inventory.put(Vegetable.PUMPKIN, 20);
        inventory.put(Vegetable.HOT_PEPPER, 20);
        inventory.put(OtherFarmingProduct.BLUE_JAZZ, 10);
    }


    public static int passedOutUsers(){
        return Player.passedOutUsers;
    }


    public Player(int[] initialLocation) {
        this.location = initialLocation;
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean decreaseEnergy(int amount) {
        if (energy <= amount){
            energy = 0;
            this.setPassedOut(true);
            return false;
        }
        energy -= amount;
        return true;
    }

    public void setEnergy(int amount){
        energy = amount;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public void addToInventory(Item item, int amount){
        if (inventory.containsKey(item)){
            int beforeAmount = inventory.get(item);
            inventory.put(item, amount + beforeAmount);
        }
        else inventory.put(item, amount);
    }

    public HashMap<Item, Integer> getInventory() {
        return inventory;
    }

    public boolean removeFromInventory(Item item, int amount){
        if (inventory.containsKey(item)){
            int beforeAmount = inventory.get(item);
            inventory.put(item, beforeAmount - amount);
            if ((beforeAmount - amount) <= 0) {
                inventory.remove(item);
            }
            return true;
        }
        return false;
    }

    public int deleteFromInventory(Item item){
        if (inventory.containsKey(item)){
            return inventory.remove(item);
        }
        return 0;
    }

    public int getAmountOfItem(Item item) {
        Integer amount = inventory.get(item);
        return amount != null ? amount : 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public boolean isExistInInventory(Item item) {
        if (!inventory.containsKey(item)) {
            return false;
        } else if (getAmountOfItem(item) <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isExistInInventory(String name) {
        for (Item item : inventory.keySet()) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Item getItemFromInventoryByName(String name) {
        for (Item item : inventory.keySet()) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public int getLevel(LevelType levelType) {
        return level.get(levelType);
    }

    public void levelUp(LevelType levelType, int amount){
        level.put(levelType, level.get(levelType) + amount);
    }

    public HashMap<LevelType, Integer> getLevelUnit() {
        return levelUnit;
    }

    public void setLevelUnit(LevelType levelType, int amount) {
        levelUnit.put(levelType, amount);
    }

    public ArrayList<Recipe> getLearnedRecipes() {
        return learnedRecipes;
    }

    public void addToRecipe(Recipe recipe){
        learnedRecipes.add(recipe);
    }

    public Item getCurrentTool() {
        return currentTool;
    }
    public void setCurrentTool(Item tool) {
        this.currentTool = tool;
    }

    public boolean isPassedOut() {
        return passedOut;
    }

    public void setPassedOut(boolean passedOut) {
        Player.passedOutUsers++;
        this.passedOut = passedOut;
    }

    private HashMap<LevelType, Integer> createInitialLevelMap() {
        HashMap<LevelType, Integer> map = new HashMap<>();
        map.put(LevelType.FARMING_LEVEL, 0);
        map.put(LevelType.MINING_LEVEL, 0);
        map.put(LevelType.FORAGING_LEVEL, 0);
        map.put(LevelType.FISHING_LEVEL, 0);
        return map;
    }

    @Override
    public String toString() {
        return "P";
    }

    public void setInteractions(HashMap<User, Player> allPlayers) {
        for (User user : allPlayers.keySet()) {
            allPlayers.get(user).setName(user.getUsername());
        }
        for (User user : allPlayers.keySet()) {
            if (allPlayers.get(user) != this) {
                interactions.put(allPlayers.get(user), new Interaction());
            }
        }
    }

    public String showFriendships() {
        StringBuilder result = new StringBuilder();
        result.append(" ===== Friendships =====\n");

        for (Player player : interactions.keySet()) {
            result
                    .append("Name: " + player.getName() + "\n")
                    .append("xp: " + interactions.get(player).getXp() + "\n")
                    .append("Friendship Level: " + interactions.get(player).getLevel() + "\n\n");
        }

        return result.toString();
    }

    public Player getOtherPlayerByName(String username) {
        for (Player player : interactions.keySet()) {
            if (player.getName().equals(username)) {
                return player;
            }
        }
        return null;
    }

    public Interaction getInteractionByPlayer(Player player) {
        for (Player player1 : interactions.keySet()) {
            if (player1.equals(player)) {
                return interactions.get(player1);
            }
        }
        return null;
    }

    public String getGiftList() {
        StringBuilder result = new StringBuilder();
        int index = 1;
        result.append(" ===== Gift List =====\n");

        for (Interaction value : interactions.values()) {
            for (Gift gift : value.getGifts()) {
                if (gift.isReceived()) {
                    result
                            .append("Number: " + gift.getNumber() + "\n")
                            .append("item: " + gift.getItem().getName() + "\n")
                            .append("Amount: " + gift.getAmount() + "\n\n");
                }
                gift.setNumber(index);
                index++;
            }
        }

        return result.toString();
    }

    public Gift getGiftByNumber(int number) {
        for (Interaction value : interactions.values()) {
            for (Gift gift : value.getGifts()) {
                if (gift.getNumber() == number) {
                    return gift;
                }
            }
        }
        return null;
    }

    public Texture getTexture() {
        return PlayerAvatarManager.female_player1;
    }

    public String getCurrentGameMenu() {
        return currentGameMenu;
    }

    public void setCurrentGameMenu(String currentGameMenu) {
        this.currentGameMenu = currentGameMenu;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }
}
