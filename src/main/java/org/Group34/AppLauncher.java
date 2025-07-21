package org.Group34;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.Group34.view.graphic.GraphicAppView;

public class AppLauncher {
    public static void main(String[] args) {
        boolean graphicMode = true;

        if (graphicMode) {
            Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
            config.setTitle("Stardew Valley - Graphic Mode");
            config.setWindowedMode(960, 540);
            new Lwjgl3Application(new GraphicAppView(), config);
        }
        else {
            Main.main(null);
        }
    }
}
