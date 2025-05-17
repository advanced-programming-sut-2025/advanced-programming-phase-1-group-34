package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.Time;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.Lake;
import org.Group34.model.entities.naturalElements.PloughedLand;
import org.Group34.model.enums.LevelType;
import org.Group34.model.enums.WeatherCondition;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.items.Item;
import org.Group34.model.items.tools.*;

public class ToolsController { // TODO This class must be filled.
    public Result toolsEquip(String toolName, Player player) {
        Item tool = player.getItemFromInventoryByName(toolName);

        if (tool == null) {
            return new Result(false, "Error: You do not have this tool.");
        }

        player.setCurrentTool(tool);
        return new Result(true, "You have been equipped with the desired tool.");
    }
    public Result showCurrentTools(Player player) {
        if (player.getCurrentTool() == null) {
            return new Result(true, "You currently do not have any tools at hand.");
        }

        return new Result(true, "Your Current Tool: " + player.getCurrentTool().getName());
    }
    public Result showAvailableTools(Player player) {
        return new Result(true, stringifyAvailableTools(player));
    }
    public Result toolUse(String direction,
                          FarmingController farmingController,
                          FishingController fishingController,
                          AnimalController animalController,
                          Player player,
                          Time time,
                          WeatherSystem weatherSystem,
                          LevelUpController levelUpController) {
        int locationX = getLocationOfDirectionX(direction, player);
        int locationY = getLocationOfDirectionY(direction, player);
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

            return farmingController.useHoe(direction, enoughEnergy, player);
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
                return farmingController.usePickaxe(direction, enoughEnergy, player);
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

            return farmingController.useAxe(direction, enoughEnergy, player, levelUpController);
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

            return farmingController.useWateringCan(direction, enoughEnergy, (WateringCan) player.getCurrentTool(), player);
        }

        else if (player.getCurrentTool() instanceof Scythe)  {
            int enoughEnergy = ((Scythe) player.getCurrentTool()).getEnergy();

            if (player.getEnergy() < enoughEnergy) {
                return new Result(false, "Error: You do not have enough energy to use this tool.");
            }

            return farmingController.useScythe(direction, enoughEnergy, player, time, levelUpController);
        }

        else if (player.getCurrentTool() instanceof MilkPail) {
            if (desiredTile instanceof Animal animal) {
                MilkPail milkPail = (MilkPail) player.getCurrentTool();

                if (milkPail.canMilk(animal.getAnimalType())) {
                    Product product = animalController.collectProduct(((Animal) desiredTile).getName(), player);
                    if (product != null) {
                        player.decreaseEnergy((int) (weatherSystem.getEnergyMultiplier(weatherSystem.getSeason()) * 4));
                        return new Result(true, "Milk pail was used successfully.");
                    }
                    else {
                        player.decreaseEnergy((int) (weatherSystem.getEnergyMultiplier(weatherSystem.getSeason()) * 4));
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
                return fishingController.startFishing(player, weatherSystem.getSeason(),
                        weatherSystem.getTodayCondition(), (FishingPole) player.getCurrentTool());
            }
            else {
                return new Result(false, "You can only fish in water.");
            }
        }


        return new Result(true, "");
    }

    private int getLocationOfDirectionX(String direction, Player player) {
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
    private int getLocationOfDirectionY(String direction, Player player) {
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
    private String stringifyAvailableTools(Player player) {
        StringBuilder result = new StringBuilder();
        result.append("* Available Tools:\n");

        for (Item item : player.getInventory().keySet()) {
            if (item instanceof Tool) {
                result.append("- " + item.getName() + "\n");
            }
        }

        return result.toString();
    }
}