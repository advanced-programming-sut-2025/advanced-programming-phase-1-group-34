package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Player;
import org.Group34.model.items.Item;

import java.util.HashMap;

public class InventoryController {

    public Result showInventory(HashMap<Item, Integer> inventory){
        StringBuilder message = new StringBuilder();
        if (inventory.isEmpty())
            return new Result(false, "Your inventory is empty!");

        message.append("Inventory: \n");
        for (Item item: inventory.keySet()){
            message.append(item.getName()).append(": ").append(inventory.get(item)).append("\n");
        }

        return new Result(true, message.toString());
    }

    public Result InventoryTrash(Player player, Item item){
        //TODO user will input item name. what if there is no item with that name.
        int removed = player.deleteFromInventory(item);

        if (removed == 0) return new Result(false, "You don't have that item in your inventory");
        return new Result(false, "Item " + item.getName() + " has been removed from inventory");
    }
}
