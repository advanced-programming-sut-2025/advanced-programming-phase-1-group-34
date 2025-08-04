package org.Group34.view.graphic.gameMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import org.Group34.model.entities.Player;
import org.Group34.model.gameAssetManagers.GameMenuAssetManager;
import org.Group34.model.gameAssetManagers.ToolAssetManager;

public class SettingMenu {
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

    // Added new sprites for exit game and delete player options
    private final static Sprite exitGameIcon = new Sprite(GameMenuAssetManager.getExitGameIcon());
    private final static Sprite deletePlayerIcon = new Sprite(GameMenuAssetManager.getDeletePlayerIcon());

    // Font for text labels
    private final static BitmapFont menuFont = new BitmapFont(); // Assuming this method exists

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

        // Set sizes for new icons
        exitGameIcon.setSize((float) (exitGameIcon.getWidth() * 0.15), (float) (exitGameIcon.getHeight() * 0.15));
        deletePlayerIcon.setSize((float) (deletePlayerIcon.getWidth() * 0.15), (float) (deletePlayerIcon.getHeight() * 0.15));

        // Set font size
        menuFont.getData().setScale(1.0f);
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;
        drawBoard(batch, x, y);

        handleInput(player, camera);
    }

    private static void drawBoard(SpriteBatch batch, float x, float y) {
        bigBoard.setPosition(x, y - 200);
        bigBoard.draw(batch);

        // Top menu items
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
        smallBoard.setPosition(x + 250, y + 203);
        smallBoard.draw(batch);
        smallBoard.setPosition(x + 294, y + 210);
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
        settingSymbol.setPosition(x + 250 + 13, y + 203 + 8);
        settingSymbol.draw(batch);

        exitIcon.setPosition(x + 608, y + 190);
        exitIcon.draw(batch);

        animalIcon.setPosition(x + 294 + 10, y + 210 + 5);
        animalIcon.draw(batch);

        // New options with text labels
        // Save and Exit option
        menuFont.setColor(Color.BLACK);
        exitGameIcon.setPosition(x + 35, y + 130);
        exitGameIcon.setSize(50, 50);
        exitGameIcon.draw(batch);
        menuFont.draw(batch, "Save and Exit", x + 30 + smallBoard.getWidth() + 10, y + 135 + smallBoard.getHeight()/2 + menuFont.getCapHeight()/2);

        // Delete player option
        deletePlayerIcon.setPosition(x + 30 + 5, y + 70);
        deletePlayerIcon.setSize(50, 50);
        deletePlayerIcon.draw(batch);
        menuFont.draw(batch, "Delete player", x + 30 + smallBoard.getWidth() + 10, y + 75 + smallBoard.getHeight()/2 + menuFont.getCapHeight()/2);
    }

    public static void handleInput(Player player, OrthographicCamera camera) {
        // Convert mouse coordinates to world coordinates
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);

        float baseX = camera.position.x - chest.getWidth() / 2;
        float baseY = camera.position.y - 30;

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
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 615 && x < 686 && y < 110 && y > 30) {
            player.setCurrentGameMenu("npc");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 761 && x < 832 && y < 110 && y > 30) {
            player.setCurrentGameMenu("animal");
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1283 && x < 1342 && y < 150 && y > 82) {
            player.setCurrentGameMenu(null);
        }
        // Check top menu items
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            // Save and Exit button
            if (mousePos.x > baseX + 30 && mousePos.x < baseX + 30 + smallBoard.getWidth() + 150 &&
                    mousePos.y > baseY + 150 && mousePos.y < baseY + 150 + smallBoard.getHeight()) {
                // Exit game option - implement game exit logic
                Gdx.app.exit(); // This will close the game
            }
            // Delete player button
            else if (mousePos.x > baseX + 30 && mousePos.x < baseX + 30 + smallBoard.getWidth() + 150 &&
                    mousePos.y > baseY + 100 && mousePos.y < baseY + 100 + smallBoard.getHeight()) {
                // Delete player option - implement player deletion logic
                // This would typically involve calling a method to delete the player data
                // For example: PlayerDataManager.deletePlayer(player.getId());
                // After deletion, you might want to return to the main menu or login screen
                player.setCurrentGameMenu(null); // Close the menu for now
            }
        }
    }
}