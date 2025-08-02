package org.Group34.view.graphic.gameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.model.entities.Player;
import org.Group34.model.gameAssetManagers.OtherAssetManager;
import org.Group34.model.gameAssetManagers.ToolAssetManager;
import org.Group34.model.items.Item;
import org.Group34.model.items.tools.TrashCan;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InventoryMenu {
    private final static Sprite chest = new Sprite(OtherAssetManager.getChest());
    private final static Sprite board = new Sprite(OtherAssetManager.getBoard());
    private final static Sprite smallBoard = new Sprite(OtherAssetManager.getSmallBoard());
    private final static Sprite inventorySymbol = new Sprite(ToolAssetManager.getBasicBackpack());
    private final static Sprite skillSymbol = new Sprite(OtherAssetManager.getFaceIcon());
    private final static Sprite socialSymbol = new Sprite(OtherAssetManager.getHearthIcon());
    private final static Sprite mapSymbol = new Sprite(OtherAssetManager.getForestIcon());
    private final static Sprite NPCSymbol = new Sprite(OtherAssetManager.getSmileIcon());
    private final static Sprite settingSymbol = new Sprite(OtherAssetManager.getSettingIcon());
    private final static Sprite exitIcon = new Sprite(OtherAssetManager.getExitIcon());

    private final static Sprite greenRect = new Sprite(OtherAssetManager.getGreenRect());
    private static int scrollNumber = 0;

    static {
        board.setSize(chest.getWidth(), chest.getHeight());
        smallBoard.setSize((float) (smallBoard.getWidth() * 0.7), (float) (smallBoard.getHeight() * 0.7));
        inventorySymbol.setSize((float) (inventorySymbol.getWidth() * 0.7), (float) (inventorySymbol.getHeight() * 0.7));
        skillSymbol.setSize((float) (skillSymbol.getWidth() * 0.8), (float) (skillSymbol.getHeight() * 0.8));
        socialSymbol.setSize((float) (socialSymbol.getWidth() * 0.4), (float) (socialSymbol.getHeight() * 0.4));
        mapSymbol.setSize((float) (mapSymbol.getWidth() * 0.6), (float) (mapSymbol.getHeight() * 0.6));
        NPCSymbol.setSize((float) (NPCSymbol.getWidth() * 0.8), (float) (NPCSymbol.getHeight() * 0.8));
        settingSymbol.setSize((float) (settingSymbol.getWidth() * 0.05), (float) (settingSymbol.getHeight() * 0.05));
        exitIcon.setSize((float) (exitIcon.getWidth() * 0.2), (float) (exitIcon.getHeight() * 0.2));
        greenRect.setSize(45, 45);
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;

        drawBoard(batch, x, y);

        ArrayList<Item> inventory = new ArrayList<>(player.getInventory().keySet());
        int index = 0;

        BitmapFont font = new BitmapFont();
        while (index + (12 * scrollNumber) < inventory.size() && index < 36) {
            Item item = inventory.get(index + (12 * scrollNumber));
            Sprite sprite = new Sprite(item.getTexture());
            sprite.setPosition(x + 37 + (48 * index), y + 137 - (index / 12 * 50));
            font.setColor(Color.BLACK);

            if (index > 11 && index < 24) {
                sprite.setPosition(x + 37 + (48 * index) - 576, y + 137 - (index / 12 * 50));
            } else if (index > 23) {
                sprite.setPosition(x + 37 + (48 * index) - 1152, y + 137 - (index / 12 * 50));
            }

            if (item.equals(player.getCurrentItem()) || item.equals(player.getCurrentTool())) {
                greenRect.setPosition(sprite.getX() - 6, sprite.getY() - 8);
                greenRect.draw(batch);
            }

            font.draw(batch, String.valueOf(player.getInventory().get(item)), sprite.getX() - 5, sprite.getY() + 3);
            sprite.setSize(35, 33);
            sprite.draw(batch);
            index++;
        }

        fullBoard(player, inventory, x, y, batch);

        handleInput(player, inventory);
    }

    private static void drawBoard(SpriteBatch batch, float x, float y) {
        chest.setPosition(x, y);
        chest.draw(batch);

        board.setSize(chest.getWidth(), chest.getHeight());
        board.setPosition(x, y - 200);
        board.draw(batch);

        smallBoard.setPosition(x + 30, y + 203);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 74, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 118, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 162, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 206, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 250, y + 210);
        smallBoard.draw(batch);

        inventorySymbol.setPosition(x + 30 + 5, y + 203 + 2);
        inventorySymbol.draw(batch);

        skillSymbol.setPosition(x + 74 + 9, y + 210 + 5);
        skillSymbol.draw(batch);

        socialSymbol.setPosition(x + 118 + 9, y + 210 + 5);
        socialSymbol.draw(batch);

        mapSymbol.setPosition(x + 162 + 11, y + 210 + 3);
        mapSymbol.draw(batch);

        NPCSymbol.setPosition(x + 206 + 10, y + 210 + 5);
        NPCSymbol.draw(batch);

        settingSymbol.setPosition(x + 250 + 13, y + 210 + 8);
        settingSymbol.draw(batch);

        exitIcon.setPosition(x + 608, y + 190);
        exitIcon.draw(batch);
    }

    private static void handleInput(Player player, ArrayList<Item> inventory) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 397 && x < 468 && y < 110 && y > 30) {
            player.setCurrentGameMenu("skill");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 470 && x < 541 && y < 110 && y > 30) {
            player.setCurrentGameMenu("social");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 542 && x < 613 && y < 110 && y > 30) {
            player.setCurrentGameMenu("map");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 615 && x < 686 && y < 110 && y > 30) {
            player.setCurrentGameMenu("npc");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 688 && x < 759 && y < 110 && y > 30) {
            player.setCurrentGameMenu("setting");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1283 && x < 1342 && y < 150 && y > 82) {
            player.setCurrentGameMenu(null);
            scrollNumber = 0;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            scrollNumber = Math.max(0, scrollNumber - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            scrollNumber = Math.min(player.getInventory().size() / 12, scrollNumber + 1);
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1005 && x < 1170 && y > 588 && y < 780) {
            Item item = player.getCurrentItem();

            if (item != null) {
                player.removeFromInventory(player.getCurrentItem(), 1);
                if (player.getAmountOfItem(item) == 0) {
                    player.setCurrentItem(null);
                }
            }
        }

        handleSelectInventory(player, inventory, x, y);
    }

    private static void handleSelectInventory(Player player, ArrayList<Item> inventory, int x, int y) {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 320 && x < 400 && y > 177 && y < 264) {
            player.setCurrentGameMenu(null);
        }
    }

    private static void fullBoard(Player player, ArrayList<Item> inventory, float x, float y, SpriteBatch batch) {
        Item target = null;
        for (Item item : inventory) {
            if (item instanceof TrashCan) {
                target = item;
            }
        }

        Sprite trashcan = new Sprite(target.getTexture());
        trashcan.setPosition(x + 440, y - 145);
        trashcan.setSize(100, 100);
        trashcan.draw(batch);

        Sprite playerSprite = new Sprite(player.getTexture());
        playerSprite.setPosition(x + 80, y - 170);
        playerSprite.setSize(100, 150);
        playerSprite.draw(batch);

        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Negin", x + 250, y - 50);
        font.draw(batch, "Energy: 2000", x + 250, y - 80);
    }
}
