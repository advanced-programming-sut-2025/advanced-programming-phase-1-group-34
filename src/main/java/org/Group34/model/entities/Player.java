package org.Group34.model.entities;

import org.Group34.model.items.Item;
import org.Group34.model.map.Space;

import java.util.HashMap;

public class Player implements Entity {
    private int[] location;
    private Space currentSpace;
    private int energy = 200;
    private HashMap<Item, Integer> inventory = new HashMap<>();


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

    @Override
    public String toString() {
        return "P";
    }
}
