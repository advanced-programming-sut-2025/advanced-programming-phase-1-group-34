package org.Group34.view.graphic.gameMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.npcs.NPC;
import org.Group34.model.entities.npcs.quests.Quest;
import org.Group34.model.gameAssetManagers.*;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;
import org.Group34.model.items.foods.CookedFood;
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

    // Gift selection dialog state
    private static boolean giftDialogOpen = false;
    private static int selectedNPCIndex = -1;
    private static String giftDialogError = "";

    // Gift options for each NPC
    private static final String[][] GIFT_OPTIONS = {
            {"Carrot", "Flower"},  // Alice's gifts
            {"1000$", "Wood"}      // Bob's gifts
    };

    // Font for text rendering
    private static BitmapFont font;
    private static BitmapFont errorFont; // Smaller font for error messages

    // NPC data
    private static List<NPC> npcs;
    private static List<String> errorMessages = new ArrayList<>();

    static {
        bigBoard.setSize(chest.getWidth(), 413);
        smallBoard.setSize((float) (smallBoard.getWidth() * 0.7), (float) (smallBoard.getHeight() * 0.7));
        inventorySymbol.setSize((float) (inventorySymbol.getWidth() * 0.7), (float) (inventorySymbol.getHeight() * 0.7));
        skillSymbol.setSize((float) (skillSymbol.getWidth() * 0.8), (float) (skillSymbol.getHeight() * 0.8));
        socialSymbol.setSize((float) (socialSymbol.getWidth() * 0.4), (float) (socialSymbol.getHeight() * 0.4));
        mapSymbol.setSize((float) (mapSymbol.getWidth() * 0.6), (float) (mapSymbol.getHeight() * 0.6));
        NPCSymbol.setSize((float) (NPCSymbol.getWidth() * 0.8), (float) (NPCSymbol.getHeight() * 0.8));
        settingSymbol.setSize((float) (settingSymbol.getWidth() * 0.05), (float) (settingSymbol.getHeight() * 0.05));

        // Initialize fonts
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(1.2f);

        // Initialize smaller font for error messages
        errorFont = new BitmapFont();
        errorFont.setColor(Color.RED);
        errorFont.getData().setScale(0.8f);
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

        // Draw NPC information if we have NPCs
        if (npcs != null && !npcs.isEmpty()) {
            drawNPCInfo(batch, x, y, player);
        }

        // Draw gift dialog if open
        if (giftDialogOpen) {
            drawGiftDialog(batch, camera);
        }

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

        // Make sure we have NPCs to display
        if (npcs == null || npcs.isEmpty()) {
            font.draw(batch, "No NPCs available", x + 50, y);
            return;
        }

        // Draw each NPC (maximum 2)
        for (int i = 0; i < Math.min(npcs.size(), 2); i++) {
            NPC npc = npcs.get(i);
            float baseY = startY - i * sectionHeight;
            float leftX = x + 30;
            float rightX = x + 300;

            // Draw NPC image
            try {
                if (i == 1) {
                    if (NPCAssetManager.bob != null) {
                        batch.draw(NPCAssetManager.bob, leftX, baseY - 30, 80, 120);
                    } else {
                        // Draw placeholder if Bob texture is missing
                        batch.setColor(Color.RED);
                        batch.draw(smallBoard, leftX, baseY - 30, 80, 120);
                        batch.setColor(Color.WHITE);
                        font.draw(batch, "Bob", leftX + 20, baseY + 30);
                    }
                    // Draw gift icon
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
                        // Draw placeholder if Alice texture is missing
                        batch.setColor(Color.RED);
                        batch.draw(smallBoard, leftX, baseY - 30, 80, 120);
                        batch.setColor(Color.WHITE);
                        font.draw(batch, "Alice", leftX + 20, baseY + 30);
                    }
                    // Draw gift icon
                    if (NPCAssetManager.gift != null) {
                        batch.draw(NPCAssetManager.gift, leftX + 15, baseY - 75, 50, 50);
                    } else {
                        batch.setColor(Color.YELLOW);
                        batch.draw(smallBoard, leftX + 15, baseY - 75, 50, 50);
                        batch.setColor(Color.WHITE);
                    }
                }
            } catch (Exception e) {
                // If there's an error drawing the NPC, show an error message
                font.draw(batch, "Error loading NPC image", leftX, baseY);
            }

            // Draw error message if exists - positioned right below the gift icon
            if (i < errorMessages.size() && !errorMessages.get(i).isEmpty()) {
                // Center the error message below the gift icon
                float errorX = leftX + 15; // Same X as gift icon
                float errorY = baseY - 85; // Just below the gift icon (which is at baseY - 75)
                // Draw error message with smaller font
                errorFont.draw(batch, errorMessages.get(i), errorX, errorY);
            }

            // Draw NPC name
            font.draw(batch, "Name: " + npc.getName(), leftX + 90, baseY + 70);

            // Draw friendship level
            int friendshipLevel = getFriendshipLevel(npc.getFriendshipPoints());
            font.draw(batch, "Friendship: Level " + friendshipLevel + " (" + npc.getFriendshipPoints() + ")", leftX + 90, baseY + 45);

            // Draw liked items
            font.draw(batch, "Liked Items:", leftX + 90, baseY + 20);
            float itemY = baseY - 5;
            for (String item : npc.getLikedItems()) {
                font.draw(batch, "- " + item, leftX + 150, itemY);
                itemY -= 20;
            }

            // Draw quests section header
            font.draw(batch, "Quests:", rightX, baseY + 70);
            float questY = baseY + 50;

            // Draw quests
            List<Quest> quests = npc.getQuests();
            for (int j = 0; j < 3; j++) {
                if (quests != null && j < quests.size()) {
                    Quest quest = quests.get(j);
                    boolean questAvailable = isQuestAvailable(quest, friendshipLevel);
                    if (questAvailable) {
                        font.setColor(Color.BLACK);
                        font.draw(batch, (j + 1) + ". " + quest.getTitle(), rightX + 10, questY);
                        font.draw(batch, "[" + (quest.isCompleted() ? "Completed" : "Delivery") + "]", rightX + 220, questY);
                    } else {
                        font.setColor(Color.GRAY);
                        font.draw(batch, (j + 1) + ". ???", rightX + 10, questY);
                        font.draw(batch, "[Locked]", rightX + 220, questY);
                    }
                } else {
                    // No quest in this slot → ??? + [Locked]
                    font.setColor(Color.GRAY);
                    font.draw(batch, (j + 1) + ". ???", rightX + 10, questY);
                    font.draw(batch, "[Locked]", rightX + 220, questY);
                }
                questY -= 25;
            }
            font.setColor(Color.BLACK);
        }
    }

    private static void drawGiftDialog(SpriteBatch batch, OrthographicCamera camera) {
        if (selectedNPCIndex < 0 || selectedNPCIndex >= npcs.size()) return;

        NPC npc = npcs.get(selectedNPCIndex);
        float dialogWidth = 400;
        float dialogHeight = 200;
        float dialogX = camera.position.x - dialogWidth / 2;
        float dialogY = camera.position.y - dialogHeight / 2;

        // Create a new sprite for dialog background to avoid modifying the static bigBoard
        Sprite dialogBackground = new Sprite(OtherAssetManager.getBigBoard());
        dialogBackground.setSize(dialogWidth, dialogHeight);
        dialogBackground.setPosition(dialogX, dialogY);
        dialogBackground.draw(batch);

        // Draw title
        font.draw(batch, "Select a gift for " + npc.getName(),
                dialogX + 50, dialogY + dialogHeight - 30);

        // Draw gift options
        float optionY = dialogY + dialogHeight - 100;
        for (int i = 0; i < GIFT_OPTIONS[selectedNPCIndex].length; i++) {
            String giftName = GIFT_OPTIONS[selectedNPCIndex][i];

            // Draw option background
            smallBoard.setPosition(dialogX + 55, optionY - i * 60);
            smallBoard.draw(batch);

            // Draw gift icon
            Sprite giftIcon = getGiftIcon(giftName);
            if (giftIcon != null) {
                giftIcon.setPosition(dialogX + 65, optionY - i * 60 + 5);
                giftIcon.setSize(30, 30);
                giftIcon.draw(batch);
            }

            // Draw gift name
            font.draw(batch, giftName, dialogX + 120, optionY - i * 60 + 30);
        }

        // Draw error message if exists
        if (!giftDialogError.isEmpty()) {
            errorFont.draw(batch, giftDialogError,
                    dialogX + 50, dialogY + 30);
        }

        // Draw close button
        font.draw(batch, "X", dialogX + dialogWidth - 30, dialogY + dialogHeight - 20);
    }

    private static Sprite getGiftIcon(String giftName) {
        // Return appropriate sprite based on gift name
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
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        // Handle gift dialog if open
        if (giftDialogOpen) {
            handleGiftDialogInput(player, x, y);
            return;
        }

        // Handle menu buttons
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

        // Handle quest interaction (if there were any quests)
        if (npcs != null && !npcs.isEmpty()) {
            for (int i = 0; i < npcs.size() && i < 2; i++) {
                NPC npc = npcs.get(i);
                // Calculate position for this NPC's quests
                float questX = 530; // Approximate position of quest buttons
                float questY = 280 - (i * 180); // Adjust based on your layout

                // Check each quest button
                if (npc.getQuests() != null && !npc.getQuests().isEmpty()) {
                    int friendshipLevel = getFriendshipLevel(npc.getFriendshipPoints());
                    for (int q = 0; q < npc.getQuests().size(); q++) {
                        Quest quest = npc.getQuests().get(q);
                        if (isQuestAvailable(quest, friendshipLevel)) {
                            float buttonY = questY - (q * 25);
                            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                                    x > questX && x < questX + 100 &&
                                    y > buttonY - 10 && y < buttonY + 10) {
                                // Handle quest interaction
                                handleQuestInteraction(player, npc, quest);
                            }
                        }
                    }
                }
            }
        }

        // Handle gift button clicks
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
                // Open gift dialog instead of directly giving gift
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
        float dialogHeight = 200; // Match the height used in drawGiftDialog
        float dialogX = Gdx.graphics.getWidth() / 2 - dialogWidth / 2;
        float dialogY = Gdx.graphics.getHeight() / 2 - dialogHeight / 2;

        // Check for close button click - only if left button is pressed
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                x > dialogX + dialogWidth + 50 && x < dialogX + dialogWidth + 100 &&
                y > dialogY - 50 && y < dialogY) {
            giftDialogOpen = false;
            return;
        }

        // Check for gift option clicks - only if left button is pressed
        float optionY = dialogY + dialogHeight - 100;
        for (int i = 0; i < GIFT_OPTIONS[selectedNPCIndex].length; i++) {
            float optionTop = optionY + i * 80;
            float optionBottom = optionTop - 50;

            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                    x > dialogX - 50 && x < dialogX + 50 &&
                    y > optionBottom && y < optionTop) {
                // Handle gift selection
                String selectedGift = GIFT_OPTIONS[selectedNPCIndex][i];
                processGiftSelection(player, npc, selectedGift);
                return;
            }
        }
    }

    private static void processGiftSelection(Player player, NPC npc, String giftName) {
        boolean success = false;

        if (selectedNPCIndex == 0) { // Alice
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
        } else { // Bob
            if (giftName.equals("1000$")) {
                if (player.getMoney() >= 1000) { // Fixed: Check for 1000 instead of 100
                    player.addMoney(-1000); // Fixed: Deduct 1000 instead of 100
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

    private static void handleQuestInteraction(Player player, NPC npc, Quest quest) {
        // This is where you would implement what happens when a player interacts with a quest
        // For example: start the quest, complete the quest, show quest details, etc.
        if (quest.isCompleted()) {
            // Quest is already completed
            System.out.println("Quest '" + quest.getTitle() + "' is already completed!");
        } else {
            // Start or progress the quest
            System.out.println("Interacting with quest: " + quest.getTitle());
            // Here you would implement quest logic based on your game design
        }
    }

    public static void dispose() {
        if (font != null) {
            font.dispose();
        }
        if (errorFont != null) {
            errorFont.dispose();
        }
    }
}