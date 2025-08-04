package org.Group34.network.client;

import org.Group34.network.lobby.Lobby;
import org.Group34.network.lobby.LobbyManager;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final LobbyManager lobbyManager;
    private String currentUser;

    public ClientHandler(Socket socket, LobbyManager lobbyManager) {
        this.socket = socket;
        this.lobbyManager = lobbyManager;
    }

    @Override
    public void run() {
        try (
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ) {
            while (true) {
                Object input = in.readObject();

                if (input instanceof String command) {
                    String[] parts = command.split(" ", 2);
                    String response = processCommand(parts[0], parts.length > 1 ? parts[1] : "");
                    out.writeObject(response);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Client disconnected: " + socket.getInetAddress());
            if (currentUser != null) {
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
                    return "LOGIN_SUCCESS";

                case "CREATE_LOBBY":
                    String[] createData = data.split(";");
                    boolean isPrivate = Boolean.parseBoolean(createData[1]);
                    boolean isVisible = Boolean.parseBoolean(createData[2]);
                    String password = createData.length > 3 ? createData[3] : "";
                    Lobby lobby = lobbyManager.createLobby(currentUser, createData[0], isPrivate, isVisible, password);
                    return "LOBBY_CREATED;" + lobby.getId();

                case "JOIN_LOBBY":
                    String[] joinData = data.split(";");
                    String joinPassword = joinData.length > 1 ? joinData[1] : "";
                    if (lobbyManager.joinLobby(currentUser, joinData[0], joinPassword)) {
                        return "JOIN_SUCCESS;" + lobbyManager.getLobbyById(joinData[0]).getName();
                    } else {
                        return "JOIN_FAILED";
                    }

                case "LEAVE_LOBBY":
                    if (lobbyManager.leaveLobby(currentUser)) {
                        return "LEFT_LOBBY";
                    } else {
                        return "LEAVE_FAILED";
                    }

                case "START_GAME":
                    if (lobbyManager.startGame(currentUser)) {
                        return "GAME_STARTED";
                    } else {
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
                    return "LOBBIES_LIST;" + (sb.length() > 0 ? sb.substring(0, sb.length()-1) : "");

                case "LOBBY_INFO":
                    Lobby userLobby = lobbyManager.getLobbyByUser(currentUser);
                    if (userLobby != null) {
                        return "LOBBY_INFO;" + userLobby.getId() + ";" +
                                userLobby.getName() + ";" +
                                userLobby.getAdmin() + ";" +
                                String.join(",", userLobby.getPlayers());
                    } else {
                        return "NOT_IN_LOBBY";
                    }

                default:
                    return "UNKNOWN_COMMAND";
            }
        } catch (Exception e) {
            return "ERROR:" + e.getMessage();
        }
    }
}