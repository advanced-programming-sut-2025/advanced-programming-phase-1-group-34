package org.Group34.view.graphic.gameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.model.entities.Player;
import org.Group34.model.gameAssetManagers.OtherAssetManager;
import org.Group34.model.gameAssetManagers.ToolAssetManager;
import org.Group34.model.items.Item;

import java.util.ArrayList;
import java.util.Set;

public class InventoryMenu {
    private final static Sprite chest = new Sprite(OtherAssetManager.getChest());
    private final static Sprite board = new Sprite(OtherAssetManager.getBoard());
    private final static Sprite smallBoard = new Sprite(OtherAssetManager.getSmallBoard());
    private final static Sprite inventorySymbol = new Sprite(ToolAssetManager.getBasicBackpack());
    private final static Sprite skillSymbol = new Sprite(OtherAssetManager.getFaceIcon());
    private final static Sprite socialSymbol = new Sprite(OtherAssetManager.getHearthIcon());
    private final static Sprite mapSymbol = new Sprite(OtherAssetManager.getForestIcon());

    static {
        board.setSize(chest.getWidth(), chest.getHeight());
        smallBoard.setSize((float) (smallBoard.getWidth() * 0.7), (float) (smallBoard.getHeight() * 0.7));
        inventorySymbol.setSize((float) (inventorySymbol.getWidth() * 0.7), (float) (inventorySymbol.getHeight() * 0.7));
        skillSymbol.setSize((float) (skillSymbol.getWidth() * 0.8), (float) (skillSymbol.getHeight() * 0.8));
        socialSymbol.setSize((float) (socialSymbol.getWidth() * 0.4), (float) (socialSymbol.getHeight() * 0.4));
        mapSymbol.setSize((float) (mapSymbol.getWidth() * 0.6), (float) (mapSymbol.getHeight() * 0.6));
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;

        drawBoard(batch, x, y);

        ArrayList<Item> inventory = new ArrayList<>(player.getInventory().keySet());
        int index = 0;

        while (index < inventory.size() && index < 36) {
            Sprite sprite = new Sprite(inventory.get(index).getTexture());
            sprite.setPosition(x + 35 + (48 * index), y + 135 - (index / 12 * 50));
            if (index > 11 && index < 24) {
                sprite.setPosition(x + 35 + (48 * index) - 576, y + 135 - (index / 12 * 50));
            } else if (index > 23) {
                sprite.setPosition(x + 35 + (48 * index) - 1152, y + 135 - (index / 12 * 50));
            }
            sprite.setSize(35, 33);
            sprite.draw(batch);
            index++;
        }

        handleInput(player);
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

        inventorySymbol.setPosition(x + 30 + 5, y + 203 + 2);
        inventorySymbol.draw(batch);

        skillSymbol.setPosition(x + 74 + 9, y + 210 + 5);
        skillSymbol.draw(batch);

        socialSymbol.setPosition(x + 118 + 9, y + 210 + 5);
        socialSymbol.draw(batch);

        mapSymbol.setPosition(x + 162 + 11, y + 210 + 3);
        mapSymbol.draw(batch);
    }

    public static void handleInput(Player player) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 397 && x < 468 && y < 110 && y > 30) {
            player.setCurrentGameMenu("skill");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 470 && x < 541 && y < 110 && y > 30) {
            player.setCurrentGameMenu("social");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 542 && x < 613 && y < 110 && y > 30) {
            player.setCurrentGameMenu("map");
        }
    }
}
