package org.Group34.view.graphic.gameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import org.Group34.model.Result;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.buildings.AnimalsBuilding;
import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.gameAssetManagers.*;
import org.Group34.controller.AnimalController;
import org.Group34.controller.AnimalBuildingController;
import org.Group34.model.map.Space;

public class AnimalMenu {
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

    private static BitmapFont font;
    private static BitmapFont errorFont;
    private static BitmapFont successFont;
    private static String errorMessage = "";
    private static String successMessage = "";
    private static float messageTimer = 0;
    private static final float MESSAGE_DURATION = 3.0f;
    private static AnimalController animalController;
    private static AnimalBuildingController buildingController;
    private static Space currentSpace;

    // Menu state
    private static MenuState currentMenuState = MenuState.MAIN;
    private static BarnType selectedBuildingType = null;
    private static AnimalType selectedAnimalType = null;
    private static boolean isPlacingBuilding = false;

    private enum MenuState {
        MAIN,
        BUILDING_SELECT,
        ANIMAL_SELECT
    }

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

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(1.0f);

        errorFont = new BitmapFont();
        errorFont.setColor(Color.RED);
        errorFont.getData().setScale(1.0f);

        successFont = new BitmapFont();
        successFont.setColor(Color.GREEN);
        successFont.getData().setScale(1.2f);
    }

    public static void initialize(AnimalController animalCtrl, AnimalBuildingController buildingCtrl, Space space) {
        animalController = animalCtrl;
        buildingController = buildingCtrl;
        currentSpace = space;
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;

        drawBoard(batch, x, y);

        if (currentMenuState == MenuState.MAIN) {
            drawMainMenu(batch, x, y, player);
        } else if (currentMenuState == MenuState.BUILDING_SELECT) {
            drawBuildingMenu(batch, x, y, player);
        } else if (currentMenuState == MenuState.ANIMAL_SELECT) {
            drawAnimalMenu(batch, x, y, player);
        }

        handleInput(player, camera);

        if (messageTimer > 0) {
            float alpha = Math.min(1.0f, messageTimer);
            if (messageTimer < 1.0f) {
                alpha = messageTimer;
            }

            if (!errorMessage.isEmpty()) {
                errorFont.setColor(1, 0, 0, alpha);
                errorFont.draw(batch, errorMessage, camera.position.x - 150, camera.position.y + 10);
            } else if (!successMessage.isEmpty()) {
                successFont.setColor(0, 0.8f, 0, alpha);
                successFont.draw(batch, successMessage, camera.position.x - 150, camera.position.y + 10);
            }

            messageTimer -= Gdx.graphics.getDeltaTime();
            if (messageTimer <= 0) {
                errorMessage = "";
                successMessage = "";
            }
        }

        // Show placement hint if in building placement mode
        if (isPlacingBuilding) {
            font.setColor(Color.YELLOW);
            font.draw(batch, "Click on the map to place the building", camera.position.x - 150, camera.position.y + 50);
            font.draw(batch, "Press ESC to cancel", camera.position.x - 150, camera.position.y + 30);
            font.setColor(Color.BLACK);
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
        smallBoard.setPosition(x + 206, y + 210);
        smallBoard.draw(batch);
        smallBoard.setPosition(x + 250, y + 210);
        smallBoard.draw(batch);
        smallBoard.setPosition(x + 294, y + 203);
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
        animalIcon.setPosition(x + 294 + 10, y + 203 + 5);
        animalIcon.draw(batch);
    }

    private static void drawMainMenu(SpriteBatch batch, float x, float y, Player player) {
        float startY = y + 100;
        float leftX = x + 65;

        Sprite optionBoard = new Sprite(GameMenuAssetManager.getBigBoard());
        optionBoard.setSize(200, 200);

        // Build Animal Building option
        optionBoard.setPosition(leftX, startY - 175);
        optionBoard.draw(batch);
        batch.draw(AnimalAssetManager.barn, leftX + 45, startY - 125, 100, 140);
        font.draw(batch, "Build building", leftX + 50, startY - 140);

        // Buy Animal option
        optionBoard.setPosition(leftX + 300, startY - 175);
        optionBoard.draw(batch);
        batch.draw(AnimalAssetManager.chicken, leftX + 345, startY - 110, 100, 90);
        font.draw(batch, "Buy animals", leftX + 360, startY - 130);
    }

    private static void drawBuildingMenu(SpriteBatch batch, float x, float y, Player player) {
        float startY = y + 100;
        float leftX = x + 30;

        // Title
        font.draw(batch, "Select Building Type", leftX, startY + 50);

        // Available buildings
        float buildingY = startY + 20;
        for (BarnType buildingType : BarnType.values()) {
            // Check if player can afford this building
            boolean canAfford = player.getMoney() >= buildingType.getPrice() &&
                    player.getAmountOfItem(org.Group34.model.items.crafting.Ingredient.WOOD) >= buildingType.getWoodCost() &&
                    player.getAmountOfItem(org.Group34.model.items.crafting.Ingredient.STONE) >= buildingType.getStoneCost();

            // Draw building info
            if (canAfford) {
                font.setColor(Color.BLACK);
            } else {
                font.setColor(Color.RED);
            }

            font.draw(batch, buildingType.getName() + " - Cost: " + buildingType.getPrice() + "g, " +
                            buildingType.getWoodCost() + " Wood, " + buildingType.getStoneCost() + " Stone",
                    leftX + 20, buildingY);

            // Select button
            if (canAfford) {
                smallBoard.setPosition(leftX + 500, buildingY - 15);
                smallBoard.draw(batch);
                font.setColor(Color.BLACK);
                font.draw(batch, "Select", leftX + 515, buildingY);
            }

            buildingY -= 40;
        }

        // Back button
        smallBoard.setPosition(leftX, buildingY - 15);
        smallBoard.draw(batch);
        font.draw(batch, "Back", leftX + 15, buildingY - 5);

        font.setColor(Color.BLACK); // Reset color
    }

    private static void drawAnimalMenu(SpriteBatch batch, float x, float y, Player player) {
        float startY = y + 100;
        float leftX = x + 30;

        // Title
        font.draw(batch, "Select Animal to Buy", leftX, startY + 50);

        // Available animals for purchase
        float animalY = startY + 20;
        for (AnimalType animalType : AnimalType.values()) {
            // Check if player has required building
            boolean hasBuilding = false;
            if (buildingController != null) {
                for (AnimalsBuilding building : buildingController.getBuildings()) {
                    if (BarnType.valueOf(building.type) == animalType.getRequiredBuilding() &&
                            building.getAnimalCount() < building.capacity) {
                        hasBuilding = true;
                        break;
                    }
                }
            }

            // Check if player can afford this animal
            boolean canAfford = player.getMoney() >= animalType.getPrice();

            // Draw animal info
            if (hasBuilding && canAfford) {
                font.setColor(Color.BLACK);
            } else {
                font.setColor(Color.RED);
            }

            font.draw(batch, animalType.getName() + " - Price: " + animalType.getPrice() + "g", leftX + 20, animalY);

            if (!hasBuilding) {
                font.draw(batch, "(Need " + animalType.getRequiredBuilding().getName() + ")", leftX + 250, animalY);
            }

            // Buy button
            if (hasBuilding && canAfford) {
                smallBoard.setPosition(leftX + 400, animalY - 15);
                smallBoard.draw(batch);
                font.setColor(Color.BLACK);
                font.draw(batch, "Buy", leftX + 415, animalY);
            }

            animalY -= 30;
        }

        // Back button
        smallBoard.setPosition(leftX, animalY - 15);
        smallBoard.draw(batch);
        font.draw(batch, "Back", leftX + 15, animalY - 5);

        font.setColor(Color.BLACK); // Reset color
    }

    public static void handleInput(Player player, OrthographicCamera camera) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        // Menu navigation
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 324 && x < 395 && y < 110 && y > 30) {
            player.setCurrentGameMenu("inventory");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 397 && x < 468 && y < 110 && y > 30) {
            player.setCurrentGameMenu("skill");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 470 && x < 541 && y < 110 && y > 30) {
            player.setCurrentGameMenu("social");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 542 && x < 613 && y < 110 && y > 30) {
            player.setCurrentGameMenu("map");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 615 && x < 686 && y < 110 && y > 30) {
            player.setCurrentGameMenu("npc");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 688 && x < 759 && y < 110 && y > 30) {
            player.setCurrentGameMenu("setting");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 761 && x < 832 && y < 110 && y > 30) {
            // Already in animal menu
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1283 && x < 1342 && y < 150 && y > 82) {
            player.setCurrentGameMenu(null);
            resetMenuState();
        }

        // Handle input based on current menu state
        if (currentMenuState == MenuState.MAIN) {
            handleMainMenuInput(player, x, y);
        } else if (currentMenuState == MenuState.BUILDING_SELECT) {
            handleBuildingMenuInput(player, x, y);
        } else if (currentMenuState == MenuState.ANIMAL_SELECT) {
            handleAnimalMenuInput(player, x, y, camera);
        }

        // Handle building placement on map
        if (isPlacingBuilding && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            // Convert screen coordinates to world coordinates
            int worldX = (int) (camera.position.x - Gdx.graphics.getWidth() / 2 + x);
            int worldY = (int) (camera.position.y - Gdx.graphics.getHeight() / 2 + (Gdx.graphics.getHeight() - y));

            // Convert world coordinates to tile coordinates
            int tileX = worldX / 32; // Assuming TILE_SIZE is 32
            int tileY = worldY / 32;

            placeBuilding(player, tileX, tileY);
        }

        // Cancel building placement with ESC key
        if (isPlacingBuilding && Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            cancelPlacingBuilding();
        }
    }

    private static void handleMainMenuInput(Player player, int x, int y) {
        float buttonX = 390;
        float buildY = 400;
        float buyY = 280;

        // Build Animal Building button
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                x > buttonX && x < buttonX + 300 &&
                y > buildY - 80 && y < buildY) {
            currentMenuState = MenuState.BUILDING_SELECT;
        }

        // Buy Animal button
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                x > buttonX && x < buttonX + 300 &&
                y > buyY - 80 && y < buyY) {
            currentMenuState = MenuState.ANIMAL_SELECT;
        }
    }

    private static void handleBuildingMenuInput(Player player, int x, int y) {
        float buttonX = 540;
        float startY = 320;
        float buttonY = startY;

        // Building selection buttons
        for (BarnType buildingType : BarnType.values()) {
            boolean canAfford = player.getMoney() >= buildingType.getPrice() &&
                    player.getAmountOfItem(org.Group34.model.items.crafting.Ingredient.WOOD) >= buildingType.getWoodCost() &&
                    player.getAmountOfItem(org.Group34.model.items.crafting.Ingredient.STONE) >= buildingType.getStoneCost();

            if (canAfford) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                        x > buttonX && x < buttonX + 60 &&
                        y > buttonY - 20 && y < buttonY + 10) {

                    selectedBuildingType = buildingType;
                    isPlacingBuilding = true;
                    successMessage = "Click on the map to place " + buildingType.getName();
                    messageTimer = MESSAGE_DURATION;
                    return;
                }
            }
            buttonY -= 40;
        }

        // Back button
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                x > buttonX - 100 && x < buttonX - 40 &&
                y > buttonY - 20 && y < buttonY + 10) {
            currentMenuState = MenuState.MAIN;
        }
    }

    private static void handleAnimalMenuInput(Player player, int x, int y, OrthographicCamera camera) {
        float buttonX = 540;
        float startY = 320;
        float buttonY = startY;

        // Animal purchase buttons
        for (AnimalType animalType : AnimalType.values()) {
            // Check if player has required building
            boolean hasBuilding = false;
            if (buildingController != null) {
                for (AnimalsBuilding building : buildingController.getBuildings()) {
                    if (BarnType.valueOf(building.type) == animalType.getRequiredBuilding() &&
                            building.getAnimalCount() < building.capacity) {
                        hasBuilding = true;
                        break;
                    }
                }
            }

            boolean canAfford = player.getMoney() >= animalType.getPrice();

            if (hasBuilding && canAfford) {
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                        x > buttonX && x < buttonX + 60 &&
                        y > buttonY - 20 && y < buttonY + 10) {

                    buyAnimal(player, animalType, camera);
                    return;
                }
            }
            buttonY -= 30;
        }

        // Back button
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                x > buttonX - 100 && x < buttonX - 40 &&
                y > buttonY - 20 && y < buttonY + 10) {
            currentMenuState = MenuState.MAIN;
        }
    }

    private static void placeBuilding(Player player, int tileX, int tileY) {
        if (buildingController == null || currentSpace == null) {
            errorMessage = "Building controller or space not initialized!";
            messageTimer = MESSAGE_DURATION;
            isPlacingBuilding = false;
            return;
        }

        if (selectedBuildingType == null) {
            errorMessage = "No building type selected!";
            messageTimer = MESSAGE_DURATION;
            isPlacingBuilding = false;
            return;
        }

        // Try to build the building
        Result result = buildingController.buildBuilding(
                selectedBuildingType.name(),
                tileX,
                tileY,
                player,
                currentSpace
        );

        if (result.success()) {
            successMessage = result.message();
            messageTimer = MESSAGE_DURATION;

            // Reset state
            isPlacingBuilding = false;
            selectedBuildingType = null;
            currentMenuState = MenuState.MAIN;
        } else {
            errorMessage = result.message();
            messageTimer = MESSAGE_DURATION;
            isPlacingBuilding = false;
        }
    }

    private static void buyAnimal(Player player, AnimalType animalType, OrthographicCamera camera) {
        if (animalController == null) {
            errorMessage = "Animal controller not initialized!";
            messageTimer = MESSAGE_DURATION;
            return;
        }

        if (player.getMoney() < animalType.getPrice()) {
            errorMessage = "Not enough money! Need " + animalType.getPrice() + "g";
            messageTimer = MESSAGE_DURATION;
            return;
        }

        // Generate unique name for the animal
        String animalName = animalType.getName() + "_" + System.currentTimeMillis() % 1000;

        // Try to add the animal
        boolean success = animalController.addAnimal(animalName, animalType);

        if (success) {
            player.addMoney(-animalType.getPrice());
            successMessage = "Bought " + animalType.getName() + " for " + animalType.getPrice() + "g!";
            messageTimer = MESSAGE_DURATION;

            // Place animal near player
            Animal newAnimal = animalController.getAnimal(animalName);
            if (newAnimal != null) {
                // Place animal near player's current position
                int playerX = (int) camera.position.x;
                int playerY = (int) camera.position.y;

                // Find a suitable nearby position
                for (int dx = -2; dx <= 2; dx++) {
                    for (int dy = -2; dy <= 2; dy++) {
                        if (dx == 0 && dy == 0) continue; // Skip player's position

                        int newX = playerX + dx * 50;
                        int newY = playerY + dy * 50;

                        // Set animal position
                        newAnimal.setX(newX);
                        newAnimal.setY(newY);

                        // Place animal on map (assuming space is accessible)
                        // This would require access to the game's space system
                        // For now, we'll just set the coordinates
                        break;
                    }
                }
            }
        } else {
            errorMessage = "Failed to buy " + animalType.getName() + "! Check building capacity.";
            messageTimer = MESSAGE_DURATION;
        }
    }

    private static void resetMenuState() {
        currentMenuState = MenuState.MAIN;
        selectedBuildingType = null;
        selectedAnimalType = null;
        isPlacingBuilding = false;
    }

    public static boolean isPlacingBuilding() {
        return isPlacingBuilding;
    }

    public static void cancelPlacingBuilding() {
        isPlacingBuilding = false;
        selectedBuildingType = null;
        currentMenuState = MenuState.MAIN;
        errorMessage = "Building placement cancelled";
        messageTimer = MESSAGE_DURATION;
    }

    public static void dispose() {
        if (font != null) {
            font.dispose();
        }
        if (errorFont != null) {
            errorFont.dispose();
        }
        if (successFont != null) {
            successFont.dispose();
        }
    }
}