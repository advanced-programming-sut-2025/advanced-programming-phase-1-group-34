package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.Lake;
import org.Group34.model.entities.naturalElements.*;
import org.Group34.model.enums.LevelType;
import org.Group34.model.enums.Season;
import org.Group34.model.enums.WeatherCondition;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.enums.creatorOfNaturalElements.CropCreator;
import org.Group34.model.enums.creatorOfNaturalElements.TreeCreator;
import org.Group34.model.items.Fertilizer;
import org.Group34.model.items.Mineral;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.tools.*;
import org.Group34.model.map.Space;
import org.Group34.model.time.Time;

import java.util.Random;

public class FarmingController {
    private Space currentSpace; // TODO It will fix in GameController
    private Player currentPLayer; // TODO It will fix in GameController
    private Time time; // TODO It will fix in GameController

    private Hoe hoe;
    private Pickaxe pickaxe;
    private Axe axe;
    private WateringCan wateringCan;
    private Scythe scythe;


    public Result showCraftInfo(String craftName) {
        Entity plant = getPlantByCraftName(craftName);

        if (plant == null) {
            return new Result(false, "Error: This fruit does not exist.");
        } else if (plant instanceof Crop crop) {
            return new Result(true, crop.getStructuralInformation());
        } else {
            Tree tree = (Tree) plant;
            return new Result(true, tree.getStructuralInformation());
        }
    }


    public Result plant(String seedName, String direction) {
        int locationX = getLocationOfDirectionX(direction);
        int locationY = getLocationOfDirectionY(direction);
        Entity desiredTile = currentSpace.getEntityByLocation(locationX, locationY);

        if (desiredTile != null && !(desiredTile instanceof PloughedLand)) {
            return new Result(false, "Error: This tile is not empty.");
        } else if (desiredTile == null) {
            return new Result(false, "Error: You should plow the soil before planting.");
        } else if (!currentPLayer.isExistInInventory(getSeedByName(seedName))) {
            return new Result(false, "Error: You do not have this seed in your inventory.");
        } else if (getSeedByName(seedName) == PlantingSource.MIXED_SEEDS) {
            Entity randomPlant = getPlantOfMixedSeed();
            placingPlantInSpace(locationX, locationY, randomPlant);

            currentPLayer.removeFromInventory(getSeedByName(seedName), 1);
            return new Result(true, "The desired seed has been successfully planted.");
        }
        else if (!isSeedForCurrentSeason(getPlantBySeedName(seedName))) {
            return new Result(false, "Error: This plant is not suitable for this season.");
        }

        placingPlantInSpace(locationX, locationY, getPlantBySeedName(seedName));
        currentPLayer.removeFromInventory(getSeedByName(seedName), 1);

        return new Result(true, "The desired seed has been successfully planted.");
    }


    public Result showPlant(int x, int y) {
        Entity desiredPlant = currentSpace.getEntityByLocation(x, y);

        if (desiredPlant instanceof Crop crop) {
            return new Result(true, crop.getInformation());
        } else if (desiredPlant instanceof ForagingCrop foragingCrop) {
            return new Result(true, foragingCrop.getInformation());
        } else if (desiredPlant instanceof Tree tree) {
            return new Result(true, tree.getInformation());
        } else if (desiredPlant instanceof ForagingTree foragingTree) {
            return new Result(true, foragingTree.getInformation());
        }

        return new Result(false, "Error: There are no plants in this place.");
    }


    public Result fertilize(String fertilizer, String direction) {
        int locationX = getLocationOfDirectionX(direction);
        int locationY = getLocationOfDirectionY(direction);
        Entity desiredPlant = currentSpace.getEntityByLocation(locationX, locationY);

        if (!(desiredPlant instanceof Crop) && !(desiredPlant instanceof Tree)) {
            return new Result(false, "Error: You can only fertilize your plants.");
        } else if (!currentPLayer.isExistInInventory(getFertilizerByName(fertilizer))) {
            return new Result(false, "Error: You do not have this fertilize in your inventory.");
        }

        if (desiredPlant instanceof Crop crop) {
            crop.useFertilizer(getFertilizerByName(fertilizer));
        } else {
            Tree tree = (Tree) desiredPlant;
            tree.useFertilizer(getFertilizerByName(fertilizer));
        }

        return new Result(true, "The desired fertilizer was successfully given to the plant.");
    }


    public Result showAmountOfWater() {
        // TODO
        return new Result(true, "");
    }

    // ----- Use Tools -----
    public Result useHoe(String direction) {
        int locationX = getLocationOfDirectionX(direction);
        int locationY = getLocationOfDirectionY(direction);
        Entity desiredTile = currentSpace.getEntityByLocation(locationX, locationY);

//        currentPLayer.setEnergy(currentPLayer.getEnergy() - hoe.getEnergy()); TODO set it with special function

        if (desiredTile != null) {
            return new Result(false, "Error: You can't plow here.");
        }

        currentSpace.placingEntity(locationX, locationY, new PloughedLand());

        return new Result(true, "The desired tile has been plowed.");
    }

    public Result usePickaxe(String direction) {
        int x = getLocationOfDirectionX(direction);
        int y = getLocationOfDirectionY(direction);

        currentSpace.placingEntity(x, y, null);
        currentPLayer.setEnergy(currentPLayer.getEnergy() - pickaxe.getEnergy());

        return new Result(true, "The plowed land has disappeared.");
    }

    public Result useAxe(String direction) {
        int x = getLocationOfDirectionX(direction);
        int y = getLocationOfDirectionY(direction);
        Entity desiredTile = currentSpace.getEntityByLocation(x, y);

        currentPLayer.setEnergy(currentPLayer.getEnergy() - axe.getEnergy());

        if (!(desiredTile instanceof Tree) && !(desiredTile instanceof ForagingTree)) {
            return new Result(false, "Error: You can only use Axe on Trees");
        } else {
            currentSpace.placingEntity(x, y, null);
            // TODO Obtaining items resulting from tree cutting
        }

        return new Result(true, "The specified tree has been successfully cut down.");
    }

    public Result useWateringCan(String direction) {
        int x = getLocationOfDirectionX(direction);
        int y = getLocationOfDirectionY(direction);
        Entity desiredTile = currentSpace.getEntityByLocation(x, y);

        currentPLayer.setEnergy(currentPLayer.getEnergy() - wateringCan.getEnergy());

        if (desiredTile instanceof Lake) {
            wateringCan.fillIt();
            return new Result(true, "The watering can is filled.");
        } else if (desiredTile instanceof Crop crop) {
            crop.setNeedWater(false);
            return new Result(true, "The desired plant was irrigated.");
        } else if (desiredTile instanceof Tree tree) {
            tree.setNeedWater(false);
            return new Result(true, "The desired plant was irrigated.");
        }

        return new Result(false, "You can not use Watering Can here");
    }

    public Result useScythe(String direction) {
        int x = getLocationOfDirectionX(direction);
        int y = getLocationOfDirectionY(direction);
        Entity desiredTile = currentSpace.getEntityByLocation(x, y);

        currentPLayer.setEnergy(currentPLayer.getEnergy() - scythe.getEnergy());

        if (desiredTile instanceof Crop crop) {
            return harvestTheCrop(crop, x, y);
        } else if (desiredTile instanceof Tree tree) {
            return harvestTheTree(tree);
        }

        return new Result(false, "You can not use Scythe here");
    }



    // ---------------------


    private int getLocationOfDirectionX(String direction) {
        int playerLocation = currentPLayer.getLocation()[0];
        int location;

        if (direction.equals("Up") || direction.equals("UpRight") || direction.equals("UpLeft")) {
            location = playerLocation - 1;
        } else if (direction.equals("Down") || direction.equals("DownRight") || direction.equals("DownLeft")) {
            location = playerLocation + 1;
        } else {
            location = playerLocation;
        }

        return location;
    }
    private int getLocationOfDirectionY(String direction) {
        int playerLocation = currentPLayer.getLocation()[1];
        int location;

        if (direction.equals("Left") || direction.equals("UpLeft") || direction.equals("DownLeft")) {
            location = playerLocation - 1;
        } else if (direction.equals("Right") || direction.equals("UpRight") || direction.equals("DownRight")) {
            location = playerLocation + 1;
        } else {
            location = playerLocation;
        }

        return location;
    }
    private Entity getPlantBySeedName(String seed) {
        return switch (seed) {
            case "Jazz Seeds" -> CropCreator.BLUE_JAZZ.createInstance();
            case "Carrot Seeds" -> CropCreator.CARROT.createInstance();
            case "Cauliflower Seeds" -> CropCreator.CAULIFLOWER.createInstance();
            case "Coffee Bean" -> CropCreator.COFFEE_BEAN.createInstance();
            case "Garlic Seeds" -> CropCreator.GARLIC.createInstance();
            case "Bean Starter" -> CropCreator.GREEN_BEAN.createInstance();
            case "Kale Seeds" -> CropCreator.KALE.createInstance();
            case "Parsnip Seeds" -> CropCreator.PARSNIP.createInstance();
            case "Potato Seeds" -> CropCreator.POTATO.createInstance();
            case "Rhubarb Seeds" -> CropCreator.RHUBARB.createInstance();
            case "Strawberry Seeds" -> CropCreator.STRAWBERRY.createInstance();
            case "Tulip Bulb" -> CropCreator.TULIP.createInstance();
            case "Rice Shoot" -> CropCreator.UNMILLED_RICE.createInstance();
            case "Blueberry Seeds" -> CropCreator.BLUEBERRY.createInstance();
            case "Corn Seeds" -> CropCreator.CORN.createInstance();
            case "Hops Starter" -> CropCreator.HOPS.createInstance();
            case "Pepper Seeds" -> CropCreator.HOT_PEPPER.createInstance();
            case "Melon Seeds" -> CropCreator.MELON.createInstance();
            case "Poppy Seeds" -> CropCreator.POPPY.createInstance();
            case "Radish Seeds" -> CropCreator.RADISH.createInstance();
            case "Red Cabbage Seeds" -> CropCreator.RED_CABBAGE.createInstance();
            case "Starfruit Seeds" -> CropCreator.STARFRUIT.createInstance();
            case "Spangle Seeds" -> CropCreator.SUMMER_SPANGLE.createInstance();
            case "Summer Squash Seeds" -> CropCreator.SUMMER_SQUASH.createInstance();
            case "Sunflower Seeds" -> CropCreator.SUNFLOWER.createInstance();
            case "Tomato Seeds" -> CropCreator.TOMATO.createInstance();
            case "Wheat Seeds" -> CropCreator.WHEAT.createInstance();
            case "Amaranth Seeds" -> CropCreator.AMARANTH.createInstance();
            case "Artichoke Seeds" -> CropCreator.ARTICHOKE.createInstance();
            case "Beet Seeds" -> CropCreator.BEET.createInstance();
            case "Bok Choy Seeds" -> CropCreator.BOK_CHOY.createInstance();
            case "Broccoli Seeds" -> CropCreator.BROCCOLI.createInstance();
            case "Cranberry Seeds" -> CropCreator.CRANBERRIES.createInstance();
            case "Eggplant Seeds" -> CropCreator.EGGPLANT.createInstance();
            case "Fairy Seeds" -> CropCreator.FAIRY_ROSE.createInstance();
            case "Grape Starter" -> CropCreator.GRAPE.createInstance();
            case "Pumpkin Seeds" -> CropCreator.PUMPKIN.createInstance();
            case "Yam Seeds" -> CropCreator.YAM.createInstance();
            case "Rare Seed" -> CropCreator.SWEET_GEM_BERRY.createInstance();
            case "Powdermelon Seeds" -> CropCreator.POWDERMELON.createInstance();
            case "Ancient Seeds" -> CropCreator.ANCIENT_FRUIT.createInstance();
            case "Apricot Sapling" -> TreeCreator.APRICOT_TREE.createInstance();
            case "Cherry Sapling" -> TreeCreator.CHERRY_TREE.createInstance();
            case "Banana Sapling" -> TreeCreator.BANANA_TREE.createInstance();
            case "Mango Sapling" -> TreeCreator.MANGO_TREE.createInstance();
            case "Orange Sapling" -> TreeCreator.ORANGE_TREE.createInstance();
            case "Peach Sapling" -> TreeCreator.PEACH_TREE.createInstance();
            case "Apple Sapling" -> TreeCreator.APPLE_TREE.createInstance();
            case "Pomegranate Sapling" -> TreeCreator.POMEGRANATE_TREE.createInstance();
            case "Acorns" -> TreeCreator.OAK_TREE.createInstance();
            case "Maple Seeds" -> TreeCreator.MAPLE_TREE.createInstance();
            case "Pine Cones" -> TreeCreator.PINE_TREE.createInstance();
            case "Mahogany Seeds" -> TreeCreator.MAHOGANY_TREE.createInstance();
            case "Mushroom Tree Seeds" -> TreeCreator.MUSHROOM_TREE.createInstance();
            case "Mystic Tree Seeds" -> TreeCreator.MYSTIC_TREE.createInstance();
            default -> null;
        };
    }
    private PlantingSource getSeedByName(String seedName) {
        return switch (seedName) {
            case "Jazz Seeds" -> PlantingSource.JAZZ_SEEDS;
            case "Carrot Seeds" -> PlantingSource.CARROT_SEEDS;
            case "Cauliflower Seeds" -> PlantingSource.CAULIFLOWER_SEEDS;
            case "Coffee Bean" -> PlantingSource.COFFEE_BEAN;
            case "Garlic Seeds" -> PlantingSource.GARLIC_SEEDS;
            case "Bean Starter" -> PlantingSource.BEAN_STARTER;
            case "Kale Seeds" -> PlantingSource.KALE_SEEDS;
            case "Parsnip Seeds" -> PlantingSource.PARSNIP_SEEDS;
            case "Potato Seeds" -> PlantingSource.POTATO_SEEDS;
            case "Rhubarb Seeds" -> PlantingSource.RHUBARB_SEEDS;
            case "Strawberry Seeds" -> PlantingSource.STRAWBERRY_SEEDS;
            case "Tulip Bulb" -> PlantingSource.TULIP_BULB;
            case "Rice Shoot" -> PlantingSource.RICE_SHOOT;
            case "Blueberry Seeds" -> PlantingSource.BLUEBERRY_SEEDS;
            case "Corn Seeds" -> PlantingSource.CORN_SEEDS;
            case "Hops Starter" -> PlantingSource.HOPS_STARTER;
            case "Pepper Seeds" -> PlantingSource.PEPPER_SEEDS;
            case "Melon Seeds" -> PlantingSource.MELON_SEEDS;
            case "Poppy Seeds" -> PlantingSource.POPPY_SEEDS;
            case "Radish Seeds" -> PlantingSource.RADISH_SEEDS;
            case "Red Cabbage Seeds" -> PlantingSource.RED_CABBAGE_SEEDS;
            case "Starfruit Seeds" -> PlantingSource.STARFRUIT_SEEDS;
            case "Spangle Seeds" -> PlantingSource.SPANGLE_SEEDS;
            case "Summer Squash Seeds" -> PlantingSource.SUMMER_SQUASH_SEEDS;
            case "Sunflower Seeds" -> PlantingSource.SUNFLOWER_SEEDS;
            case "Tomato Seeds" -> PlantingSource.TOMATO_SEEDS;
            case "Wheat Seeds" -> PlantingSource.WHEAT_SEEDS;
            case "Amaranth Seeds" -> PlantingSource.AMARANTH_SEEDS;
            case "Artichoke Seeds" -> PlantingSource.ARTICHOKE_SEEDS;
            case "Beet Seeds" -> PlantingSource.BEET_SEEDS;
            case "Bok Choy Seeds" -> PlantingSource.BOK_CHOY_SEEDS;
            case "Broccoli Seeds" -> PlantingSource.BROCCOLI_SEEDS;
            case "Cranberry Seeds" -> PlantingSource.CRANBERRY_SEEDS;
            case "Eggplant Seeds" -> PlantingSource.EGGPLANT_SEEDS;
            case "Fairy Seeds" -> PlantingSource.FAIRY_SEEDS;
            case "Grape Starter" -> PlantingSource.GRAPE_STARTER;
            case "Pumpkin Seeds" -> PlantingSource.PUMPKIN_SEEDS;
            case "Yam Seeds" -> PlantingSource.YAM_SEEDS;
            case "Rare Seed" -> PlantingSource.RARE_SEED;
            case "Powdermelon Seeds" -> PlantingSource.POWDERMELON_SEEDS;
            case "Ancient Seeds" -> PlantingSource.ANCIENT_SEEDS;
            case "Apricot Sapling" -> PlantingSource.APRICOT_SAPLING;
            case "Cherry Sapling" -> PlantingSource.CHERRY_SAPLING;
            case "Banana Sapling" -> PlantingSource.BANANA_SAPLING;
            case "Mango Sapling" -> PlantingSource.MANGO_SAPLING;
            case "Orange Sapling" -> PlantingSource.ORANGE_SAPLING;
            case "Peach Sapling" -> PlantingSource.PEACH_SAPLING;
            case "Apple Sapling" -> PlantingSource.APPLE_SAPLING;
            case "Pomegranate Sapling" -> PlantingSource.POMEGRANATE_SAPLING;
            case "Acorns" -> PlantingSource.OAK_SAPLING;
            case "Maple Seeds" -> PlantingSource.MAPLE_SAPLING;
            case "Pine Cones" -> PlantingSource.PINE_SAPLING;
            case "Mahogany Seeds" -> PlantingSource.MAHOGANY_SAPLING;
            case "Mushroom Tree Seeds" -> PlantingSource.MUSHROOM_SAPLING;
            case "Mystic Tree Seeds" -> PlantingSource.MYSTIC_SAPLING;
            default -> null;
        };
    }
    private boolean isSeedForCurrentSeason(Entity plant) {
        if (plant instanceof Crop crop) {
            return crop.getSeasons().contains(time.getSeason());
        } else if (plant instanceof Tree tree) {
            return tree.getSeason().contains(time.getSeason());
        }
        return false;
    }
    private Fertilizer getFertilizerByName(String name) {
        return switch (name) {
            case "Deluxe Retaining Soil" -> Fertilizer.DELUXE_RETAINING_SOIL;
            case "Basic Retaining Soil" -> Fertilizer.BASIC_RETAINING_SOIL;
            case "Quality Retaining Soil" -> Fertilizer.QUALITY_RETAINING_SOIL;
            default -> null;
        };
    }
    private Entity getPlantByCraftName(String craftName) {
        return switch (craftName) {
            case "Blue Jazz" -> CropCreator.BLUE_JAZZ.createInstance();
            case "Carrot" -> CropCreator.CARROT.createInstance();
            case "Cauliflower" -> CropCreator.CAULIFLOWER.createInstance();
            case "Coffee Bean" -> CropCreator.COFFEE_BEAN.createInstance();
            case "Garlic" -> CropCreator.GARLIC.createInstance();
            case "Green Bean" -> CropCreator.GREEN_BEAN.createInstance();
            case "Kale" -> CropCreator.KALE.createInstance();
            case "Parsnip" -> CropCreator.PARSNIP.createInstance();
            case "Potato" -> CropCreator.POTATO.createInstance();
            case "Rhubarb" -> CropCreator.RHUBARB.createInstance();
            case "Strawberry" -> CropCreator.STRAWBERRY.createInstance();
            case "Tulip" -> CropCreator.TULIP.createInstance();
            case "Unmilled Rice" -> CropCreator.UNMILLED_RICE.createInstance();
            case "Blueberry" -> CropCreator.BLUEBERRY.createInstance();
            case "Corn" -> CropCreator.CORN.createInstance();
            case "Hops" -> CropCreator.HOPS.createInstance();
            case "Hot Pepper" -> CropCreator.HOT_PEPPER.createInstance();
            case "Melon" -> CropCreator.MELON.createInstance();
            case "Poppy" -> CropCreator.POPPY.createInstance();
            case "Radish" -> CropCreator.RADISH.createInstance();
            case "Red Cabbage" -> CropCreator.RED_CABBAGE.createInstance();
            case "Starfruit" -> CropCreator.STARFRUIT.createInstance();
            case "Summer Spangle" -> CropCreator.SUMMER_SPANGLE.createInstance();
            case "Summer Squash" -> CropCreator.SUMMER_SQUASH.createInstance();
            case "Sunflower" -> CropCreator.SUNFLOWER.createInstance();
            case "Tomato" -> CropCreator.TOMATO.createInstance();
            case "Wheat" -> CropCreator.WHEAT.createInstance();
            case "Amaranth" -> CropCreator.AMARANTH.createInstance();
            case "Artichoke" -> CropCreator.ARTICHOKE.createInstance();
            case "Beet" -> CropCreator.BEET.createInstance();
            case "Bok Choy" -> CropCreator.BOK_CHOY.createInstance();
            case "Broccoli" -> CropCreator.BROCCOLI.createInstance();
            case "Cranberries" -> CropCreator.CRANBERRIES.createInstance();
            case "Eggplant" -> CropCreator.EGGPLANT.createInstance();
            case "Fairy Rose" -> CropCreator.FAIRY_ROSE.createInstance();
            case "Grape" -> CropCreator.GRAPE.createInstance();
            case "Pumpkin" -> CropCreator.PUMPKIN.createInstance();
            case "Yam" -> CropCreator.YAM.createInstance();
            case "Sweet Gem Berry" -> CropCreator.SWEET_GEM_BERRY.createInstance();
            case "Powdermelon" -> CropCreator.POWDERMELON.createInstance();
            case "Ancient Fruit" -> CropCreator.ANCIENT_FRUIT.createInstance();

            case "Apricot" -> TreeCreator.APRICOT_TREE.createInstance();
            case "Cherry" -> TreeCreator.CHERRY_TREE.createInstance();
            case "Banana" -> TreeCreator.BANANA_TREE.createInstance();
            case "Mango" -> TreeCreator.MANGO_TREE.createInstance();
            case "Orange" -> TreeCreator.ORANGE_TREE.createInstance();
            case "Peach" -> TreeCreator.PEACH_TREE.createInstance();
            case "Apple" -> TreeCreator.APPLE_TREE.createInstance();
            case "Pomegranate" -> TreeCreator.POMEGRANATE_TREE.createInstance();
            case "Oak Resin" -> TreeCreator.OAK_TREE.createInstance();
            case "Maple Syrup" -> TreeCreator.MAPLE_TREE.createInstance();
            case "Pine Tar" -> TreeCreator.PINE_TREE.createInstance();
            case "Sap" -> TreeCreator.MAHOGANY_TREE.createInstance();
            case "Common Mushroom" -> TreeCreator.MUSHROOM_TREE.createInstance();
            case "Mystic Syrup" -> TreeCreator.MYSTIC_TREE.createInstance();
            default -> null;
        };

    }
    private void placingPlantInSpace(int x, int y, Entity plant) {
        if (!(plant instanceof Tree)) {
            currentSpace.placingEntity(x, y, plant);
        } else {
            Crop crop = (Crop) plant;
            if (!crop.isCanBecomeGiant()) {
                currentSpace.placingEntity(x, y, plant);
            } else {
                checkAndBecomeGiant(x, y, crop);
            }
        }
    }
    private void checkAndBecomeGiant(int x, int y, Crop crop) {
        if (currentSpace.getEntityByLocation(x-1, y-1) instanceof Crop crop1 &&
            currentSpace.getEntityByLocation(x-1, y) instanceof Crop crop2 &&
            currentSpace.getEntityByLocation(x, y-1) instanceof Crop crop3 &&
            crop1.getName().equals(crop.getName()) &&
            crop2.getName().equals(crop.getName()) &&
            crop3.getName().equals(crop.getName()) &&
            !crop1.isGiant() &&
            !crop2.isGiant() &&
            !crop3.isGiant()) {

            crop.setGiant(true);
            crop.setAge(Math.max(crop.getAge(), crop1.getAge()));
            crop.setAge(Math.max(crop.getAge(), crop2.getAge()));
            crop.setAge(Math.max(crop.getAge(), crop3.getAge()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop1.getGrowthLevel()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop2.getGrowthLevel()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop3.getGrowthLevel()));
            if (crop.getHarvested() || crop1.getHarvested() || crop2.getHarvested() || crop3.getHarvested()) {
                crop.setHarvested(true);
            }
            if (!crop.getNeedWater() || !crop1.getNeedWater() || !crop2.getNeedWater() || !crop3.getNeedWater()) {
                crop.setNeedWater(false);
            }

            if (crop.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                crop1.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                crop2.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                crop3.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.DELUXE_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            } else if (crop.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                       crop1.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                       crop2.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                       crop3.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.QUALITY_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            } else if (crop.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop1.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop2.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop3.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.BASIC_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            }

            currentSpace.placingEntity(x-1, y-1, crop);
            currentSpace.placingEntity(x-1, y, crop);
            currentSpace.placingEntity(x, y-1, crop);
            currentSpace.placingEntity(x, y, crop);
        }
        else if (currentSpace.getEntityByLocation(x-1, y) instanceof Crop crop1 &&
                currentSpace.getEntityByLocation(x-1, y+1) instanceof Crop crop2 &&
                currentSpace.getEntityByLocation(x, y+1) instanceof Crop crop3 &&
                crop1.getName().equals(crop.getName()) &&
                crop2.getName().equals(crop.getName()) &&
                crop3.getName().equals(crop.getName()) &&
                !crop1.isGiant() &&
                !crop2.isGiant() &&
                !crop3.isGiant()) {

            crop.setGiant(true);
            crop.setAge(Math.max(crop.getAge(), crop1.getAge()));
            crop.setAge(Math.max(crop.getAge(), crop2.getAge()));
            crop.setAge(Math.max(crop.getAge(), crop3.getAge()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop1.getGrowthLevel()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop2.getGrowthLevel()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop3.getGrowthLevel()));
            if (crop.getHarvested() || crop1.getHarvested() || crop2.getHarvested() || crop3.getHarvested()) {
                crop.setHarvested(true);
            }
            if (!crop.getNeedWater() || !crop1.getNeedWater() || !crop2.getNeedWater() || !crop3.getNeedWater()) {
                crop.setNeedWater(false);
            }

            if (crop.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                    crop1.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                    crop2.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                    crop3.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.DELUXE_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            } else if (crop.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                    crop1.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                    crop2.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                    crop3.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.QUALITY_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            } else if (crop.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop1.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop2.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop3.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.BASIC_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            }

            currentSpace.placingEntity(x-1, y, crop);
            currentSpace.placingEntity(x-1, y+1, crop);
            currentSpace.placingEntity(x, y+1, crop);
            currentSpace.placingEntity(x, y, crop);
        }
        else if (currentSpace.getEntityByLocation(x, y-1) instanceof Crop crop1 &&
                currentSpace.getEntityByLocation(x+1, y-1) instanceof Crop crop2 &&
                currentSpace.getEntityByLocation(x+1, y) instanceof Crop crop3 &&
                crop1.getName().equals(crop.getName()) &&
                crop2.getName().equals(crop.getName()) &&
                crop3.getName().equals(crop.getName()) &&
                !crop1.isGiant() &&
                !crop2.isGiant() &&
                !crop3.isGiant()) {

            crop.setGiant(true);
            crop.setAge(Math.max(crop.getAge(), crop1.getAge()));
            crop.setAge(Math.max(crop.getAge(), crop2.getAge()));
            crop.setAge(Math.max(crop.getAge(), crop3.getAge()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop1.getGrowthLevel()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop2.getGrowthLevel()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop3.getGrowthLevel()));
            if (crop.getHarvested() || crop1.getHarvested() || crop2.getHarvested() || crop3.getHarvested()) {
                crop.setHarvested(true);
            }
            if (!crop.getNeedWater() || !crop1.getNeedWater() || !crop2.getNeedWater() || !crop3.getNeedWater()) {
                crop.setNeedWater(false);
            }

            if (crop.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                    crop1.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                    crop2.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                    crop3.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.DELUXE_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            } else if (crop.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                    crop1.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                    crop2.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                    crop3.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.QUALITY_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            } else if (crop.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop1.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop2.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop3.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.BASIC_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            }

            currentSpace.placingEntity(x, y-1, crop);
            currentSpace.placingEntity(x+1, y-1, crop);
            currentSpace.placingEntity(x+1, y, crop);
            currentSpace.placingEntity(x, y, crop);
        }
        else if (currentSpace.getEntityByLocation(x, y+1) instanceof Crop crop1 &&
                currentSpace.getEntityByLocation(x+1, y) instanceof Crop crop2 &&
                currentSpace.getEntityByLocation(x+1, y+1) instanceof Crop crop3 &&
                crop1.getName().equals(crop.getName()) &&
                crop2.getName().equals(crop.getName()) &&
                crop3.getName().equals(crop.getName()) &&
                !crop1.isGiant() &&
                !crop2.isGiant() &&
                !crop3.isGiant()) {

            crop.setGiant(true);
            crop.setAge(Math.max(crop.getAge(), crop1.getAge()));
            crop.setAge(Math.max(crop.getAge(), crop2.getAge()));
            crop.setAge(Math.max(crop.getAge(), crop3.getAge()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop1.getGrowthLevel()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop2.getGrowthLevel()));
            crop.setGrowthLevel(Math.max(crop.getGrowthLevel(), crop3.getGrowthLevel()));
            if (crop.getHarvested() || crop1.getHarvested() || crop2.getHarvested() || crop3.getHarvested()) {
                crop.setHarvested(true);
            }
            if (!crop.getNeedWater() || !crop1.getNeedWater() || !crop2.getNeedWater() || !crop3.getNeedWater()) {
                crop.setNeedWater(false);
            }

            if (crop.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                    crop1.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                    crop2.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL ||
                    crop3.getFertilizer() == Fertilizer.DELUXE_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.DELUXE_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            } else if (crop.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                    crop1.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                    crop2.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL ||
                    crop3.getFertilizer() == Fertilizer.QUALITY_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.QUALITY_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            } else if (crop.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop1.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop2.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL ||
                    crop3.getFertilizer() == Fertilizer.BASIC_RETAINING_SOIL) {
                crop.setFertilizer(Fertilizer.BASIC_RETAINING_SOIL);
                crop.setGivenFertilizer(true);
            }

            currentSpace.placingEntity(x, y+1, crop);
            currentSpace.placingEntity(x+1, y, crop);
            currentSpace.placingEntity(x+1, y+1, crop);
            currentSpace.placingEntity(x, y, crop);
        } else {
            currentSpace.placingEntity(x, y, crop);
        }
    }
    private Entity getPlantOfMixedSeed() {
        Random rand = new Random();
        if (time.getSeason() == Season.SPRING) {
            int randInt = rand.nextInt(5);

            if (randInt == 0) {
                return CropCreator.CAULIFLOWER.createInstance();
            } else if (randInt == 1) {
                return CropCreator.PARSNIP.createInstance();
            } else if (randInt == 2) {
                return CropCreator.POTATO.createInstance();
            } else if (randInt == 3) {
                return CropCreator.BLUE_JAZZ.createInstance();
            } else {
                return CropCreator.TULIP.createInstance();
            }
        }

        else if (time.getSeason() == Season.SUMMER) {
            int randInt = rand.nextInt(7);

            if (randInt == 0) {
                return CropCreator.CORN.createInstance();
            } else if (randInt == 1) {
                return CropCreator.HOT_PEPPER.createInstance();
            } else if (randInt == 2) {
                return CropCreator.RADISH.createInstance();
            } else if (randInt == 3) {
                return CropCreator.WHEAT.createInstance();
            } else if (randInt == 4) {
                return CropCreator.POPPY.createInstance();
            } else if (randInt == 5) {
                return CropCreator.SUNFLOWER.createInstance();
            } else {
                return CropCreator.SUMMER_SPANGLE.createInstance();
            }
        }

        else if (time.getSeason() == Season.AUTUMN) {
            int randInt = rand.nextInt(6);

            if (randInt == 0) {
                return CropCreator.ARTICHOKE.createInstance();
            } else if (randInt == 1) {
                return CropCreator.CORN.createInstance();
            } else if (randInt == 2) {
                return CropCreator.EGGPLANT.createInstance();
            } else if (randInt == 3) {
                return CropCreator.PUMPKIN.createInstance();
            } else if (randInt == 4) {
                 return CropCreator.SUNFLOWER.createInstance();
            } else {
                return CropCreator.FAIRY_ROSE.createInstance();
            }
        }

        else {
            return CropCreator.POWDERMELON.createInstance();
        }
    }
    private Result harvestTheCrop(Crop crop, int x, int y) {
        if (crop.isGiant()) {
            if (crop.getGrowthLevel() == crop.getMaxLevel()) {
                currentPLayer.addToInventory(crop.getFarmingProduct(), 10);

                if (crop.isOneTime()) {
                    removeGiantCrops(crop, x, y);
                } else {
                    crop.harvest();
                }

                return new Result(true, "The desired plant has been harvested.");
            }
            else {
                return new Result(false, "The plant in question has not yet reached the harvesting stage.");
            }
        }
        else {
            if (crop.getGrowthLevel() == crop.getMaxLevel()) {
                currentPLayer.addToInventory(crop.getFarmingProduct(), 1);

                if (crop.isOneTime()) {
                    currentSpace.placingEntity(x, y, null);
                } else {
                    crop.harvest();
                }

                return new Result(true, "The desired plant has been harvested.");
            }
            else {
                return new Result(false, "The plant in question has not yet reached the harvesting stage.");
            }
        }
    }
    private Result harvestTheTree(Tree tree) {
        if (tree.isBurned()) {
            currentPLayer.addToInventory(Mineral.COAL, 1);
            return new Result(true, "The tree in question was burned due to being struck by lightning and coal was harvested.");
        }
        else if (tree.getGrowthLevel() == tree.getMaxLevel()) {
            currentPLayer.addToInventory(tree.getFruit(), 1);

            tree.harvest();

            return new Result(true, "The desired plant has been harvested.");
        }
        return new Result(true, "The plant in question has not yet reached the harvesting stage.");
    }
    private void removeGiantCrops(Crop crop, int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < 100 && j >= 0 && j < 100) {
                    if (currentSpace.getEntityByLocation(i, j) == crop) {
                        currentSpace.placingEntity(i, j, null);
                    }
                }
            }
        }
    }
}