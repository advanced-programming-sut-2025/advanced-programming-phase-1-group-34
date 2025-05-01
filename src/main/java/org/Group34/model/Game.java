package org.Group34.model;

import org.Group34.model.entities.Player;
import org.Group34.model.map.Map;

import java.util.HashMap;



public record Game(User creator, HashMap<User, Player> players, Map map) {}
