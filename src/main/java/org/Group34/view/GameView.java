package org.Group34.view;

import org.Group34.controller.GameController;
import org.Group34.model.Game;
import org.Group34.view.menu.AppMenu;

import java.util.Scanner;

public class GameView extends AppMenu {
    private final GameController controller;

    public GameView(Game game) {
        this.controller = new GameController(game);
    }

    @Override
    public void run(Scanner scanner) {

    }
}
