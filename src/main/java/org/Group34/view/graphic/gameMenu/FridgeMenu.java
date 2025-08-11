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
import org.Group34.model.items.Item;
import org.Group34.model.items.tools.Tool;
import java.util.ArrayList;

public class FridgeMenu {
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
    private final static Sprite greenRect = new Sprite(GameMenuAssetManager.getGreenRect());
    private final static Sprite rightIcon = new Sprite(GameMenuAssetManager.getRightIcon());
    private final static Sprite leftIcon = new Sprite(GameMenuAssetManager.getLeftIcon());
    private final static Sprite bigBoard = new Sprite(GameMenuAssetManager.getBigBoard());
    private static int scrollNumber = 0;
    private static int infoNumber = 0;
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
        craftingIcon.setSize((float) (craftingIcon.getWidth() * 0.5), (float) (craftingIcon.getHeight() * 0.5));
        cookingIcon.setSize((float) (cookingIcon.getWidth() * 0.5), (float) (cookingIcon.getHeight() * 0.5));
        fridgeIcon.setSize((float) (fridgeIcon.getWidth() * 0.3), (float) (fridgeIcon.getHeight() * 0.3));
        greenRect.setSize(45, 45);
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera, GameController gameController) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;
        drawBoard(batch, x, y);
        ArrayList<Item> fridgeItems = new ArrayList<>(player.getFridge().keySet());
        int index = 0;
        BitmapFont font = new BitmapFont();

        font.setColor(Color.BLUE);
        font.getData().setScale(1.0f);
        font.draw(batch, "Press ENTER to transfer selected item to inventory", x + 100, y + 300);

        while (index + (12 * scrollNumber) < fridgeItems.size() && index < 36) {
            Item item = fridgeItems.get(index + (12 * scrollNumber));
            Sprite sprite = new Sprite(item.getTexture());
            sprite.setPosition(x + 37 + (48 * index), y + 137 - (index / 12 * 50));
            font.setColor(Color.BLACK);
            if (index > 11 && index < 24) {
                sprite.setPosition(x + 37 + (48 * index) - 576, y + 137 - (index / 12 * 50));
            } else if (index > 23) {
                sprite.setPosition(x + 37 + (48 * index) - 1152, y + 137 - (index / 12 * 50));
            }
            if (item.equals(player.getCurrentFridgeItem())) {
                greenRect.setPosition(sprite.getX() - 6, sprite.getY() - 8);
                greenRect.draw(batch);
            }
            font.getData().setScale(0.5f);
            font.draw(batch, String.valueOf(player.getAmountInFridge(item)), sprite.getX() - 5, sprite.getY() + 5);
            font.draw(batch, item.getName(), sprite.getX() - 5, sprite.getY() - 3);
            sprite.setSize(35, 33);
            sprite.draw(batch);
            index++;
        }
        fullBoard(player, fridgeItems, x, y, batch, gameController);
        handleInput(player, fridgeItems, x, y, camera);
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

        smallBoard.setPosition(x + 338, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 382, y + 210);
        smallBoard.draw(batch);

        smallBoard.setPosition(x + 426, y + 203);
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

        fridgeIcon.setPosition(x + 426 + 15, y + 203 + 3);
        fridgeIcon.draw(batch);

        exitIcon.setPosition(x + 608, y + 190);
        exitIcon.draw(batch);
    }

    private static void handleInput(Player player, ArrayList<Item> fridgeItems, float x, float y, OrthographicCamera camera) {
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        // Handle menu navigation
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 324 && mouseX < 395 && mouseY < 110 && mouseY > 30) {
            player.setCurrentGameMenu("inventory");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 397 && mouseX < 468 && mouseY < 110 && mouseY > 30) {
            player.setCurrentGameMenu("skill");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 470 && mouseX < 541 && mouseY < 110 && mouseY > 30) {
            player.setCurrentGameMenu("social");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 542 && mouseX < 613 && mouseY < 110 && mouseY > 30) {
            player.setCurrentGameMenu("map");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 688 && mouseX < 759 && mouseY < 110 && mouseY > 30) {
            player.setCurrentGameMenu("setting");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 615 && mouseX < 686 && mouseY < 110 && mouseY > 30) {
            player.setCurrentGameMenu("npc");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 761 && mouseX < 832 && mouseY < 110 && mouseY > 30) {
            player.setCurrentGameMenu("animal");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 834 && mouseX < 905 && mouseY < 110 && mouseY > 30) {
            player.setCurrentGameMenu("crafting");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 907 && mouseX < 978 && mouseY < 110 && mouseY > 30) {
            player.setCurrentGameMenu("cooking");
            scrollNumber = 0;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 1283 && mouseX < 1342 && mouseY < 150 && mouseY > 82) {
            player.setCurrentGameMenu(null);
            scrollNumber = 0;
        }

        // Handle Enter key to transfer to inventory
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (player.getCurrentFridgeItem() != null) {
                // Transfer the current item to inventory
                Item itemToTransfer = player.getCurrentFridgeItem();
                int amount = player.getAmountInFridge(itemToTransfer);

                // Add to inventory
                player.addToInventory(itemToTransfer, amount);

                // Remove from fridge
                player.removeFromFridge(itemToTransfer, amount);

                // Clear selection if amount becomes zero
                if (player.getAmountInFridge(itemToTransfer) == 0) {
                    player.setCurrentFridgeItem(null);
                }

                // Show success message
                System.out.println("Item transferred to inventory successfully!");
            } else {
                // Show message if no item selected
                System.out.println("No item selected for transfer!");
            }
        }
        // Handle scrolling
        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            scrollNumber = Math.max(0, scrollNumber - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            scrollNumber = Math.min(player.getFridge().size() / 12, scrollNumber + 1);
        }
        // Handle item selection
        handleItemClick(player, fridgeItems, x, y, camera);
        // Handle navigation buttons
        int current = 0;
        if (player.getCurrentFridgeItem() != null) {
            current = fridgeItems.indexOf(player.getCurrentFridgeItem());
        }
        Item newSelect;
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 462 && mouseX < 512 && mouseY > 702 && mouseY < 759) {
            if (current - 1 == -1) {
                current = fridgeItems.size();
            }
            newSelect = fridgeItems.get(current - 1);
            player.setCurrentFridgeItem(newSelect);
        }
        else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 520 && mouseX < 570 && mouseY > 702 && mouseY < 759) {
            newSelect = fridgeItems.get((current + 1) % fridgeItems.size());
            player.setCurrentFridgeItem(newSelect);
        }
        // Handle craft info navigation
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 855 && mouseX < 888 && mouseY > 543 && mouseY < 580) {
            infoNumber++;
            if (infoNumber == getAllCrafts().size()) {
                infoNumber = 0;
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && mouseX > 897 && mouseX < 930 && mouseY > 543 && mouseY < 580) {
            infoNumber--;
            if (infoNumber == -1) {
                infoNumber = getAllCrafts().size() - 1;
            }
        }
    }

    private static void handleItemClick(Player player, ArrayList<Item> fridgeItems, float x, float y, OrthographicCamera camera) {
        if (!Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            return;
        }
        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.input.getY();
        // Convert mouse coordinates to world coordinates
        float worldX = camera.position.x - (Gdx.graphics.getWidth() / 2) + mouseX;
        float worldY = camera.position.y + (Gdx.graphics.getHeight() / 2) - mouseY;
        // Check if click is within the item grid area
        float gridStartX = x + 37;
        float gridStartY = y + 137;
        float gridWidth = 12 * 48;
        float gridHeight = 3 * 50;
        if (worldX < gridStartX || worldX > gridStartX + gridWidth ||
                worldY < gridStartY - gridHeight || worldY > gridStartY) {
            return;
        }
        // Calculate which item was clicked
        int col = (int)((worldX - gridStartX) / 48);
        int row = (int)((gridStartY - worldY) / 50);
        int indexInPage = row * 12 + col;
        int actualIndex = indexInPage + (12 * scrollNumber);
        if (actualIndex < fridgeItems.size()) {
            Item clickedItem = fridgeItems.get(actualIndex);
            player.setCurrentFridgeItem(clickedItem);
        }
    }

    private static void fullBoard(Player player, ArrayList<Item> fridgeItems, float x, float y, SpriteBatch batch, GameController gameController) {
        drawSelectState(batch, x, y, player);
        drawCraftInfoState(batch, x, y, gameController);
    }

    private static void drawSelectState(SpriteBatch batch, float x, float y, Player player) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Fridge Item", x + 20, y - 20);
        board.setSize(chest.getWidth() / 4, chest.getHeight() / 4);
        board.setPosition(x + 30, y - 100);
        board.draw(batch);
        font.getData().setScale(0.8f);
        if (player.getCurrentFridgeItem() != null) {
            Sprite sprite = new Sprite(player.getCurrentFridgeItem().getTexture());
            sprite.setSize(48, 48);
            sprite.setPosition(x + 33, y - 97);
            sprite.draw(batch);
            font.draw(batch, player.getCurrentFridgeItem().getName(), x + 85, y - 67);
        }
        leftIcon.setPosition(x + 115, y - 135);
        leftIcon.setSize(30, 30);
        leftIcon.draw(batch);
        rightIcon.setPosition(x + 150, y - 135);
        rightIcon.setSize(30, 30);
        rightIcon.draw(batch);
    }

    private static void drawCraftInfoState(SpriteBatch batch, float x, float y, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Craft Info", x + 220, y - 20);
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
        font.draw(batch, getAllCrafts().get(infoNumber), x + 240, y - 47);
        font.getData().setScale(0.5f);
        font.draw(batch, gameController.showCraftInfo(getAllCrafts().get(infoNumber)).message(), x + 240, y - 65);
    }

    private static ArrayList<String> getAllCrafts() {
        ArrayList<String> crafts = new ArrayList<>();
        crafts.add("Blue Jazz");
        crafts.add("Carrot");
        crafts.add("Cauliflower");
        crafts.add("Coffee Bean");
        crafts.add("Garlic");
        crafts.add("Green Bean");
        crafts.add("Kale");
        crafts.add("Parsnip");
        crafts.add("Potato");
        crafts.add("Rhubarb");
        crafts.add("Strawberry");
        crafts.add("Tulip");
        crafts.add("Unmilled Rice");
        crafts.add("Blueberry");
        crafts.add("Corn");
        crafts.add("Hops");
        crafts.add("Hot Pepper");
        crafts.add("Melon");
        crafts.add("Poppy");
        crafts.add("Radish");
        crafts.add("Red Cabbage");
        crafts.add("Starfruit");
        crafts.add("Summer Spangle");
        crafts.add("Summer Squash");
        crafts.add("Sunflower");
        crafts.add("Tomato");
        crafts.add("Wheat");
        crafts.add("Amaranth");
        crafts.add("Artichoke");
        crafts.add("Beet");
        crafts.add("Bok Choy");
        crafts.add("Broccoli");
        crafts.add("Cranberries");
        crafts.add("Eggplant");
        crafts.add("Fairy Rose");
        crafts.add("Grape");
        crafts.add("Pumpkin");
        crafts.add("Yam");
        crafts.add("Sweet Gem Berry");
        crafts.add("Powdermelon");
        crafts.add("Ancient Fruit");
        crafts.add("Apricot");
        crafts.add("Cherry");
        crafts.add("Banana");
        crafts.add("Mango");
        crafts.add("Orange");
        crafts.add("Peach");
        crafts.add("Apple");
        crafts.add("Pomegranate");
        return crafts;
    }
}