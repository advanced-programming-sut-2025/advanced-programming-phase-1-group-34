package org.Group34.network;

import org.Group34.model.User;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LobbyManager {
    private final Map<String, Lobby> lobbies = new ConcurrentHashMap<>();
    private final Map<User, String> userLobbyMap = new ConcurrentHashMap<>();
    private final Random random = new Random();
    private final Timer cleanupTimer = new Timer();

    public LobbyManager() {
        cleanupTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                cleanupInactiveLobbies();
            }
        }, 60000, 60000);
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
                if (lobby.admin.equals(user) && !lobby.players.isEmpty()) {
                    lobby.admin = lobby.players.get(0);
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
                lobby.admin = null;
            }
        }

        return "LEFT_LOBBY:" + lobbyId;
    }

    public synchronized String getLobbies() {
        StringBuilder sb = new StringBuilder("LOBBY_LIST:");
        for (Lobby lobby : lobbies.values()) {
            sb.append(lobby.id).append(",")
                    .append(lobby.name).append(",")
                    .append(lobby.players.size()).append(",")
                    .append(lobby.maxPlayers).append(",")
                    .append(lobby.isPrivate).append(",")
                    .append(lobby.isVisible).append(",")
                    .append(lobby.admin.getUsername()).append("|");
        }
        return sb.toString();
    }

    public synchronized String searchLobby(String searchTerm) {
        StringBuilder sb = new StringBuilder("LOBBY_LIST:");
        boolean found = false;
        for (Lobby lobby : lobbies.values()) {
            if (lobby.isVisible || lobby.name.equalsIgnoreCase(searchTerm)) {
                sb.append(lobby.id).append(",")
                        .append(lobby.name).append(",")
                        .append(lobby.players.size()).append(",")
                        .append(lobby.maxPlayers).append(",")
                        .append(lobby.isPrivate).append(",")
                        .append(lobby.isVisible).append(",")
                        .append(lobby.admin.getUsername()).append("|");
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
        return "GAME_STARTED:" + lobbyId;
    }

    public synchronized String getPlayers(String lobbyId) {
        Lobby lobby = lobbies.get(lobbyId);
        if (lobby == null) {
            return "ERROR:Lobby not found";
        }
        StringBuilder sb = new StringBuilder("PLAYER_LIST:");
        sb.append(lobbyId).append(":");
        sb.append(lobby.admin.getUsername());
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
            if (lobby.players.isEmpty() && currentTime - lobby.lastActivityTime > 300000) {
                iterator.remove();
                System.out.println("Removed inactive lobby: " + lobby.id + " (" + lobby.name + ")");
            }
        }
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

        Lobby(String id, String name, boolean isPrivate, boolean isVisible, String password) {
            this.id = id;
            this.name = name;
            this.isPrivate = isPrivate;
            this.isVisible = isVisible;
            this.password = password;
            this.admin = null;
            this.lastActivityTime = System.currentTimeMillis();
        }
    }
}