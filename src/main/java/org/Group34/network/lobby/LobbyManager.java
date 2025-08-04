package org.Group34.network.lobby;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LobbyManager {
    public static final int MAX_LOBBY_CAPACITY = 4;
    private static final int LOBBY_TIMEOUT_MINUTES = 5;

    private final Map<String, Lobby> lobbies = new ConcurrentHashMap<>();
    private final Map<String, String> userToLobbyMap = new ConcurrentHashMap<>();
    private final Timer cleanupTimer = new Timer(true);

    public LobbyManager() {
        // Schedule periodic cleanup of empty lobbies
        cleanupTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                cleanupEmptyLobbies();
            }
        }, LOBBY_TIMEOUT_MINUTES * 60 * 1000, LOBBY_TIMEOUT_MINUTES * 60 * 1000);
    }

    public synchronized Lobby createLobby(String adminUsername, String lobbyName, boolean isPrivate, boolean isVisible, String password) {
        if (userToLobbyMap.containsKey(adminUsername)) {
            throw new IllegalStateException("User is already in a lobby");
        }

        String lobbyId = UUID.randomUUID().toString().substring(0, 8);
        Lobby lobby = new Lobby(lobbyId, lobbyName, adminUsername, isPrivate, isVisible, password);
        lobbies.put(lobbyId, lobby);
        userToLobbyMap.put(adminUsername, lobbyId);
        return lobby;
    }

    public synchronized boolean joinLobby(String username, String lobbyId, String password) {
        if (userToLobbyMap.containsKey(username)) {
            return false; // Already in a lobby
        }

        Lobby lobby = lobbies.get(lobbyId);
        if (lobby == null || lobby.isFull() ||
                (lobby.isPrivate() && !lobby.checkPassword(password))) {
            return false;
        }

        lobby.addPlayer(username);
        userToLobbyMap.put(username, lobbyId);
        return true;
    }

    public synchronized boolean leaveLobby(String username) {
        String lobbyId = userToLobbyMap.get(username);
        if (lobbyId == null) {
            return false; // Not in any lobby
        }

        Lobby lobby = lobbies.get(lobbyId);
        if (lobby == null) {
            userToLobbyMap.remove(username);
            return false;
        }

        lobby.removePlayer(username);
        userToLobbyMap.remove(username);

        // Handle admin transfer or lobby deletion
        if (lobby.getAdmin().equals(username)) {
            if (lobby.getPlayers().isEmpty()) {
                lobbies.remove(lobbyId);
            } else {
                // Transfer admin to next player
                String newAdmin = lobby.getPlayers().get(0);
                lobby.setAdmin(newAdmin);
            }
        }

        return true;
    }

    public synchronized boolean startGame(String adminUsername) {
        String lobbyId = userToLobbyMap.get(adminUsername);
        if (lobbyId == null) {
            return false;
        }

        Lobby lobby = lobbies.get(lobbyId);
        if (lobby == null || !lobby.getAdmin().equals(adminUsername) || lobby.getPlayerCount() < 2) {
            return false;
        }

        // Mark lobby as in-game (can't join anymore)
        lobby.setInGame(true);
        return true;
    }

    public List<Lobby> getVisibleLobbies() {
        List<Lobby> visibleLobbies = new ArrayList<>();
        for (Lobby lobby : lobbies.values()) {
            if (lobby.isVisible() && !lobby.isInGame() && !lobby.isPrivate()) {
                visibleLobbies.add(lobby);
            }
        }
        return visibleLobbies;
    }

    public Lobby getLobbyById(String lobbyId) {
        return lobbies.get(lobbyId);
    }

    public Lobby getLobbyByUser(String username) {
        String lobbyId = userToLobbyMap.get(username);
        return lobbyId != null ? lobbies.get(lobbyId) : null;
    }

    private void cleanupEmptyLobbies() {
        Iterator<Map.Entry<String, Lobby>> it = lobbies.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Lobby> entry = it.next();
            if (entry.getValue().getPlayerCount() == 0) {
                it.remove();
            }
        }
    }
}
