package org.Group34.network;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LobbyManager {
    private final Map<String, Lobby> lobbies = new ConcurrentHashMap<>();
    private final Map<String, String> userLobbyMap = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private final Timer cleanupTimer = new Timer();

    public LobbyManager() {
        // Start cleanup task to remove inactive lobbies
        cleanupTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                cleanupInactiveLobbies();
            }
        }, 60000, 60000); // Check every minute
    }

    public synchronized String createLobby(String name, boolean isPrivate, String password) {
        String lobbyId = generateLobbyId();
        Lobby lobby = new Lobby(lobbyId, name, isPrivate, password);
        lobbies.put(lobbyId, lobby);
        return lobbyId;
    }

    public synchronized String joinLobby(String userId, String lobbyId, String password) {
        Lobby lobby = lobbies.get(lobbyId);
        if (lobby == null) {
            return "ERROR:Lobby not found";
        }

        if (lobby.isPrivate && !lobby.password.equals(password)) {
            return "ERROR:Invalid password";
        }

        if (lobby.players.size() >= lobby.maxPlayers) {
            return "ERROR:Lobby is full";
        }

        // Remove user from previous lobby if any
        if (userLobbyMap.containsKey(userId)) {
            leaveLobby(userId);
        }

        lobby.players.add(userId);
        userLobbyMap.put(userId, lobbyId);
        return "JOINED_LOBBY:" + lobbyId;
    }

    public synchronized void leaveLobby(String userId) {
        String lobbyId = userLobbyMap.get(userId);
        if (lobbyId != null) {
            Lobby lobby = lobbies.get(lobbyId);
            if (lobby != null) {
                lobby.players.remove(userId);
                lobby.lastActivityTime = System.currentTimeMillis();

                // Transfer admin if the admin left
                if (lobby.admin.equals(userId) && !lobby.players.isEmpty()) {
                    lobby.admin = lobby.players.get(0);
                }

                // Remove lobby if empty
                if (lobby.players.isEmpty()) {
                    lobbies.remove(lobbyId);
                }
            }
            userLobbyMap.remove(userId);
        }
    }

    public synchronized String getLobbies() {
        StringBuilder sb = new StringBuilder("LOBBY_LIST:");

        for (Lobby lobby : lobbies.values()) {
            sb.append(lobby.id).append(",")
                    .append(lobby.name).append(",")
                    .append(lobby.players.size()).append(",")
                    .append(lobby.maxPlayers).append(",")
                    .append(lobby.isPrivate).append("|");
        }

        return sb.toString();
    }

    public synchronized String searchLobby(String searchTerm) {
        StringBuilder sb = new StringBuilder("LOBBY_LIST:");
        boolean found = false;

        for (Lobby lobby : lobbies.values()) {
            if (lobby.id.equals(searchTerm) ||
                    (lobby.isVisible && lobby.name.toLowerCase().contains(searchTerm.toLowerCase()))) {
                sb.append(lobby.id).append(",")
                        .append(lobby.name).append(",")
                        .append(lobby.players.size()).append(",")
                        .append(lobby.maxPlayers).append(",")
                        .append(lobby.isPrivate).append("|");
                found = true;
            }
        }

        return found ? sb.toString() : "LOBBY_LIST:";
    }

    private String generateLobbyId() {
        String id;
        do {
            id = String.valueOf(100000 + random.nextInt(900000));
        } while (lobbies.containsKey(id));
        return id;
    }

    private void cleanupInactiveLobbies() {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<String, Lobby>> iterator = lobbies.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Lobby> entry = iterator.next();
            Lobby lobby = entry.getValue();

            // Remove lobbies inactive for more than 5 minutes
            if (currentTime - lobby.lastActivityTime > 300000) {
                // Remove all users from this lobby
                for (String userId : lobby.players) {
                    userLobbyMap.remove(userId);
                }
                iterator.remove();
            }
        }
    }

    private static class Lobby {
        final String id;
        final String name;
        final boolean isPrivate;
        final String password;
        final List<String> players = new ArrayList<>();
        String admin;
        final int maxPlayers = 4;
        boolean isVisible = true;
        long lastActivityTime;

        Lobby(String id, String name, boolean isPrivate, String password) {
            this.id = id;
            this.name = name;
            this.isPrivate = isPrivate;
            this.password = password;
            this.admin = ""; // Will be set when first player joins
            this.lastActivityTime = System.currentTimeMillis();
        }
    }
}