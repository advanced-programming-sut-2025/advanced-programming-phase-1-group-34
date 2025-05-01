package org.Group34.model.map;

import org.Group34.model.entities.*;
import org.Group34.model.entities.buildings.*;
import org.Group34.model.enums.FarmType;

import java.util.HashMap;

/**
 * This class first generates Farms of each Player
 * Then adds NPC Village to map
 */
public class MapBuilder {
    private static final int SPACE_WIDTH           = 100;
    private static final int SPACE_HEIGHT          = 100;
    private static final int HOUSE_WIDTH           =   4;
    private static final int HOUSE_HEIGHT          =   4;
    private static final int GREEN_HOUSE_WIDTH     =   6;
    private static final int GREEN_HOUSE_HEIGHT    =   5;
    private static final int LAKE_WIDTH            =   8;
    private static final int LAKE_HEIGHT           =   8;
    private static final int QUARRY_WIDTH          =   8;
    private static final int QUARRY_HEIGHT         =   8;
    private static final int PLAYER_INITIAL_X      =  72;
    private static final int PLAYER_INITIAL_Y      =  10;

    private static final Building WALL = new Building(new int[]{0, 0});

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
        }
        Space npcVillage = generateNpcVillage();
        return new Map(playerFarms, npcVillage);
    }

    /**
     * Generates NPC Village and adds Buildings into it
     */
    private Space generateNpcVillage() {
        Entity[][] villageGrid = new Entity[SPACE_WIDTH][SPACE_HEIGHT];
        // TODO Complete NPC Buildings after making all NPCs
        return new Space(SPACE_WIDTH, SPACE_HEIGHT, villageGrid);
    }

    /**
     * Adds necessary Buildings and spawns random Items into farm
     */
    private Space generateFarm(FarmType farmType, Player player) {
        Entity[][] farmGrid = new Entity[SPACE_WIDTH][SPACE_HEIGHT];

        farmGrid[PLAYER_INITIAL_X][PLAYER_INITIAL_Y] = player;

        addBuilding(farmGrid, new House(farmType.getHouseLocation()), HOUSE_WIDTH, HOUSE_HEIGHT);
        addBuilding(farmGrid, new Lake(farmType.getLakeLocation()), LAKE_WIDTH, LAKE_HEIGHT);
        addBuilding(farmGrid, new GreenHouse(farmType.getGreenHouseLocation()), GREEN_HOUSE_WIDTH, GREEN_HOUSE_HEIGHT);
        addBuilding(farmGrid, new Quarry(farmType.getQuarryLocation()), QUARRY_WIDTH, QUARRY_HEIGHT);
        addRandomItems(farmGrid, farmType);

        return new Space(SPACE_WIDTH, SPACE_HEIGHT, farmGrid);
    }

    /**
     * Add buildings to spaceGrid
     * Location of each building is top left corner of it
     * Only bottom of building is considered as Building and player can do things with it
     * to make a door-like functionality; tiles that Building took are just placeholders
     */
    private void addBuilding(Entity[][] spaceGrid, Building building, int width, int height) {
        int[] location = building.getLocation();
        int startX = location[0], startY = location[1];

        for (int y = startY; y <= startY + height; y++)
            for (int x = startX; x <= startX + width; x++) {
                if (y == startY + height)
                    spaceGrid[y][x] = building;
                else
                    spaceGrid[y][x] = WALL;
            }
    }

    /**
     * Adds random Items like Tree, Stone and Foraging to spaceGrid
     */
    private void addRandomItems(Entity[][] spaceGrid, FarmType farmType) {
        for (int x = 0; x < SPACE_WIDTH; x++)
            for (int y = 0; y < SPACE_HEIGHT; y++)
                if (spaceGrid[x][y] == null) {
                    double randomNum = Math.random();
                    float  chance    = farmType.getTreeSpawnChance();

                    if (randomNum < chance)
                        spaceGrid[x][y] = new Tree(new int[]{x, y});
                    else if (randomNum < (chance += farmType.getStoneSpawnChance()))
                        spaceGrid[x][y] = new Stone(new int[]{x, y});
                    else if (randomNum < chance + farmType.getForagingSpawnChance())
                        spaceGrid[x][y] = new Foraging(new int[]{x, y});
                }
    }
}
