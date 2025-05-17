package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.Time;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.shops.*;
import org.Group34.model.entities.buildings.shops.products.ShippingBin;
import org.Group34.model.entities.buildings.shops.products.UpgradeTools;
import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.items.Fertilizer;
import org.Group34.model.items.Item;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.Recipe;
import org.Group34.model.items.crafting.Ingredient;
import org.Group34.model.items.crafting.PlacingCraft;
import org.Group34.model.items.foods.ProcessedFood;
import org.Group34.model.items.tools.*;
import org.Group34.model.map.Space;

import java.util.ArrayList;

public class ShopController {
    public Result showAllProducts(Player player) {
        Entity playerTile = getDesiredShop(player);

        if (playerTile instanceof Blacksmith shop) {
            return new Result(true, shop.showAllProducts());
        }

        else if (playerTile instanceof MarnieRanch shop) {
            return new Result(true, shop.showAllProducts());
        }

        else if (playerTile instanceof CarpenterShop shop) {
            return new Result(true, shop.showAllProducts());
        }

        else if (playerTile instanceof JojaMart shop) {
            return new Result(true, shop.showAllProducts());
        }

        else if (playerTile instanceof PierreGeneralStore shop) {
            return new Result(true, shop.showAllProducts());
        }

        else if (playerTile instanceof FishShop shop) {
            return new Result(true, shop.showAllProducts());
        }

        return new Result(false, "Error: You first need to enter a shop.");
    }
    public Result showAvailableProducts(Player player, Time time) {
        Entity playerTile = getDesiredShop(player);

        if (playerTile instanceof Blacksmith shop) {
            return new Result(true, shop.showAvailableProducts());
        }

        else if (playerTile instanceof MarnieRanch shop) {
            return new Result(true, shop.showAvailableProducts());
        }


        else if (playerTile instanceof CarpenterShop shop) {
            return new Result(true, shop.showAvailableProducts());
        }

        else if (playerTile instanceof JojaMart shop) {
            return new Result(true, shop.showAvailableProducts(time.getSeason()));
        }

        else if (playerTile instanceof PierreGeneralStore shop) {
            return new Result(true, shop.showAvailableProducts());
        }

        else if (playerTile instanceof FishShop shop) {
            return new Result(true, shop.shopAvailableProducts());
        }

        return new Result(false, "Error: You first need to enter a shop.");
    }
    public Result purchase(String productName, int count, Player player, Time time) {
        Entity playerTile = getDesiredShop(player);

        if (playerTile instanceof Blacksmith shop) {
            Item product = shop.getProductByName(productName);

            if (product == null) {
                return new Result(false, "Error: This product is not available in this store.");
            }

            if (product instanceof Ingredient item) {
                if (shop.getStockLimit(product) <= count - 1 && shop.getStockLimit(product) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < item.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addToInventory(product, count);
                player.addMoney(item.getPrice() * count * -1);
                shop.buy(product, count);

                return new Result(true, "The desired product has been purchased.");
            }
            else if (product instanceof UpgradeTools tool) {
                if (shop.getUpgradeToolLimit(product) <= count - 1 && shop.getUpgradeToolLimit(product) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < tool.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }


                player.addMoney(tool.getPrice() * count * -1);
                shop.buy(product, 1);
                upgradeTools(tool, player);

                return new Result(true, "The desired product has been purchased.");
            }
        }

        else if (playerTile instanceof MarnieRanch shop) {
            Item product = shop.getProductByName(productName);

            if (product == null) {
                return new Result(false, "Error: This product is not available in this store.");
            }

            if (product instanceof MilkPail tool) {
                if (shop.getShopInventoryLimit(tool) <= count - 1 && shop.getShopInventoryLimit(tool) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < tool.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }


                player.addMoney(tool.getPrice() * count * -1);
                shop.buy(product, count);
                player.addToInventory(new MilkPail(), 1);

                return new Result(true, "The desired product has been purchased.");
            }
            else if (product instanceof Shear tool) {
                if (shop.getShopInventoryLimit(tool) <= count - 1 && shop.getShopInventoryLimit(tool) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < tool.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }


                player.addMoney(tool.getPrice() * count * -1);
                shop.buy(product, count);
                player.addToInventory(new Shear(), 1);

                return new Result(true, "The desired product has been purchased.");
            }
            else if (product instanceof AnimalType animal) {
                if (shop.getShopInventoryLimit(animal) <= count - 1 && shop.getShopInventoryLimit(animal) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < animal.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }


                player.addMoney(animal.getPrice() * count * -1);
                shop.buy(product, count);
                player.addToInventory(animal, count);

                return new Result(true, "The desired product has been purchased.");
            }
        }

        else if (playerTile instanceof CarpenterShop shop) {
            Item product = shop.getProductByName(productName);

            if (product == null) {
                return new Result(false, "Error: This product is not available in this store.");
            }
            if (product instanceof Ingredient item) {
                if (shop.getPermanentStockLimit(product) <= count - 1 && shop.getPermanentStockLimit(product) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < item.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addToInventory(product, count);
                player.addMoney(item.getPrice() * count * -1);
                shop.buy(product, count);

                return new Result(true, "The desired product has been purchased.");
            }
            else if (product instanceof BarnType item) {
                if (shop.getFarmBuildingLimit(product) <= count - 1 && shop.getFarmBuildingLimit(product) == -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < item.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addToInventory(product, count);
                player.addMoney(item.getPrice() * count * -1);
                shop.buy(product, count);

                return new Result(true, "The desired product has been purchased.");
            }
            else if (product instanceof ShippingBin item) {
                if (shop.getFarmBuildingLimit(product) <= count - 1 && shop.getFarmBuildingLimit(product) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < item.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addToInventory(product, count);
                player.addMoney(item.getPrice() * count * -1);
                shop.buy(product, count);
                addShippingBin(count, player);

                return new Result(true, "The desired product has been purchased.");
            }
        }

        else if (playerTile instanceof JojaMart shop) {
            Item product = shop.getProductByName(productName);

            if (product == null) {
                return new Result(false, "Error: This product is not available in this store.");
            } else if (shop.isAvailable(product, count, time.getSeason())) {
                return new Result(false, "Error: This item is not available.");
            }

            PlantingSource item = (PlantingSource) product;
            if (player.getMoney() < item.getPrice() * count) {
                return new Result(false, "Error: You do not have enough balance.");
            }

            player.addMoney(item.getPrice() * count * -1);
            shop.buy(item, count);
            player.addToInventory(item, count);

            return new Result(true, "The desired product has been purchased.");
        }

        else if (playerTile instanceof PierreGeneralStore shop) {
            Item product = shop.getProductByName(productName);

            if (product == null) {
                return new Result(false, "Error: This product is not available in this store.");
            }

            if (PierreGeneralStore.getYearRoundStock().contains(product)) {
                if (product instanceof ProcessedFood stock) {
                    if (shop.getYearRoundStockLimit(product) <= count - 1 && shop.getYearRoundStockLimit(product) != -11) {
                        return new Result(false, "Error: This product is sold out.");
                    }
                    else if (player.getMoney() < stock.getPrice() * count) {
                        return new Result(false, "Error: You do not have enough balance.");
                    }

                    player.addMoney(stock.getPrice() * count * -1);
                    shop.buy(product, count);
                    player.addToInventory(product, count);

                    return new Result(true, "The desired product has been purchased.");
                } else if (product instanceof Fertilizer stock) {
                    if (shop.getYearRoundStockLimit(product) <= count - 1 && shop.getYearRoundStockLimit(product) != -11) {
                        return new Result(false, "Error: This product is sold out.");
                    }
                    else if (player.getMoney() < stock.getPrice() * count) {
                        return new Result(false, "Error: You do not have enough balance.");
                    }

                    player.addMoney(stock.getPrice() * count * -1);
                    shop.buy(product, count);
                    player.addToInventory(product, count);

                    return new Result(true, "The desired product has been purchased.");
                } else if (product instanceof PlacingCraft stock) {
                    if (shop.getYearRoundStockLimit(product) <= count - 1 && shop.getYearRoundStockLimit(product) != -11) {
                        return new Result(false, "Error: This product is sold out.");
                    }
                    else if (player.getMoney() < stock.getPrice() * count) {
                        return new Result(false, "Error: You do not have enough balance.");
                    }

                    player.addMoney(stock.getPrice() * count * -1);
                    shop.buy(product, count);
                    player.addToInventory(product, count);

                    return new Result(true, "The desired product has been purchased.");
                } else if (product instanceof PlantingSource stock) {
                    if (shop.getYearRoundStockLimit(product) <= count - 1 && shop.getYearRoundStockLimit(product) != -11) {
                        return new Result(false, "Error: This product is sold out.");
                    }
                    else if (player.getMoney() < stock.getPrice() * count) {
                        return new Result(false, "Error: You do not have enough balance.");
                    }

                    player.addMoney(stock.getPrice() * count * -1);
                    shop.buy(product, count);
                    player.addToInventory(product, count);

                    return new Result(true, "The desired product has been purchased.");
                } else if (product instanceof UpgradeTools stock) {
                    if (shop.getYearRoundStockLimit(product) <= count - 1 && shop.getYearRoundStockLimit(product) != -11) {
                        return new Result(false, "Error: This product is sold out.");
                    }
                    else if (player.getMoney() < stock.getPrice() * count) {
                        return new Result(false, "Error: You do not have enough balance.");
                    }

                    player.addMoney(stock.getPrice() * count * -1);
                    shop.buy(product, count);
                    upgradeBackpack(stock, player);

                    return new Result(true, "The desired product has been purchased.");
                }
            }
            else if (PierreGeneralStore.getSpringStock().contains(product)) {
                PlantingSource stock = (PlantingSource) product;

                if (shop.getSpringStockLimit(product) <= count - 1 && shop.getSpringStockLimit(product) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < stock.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addMoney(stock.getPrice() * count * -1);
                shop.buy(product, count);
                player.addToInventory(product, count);
            }
            else if (PierreGeneralStore.getSummerStock().contains(product)) {
                PlantingSource stock = (PlantingSource) product;

                if (shop.getSpringStockLimit(product) <= count - 1 && shop.getSpringStockLimit(product) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < stock.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addMoney(stock.getPrice() * count * -1);
                shop.buy(product, count);
                player.addToInventory(product, count);
            }
            else if (PierreGeneralStore.getFallStock().contains(product)) {
                PlantingSource stock = (PlantingSource) product;

                if (shop.getSpringStockLimit(product) <= count - 1 && shop.getSpringStockLimit(product) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < stock.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addMoney(stock.getPrice() * count * -1);
                shop.buy(product, count);
                player.addToInventory(product, count);
            }
        }

        else if (playerTile instanceof FishShop shop) {
            Item product = shop.getProductByName(productName);

            if (product == null) {
                return new Result(false, "Error: This product is not available in this store.");
            }

            if (product instanceof Recipe item) {
                if (shop.getStockLimit(product) <= count - 1 && shop.getStockLimit(product) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < item.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addToInventory(product, count);
                player.addMoney(item.getPrice() * count * -1);
                shop.buy(product, count);

                return new Result(true, "The desired product has been purchased.");
            }

            if (product instanceof UpgradeTools item) {
                if (shop.getStockLimit(product) <= count - 1 && shop.getStockLimit(product) != -11) {
                    return new Result(false, "Error: This product is sold out.");
                }
                else if (player.getMoney() < item.getPrice() * count) {
                    return new Result(false, "Error: You do not have enough balance.");
                }

                player.addMoney(item.getPrice() * count * -1);
                shop.buy(product, count);
                upgradeFishingPole(item, player);

                return new Result(true, "The desired product has been purchased.");
            }
        }

        return new Result(false, "Error: You first need to enter a shop.");
    }
    public Result cheatAddDollars(int count, Player player) {
        player.addMoney(count);
        return new Result(true, "count" + " dollars added to money");
    }
    public Result sell(String productName, Player player) {
        Entity playerTile = getSalePlace(player);

        if (!(playerTile instanceof SalePlace)) {
            return new Result(false, "Error: You should go to the sales location first.");
        }

        SalePlace salePlace = (SalePlace) playerTile;
        if (salePlace.getNumberOfShippingBins() <= 0) {
            return new Result(false, "Error: You do not have an empty Shipping Bin.");
        }

        Item desiredItem = player.getItemFromInventoryByName(productName);
        int count = player.getAmountOfItem(desiredItem);
        if (desiredItem == null) {
            return new Result(false, "Error: This item is not available in your inventory.");
        }
        else if (player.getAmountOfItem(desiredItem) < count) {
            return new Result(false, "Error: You do not have enough of this product available.");
        }

        salePlace.addItemToSale(desiredItem, count);
        player.removeFromInventory(desiredItem, count);
        return new Result(true, "The desired product has been successfully listed for sale.");
    }
    public Result sellWithCount(String productName, int count, Player player) {
        Entity playerTile = getSalePlace(player);

        if (!(playerTile instanceof SalePlace)) {
            return new Result(false, "Error: You should go to the sales location first.");
        }

        SalePlace salePlace = (SalePlace) playerTile;
        if (salePlace.getNumberOfShippingBins() <= 0) {
            return new Result(false, "Error: You do not have an empty Shipping Bin.");
        }

        Item desiredItem = player.getItemFromInventoryByName(productName);
        if (desiredItem == null) {
            return new Result(false, "Error: This item is not available in your inventory.");
        }
        else if (player.getAmountOfItem(desiredItem) < count) {
            return new Result(false, "Error: You do not have enough of this product available.");
        }

        salePlace.addItemToSale(desiredItem, count);
        player.removeFromInventory(desiredItem, count);
        return new Result(true, "The desired product has been successfully listed for sale.");
    }


    private void upgradeTools(UpgradeTools tool, Player player) {
        if (tool == UpgradeTools.COPPER_TOOL) {
            removeTools(player);
            player.addToInventory(new Axe(ToolType.COPPER_AXE), 1);
            player.addToInventory(new Hoe(ToolType.COPPER_HOE), 1);
            player.addToInventory(new Pickaxe(ToolType.COPPER_PICKAXE), 1);
            player.addToInventory(new WateringCan(ToolType.COPPER_WATERING_CAN), 1);
        } else if (tool == UpgradeTools.STEEL_TOOL) {
            removeTools(player);
            player.addToInventory(new Axe(ToolType.IRON_AXE), 1);
            player.addToInventory(new Hoe(ToolType.IRON_HOE), 1);
            player.addToInventory(new Pickaxe(ToolType.IRON_PICKAXE), 1);
            player.addToInventory(new WateringCan(ToolType.IRON_WATERING_CAN), 1);
        } else if (tool == UpgradeTools.GOLD_TOOL) {
            removeTools(player);
            player.addToInventory(new Axe(ToolType.GOLD_AXE), 1);
            player.addToInventory(new Hoe(ToolType.GOLD_HOE), 1);
            player.addToInventory(new Pickaxe(ToolType.GOLD_PICKAXE), 1);
            player.addToInventory(new WateringCan(ToolType.GOLD_WATERING_CAN), 1);
        } else if (tool == UpgradeTools.IRIDIUM_TOOL) {
            removeTools(player);
            player.addToInventory(new Axe(ToolType.IRIDIUM_AXE), 1);
            player.addToInventory(new Hoe(ToolType.IRIDIUM_HOE), 1);
            player.addToInventory(new Pickaxe(ToolType.IRIDIUM_PICKAXE), 1);
            player.addToInventory(new WateringCan(ToolType.IRIDIUM_WATERING_CAN), 1);
        } else if (tool == UpgradeTools.COPPER_TRASH_CAN) {
            removeTrashCan(player);
            player.addToInventory(new TrashCan(ToolType.COPPER_TRASH_CAN), 1);
        } else if (tool == UpgradeTools.STEEL_TRASH_CAN) {
            removeTrashCan(player);
            player.addToInventory(new TrashCan(ToolType.IRON_TRASH_CAN), 1);
        } else if (tool == UpgradeTools.GOLD_TRASH_CAN) {
            removeTrashCan(player);
            player.addToInventory(new TrashCan(ToolType.GOLD_TRASH_CAN), 1);
        } else if (tool == UpgradeTools.IRIDIUM_TRASH_CAN) {
            removeTrashCan(player);
            player.addToInventory(new TrashCan(ToolType.IRIDIUM_TRASH_CAN), 1);
        }
    }
    private void removeTools(Player player) {
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
    private void removeTrashCan(Player player) {
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
    private void upgradeBackpack(UpgradeTools stock, Player player) {
        Backpack backpack = null;
        for (Item item : player.getInventory().keySet()) {
            if (item instanceof Backpack backpack1) {
                backpack = backpack1;
            }
        }
        player.getInventory().remove(backpack);


        if (stock == UpgradeTools.BIG_BACKPACK) {
            backpack.setType(ToolType.BIG_BACKPACK);
            player.addToInventory(backpack, 1);
        }
        else if (stock == UpgradeTools.DELUXE_BACKPACK) {
            backpack.setType(ToolType.DELUXE_BACKPACK);
            player.addToInventory(backpack, 1);
        }
    }
    private void upgradeFishingPole(UpgradeTools stock, Player player) {
        removeFishingPole(player);
        if (stock == UpgradeTools.TRAINING_FISHING_POLE) {
            player.addToInventory(new FishingPole(ToolType.TRAINING_FISHING_POLE), 1);
        } else if (stock == UpgradeTools.BAMBOO_FISHING_POLE) {
            player.addToInventory(new FishingPole(ToolType.BAMBOO_FISHING_POLE), 1);
        } else if (stock == UpgradeTools.FIBERGLASS_FISHING_POLE) {
            player.addToInventory(new FishingPole(ToolType.FIBERGLASS_FISHING_POLE), 1);
        } else if (stock == UpgradeTools.IRIDIUM_FISHING_POLE) {
            player.addToInventory(new FishingPole(ToolType.IRIDIUM_FISHING_POLE), 1);
        }
    }
    private void removeFishingPole(Player player) {
        ArrayList<Item> remove = new ArrayList<>();

        for (Item item : player.getInventory().keySet()) {
            if (item instanceof FishShop) {
                remove.add(item);
            }
        }

        for (Item item : remove) {
            player.getInventory().remove(item);
        }
    }
    private void addShippingBin(int count, Player player) {
        Space space = player.getCurrentSpace();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (space.getEntityByLocation(i, j) instanceof SalePlace salePlace) {
                    salePlace.increaseNumberOfShippingBins(count);
                    return;
                }
            }
        }
    }
    private Entity getDesiredShop(Player player) {
        Space space = player.getCurrentSpace();

        int x = player.getLocation()[0];
        int y = player.getLocation()[1];

        Entity desiredShop = null;

        if (space.getEntityByLocation(x-1, y-1) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x-1, y) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x-1, y+1) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x, y-1) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x, y) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x, y+1) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x+1, y-1) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x+1, y) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x+1, y+1) instanceof Shop shop) {
            return shop;
        }

        return null;
    }
    private Entity getSalePlace(Player player) {
        Space space = player.getCurrentSpace();

        int x = player.getLocation()[0];
        int y = player.getLocation()[1];

        Entity desiredShop = null;

        if (space.getEntityByLocation(x-1, y-1) instanceof SalePlace shop) {
            return shop;
        } else if (space.getEntityByLocation(x-1, y) instanceof SalePlace shop) {
            return shop;
        } else if (space.getEntityByLocation(x-1, y+1) instanceof SalePlace shop) {
            return shop;
        } else if (space.getEntityByLocation(x, y-1) instanceof SalePlace shop) {
            return shop;
        } else if (space.getEntityByLocation(x, y) instanceof SalePlace shop) {
            return shop;
        } else if (space.getEntityByLocation(x, y+1) instanceof SalePlace shop) {
            return shop;
        } else if (space.getEntityByLocation(x+1, y-1) instanceof SalePlace shop) {
            return shop;
        } else if (space.getEntityByLocation(x+1, y) instanceof SalePlace shop) {
            return shop;
        } else if (space.getEntityByLocation(x+1, y+1) instanceof SalePlace shop) {
            return shop;
        }

        return null;
    }
}

