package org.Group34.model;

import org.Group34.controller.WeatherSystem;
import org.Group34.model.entities.Player;
import org.Group34.model.map.Map;

import java.util.HashMap;



public record Game(User creator, HashMap<User, Player> players, Map map, Time time, WeatherSystem weatherSystem) {

    public static Game load(String userName){
        //TODO reads json file and returns null if there is no game
        return null;
    }

    public void save(){
        //TODO saves in files in directory
    }

    public void delete(){
        //TODO removes files from directory

        for (User user: players.keySet())
            user.setGame(null);
    }
}
