package org.Group34.controller;

import org.Group34.model.Game;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.naturalElements.Crop;
import org.Group34.model.entities.naturalElements.PlantAble;
import org.Group34.model.entities.naturalElements.PloughedLand;
import org.Group34.model.entities.naturalElements.Tree;
import org.Group34.model.enums.Season;
import org.Group34.model.enums.WeatherCondition;
import org.Group34.model.enums.creatorOfNaturalElements.*;
import org.Group34.model.items.Item;
import org.Group34.model.items.Mineral;
import org.Group34.model.items.crafting.PlacingCraft;
import org.Group34.model.map.Space;
import org.Group34.model.time.Time;

import java.util.*;

/**
 * This class handles the tasks that need to be done at the beginning of the day
 */

public class StartANewDayController {

    private final static int MAX_ENERGY = 200;
    private Game currentGame; //TODO It will fix in GameController
    private ArrayList<Space> spaces; //TODO It will fix in GameController
    private Time time; //TODO It will fix in GameController
    private WeatherSystem weatherSystem; // TODO It will fix in GameController


    /**
     * This function calls all the functions necessary
      to perform tasks to start a new day
     * */
    public void ManageAllTasks() {
        randomPlacementOfForagingMinerals();
        iterateWholeMap();
        resetPlayersEnergy();
    }



    private void resetPlayersEnergy() {
        for (Player player: currentGame.players().values()){
            if (player.isPassedOut()){
                player.setEnergy(MAX_ENERGY * 3 /4);
                player.setPassedOut(false);
            }

            player.setEnergy(MAX_ENERGY);
        }
    }


    /**
     * Some functionalities need to iterate throw map
      and if there is a specific Entity do a certain function
     * */
    private void iterateWholeMap() {
        HashSet<int[]> plantsOnFarm = new HashSet<>();
        HashSet<int[]> scareCrowPlants = new HashSet<>();

        for (Space space : spaces) {
            for (int i = 0; i < space.width(); i++)
                for (int j = 0; j < space.height(); j++) {
                    randomPlacementOfForagingCropsAndSeeds(space, i, j);
                    sprinklerWatering(space, i, j);
                    addPlant(plantsOnFarm, space, i, j);
                    startANewDayForPlants(space, i, j);
                    checkWeatherAndWateringThePlant(space, i, j);
                    removeDriedPlants(space, i, j);
                    scareCrow(scareCrowPlants, space, i, j);
                }

            crowInvasion(space, plantsOnFarm, scareCrowPlants);
        }
    }

    private void crowInvasion(Space space, HashSet<int[]> plantsOnFarm, HashSet<int[]> scareCrowPlants) {
        int countOfInvasion = plantsOnFarm.size() / 16;
        Entity[][] entities = space.entities();
        Random rand = new Random();

        plantsOnFarm.remove(scareCrowPlants);
        List<int[]> plantList = new ArrayList<>(plantsOnFarm);

        for (int i = 0; i<countOfInvasion; i++)
            if (rand.nextInt(100) < 25) {
                int index = rand.nextInt(plantList.size());
                int[] randomPlant = plantList.get(index);

                if (entities[randomPlant[0]][randomPlant[1]] instanceof Crop) {
                    entities[randomPlant[0]][randomPlant[1]] = null;
                }if (entities[randomPlant[0]][randomPlant[1]] instanceof Tree tree){
                    tree.crowInvasion();
                }
            }

        plantsOnFarm.clear();
        scareCrowPlants.clear();
    }

    private static void scareCrow(HashSet<int[]> scareCrowPlants, Space space, int x, int y) {
        Entity entity = space.getEntityByLocation(x, y);
        if (entity.equals(PlacingCraft.SCARECROW))
            scareSurrounding(scareCrowPlants, space, x, y, 8);
        if (entity.equals(PlacingCraft.DELUXE_SCARECROW))
            scareSurrounding(scareCrowPlants, space, x, y, 12);
    }

    private static void scareSurrounding(HashSet<int[]> scareCrowPlants, Space space, int x, int y, int r) {
        Entity[][] entities = space.entities();
        int xBegin = x - r;
        int xEnd = x + r;
        int yBegin = y - r;
        int yEnd = y + r;

        for (int i = xBegin; i <= xEnd; i++)
            for (int j = yBegin; j <= yEnd; j++) {
                Entity entity = entities[i][j];

                if (entity instanceof PlantAble)
                    scareCrowPlants.add(new int[]{i, j});
            }
    }

    private static void addPlant(HashSet<int[]> plantsOnFarm, Space space, int i, int j) {
        if (space.getEntityByLocation(i, j) instanceof PlantAble){
            plantsOnFarm.add(new int[]{i, j});
        }
    }


    private void sprinklerWatering(Space space, int x, int y) {
        Entity entity = space.getEntityByLocation(x, y);
        if (entity.equals(PlacingCraft.SPRINKLER))
            waterSurrounding(space, x, y, 4);
        if (entity.equals(PlacingCraft.QUALITY_SPRINKLER))
            waterSurrounding(space, x, y, 8);
        if (entity.equals(PlacingCraft.IRIDIUM_SPRINKLER))
            waterSurrounding(space, x, y, 24);
    }

    private void waterSurrounding(Space space, int x, int y, int r) {
        Entity[][] entities = space.entities();
        int xBegin = x - r;
        int xEnd = x + r;
        int yBegin = y - r;
        int yEnd = y + r;

        for (int i = xBegin; i <= xEnd; i++)
            for (int j = yBegin; j <= yEnd; j++) {
                Entity entity = entities[i][j];

                if (entity instanceof PlantAble)
                    ((PlantAble) entity).setNeedWater(false);
            }
    }


    // ----- Random Placement Of Foraging Crops And Seeds -----
    private void randomPlacementOfForagingCropsAndSeeds(Space space, int i, int j) {
        Random rand = new Random();
        if (space.getEntityByLocation(i, j) instanceof PloughedLand && rand.nextInt(100) == 0) {

            ArrayList<Entity> plants = getPlantsOfCurrentSeason();
            int randInt = rand.nextInt(plants.size());

            for (int z = 0; z < plants.size(); z++) {
                if (randInt == z) {
                    space.placingEntity(i, j, plants.get(z));
                    break;
                }
            }
        }
    }
    private ArrayList<Entity> getPlantsOfCurrentSeason() {
        ArrayList<Entity> plants = new ArrayList<>();

        if (time.getSeason() == Season.SPRING) {
            plants.add(ForagingCropCreator.COMMON_MUSHROOM.createInstance());
            plants.add(ForagingCropCreator.DAFFODIL.createInstance());
            plants.add(ForagingCropCreator.DANDELION.createInstance());
            plants.add(ForagingCropCreator.LEEK.createInstance());
            plants.add(ForagingCropCreator.MOREL.createInstance());
            plants.add(ForagingCropCreator.SALMONBERRY.createInstance());
            plants.add(ForagingCropCreator.SPRING_ONION.createInstance());
            plants.add(ForagingCropCreator.WILD_HORSERADISH.createInstance());

            plants.add(CropCreator.BLUE_JAZZ.createInstance());
            plants.add(CropCreator.CARROT.createInstance());
            plants.add(CropCreator.CAULIFLOWER.createInstance());
            plants.add(CropCreator.COFFEE_BEAN.createInstance());
            plants.add(CropCreator.GARLIC.createInstance());
            plants.add(CropCreator.GREEN_BEAN.createInstance());
            plants.add(CropCreator.KALE.createInstance());
            plants.add(CropCreator.PARSNIP.createInstance());
            plants.add(CropCreator.POTATO.createInstance());
            plants.add(CropCreator.RHUBARB.createInstance());
            plants.add(CropCreator.STRAWBERRY.createInstance());
            plants.add(CropCreator.TULIP.createInstance());
            plants.add(CropCreator.UNMILLED_RICE.createInstance());
            plants.add(CropCreator.ANCIENT_FRUIT.createInstance());

            plants.add(TreeCreator.APRICOT_TREE.createInstance());
            plants.add(TreeCreator.CHERRY_TREE.createInstance());
            plants.add(TreeCreator.OAK_TREE.createInstance());
            plants.add(TreeCreator.MAPLE_TREE.createInstance());
            plants.add(TreeCreator.PINE_TREE.createInstance());
            plants.add(TreeCreator.MAHOGANY_TREE.createInstance());
            plants.add(TreeCreator.MUSHROOM_TREE.createInstance());
            plants.add(TreeCreator.MYSTIC_TREE.createInstance());
        }

        else if (time.getSeason() == Season.SUMMER) {
            plants.add(ForagingCropCreator.COMMON_MUSHROOM.createInstance());
            plants.add(ForagingCropCreator.FIDDLEHEAD_FERN.createInstance());
            plants.add(ForagingCropCreator.GRAPE.createInstance());
            plants.add(ForagingCropCreator.RED_MUSHROOM.createInstance());
            plants.add(ForagingCropCreator.SPICE_BERRY.createInstance());
            plants.add(ForagingCropCreator.SWEET_PEA.createInstance());

            plants.add(CropCreator.COFFEE_BEAN.createInstance());
            plants.add(CropCreator.BLUEBERRY.createInstance());
            plants.add(CropCreator.CORN.createInstance());
            plants.add(CropCreator.HOPS.createInstance());
            plants.add(CropCreator.HOT_PEPPER.createInstance());
            plants.add(CropCreator.MELON.createInstance());
            plants.add(CropCreator.POPPY.createInstance());
            plants.add(CropCreator.RADISH.createInstance());
            plants.add(CropCreator.RED_CABBAGE.createInstance());
            plants.add(CropCreator.STARFRUIT.createInstance());
            plants.add(CropCreator.SUMMER_SPANGLE.createInstance());
            plants.add(CropCreator.SUMMER_SQUASH.createInstance());
            plants.add(CropCreator.SUNFLOWER.createInstance());
            plants.add(CropCreator.TOMATO.createInstance());
            plants.add(CropCreator.WHEAT.createInstance());
            plants.add(CropCreator.ANCIENT_FRUIT.createInstance());

            plants.add(TreeCreator.BANANA_TREE.createInstance());
            plants.add(TreeCreator.MANGO_TREE.createInstance());
            plants.add(TreeCreator.ORANGE_TREE.createInstance());
            plants.add(TreeCreator.PEACH_TREE.createInstance());
            plants.add(TreeCreator.OAK_TREE.createInstance());
            plants.add(TreeCreator.MAPLE_TREE.createInstance());
            plants.add(TreeCreator.PINE_TREE.createInstance());
            plants.add(TreeCreator.MAHOGANY_TREE.createInstance());
            plants.add(TreeCreator.MUSHROOM_TREE.createInstance());
            plants.add(TreeCreator.MYSTIC_TREE.createInstance());
        }

        else if (time.getSeason() == Season.AUTUMN) {
            plants.add(ForagingCropCreator.COMMON_MUSHROOM.createInstance());
            plants.add(ForagingCropCreator.BLACKBERRY.createInstance());
            plants.add(ForagingCropCreator.CHANTERELLE.createInstance());
            plants.add(ForagingCropCreator.HAZELNUT.createInstance());
            plants.add(ForagingCropCreator.PURPLE_MUSHROOM.createInstance());
            plants.add(ForagingCropCreator.WILD_PLUM.createInstance());

            plants.add(CropCreator.CORN.createInstance());
            plants.add(CropCreator.SUNFLOWER.createInstance());
            plants.add(CropCreator.WHEAT.createInstance());
            plants.add(CropCreator.AMARANTH.createInstance());
            plants.add(CropCreator.ARTICHOKE.createInstance());
            plants.add(CropCreator.BEET.createInstance());
            plants.add(CropCreator.BOK_CHOY.createInstance());
            plants.add(CropCreator.BROCCOLI.createInstance());
            plants.add(CropCreator.CRANBERRIES.createInstance());
            plants.add(CropCreator.EGGPLANT.createInstance());
            plants.add(CropCreator.FAIRY_ROSE.createInstance());
            plants.add(CropCreator.GRAPE.createInstance());
            plants.add(CropCreator.PUMPKIN.createInstance());
            plants.add(CropCreator.YAM.createInstance());
            plants.add(CropCreator.SWEET_GEM_BERRY.createInstance());
            plants.add(CropCreator.ANCIENT_FRUIT.createInstance());

            plants.add(TreeCreator.APPLE_TREE.createInstance());
            plants.add(TreeCreator.POMEGRANATE_TREE.createInstance());
            plants.add(TreeCreator.OAK_TREE.createInstance());
            plants.add(TreeCreator.MAPLE_TREE.createInstance());
            plants.add(TreeCreator.PINE_TREE.createInstance());
            plants.add(TreeCreator.MAHOGANY_TREE.createInstance());
            plants.add(TreeCreator.MUSHROOM_TREE.createInstance());
            plants.add(TreeCreator.MYSTIC_TREE.createInstance());
        }

        else if (time.getSeason() == Season.WINTER) {
            plants.add(ForagingCropCreator.COMMON_MUSHROOM.createInstance());
            plants.add(ForagingCropCreator.CROCUS.createInstance());
            plants.add(ForagingCropCreator.CRYSTAL_FRUIT.createInstance());
            plants.add(ForagingCropCreator.HOLLY.createInstance());
            plants.add(ForagingCropCreator.SNOW_YAM.createInstance());
            plants.add(ForagingCropCreator.WINTER_ROOT.createInstance());

            plants.add(CropCreator.POWDERMELON.createInstance());

            plants.add(TreeCreator.OAK_TREE.createInstance());
            plants.add(TreeCreator.MAPLE_TREE.createInstance());
            plants.add(TreeCreator.PINE_TREE.createInstance());
            plants.add(TreeCreator.MAHOGANY_TREE.createInstance());
            plants.add(TreeCreator.MUSHROOM_TREE.createInstance());
            plants.add(TreeCreator.MYSTIC_TREE.createInstance());
        }

        return plants;
    }

    // ----- Random Placement Of Foraging Minerals -----
    private void randomPlacementOfForagingMinerals() {
        for (int i = 0; i < 5; i++) {
            Mineral randomMineral = getRandomMineral();
            // TODO Placing this random mineral in Quarry
        }
    }
    private Mineral getRandomMineral() {
        Random rand = new Random();
        int randInt = rand.nextInt(17);

        if (randInt == 0) {
            return Mineral.QUARTZ;
        } else if (randInt == 1) {
            return Mineral.EARTH_CRYSTAL;
        } else if (randInt == 2) {
            return Mineral.FROZEN_TEAR;
        } else if (randInt == 3) {
            return Mineral.FIRE_QUARTZ;
        } else if (randInt == 4) {
            return Mineral.EMERALD;
        } else if (randInt == 5) {
            return Mineral.AQUAMARINE;
        } else if (randInt == 6) {
            return Mineral.RUBY;
        } else if (randInt == 7) {
            return Mineral.AMETHYST;
        } else if (randInt == 8) {
            return Mineral.TOPAZ;
        } else if (randInt == 9) {
            return Mineral.JADE;
        } else if (randInt == 10) {
            return Mineral.DIAMOND;
        } else if (randInt == 11) {
            return Mineral.PRISMATIC_SHARD;
        } else if (randInt == 12) {
            return Mineral.COPPER;
        } else if (randInt == 13) {
            return Mineral.IRON;
        } else if (randInt == 14) {
            return Mineral.GOLD;
        } else if (randInt == 15) {
            return Mineral.IRIDIUM;
        } else {
            return Mineral.COAL;
        }
    }

    // ----- Start A New Day For Plants -----
    private void startANewDayForPlants(Space space, int i, int j) {
        if (space.getEntityByLocation(i, j) instanceof Crop crop) {
            crop.startANewDay();
        } else if (space.getEntityByLocation(i, j) instanceof Tree tree) {
            tree.startANewDay();
        }
    }

    // ----- Watering The Plants -----
    private void checkWeatherAndWateringThePlant(Space space, int i, int j) {
        if (weatherSystem.getTodayCondition() == WeatherCondition.RAIN ||
            weatherSystem.getTodayCondition() == WeatherCondition.STORM) {
            if (space.getEntityByLocation(i, j) instanceof Crop crop) {
                crop.setNeedWater(false);
            } else if (space.getEntityByLocation(i, j) instanceof Tree tree) {
                tree.setNeedWater(false);
            }
        }
    }

    // ----- Remove Dried Plants -----
    private void removeDriedPlants(Space space, int i, int j) {
        if (space.getEntityByLocation(i, j) instanceof Crop crop && crop.getNumberOfDaysNeedWater() > 2) {
            space.placingEntity(i, j, null);
        } else if (space.getEntityByLocation(i, j) instanceof Tree tree && tree.getNumberOfDaysNeedWater() > 2) {
            space.placingEntity(i, j, null);
        }
    }
}
