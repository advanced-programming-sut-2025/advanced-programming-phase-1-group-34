package org.Group34.model.map;

import org.Group34.model.entities.Entity;


/**
 * Each MyGame Map composed of sub individual Spaces in which player switch between them
 * Each Space is a rectangular matrix contained entities in it
 * Farms and NPC village is considered Space
 * Player can switch from its own farm to NPC Village and vise verse
 * Location of each entity is calculated related to its sub Space not in relation to whole map
 */

public record Space(int width, int height, Entity[][] entities) {

    public Entity getEntityByLocation(int x, int y){
        return entities[x][y];
    }

    public void placingEntity(int x, int y, Entity entity) {
        entities[x][y] = entity;
    }
}
