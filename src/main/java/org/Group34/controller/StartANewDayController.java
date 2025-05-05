package org.Group34.controller;

import org.Group34.model.entities.naturalElements.ForagingCrop;
import org.Group34.model.entities.naturalElements.ForagingMineral;
import org.Group34.model.entities.naturalElements.ForagingSeed;
import org.Group34.model.entities.naturalElements.PloughedLand;
import org.Group34.model.enums.Season;
import org.Group34.model.enums.creatorOfNaturalElements.ForagingCropCreator;
import org.Group34.model.enums.creatorOfNaturalElements.ForagingMineralCreator;
import org.Group34.model.enums.creatorOfNaturalElements.ForagingSeedCreator;
import org.Group34.model.map.Space;
import org.Group34.model.time.Time;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * This class handles the tasks that need to be done at the beginning of the day
 */

public class StartANewDayController {
    private Map currentMap; //TODO It will fix in GameController
    private ArrayList<Space> spaces; //TODO It will fix in GameController
    private Time time; //TODO It will fix in GameController


    /**
     * This function calls all the functions necessary
     * to perform tasks to start a new day
     * */
    public void ManageAllTasks() {
        randomPlacementOfForagingCropsAndSeeds();
        randomPlacementOfForagingMinerals();
    }



    // ----- Random Placement Of Foraging Crops And Seeds -----
    private void randomPlacementOfForagingCropsAndSeeds() {
        Random rand = new Random();
        for (Space space : spaces) {
            for (int i = 0; i < space.width(); i++) {
                for (int j = 0; j < space.height(); j++) {
                    if (space.getEntityByLocation(i, j) instanceof PloughedLand && rand.nextInt(100) == 0) {

                        if (rand.nextInt(2) == 0) {
                            ArrayList<ForagingCrop> crops = getForagingCropsOfCurrentSeason();
                            int randInt = rand.nextInt(crops.size());

                            for (int z = 0; z < crops.size(); z++) {
                                if (randInt == z) {
                                    space.placingEntity(i, j, crops.get(z));
                                    break;
                                }
                            }
                        }

                        else {
                            ArrayList<ForagingSeed> seeds = getForagingSeedsOfCurrentSeason();
                            int randInt = rand.nextInt(seeds.size());

                            for (int z = 0; z < seeds.size(); z++) {
                                if (randInt == z) {
                                    space.placingEntity(i, j, seeds.get(z));
                                    break;
                                }
                            }
                        }

                    }
                }
            }
        }
    }
    private ArrayList<ForagingCrop> getForagingCropsOfCurrentSeason() {
        ArrayList<ForagingCrop> crops = new ArrayList<>();

        if (time.getSeason() == Season.SPRING) {
            crops.add(ForagingCropCreator.COMMON_MUSHROOM.createInstance());
            crops.add(ForagingCropCreator.DAFFODIL.createInstance());
            crops.add(ForagingCropCreator.DANDELION.createInstance());
            crops.add(ForagingCropCreator.LEEK.createInstance());
            crops.add(ForagingCropCreator.MOREL.createInstance());
            crops.add(ForagingCropCreator.SALMONBERRY.createInstance());
            crops.add(ForagingCropCreator.SPRING_ONION.createInstance());
            crops.add(ForagingCropCreator.WILD_HORSERADISH.createInstance());
        } else if (time.getSeason() == Season.SUMMER) {
            crops.add(ForagingCropCreator.COMMON_MUSHROOM.createInstance());
            crops.add(ForagingCropCreator.FIDDLEHEAD_FERN.createInstance());
            crops.add(ForagingCropCreator.GRAPE.createInstance());
            crops.add(ForagingCropCreator.RED_MUSHROOM.createInstance());
            crops.add(ForagingCropCreator.SPICE_BERRY.createInstance());
            crops.add(ForagingCropCreator.SWEET_PEA.createInstance());
        } else if (time.getSeason() == Season.AUTUMN) {
            crops.add(ForagingCropCreator.COMMON_MUSHROOM.createInstance());
            crops.add(ForagingCropCreator.BLACKBERRY.createInstance());
            crops.add(ForagingCropCreator.CHANTERELLE.createInstance());
            crops.add(ForagingCropCreator.HAZELNUT.createInstance());
            crops.add(ForagingCropCreator.PURPLE_MUSHROOM.createInstance());
            crops.add(ForagingCropCreator.WILD_PLUM.createInstance());
        } else if (time.getSeason() == Season.WINTER) {
            crops.add(ForagingCropCreator.COMMON_MUSHROOM.createInstance());
            crops.add(ForagingCropCreator.CROCUS.createInstance());
            crops.add(ForagingCropCreator.CRYSTAL_FRUIT.createInstance());
            crops.add(ForagingCropCreator.HOLLY.createInstance());
            crops.add(ForagingCropCreator.SNOW_YAM.createInstance());
            crops.add(ForagingCropCreator.WINTER_ROOT.createInstance());
        }

        return crops;
    }
    private ArrayList<ForagingSeed> getForagingSeedsOfCurrentSeason() {
        ArrayList<ForagingSeed> seeds = new ArrayList<>();

        if (time.getSeason() == Season.SPRING) {
            seeds.add(ForagingSeedCreator.ANCIENT_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.MIXED_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.JAZZ_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.CARROT_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.CAULIFLOWER_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.COFFEE_BEAN.createInstance());
            seeds.add(ForagingSeedCreator.GARLIC_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.BEAN_STARTER.createInstance());
            seeds.add(ForagingSeedCreator.KALE_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.PARSNIP_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.POTATO_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.RHUBARB_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.STRAWBERRY_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.TULIP_BULB.createInstance());
            seeds.add(ForagingSeedCreator.RICE_SHOOT.createInstance());
        } else if (time.getSeason() == Season.SUMMER) {
            seeds.add(ForagingSeedCreator.ANCIENT_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.MIXED_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.BLUEBERRY_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.CORN_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.HOPS_STARTER.createInstance());
            seeds.add(ForagingSeedCreator.PEPPER_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.MELON_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.POPPY_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.RADISH_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.RED_CABBAGE_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.STARFRUIT_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.SPANGLE_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.SUMMER_SQUASH_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.SUNFLOWER_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.TOMATO_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.WHEAT_SEEDS.createInstance());
        } else if (time.getSeason() == Season.AUTUMN) {
            seeds.add(ForagingSeedCreator.ANCIENT_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.MIXED_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.AMARANTH_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.ARTICHOKE_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.BEET_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.BOK_CHOY_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.BROCCOLI_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.CRANBERRY_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.EGGPLANT_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.FAIRY_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.GRAPE_STARTER.createInstance());
            seeds.add(ForagingSeedCreator.PUMPKIN_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.YAM_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.RARE_SEED.createInstance());
        } else if (time.getSeason() == Season.WINTER) {
            seeds.add(ForagingSeedCreator.ANCIENT_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.MIXED_SEEDS.createInstance());
            seeds.add(ForagingSeedCreator.POWDERMELON_SEEDS.createInstance());
        }

        return seeds;
    }

    // ----- Random Placement Of Foraging Minerals -----
    private void randomPlacementOfForagingMinerals() {
        // TODO
    }
    private ArrayList<ForagingMineral> getForagingMinerals() {
        ArrayList<ForagingMineral> minerals = new ArrayList<>();

        minerals.add(ForagingMineralCreator.QUARTZ.createInstance());
        minerals.add(ForagingMineralCreator.EARTH_CRYSTAL.createInstance());
        minerals.add(ForagingMineralCreator.FROZEN_TEAR.createInstance());
        minerals.add(ForagingMineralCreator.FIRE_QUARTZ.createInstance());
        minerals.add(ForagingMineralCreator.EMERALD.createInstance());
        minerals.add(ForagingMineralCreator.AQUAMARINE.createInstance());
        minerals.add(ForagingMineralCreator.RUBY.createInstance());
        minerals.add(ForagingMineralCreator.AMETHYST.createInstance());
        minerals.add(ForagingMineralCreator.TOPAZ.createInstance());
        minerals.add(ForagingMineralCreator.JADE.createInstance());
        minerals.add(ForagingMineralCreator.DIAMOND.createInstance());
        minerals.add(ForagingMineralCreator.PRISMATIC_SHARD.createInstance());
        minerals.add(ForagingMineralCreator.COPPER.createInstance());
        minerals.add(ForagingMineralCreator.IRON.createInstance());
        minerals.add(ForagingMineralCreator.GOLD.createInstance());
        minerals.add(ForagingMineralCreator.IRIDUIM.createInstance());
        minerals.add(ForagingMineralCreator.COAL.createInstance());

        return minerals;
    }
}
