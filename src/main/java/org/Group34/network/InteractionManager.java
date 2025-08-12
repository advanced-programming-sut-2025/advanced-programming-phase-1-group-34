package org.Group34.network;

import org.Group34.model.NetworkObjects.*;

import java.util.ArrayList;
import java.util.HashMap;

public class InteractionManager {
    private NetworkInteraction lastInteraction = null;
    private NetworkShopLimit networkShopLimit = null;
    private NetworkReaction lastReaction = null;
    private ArrayList<String> players = new ArrayList<>();
    private HashMap<String, NetworkPlayerLocation> locations = new HashMap<>();
    private HashMap<String, NetworkScore> scores = new HashMap<>();

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
            scores.put(player, new NetworkScore(player, 0, 0));
        }
    }

    public synchronized HashMap<String, NetworkPlayerLocation> getLocations() {
        return locations;
    }

    public synchronized void setLocations(HashMap<String, NetworkPlayerLocation> locations) {
        this.locations = locations;
    }

    public synchronized NetworkReaction getLastReaction() {
        return lastReaction;
    }

    public synchronized void setLastReaction(NetworkReaction lastReaction) {
        this.lastReaction = lastReaction;
    }

    public HashMap<String, NetworkScore> getScores() {
        return scores;
    }

    public void setScores(HashMap<String, NetworkScore> scores) {
        this.scores = scores;
    }
}
