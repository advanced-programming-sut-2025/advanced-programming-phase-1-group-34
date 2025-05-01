package org.Group34.model.map;

import org.Group34.model.entities.Player;

import java.util.HashMap;


/**
 * Each Map has to remember each Farm is for what player
 * Map has to handle switching between its spaces
 * There is no universal location for entities in this game
 */

public record Map(HashMap<Player, Space> playerFarms, Space NPCVillage) {
}
