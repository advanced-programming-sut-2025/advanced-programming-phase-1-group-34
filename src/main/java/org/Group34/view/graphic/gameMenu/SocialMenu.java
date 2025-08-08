package org.Group34.view.graphic.gameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.controller.GameController;
import org.Group34.model.entities.Player;
import org.Group34.model.gameAssetManagers.GameMenuAssetManager;
import org.Group34.model.gameAssetManagers.ToolAssetManager;

public class SocialMenu {
    private final static Sprite chest = new Sprite(GameMenuAssetManager.getChest());
    private final static Sprite bigBoard = new Sprite(GameMenuAssetManager.getLinedBoard());
    private final static Sprite smallBoard = new Sprite(GameMenuAssetManager.getSmallBoard());
    private final static Sprite inventorySymbol = new Sprite(ToolAssetManager.getBasicBackpack());
    private final static Sprite skillSymbol = new Sprite(GameMenuAssetManager.getFaceIcon());
    private final static Sprite socialSymbol = new Sprite(GameMenuAssetManager.getHearthIcon());
    private final static Sprite mapSymbol = new Sprite(GameMenuAssetManager.getForestIcon());
    private final static Sprite NPCSymbol = new Sprite(GameMenuAssetManager.getSmileIcon());
    private final static Sprite settingSymbol = new Sprite(GameMenuAssetManager.getSettingIcon());
    private final static Sprite exitIcon = new Sprite(GameMenuAssetManager.getExitIcon());
    private final static Sprite animalIcon = new Sprite(GameMenuAssetManager.getAnimalIcon());

    static {
        bigBoard.setSize(chest.getWidth(), 413);
        smallBoard.setSize((float) (smallBoard.getWidth() * 0.7), (float) (smallBoard.getHeight() * 0.7));
        inventorySymbol.setSize((float) (inventorySymbol.getWidth() * 0.7), (float) (inventorySymbol.getHeight() * 0.7));
        skillSymbol.setSize((float) (skillSymbol.getWidth() * 0.8), (float) (skillSymbol.getHeight() * 0.8));
        socialSymbol.setSize((float) (socialSymbol.getWidth() * 0.4), (float) (socialSymbol.getHeight() * 0.4));
        mapSymbol.setSize((float) (mapSymbol.getWidth() * 0.6), (float) (mapSymbol.getHeight() * 0.6));
        NPCSymbol.setSize((float) (NPCSymbol.getWidth() * 0.8), (float) (NPCSymbol.getHeight() * 0.8));
        settingSymbol.setSize((float) (settingSymbol.getWidth() * 0.05), (float) (settingSymbol.getHeight() * 0.05));
        exitIcon.setSize((float) (exitIcon.getWidth() * 0.2), (float) (exitIcon.getHeight() * 0.2));
        animalIcon.setSize((float) (animalIcon.getWidth() * 0.5), (float) (animalIcon.getHeight() * 0.5));
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera, GameController gameController) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;
        drawBoard(batch, x, y);

        fullTheBoard(batch, player, x, y, gameController);

        handleInput(player);
    }

    private static void drawBoard(SpriteBatch batch, float x, float y) {
        bigBoard.setPosition(x, y - 200);
        bigBoard.draw(batch);

        smallBoard.setPosition(x + 30, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 74, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 118, y + 203);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 162, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 206, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 250, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 294, y + 210);
        smallBoard.draw(batch);

        inventorySymbol.setPosition(x + 30 + 5, y + 210 + 2);
        inventorySymbol.draw(batch);

        skillSymbol.setPosition(x + 74 + 9, y + 210 + 5);
        skillSymbol.draw(batch);

        socialSymbol.setPosition(x + 118 + 9, y + 203 + 5);
        socialSymbol.draw(batch);

        mapSymbol.setPosition(x + 162 + 11, y + 210 + 3);
        mapSymbol.draw(batch);

        NPCSymbol.setPosition(x + 206 + 10, y + 210 + 5);
        NPCSymbol.draw(batch);

        settingSymbol.setPosition(x + 250 + 13, y + 210 + 8);
        settingSymbol.draw(batch);

        exitIcon.setPosition(x + 608, y + 190);
        exitIcon.draw(batch);

        animalIcon.setPosition(x + 294 + 10, y + 210 + 5);
        animalIcon.draw(batch);
    }

    private static void handleInput(Player player) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 324 && x < 395 && y < 110 && y > 30) {
            player.setCurrentGameMenu("inventory");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 397 && x < 468 && y < 110 && y > 30) {
            player.setCurrentGameMenu("skill");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 542 && x < 613 && y < 110 && y > 30) {
            player.setCurrentGameMenu("map");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 688 && x < 759 && y < 110 && y > 30) {
            player.setCurrentGameMenu("setting");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 615 && x < 686 && y < 110 && y > 30) {
            player.setCurrentGameMenu("npc");
        }  else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 761 && x < 832 && y < 110 && y > 30) {
            player.setCurrentGameMenu("animal");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1283 && x < 1342 && y < 150 && y > 82) {
            player.setCurrentGameMenu(null);
        }
    }

    private static void fullTheBoard(SpriteBatch batch, Player player, float x, float y, GameController gameController) {
        talkBoard(batch, player, x, y, gameController);
        giftBoard(batch, player, x, y, gameController);
        hugBoard(batch, player, x, y, gameController);
        loveBoard(batch, player, x, y, gameController);
        otherBoard(batch, player, x, y, gameController);


    }

    private static void talkBoard(SpriteBatch batch, Player player, float x, float y, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Talking:", x + 25, y + 190);

        font.draw(batch, gameController.showFriendships(player).message(), x + 25, y + 170);
    }

    private static void giftBoard(SpriteBatch batch, Player player, float x, float y, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Giving gift:", x + 330, y + 190);
    }

    private static void hugBoard(SpriteBatch batch, Player player, float x, float y, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Hugging:", x + 25, y - 5);
    }

    private static void loveBoard(SpriteBatch batch, Player player, float x, float y, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Love:", x + 330, y - 5);
    }

    private static void otherBoard(SpriteBatch batch, Player player, float x, float y, GameController gameController) {

    }
}

