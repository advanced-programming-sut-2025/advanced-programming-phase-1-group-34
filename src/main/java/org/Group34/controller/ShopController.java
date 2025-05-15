package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.shops.*;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;
import org.Group34.model.map.Space;

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
    public Result purchase(String productName) {
        Entity playerTile = space.getEntityByLocation(player.getLocation()[0], player.getLocation()[1]);

        if (playerTile instanceof Blacksmith shop) {
            Item product = shop.getProductByName(productName);

            if (product == null) {
                return new Result(false, "Error: This product is not available in this store.");
            } else if (shop.getStockLimit(product) <= 0) {
                return new Result(false, "Error: This product is sold out.");
            }

            if (product instanceof Ingredient item) {
                if (player.getMoney() < item.getPrice()) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addToInventory(product, 1);
                player.addMoney(item.getPrice() * -1);
                shop.buy(product, 1);

                return new Result(true, "The desired product has been purchased.");
            }
            else if (product instanceof UpgradeTools tool) {
                if (player.getMoney() < tool.getPrice()) {
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
                if (player.getMoney() < item.getPrice()) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addToInventory(product, count);
                player.addMoney(item.getPrice() * count * -1);
                shop.buy(product, count);

                return new Result(true, "The desired product has been purchased.");
            }
            else if (product instanceof UpgradeTools tool) {
                if (player.getMoney() < tool.getPrice()) {
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
        // TODO
    }
}
