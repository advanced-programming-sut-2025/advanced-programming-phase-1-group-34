package org.Group34.view.graphic.gameMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.npcs.NPC;
import org.Group34.model.entities.npcs.quests.Quest;
import org.Group34.model.gameAssetManagers.*;
import org.Group34.model.items.crafting.Ingredient;
import org.Group34.model.items.foods.CookedFood;
import org.Group34.model.items.foods.CropProduct;
import org.Group34.model.items.foods.Vegetable;
import java.util.ArrayList;
import java.util.List;

public class NPCMenu {
    private final static Sprite chest = new Sprite(OtherAssetManager.getChest());
    private final static Sprite bigBoard = new Sprite(OtherAssetManager.getBigBoard());
    private final static Sprite smallBoard = new Sprite(OtherAssetManager.getSmallBoard());
    private final static Sprite inventorySymbol = new Sprite(ToolAssetManager.getBasicBackpack());
    private final static Sprite skillSymbol = new Sprite(OtherAssetManager.getFaceIcon());
    private final static Sprite socialSymbol = new Sprite(OtherAssetManager.getHearthIcon());
    private final static Sprite mapSymbol = new Sprite(OtherAssetManager.getForestIcon());
    private final static Sprite NPCSymbol = new Sprite(OtherAssetManager.getSmileIcon());
    private final static Sprite settingSymbol = new Sprite(OtherAssetManager.getSettingIcon());

    private static boolean giftDialogOpen = false;
    private static int selectedNPCIndex = -1;
    private static String giftDialogError = "";

    private static final String[][] GIFT_OPTIONS = {
            {"Carrot", "Flower"},
            {"1000$", "Wood"}
    };

    private static BitmapFont font;
    private static BitmapFont errorFont;
    private static BitmapFont rewardFont;

    private static List<NPC> npcs;
    private static List<String> errorMessages = new ArrayList<>();

    private static String rewardMessage = "";
    private static float rewardMessageTimer = 0;
    private static final float REWARD_MESSAGE_DURATION = 3.0f;

    static {
        bigBoard.setSize(chest.getWidth(), 413);
        smallBoard.setSize((float) (smallBoard.getWidth() * 0.7), (float) (smallBoard.getHeight() * 0.7));
        inventorySymbol.setSize((float) (inventorySymbol.getWidth() * 0.7), (float) (inventorySymbol.getHeight() * 0.7));
        skillSymbol.setSize((float) (skillSymbol.getWidth() * 0.8), (float) (skillSymbol.getHeight() * 0.8));
        socialSymbol.setSize((float) (socialSymbol.getWidth() * 0.4), (float) (socialSymbol.getHeight() * 0.4));
        mapSymbol.setSize((float) (mapSymbol.getWidth() * 0.6), (float) (mapSymbol.getHeight() * 0.6));
        NPCSymbol.setSize((float) (NPCSymbol.getWidth() * 0.8), (float) (NPCSymbol.getHeight() * 0.8));
        settingSymbol.setSize((float) (settingSymbol.getWidth() * 0.05), (float) (settingSymbol.getHeight() * 0.05));

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(1.2f);

        errorFont = new BitmapFont();
        errorFont.setColor(Color.RED);
        errorFont.getData().setScale(1.0f);

        rewardFont = new BitmapFont();
        rewardFont.setColor(Color.GREEN);
        rewardFont.getData().setScale(2.0f);
    }

    public static void setNPCs(List<NPC> npcList) {
        npcs = npcList;
        errorMessages.clear();
        for (int i = 0; i < npcList.size(); i++) {
            errorMessages.add("");
        }
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;
        drawBoard(batch, x, y);

        handleInput(player);

        if (npcs != null && !npcs.isEmpty()) {
            drawNPCInfo(batch, x, y, player);
        }

        if (giftDialogOpen) {
            drawGiftDialog(batch, camera);
        }

        if (rewardMessageTimer > 0) {
            float alpha = Math.min(1.0f, rewardMessageTimer);
            if (rewardMessageTimer < 1.0f) {
                alpha = rewardMessageTimer;
            }

            rewardFont.setColor(0, 0.8f, 0, alpha);
            float messageX = camera.position.x - 150;
            float messageY = camera.position.y + 10;
            rewardFont.draw(batch, rewardMessage, messageX, messageY);
        }
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
        smallBoard.setPosition(x + 206, y + 203);
        smallBoard.draw(batch);
        smallBoard.setPosition(x + 250, y + 210);
        smallBoard.draw(batch);
        inventorySymbol.setPosition(x + 30 + 5, y + 210 + 2);
        inventorySymbol.draw(batch);
        skillSymbol.setPosition(x + 74 + 9, y + 210 + 5);
        skillSymbol.draw(batch);
        socialSymbol.setPosition(x + 118 + 9, y + 210 + 5);
        socialSymbol.draw(batch);
        mapSymbol.setPosition(x + 162 + 11, y + 210 + 3);
        mapSymbol.draw(batch);
        NPCSymbol.setPosition(x + 206 + 10, y + 203 + 5);
        NPCSymbol.draw(batch);
        settingSymbol.setPosition(x + 250 + 13, y + 210 + 8);
        settingSymbol.draw(batch);
    }

    private static void drawNPCInfo(SpriteBatch batch, float x, float y, Player player) {
        float sectionHeight = 180;
        float startY = y + 100;

        if (npcs == null || npcs.isEmpty()) {
            font.draw(batch, "No NPCs available", x + 50, y);
            return;
        }

        for (int i = 0; i < Math.min(npcs.size(), 2); i++) {
            NPC npc = npcs.get(i);
            float baseY = startY - i * sectionHeight;
            float leftX = x + 30;
            float rightX = x + 300;

            try {
                if (i == 1) {
                    if (NPCAssetManager.bob != null) {
                        batch.draw(NPCAssetManager.bob, leftX, baseY - 30, 80, 120);
                    } else {
                        batch.setColor(Color.RED);
                        batch.draw(smallBoard, leftX, baseY - 30, 80, 120);
                        batch.setColor(Color.WHITE);
                        font.draw(batch, "Bob", leftX + 20, baseY + 30);
                    }
                    if (NPCAssetManager.gift != null) {
                        batch.draw(NPCAssetManager.gift, leftX + 15, baseY - 75, 50, 50);
                    } else {
                        batch.setColor(Color.YELLOW);
                        batch.draw(smallBoard, leftX + 15, baseY - 75, 50, 50);
                        batch.setColor(Color.WHITE);
                    }
                } else {
                    if (NPCAssetManager.alice != null) {
                        batch.draw(NPCAssetManager.alice, leftX, baseY - 30, 80, 120);
                    } else {
                        batch.setColor(Color.RED);
                        batch.draw(smallBoard, leftX, baseY - 30, 80, 120);
                        batch.setColor(Color.WHITE);
                        font.draw(batch, "Alice", leftX + 20, baseY + 30);
                    }
                    if (NPCAssetManager.gift != null) {
                        batch.draw(NPCAssetManager.gift, leftX + 15, baseY - 75, 50, 50);
                    } else {
                        batch.setColor(Color.YELLOW);
                        batch.draw(smallBoard, leftX + 15, baseY - 75, 50, 50);
                        batch.setColor(Color.WHITE);
                    }
                }
            } catch (Exception e) {
                font.draw(batch, "Error loading NPC image", leftX, baseY);
            }

            if (i < errorMessages.size() && !errorMessages.get(i).isEmpty()) {
                float errorX = leftX + 10;
                float errorY = baseY - 70;
                errorFont.draw(batch, errorMessages.get(i), errorX, errorY);
            }

            font.draw(batch, "Name: " + npc.getName(), leftX + 90, baseY + 70);
            int friendshipLevel = getFriendshipLevel(npc.getFriendshipPoints());
            font.draw(batch, "Friendship: Level " + friendshipLevel + " (" + npc.getFriendshipPoints() + ")", leftX + 90, baseY + 45);

            font.draw(batch, "Liked Items:", leftX + 90, baseY + 20);
            float itemY = baseY - 5;
            for (String item : npc.getLikedItems()) {
                font.draw(batch, "- " + item, leftX + 150, itemY);
                itemY -= 20;
            }

            font.draw(batch, "Quests:", rightX, baseY + 70);
            float questY = baseY + 50;
            List<Quest> quests = npc.getQuests();
            int maxQuests = 3;

            for (int j = 0; j < maxQuests; j++) {
                if (friendshipLevel >= (j + 1)) {
                    if (quests != null && j < quests.size()) {
                        Quest quest = quests.get(j);
                        font.setColor(Color.BLACK);
                        font.draw(batch, (j + 1) + ". " + quest.getTitle(), rightX + 10, questY);

                        boolean hasItems = checkRequiredItemForQuest(player, quest);
                        if (quest.isCompleted()) {
                            font.draw(batch, "[Completed]", rightX + 220, questY);
                        } else if (hasItems) {
                            font.setColor(Color.GREEN);
                            font.draw(batch, "[Available]", rightX + 220, questY);
                            font.setColor(Color.BLACK);
                            font.draw(batch, "[R]", rightX + 190, questY);
                        } else {
                            font.setColor(Color.RED);
                            font.draw(batch, "[Need Item]", rightX + 220, questY);
                            font.setColor(Color.GRAY);
                            font.draw(batch, "[R]", rightX + 190, questY);
                        }
                    } else {
                        font.setColor(Color.GRAY);
                        font.draw(batch, (j + 1) + ". No quest", rightX + 10, questY);
                        font.draw(batch, "[Empty]", rightX + 220, questY);
                    }
                } else {
                    font.setColor(Color.GRAY);
                    font.draw(batch, (j + 1) + ". ???", rightX + 10, questY);
                    font.draw(batch, "[Locked]", rightX + 220, questY);
                }
                questY -= 25;
            }
            font.setColor(Color.BLACK);
        }
    }

    private static boolean checkRequiredItemForQuest(Player player, Quest quest) {
        if (quest.getTitle() == null) {
            return false;
        }

        String description = quest.getTitle().toLowerCase();
        if (description.contains("carrot")) {
            return player.getAmountOfItem(Vegetable.CARROT) >= 1;
        } else if (description.contains("blackberry")) {
            return player.getAmountOfItem(CropProduct.BLACKBERRY) >= 1;
        } else if (description.contains("wood")) {
            return player.getAmountOfItem(Ingredient.WOOD) >= 1;
        } else if (description.contains("money") || description.contains("$")) {
            return player.getMoney() >= 1000;
        } else if (description.contains("fish")) {
            return player.getAmountOfItem(CookedFood.SALMON_DINNER) >= 1;
        }
        return false;
    }

    private static void handleQuestInteraction(Player player, NPC npc, Quest quest) {
        if (quest.getLevel() > getFriendshipLevel(npc.getFriendshipPoints())) {
            return;
        }

        if (quest.isCompleted()) {
            System.out.println("Quest '" + quest.getTitle() + "' is already completed!");
            return;
        }

        if (checkRequiredItemForQuest(player, quest)) {
            String description = quest.getTitle() != null ? quest.getTitle().toLowerCase() : "";
            if (description.contains("carrot")) {
                player.removeFromInventory(Vegetable.CARROT, 1);
                player.addToInventory(CookedFood.FRUIT_SALAD, 1);
                rewardMessage = "You received a Fruit Salad!";
            } else if (description.contains("blackberry")) {
                player.removeFromInventory(CropProduct.BLACKBERRY, 1);
                player.addMoney(10000);
                rewardMessage = "You received 10,000$!";
            } else if (description.contains("wood")) {
                player.removeFromInventory(Ingredient.WOOD, 1);
                player.addMoney(1000);
                rewardMessage = "You received 1000$!";
            } else if (description.contains("money") || description.contains("$")) {
                player.addMoney(-1000);
                player.addToInventory(Ingredient.IRON_BAR, 1);
                rewardMessage = "You received an Iron Bar!";
            } else if (description.contains("fish")) {
                player.removeFromInventory(CookedFood.SALMON_DINNER, 1);
                player.addToInventory(CropProduct.CRYSTAL_FRUIT, 1);
                rewardMessage = "You received a Crystal Fruit!";
            }
            rewardMessageTimer = REWARD_MESSAGE_DURATION;
            quest.setCompleted(true);
            npc.increaseFriendship(200);
            System.out.println("Quest '" + quest.getTitle() + "' completed!");
        } else {
            int npcIndex = npcs.indexOf(npc);
            if (npcIndex >= 0 && npcIndex < errorMessages.size()) {
                errorMessages.set(npcIndex, "Need: " + (quest.getTitle() != null ? quest.getTitle() : "Unknown item"));
            }
        }
    }

    private static void drawGiftDialog(SpriteBatch batch, OrthographicCamera camera) {
        if (selectedNPCIndex < 0 || selectedNPCIndex >= npcs.size()) return;

        NPC npc = npcs.get(selectedNPCIndex);
        float dialogWidth = 400;
        float dialogHeight = 200;
        float dialogX = camera.position.x - dialogWidth / 2;
        float dialogY = camera.position.y - dialogHeight / 2;

        Sprite dialogBackground = new Sprite(OtherAssetManager.getBigBoard());
        dialogBackground.setSize(dialogWidth, dialogHeight);
        dialogBackground.setPosition(dialogX, dialogY);
        dialogBackground.draw(batch);

        font.draw(batch, "Select a gift for " + npc.getName(),
                dialogX + 50, dialogY + dialogHeight - 30);

        float optionY = dialogY + dialogHeight - 100;
        for (int i = 0; i < GIFT_OPTIONS[selectedNPCIndex].length; i++) {
            String giftName = GIFT_OPTIONS[selectedNPCIndex][i];
            smallBoard.setPosition(dialogX + 55, optionY - i * 60);
            smallBoard.draw(batch);

            Sprite giftIcon = getGiftIcon(giftName);
            if (giftIcon != null) {
                giftIcon.setPosition(dialogX + 65, optionY - i * 60 + 5);
                giftIcon.setSize(30, 30);
                giftIcon.draw(batch);
            }

            font.draw(batch, giftName, dialogX + 120, optionY - i * 60 + 30);
        }

        if (!giftDialogError.isEmpty()) {
            errorFont.draw(batch, giftDialogError,
                    dialogX + 50, dialogY + 30);
        }

        font.draw(batch, "X", dialogX + dialogWidth - 30, dialogY + dialogHeight - 20);
    }

    private static Sprite getGiftIcon(String giftName) {
        if (giftName.equals("Carrot")) {
            return new Sprite(CropAssetManager.getCarrot());
        }
        else if (giftName.equals("Flower")) {
            return new Sprite(CropAssetManager.getFairyRose());
        }
        else if (giftName.equals("1000$")) {
            return new Sprite(OtherAssetManager.getCoinIcon());
        }
        else if (giftName.equals("Wood")) {
            return new Sprite(OtherAssetManager.getWoodIcon());
        }
        return null;
    }

    private static int getFriendshipLevel(int friendshipPoints) {
        if (friendshipPoints < 200) return 1;
        if (friendshipPoints < 400) return 2;
        if (friendshipPoints < 600) return 3;
        return 4;
    }

    private static boolean isQuestAvailable(Quest quest, int friendshipLevel) {
        return quest.getLevel() <= friendshipLevel;
    }

    public static void handleInput(Player player) {
        if (rewardMessageTimer > 0) {
            rewardMessageTimer -= Gdx.graphics.getDeltaTime();
            if (rewardMessageTimer <= 0) {
                rewardMessage = "";
            }
        }

        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (giftDialogOpen) {
            handleGiftDialogInput(player, x, y);
            return;
        }

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
        }

        if (npcs != null && !npcs.isEmpty()) {
            for (int i = 0; i < npcs.size() && i < 2; i++) {
                NPC npc = npcs.get(i);
                float questX = 820;
                float questY = 320 + (i * 340);

                if (npc.getQuests() != null && !npc.getQuests().isEmpty()) {
                    int friendshipLevel = getFriendshipLevel(npc.getFriendshipPoints());
                    for (int q = 0; q <= 2; q++) {
                        Quest quest = npc.getQuests().get(q);
                        if (isQuestAvailable(quest, friendshipLevel)) {
                            float buttonY = questY - 100 + (q * 45);
                            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                                    x > questX && x < questX + 100 &&
                                    y > buttonY - 20 && y < buttonY + 20) {
                                handleQuestInteraction(player, npc, quest);
                            }
                        }
                    }
                }
            }
        }

        float giftX = 375;
        float giftWidth = 70;
        float giftHeight = 70;
        for (int i = 0; i < 2; i++) {
            int giftY;
            if (i == 0) {
                giftY = 400;
            }
            else {
                giftY = 700;
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                    x > giftX && x < giftX + giftWidth &&
                    y > giftY && y < giftY + giftHeight) {
                giftDialogOpen = true;
                selectedNPCIndex = i;
                giftDialogError = "";
                return;
            }
        }
    }

    private static void handleGiftDialogInput(Player player, int x, int y) {
        if (selectedNPCIndex < 0 || selectedNPCIndex >= npcs.size()) return;

        NPC npc = npcs.get(selectedNPCIndex);
        float dialogWidth = 400;
        float dialogHeight = 200;
        float dialogX = Gdx.graphics.getWidth() / 2 - dialogWidth / 2;
        float dialogY = Gdx.graphics.getHeight() / 2 - dialogHeight / 2;

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                x > dialogX + dialogWidth + 50 && x < dialogX + dialogWidth + 100 &&
                y > dialogY - 50 && y < dialogY) {
            giftDialogOpen = false;
            return;
        }

        float optionY = dialogY + dialogHeight - 100;
        for (int i = 0; i < GIFT_OPTIONS[selectedNPCIndex].length; i++) {
            float optionTop = optionY + i * 80;
            float optionBottom = optionTop - 50;
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                    x > dialogX - 50 && x < dialogX + 50 &&
                    y > optionBottom && y < optionTop) {
                String selectedGift = GIFT_OPTIONS[selectedNPCIndex][i];
                processGiftSelection(player, npc, selectedGift);
                return;
            }
        }
    }

    private static void processGiftSelection(Player player, NPC npc, String giftName) {
        boolean success = false;
        if (selectedNPCIndex == 0) {
            if (giftName.equals("Carrot")) {
                if (player.getAmountOfItem(Vegetable.CARROT) > 0) {
                    player.removeFromInventory(Vegetable.CARROT, 1);
                    npc.increaseFriendship(50);
                    success = true;
                } else {
                    giftDialogError = "Not enough carrots!";
                }
            } else if (giftName.equals("Flower")) {
                if (player.getAmountOfItem(CookedFood.COOKIE) > 0) {
                    player.removeFromInventory(CookedFood.COOKIE, 1);
                    npc.increaseFriendship(50);
                    success = true;
                } else {
                    giftDialogError = "Not enough flowers!";
                }
            }
        } else {
            if (giftName.equals("1000$")) {
                if (player.getMoney() >= 1000) {
                    player.addMoney(-1000);
                    npc.increaseFriendship(50);
                    success = true;
                } else {
                    giftDialogError = "Not enough money!";
                }
            } else if (giftName.equals("Wood")) {
                if (player.getAmountOfItem(Ingredient.WOOD) > 0) {
                    player.removeFromInventory(Ingredient.WOOD, 1);
                    npc.increaseFriendship(50);
                    success = true;
                } else {
                    giftDialogError = "Not enough wood!";
                }
            }
        }

        if (success) {
            giftDialogOpen = false;
            System.out.println("Gift given to " + npc.getName() + ": " + giftName);
        }
    }

    public static void dispose() {
        if (font != null) {
            font.dispose();
        }
        if (errorFont != null) {
            errorFont.dispose();
        }
        if (rewardFont != null) {
            rewardFont.dispose();
        }
    }
}