package org.Group34.view;

import org.Group34.model.enums.Menu;
import org.Group34.view.menu.*;

import java.util.Scanner;

public class AppView {
    private Menu currentMenu = Menu.REGISTER_MENU;

    public void run(Scanner scanner) {
        while (true) {
            switch (currentMenu) {
                case REGISTER_MENU:
                    RegisterMenu registerMenu = new RegisterMenu();
                    registerMenu.run(scanner);
                    currentMenu = registerMenu.getMenu();
                    break;
                case LOGIN_MENU:
                    LoginMenu loginMenu = new LoginMenu();
                    loginMenu.run(scanner);
                    currentMenu = loginMenu.getMenu();
                    break;
                case MAIN_MENU:
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.run(scanner);
                    currentMenu = mainMenu.getMenu();
                    break;
                case PROFILE_MENU:
                    ProfileMenu profileMenu = new ProfileMenu();
                    profileMenu.run(scanner);
                    currentMenu = profileMenu.getMenu();
                    break;
                case GAME_MENU:
                    GameMenu gameMenu = new GameMenu();
                    gameMenu.run(scanner);
                    currentMenu = gameMenu.getMenu();
                    break;
            }
        }
    }
}
