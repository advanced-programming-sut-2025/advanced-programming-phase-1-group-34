package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.shops.*;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;
import org.Group34.model.items.tools.*;
import org.Group34.model.map.Space;

import java.util.ArrayList;

public class ShopController {
    private Player player; // TODO It will fix in GameController
    private Space space; // TODO It will fix in GameController

    public Result showAllProducts() {
        Entity playerTile = space.getEntityByLocation(player.getLocation()[0], player.getLocation()[1]);

        if (playerTile instanceof Blacksmith shop) {
            return new Result(true, shop.showAllProducts());
        }

        else if (playerTile instanceof MarnieRanch) {

        } else if (playerTile instanceof TheStardropSaloon) {

        } else if (playerTile instanceof CarpenterShop) {

        } else if (playerTile instanceof JojaMart) {

        } else if (playerTile instanceof PierreGeneralStore) {

        } else if (playerTile instanceof FishShop) {

        }

        return new Result(false, "Error: You first need to enter a shop.");
    }
    public Result showAvailableProducts() {
        Entity playerTile = space.getEntityByLocation(player.getLocation()[0], player.getLocation()[1]);

        if (playerTile instanceof Blacksmith shop) {
            return new Result(true, shop.showAvailableProducts());
        }

        else if (playerTile instanceof MarnieRanch) {

        } else if (playerTile instanceof TheStardropSaloon) {

        } else if (playerTile instanceof CarpenterShop) {

        } else if (playerTile instanceof JojaMart) {

        } else if (playerTile instanceof PierreGeneralStore) {

        } else if (playerTile instanceof FishShop) {

        }

        return new Result(false, "Error: You first need to enter a shop.");
    }
    public Result purchase(String productName, int count) {
        Entity playerTile = space.getEntityByLocation(player.getLocation()[0], player.getLocation()[1]);

        if (playerTile instanceof Blacksmith shop) {
            Item product = shop.getProductByName(productName);

            if (product == null) {
                return new Result(false, "Error: This product is not available in this store.");
            } else if (shop.getStockLimit(product) <= count - 1) {
                return new Result(false, "Error: This product is sold out.");
            }

            if (product instanceof Ingredient item) {
                if (player.getMoney() < item.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addToInventory(product, count);
                player.addMoney(item.getPrice() * count * -1);
                shop.buy(product, count);

                return new Result(true, "The desired product has been purchased.");
            }
            else if (product instanceof UpgradeTools tool) {
                if (player.getMoney() < tool.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addMoney(tool.getPrice() * -1);
                shop.buy(product, 1);
                upgradeTools(tool);

                return new Result(true, "The desired product has been purchased.");
            }
        }

        else if (playerTile instanceof MarnieRanch) {

        } else if (playerTile instanceof TheStardropSaloon) {

        } else if (playerTile instanceof CarpenterShop) {

        } else if (playerTile instanceof JojaMart) {

        } else if (playerTile instanceof PierreGeneralStore) {

        } else if (playerTile instanceof FishShop) {

        }

        return new Result(false, "Error: You first need to enter a shop.");
    }










    private void upgradeTools(UpgradeTools tool) {
        if (tool == UpgradeTools.COPPER_TOOL) {
            removeTools();
            player.addToInventory(new Axe(ToolType.COPPER_AXE), 1);
            player.addToInventory(new Hoe(ToolType.COPPER_HOE), 1);
            player.addToInventory(new Pickaxe(ToolType.COPPER_PICKAXE), 1);
            player.addToInventory(new WateringCan(ToolType.COPPER_WATERING_CAN), 1);
        } else if (tool == UpgradeTools.STEEL_TOOL) {
            removeTools();
            player.addToInventory(new Axe(ToolType.IRON_AXE), 1);
            player.addToInventory(new Hoe(ToolType.IRON_HOE), 1);
            player.addToInventory(new Pickaxe(ToolType.IRON_PICKAXE), 1);
            player.addToInventory(new WateringCan(ToolType.IRON_WATERING_CAN), 1);
        } else if (tool == UpgradeTools.GOLD_TOOL) {
            removeTools();
            player.addToInventory(new Axe(ToolType.GOLD_AXE), 1);
            player.addToInventory(new Hoe(ToolType.GOLD_HOE), 1);
            player.addToInventory(new Pickaxe(ToolType.GOLD_PICKAXE), 1);
            player.addToInventory(new WateringCan(ToolType.GOLD_WATERING_CAN), 1);
        } else if (tool == UpgradeTools.IRIDIUM_TOOL) {
            removeTools();
            player.addToInventory(new Axe(ToolType.IRIDIUM_AXE), 1);
            player.addToInventory(new Hoe(ToolType.IRIDIUM_HOE), 1);
            player.addToInventory(new Pickaxe(ToolType.IRIDIUM_PICKAXE), 1);
            player.addToInventory(new WateringCan(ToolType.IRIDIUM_WATERING_CAN), 1);
        } else if (tool == UpgradeTools.COPPER_TRASH_CAN) {
            removeTrashCan();
            player.addToInventory(new TrashCan(ToolType.COPPER_TRASH_CAN), 1);
        } else if (tool == UpgradeTools.STEEL_TRASH_CAN) {
            removeTrashCan();
            player.addToInventory(new TrashCan(ToolType.IRON_TRASH_CAN), 1);
        } else if (tool == UpgradeTools.GOLD_TRASH_CAN) {
            removeTrashCan();
            player.addToInventory(new TrashCan(ToolType.GOLD_TRASH_CAN), 1);
        } else if (tool == UpgradeTools.IRIDIUM_TRASH_CAN) {
            removeTrashCan();
            player.addToInventory(new TrashCan(ToolType.IRIDIUM_TRASH_CAN), 1);
        }
    }
    private void removeTools() {
        ArrayList<Item> remove = new ArrayList<>();

        for (Item item : player.getInventory().keySet()) {
            if (item instanceof Axe || item instanceof Hoe || item instanceof Pickaxe || item instanceof WateringCan) {
                remove.add(item);
            }
        }

        for (Item item : remove) {
            player.getInventory().remove(item);
        }
    }
    private void removeTrashCan() {
        ArrayList<Item> remove = new ArrayList<>();

        for (Item item : player.getInventory().keySet()) {
            if (item instanceof TrashCan) {
                remove.add(item);
            }
        }

        for (Item item : remove) {
            player.getInventory().remove(item);
        }
    }
}












