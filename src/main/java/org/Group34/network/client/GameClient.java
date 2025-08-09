package org.Group34.network.client;
import org.Group34.model.TestObject;
import org.Group34.model.User;
import org.Group34.model.entities.Player;

import java.io.*;
import java.net.Socket;
import java.util.function.Consumer;

public class GameClient {
    private final Socket socket;
    private final ObjectOutputStream out;
    private final Consumer<Object> messageHandler;
    private final Thread receiveThread;

    public GameClient(String host, int port, Consumer<Object> messageHandler) throws IOException {
        this.socket = new Socket(host, port);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.messageHandler = messageHandler;

        // Start receiving thread
        this.receiveThread = new Thread(() -> {
            try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
                while (true) {
                    Object received = in.readObject();
                    if (received instanceof String) {
                        messageHandler.accept(received);
                    } else if (received instanceof TestObject testObject) {
                        messageHandler.accept(received);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Connection closed: " + e.getMessage());
            }
        });
        this.receiveThread.start();
    }

    public void send(String message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            System.err.println("Failed to send message: " + e.getMessage());
        }
    }

    public void sendUser(User user) {
        try {
            out.writeObject(user);
            out.flush();
        } catch (IOException e) {
            System.err.println("Failed to send user: " + e.getMessage());
        }
    }

    public void close() {
        try {
            receiveThread.interrupt();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}