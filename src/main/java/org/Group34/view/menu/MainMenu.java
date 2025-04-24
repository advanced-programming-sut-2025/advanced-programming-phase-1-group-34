package main.java.org.Group34.view.menu;

import main.java.org.Group34.controller.menu.MainMenuController;
import main.java.org.Group34.model.enums.Menu;

import java.util.Scanner;

public class MainMenu extends AppMenu{
    private final MainMenuController controller;

    public MainMenu() {
        currentMenu = Menu.MAIN_MENU;
        controller = new MainMenuController();
    }

    @Override
    public void run(Scanner scanner) {
        while (currentMenu == Menu.MAIN_MENU) {
            String command = scanner.nextLine().trim();
        }
    }
}
