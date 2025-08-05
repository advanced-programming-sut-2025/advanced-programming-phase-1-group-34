package org.Group34;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.Group34.model.App;
import org.Group34.network.server.GameServer;
import org.Group34.view.AppView;
import org.Group34.view.graphic.GraphicAppView;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Graphic
        Thread graphicThread = new Thread(() -> {
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setTitle("Stardew Valley - Graphic Mode");
            config.setWindowedMode(1600, 900);
            new Lwjgl3Application(new GraphicAppView(), config);
        });
        graphicThread.start();

        // Server
        Thread serverThread = new Thread(() -> {
            try {
                GameServer server = new GameServer();
                server.start(12345);
                System.out.println("Server started");
            } catch (IOException e) {
                System.err.println("Server failed to start");
                e.printStackTrace();
            }
        });
        serverThread.start();

        // Terminal
        Scanner scanner = new Scanner(System.in);
        AppView appView = new AppView();
        appView.run(scanner);
    }
}
