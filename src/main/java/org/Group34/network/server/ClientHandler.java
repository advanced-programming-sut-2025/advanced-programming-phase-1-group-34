package org.Group34.network.server;

import org.Group34.model.NetworkObjects.NetworkInteraction;
import org.Group34.model.User;
import org.Group34.network.InteractionManager;
import org.Group34.network.LobbyManager;
import org.Group34.model.TestObject;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final LobbyManager lobbyManager;
    private User currentUser = null;
    private static final AtomicInteger handlerCounter = new AtomicInteger(0);
    private final int handlerId;

    private final InteractionManager interactionManager;

    public ClientHandler(Socket socket, LobbyManager lobbyManager, InteractionManager interactionManager) {
        this.socket = socket;
        this.lobbyManager = lobbyManager;
        this.handlerId = handlerCounter.incrementAndGet();
        System.out.println("Created ClientHandler #" + handlerId + " for " + socket.getInetAddress());

        this.interactionManager = interactionManager;
    }

    @Override
    public void run() {
        try (
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ) {
            System.out.println("ClientHandler #" + handlerId + " started processing");
            while (true) {
                Object input = in.readObject();
                if (input instanceof String command) {
                    if (command.startsWith("social")) {
                        System.out.println("ClientHandler #" + handlerId + " received command: " + command);
                        Object response = processSocialCommand(command);
                        out.writeObject(response);
                        System.out.println("ClientHandler #" + handlerId + " sending response: " + response);
                    } else {
                        System.out.println("ClientHandler #" + handlerId + " received command: " + command);
                        String[] parts = command.split(" ", 5);
                        String response = processCommand(parts[0],
                                parts.length > 1 ? parts[1] : "",
                                parts.length > 2 ? parts[2] : "",
                                parts.length > 3 ? parts[3] : "",
                                parts.length > 4 ? parts[4] : "");
                        System.out.println("ClientHandler #" + handlerId + " sending response: " + response);
                        out.writeObject(response);
                    }
                } else if (input instanceof User) {
                    currentUser = (User) input;
                    String response = lobbyManager.connectUser(currentUser);
                    System.out.println("ClientHandler #" + handlerId + " set user to: " + currentUser.getUsername());
                    out.writeObject(response);
                } else if (input instanceof NetworkInteraction) {
                    System.out.println("ClientHandler #" + handlerId + " received command: " + input);
                    String response = processSocialObject(input);
                    out.writeObject(response);
                    System.out.println("ClientHandler #" + handlerId + " sending response: " + response);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ClientHandler #" + handlerId + " disconnected: " + socket.getInetAddress());
            if (currentUser != null) {
                lobbyManager.disconnectUser(currentUser);
                lobbyManager.leaveLobby(currentUser);
            }
        }
    }

    private String processCommand(String command, String param1, String param2, String param3, String param4) {
        try {
            switch (command) {
                case "GET_LOBBIES":
                    return lobbyManager.getLobbies();

                case "SEARCH_LOBBY":
                    return lobbyManager.searchLobby(param1);

                case "CREATE_LOBBY":
                    String lobbyName = param1;
                    boolean isPrivate = Boolean.parseBoolean(param2);
                    boolean isVisible = Boolean.parseBoolean(param3);
                    String password = param4;
                    if (currentUser == null) {
                        return "ERROR:User not set";
                    }
                    String lobbyId = lobbyManager.createLobby(lobbyName, isPrivate, isVisible, password, currentUser);
                    return "LOBBY_CREATED:" + lobbyId;

                case "JOIN_LOBBY":
                    String lobbyIdToJoin = param1;
                    String joinPassword = param2.isEmpty() ? "" : param2;
                    if (currentUser == null) {
                        return "ERROR:User not set";
                    }
                    return lobbyManager.joinLobby(currentUser, lobbyIdToJoin, joinPassword);

                case "START_GAME":
                    String lobbyIdToStart = param1;
                    if (currentUser == null) {
                        return "ERROR:User not set";
                    }
                    return lobbyManager.startGame(lobbyIdToStart, currentUser);

                case "GET_PLAYERS":
                    String lobbyIdForPlayers = param1;
                    return lobbyManager.getPlayers(lobbyIdForPlayers);

                case "LEAVE_LOBBY":
                    String lobbyIdToLeave = param1;
                    if (currentUser == null) {
                        return "ERROR:User not set";
                    }
                    return lobbyManager.leaveLobby(currentUser, lobbyIdToLeave);

                case "GET_ALL_PLAYERS":
                    return lobbyManager.getAllPlayers();

                case "CHECK_GAME_STATUS":
                    String lobbyIdToCheck = param1;
                    if (currentUser == null) {
                        return "ERROR:User not set";
                    }
                    return lobbyManager.checkGameStatus(lobbyIdToCheck, currentUser);

                default:
                    System.out.println("ClientHandler #" + handlerId + " unknown command: " + command);
                    return "UNKNOWN_COMMAND";
            }
        } catch (Exception e) {
            System.err.println("ClientHandler #" + handlerId + " error processing command: " + e.getMessage());
            return "ERROR:" + e.getMessage();
        }
    }

    private Object processSocialCommand(String command) {
        try {
            if (command.equals("socialGetPlayers")) {
                return lobbyManager.getLobbyPlayers();
            } else if (command.equals("socialGetLastInteraction")) {
                return interactionManager.getLastInteraction();
            } else if (command.equals("socialReadLastInteraction")) {
                interactionManager.setLastInteraction(null);
                return "reset last interaction";
            }
            else {
                return "Unknown Message";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String processSocialObject(Object object) {
        if (object instanceof NetworkInteraction interaction) {
            interactionManager.setLastInteraction(interaction);
            return "last interaction set";
        }

        return "Unknown Message";
    }
}