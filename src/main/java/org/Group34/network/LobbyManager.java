package org.Group34.network;

import org.Group34.model.User;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LobbyManager {
    private final Map<String, Lobby> lobbies = new ConcurrentHashMap<>();
    private final Map<User, String> userLobbyMap = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private final Timer cleanupTimer = new Timer();
    private final Set<User> connectedUsers = Collections.synchronizedSet(new HashSet<>());

    public LobbyManager() {
        cleanupTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                cleanupInactiveLobbies();
            }
        }, 60000, 60000);
    }

    public synchronized String connectUser(User user) {
        connectedUsers.add(user);
        return "USER_CONNECTED:" + user.getUsername();
    }

    public synchronized String disconnectUser(User user) {
        connectedUsers.remove(user);
        return "USER_DISCONNECTED:" + user.getUsername();
    }

    public synchronized String createLobby(String name, boolean isPrivate, boolean isVisible, String password, User creator) {
        String lobbyId = generateLobbyId();
        Lobby lobby = new Lobby(lobbyId, name, isPrivate, isVisible, password);
        lobby.players.add(creator);
        lobby.admin = creator;
        userLobbyMap.put(creator, lobbyId);
        lobbies.put(lobbyId, lobby);
        return lobbyId;
    }

    public synchronized String joinLobby(User user, String lobbyId, String password) {
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
        if (userLobbyMap.containsKey(user)) {
            leaveLobby(user);
        }
        lobby.players.add(user);
        lobby.lastActivityTime = System.currentTimeMillis();
        userLobbyMap.put(user, lobbyId);
        return "JOINED_LOBBY:" + lobbyId;
    }

    public synchronized void leaveLobby(User user) {
        String lobbyId = userLobbyMap.get(user);
        if (lobbyId != null) {
            Lobby lobby = lobbies.get(lobbyId);
            if (lobby != null) {
                lobby.players.remove(user);
                lobby.lastActivityTime = System.currentTimeMillis();
                // If the leaving user was admin and there are other players, transfer admin
                if (lobby.admin.equals(user) && !lobby.players.isEmpty()) {
                    lobby.admin = lobby.players.get(0);
                }
                // If the lobby is now empty, remove it
                else if (lobby.players.isEmpty()) {
                    lobbies.remove(lobbyId);
                }
            }
            userLobbyMap.remove(user);
        }
    }

    public synchronized String leaveLobby(User user, String lobbyId) {
        // Verify user is in the specified lobby
        String currentLobbyId = userLobbyMap.get(user);
        if (currentLobbyId == null || !currentLobbyId.equals(lobbyId)) {
            return "ERROR:User is not in the specified lobby";
        }

        Lobby lobby = lobbies.get(lobbyId);
        if (lobby == null) {
            return "ERROR:Lobby not found";
        }

        // Remove user from lobby
        lobby.players.remove(user);
        lobby.lastActivityTime = System.currentTimeMillis();
        userLobbyMap.remove(user);

        // Handle admin transfer if leaving user was admin
        if (lobby.admin.equals(user)) {
            if (!lobby.players.isEmpty()) {
                lobby.admin = lobby.players.get(0);
            } else {
                // If lobby is empty, remove it
                lobbies.remove(lobbyId);
            }
        }

        return "LEFT_LOBBY:" + lobbyId;
    }

    public synchronized String getLobbies() {
        StringBuilder sb = new StringBuilder("LOBBY_LIST:");
        Iterator<Map.Entry<String, Lobby>> iterator = lobbies.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Lobby> entry = iterator.next();
            Lobby lobby = entry.getValue();

            // Skip empty lobbies (where admin is null)
            if (lobby.admin == null) {
                iterator.remove();
                continue;
            }

            sb.append(lobby.id).append(",")
                    .append(lobby.name).append(",")
                    .append(lobby.players.size()).append(",")
                    .append(lobby.maxPlayers).append(",")
                    .append(lobby.isPrivate).append(",")
                    .append(lobby.isVisible).append(",")
                    .append(lobby.admin.getUsername()).append("|"); // Add admin username
        }
        return sb.toString();
    }

    public synchronized String searchLobby(String searchTerm) {
        StringBuilder sb = new StringBuilder("LOBBY_LIST:");
        boolean found = false;
        Iterator<Map.Entry<String, Lobby>> iterator = lobbies.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Lobby> entry = iterator.next();
            Lobby lobby = entry.getValue();

            // Skip empty lobbies (where admin is null)
            if (lobby.admin == null) {
                iterator.remove();
                continue;
            }

            if (lobby.isVisible || lobby.name.equalsIgnoreCase(searchTerm)) {
                sb.append(lobby.id).append(",")
                        .append(lobby.name).append(",")
                        .append(lobby.players.size()).append(",")
                        .append(lobby.maxPlayers).append(",")
                        .append(lobby.isPrivate).append(",")
                        .append(lobby.isVisible).append(",")
                        .append(lobby.admin.getUsername()).append("|"); // Add admin username
                found = true;
            }
        }
        return found ? sb.toString() : "LOBBY_LIST:";
    }

    public synchronized String startGame(String lobbyId, User user) {
        Lobby lobby = lobbies.get(lobbyId);
        if (lobby == null) {
            return "ERROR:Lobby not found";
        }
        if (!lobby.admin.equals(user)) {
            return "ERROR:Only the lobby admin can start the game";
        }
        if (lobby.players.size() < 2) {
            return "ERROR:Need at least 2 players to start the game";
        }

        lobby.gameStarted = true;

        return "GAME_STARTED:" + lobbyId;
    }

    public synchronized String getPlayers(String lobbyId) {
        Lobby lobby = lobbies.get(lobbyId);
        if (lobby == null) {
            return "ERROR:Lobby not found";
        }
        StringBuilder sb = new StringBuilder("PLAYER_LIST:");
        sb.append(lobbyId).append(":");
        // First, add the admin
        sb.append(lobby.admin.getUsername());
        // Then add other players (excluding the admin)
        for (User player : lobby.players) {
            if (!player.equals(lobby.admin)) {
                sb.append(",").append(player.getUsername());
            }
        }
        return sb.toString();
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

            // Remove lobbies with only one player that have been inactive for more than 5 minutes
            if (lobby.players.size() == 1 && currentTime - lobby.lastActivityTime > 1000) {
                iterator.remove();
                System.out.println("Removed inactive single-player lobby: " + lobby.id + " (" + lobby.name + ")");
            }
            // Also remove completely empty lobbies (for safety)
            else if (lobby.players.isEmpty() && currentTime - lobby.lastActivityTime > 1000) {
                iterator.remove();
                System.out.println("Removed inactive empty lobby: " + lobby.id + " (" + lobby.name + ")");
            }
        }
    }

    public synchronized String getAllPlayers() {
        StringBuilder sb = new StringBuilder("ALL_PLAYERS:");
        // Add all connected users first
        for (User user : connectedUsers) {
            String lobbyId = userLobbyMap.getOrDefault(user, "Not in lobby");
            sb.append(user.getUsername()).append(",").append(lobbyId).append("|");
        }
        return sb.toString();
    }

    public synchronized String checkGameStatus(String lobbyId, User user) {
        Lobby lobby = lobbies.get(lobbyId);
        if (lobby == null) {
            return "GAME_STATUS:" + lobbyId + ":false";
        }

        if (!lobby.players.contains(user)) {
            return "GAME_STATUS:" + lobbyId + ":false";
        }

        return "GAME_STATUS:" + lobbyId + ":" + lobby.gameStarted;
    }

    private static class Lobby {
        final String id;
        final String name;
        final boolean isPrivate;
        final String password;
        final List<User> players = new ArrayList<>();
        User admin;
        final int maxPlayers = 4;
        boolean isVisible;
        long lastActivityTime;

        boolean gameStarted = false;

        Lobby(String id, String name, boolean isPrivate, boolean isVisible, String password) {
            this.id = id;
            this.name = name;
            this.isPrivate = isPrivate;
            this.isVisible = isVisible;
            this.password = password;
            this.admin = null;
            this.lastActivityTime = System.currentTimeMillis();
            this.gameStarted = false;
        }
    }
}