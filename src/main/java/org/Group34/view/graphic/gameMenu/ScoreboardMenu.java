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
import org.Group34.model.interactions.Interaction;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreboardMenu {
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
    private final static Sprite craftingIcon = new Sprite(GameMenuAssetManager.getCraftingIcon());
    private final static Sprite cookingIcon = new Sprite(GameMenuAssetManager.getCookingIcon());
    private final static Sprite fridgeIcon = new Sprite(GameMenuAssetManager.getFridgeIcon());
    private final static Sprite reactionIcon = new Sprite(GameMenuAssetManager.getReactionIcon());
    private final static Sprite scoreboardIcon = new Sprite(GameMenuAssetManager.getScoreboardIcon());
    private final static Sprite selectBoard = new Sprite(GameMenuAssetManager.getBoard());

    private final static Sprite rightIcon = new Sprite(GameMenuAssetManager.getRightIcon());
    private final static Sprite line = new Sprite(GameMenuAssetManager.getLine());
    private static ArrayList<String> sort = new ArrayList<>();
    private static int scroller = 0;

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
        craftingIcon.setSize((float) (craftingIcon.getWidth() * 0.5), (float) (craftingIcon.getHeight() * 0.5));
        cookingIcon.setSize((float) (cookingIcon.getWidth() * 0.5), (float) (cookingIcon.getHeight() * 0.5));
        fridgeIcon.setSize((float) (fridgeIcon.getWidth() * 0.3), (float) (fridgeIcon.getHeight() * 0.3));
        reactionIcon.setSize((float) (reactionIcon.getWidth() * 0.3), (float) (reactionIcon.getHeight() * 0.3));
        scoreboardIcon.setSize((float) (scoreboardIcon.getWidth() * 0.5), (float) (scoreboardIcon.getHeight() * 0.5));

        rightIcon.setSize(20, 20);
        sort.add("Money");
        sort.add("Quest");
        sort.add("Skill");
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera, GameController gameController) {
        if (player.getInteractions().isEmpty()) {
            Player player1 = new Player(new int[]{1, 1});
            Player player2 = new Player(new int[]{2, 2});
            Player player3 = new Player(new int[]{3, 3});
            player.setName("Negin");
            player1.setName("Fateme");
            player2.setName("Mmd");
            player3.setName("Amin");
            player.getInteractions().put(player1, new Interaction());
            player.getInteractions().put(player2, new Interaction());
            player.getInteractions().put(player3, new Interaction());
            player1.getInteractions().put(player, new Interaction());
            player2.getInteractions().put(player, new Interaction());
            player3.getInteractions().put(player, new Interaction());

            gameController.talk(player.getName(), "Hello", player1);
            player2.getInteractionByPlayer(player).setLevel(4);
            gameController.gift(player.getName(), "Apple Sapling", 5, player2);
        }

        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;
        drawBoard(batch, x, y);

        fullBoard(batch, player, x, y);

        handleInput(player);
    }

    private static void drawBoard(SpriteBatch batch, float x, float y) {
        bigBoard.setPosition(x, y - 200);
        bigBoard.draw(batch);

        smallBoard.setPosition(x + 30, y + 210);
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

        smallBoard.setPosition(x + 294, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 338, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 382, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 426, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 470, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 514, y + 203);
        smallBoard.draw(batch);

        inventorySymbol.setPosition(x + 30 + 5, y + 210 + 2);
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

        animalIcon.setPosition(x + 294 + 10, y + 210 + 5);
        animalIcon.draw(batch);

        craftingIcon.setPosition(x + 338 + 10, y + 210 + 5);
        craftingIcon.draw(batch);

        cookingIcon.setPosition(x + 382 + 10, y + 210 + 5);
        cookingIcon.draw(batch);

        fridgeIcon.setPosition(x + 426 + 15, y + 210 + 3);
        fridgeIcon.draw(batch);

        reactionIcon.setPosition(x + 470 + 13, y + 210 + 7);
        reactionIcon.draw(batch);

        scoreboardIcon.setPosition(x + 514 + 10, y + 203 + 5);
        scoreboardIcon.draw(batch);
    }

    private static void handleInput(Player player) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 324 && x < 395 && y < 110 && y > 30) {
            player.setCurrentGameMenu("inventory");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 397 && x < 468 && y < 110 && y > 30) {
            player.setCurrentGameMenu("skill");
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
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 834 && x < 905 && y < 110 && y > 30) {
            player.setCurrentGameMenu("crafting");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 907 && x < 978 && y < 110 && y > 30) {
            player.setCurrentGameMenu("cooking");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 980 && x < 1051 && y < 110 && y > 30) {
            player.setCurrentGameMenu("fridge");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1053 && x < 1124 && y < 110 && y > 30) {
            player.setCurrentGameMenu("reaction");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1126 && x < 1197 && y < 110 && y > 30) {
            player.setCurrentGameMenu("scoreboard");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1283 && x < 1342 && y < 150 && y > 82) {
            player.setCurrentGameMenu(null);
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (x > 937 && x < 972 && y < 280 && y > 242) {
                scroller++;
                scroller %= sort.size();
            }
        }
    }

    private static void fullBoard(SpriteBatch batch, Player player, float x, float y) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Sorted by:", x + 170, y + 170);
        selectBoard.setSize(120, 25);
        selectBoard.setPosition(x + 270, y + 120);
        selectBoard.draw(batch);
        rightIcon.setPosition(x + 400, y + 120);
        rightIcon.draw(batch);
        font.draw(batch, sort.get(scroller), x + 280, y + 138);
        line.setPosition(x + 15, y + 80);
        line.setSize(600, 10);
        line.draw(batch);

        font.draw(batch, "Rank" , x + 130, y + 50);
        font.draw(batch, "Player", x + 280, y + 50);
        font.draw(batch, "Score", x + 430, y + 50);

        ArrayList<Player> players = new ArrayList<>(player.getInteractions().keySet());
        players.add(player);

        if (sort.get(scroller).equals("Money")) {
            players.sort(Comparator.comparing(Player::getMoney).reversed());
            for (int i = 0; i < players.size(); i++) {
                font.draw(batch, String.valueOf(i + 1), x + 140, y + 20 - i * 30);
                font.draw(batch, players.get(i).getName(), x + 280, y + 20 - i * 30);
                font.draw(batch, String.valueOf(players.get(i).getMoney()), x + 440, y + 20 - i * 30);
            }
        }
        else if (sort.get(scroller).equals("Skill")) {
            players.sort(Comparator.comparing(Player::getSumOfSkills).reversed());
            for (int i = 0; i < players.size(); i++) {
                font.draw(batch, String.valueOf(i + 1), x + 140, y + 20 - i * 30);
                font.draw(batch, players.get(i).getName(), x + 280, y + 20 - i * 30);
                if (players.get(i).equals(player)) {
                    font.draw(batch, String.valueOf(players.get(i).getSumOfSkills()), x + 440, y + 20 - i * 30);
                } else {
                    font.draw(batch, String.valueOf(players.get(i).getSkill()), x + 440, y + 20 - i * 30);
                }
            }
        }
        else {
            for (int i = 0; i < players.size(); i++) {
                font.draw(batch, String.valueOf(i + 1), x + 140, y + 20 - i * 30);
                font.draw(batch, players.get(i).getName(), x + 280, y + 20 - i * 30);
                font.draw(batch, String.valueOf(0), x + 440, y + 20 - i * 30);
            }
        }
    }
}
