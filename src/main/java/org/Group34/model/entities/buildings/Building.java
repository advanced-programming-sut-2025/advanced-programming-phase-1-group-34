package org.Group34.model.entities.buildings;

import org.Group34.model.entities.Entity;
import org.Group34.model.map.Space;

/**
 * Some Entities have specific functionalities of game in themselves
 * Sometimes this class can play role of wall in map
 * That means it's just a dummy object without any functions that just defines boarders of buillding
 */

public class Building extends Entity {
    public Building(int[] initialLocation) {
        super(initialLocation, false);
    }
}
