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
import java.util.ArrayList;
import java.util.List;

public class AnimalMenu {
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
    private final static Sprite greenRect = new Sprite(GameMenuAssetManager.getGreenRect());
    private final static Sprite rightIcon = new Sprite(GameMenuAssetManager.getRightIcon());
    private final static Sprite leftIcon = new Sprite(GameMenuAssetManager.getLeftIcon());
    private final static Sprite bigBoard = new Sprite(GameMenuAssetManager.getBigBoard());
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
    private static int scrollNumber = 0;
    private static int infoNumber = 0;

    private enum MenuState {
        MAIN,
        BUILDING_SELECT,
        ANIMAL_SELECT
    }

    static {
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
        greenRect.setSize(45, 45);
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
        if (currentMenuState != MenuState.MAIN) {
            fullBoard(player, x, y, batch);
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
        if (currentMenuState == MenuState.MAIN) {
            // Use bigBoard for MAIN menu background
            bigBoard.setSize(chest.getWidth(), 413);
            bigBoard.setPosition(x, y - 200);
            bigBoard.draw(batch);
        } else {
            // Use chest and board for other menus
            chest.setPosition(x, y);
            chest.draw(batch);
            board.setSize(chest.getWidth(), chest.getHeight());
            board.setPosition(x, y - 200);
            board.draw(batch);
        }

        // Draw top menu icons
        smallBoard.setPosition(x + 30, y + 203);
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
        smallBoard.setPosition(x + 294, y + 210);
        smallBoard.draw(batch);
        inventorySymbol.setPosition(x + 30 + 5, y + 203 + 2);
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
        animalIcon.setPosition(x + 294 + 10, y + 210 + 5);
        animalIcon.draw(batch);
        exitIcon.setPosition(x + 608, y + 190);
        exitIcon.draw(batch);
    }

    private static void drawMainMenu(SpriteBatch batch, float x, float y, Player player) {
        float startY = y + 100;
        float leftX = x + 65;

        // Build Animal Building option
        Sprite optionBoard = new Sprite(GameMenuAssetManager.getBigBoard());
        optionBoard.setSize(200, 200);
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
        int index = 0;
        List<BarnType> buildings = new ArrayList<>(java.util.Arrays.asList(BarnType.values()));
        while (index + (12 * scrollNumber) < buildings.size() && index < 36) {
            BarnType buildingType = buildings.get(index + (12 * scrollNumber));
            // Check if player can afford this building
            boolean canAfford = player.getMoney() >= buildingType.getPrice() &&
                    player.getAmountOfItem(org.Group34.model.items.crafting.Ingredient.WOOD) >= buildingType.getWoodCost() &&
                    player.getAmountOfItem(org.Group34.model.items.crafting.Ingredient.STONE) >= buildingType.getStoneCost();
            // Draw building
            Sprite buildingSprite;
            if (buildingType.name().contains("COOP")) {
                buildingSprite = new Sprite(AnimalAssetManager.coop);
            } else {
                buildingSprite = new Sprite(AnimalAssetManager.barn);
            }
            buildingSprite.setPosition(x + 37 + (48 * index), y + 137 - (index / 12 * 50));
            if (index > 11 && index < 24) {
                buildingSprite.setPosition(x + 37 + (48 * index) - 576, y + 137 - (index / 12 * 50));
            } else if (index > 23) {
                buildingSprite.setPosition(x + 37 + (48 * index) - 1152, y + 137 - (index / 12 * 50));
            }
            if (buildingType.equals(selectedBuildingType)) {
                greenRect.setPosition(buildingSprite.getX() - 6, buildingSprite.getY() - 8);
                greenRect.draw(batch);
            }
            font.getData().setScale(0.5f);
            font.draw(batch, buildingType.getName(), buildingSprite.getX() - 5, buildingSprite.getY() - 3);
            buildingSprite.setSize(35, 33);
            buildingSprite.draw(batch);
            index++;
        }
    }

    private static void drawAnimalMenu(SpriteBatch batch, float x, float y, Player player) {
        int index = 0;
        List<AnimalType> animals = new ArrayList<>(java.util.Arrays.asList(AnimalType.values()));
        while (index + (12 * scrollNumber) < animals.size() && index < 36) {
            AnimalType animalType = animals.get(index + (12 * scrollNumber));
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
            // Draw animal
            Sprite animalSprite = getAnimalSprite(animalType);
            animalSprite.setPosition(x + 37 + (48 * index), y + 137 - (index / 12 * 50));
            if (index > 11 && index < 24) {
                animalSprite.setPosition(x + 37 + (48 * index) - 576, y + 137 - (index / 12 * 50));
            } else if (index > 23) {
                animalSprite.setPosition(x + 37 + (48 * index) - 1152, y + 137 - (index / 12 * 50));
            }
            if (animalType.equals(selectedAnimalType)) {
                greenRect.setPosition(animalSprite.getX() - 6, animalSprite.getY() - 8);
                greenRect.draw(batch);
            }
            font.getData().setScale(0.5f);
            font.draw(batch, animalType.getName(), animalSprite.getX() - 5, animalSprite.getY() - 3);
            animalSprite.setSize(35, 33);
            animalSprite.draw(batch);
            index++;
        }
    }

    private static Sprite getAnimalSprite(AnimalType animalType) {
        switch (animalType) {
            case CHICKEN:
                return new Sprite(AnimalAssetManager.chicken);
            case DUCK:
                return new Sprite(AnimalAssetManager.duck);
            case RABBIT:
                return new Sprite(AnimalAssetManager.rabbit);
            case DINOSAUR:
                return new Sprite(AnimalAssetManager.dinosaur);
            case COW:
                return new Sprite(AnimalAssetManager.cow);
            case GOAT:
                return new Sprite(AnimalAssetManager.goat);
            case SHEEP:
                return new Sprite(AnimalAssetManager.sheep);
            case PIG:
                return new Sprite(AnimalAssetManager.pig);
            default:
                return new Sprite(AnimalAssetManager.chicken);
        }
    }

    private static void fullBoard(Player player, float x, float y, SpriteBatch batch) {
        drawSelectState(batch, x, y, player);
        drawInfoState(batch, x, y, player);
        drawBackButton(batch, x, y);
    }

    private static void drawSelectState(SpriteBatch batch, float x, float y, Player player) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Selected Item", x + 20, y - 20);
        board.setSize(chest.getWidth() / 4, chest.getHeight() / 4);
        board.setPosition(x + 30, y - 100);
        board.draw(batch);
        font.getData().setScale(0.8f);
        if (currentMenuState == MenuState.BUILDING_SELECT && selectedBuildingType != null) {
            Sprite buildingSprite;
            if (selectedBuildingType.name().contains("COOP")) {
                buildingSprite = new Sprite(AnimalAssetManager.coop);
            } else {
                buildingSprite = new Sprite(AnimalAssetManager.barn);
            }
            buildingSprite.setSize(48, 48);
            buildingSprite.setPosition(x + 33, y - 97);
            buildingSprite.draw(batch);
            font.draw(batch, selectedBuildingType.getName(), x + 85, y - 67);
        } else if (currentMenuState == MenuState.ANIMAL_SELECT && selectedAnimalType != null) {
            Sprite animalSprite = getAnimalSprite(selectedAnimalType);
            animalSprite.setSize(48, 48);
            animalSprite.setPosition(x + 33, y - 97);
            animalSprite.draw(batch);
            font.draw(batch, selectedAnimalType.getName(), x + 85, y - 67);
        }
        leftIcon.setPosition(x + 115, y - 135);
        leftIcon.setSize(30, 30);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 150, y - 135);
        rightIcon.setSize(30, 30);
        rightIcon.draw(batch);
    }

    private static void drawInfoState(SpriteBatch batch, float x, float y, Player player) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        if (currentMenuState == MenuState.BUILDING_SELECT) {
            font.draw(batch, "Building Info", x + 220, y - 20);
            board.setSize(170, 20);
            board.setPosition(x + 230, y - 65);
            board.draw(batch);
            bigBoard.setSize(170, 120);
            bigBoard.setPosition(x + 230, y - 180);
            bigBoard.draw(batch);
            leftIcon.setPosition(x + 350, y - 40);
            leftIcon.setSize(20, 20);
            leftIcon.draw(batch);
            rightIcon.setPosition(x + 375, y - 40);
            rightIcon.setSize(20, 20);
            rightIcon.draw(batch);
            font.getData().setScale(0.8f);
            if (selectedBuildingType != null) {
                font.draw(batch, selectedBuildingType.getName(), x + 240, y - 47);
                font.getData().setScale(0.5f);
                font.draw(batch, "Capacity: " + selectedBuildingType.getCapacity(), x + 240, y - 65);
                font.draw(batch, "Price: " + selectedBuildingType.getPrice() + "g", x + 240, y - 80);
                font.draw(batch, "Wood: " + selectedBuildingType.getWoodCost(), x + 240, y - 95);
                font.draw(batch, "Stone: " + selectedBuildingType.getStoneCost(), x + 240, y - 110);
                // Draw buy button
                if (player.getMoney() >= selectedBuildingType.getPrice() &&
                        player.getAmountOfItem(org.Group34.model.items.crafting.Ingredient.WOOD) >= selectedBuildingType.getWoodCost() &&
                        player.getAmountOfItem(org.Group34.model.items.crafting.Ingredient.STONE) >= selectedBuildingType.getStoneCost()) {
                    smallBoard.setPosition(x + 240, y - 140);
                    smallBoard.draw(batch);
                    font.draw(batch, "Build", x + 255, y - 135);
                } else {
                    font.setColor(Color.RED);
                    font.draw(batch, "Not enough resources", x + 240, y - 140);
                    font.setColor(Color.BLACK);
                }
            }
        } else if (currentMenuState == MenuState.ANIMAL_SELECT) {
            font.draw(batch, "Animal Info", x + 220, y - 20);
            board.setSize(170, 20);
            board.setPosition(x + 230, y - 65);
            board.draw(batch);
            bigBoard.setSize(170, 120);
            bigBoard.setPosition(x + 230, y - 180);
            bigBoard.draw(batch);
            leftIcon.setPosition(x + 350, y - 40);
            leftIcon.setSize(20, 20);
            leftIcon.draw(batch);
            rightIcon.setPosition(x + 375, y - 40);
            rightIcon.setSize(20, 20);
            rightIcon.draw(batch);
            font.getData().setScale(0.8f);
            if (selectedAnimalType != null) {
                font.draw(batch, selectedAnimalType.getName(), x + 240, y - 47);
                font.getData().setScale(0.5f);
                font.draw(batch, "Price: " + selectedAnimalType.getPrice() + "g", x + 240, y - 65);
                font.draw(batch, "Required: " + selectedAnimalType.getRequiredBuilding().getName(), x + 240, y - 80);
                // Check if player has required building
                boolean hasBuilding = false;
                if (buildingController != null) {
                    for (AnimalsBuilding building : buildingController.getBuildings()) {
                        if (BarnType.valueOf(building.type) == selectedAnimalType.getRequiredBuilding() &&
                                building.getAnimalCount() < building.capacity) {
                            hasBuilding = true;
                            break;
                        }
                    }
                }
                if (hasBuilding && player.getMoney() >= selectedAnimalType.getPrice()) {
                    // Draw buy button
                    smallBoard.setPosition(x + 240, y - 140);
                    smallBoard.draw(batch);
                    font.draw(batch, "Buy", x + 255, y - 135);
                } else {
                    font.setColor(Color.RED);
                    if (!hasBuilding) {
                        font.draw(batch, "Need " + selectedAnimalType.getRequiredBuilding().getName(), x + 240, y - 140);
                    } else {
                        font.draw(batch, "Not enough money", x + 240, y - 140);
                    }
                    font.setColor(Color.BLACK);
                }
            }
        }
    }

    private static void drawBackButton(SpriteBatch batch, float x, float y) {
        // Draw back button in the same position as the trash can in inventory
        Sprite backIcon = new Sprite(GameMenuAssetManager.getLeftIcon());
        backIcon.setPosition(x + 440, y - 145);
        backIcon.setSize(100, 100);
        backIcon.draw(batch);
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(0.8f);
        font.draw(batch, "Back", x + 465, y - 100);
    }

    public static void handleInput(Player player, OrthographicCamera camera) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        // Menu navigation
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 324 && x < 395 && y < 110 && y > 30) {
            player.setCurrentGameMenu("inventory");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 397 && x < 468 && y < 110 && y > 30) {
            player.setCurrentGameMenu("skill");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 470 && x < 541 && y < 110 && y > 30) {
            player.setCurrentGameMenu("social");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 542 && x < 613 && y < 110 && y > 30) {
            player.setCurrentGameMenu("map");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 615 && x < 686 && y < 110 && y > 30) {
            player.setCurrentGameMenu("npc");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 688 && x < 759 && y < 110 && y > 30) {
            player.setCurrentGameMenu("setting");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 761 && x < 832 && y < 110 && y > 30) {
            // Toggle between building and animal menus if not in main menu
            if (currentMenuState != MenuState.MAIN) {
                if (currentMenuState == MenuState.BUILDING_SELECT) {
                    currentMenuState = MenuState.ANIMAL_SELECT;
                } else {
                    currentMenuState = MenuState.BUILDING_SELECT;
                }
                selectedBuildingType = null;
                selectedAnimalType = null;
                scrollNumber = 0;
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1283 && x < 1342 && y < 150 && y > 82) {
            player.setCurrentGameMenu(null);
            resetMenuState();
            scrollNumber = 0;
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
        // Handle input based on current menu state
        if (currentMenuState == MenuState.MAIN) {
            handleMainMenuInput(player, x, y, camera);
        } else {
            // Handle scroll
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                scrollNumber = Math.max(0, scrollNumber - 1);
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                if (currentMenuState == MenuState.BUILDING_SELECT) {
                    scrollNumber = Math.min(BarnType.values().length / 12, scrollNumber + 1);
                } else {
                    scrollNumber = Math.min(AnimalType.values().length / 12, scrollNumber + 1);
                }
            }
            // Handle navigation buttons
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 462 && x < 512 && y > 702 && y < 759) {
                if (currentMenuState == MenuState.BUILDING_SELECT) {
                    int current = java.util.Arrays.asList(BarnType.values()).indexOf(selectedBuildingType);
                    if (current - 1 == -1) {
                        current = BarnType.values().length;
                    }
                    selectedBuildingType = BarnType.values()[current - 1];
                    selectedAnimalType = null;
                } else {
                    int current = java.util.Arrays.asList(AnimalType.values()).indexOf(selectedAnimalType);
                    if (current - 1 == -1) {
                        current = AnimalType.values().length;
                    }
                    selectedAnimalType = AnimalType.values()[current - 1];
                    selectedBuildingType = null;
                }
            } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 520 && x < 570 && y > 702 && y < 759) {
                if (currentMenuState == MenuState.BUILDING_SELECT) {
                    int current = java.util.Arrays.asList(BarnType.values()).indexOf(selectedBuildingType);
                    selectedBuildingType = BarnType.values()[(current + 1) % BarnType.values().length];
                    selectedAnimalType = null;
                } else {
                    int current = java.util.Arrays.asList(AnimalType.values()).indexOf(selectedAnimalType);
                    selectedAnimalType = AnimalType.values()[(current + 1) % AnimalType.values().length];
                    selectedBuildingType = null;
                }
            }
            // Handle item selection
            handleItemSelection(player, x, y);
            // Handle buy button
            handleBuyButton(player, x, y, camera);
            // Handle back button
            handleBackButton(player, x, y);
        }
    }

    private static void handleMainMenuInput(Player player, int x, int y, OrthographicCamera camera) {
        // Calculate screen center
        float centerX = Gdx.graphics.getWidth() / 2;
        float centerY = Gdx.graphics.getHeight() / 2;

        // Build Animal Building button (left side)
        float buildLeft = centerX - 150;
        float buildRight = centerX + 50;
        float buildTop = centerY + 100;
        float buildBottom = centerY - 100;

        // Buy Animal button (right side)
        float buyLeft = centerX + 150;
        float buyRight = centerX + 350;
        float buyTop = centerY + 100;
        float buyBottom = centerY - 100;

        // Check for Build button click
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                x > buildLeft && x < buildRight &&
                y > buildBottom && y < buildTop) {
            currentMenuState = MenuState.BUILDING_SELECT;
        }

        // Check for Buy button click
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                x > buyLeft && x < buyRight &&
                y > buyBottom && y < buyTop) {
            currentMenuState = MenuState.ANIMAL_SELECT;
        }
    }

    private static void handleItemSelection(Player player, int x, int y) {
        // Calculate item positions based on scroll
        float baseX = Gdx.graphics.getWidth() / 2 - 750;
        float baseY = Gdx.graphics.getHeight() / 2 + 300;
        if (currentMenuState == MenuState.BUILDING_SELECT) {
            List<BarnType> buildings = new ArrayList<>(java.util.Arrays.asList(BarnType.values()));
            for (int i = 0; i < 36; i++) {
                int index = i + (12 * scrollNumber);
                if (index >= buildings.size()) break;
                float itemX = baseX + 37 + (48 * i);
                float itemY = baseY - 163 - (i / 12 * 50);
                if (i > 11 && i < 24) {
                    itemX = baseX + 37 + (48 * i) - 576;
                } else if (i > 23) {
                    itemX = baseX + 37 + (48 * i) - 1152;
                }
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                        x > itemX && x < itemX + 35 &&
                        y > itemY && y < itemY + 33) {
                    selectedBuildingType = buildings.get(index);
                    selectedAnimalType = null;
                    return;
                }
            }
        } else if (currentMenuState == MenuState.ANIMAL_SELECT) {
            List<AnimalType> animals = new ArrayList<>(java.util.Arrays.asList(AnimalType.values()));
            for (int i = 0; i < 36; i++) {
                int index = i + (12 * scrollNumber);
                if (index >= animals.size()) break;
                float itemX = baseX + 37 + (48 * i);
                float itemY = baseY - 163 - (i / 12 * 50);
                if (i > 11 && i < 24) {
                    itemX = baseX + 37 + (48 * i) - 576;
                } else if (i > 23) {
                    itemX = baseX + 37 + (48 * i) - 1152;
                }
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                        x > itemX && x < itemX + 35 &&
                        y > itemY && y < itemY + 33) {
                    selectedAnimalType = animals.get(index);
                    selectedBuildingType = null;
                    return;
                }
            }
        }
    }

    private static void handleBuyButton(Player player, int x, int y, OrthographicCamera camera) {
        float baseX = Gdx.graphics.getWidth() / 2 - 750;
        float baseY = Gdx.graphics.getHeight() / 2 + 300;
        if (currentMenuState == MenuState.BUILDING_SELECT && selectedBuildingType != null) {
            // Check if player can afford this building
            boolean canAfford = player.getMoney() >= selectedBuildingType.getPrice() &&
                    player.getAmountOfItem(org.Group34.model.items.crafting.Ingredient.WOOD) >= selectedBuildingType.getWoodCost() &&
                    player.getAmountOfItem(org.Group34.model.items.crafting.Ingredient.STONE) >= selectedBuildingType.getStoneCost();
            if (canAfford) {
                float buttonX = baseX + 240;
                float buttonY = baseY - 440;
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                        x > buttonX && x < buttonX + 70 &&
                        y > buttonY && y < buttonY + 25) {
                    selectedBuildingType = selectedBuildingType;
                    isPlacingBuilding = true;
                    successMessage = "Click on the map to place " + selectedBuildingType.getName();
                    messageTimer = MESSAGE_DURATION;
                }
            }
        } else if (currentMenuState == MenuState.ANIMAL_SELECT && selectedAnimalType != null) {
            // Check if player has required building and can afford
            boolean hasBuilding = false;
            if (buildingController != null) {
                for (AnimalsBuilding building : buildingController.getBuildings()) {
                    if (BarnType.valueOf(building.type) == selectedAnimalType.getRequiredBuilding() &&
                            building.getAnimalCount() < building.capacity) {
                        hasBuilding = true;
                        break;
                    }
                }
            }
            boolean canAfford = player.getMoney() >= selectedAnimalType.getPrice();
            if (hasBuilding && canAfford) {
                float buttonX = baseX + 240;
                float buttonY = baseY - 440;
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                        x > buttonX && x < buttonX + 70 &&
                        y > buttonY && y < buttonY + 25) {
                    buyAnimal(player, selectedAnimalType, camera);
                }
            }
        }
    }

    private static void handleBackButton(Player player, int x, int y) {
        float baseX = Gdx.graphics.getWidth() / 2 - 750;
        float baseY = Gdx.graphics.getHeight() / 2 + 300;
        // Back button position (same as trash can in inventory)
        float backButtonX = baseX + 440;
        float backButtonY = baseY - 440;
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                x > backButtonX && x < backButtonX + 100 &&
                y > backButtonY && y < backButtonY + 100) {
            // Go back to main menu
            currentMenuState = MenuState.MAIN;
            selectedBuildingType = null;
            selectedAnimalType = null;
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
        scrollNumber = 0;
    }

    public static boolean isPlacingBuilding() {
        return isPlacingBuilding;
    }

    public static void cancelPlacingBuilding() {
        isPlacingBuilding = false;
        selectedBuildingType = null;
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