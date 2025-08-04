package org.Group34.view.graphic.gameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.model.entities.Player;
import org.Group34.model.enums.LevelType;
import org.Group34.model.gameAssetManagers.GameMenuAssetManager;
import org.Group34.model.gameAssetManagers.ToolAssetManager;

public class SkillMenu {
    private final static Sprite chest = new Sprite(GameMenuAssetManager.getChest());
    private final static Sprite bigBoard = new Sprite(GameMenuAssetManager.getBigBoard());
    private final static Sprite smallBoard = new Sprite(GameMenuAssetManager.getSmallBoard());
    private final static Sprite inventorySymbol = new Sprite(ToolAssetManager.getBasicBackpack());
    private final static Sprite skillSymbol = new Sprite(GameMenuAssetManager.getFaceIcon());
    private final static Sprite socialSymbol = new Sprite(GameMenuAssetManager.getHearthIcon());
    private final static Sprite mapSymbol = new Sprite(GameMenuAssetManager.getForestIcon());
    private final static Sprite NPCSymbol = new Sprite(GameMenuAssetManager.getSmileIcon());
    private final static Sprite settingSymbol = new Sprite(GameMenuAssetManager.getSettingIcon());
    private final static Sprite exitIcon = new Sprite(GameMenuAssetManager.getExitIcon());
    private final static Sprite animalIcon = new Sprite(GameMenuAssetManager.getAnimalIcon());

    private final static Sprite farmingSkillIcon = new Sprite(GameMenuAssetManager.getFarmingSkillIcon());
    private final static Sprite foragingSkillIcon = new Sprite(GameMenuAssetManager.getForagingSkillIcon());
    private final static Sprite miningSkillIcon = new Sprite(GameMenuAssetManager.getMiningSkillIcon());
    private final static Sprite fishingSkillIcon = new Sprite(GameMenuAssetManager.getFishingSkillIcon());
    private final static Sprite redCircle = new Sprite(GameMenuAssetManager.getRedCircle());
    private final static Sprite grayCircle = new Sprite(GameMenuAssetManager.getGrayCircle());
    private final static Sprite board = new Sprite(GameMenuAssetManager.getBigBoard());

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

        farmingSkillIcon.setSize(40, 40);
        foragingSkillIcon.setSize(40, 40);
        miningSkillIcon.setSize(40, 40);
        fishingSkillIcon.setSize(40, 40);
        grayCircle.setSize(70, 70);
        redCircle.setSize(70, 70);
        board.setSize(150, 100);
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;
        drawBoard(batch, x, y);

        fullBoard(batch, player, x, y);

        handleInput(player);

        handleHover(batch, x, y);
    }

    private static void drawBoard(SpriteBatch batch, float x, float y) {
        bigBoard.setPosition(x, y - 200);
        bigBoard.draw(batch);

        smallBoard.setPosition(x + 30, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 74, y + 203);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 118, y + 210);
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

        skillSymbol.setPosition(x + 74 + 9, y + 203 + 5);
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

        animalIcon.setPosition(x + 294 + 10, y + 210 + 5);
        animalIcon.draw(batch);
    }

    private static void handleInput(Player player) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 324 && x < 395 && y < 110 && y > 30) {
            player.setCurrentGameMenu("inventory");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 470 && x < 541 && y < 110 && y > 30) {
            player.setCurrentGameMenu("social");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 542 && x < 613 && y < 110 && y > 30) {
            player.setCurrentGameMenu("map");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 688 && x < 759 && y < 110 && y > 30) {
            player.setCurrentGameMenu("setting");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 615 && x < 686 && y < 110 && y > 30) {
            player.setCurrentGameMenu("npc");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 761 && x < 832 && y < 110 && y > 30) {
            player.setCurrentGameMenu("animal");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1283 && x < 1342 && y < 150 && y > 82) {
            player.setCurrentGameMenu(null);
        }
    }

    private static void handleHover(SpriteBatch batch, float x2, float y2) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (x > 353 && x < 422 && y > 186 && y < 261) {
            handleInfoBoard(batch, "farming", x2, y2);
        } else if (x > 353 && x < 422 && y > 279 && y < 355) {
            handleInfoBoard(batch, "foraging", x2, y2);
        } else if (x > 353 && x < 422 && y > 373 && y < 448) {
            handleInfoBoard(batch, "mining", x2, y2);
        } else if (x > 353 && x < 422 && y > 467 && y < 542) {
            handleInfoBoard(batch, "fishing", x2, y2);
        }
    }

    private static void fullBoard(SpriteBatch batch, Player player, float x, float y) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);

        font.draw(batch, "Farming", x + 110, y + 155);
        font.draw(batch, "Foraging", x + 110, y + 105);
        font.draw(batch, "Mining", x + 110, y + 55);
        font.draw(batch, "Fishing", x + 110, y + 5);

        farmingSkillIcon.setPosition(x + 50, y + 130);
        farmingSkillIcon.draw(batch);

        foragingSkillIcon.setPosition(x + 50, y + 80);
        foragingSkillIcon.draw(batch);

        miningSkillIcon.setPosition(x + 50, y + 30);
        miningSkillIcon.draw(batch);

        fishingSkillIcon.setPosition(x + 50, y - 20);
        fishingSkillIcon.draw(batch);

        grayCircle.setPosition(x + 200, y + 115);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 250, y + 115);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 300, y + 115);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 350, y + 115);
        grayCircle.draw(batch);

        grayCircle.setPosition(x + 200, y + 65);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 250, y + 65);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 300, y + 65);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 350, y + 65);
        grayCircle.draw(batch);

        grayCircle.setPosition(x + 200, y + 15);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 250, y + 15);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 300, y + 15);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 350, y + 15);
        grayCircle.draw(batch);

        grayCircle.setPosition(x + 200, y - 35);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 250, y - 35);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 300, y - 35);
        grayCircle.draw(batch);
        grayCircle.setPosition(x + 350, y - 35);
        grayCircle.draw(batch);

        fullTheSkills(batch, player, x, y);
    }

    private static void fullTheSkills(SpriteBatch batch, Player player, float x, float y) {
        for (int i = 0; i < player.getLevel(LevelType.FARMING_LEVEL); i++) {
            redCircle.setPosition(x + 200 + (50 * i), y + 115);
            redCircle.draw(batch);
        }
        for (int i = 0; i < player.getLevel(LevelType.FORAGING_LEVEL); i++) {
            redCircle.setPosition(x + 200 + (50 * i), y + 65);
            redCircle.draw(batch);
        }
        for (int i = 0; i < player.getLevel(LevelType.MINING_LEVEL); i++) {
            redCircle.setPosition(x + 200 + (50 * i), y + 15);
            redCircle.draw(batch);
        }
        for (int i = 0; i < player.getLevel(LevelType.FISHING_LEVEL); i++) {
            redCircle.setPosition(x + 200 + (50 * i), y - 35);
            redCircle.draw(batch);
        }
    }

    private static void handleInfoBoard(SpriteBatch batch, String skill, float x, float y) {
        board.setPosition(x + 200, y + 30);
        board.draw(batch);

        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, skill, x + 245, y + 120);

        font.getData().setScale(0.8f);
        if (skill.equals("farming")) {
            font.draw(batch, "You can use a hoe\nand watering can with\nless energy.", x + 210, y + 100);
        } else if (skill.equals("foraging")) {
            font.draw(batch, "You can use a axe with\nless energy.", x + 210, y + 100);
        } else if (skill.equals("mining")) {
            font.draw(batch, "You can use a pickaxe\nwith less energy.", x + 210, y + 100);
        } else if (skill.equals("fishing")) {
            font.draw(batch, "You can fish with\nless energy expenditure.", x + 210, y + 100);
        }
    }
}

