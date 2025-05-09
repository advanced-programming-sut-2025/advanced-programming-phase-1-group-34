package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.naturalElements.*;
import org.Group34.model.enums.creatorOfNaturalElements.CropCreator;
import org.Group34.model.enums.creatorOfNaturalElements.TreeCreator;
import org.Group34.model.items.Fertilizer;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.map.Space;
import org.Group34.model.time.Time;

public class FarmingController {
    private Space currentSpace; // TODO It will fix in GameController
    private Player currentPLayer; // TODO It will fix in GameController
    private Time time; // TODO It will fix in GameController


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
        } else if (!isSeedForCurrentSeason(getPlantBySeedName(seedName))) {
            return new Result(false, "Error: This plant is not suitable for this season.");
        }

        currentSpace.placingEntity(locationX, locationY, getPlantBySeedName(seedName));
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
}