package org.Group34.view.graphic.gameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.controller.GameController;
import org.Group34.model.Result;
import org.Group34.model.entities.Player;
import org.Group34.model.gameAssetManagers.GameMenuAssetManager;
import org.Group34.model.items.Item;
import org.Group34.model.items.tools.Tool;

import java.util.ArrayList;
import java.util.HashMap;

public class SalePlaceMenu {
    private final static Sprite bigBoard = new Sprite(GameMenuAssetManager.getBigBoard());
    private final static Sprite smallBoard = new Sprite(GameMenuAssetManager.getBoard());
    private final static Sprite exitIcon = new Sprite(GameMenuAssetManager.getExitIcon());
    private final static Sprite filterBoard = new Sprite(GameMenuAssetManager.getBoard());
    private final static Sprite buyBoard = new Sprite(GameMenuAssetManager.getBoard());
    private final static Sprite rightIcon = new Sprite(GameMenuAssetManager.getRightIcon());
    private final static Sprite leftIcon = new Sprite(GameMenuAssetManager.getLeftIcon());
    private final static Sprite sellIcon = new Sprite(GameMenuAssetManager.getCheckMark());

    private static int scrollNumber = 0;
    private static int sellAmount = 0;
    private static String error = "";

    static {
        bigBoard.setSize((float) (bigBoard.getWidth() * 0.75), (float) (bigBoard.getHeight() * 0.75));
        smallBoard.setSize((float) (smallBoard.getWidth() * 0.3), (float) (smallBoard.getHeight() * 0.1));
        exitIcon.setSize((float) (exitIcon.getWidth() * 0.2), (float) (exitIcon.getHeight() * 0.2));
        filterBoard.setSize((float) (filterBoard.getWidth() * 0.1), (float) (filterBoard.getHeight() * 0.1));
        buyBoard.setSize(bigBoard.getWidth(), 200);
        leftIcon.setSize(30, 30);
        rightIcon.setSize(30, 30);
        sellIcon.setSize(30, 30);
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera, GameController gameController) {
        float x = camera.position.x - 450;
        float y = camera.position.y - 200;

        drawBoard(batch, x, y);
        fullTheBoard(batch, x, y, player, gameController);

        handleInput(player, gameController);
    }

    private static void drawBoard(SpriteBatch batch, float x, float y) {
        bigBoard.setPosition(x + 130, y - 35);
        bigBoard.draw(batch);

        smallBoard.setPosition(x + 315, y + 390);
        smallBoard.draw(batch);

        exitIcon.setPosition(x + 738, y + 380);
        exitIcon.draw(batch);

        buyBoard.setPosition(x + 130, y - 35);
        buyBoard.draw(batch);

        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Sale Place", x + 405, y + 410);
    }

    private static void handleInput(Player player, GameController gameController) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (x > 1279 && x < 1338 && y < 112 && y > 44) {
                player.setCurrentGameMenu(null);
                scrollNumber = 0;
                sellAmount = 0;
                error = "";
            }
            else if (x > 917 && x < 967 && y > 692 && y < 748) {
                sellAmount--;
                sellAmount = Math.max(0, sellAmount);
            } else if (x > 983 && x < 1033 && y > 692 && y < 748) {
                sellAmount++;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            scrollNumber++;
            sellAmount = 0;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            scrollNumber--;
            sellAmount = 0;
        }
    }

    private static void fullTheBoard(SpriteBatch batch, float x, float y, Player player, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);

        HashMap<Item, Integer> inventory = new HashMap<>(player.getInventory());
        ArrayList<Item> tools = new ArrayList<>();
        for (Item item : inventory.keySet()) {
            if (item instanceof Tool) {
                tools.add(item);
            }
        }
        for (Item tool : tools) {
            if (inventory.containsKey(tool)) {
                inventory.remove(tool);
            }
        }

        font.getData().setScale(0.7f);

        scrollNumber %= inventory.size();
        if (scrollNumber == -1) {
            scrollNumber = inventory.size() - 1;
        }

        font.getData().setScale(1f);

        Item item = new ArrayList<>(inventory.keySet()).get(scrollNumber);

        font.draw(batch, "Item:", x + 200, y + 340);
        font.draw(batch, "Price:", x + 200, y + 300);
        font.draw(batch, "Amount:", x + 200, y + 260);
        font.draw(batch, item.getName(), x + 270, y + 340);
        font.draw(batch, String.valueOf(item.getPrice()), x + 270, y + 300);
        font.draw(batch, String.valueOf(inventory.get(item)), x + 270, y + 260);

        font.setColor(Color.BLACK);
        font.draw(batch, "How much do you want to sell?", x + 200, y + 110);

        filterBoard.setPosition(x + 400, y + 40);
        filterBoard.draw(batch);

        if (inventory.get(item) != -11 && inventory.get(item) < sellAmount) {
            sellAmount--;
        }
        font.draw(batch, String.valueOf(sellAmount), x + 410, y + 60);

        leftIcon.setPosition(x + 520, y + 40);
        leftIcon.draw(batch);

        rightIcon.setPosition(x + 560, y + 40);
        rightIcon.draw(batch);

        sellIcon.setPosition(x + 620, y + 40);
        sellIcon.draw(batch);

        font.setColor(Color.RED);
        font.draw(batch, error, x + 250, y + 10);

        sell(player, gameController, item);
    }

    private static void sell(Player player, GameController gameController, Item product) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1082 && x < 1132 && y > 692 && y < 748) {
            Result result = gameController.sellWithCount(product.getName(), sellAmount, player);
            error = result.message();
        }
    }
}
