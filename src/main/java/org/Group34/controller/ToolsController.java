package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.Lake;
import org.Group34.model.entities.naturalElements.Crop;
import org.Group34.model.entities.naturalElements.PloughedLand;
import org.Group34.model.entities.naturalElements.Tree;
import org.Group34.model.items.tools.*;

public class ToolsController { // TODO This class must be filled.
    private Player player; // TODO It will fix in GameController

    public Result toolsEquip(String toolName) {
        return new Result(true, "");
    }
    public Result showCurrentTools() {
        return new Result(true, "");
    }
    public Result showAvailableTools() {
        return new Result(true, "");
    }
    public Result toolsUpgrade(String toolName) {
        return new Result(true, "");
    }
    public Result toolUse(String direction) {
        FarmingController farmingController = new FarmingController();
        FishingController fishingController = new FishingController();
        AnimalHusbandryController animalHusbandryController = new AnimalHusbandryController();

        int locationX = getLocationOfDirectionX(direction);
        int locationY = getLocationOfDirectionY(direction);
        Entity desiredTile = player.getCurrentSpace().getEntityByLocation(locationX, locationY);

        if (player.getCurrentTool() instanceof Hoe) {
            return farmingController.useHoe(direction);
        }

        else if (player.getCurrentTool() instanceof Pickaxe) {
            if (desiredTile instanceof PloughedLand) {
                return farmingController.usePickaxe(direction);
            }
        }

        else if (player.getCurrentTool() instanceof Axe) {
            return farmingController.useAxe(direction);
        }

        else if (player.getCurrentTool() instanceof WateringCan) {
            return farmingController.useWateringCan(direction);
        }

        else if (player.getCurrentTool() instanceof Scythe) {
            if (desiredTile instanceof Crop || desiredTile instanceof Tree) {
                return farmingController.useScythe(direction);
            }
        }

        else if (player.getCurrentTool() instanceof MilkPail) {
            if (desiredTile instanceof Animal animal) {
                MilkPail milkPail = (MilkPail) player.getCurrentTool();
                if (milkPail.canMilk(animal.getAnimalType())) {
                    int x = getLocationOfDirectionX(direction);
                    int y = getLocationOfDirectionY(direction);
                    return animalHusbandryController.useMilkPail(player, x, y);
                }
                else {
                    return new Result(false, "This tool cannot be used on this type of animal.");
                }
            }
            else {
                return new Result(false, "You can only use Milk Pail on cows or goats.");
            }
        }

        else if (player.getCurrentTool() instanceof FishingPole) {
            if (desiredTile instanceof Lake) {
//                return fishingController.useFishingPole(player, currentSeason, weather);
            }
            else {
                return new Result(false, "You can only fish in water.");
            }
            //TODO add current season and weather and to game, then activate this part
        }


        return new Result(true, "");
    }

    private int getLocationOfDirectionX(String direction) {
        int playerLocation = player.getLocation()[0];
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
        int playerLocation = player.getLocation()[1];
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
}
