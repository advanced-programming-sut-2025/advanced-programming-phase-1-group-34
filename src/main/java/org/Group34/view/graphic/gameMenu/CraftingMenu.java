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
import org.Group34.model.gameAssetManagers.ProcessorAssetManager;
import org.Group34.model.gameAssetManagers.ToolAssetManager;
import org.Group34.model.items.crafting.ProcessorCraft;

import java.util.Map;

public class CraftingMenu {
    private final static Sprite chest = new Sprite(GameMenuAssetManager.getChest());
    private final static Sprite board = new Sprite(GameMenuAssetManager.getBoard());
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
    private final static Sprite greenRect = new Sprite(GameMenuAssetManager.getGreenRect());
    private final static Sprite rightIcon = new Sprite(GameMenuAssetManager.getRightIcon());
    private final static Sprite leftIcon = new Sprite(GameMenuAssetManager.getLeftIcon());
    private final static Sprite bigBoard = new Sprite(GameMenuAssetManager.getBigBoard());
    private final static Sprite lockIcon = new Sprite(GameMenuAssetManager.getLockIcon());
    private final static Sprite unlockIcon = new Sprite(GameMenuAssetManager.getLearnIcon());
    private final static Sprite createIcon = new Sprite(GameMenuAssetManager.getCraftIcon()); // Changed from cookIcon
    private static int scrollNumber = 0;
    private static ProcessorCraft currentProcessor = null; // Changed from currentRecipe
    private static boolean[] unlockedProcessors; // Changed from unlockedRecipes
    private static final int UNLOCK_COST = 1000;
    private static String statusMessage = null;
    private static float statusMessageTimer = 0;
    private static BitmapFont statusFont = new BitmapFont();

    static {
        // Initialize unlocked processors array (first two unlocked by default)
        ProcessorCraft[] allProcessors = ProcessorCraft.values();
        unlockedProcessors = new boolean[allProcessors.length];
        unlockedProcessors[0] = true; // CHARCOAL_KILN
        unlockedProcessors[1] = true; // FURNACE

        board.setSize(chest.getWidth(), chest.getHeight());
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
        greenRect.setSize(45, 45);
        lockIcon.setSize(30, 30);
        unlockIcon.setSize(30, 30);
        createIcon.setSize(100, 100); // Changed from cookIcon
        statusFont.setColor(Color.RED);
        statusFont.getData().setScale(1.0f);
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera, GameController gameController) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;
        drawBoard(batch, x, y);
        ProcessorCraft[] processors = ProcessorCraft.values();
        int index = 0;
        BitmapFont font = new BitmapFont();
        while (index + (12 * scrollNumber) < processors.length && index < 36) {
            ProcessorCraft processor = processors[index + (12 * scrollNumber)];
            Sprite sprite = new Sprite(getTexture(processor));
            sprite.setPosition(x + 37 + (48 * index), y + 137 - (index / 12 * 50));
            font.setColor(Color.BLACK);
            if (index > 11 && index < 24) {
                sprite.setPosition(x + 37 + (48 * index) - 576, y + 137 - (index / 12 * 50));
            } else if (index > 23) {
                sprite.setPosition(x + 37 + (48 * index) - 1152, y + 137 - (index / 12 * 50));
            }
            if (processor.equals(currentProcessor)) {
                greenRect.setPosition(sprite.getX() - 6, sprite.getY() - 8);
                greenRect.draw(batch);
            }
            font.getData().setScale(0.5f);
            // Check if processor is unlocked
            int processorIndex = index + (12 * scrollNumber);
            if (unlockedProcessors[processorIndex]) {
                // Draw normally if unlocked
                font.draw(batch, processor.getName(), sprite.getX() - 5, sprite.getY() - 3);
                sprite.setSize(35, 33);
                sprite.draw(batch);
            } else {
                // Draw in gray if locked
                font.setColor(Color.GRAY);
                font.draw(batch, "???", sprite.getX() - 5, sprite.getY() - 3);
                sprite.setColor(Color.GRAY);
                sprite.setSize(35, 33);
                sprite.draw(batch);
                sprite.setColor(Color.WHITE);
                // Draw lock icon
                lockIcon.setPosition(sprite.getX() + 5, sprite.getY() + 5);
                lockIcon.draw(batch);
            }
            index++;
        }
        fullBoard(player, processors, x, y, batch, gameController);
        handleInput(player, processors);
        // Draw status message if active
        if (statusMessage != null && statusMessageTimer > 0) {
            statusFont.draw(batch, statusMessage, x + chest.getWidth() / 2 - statusFont.getRegion().getRegionWidth() / 2, y + 50);
            statusMessageTimer -= Gdx.graphics.getDeltaTime();
            if (statusMessageTimer <= 0) {
                statusMessage = null;
            }
        }
    }

    private static void drawBoard(SpriteBatch batch, float x, float y) {
        chest.setPosition(x, y);
        chest.draw(batch);
        board.setSize(chest.getWidth(), chest.getHeight());
        board.setPosition(x, y - 200);
        board.draw(batch);
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
        smallBoard.setPosition(x + 338, y + 203);
        smallBoard.draw(batch);
        smallBoard.setPosition(x + 382, y + 210);
        smallBoard.draw(batch);
        smallBoard.setPosition(x + 426, y + 210);
        smallBoard.draw(batch);
        smallBoard.setPosition(x + 470, y + 210);
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
        animalIcon.setPosition(x + 294 + 10, y + 210 + 5);
        animalIcon.draw(batch);
        craftingIcon.setPosition(x + 338 + 10, y + 203 + 5);
        craftingIcon.draw(batch);
        cookingIcon.setPosition(x + 382 + 10, y + 210 + 5);
        cookingIcon.draw(batch);
        fridgeIcon.setPosition(x + 426 + 15, y + 210 + 3);
        fridgeIcon.draw(batch);
        reactionIcon.setPosition(x + 470 + 13, y + 210 + 7);
        reactionIcon.draw(batch);
        scoreboardIcon.setPosition(x + 514 + 10, y + 210 + 5);
        scoreboardIcon.draw(batch);
        exitIcon.setPosition(x + 608, y + 190);
        exitIcon.draw(batch);
    }

    private static void handleInput(Player player, ProcessorCraft[] processors) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 324 && x < 395 && y < 110 && y > 30) {
            player.setCurrentGameMenu("inventory");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 397 && x < 468 && y < 110 && y > 30) {
            player.setCurrentGameMenu("skill");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 470 && x < 541 && y < 110 && y > 30) {
            player.setCurrentGameMenu("social");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 542 && x < 613 && y < 110 && y > 30) {
            player.setCurrentGameMenu("map");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 615 && x < 686 && y < 110 && y > 30) {
            player.setCurrentGameMenu("npc");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 688 && x < 759 && y < 110 && y > 30) {
            player.setCurrentGameMenu("setting");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 761 && x < 832 && y < 110 && y > 30) {
            player.setCurrentGameMenu("animal");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 834 && x < 905 && y < 110 && y > 30) {
            player.setCurrentGameMenu("crafting");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 907 && x < 978 && y < 110 && y > 30) {
            player.setCurrentGameMenu("cooking");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 980 && x < 1051 && y < 110 && y > 30) {
            player.setCurrentGameMenu("fridge");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1053 && x < 1124 && y < 110 && y > 30) {
            player.setCurrentGameMenu("reaction");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1126 && x < 1197 && y < 110 && y > 30) {
            player.setCurrentGameMenu("scoreboard");
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1283 && x < 1342 && y < 150 && y > 82) {
            player.setCurrentGameMenu(null);
            scrollNumber = 0;
            currentProcessor = null;
            statusMessage = null;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            scrollNumber = Math.max(0, scrollNumber - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            scrollNumber = Math.min(processors.length / 12, scrollNumber + 1);
        }

        // Handle unlock/create button
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1005 && x < 1170 && y > 588 && y < 780) {
            if (currentProcessor != null) {
                int processorIndex = -1;
                for (int i = 0; i < processors.length; i++) {
                    if (processors[i] == currentProcessor) {
                        processorIndex = i;
                        break;
                    }
                }
                if (processorIndex != -1) {
                    if (!unlockedProcessors[processorIndex]) {
                        // Unlock the processor
                        if (player.getMoney() >= UNLOCK_COST) {
                            player.setMoney(player.getMoney() - UNLOCK_COST);
                            unlockedProcessors[processorIndex] = true;
                        } else {
                            statusMessage = "Not enough money to learn this processor!";
                            statusMessageTimer = 3.0f;
                        }
                    } else {
                        // Create the processor
                        boolean canCreate = true;
                        StringBuilder missingIngredients = new StringBuilder();
                        for (Map.Entry<org.Group34.model.items.Item, Integer> entry : currentProcessor.getIngredients().entrySet()) {
                            int required = entry.getValue();
                            int available = player.getAmountOfItem(entry.getKey());
                            if (entry.getKey().getName() == null) {
                                canCreate = true;
                            } else if (available < required) {
                                canCreate = false;
                                missingIngredients.append(entry.getKey().getName())
                                        .append(" (need ")
                                        .append(required)
                                        .append(", have ")
                                        .append(available)
                                        .append("), ");
                            }
                        }
                        if (canCreate) {
                            for (Map.Entry<org.Group34.model.items.Item, Integer> entry : currentProcessor.getIngredients().entrySet()) {
                                player.removeFromInventory(entry.getKey(), entry.getValue());
                            }
                            player.addToInventory(currentProcessor, 1);
                            statusMessage = "Created!";
                            statusMessageTimer = 3.0f;
                        } else {
                            if (missingIngredients.length() > 0) {
                                missingIngredients.setLength(missingIngredients.length() - 2);
                            }
                            statusMessage = "Missing ingredients: " + missingIngredients.toString();
                            statusMessageTimer = 3.0f;
                        }
                    }
                }
            }
        }

        int currentIndex = -1;
        if (currentProcessor != null) {
            for (int i = 0; i < processors.length; i++) {
                if (processors[i] == currentProcessor) {
                    currentIndex = i;
                    break;
                }
            }
        }
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 462 && x < 512 && y > 702 && y < 759) {
            if (currentIndex == -1) {
                currentProcessor = processors[processors.length - 1];
            } else {
                currentProcessor = processors[(currentIndex - 1 + processors.length) % processors.length];
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 520 && x < 570 && y > 702 && y < 759) {
            if (currentIndex == -1) {
                currentProcessor = processors[0];
            } else {
                currentProcessor = processors[(currentIndex + 1) % processors.length];
            }
        }
    }

    private static void fullBoard(Player player, ProcessorCraft[] processors, float x, float y, SpriteBatch batch, GameController gameController) {
        drawCreateButton(x, y, batch, player); // Changed from drawUnlockCookButton
        drawSelectState(batch, x, y, player);
        drawProcessorInfoState(batch, x, y, player); // Changed from drawRecipeInfoState
    }

    private static void drawCreateButton(float x, float y, SpriteBatch batch, Player player) { // Changed from drawUnlockCookButton
        int processorIndex = -1;
        if (currentProcessor != null) {
            ProcessorCraft[] processors = ProcessorCraft.values();
            for (int i = 0; i < processors.length; i++) {
                if (processors[i] == currentProcessor) {
                    processorIndex = i;
                    break;
                }
            }
        }
        if (processorIndex != -1 && !unlockedProcessors[processorIndex]) {
            // Draw unlock button
            unlockIcon.setPosition(x + 450, y - 130);
            unlockIcon.setSize(75, 75);
            unlockIcon.draw(batch);
            BitmapFont font = new BitmapFont();
            font.setColor(Color.BLACK);
            font.getData().setScale(0.8f);
            font.draw(batch, "Learn: " + UNLOCK_COST, x + 460, y - 155);
        } else {
            // Draw create button
            createIcon.setPosition(x + 440, y - 145);
            createIcon.setSize(100, 100);
            createIcon.draw(batch);
        }
    }

    private static void drawSelectState(SpriteBatch batch, float x, float y, Player player) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Select Processor", x + 20, y - 20); // Changed text
        board.setSize(chest.getWidth() / 4, chest.getHeight() / 4);
        board.setPosition(x + 30, y - 100);
        board.draw(batch);
        font.getData().setScale(0.8f);
        if (currentProcessor != null) {
            int processorIndex = -1;
            ProcessorCraft[] processors = ProcessorCraft.values();
            for (int i = 0; i < processors.length; i++) {
                if (processors[i] == currentProcessor) {
                    processorIndex = i;
                    break;
                }
            }
            if (processorIndex != -1 && unlockedProcessors[processorIndex]) {
                Sprite sprite = new Sprite(getTexture(currentProcessor));
                sprite.setSize(48, 48);
                sprite.setPosition(x + 33, y - 97);
                sprite.draw(batch);
                font.draw(batch, currentProcessor.getName(), x + 85, y - 67);
            } else {
                font.draw(batch, "???", x + 33, y - 67);
            }
        }
        leftIcon.setPosition(x + 115, y - 135);
        leftIcon.setSize(30, 30);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 150, y - 135);
        rightIcon.setSize(30, 30);
        rightIcon.draw(batch);
    }

    private static void drawProcessorInfoState(SpriteBatch batch, float x, float y, Player player) { // Changed from drawRecipeInfoState
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Processor Info", x + 220, y - 20); // Changed text
        board.setSize(170, 20);
        board.setPosition(x + 230, y - 65);
        board.draw(batch);
        bigBoard.setSize(170, 120);
        bigBoard.setPosition(x + 230, y - 180);
        bigBoard.draw(batch);
        if (currentProcessor != null) {
            int processorIndex = -1;
            ProcessorCraft[] processors = ProcessorCraft.values();
            for (int i = 0; i < processors.length; i++) {
                if (processors[i] == currentProcessor) {
                    processorIndex = i;
                    break;
                }
            }
            font.getData().setScale(0.8f);
            if (processorIndex != -1 && unlockedProcessors[processorIndex]) {
                font.draw(batch, currentProcessor.getName(), x + 240, y - 47);
                font.getData().setScale(0.5f);
                // Draw ingredients
                int yPos = (int) (y - 65);
                font.draw(batch, "Ingredients:", x + 240, yPos);
                yPos -= 15;
                for (Map.Entry<org.Group34.model.items.Item, Integer> entry : currentProcessor.getIngredients().entrySet()) {
                    String ingredientName = entry.getKey().getName();
                    int amount = entry.getValue();
                    int available = player.getAmountOfItem(entry.getKey());
                    // Check if player has enough of this ingredient
                    if (available >= amount) {
                        font.setColor(Color.BLACK);
                    } else {
                        font.setColor(Color.RED);
                    }
                    font.draw(batch, "- " + ingredientName + " x" + amount + " (have " + available + ")", x + 240, yPos);
                    yPos -= 15;
                }
                // Reset color
                font.setColor(Color.BLACK);
                // Draw price (processors don't have energy)
                yPos -= 5;
                font.draw(batch, "Price: " + currentProcessor.getPrice(), x + 240, yPos);
            } else {
                font.draw(batch, "???", x + 240, y - 47);
                font.getData().setScale(0.5f);
                font.draw(batch, "Learn this processor to see details", x + 240, y - 65);
            }
        }
    }

    private static com.badlogic.gdx.graphics.Texture getTexture(ProcessorCraft processor) {
        return switch (processor) {
            case CHARCOAL_KILN -> ProcessorAssetManager.charcoalKiln;
            case FURNACE -> ProcessorAssetManager.furnace;
            case BEE_HOUSE -> ProcessorAssetManager.beeHouse;
            case CHEESE_PRESS -> ProcessorAssetManager.cheesePress;
            case KEG -> ProcessorAssetManager.keg;
            case LOOM -> ProcessorAssetManager.loom;
            case MAYONNAISE_MACHINE -> ProcessorAssetManager.mayonnaiseMachine;
            case OIL_MAKER -> ProcessorAssetManager.oilMaker;
            case PRESERVES_JAR -> ProcessorAssetManager.preservesJar;
            case DEHYDRATOR -> ProcessorAssetManager.dehydrator;
            case FISH_SMOKER -> ProcessorAssetManager.fishSmoker;
            default -> ProcessorAssetManager.cheesePress;
        };
    }
}