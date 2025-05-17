package org.Group34.model.items.crafting.srategies;

import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.Building;
import org.Group34.model.entities.naturalElements.ForagingTree;
import org.Group34.model.entities.naturalElements.PlantAble;
import org.Group34.model.enums.Color;
import org.Group34.model.enums.creatorOfNaturalElements.TreeCreator;
import org.Group34.model.map.Space;

import java.util.HashMap;
import java.util.HashSet;

public enum PlacingStrategy {

    CHERRY_BOMB {
        @Override
        public boolean place(Space space, int x, int y) {
            return explode(space, x, y, 3);
        }
    },

     BOMB {
        @Override
        public boolean place(Space space, int x, int y) {
            return explode(space, x, y, 5);
        }
    },

    MEGA_BOMB {
        @Override
        public boolean place(Space space, int x, int y) {
            return explode(space, x, y, 7);
        }
    },

    GRASS_STARTER {
        @Override
        public boolean place(Space space, int x, int y) {
            return true;
        }
    },

    MYSTIC_TREE_SEED{
        @Override
        public boolean place(Space space, int x, int y) {
            Entity[][] entities = space.entities();

            if (entities[x][y] != null)
                return false;

            entities[x][y] = TreeCreator.MYSTIC_TREE.createInstance();
            return true;
        }
    }, SPRINKLER{
        @Override
        public boolean place(Space space, int x, int y) {
            PlacingStrategy.waterSurrounding(space, x, y, 4);
            return true;
        }
    },
    QUALITY_SPRINKLER{
        @Override
        public boolean place(Space space, int x, int y) {
            PlacingStrategy.waterSurrounding(space, x, y, 8);
            return true;
        }
    },
    IRIDIUM_SPRINKLER{
        @Override
        public boolean place(Space space, int x, int y) {
            PlacingStrategy.waterSurrounding(space, x, y, 24);
            return true;
        }
    },
    SCARECROW{
        @Override
        public boolean place(HashSet<int[]> scareCrowPlants, Space space, int x, int y) {
            PlacingStrategy.scareSurrounding(scareCrowPlants, space, x, y, 8);
            return true;
        }
    },
    DELUXE_SCARECROW{
        @Override
        public boolean place(HashSet<int[]> scareCrowPlants, Space space, int x, int y) {
            PlacingStrategy.scareSurrounding(scareCrowPlants, space, x, y, 12);
            return true;
        }
    };

    public boolean place(Space space, int x, int y){
        return false;
    }
    public boolean place(HashSet<int[]> scareCrowPlants, Space space, int x, int y){
        return false;
    }


    private static boolean explode(Space space, int x, int y, int r){
        Entity[][] entities = space.entities();
        int xBegin = x-r; int xEnd = x+r;
        int yBegin = y-r; int yEnd = y+r;

        if (entities[x][y] != null)
            return false;

        for (int i = xBegin; i<=xEnd; i++)
            for (int j = yBegin; j<=yEnd; j++){
                if (!entities[i][j].getClass().equals(Player.class) &&
                        !entities[i][j].getClass().equals(Building.class))
                    entities[i][j] = null;
            }

        return true;
    }

    private static void waterSurrounding(Space space, int x, int y, int r) {
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

}
