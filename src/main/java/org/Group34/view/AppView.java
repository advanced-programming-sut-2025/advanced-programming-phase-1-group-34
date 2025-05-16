package org.Group34.view;

import org.Group34.model.App;
import org.Group34.model.Game;
import org.Group34.view.menu.*;

import java.util.Scanner;

public class AppView {
    public void run(Scanner scanner) {
        while (true) {
            switch (App.getCurrentMenu()) {
                case REGISTER_MENU:
                    RegisterMenu registerMenu = new RegisterMenu();
                    registerMenu.run(scanner);
                    break;
                case LOGIN_MENU:
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.run(scanner);
                    break;
                case MAIN_MENU:
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.run(scanner);
                    break;
                case PROFILE_MENU:
                    ProfileMenu profileMenu = new ProfileMenu();
                    profileMenu.run(scanner);
                    break;
                case GAME_MENU:
                    GameMenu gameMenu = new GameMenu();
                    gameMenu.run(scanner);
                    break;
                case GAME:
                    App.getAppMenu().run(scanner);
                    break;
            }
        }
    }
}
