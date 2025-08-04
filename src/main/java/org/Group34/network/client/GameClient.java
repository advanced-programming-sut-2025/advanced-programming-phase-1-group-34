package org.Group34.network.client;

import java.io.*;
import java.net.Socket;

public class GameClient {
    public interface MessageListener {
        void onMessage(String msg);
    }

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final Thread reader;
    private final MessageListener listener;

    public GameClient(String host, int port, MessageListener listener) throws IOException {
        this.listener = listener;
        System.out.println("Connecting to " + host + ":" + port);

        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());

        reader = new Thread(this::readLoop, "Client-Reader");
        reader.setDaemon(true);
        reader.start();

        System.out.println("Connected successfully");
    }

    public void send(String msg) {
        try {
            synchronized (out) {
                System.out.println("Sending: " + msg);
                out.writeObject(msg);
                out.flush();
            }
        } catch (IOException e) {
            System.err.println("Failed to send message: " + e.getMessage());
        }
    }

    private void readLoop() {
        try {
            while (true) {
                Object o = in.readObject();
                if (o instanceof String s) {
                    if (listener != null) {
                        System.out.println("Received: " + s);
                        listener.onMessage(s);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Disconnected from server.");
        }
    }

    public void close() {
        try {
            System.out.println("Closing connection");
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException ignored) {}
    }
}