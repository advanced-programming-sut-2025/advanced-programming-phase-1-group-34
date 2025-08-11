package org.Group34.view.graphic.gameMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.controller.GameController;
import org.Group34.model.NetworkObjects.NetworkShopLimit;
import org.Group34.model.Result;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.shops.Blacksmith;
import org.Group34.model.entities.buildings.shops.Shop;
import org.Group34.model.entities.buildings.shops.products.UpgradeTools;
import org.Group34.model.gameAssetManagers.GameMenuAssetManager;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;
import org.Group34.model.map.Space;

import java.util.ArrayList;
import java.util.HashMap;

public class BlacksmithMenu {
    private final static Sprite bigBoard = new Sprite(GameMenuAssetManager.getBigBoard());
    private final static Sprite smallBoard = new Sprite(GameMenuAssetManager.getBoard());
    private final static Sprite exitIcon = new Sprite(GameMenuAssetManager.getExitIcon());
    private final static Sprite filterBoard = new Sprite(GameMenuAssetManager.getBoard());
    private final static Sprite buyBoard = new Sprite(GameMenuAssetManager.getBoard());
    private final static Sprite rightIcon = new Sprite(GameMenuAssetManager.getRightIcon());
    private final static Sprite leftIcon = new Sprite(GameMenuAssetManager.getLeftIcon());
    private final static Sprite buyIcon = new Sprite(GameMenuAssetManager.getBuyIcon());

    private static int scrollNumber = 0;
    private static boolean showAll = true;
    private static int buyAmount = 0;
    private static String error = "";

    static {
        bigBoard.setSize((float) (bigBoard.getWidth() * 0.75), (float) (bigBoard.getHeight() * 0.75));
        smallBoard.setSize((float) (smallBoard.getWidth() * 0.3), (float) (smallBoard.getHeight() * 0.1));
        exitIcon.setSize((float) (exitIcon.getWidth() * 0.2), (float) (exitIcon.getHeight() * 0.2));
        filterBoard.setSize((float) (filterBoard.getWidth() * 0.1), (float) (filterBoard.getHeight() * 0.1));
        buyBoard.setSize(bigBoard.getWidth(), 200);
        leftIcon.setSize(30, 30);
        rightIcon.setSize(30, 30);
        buyIcon.setSize(30, 30);
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera, GameController gameController) {
        float x = camera.position.x - 450;
        float y = camera.position.y - 200;

        drawBoard(batch, x, y);
        fullTheBoard(batch, x, y, player, gameController);

        handleInput(player, gameController);
    }

    private static void drawBoard(SpriteBatch batch, float x, float y) {
        bigBoard.setPosition(x + 130, y - 35);
        bigBoard.draw(batch);

        smallBoard.setPosition(x + 315, y + 390);
        smallBoard.draw(batch);

        exitIcon.setPosition(x + 738, y + 380);
        exitIcon.draw(batch);

        buyBoard.setPosition(x + 130, y - 35);
        buyBoard.draw(batch);

        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Blacksmith", x + 405, y + 410);
    }

    private static void handleInput(Player player, GameController gameController) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (x > 1279 && x < 1338 && y < 112 && y > 44) {
                player.setCurrentGameMenu(null);
                scrollNumber = 0;
                showAll = true;
                buyAmount = 0;
                error = "";
            } else if (x > 1082 && x < 1222 && y > 166 && y < 226) {
                showAll = !showAll;
                scrollNumber = 0;
                buyAmount = 0;
            } else if (x > 917 && x < 967 && y > 692 && y < 748) {
                buyAmount--;
                buyAmount = Math.max(0, buyAmount);
            } else if (x > 983 && x < 1033 && y > 692 && y < 748) {
                buyAmount++;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            scrollNumber++;
            buyAmount = 0;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            scrollNumber--;
            buyAmount = 0;
        }
    }

    private static void fullTheBoard(SpriteBatch batch, float x, float y, Player player, GameController gameController) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);

        Blacksmith blacksmith = (Blacksmith) getDesiredShop(player);
        HashMap<Item, Integer> products;

        filterBoard.setPosition(x + 620, y + 320);
        filterBoard.draw(batch);

        font.getData().setScale(0.7f);
        if (showAll) {
            font.draw(batch, "Show Available", x + 630, y + 340);
            products = getAllProducts(blacksmith);
        } else {
            font.draw(batch, "Show All", x + 630, y + 340);
            products = getAvailableProducts(blacksmith);
        }

        scrollNumber %= products.size();
        if (scrollNumber == -1) {
            scrollNumber = products.size() - 1;
        }

        font.getData().setScale(1f);

        Item product = new ArrayList<>(products.keySet()).get(scrollNumber);
        if (products.get(product) != -11 && products.get(product) <= 0) {
            font.setColor(Color.GRAY);
        }

        font.draw(batch, "Product:", x + 200, y + 340);
        font.draw(batch, "Price:", x + 200, y + 300);
        font.draw(batch, "Limit:", x + 200, y + 220);

        if (product instanceof Ingredient ingredient) {
            font.draw(batch, ingredient.getName(), x + 270, y + 340);
            font.draw(batch, String.valueOf(ingredient.getPrice()), x + 270, y + 300);
            font.draw(batch, "Description:", x + 200, y + 260);
            font.draw(batch, ingredient.getDescription(), x + 290, y + 260);

            int limit = products.get(product);
            if (limit != -11) {
                font.draw(batch, String.valueOf(products.get(product)), x + 270, y + 220);
            } else {
                font.draw(batch, "Unlimit", x + 270, y + 220);
            }
        } else if (product instanceof UpgradeTools tool) {
            font.draw(batch, tool.getName(), x + 270, y + 340);
            font.draw(batch, String.valueOf(tool.getPrice()), x + 270, y + 300);
            font.draw(batch, "Ingredient:", x + 200, y + 260);
            font.draw(batch, tool.getIngredient().getName(), x + 290, y + 260);

            int limit = products.get(product);
            if (limit != -11) {
                font.draw(batch, String.valueOf(products.get(product)), x + 270, y + 220);
            } else {
                font.draw(batch, "Unlimit", x + 270, y + 220);
            }
        }

        font.setColor(Color.BLACK);
        font.draw(batch, "How much do you want to buy?", x + 200, y + 110);

        filterBoard.setPosition(x + 400, y + 40);
        filterBoard.draw(batch);

        if (products.get(product) != -11 && products.get(product) < buyAmount) {
            buyAmount--;
        }
        font.draw(batch, String.valueOf(buyAmount), x + 410, y + 60);

        leftIcon.setPosition(x + 520, y + 40);
        leftIcon.draw(batch);

        rightIcon.setPosition(x + 560, y + 40);
        rightIcon.draw(batch);

        buyIcon.setPosition(x + 620, y + 40);
        buyIcon.draw(batch);

        font.setColor(Color.RED);
        font.draw(batch, error, x + 300, y + 10);

        buy(player, gameController, product);
    }

    private static Entity getDesiredShop(Player player) {
        Space space = player.getCurrentSpace();

        int x = player.getLocation()[0];
        int y = player.getLocation()[1];

        Entity desiredShop = null;

        if (space.getEntityByLocation(x - 1, y - 1) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x - 1, y) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x - 1, y + 1) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x, y - 1) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x, y) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x, y + 1) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x + 1, y - 1) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x + 1, y) instanceof Shop shop) {
            return shop;
        } else if (space.getEntityByLocation(x + 1, y + 1) instanceof Shop shop) {
            return shop;
        }

        return null;
    }

    private static HashMap<Item, Integer> getAllProducts(Blacksmith blacksmith) {
        HashMap<Item, Integer> products = new HashMap<>();
        for (Item stock : Blacksmith.getStocks()) {
            int amount = blacksmith.getStockLimit(stock);
            products.put(stock, amount);
        }
        for (Item upgradeTool : Blacksmith.getUpgradeTools()) {
            int amount = blacksmith.getUpgradeToolLimit(upgradeTool);
            products.put(upgradeTool, amount);
        }
        return products;
    }

    private static HashMap<Item, Integer> getAvailableProducts(Blacksmith blacksmith) {
        HashMap<Item, Integer> products = new HashMap<>();
        for (Item stock : Blacksmith.getStocks()) {
            int amount = blacksmith.getStockLimit(stock);
            if (amount > 0 || amount == -11) {
                products.put(stock, amount);
            }
        }
        for (Item upgradeTool : Blacksmith.getUpgradeTools()) {
            int amount = blacksmith.getUpgradeToolLimit(upgradeTool);
            if (amount > 0 || amount == -11) {
                products.put(upgradeTool, amount);
            }
        }
        return products;
    }

    private static void buy(Player player, GameController gameController, Item product) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1082 && x < 1132 && y > 692 && y < 748) {
            Result result = gameController.purchase(product.getName(), buyAmount, player);
            error = result.message();
            if (result.success()) {
                NetworkShopLimit networkShopLimit = new NetworkShopLimit(product.getName(), buyAmount);
                gameController.getClient().sendObject(networkShopLimit);
            }
        }
    }
}
