package org.Group34.network;

import org.Group34.model.NetworkObjects.NetworkInteraction;
import org.Group34.model.NetworkObjects.NetworkPlayerLocation;
import org.Group34.model.NetworkObjects.NetworkShopLimit;

import java.util.ArrayList;
import java.util.HashMap;

public class InteractionManager {
    private NetworkInteraction lastInteraction = null;
    private NetworkShopLimit networkShopLimit = null;
    private ArrayList<String> players = new ArrayList<>();
    private HashMap<String, NetworkPlayerLocation> locations = new HashMap<>();

    public synchronized NetworkInteraction getLastInteraction() {
        return lastInteraction;
    }

    public synchronized void setLastInteraction(NetworkInteraction lastInteraction) {
        this.lastInteraction = lastInteraction;
    }

    public synchronized NetworkShopLimit getNetworkShopLimit() {
        return networkShopLimit;
    }

    public synchronized void setNetworkShopLimit(NetworkShopLimit networkShopLimit) {
        this.networkShopLimit = networkShopLimit;
    }

    public synchronized ArrayList<String> getPlayers() {
        return players;
    }

    public synchronized void setPlayers(ArrayList<String> players) {
        this.players = players;
        for (String player : players) {
            locations.put(player, new NetworkPlayerLocation(player, 1, 1));
        }
    }

    public synchronized HashMap<String, NetworkPlayerLocation> getLocations() {
        return locations;
    }

    public synchronized void setLocations(HashMap<String, NetworkPlayerLocation> locations) {
        this.locations = locations;
    }
}
