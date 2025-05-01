package org.Group34.model.entities;

import org.Group34.model.map.Space;

/**
* Anything that can be displayed on the map is an entity
 * Each Entity has a location related to its Space which is in it
 * Some Entities like Building are not removeAble
 */

public abstract class Entity {
    private int[] location;
    private boolean removeAble;

    private class EntityRemovalException extends RuntimeException {
        public EntityRemovalException(Entity entityName) {
            super("Error: Entity " + entityName + " can not be removed");
        }
    }
    public Entity(int[] initialLocation, boolean removeAble) {
        this.location = initialLocation;
        this.removeAble = removeAble;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        if (removeAble)
            this.location = location;
        else throw new EntityRemovalException(this);
    }
}
