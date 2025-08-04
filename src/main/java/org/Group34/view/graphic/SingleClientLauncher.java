package org.Group34.view.graphic;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class SingleClientLauncher {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Usage: java SingleClientLauncher <title> <x> <y>");
            System.exit(1);
        }

        String title = args[0];
        int x = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);

        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(title);
        config.setWindowedMode(1600, 900);
        config.setWindowPosition(x, y);
        config.setForegroundFPS(60);
        config.useVsync(true);
        config.setAudioConfig(0, 0, 0); // Disable audio

        // Add JVM arguments
        System.setProperty("java.awt.headless", "true");

        new Lwjgl3Application(new GraphicAppView(), config);
    }
}