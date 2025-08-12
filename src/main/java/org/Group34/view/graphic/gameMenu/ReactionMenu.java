package org.Group34.view.graphic.gameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.controller.GameController;
import org.Group34.model.NetworkObjects.NetworkReaction;
import org.Group34.model.entities.Player;
import org.Group34.model.gameAssetManagers.GameMenuAssetManager;
import org.Group34.model.gameAssetManagers.ReactionAssetManager;
import org.Group34.model.gameAssetManagers.ToolAssetManager;

import java.util.ArrayList;

public class ReactionMenu {
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

    private final static Sprite rightIcon = new Sprite(GameMenuAssetManager.getRightIcon());
    private final static Sprite leftIcon = new Sprite(GameMenuAssetManager.getLeftIcon());
    private final static Sprite chekIcon = new Sprite(GameMenuAssetManager.getCheckMark());
    private final static Sprite line = new Sprite(GameMenuAssetManager.getLine());

    private static int emojiScroller = 0;
    private static int textScroller = 0;

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

        chekIcon.setSize(20, 20);
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera, GameController gameController) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;
        drawBoard(batch, x, y);

        fullBoard(batch, player, x, y);
        handleInput(player, gameController);
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

        smallBoard.setPosition(x + 470, y + 203);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 514, y + 210);
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

        reactionIcon.setPosition(x + 470 + 13, y + 203 + 7);
        reactionIcon.draw(batch);

        scoreboardIcon.setPosition(x + 514 + 10, y + 210 + 5);
        scoreboardIcon.draw(batch);
    }

    private static void handleInput(Player player, GameController gameController) {
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
            if (x > 455 && x < 480 && y < 339 && y > 307) {
                if (player.getGiftingTexture() == ReactionAssetManager.gifting1) {
                    player.setGiftingTexture(ReactionAssetManager.gifting2);
                } else {
                    player.setGiftingTexture(ReactionAssetManager.gifting1);
                }
            } else if (x > 655 && x < 680 && y < 339 && y > 307) {
                if (player.getHuggingTexture() == ReactionAssetManager.hugging1) {
                    player.setHuggingTexture(ReactionAssetManager.hugging2);
                } else {
                    player.setHuggingTexture(ReactionAssetManager.hugging1);
                }
            } else if (x > 879 && x < 904 && y < 339 && y > 307) {
                if (player.getFloweringTexture() == ReactionAssetManager.flowering1) {
                    player.setFloweringTexture(ReactionAssetManager.flowering2);
                } else {
                    player.setFloweringTexture(ReactionAssetManager.flowering1);
                }
            } else if (x > 1146 && x < 1171 && y < 339 && y > 307) {
                if (player.getMarriageTexture() == ReactionAssetManager.marriage1) {
                    player.setMarriageTexture(ReactionAssetManager.marriage2);
                } else {
                    player.setMarriageTexture(ReactionAssetManager.marriage1);
                }
            } else if (x > 571 && x < 605 && y < 732 && y > 694) {
                emojiScroller--;
                if (emojiScroller == -1) {
                    emojiScroller = getEmojis().size() - 1;
                }
            } else if (x > 611 && x < 645 && y < 732 && y > 694) {
                emojiScroller++;
                emojiScroller %= getEmojis().size();
            } else if (x > 670 && x < 704 && y < 732 && y > 694) {
                player.setReaction(getEmojis().get(emojiScroller));
                player.setCurrentGameMenu(null);
                NetworkReaction reaction = new NetworkReaction(player.getName(), getNameOfReaction(getEmojis().get(emojiScroller)));
                gameController.getClient().sendObject(reaction);
            } else if (x > 1038 && x < 1072 && y < 732 && y > 694) {
                textScroller--;
                if (textScroller == -1) {
                    textScroller = getTexts().size() - 1;
                }
            } else if (x > 1078 && x < 1112 && y < 732 && y > 694) {
                textScroller++;
                textScroller %= getTexts().size();
            } else if (x > 1137 && x < 1171 && y < 732 && y > 694) {
                player.setReaction(getTexts().get(textScroller));
                player.setCurrentGameMenu(null);
                NetworkReaction reaction = new NetworkReaction(player.getName(), getNameOfReaction(getTexts().get(textScroller)));
                gameController.getClient().sendObject(reaction);
            }
        }
    }

    private static void fullBoard(SpriteBatch batch, Player player, float x, float y) {
        fullDefaultReactions(batch, player, x, y);
        fullEmojiBoard(batch, player, x, y);
    }

    private static void fullDefaultReactions(SpriteBatch batch, Player player, float x, float y) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        rightIcon.setSize(15, 15);

        font.draw(batch, "Giving gift:", x + 50, y + 170);
        Sprite sprite1 = new Sprite(player.getGiftingTexture());
        sprite1.setPosition(x + 65, y + 100);
        sprite1.setSize(40, 40);
        sprite1.draw(batch);
        rightIcon.setPosition(x + 110, y + 90);
        rightIcon.draw(batch);

        font.draw(batch, "Hugging:", x + 180, y + 170);
        Sprite sprite2 = new Sprite(player.getHuggingTexture());
        sprite2.setPosition(x + 185, y + 100);
        sprite2.setSize(40, 40);
        sprite2.draw(batch);
        rightIcon.setPosition(x + 230, y + 90);
        rightIcon.draw(batch);

        font.draw(batch, "Giving a flower:", x + 290, y + 170);
        Sprite sprite3 = new Sprite(player.getFloweringTexture());
        sprite3.setPosition(x + 320, y + 100);
        sprite3.setSize(40, 40);
        sprite3.draw(batch);
        rightIcon.setPosition(x + 365, y + 90);
        rightIcon.draw(batch);

        font.draw(batch, "Marriage proposal:", x + 440, y + 170);
        Sprite sprite4 = new Sprite(player.getMarriageTexture());
        sprite4.setPosition(x + 480, y + 100);
        sprite4.setSize(40, 40);
        sprite4.draw(batch);
        rightIcon.setPosition(x + 525, y + 90);
        rightIcon.draw(batch);

        line.setPosition(x + 15, y + 40);
        line.setSize(600, 10);
        line.draw(batch);
    }

    private static void fullEmojiBoard(SpriteBatch batch, Player player, float x, float y) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        leftIcon.setSize(20, 20);
        rightIcon.setSize(20, 20);

        font.draw(batch, "Choose your desired emoji:", x + 70, y);
        Sprite sprite1 = new Sprite(getEmojis().get(emojiScroller));
        sprite1.setSize(50, 50);
        sprite1.setPosition(x + 120, y - 90);
        sprite1.draw(batch);
        leftIcon.setPosition(x + 180, y - 120);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 205, y - 120);
        rightIcon.draw(batch);
        chekIcon.setPosition(x + 240, y - 120);
        chekIcon.draw(batch);

        font.draw(batch, "Choose your desired text:", x + 350, y);
        Sprite sprite2 = new Sprite(getTexts().get(textScroller));
        sprite2.setSize(100, 30);
        sprite2.setPosition(x + 400, y - 70);
        sprite2.draw(batch);
        leftIcon.setPosition(x + 460, y - 120);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 485, y - 120);
        rightIcon.draw(batch);
        chekIcon.setPosition(x + 520, y - 120);
        chekIcon.draw(batch);
    }

    public static ArrayList<Texture> getEmojis() {
        ArrayList<Texture> emojis = new ArrayList<>();

        emojis.add(ReactionAssetManager.emoji0);
        emojis.add(ReactionAssetManager.emoji1);
        emojis.add(ReactionAssetManager.emoji2);
        emojis.add(ReactionAssetManager.emoji3);
        emojis.add(ReactionAssetManager.emoji4);
        emojis.add(ReactionAssetManager.emoji5);
        emojis.add(ReactionAssetManager.emoji6);
        emojis.add(ReactionAssetManager.emoji7);
        emojis.add(ReactionAssetManager.emoji8);
        emojis.add(ReactionAssetManager.emoji9);
        emojis.add(ReactionAssetManager.emoji10);
        emojis.add(ReactionAssetManager.emoji11);
        emojis.add(ReactionAssetManager.emoji12);
        emojis.add(ReactionAssetManager.emoji13);
        emojis.add(ReactionAssetManager.emoji14);
        emojis.add(ReactionAssetManager.emoji15);
        emojis.add(ReactionAssetManager.emoji16);
        emojis.add(ReactionAssetManager.emoji17);
        emojis.add(ReactionAssetManager.emoji18);
        emojis.add(ReactionAssetManager.emoji19);
        emojis.add(ReactionAssetManager.emoji20);
        emojis.add(ReactionAssetManager.emoji21);
        emojis.add(ReactionAssetManager.emoji22);
        emojis.add(ReactionAssetManager.emoji23);
        emojis.add(ReactionAssetManager.emoji24);
        emojis.add(ReactionAssetManager.emoji25);
        emojis.add(ReactionAssetManager.emoji26);
        emojis.add(ReactionAssetManager.emoji27);
        emojis.add(ReactionAssetManager.emoji28);
        emojis.add(ReactionAssetManager.emoji29);
        emojis.add(ReactionAssetManager.emoji30);
        emojis.add(ReactionAssetManager.emoji31);
        emojis.add(ReactionAssetManager.emoji32);
        emojis.add(ReactionAssetManager.emoji33);
        emojis.add(ReactionAssetManager.emoji34);

        return emojis;
    }

    public static ArrayList<Texture> getTexts() {
        ArrayList<Texture> texts = new ArrayList<>();

        texts.add(ReactionAssetManager.text0);
        texts.add(ReactionAssetManager.text1);
        texts.add(ReactionAssetManager.text2);
        texts.add(ReactionAssetManager.text3);
        texts.add(ReactionAssetManager.text4);
        texts.add(ReactionAssetManager.text5);
        texts.add(ReactionAssetManager.text6);
        texts.add(ReactionAssetManager.text7);

        return texts;
    }

    public static Texture getReactionByName(String name) {
        if (name.equals("emoji0")) {
            return ReactionAssetManager.emoji0;
        } else if (name.equals("emoji1")) {
            return ReactionAssetManager.emoji1;
        } else if (name.equals("emoji2")) {
            return ReactionAssetManager.emoji2;
        } else if (name.equals("emoji3")) {
            return ReactionAssetManager.emoji3;
        } else if (name.equals("emoji4")) {
            return ReactionAssetManager.emoji4;
        } else if (name.equals("emoji5")) {
            return ReactionAssetManager.emoji5;
        } else if (name.equals("emoji6")) {
            return ReactionAssetManager.emoji6;
        } else if (name.equals("emoji7")) {
            return ReactionAssetManager.emoji7;
        } else if (name.equals("emoji8")) {
            return ReactionAssetManager.emoji8;
        } else if (name.equals("emoji9")) {
            return ReactionAssetManager.emoji9;
        } else if (name.equals("emoji10")) {
            return ReactionAssetManager.emoji10;
        } else if (name.equals("emoji11")) {
            return ReactionAssetManager.emoji11;
        } else if (name.equals("emoji12")) {
            return ReactionAssetManager.emoji12;
        } else if (name.equals("emoji13")) {
            return ReactionAssetManager.emoji13;
        } else if (name.equals("emoji14")) {
            return ReactionAssetManager.emoji14;
        } else if (name.equals("emoji15")) {
            return ReactionAssetManager.emoji15;
        } else if (name.equals("emoji16")) {
            return ReactionAssetManager.emoji16;
        } else if (name.equals("emoji17")) {
            return ReactionAssetManager.emoji17;
        } else if (name.equals("emoji18")) {
            return ReactionAssetManager.emoji18;
        } else if (name.equals("emoji19")) {
            return ReactionAssetManager.emoji19;
        } else if (name.equals("emoji20")) {
            return ReactionAssetManager.emoji20;
        } else if (name.equals("emoji21")) {
            return ReactionAssetManager.emoji21;
        } else if (name.equals("emoji22")) {
            return ReactionAssetManager.emoji22;
        } else if (name.equals("emoji23")) {
            return ReactionAssetManager.emoji23;
        } else if (name.equals("emoji24")) {
            return ReactionAssetManager.emoji24;
        } else if (name.equals("emoji25")) {
            return ReactionAssetManager.emoji25;
        } else if (name.equals("emoji26")) {
            return ReactionAssetManager.emoji26;
        } else if (name.equals("emoji27")) {
            return ReactionAssetManager.emoji27;
        } else if (name.equals("emoji28")) {
            return ReactionAssetManager.emoji28;
        } else if (name.equals("emoji29")) {
            return ReactionAssetManager.emoji29;
        } else if (name.equals("emoji30")) {
            return ReactionAssetManager.emoji30;
        } else if (name.equals("emoji31")) {
            return ReactionAssetManager.emoji31;
        } else if (name.equals("emoji32")) {
            return ReactionAssetManager.emoji32;
        } else if (name.equals("emoji33")) {
            return ReactionAssetManager.emoji33;
        } else if (name.equals("emoji34")) {
            return ReactionAssetManager.emoji34;
        } else if (name.equals("text0")) {
            return ReactionAssetManager.text0;
        } else if (name.equals("text1")) {
            return ReactionAssetManager.text1;
        } else if (name.equals("text2")) {
            return ReactionAssetManager.text2;
        } else if (name.equals("text3")) {
            return ReactionAssetManager.text3;
        } else if (name.equals("text4")) {
            return ReactionAssetManager.text4;
        } else if (name.equals("text5")) {
            return ReactionAssetManager.text5;
        } else if (name.equals("text6")) {
            return ReactionAssetManager.text6;
        } else if (name.equals("text7")) {
            return ReactionAssetManager.text7;
        }
        return null;
    }

    public static String getNameOfReaction(Texture texture) {
        if (texture == null) {
            return "";
        }
        if (texture.equals(ReactionAssetManager.emoji0)) {
            return "emoji0";
        } else if (texture.equals(ReactionAssetManager.emoji1)) {
            return "emoji1";
        } else if (texture.equals(ReactionAssetManager.emoji2)) {
            return "emoji2";
        } else if (texture.equals(ReactionAssetManager.emoji3)) {
            return "emoji3";
        } else if (texture.equals(ReactionAssetManager.emoji4)) {
            return "emoji4";
        } else if (texture.equals(ReactionAssetManager.emoji5)) {
            return "emoji5";
        } else if (texture.equals(ReactionAssetManager.emoji6)) {
            return "emoji6";
        } else if (texture.equals(ReactionAssetManager.emoji7)) {
            return "emoji7";
        } else if (texture.equals(ReactionAssetManager.emoji8)) {
            return "emoji8";
        } else if (texture.equals(ReactionAssetManager.emoji9)) {
            return "emoji9";
        } else if (texture.equals(ReactionAssetManager.emoji10)) {
            return "emoji10";
        } else if (texture.equals(ReactionAssetManager.emoji11)) {
            return "emoji11";
        } else if (texture.equals(ReactionAssetManager.emoji12)) {
            return "emoji12";
        } else if (texture.equals(ReactionAssetManager.emoji13)) {
            return "emoji13";
        } else if (texture.equals(ReactionAssetManager.emoji14)) {
            return "emoji14";
        } else if (texture.equals(ReactionAssetManager.emoji15)) {
            return "emoji15";
        } else if (texture.equals(ReactionAssetManager.emoji16)) {
            return "emoji16";
        } else if (texture.equals(ReactionAssetManager.emoji17)) {
            return "emoji17";
        } else if (texture.equals(ReactionAssetManager.emoji18)) {
            return "emoji18";
        } else if (texture.equals(ReactionAssetManager.emoji19)) {
            return "emoji19";
        } else if (texture.equals(ReactionAssetManager.emoji20)) {
            return "emoji20";
        } else if (texture.equals(ReactionAssetManager.emoji21)) {
            return "emoji21";
        } else if (texture.equals(ReactionAssetManager.emoji22)) {
            return "emoji22";
        } else if (texture.equals(ReactionAssetManager.emoji23)) {
            return "emoji23";
        } else if (texture.equals(ReactionAssetManager.emoji24)) {
            return "emoji24";
        } else if (texture.equals(ReactionAssetManager.emoji25)) {
            return "emoji25";
        } else if (texture.equals(ReactionAssetManager.emoji26)) {
            return "emoji26";
        } else if (texture.equals(ReactionAssetManager.emoji27)) {
            return "emoji27";
        } else if (texture.equals(ReactionAssetManager.emoji28)) {
            return "emoji28";
        } else if (texture.equals(ReactionAssetManager.emoji29)) {
            return "emoji29";
        } else if (texture.equals(ReactionAssetManager.emoji30)) {
            return "emoji30";
        } else if (texture.equals(ReactionAssetManager.emoji31)) {
            return "emoji31";
        } else if (texture.equals(ReactionAssetManager.emoji32)) {
            return "emoji32";
        } else if (texture.equals(ReactionAssetManager.emoji33)) {
            return "emoji33";
        } else if (texture.equals(ReactionAssetManager.emoji34)) {
            return "emoji34";
        } else if (texture.equals(ReactionAssetManager.text0)) {
            return "text0";
        } else if (texture.equals(ReactionAssetManager.text1)) {
            return "text1";
        } else if (texture.equals(ReactionAssetManager.text2)) {
            return "text2";
        } else if (texture.equals(ReactionAssetManager.text3)) {
            return "text3";
        } else if (texture.equals(ReactionAssetManager.text4)) {
            return "text4";
        } else if (texture.equals(ReactionAssetManager.text5)) {
            return "text5";
        } else if (texture.equals(ReactionAssetManager.text6)) {
            return "text6";
        } else if (texture.equals(ReactionAssetManager.text7)) {
            return "text7";
        }
        else {
            return "";
        }
    }
}
