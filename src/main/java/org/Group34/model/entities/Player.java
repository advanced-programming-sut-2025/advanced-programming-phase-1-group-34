package org.Group34.model.entities;

import org.Group34.model.enums.LevelType;
import org.Group34.model.items.Item;
import org.Group34.model.items.Recipe;
import org.Group34.model.map.Space;

import java.util.ArrayList;
import java.util.HashMap;

public class  Player implements Entity {
    private static int passedOutUsers = 0;

    private String name;
    private int money;
    private int[] location;
    private Space currentSpace;
    private int energy = 200;
    private boolean passedOut = false;
    private final HashMap<Item, Integer> inventory = new HashMap<>();
    private final ArrayList<Recipe> learnedRecipes = new ArrayList<>();
    private HashMap<LevelType, Integer> levelUnit = createInitialLevelMap();
    private HashMap<LevelType, Integer> level = createInitialLevelMap();

    private Item currentTool;


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
        return inventory.get(item);
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
}
