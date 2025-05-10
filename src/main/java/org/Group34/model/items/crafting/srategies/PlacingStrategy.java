package org.Group34.model.items.crafting.srategies;

import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.Building;
import org.Group34.model.entities.naturalElements.ForagingTree;
import org.Group34.model.enums.creatorOfNaturalElements.TreeCreator;
import org.Group34.model.map.Space;

import java.util.HashMap;

public enum PlacingStrategy {

    PLACE {
        @Override
        public boolean place(Space space, Entity craft, int x, int y) {
            Entity[][] entities = space.entities();

            if (entities[x][y] != null)
                return false;

            entities[x][y] = craft;
            return true;
        }
    },

    CHERRY_BOMB {
        @Override
        public boolean place(Space space, Entity craft, int x, int y) {
            return explode(space, x, y, 3);
        }
    },

     BOMB {
        @Override
        public boolean place(Space space, Entity craft, int x, int y) {
            return explode(space, x, y, 5);
        }
    },

    MEGA_BOMB {
        @Override
        public boolean place(Space space, Entity craft, int x, int y) {
            return explode(space, x, y, 7);
        }
    },

    GRASS_STARTER {
        @Override
        public boolean place(Space space, Entity craft, int x, int y) {
            return true;
        }
    },

    MYSTIC_TREE_SEED{
        @Override
        public boolean place(Space space, Entity craft, int x, int y) {
            Entity[][] entities = space.entities();

            if (entities[x][y] != null)
                return false;

            entities[x][y] = TreeCreator.MYSTIC_TREE.createInstance();
            return true;
        }
    };

    public abstract boolean place(Space space, Entity craft, int x, int y);


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
}
