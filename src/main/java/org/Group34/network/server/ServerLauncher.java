package org.Group34.network.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ServerLauncher {
    public static void main(String[] args) {
        // Try different ports if the default one is in use
        int[] ports = {12345, 12346, 12347, 12348, 12349};

        for (int port : ports) {
            try {
                if (isPortAvailable(port)) {
                    GameServer server = new GameServer();
                    server.start(port);
                    System.out.println("Server started successfully on port " + port);
                    return;
                }
            } catch (IOException e) {
                System.err.println("Failed to start server on port " + port + ": " + e.getMessage());
            }
        }

        System.err.println("Failed to start server on any port");
    }

    private static boolean isPortAvailable(int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("localhost", port), 100);
            return false; // Port is in use
        } catch (IOException e) {
            return true; // Port is available
        }
    }
}