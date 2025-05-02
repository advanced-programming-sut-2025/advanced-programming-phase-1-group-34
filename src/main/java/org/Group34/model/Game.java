package org.Group34.model;

import org.Group34.model.entities.Player;
import org.Group34.model.features.Time;
import org.Group34.model.map.Map;

import java.util.HashMap;



public record Game(User creator, HashMap<User, Player> players, Map map, Time time) {

    public void save(){
        //TODO saves in files in directory
    }

    public void delete(){
        //TODO removes files from directory

        for (User user: players.keySet())
            user.setGame(null);
    }
}
