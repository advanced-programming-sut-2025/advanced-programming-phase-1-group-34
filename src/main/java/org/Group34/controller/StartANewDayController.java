package org.Group34.controller;

import org.Group34.model.Game;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.shops.*;
import org.Group34.model.entities.buildings.shops.products.ShippingBin;
import org.Group34.model.entities.naturalElements.*;
import org.Group34.model.Time;
import org.Group34.model.items.crafting.Ingredient;
import org.Group34.model.items.crafting.PlacingCraft;
import org.Group34.model.map.MapBuilder;
import org.Group34.model.map.Space;

import java.util.*;

/**
 * This class handles the tasks that need to be done at the beginning of the day
 */

public class StartANewDayController {

    private final static int MAX_ENERGY = 200;
    private Game currentGame;
    private ArrayList<Space> spaces;
    private Time time;


    public StartANewDayController(Game currentGame, ArrayList<Space> spaces, Time time) {
        this.currentGame = currentGame;
        this.spaces = spaces;
        this.time = time;
    }

    /**
     * This function calls all the functions necessary
      to perform tasks to start a new day
     * */
    public void ManageAllTasks() {
        currentGame.weatherSystem().advanceWeather(currentGame.time());
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
        Blacksmith blacksmith = new Blacksmith();
        MarnieRanch marnieRanch = new MarnieRanch();
        TheStardropSaloon theStardropSaloon = new TheStardropSaloon();
        CarpenterShop carpenterShop = new CarpenterShop();
        JojaMart jojaMart = new JojaMart();
        PierreGeneralStore pierreGeneralStore = new PierreGeneralStore();
        FishShop fishShop = new FishShop();

        for (Space space : spaces) {
            lightningStrike(space);

            for (int i = 0; i < space.width(); i++)
                for (int j = 0; j < space.height(); j++) {
                    randomPlacementOfForagingCropsAndSeeds(space, i, j);
                    randomPlacementOfForagingMinerals();
                    sprinklerWatering(space, i, j);
                    addPlant(plantsOnFarm, space, i, j);
                    scareCrow(scareCrowPlants, space, i, j);
                    reStartShops(blacksmith, marnieRanch, theStardropSaloon, carpenterShop, jojaMart, pierreGeneralStore, fishShop, space, i, j);
                    sellItems(space, i, j);
                }

            crowInvasion(space, plantsOnFarm, scareCrowPlants);
        }
    }

    private void lightningStrike(Space space) {
        WeatherSystem weather = currentGame.weatherSystem();
        Entity[][] entities = space.entities();

        for (int[] coordinate: weather.generateLightningStrikes()){
            int x = coordinate[0];
            int y = coordinate[1];

            if (entities[x][y] instanceof Tree || entities[x][y] instanceof ForagingTree)
                entities[x][y] = Ingredient.COAL;
            if (entities[x][y] instanceof Crop)
                entities[x][y] = null;
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
        if (entity.equals(PlacingCraft.SCARECROW) || entity.equals(PlacingCraft.DELUXE_SCARECROW))
            ((PlacingCraft) entity).place(scareCrowPlants, space, x, y);
    }



    private static void addPlant(HashSet<int[]> plantsOnFarm, Space space, int i, int j) {
        if (space.getEntityByLocation(i, j) instanceof PlantAble){
            plantsOnFarm.add(new int[]{i, j});
        }
    }


    private void sprinklerWatering(Space space, int x, int y) {
        Entity entity = space.getEntityByLocation(x, y);
        if (entity.equals(PlacingCraft.SPRINKLER) || entity.equals(PlacingCraft.QUALITY_SPRINKLER)
                 || entity.equals(PlacingCraft.IRIDIUM_SPRINKLER))
            ((PlacingCraft) entity).place(space, x, y);
    }




    // ----- Random Placement Of Foraging Crops And Seeds -----
    private void randomPlacementOfForagingCropsAndSeeds(Space space, int i, int j) {
        Random rand = new Random();
        if (space.getEntityByLocation(i, j) instanceof PloughedLand && rand.nextInt(100) == 0) {

            ArrayList<Entity> plants = MapBuilder.getPlantsOfCurrentSeason(time);
            int randInt = rand.nextInt(plants.size());

            for (int z = 0; z < plants.size(); z++) {
                if (randInt == z) {
                    space.placingEntity(i, j, plants.get(z));
                    break;
                }
            }
        }
    }

    // ----- Random Placement Of Foraging Minerals -----
    public void randomPlacementOfForagingMinerals() {
        // TODO
    }

    // ----- Check animals status -----
    private void checkAnimalStatus() {
    }

    private void reStartShops(Blacksmith blacksmith,
                              MarnieRanch marnieRanch,
                              TheStardropSaloon theStardropSaloon,
                              CarpenterShop carpenterShop,
                              JojaMart jojaMart,
                              PierreGeneralStore pierreGeneralStore,
                              FishShop fishShop,
                              Space space,
                              int i,
                              int j) {
        if (space.getEntityByLocation(i, j) instanceof Blacksmith) {
            space.placingEntity(i, j, blacksmith);
        } else if (space.getEntityByLocation(i, j) instanceof MarnieRanch) {
            space.placingEntity(i, j, marnieRanch);
        } else if (space.getEntityByLocation(i, j) instanceof TheStardropSaloon) {
            space.placingEntity(i, j, theStardropSaloon);
        } else if (space.getEntityByLocation(i, j) instanceof CarpenterShop) {
            space.placingEntity(i, j, carpenterShop);
        } else if (space.getEntityByLocation(i, j) instanceof JojaMart) {
            space.placingEntity(i, j, jojaMart);
        } else if (space.getEntityByLocation(i, j) instanceof PierreGeneralStore) {
            space.placingEntity(i, j, pierreGeneralStore);
        } else if (space.getEntityByLocation(i, j) instanceof FishShop) {
            space.placingEntity(i, j, fishShop);
        }
    }

    private void sellItems(Space space, int i, int j) {
        Player player = null;

        if (space.getEntityByLocation(i, j) instanceof SalePlace salePlace) {
            for (ShippingBin shippingBin : salePlace.getShippingBins()) {
                player.addMoney(shippingBin.getAmountOfItem() * 200);
            }

            salePlace.reStart();
        }
    }
}
