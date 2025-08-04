package org.Group34.network.server;

import org.Group34.network.lobby.Lobby;
import org.Group34.network.lobby.LobbyManager;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final LobbyManager lobbyManager;
    private String currentUser;
    private static final AtomicInteger handlerCounter = new AtomicInteger(0);
    private final int handlerId;

    public ClientHandler(Socket socket, LobbyManager lobbyManager) {
        this.socket = socket;
        this.lobbyManager = lobbyManager;
        this.handlerId = handlerCounter.incrementAndGet();
        System.out.println("Created ClientHandler #" + handlerId + " for " + socket.getInetAddress());
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
                    System.out.println("ClientHandler #" + handlerId + " received command: " + command);
                    String[] parts = command.split(" ", 2);
                    String response = processCommand(parts[0], parts.length > 1 ? parts[1] : "");
                    System.out.println("ClientHandler #" + handlerId + " sending response: " + response);
                    out.writeObject(response);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("ClientHandler #" + handlerId + " disconnected: " + socket.getInetAddress());
            if (currentUser != null) {
                System.out.println("ClientHandler #" + handlerId + " removing user " + currentUser + " from lobby");
                lobbyManager.leaveLobby(currentUser);
            }
        }
    }

    private String processCommand(String command, String data) {
        try {
            switch (command) {
                case "LOGIN":
                    // Implement authentication logic
                    currentUser = data; // Assuming data is username after auth
                    System.out.println("ClientHandler #" + handlerId + " user logged in: " + currentUser);
                    return "LOGIN_SUCCESS";

                case "CREATE_LOBBY":
                    String[] createData = data.split(";");
                    boolean isPrivate = Boolean.parseBoolean(createData[1]);
                    boolean isVisible = Boolean.parseBoolean(createData[2]);
                    String password = createData.length > 3 ? createData[3] : "";
                    Lobby lobby = lobbyManager.createLobby(currentUser, createData[0], isPrivate, isVisible, password);
                    System.out.println("ClientHandler #" + handlerId + " created lobby: " + lobby.getId());
                    return "LOBBY_CREATED;" + lobby.getId();

                case "JOIN_LOBBY":
                    String[] joinData = data.split(";");
                    String joinPassword = joinData.length > 1 ? joinData[1] : "";
                    if (lobbyManager.joinLobby(currentUser, joinData[0], joinPassword)) {
                        System.out.println("ClientHandler #" + handlerId + " user " + currentUser + " joined lobby: " + joinData[0]);
                        return "JOIN_SUCCESS;" + lobbyManager.getLobbyById(joinData[0]).getName();
                    } else {
                        System.out.println("ClientHandler #" + handlerId + " user " + currentUser + " failed to join lobby: " + joinData[0]);
                        return "JOIN_FAILED";
                    }

                case "LEAVE_LOBBY":
                    if (lobbyManager.leaveLobby(currentUser)) {
                        System.out.println("ClientHandler #" + handlerId + " user " + currentUser + " left lobby");
                        return "LEFT_LOBBY";
                    } else {
                        System.out.println("ClientHandler #" + handlerId + " user " + currentUser + " failed to leave lobby");
                        return "LEAVE_FAILED";
                    }

                case "START_GAME":
                    if (lobbyManager.startGame(currentUser)) {
                        System.out.println("ClientHandler #" + handlerId + " game started by user: " + currentUser);
                        return "GAME_STARTED";
                    } else {
                        System.out.println("ClientHandler #" + handlerId + " failed to start game by user: " + currentUser);
                        return "START_FAILED";
                    }

                case "LIST_LOBBIES":
                    List<Lobby> lobbies = lobbyManager.getVisibleLobbies();
                    StringBuilder sb = new StringBuilder();
                    for (Lobby l : lobbies) {
                        sb.append(l.getId()).append(",")
                                .append(l.getName()).append(",")
                                .append(l.getPlayerCount()).append(";");
                    }
                    String lobbiesList = sb.length() > 0 ? sb.substring(0, sb.length()-1) : "";
                    System.out.println("ClientHandler #" + handlerId + " sending lobbies list: " + lobbiesList);
                    return "LOBBIES_LIST;" + lobbiesList;

                case "LOBBY_INFO":
                    Lobby userLobby = lobbyManager.getLobbyByUser(currentUser);
                    if (userLobby != null) {
                        String info = "LOBBY_INFO;" + userLobby.getId() + ";" +
                                userLobby.getName() + ";" +
                                userLobby.getAdmin() + ";" +
                                String.join(",", userLobby.getPlayers());
                        System.out.println("ClientHandler #" + handlerId + " sending lobby info: " + info);
                        return info;
                    } else {
                        System.out.println("ClientHandler #" + handlerId + " user not in lobby");
                        return "NOT_IN_LOBBY";
                    }

                default:
                    System.out.println("ClientHandler #" + handlerId + " unknown command: " + command);
                    return "UNKNOWN_COMMAND";
            }
        } catch (Exception e) {
            System.err.println("ClientHandler #" + handlerId + " error processing command: " + e.getMessage());
            return "ERROR:" + e.getMessage();
        }
    }
}