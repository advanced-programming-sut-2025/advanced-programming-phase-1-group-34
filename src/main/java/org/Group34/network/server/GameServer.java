package org.Group34.network.server;

import org.Group34.network.client.ClientHandler;
import org.Group34.network.lobby.LobbyManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket serverSocket;
    private final LobbyManager lobbyManager = new LobbyManager();

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("New client connected: " + clientSocket.getInetAddress());
            new ClientHandler(clientSocket, lobbyManager).start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }
}