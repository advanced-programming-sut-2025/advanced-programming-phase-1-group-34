package org.Group34.model.entities;

import org.Group34.model.enums.LevelType;
import org.Group34.model.items.Item;
import org.Group34.model.items.Recipe;
import org.Group34.model.map.Space;

import java.util.ArrayList;
import java.util.HashMap;

public class  Player implements Entity {
    private int[] location;
    private Space currentSpace;
    private int energy = 200;
    private HashMap<Item, Integer> inventory = new HashMap<>();
    private ArrayList<Recipe> learnedRecipes = new ArrayList<>();
    private HashMap<LevelType, Integer> level = new HashMap<>(){{
        level.put(LevelType.FARMING_LEVEL, 0);
        level.put(LevelType.MINING_LEVEL, 0);
        level.put(LevelType.FORAGING_LEVEL, 0);
        level.put(LevelType.FISHING_LEVEL, 0);
    }};

    private Item currentTool;


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

    public void setEnergy(int energy) {
        this.energy = energy;
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

    public boolean isExistInInventory(Item item) {
        if (!inventory.containsKey(item)) {
            return false;
        } else if (getAmountOfItem(item) <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public HashMap<LevelType, Integer> getLevel() {
        return level;
    }

    public void levelUp(LevelType levelType){
        level.put(levelType, level.get(levelType));
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

    @Override
    public String toString() {
        return "P";
    }
}
