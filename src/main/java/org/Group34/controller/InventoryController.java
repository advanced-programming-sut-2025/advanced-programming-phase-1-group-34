package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.PlacingCraft;
import org.Group34.model.map.Space;

import java.util.HashMap;

public class InventoryController {
    private final static HashMap<String, int[]> direction = new HashMap<>(){{
        put("UpLeft" ,new int[]{-1 , -1});
        put("Up" ,new int[]{0 , -1});
        put("UpRight" ,new int[]{1 , -1});
        put("Right" ,new int[]{1 , 0});
        put("DownRight" ,new int[]{1 , 1});
        put("Down" ,new int[]{0 , 1});
        put("DownLeft" ,new int[]{-1 , 1});
        put("Left" ,new int[]{-1 , 0});
    }};

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

    public Result InventoryTrash(Player player, Item item, int number){
        boolean removed = player.removeFromInventory(item, number);

        if (removed) return new Result(true, "Item " + item.getName() + " has been removed from inventory");
        return new Result(false, "You don't have that item in your inventory");
    }

    public Result placeItem(Player player, Item item, Space space, String direction){
        if (!InventoryController.direction.containsKey(direction))
            return new Result(false, "Error: This direction isn't valid!");
        if (!player.getInventory().containsKey(item))
            return new Result(false, "Error: This Item isn't available in your Inventory!");

        int x = player.getLocation()[0] + InventoryController.direction.get(direction)[0];
        int y = player.getLocation()[1] + InventoryController.direction.get(direction)[1];

        if (item instanceof Entity){
            player.removeFromInventory(item, 1);

            if (item.equals(PlacingCraft.BOMB) || item.equals(PlacingCraft.CHERRY_BOMB) || item.equals(PlacingCraft.MEGA_BOMB)){
                ((PlacingCraft) item).place(space, x, y);
                return new Result(true, "Bomb exploded!");
            }
            if (item.equals(PlacingCraft.MYSTIC_TREE_SEED)){
                ((PlacingCraft) item).place(space, x, y);
                return new Result(true, "Mystic tree planted!");
            }
            if (item.equals(PlacingCraft.GRASS_STARTER)){
                ((PlacingCraft) item).place(space, x, y);
                return new Result(true, "Grass have been grown");
            }
            if (space.entities()[x][y] == null){
                space.entities()[x][y] = ((Entity) item);
                return new Result(true, "Item have been placed on map!");
            }
            else {
                player.addToInventory(item, 1);
                return new Result(false, "Error: You can not place Item in this location!");
            }
        }

        return new Result(false, "Error: This Item can not be placed on map!");
    }


    public Result cheatAddItem(Player player, Item item, int amount){
        player.addToInventory(item, amount);
        return new Result(true, "Cheat code activated: Item " + item.getName() + " has been added to player inventory.");
    }
}
