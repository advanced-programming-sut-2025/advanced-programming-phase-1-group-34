package org.Group34.network.lobby;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private final String id;
    private final String name;
    private String admin;
    private final List<String> players = new ArrayList<>();
    private final boolean isPrivate;
    private final boolean isVisible;
    private final String password;
    private boolean inGame = false;

    public Lobby(String id, String name, String admin, boolean isPrivate, boolean isVisible, String password) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        this.isPrivate = isPrivate;
        this.isVisible = isVisible;
        this.password = password;
        this.players.add(admin);
        System.out.println("Lobby created: " + id + " by " + admin);
    }

    // Getters and setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getAdmin() { return admin; }
    public void setAdmin(String admin) {
        this.admin = admin;
        System.out.println("Lobby " + id + " admin changed to " + admin);
    }
    public List<String> getPlayers() { return new ArrayList<>(players); }
    public int getPlayerCount() { return players.size(); }
    public boolean isPrivate() { return isPrivate; }
    public boolean isVisible() { return isVisible; }
    public boolean isInGame() { return inGame; }
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
        System.out.println("Lobby " + id + " game status: " + inGame);
    }
    public boolean isFull() { return players.size() >= LobbyManager.MAX_LOBBY_CAPACITY; }
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void addPlayer(String username) {
        if (!players.contains(username) && !isFull()) {
            players.add(username);
            System.out.println("Player " + username + " added to lobby " + id);
        }
    }

    public void removePlayer(String username) {
        if (players.remove(username)) {
            System.out.println("Player " + username + " removed from lobby " + id);
        }
    }
}