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
import org.Group34.model.gameAssetManagers.ToolAssetManager;
import org.Group34.model.interactions.Gift;
import org.Group34.model.interactions.Interaction;
import org.Group34.model.interactions.Message;
import org.Group34.model.items.Item;
import org.Group34.model.items.foods.Fruit;
import org.Group34.model.items.tools.Tool;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.HashMap;

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

    private final static Sprite selectBoard = new Sprite(GameMenuAssetManager.getBoard());
    private final static Sprite leftIcon = new Sprite(GameMenuAssetManager.getLeftIcon());
    private final static Sprite rightIcon = new Sprite(GameMenuAssetManager.getRightIcon());
    private final static Sprite checkIcon = new Sprite(GameMenuAssetManager.getCheckMark());
    private final static Sprite otherBoard = new Sprite(GameMenuAssetManager.getBigBoard());
    private final static Sprite redCircle = new Sprite(GameMenuAssetManager.getRedCircle());
    private static String otherMenu = null;

    private static int talkScroller = 0;
    private static String talkError = "";
    private static StringBuilder message = new StringBuilder();

    private static int giftScroller = 0;
    private static String giftError = "";
    private static int inventoryScroller = 0;
    private static int giftAmount = 0;
    private static int rankIndex = 0;

    private static int hugScroller = 0;
    private static String hugError = "";

    private static int loveScroller = 0;
    private static String loveError = "";
    private static String work = "Giving a flower";

    private static int otherScroller = 0;

    private static String string = "";

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

        otherBoard.setSize(300, 400);
        leftIcon.setSize(20, 20);
        rightIcon.setSize(20, 20);
        checkIcon.setSize(20, 20);
        redCircle.setSize(30, 30);
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
//            string = gameController.giftRate(1, 3, player).message();
        }

        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;
        BitmapFont font = new BitmapFont();

        drawBoard(batch, x, y);

        fullTheBoard(batch, player, x, y, gameController);

        font.draw(batch, string, x, y);
        handleInput(player, gameController);
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

    private static void handleInput(Player player, GameController gameController) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 324 && x < 395 && y < 110 && y > 30) {
            handleExit();
            player.setCurrentGameMenu("inventory");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 397 && x < 468 && y < 110 && y > 30) {
            handleExit();
            player.setCurrentGameMenu("skill");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 542 && x < 613 && y < 110 && y > 30) {
            handleExit();
            player.setCurrentGameMenu("map");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 688 && x < 759 && y < 110 && y > 30) {
            handleExit();
            player.setCurrentGameMenu("setting");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 615 && x < 686 && y < 110 && y > 30) {
            handleExit();
            player.setCurrentGameMenu("npc");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 761 && x < 832 && y < 110 && y > 30) {
            handleExit();
            player.setCurrentGameMenu("animal");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1283 && x < 1342 && y < 150 && y > 82) {
            handleExit();
            player.setCurrentGameMenu(null);
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 962 && x < 1021 && y > 160 && y < 228) {
            otherMenu = null;
        }

        // talk
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (x > 671 && x < 703 && y < 263 && y > 225) {
                talkScroller--;
                if (talkScroller == -1) {
                    talkScroller = player.getInteractions().size() - 1;
                }
            } else if (x > 712 && x < 744 && y < 264 && y > 226) {
                talkScroller++;
                talkScroller %= player.getInteractions().size();
            } else if (x > 738 && x < 770 && y > 394 && y < 429) {
                talk(player, gameController);
            } else if (x > 687 && x < 773 && y > 150 && y < 185) {
                otherMenu = "talkHistory";
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            message.append("a");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            message.append("b");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            message.append("c");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            message.append("d");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            message.append("e");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            message.append("f");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            message.append("g");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            message.append("h");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            message.append("i");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
            message.append("j");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.K)) {
            message.append("k");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            message.append("l");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            message.append("m");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            message.append("n");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            message.append("o");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            message.append("p");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            message.append("q");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            message.append("r");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            message.append("s");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            message.append("t");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.U)) {
            message.append("u");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
            message.append("v");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            message.append("w");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            message.append("x");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Y)) {
            message.append("y");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            message.append("z");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            message.append(" ");
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DEL)) {
            message.deleteCharAt(message.length() - 1);
        }

        // gift
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (x > 1171 && x < 1203 && y < 263 && y > 225) {
                giftScroller--;
                if (giftScroller == -1) {
                    giftScroller = player.getInteractions().size() - 1;
                }
            } else if (x > 1212 && x < 1244 && y < 264 && y > 226) {
                giftScroller++;
                giftScroller %= player.getInteractions().size();
            } else if (x > 1070 && x < 1098 && y > 336 && y < 365) {
                inventoryScroller--;
                giftAmount = 0;
            } else if (x > 1104 && x < 1130 && y > 336 && y < 365) {
                inventoryScroller++;
                giftAmount = 0;
            } else if (x > 1161 && x < 1189 && y > 336 && y < 365) {
                giftAmount--;
            } else if (x > 1195 && x < 1221 && y > 336 && y < 365) {
                giftAmount++;
            } else if (x > 1238 && x < 1270 && y > 394 && y < 429) {
                gift(player, gameController);
            } else if (x > 1187 && x < 1270 && y > 150 && y < 186) {
                otherMenu = "giftHistory";
            } else if (x > 1101 && x < 1184 && y > 150 && y < 186) {
                otherMenu = "gifts";
            } else if (x > 1015 && x < 1098 && y > 150 && y < 186) {
                otherMenu = "giftRank";
            }
        }

        // hug
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (x > 671 && x < 703 && y < 638 && y > 600) {
                hugScroller--;
                if (hugScroller == -1) {
                    hugScroller = player.getInteractions().size() - 1;
                }
            } else if (x > 712 && x < 744 && y < 638 && y > 600) {
                hugScroller++;
                hugScroller %= player.getInteractions().size();
            } else if (x > 754 && x < 786 && y < 638 && y > 600) {
                hug(player, gameController);
            }
        }

        // love
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (x > 1171 && x < 1203 && y < 638 && y > 600) {
                loveScroller--;
                if (loveScroller == -1) {
                    loveScroller = player.getInteractions().size() - 1;
                }
            } else if (x > 1212 && x < 1244 && y < 638 && y > 600) {
                loveScroller++;
                loveScroller %= player.getInteractions().size();
            } else if (x > 1171 && x < 1203 && y < 768 && y > 730) {
                if (work.equals("Giving a flower")) {
                    work = "Marriage proposal";
                } else {
                    work = "Giving a flower";
                }
            } else if (x > 1212 && x < 1244 && y < 768 && y > 730) {
                if (work.equals("Giving a flower")) {
                    work = "Marriage proposal";
                } else {
                    work = "Giving a flower";
                }
            } else if (x > 1254 && x < 1286 && y < 768 && y > 730) {
                if (work.equals("Giving a flower")) {
                    flower(player, gameController);
                } else {
                    marriage(player, gameController);
                }
            }
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
        font.setColor(Color.BROWN);
        font.draw(batch, "Talking:", x + 25, y + 190);
        font.setColor(Color.BLACK);

        font.draw(batch, "Player:", x + 50, y + 170);
        selectBoard.setSize(170, 30);
        selectBoard.setPosition(x + 60, y + 120);
        selectBoard.draw(batch);
        leftIcon.setPosition(x + 240, y + 130);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 265, y + 130);
        rightIcon.draw(batch);

        font.draw(batch, "Write your message:", x + 50, y + 100);
        selectBoard.setSize(200, 30);
        selectBoard.setPosition(x + 60, y + 40);
        selectBoard.draw(batch);
        checkIcon.setPosition(x + 280, y + 40);
        checkIcon.draw(batch);

        font.setColor(Color.RED);
        font.draw(batch, talkError, x + 20, y + 30);
        font.setColor(Color.BLACK);

        HashMap<Player, Interaction> interactions = player.getInteractions();
        Player friend = new ArrayList<>(interactions.keySet()).get(talkScroller);
        Interaction interaction = new ArrayList<>(interactions.values()).get(talkScroller);
        StringBuilder result = new StringBuilder();
        font.draw(batch, friend.getName(), x + 70, y + 143);
        font.getData().setScale(0.7f);
        result.append("(XP : ").append(interaction.getXp()).append(") (Level : ").append(interaction.getLevel()).append(")");
        font.draw(batch, result, x + 135, y + 140);
        font.getData().setScale(1f);

        font.draw(batch, message, x + 70, y + 60);

        selectBoard.setSize(50, 20);
        selectBoard.setPosition(x + 250, y + 170);
        selectBoard.draw(batch);
        font.draw(batch, "History", x + 252, y + 186);

        if (talkNotif(player)) {
            redCircle.setPosition(x + 283, y + 173);
            redCircle.draw(batch);
        }
    }

    private static void giftBoard(SpriteBatch batch, Player player, float x, float y, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BROWN);
        font.draw(batch, "Giving gift:", x + 330, y + 190);
        font.setColor(Color.BLACK);

        font.draw(batch, "Player:", x + 350, y + 170);
        selectBoard.setSize(170, 30);
        selectBoard.setPosition(x + 360, y + 120);
        selectBoard.draw(batch);
        leftIcon.setPosition(x + 540, y + 130);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 565, y + 130);
        rightIcon.draw(batch);

        font.draw(batch, "Choose gift:", x + 350, y + 100);
        selectBoard.setSize(170, 30);
        selectBoard.setPosition(x + 360, y + 40);
        selectBoard.draw(batch);
        selectBoard.setSize(50, 30);
        selectBoard.setPosition(x + 520, y + 40);
        selectBoard.draw(batch);
        checkIcon.setPosition(x + 580, y + 40);
        checkIcon.draw(batch);
        leftIcon.setSize(15, 15);
        rightIcon.setSize(15, 15);
        leftIcon.setPosition(x + 480, y + 75);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 500, y + 75);
        rightIcon.draw(batch);
        leftIcon.setPosition(x + 535, y + 75);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 555, y + 75);
        rightIcon.draw(batch);
        rightIcon.setSize(20, 20);
        leftIcon.setSize(20, 20);

        font.setColor(Color.RED);
        font.draw(batch, giftError, x + 330, y + 30);
        font.setColor(Color.BLACK);

        HashMap<Player, Interaction> interactions = player.getInteractions();
        Player friend = new ArrayList<>(interactions.keySet()).get(giftScroller);
        Interaction interaction = new ArrayList<>(interactions.values()).get(giftScroller);
        StringBuilder result = new StringBuilder();
        font.draw(batch, friend.getName(), x + 370, y + 143);
        font.getData().setScale(0.7f);
        result.append("(XP : ").append(interaction.getXp()).append(") (Level : ").append(interaction.getLevel()).append(")");
        font.draw(batch, result, x + 435, y + 140);
        font.getData().setScale(1f);

        ArrayList<Item> inventory = new ArrayList<>(player.getInventory().keySet());
        ArrayList<Item> delete = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Tool) {
                delete.add(item);
            }
        }

        inventory.removeAll(delete);
        inventoryScroller %= inventory.size();
        if (inventoryScroller == -1) {
            inventoryScroller = inventory.size() - 1;
        }

        Item item = inventory.get(inventoryScroller);
        int count = player.getAmountOfItem(item);
        giftAmount = Math.max(0, giftAmount);
        giftAmount = Math.min(count, giftAmount);

        font.draw(batch, item.getName(), x + 370, y + 60);
        font.draw(batch, String.valueOf(giftAmount), x + 540, y + 60);

        selectBoard.setSize(50, 20);
        selectBoard.setPosition(x + 550, y + 170);
        selectBoard.draw(batch);
        font.draw(batch, "History", x + 552, y + 186);

        selectBoard.setSize(50, 20);
        selectBoard.setPosition(x + 498, y + 170);
        selectBoard.draw(batch);
        font.draw(batch, "Gifts", x + 507, y + 186);

        selectBoard.setSize(50, 20);
        selectBoard.setPosition(x + 446, y + 170);
        selectBoard.draw(batch);
        font.draw(batch, "Rank", x + 455, y + 186);

        if (giftNotif(player)) {
            redCircle.setPosition(x + 532, y + 173);
            redCircle.draw(batch);
        }
    }

    private static void hugBoard(SpriteBatch batch, Player player, float x, float y, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BROWN);
        font.draw(batch, "Hugging:", x + 25, y - 5);
        font.setColor(Color.BLACK);

        font.draw(batch, "Player:", x + 50, y - 30);
        selectBoard.setSize(170, 30);
        selectBoard.setPosition(x + 60, y - 80);
        selectBoard.draw(batch);
        leftIcon.setPosition(x + 240, y - 70);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 265, y - 70);
        rightIcon.draw(batch);

        font.setColor(Color.RED);
        font.draw(batch, hugError, x + 25, y - 165);
        font.setColor(Color.BLACK);

        checkIcon.setPosition(x + 290, y - 70);
        checkIcon.draw(batch);

        HashMap<Player, Interaction> interactions = player.getInteractions();
        Player friend = new ArrayList<>(interactions.keySet()).get(hugScroller);
        Interaction interaction = new ArrayList<>(interactions.values()).get(hugScroller);
        StringBuilder result = new StringBuilder();
        font.draw(batch, friend.getName(), x + 70, y - 57);
        font.getData().setScale(0.7f);
        result.append("(XP : ").append(interaction.getXp()).append(") (Level : ").append(interaction.getLevel()).append(")");
        font.draw(batch, result, x + 135, y - 60);
        font.getData().setScale(1f);
    }

    private static void loveBoard(SpriteBatch batch, Player player, float x, float y, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BROWN);
        font.draw(batch, "Love:", x + 330, y - 5);
        font.setColor(Color.BLACK);

        font.draw(batch, "Player:", x + 350, y - 30);
        selectBoard.setSize(170, 30);
        selectBoard.setPosition(x + 360, y - 80);
        selectBoard.draw(batch);
        leftIcon.setPosition(x + 540, y - 70);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 565, y - 70);
        rightIcon.draw(batch);

        font.setColor(Color.RED);
        font.draw(batch, loveError, x + 325, y - 165);
        font.setColor(Color.BLACK);

        font.draw(batch, "Choose work:", x + 350, y - 100);
        selectBoard.setSize(170, 30);
        selectBoard.setPosition(x + 360, y - 150);
        selectBoard.draw(batch);
        leftIcon.setPosition(x + 540, y - 140);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 565, y - 140);
        rightIcon.draw(batch);

        checkIcon.setPosition(x + 590, y - 140);
        checkIcon.draw(batch);

        selectBoard.setSize(133, 20);
        selectBoard.setPosition(x + 473, y - 30);
        selectBoard.draw(batch);
        font.draw(batch, "Answer to marriage", x + 475, y - 14);

        HashMap<Player, Interaction> interactions = player.getInteractions();
        Player friend = new ArrayList<>(interactions.keySet()).get(loveScroller);
        Interaction interaction = new ArrayList<>(interactions.values()).get(loveScroller);
        StringBuilder result = new StringBuilder();
        font.draw(batch, friend.getName(), x + 370, y - 57);
        font.getData().setScale(0.7f);
        result.append("(XP : ").append(interaction.getXp()).append(") (Level : ").append(interaction.getLevel()).append(")");
        font.draw(batch, result, x + 435, y - 60);
        font.getData().setScale(1f);

        font.draw(batch, work, x + 370, y - 130);
    }

    private static void otherBoard(SpriteBatch batch, Player player, float x, float y, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);

        if (otherMenu != null) {
            otherBoard.setPosition(x + 160, y - 200);
            otherBoard.draw(batch);
            exitIcon.setPosition(x + 415, y + 150);
            exitIcon.draw(batch);

            if (otherMenu.equals("talkHistory")) {
                for (Interaction value : player.getInteractions().values()) {
                    for (Message valueMessage : value.getMessages()) {
                        valueMessage.setInNew(false);
                    }
                }

                font.draw(batch, "Player:", x + 190, y + 170);
                selectBoard.setSize(170, 30);
                selectBoard.setPosition(x + 200, y + 120);
                selectBoard.draw(batch);
                leftIcon.setPosition(x + 380, y + 120);
                leftIcon.draw(batch);
                rightIcon.setPosition(x + 405, y + 120);
                rightIcon.draw(batch);

                HashMap<Player, Interaction> interactions = player.getInteractions();
                Player friend = new ArrayList<>(interactions.keySet()).get(otherScroller);
                Interaction interaction = new ArrayList<>(interactions.values()).get(otherScroller);
                StringBuilder result = new StringBuilder();
                font.draw(batch, friend.getName(), x + 210, y + 143);
                font.getData().setScale(0.7f);
                result.append("(XP : ").append(interaction.getXp()).append(") (Level : ").append(interaction.getLevel()).append(")");
                font.draw(batch, result, x + 275, y + 140);
                font.getData().setScale(1f);

                font.draw(batch, gameController.talkHistory(new ArrayList<>(interactions.keySet()).get(otherScroller).getName(), player).message(), x + 215, y + 110);

                handleOtherBoard(player);
            } else if (otherMenu.equals("gifts")) {
                for (Interaction value : player.getInteractions().values()) {
                    for (Gift gift : value.getGifts()) {
                        gift.setNew(false);
                    }
                }
                font.draw(batch, gameController.giftList(player).message(), x + 210, y + 180);
            } else if (otherMenu.equals("giftHistory")) {
                for (Interaction value : player.getInteractions().values()) {
                    for (Message valueMessage : value.getMessages()) {
                        valueMessage.setInNew(false);
                    }
                }

                font.draw(batch, "Player:", x + 190, y + 170);
                selectBoard.setSize(170, 30);
                selectBoard.setPosition(x + 200, y + 120);
                selectBoard.draw(batch);
                leftIcon.setPosition(x + 380, y + 120);
                leftIcon.draw(batch);
                rightIcon.setPosition(x + 405, y + 120);
                rightIcon.draw(batch);

                HashMap<Player, Interaction> interactions = player.getInteractions();
                Player friend = new ArrayList<>(interactions.keySet()).get(otherScroller);
                Interaction interaction = new ArrayList<>(interactions.values()).get(otherScroller);
                StringBuilder result = new StringBuilder();
                font.draw(batch, friend.getName(), x + 210, y + 143);
                font.getData().setScale(0.7f);
                result.append("(XP : ").append(interaction.getXp()).append(") (Level : ").append(interaction.getLevel()).append(")");
                font.draw(batch, result, x + 275, y + 140);
                font.getData().setScale(1f);

                font.draw(batch, gameController.giftHistory(new ArrayList<>(interactions.keySet()).get(otherScroller).getName(), player).message(), x + 215, y + 110);

                handleOtherBoard(player);
            } else if (otherMenu.equals("giftRank")) {
                font.draw(batch, "Choose Gift:", x + 190, y + 170);
                selectBoard.setSize(170, 30);
                selectBoard.setPosition(x + 200, y + 120);
                selectBoard.draw(batch);
                leftIcon.setPosition(x + 380, y + 120);
                leftIcon.draw(batch);
                rightIcon.setPosition(x + 405, y + 120);
                rightIcon.draw(batch);

                font.draw(batch, String.valueOf(otherScroller), x + 210, y + 143);

                font.draw(batch, "Choose Rank:", x + 190, y + 70);
                selectBoard.setSize(170, 30);
                selectBoard.setPosition(x + 200, y + 20);
                selectBoard.draw(batch);
                leftIcon.setPosition(x + 380, y + 20);
                leftIcon.draw(batch);
                rightIcon.setPosition(x + 405, y + 20);
                rightIcon.draw(batch);

                checkIcon.setPosition(x + 392, y - 5);
                checkIcon.draw(batch);

                handleOtherBoard(player);

                int x2 = Gdx.input.getX();
                int y2 = Gdx.input.getY();

                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    if (x2 > 903 && x2 < 935 && y2 > 430 && y2 < 468) {
                        rankIndex--;
                        if (rankIndex == -1) {
                            rankIndex = 0;
                        }
                    } else if (x2 > 945 && x2 < 977 && y2 < 468 && y2 > 430) {
                        rankIndex++;
                        rankIndex = Math.min(rankIndex, 5);
                    } else if (x2 > 925 && x2 < 957 && y2 > 475 && y2 < 513) {
                        rankIndex = 0;
                        otherMenu = null;
                    }
                }
                font.draw(batch, String.valueOf(rankIndex), x + 210, y + 43);
            }
        }
    }

    private static void talk(Player player, GameController gameController) {
        Result result = gameController.talk(new ArrayList<>(player.getInteractions().keySet()).get(talkScroller).getName(), message.toString(), player);
        talkError = result.message();
        if (result.success()) {
            message.delete(0, message.length());
        }
    }

    private static void gift(Player player, GameController gameController) {
        ArrayList<Item> inventory = new ArrayList<>(player.getInventory().keySet());
        ArrayList<Item> delete = new ArrayList<>();
        for (Item item : inventory) {
            if (item instanceof Tool) {
                delete.add(item);
            }
        }
        inventory.removeAll(delete);

        Result result = gameController.gift(new ArrayList<>(player.getInteractions().keySet()).get(talkScroller).getName(), inventory.get(inventoryScroller).getName(), giftAmount, player);
        giftError = result.message();
        if (result.success()) {
            giftAmount = 0;
        }
    }

    private static void hug(Player player, GameController gameController) {
        hugError = gameController.hug(new ArrayList<>(player.getInteractions().keySet()).get(hugScroller).getName(), player).message();
    }

    private static void flower(Player player, GameController gameController) {
        loveError = gameController.flower(new ArrayList<>(player.getInteractions().keySet()).get(loveScroller).getName(), player).message();
    }

    private static void marriage(Player player, GameController gameController) {
        loveError = gameController.askMarriage(new ArrayList<>(player.getInteractions().keySet()).get(loveScroller).getName(), "", player).message();
    }

    private static void handleOtherBoard(Player player) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (x > 903 && x < 935 && y > 241 && y < 279) {
                otherScroller--;
                if (otherScroller == -1) {
                    otherScroller = player.getInteractions().size() - 1;
                }
            } else if (x > 945 && x < 977 && y < 279 && y > 241) {
                otherScroller++;
                otherScroller %= player.getInteractions().size();
            }
        }
    }

    private static boolean talkNotif(Player player) {
        for (Interaction value : player.getInteractions().values()) {
            for (Message valueMessage : value.getMessages()) {
                if (valueMessage.isInNew()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void handleExit() {
        otherMenu = null;
        talkScroller = 0;
        talkError = "";
        message = new StringBuilder();
        giftScroller = 0;
        giftError = "";
        inventoryScroller = 0;
        giftAmount = 0;
        hugScroller = 0;
        hugError = "";
        loveScroller = 0;
        loveError = "";
        work = "Giving a flower";
        otherScroller = 0;
    }

    private static boolean giftNotif(Player player) {
        for (Interaction value : player.getInteractions().values()) {
            for (Gift gift : value.getGifts()) {
                if (gift.isNew()) {
                    return true;
                }
            }
        }
        return false;
    }
}

