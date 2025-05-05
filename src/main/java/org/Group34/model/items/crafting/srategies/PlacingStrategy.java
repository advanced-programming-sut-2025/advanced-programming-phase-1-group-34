package org.Group34.model.items.crafting.srategies;

import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.Building;
import org.Group34.model.map.Space;

public class PlacingStrategy {

    public static final PlacingStrategy BASIC_SPRINKLER = new PlacingStrategy() {
        @Override
        public void place(Space space, int x, int y) {
            // Place logic for BASIC_SPRINKLER
        }
    };

    public static final PlacingStrategy QUALITY_SPRINKLER = new PlacingStrategy() {
        @Override
        public void place(Space space, int x, int y) {
            // Place logic for QUALITY_SPRINKLER
        }
    };

    public static final PlacingStrategy IRIDIUM_SPRINKLER = new PlacingStrategy() {
        @Override
        public void place(Space space, int x, int y) {
            // Place logic for IRIDIUM_SPRINKLER
        }
    };

    public static final PlacingStrategy BASIC_SCARECROW = new PlacingStrategy() {
        @Override
        public void place(Space space, int x, int y) {
            // Place logic for BASIC_SCARECROW
        }
    };

    public static final PlacingStrategy DELUXE_SCARECROW = new PlacingStrategy() {
        @Override
        public void place(Space space, int x, int y) {
            // Place logic for DELUXE_SCARECROW
        }
    };

    public static final PlacingStrategy CHERRY_BOMB = new PlacingStrategy() {
        @Override
        public void place(Space space, int x, int y) {
            explode(space, x, y, 3);
        }
    };

    public static final PlacingStrategy BOMB = new PlacingStrategy() {
        @Override
        public void place(Space space, int x, int y) {
            explode(space, x, y, 5);
        }
    };

    public static final PlacingStrategy MEGA_BOMB = new PlacingStrategy() {
        @Override
        public void place(Space space, int x, int y) {
            explode(space, x, y, 7);
        }
    };

    public static final PlacingStrategy GRASS_STARTER = new PlacingStrategy() {
        @Override
        public void place(Space space, int x, int y) {
            // Place logic for GRASS_STARTER
        }
    };

    public static final PlacingStrategy MYSTIC_TREE_SEED = new PlacingStrategy() {
        @Override
        public void place(Space space, int x, int y) {
            // Place logic for SAPLING
        }
    };

    public void place(Space space, int x, int y) {
        // Default implementation, can be overridden
    }


    private static void explode(Space space, int x, int y, int r){
        Entity[][] entities = space.entities();
        int xBegin = x-r; int xEnd = x+r;
        int yBegin = y-r; int yEnd = y+r;

        for (int i = xBegin; i<=xEnd; i++)
            for (int j = yBegin; j<=yEnd; j++){
                if (!entities[i][j].getClass().equals(Player.class) &&
                        !entities[i][j].getClass().equals(Building.class))
                    entities[i][j] = null;
            }
    }
}
