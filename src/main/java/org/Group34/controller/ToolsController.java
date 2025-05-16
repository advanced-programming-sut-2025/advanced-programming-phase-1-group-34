package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.Lake;
import org.Group34.model.entities.naturalElements.PloughedLand;
import org.Group34.model.enums.LevelType;
import org.Group34.model.enums.WeatherCondition;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.items.tools.*;

public class ToolsController { // TODO This class must be filled.
    private Player player; // TODO It will fix in GameController
    private WeatherSystem weatherSystem; // TODO It will fix in GameController

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
        AnimalController animalController = new AnimalController();

        int locationX = getLocationOfDirectionX(direction);
        int locationY = getLocationOfDirectionY(direction);
        Entity desiredTile = player.getCurrentSpace().getEntityByLocation(locationX, locationY);

        if (player.getCurrentTool() instanceof Hoe) {
            int enoughEnergy = ((Hoe) player.getCurrentTool()).getEnergy();
            if (player.getLevel(LevelType.FARMING_LEVEL) == 4) {
                enoughEnergy--;
            }
            if (weatherSystem.getTodayCondition() == WeatherCondition.RAIN ||
                weatherSystem.getTodayCondition() == WeatherCondition.STORM) {
                enoughEnergy *= 1.5;
            } else if (weatherSystem.getTodayCondition() == WeatherCondition.SNOW) {
                enoughEnergy *= 2;
            }

            if (player.getEnergy() < enoughEnergy) {
                return new Result(false, "Error: You do not have enough energy to use this tool.");
            }

            return farmingController.useHoe(direction, enoughEnergy);
        }

        else if (player.getCurrentTool() instanceof Pickaxe) {
            int enoughEnergy = ((Pickaxe) player.getCurrentTool()).getEnergy();
            if (player.getLevel(LevelType.MINING_LEVEL) == 4) {
                enoughEnergy--;
            }
            if (weatherSystem.getTodayCondition() == WeatherCondition.RAIN ||
                    weatherSystem.getTodayCondition() == WeatherCondition.STORM) {
                enoughEnergy *= 1.5;
            } else if (weatherSystem.getTodayCondition() == WeatherCondition.SNOW) {
                enoughEnergy *= 2;
            }

            if (player.getEnergy() < enoughEnergy) {
                return new Result(false, "Error: You do not have enough energy to use this tool.");
            }

            if (desiredTile instanceof PloughedLand) {
                return farmingController.usePickaxe(direction, enoughEnergy);
            }
            // TODO This tool can perform other tasks that are not related to farming.
        }

        else if (player.getCurrentTool() instanceof Axe) {
            int enoughEnergy = ((Axe) player.getCurrentTool()).getEnergy();
            if (player.getLevel(LevelType.FORAGING_LEVEL) == 4) {
                enoughEnergy--;
            }
            if (weatherSystem.getTodayCondition() == WeatherCondition.RAIN ||
                    weatherSystem.getTodayCondition() == WeatherCondition.STORM) {
                enoughEnergy *= 1.5;
            } else if (weatherSystem.getTodayCondition() == WeatherCondition.SNOW) {
                enoughEnergy *= 2;
            }

            if (player.getEnergy() < enoughEnergy) {
                return new Result(false, "Error: You do not have enough energy to use this tool.");
            }

            return farmingController.useAxe(direction, enoughEnergy);
        }

        else if (player.getCurrentTool() instanceof WateringCan) {
            int enoughEnergy = ((WateringCan) player.getCurrentTool()).getEnergy();
            if (player.getLevel(LevelType.FARMING_LEVEL) == 4) {
                enoughEnergy--;
            }
            if (weatherSystem.getTodayCondition() == WeatherCondition.RAIN ||
                    weatherSystem.getTodayCondition() == WeatherCondition.STORM) {
                enoughEnergy *= 1.5;
            } else if (weatherSystem.getTodayCondition() == WeatherCondition.SNOW) {
                enoughEnergy *= 2;
            }

            if (player.getEnergy() < enoughEnergy) {
                return new Result(false, "Error: You do not have enough energy to use this tool.");
            }

            return farmingController.useWateringCan(direction, enoughEnergy, (WateringCan) player.getCurrentTool());
        }

        else if (player.getCurrentTool() instanceof Scythe)  {
            int enoughEnergy = ((Scythe) player.getCurrentTool()).getEnergy();

            if (player.getEnergy() < enoughEnergy) {
                return new Result(false, "Error: You do not have enough energy to use this tool.");
            }

            return farmingController.useScythe(direction, enoughEnergy);
        }

        else if (player.getCurrentTool() instanceof MilkPail) {
            if (desiredTile instanceof Animal animal) {
                MilkPail milkPail = (MilkPail) player.getCurrentTool();

                if (milkPail.canMilk(animal.getAnimalType())) {
                    Product product = animalController.collectProduct(((Animal) desiredTile).getName(), player);
                    if (product != null) {
                        player.decreaseEnergy(4);
                        return new Result(true, "Milk pail was used successfully.");
                    }
                    else {
                        player.decreaseEnergy(4);
                        return new Result(false, "No product found for this animal.");
                    }
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
    //          return fishingController.useFishingPole(player, currentSeason, weather);
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
