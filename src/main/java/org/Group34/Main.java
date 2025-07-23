package org.Group34;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.Group34.view.AppView;
import org.Group34.view.graphic.GraphicAppView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Thread graphicThread = new Thread(() -> {
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setTitle("Stardew Valley - Graphic Mode");
            config.setWindowedMode(1600, 900);
            new Lwjgl3Application(new GraphicAppView(), config);
        });
        graphicThread.start();

        Scanner scanner = new Scanner(System.in);
        AppView appView = new AppView();
        appView.run(scanner);
    }
}
