package org.Group34.model.map;

import org.Group34.model.Time;
import org.Group34.model.entities.*;
import org.Group34.model.entities.buildings.*;
import org.Group34.model.entities.buildings.shops.*;
import org.Group34.model.enums.FarmType;
import org.Group34.model.enums.Season;
import org.Group34.model.enums.creatorOfNaturalElements.CropCreator;
import org.Group34.model.enums.creatorOfNaturalElements.ForagingCropCreator;
import org.Group34.model.enums.creatorOfNaturalElements.TreeCreator;
import org.Group34.model.items.crafting.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class first generates Farms of each Player
 * Then adds NPC Village to map
 */
public class MapBuilder {
    public static final int SPACE_WIDTH           = 100;
    public static final int SPACE_HEIGHT          = 100;
    private static final int BUILDING_WIDTH        =   4;
    private static final int BUILDING_HEIGHT       =   4;
    private static final int GREEN_HOUSE_WIDTH     =   6;
    private static final int GREEN_HOUSE_HEIGHT    =   5;
    private static final int LAKE_WIDTH            =   8;
    private static final int LAKE_HEIGHT           =   8;
    private static final int QUARRY_WIDTH          =   8;
    private static final int QUARRY_HEIGHT         =   8;
    private static final int PLAYER_INITIAL_X      =  72;
    private static final int PLAYER_INITIAL_Y      =  10;
    private static final int[] BLACKSMITH = new int[]{22, 22};
    private static final int[] FISH_SHOP = new int[]{22, 46};
    private static final int[] JOJO_MART = new int[]{22, 72};
    private static final int[] MARNIE_RANCH = new int[]{46, 30};
    private static final int[] SALE_PLACE = new int[]{46, 64};
    private static final int[] PIERRE_GENERAL_STORE = new int[]{72, 22};
    private static final int[] THE_STARDROP_SALOON = new int[]{72, 46};
    private static final int[] CARPENTER_SHOP = new int[]{72, 22};



    private FarmType[] farmTypes;
    private Player[]  players;

    public void setFarms(FarmType[] farmTypes) {
        this.farmTypes = farmTypes;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * After setting all Players FarmType we will call this function to make a Map for Game
     */
    public Map generate() {
        HashMap<Player, Space> playerFarms = new HashMap<>();

        for (int idx = 0; idx < players.length; idx++) {
            Space farmSpace = generateFarm(farmTypes[idx], players[idx]);
            playerFarms.put(players[idx], farmSpace);
            players[idx].setCurrentSpace(farmSpace);
        }
        Space npcVillage = generateNpcVillage();
        return new Map(playerFarms, npcVillage);
    }

    /**
     * Generates NPC Village and adds Buildings into it
     */
    private Space generateNpcVillage() {
        Entity[][] villageGrid = new Entity[SPACE_WIDTH][SPACE_HEIGHT];

        addBuilding(villageGrid, new Blacksmith(), BUILDING_WIDTH, BUILDING_HEIGHT, BLACKSMITH);
        addBuilding(villageGrid, new CarpenterShop(), BUILDING_WIDTH, BUILDING_HEIGHT, CARPENTER_SHOP);
        addBuilding(villageGrid, new FishShop(), BUILDING_WIDTH, BUILDING_HEIGHT, FISH_SHOP);
        addBuilding(villageGrid, new JojaMart(), BUILDING_WIDTH, BUILDING_HEIGHT, JOJO_MART);
        addBuilding(villageGrid, new MarnieRanch(), BUILDING_WIDTH, BUILDING_HEIGHT, MARNIE_RANCH);
        addBuilding(villageGrid, new PierreGeneralStore(), BUILDING_WIDTH, BUILDING_HEIGHT, PIERRE_GENERAL_STORE);
        addBuilding(villageGrid, new TheStardropSaloon(), BUILDING_WIDTH, BUILDING_HEIGHT, THE_STARDROP_SALOON);
        addBuilding(villageGrid, new SalePlace(), BUILDING_WIDTH, BUILDING_HEIGHT, SALE_PLACE);


        return new Space(SPACE_WIDTH, SPACE_HEIGHT, villageGrid);
    }

    /**
     * Adds necessary Buildings and spawns random Items into farm
     */
    private Space generateFarm(FarmType farmType, Player player) {
        Entity[][] farmGrid = new Entity[SPACE_WIDTH][SPACE_HEIGHT];

        farmGrid[PLAYER_INITIAL_X][PLAYER_INITIAL_Y] = player;

        addBuilding(farmGrid, new House(), BUILDING_WIDTH, BUILDING_HEIGHT, farmType.getHouseLocation());
        addBuilding(farmGrid, new Lake(), LAKE_WIDTH, LAKE_HEIGHT, farmType.getLakeLocation());
        addBuilding(farmGrid, new GreenHouse(), GREEN_HOUSE_WIDTH, GREEN_HOUSE_HEIGHT, farmType.getGreenHouseLocation());
        addBuilding(farmGrid, new Quarry(), QUARRY_WIDTH, QUARRY_HEIGHT, farmType.getQuarryLocation());
        addRandomItems(farmGrid, farmType);

        return new Space(SPACE_WIDTH, SPACE_HEIGHT, farmGrid);
    }

    /**
     * Add buildings to spaceGrid
     * Location of each building is top left corner of it
     * Only bottom of building is considered as Building and player can do things with it
     * to make a door-like functionality; tiles that Building took are just placeholders
     */
    private void addBuilding(Entity[][] spaceGrid, Entity building, int width, int height, int[] location) {
        int startX = location[1], startY = location[0];

        for (int y = startY; y <= startY + height; y++)
            for (int x = startX; x <= startX + width; x++)
                spaceGrid[y][x] = building;

    }

    /**
     * Adds random Items like Tree, Stone and Foraging to spaceGrid
     */
    private void addRandomItems(Entity[][] spaceGrid, FarmType farmType) {
        for (int x = 0; x < SPACE_WIDTH; x++)
            for (int y = 0; y < SPACE_HEIGHT; y++)
                if (spaceGrid[x][y] == null) {
                    Random rand = new Random();

                    if (rand.nextInt(100) < farmType.getForagingSpawnChance()) {
                        ArrayList<Entity> plants = MapBuilder.getPlantsOfCurrentSeason(new Time());
                        int randInt = rand.nextInt(plants.size());
                        spaceGrid[x][y] = plants.get(randInt);
                    }
                    else if (rand.nextInt(100) < farmType.getStoneSpawnChance())
                        spaceGrid[x][y] = Ingredient.STONE;
                }
    }

    public static ArrayList<Entity> getPlantsOfCurrentSeason(Time time) {
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

        else if (time.getSeason() == Season.FALL) {
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
}
