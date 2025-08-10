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
import org.Group34.model.gameAssetManagers.CookingAssetManager;
import org.Group34.model.gameAssetManagers.GameMenuAssetManager;
import org.Group34.model.gameAssetManagers.ToolAssetManager;
import org.Group34.model.items.foods.CookedFood;
import java.util.ArrayList;
import java.util.Map;

public class CookingMenu {
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
    private final static Sprite lockIcon = new Sprite(GameMenuAssetManager.getLockIcon());
    private final static Sprite unlockIcon = new Sprite(GameMenuAssetManager.getLearnIcon());
    private final static Sprite cookIcon = new Sprite(GameMenuAssetManager.getCookIcon());

    private static int scrollNumber = 0;
    private static CookedFood currentRecipe = null;
    private static boolean[] unlockedRecipes;
    private static final int UNLOCK_COST = 1000;
    private static String statusMessage = null;
    private static float statusMessageTimer = 0;
    private static BitmapFont statusFont = new BitmapFont();

    static {
        // Initialize unlocked recipes array (first two recipes unlocked by default)
        CookedFood[] allRecipes = CookedFood.values();
        unlockedRecipes = new boolean[allRecipes.length];
        unlockedRecipes[0] = true; // FRIED_EGG
        unlockedRecipes[1] = true; // BAKED_FISH

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
        lockIcon.setSize(30, 30);
        unlockIcon.setSize(30, 30);

        statusFont.setColor(Color.RED);
        statusFont.getData().setScale(1.0f);
    }

    public static void draw(SpriteBatch batch, Player player, OrthographicCamera camera, GameController gameController) {
        float x = camera.position.x - chest.getWidth() / 2;
        float y = camera.position.y - 30;
        drawBoard(batch, x, y);
        CookedFood[] recipes = CookedFood.values();
        int index = 0;
        BitmapFont font = new BitmapFont();
        while (index + (12 * scrollNumber) < recipes.length && index < 36) {
            CookedFood recipe = recipes[index + (12 * scrollNumber)];
            Sprite sprite = new Sprite(getTexture(recipe));
            sprite.setPosition(x + 37 + (48 * index), y + 137 - (index / 12 * 50));
            font.setColor(Color.BLACK);
            if (index > 11 && index < 24) {
                sprite.setPosition(x + 37 + (48 * index) - 576, y + 137 - (index / 12 * 50));
            } else if (index > 23) {
                sprite.setPosition(x + 37 + (48 * index) - 1152, y + 137 - (index / 12 * 50));
            }
            if (recipe.equals(currentRecipe)) {
                greenRect.setPosition(sprite.getX() - 6, sprite.getY() - 8);
                greenRect.draw(batch);
            }
            font.getData().setScale(0.5f);
            // Check if recipe is unlocked
            int recipeIndex = index + (12 * scrollNumber);
            if (unlockedRecipes[recipeIndex]) {
                // Draw normally if unlocked
                font.draw(batch, recipe.getName(), sprite.getX() - 5, sprite.getY() - 3);
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
        fullBoard(player, recipes, x, y, batch, gameController);
        handleInput(player, recipes);

        // Draw status message if active
        if (statusMessage != null && statusMessageTimer > 0) {
            statusFont.draw(batch, statusMessage, x + chest.getWidth()/2 - statusFont.getRegion().getRegionWidth()/2, y + 50);
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
        smallBoard.setPosition(x + 30, y + 203);
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
        inventorySymbol.setPosition(x + 30 + 5, y + 203 + 2);
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
        craftingIcon.setPosition(x + 338 + 10, y + 210 + 5);
        craftingIcon.draw(batch);
        cookingIcon.setPosition(x + 382 + 10, y + 210 + 5);
        cookingIcon.draw(batch);
        fridgeIcon.setPosition(x + 426 + 15, y + 210 + 3);
        fridgeIcon.draw(batch);
        exitIcon.setPosition(x + 608, y + 190);
        exitIcon.draw(batch);
    }

    private static void handleInput(Player player, CookedFood[] recipes) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 397 && x < 468 && y < 110 && y > 30) {
            player.setCurrentGameMenu("skill");
            scrollNumber = 0;
            currentRecipe = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 470 && x < 541 && y < 110 && y > 30) {
            player.setCurrentGameMenu("social");
            scrollNumber = 0;
            currentRecipe = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 542 && x < 613 && y < 110 && y > 30) {
            player.setCurrentGameMenu("map");
            scrollNumber = 0;
            currentRecipe = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 615 && x < 686 && y < 110 && y > 30) {
            player.setCurrentGameMenu("npc");
            scrollNumber = 0;
            currentRecipe = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 688 && x < 759 && y < 110 && y > 30) {
            player.setCurrentGameMenu("setting");
            scrollNumber = 0;
            currentRecipe = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 761 && x < 832 && y < 110 && y > 30) {
            player.setCurrentGameMenu("animal");
            scrollNumber = 0;
            currentRecipe = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 834 && x < 905 && y < 110 && y > 30) {
            player.setCurrentGameMenu("crafting");
            scrollNumber = 0;
            currentRecipe = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 907 && x < 978 && y < 110 && y > 30) {
            // Already in cooking menu
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 980 && x < 1051 && y < 110 && y > 30) {
            player.setCurrentGameMenu("fridge");
            scrollNumber = 0;
            currentRecipe = null;
            statusMessage = null;
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1283 && x < 1342 && y < 150 && y > 82) {
            player.setCurrentGameMenu(null);
            scrollNumber = 0;
            currentRecipe = null;
            statusMessage = null;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            scrollNumber = Math.max(0, scrollNumber - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            scrollNumber = Math.min(recipes.length / 12, scrollNumber + 1);
        }

        // Handle unlock/cook button
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 1005 && x < 1170 && y > 588 && y < 780) {
            if (currentRecipe != null) {
                int recipeIndex = -1;
                for (int i = 0; i < recipes.length; i++) {
                    if (recipes[i] == currentRecipe) {
                        recipeIndex = i;
                        break;
                    }
                }
                if (recipeIndex != -1) {
                    if (!unlockedRecipes[recipeIndex]) {
                        // Unlock the recipe
                        if (player.getMoney() >= UNLOCK_COST) {
                            player.setMoney(player.getMoney() - UNLOCK_COST);
                            unlockedRecipes[recipeIndex] = true;
                        } else {
                            statusMessage = "Not enough money to learn this recipe!";
                            statusMessageTimer = 3.0f;
                        }
                    } else {
                        // Cook the recipe
                        boolean canCook = true;
                        StringBuilder missingIngredients = new StringBuilder();

                        for (Map.Entry<org.Group34.model.items.Item, Integer> entry : currentRecipe.getIngredients().entrySet()) {
                            int required = entry.getValue();
                            int available = player.getAmountOfItem(entry.getKey());

                            if (entry.getKey().getName() == null) {
                                canCook = true;
                            }
                            else if (available < required) {
                                canCook = false;
                                missingIngredients.append(entry.getKey().getName())
                                        .append(" (need ")
                                        .append(required)
                                        .append(", have ")
                                        .append(available)
                                        .append("), ");
                            }
                        }

                        if (canCook) {
                            for (Map.Entry<org.Group34.model.items.Item, Integer> entry : currentRecipe.getIngredients().entrySet()) {
                                player.removeFromInventory(entry.getKey(), entry.getValue());
                            }
                            player.addToInventory(currentRecipe, 1);
                            statusMessage = "Cooked!";
                            statusMessageTimer = 3.0f;
                        } else {
                            // Remove the last comma and space
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
        if (currentRecipe != null) {
            for (int i = 0; i < recipes.length; i++) {
                if (recipes[i] == currentRecipe) {
                    currentIndex = i;
                    break;
                }
            }
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 462 && x < 512 && y > 702 && y < 759) {
            if (currentIndex == -1) {
                currentRecipe = recipes[recipes.length - 1];
            } else {
                currentRecipe = recipes[(currentIndex - 1 + recipes.length) % recipes.length];
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 520 && x < 570 && y > 702 && y < 759) {
            if (currentIndex == -1) {
                currentRecipe = recipes[0];
            } else {
                currentRecipe = recipes[(currentIndex + 1) % recipes.length];
            }
        }
    }

    private static void fullBoard(Player player, CookedFood[] recipes, float x, float y, SpriteBatch batch, GameController gameController) {
        drawUnlockCookButton(x, y, batch, player);
        drawSelectState(batch, x, y, player);
        drawRecipeInfoState(batch, x, y, player);
    }

    private static void drawUnlockCookButton(float x, float y, SpriteBatch batch, Player player) {
        int recipeIndex = -1;
        if (currentRecipe != null) {
            CookedFood[] recipes = CookedFood.values();
            for (int i = 0; i < recipes.length; i++) {
                if (recipes[i] == currentRecipe) {
                    recipeIndex = i;
                    break;
                }
            }
        }

        if (recipeIndex != -1 && !unlockedRecipes[recipeIndex]) {
            // Draw unlock button
            unlockIcon.setPosition(x + 450, y - 130);
            unlockIcon.setSize(75, 75);
            unlockIcon.draw(batch);
            BitmapFont font = new BitmapFont();
            font.setColor(Color.BLACK);
            font.getData().setScale(0.8f);
            font.draw(batch, "Learn: " + UNLOCK_COST, x + 460, y - 155);
        } else {
            // Draw cook button
            cookIcon.setPosition(x + 440, y - 145);
            cookIcon.setSize(100, 100);
            cookIcon.draw(batch);
        }
    }

    private static void drawSelectState(SpriteBatch batch, float x, float y, Player player) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Select Recipe", x + 20, y - 20);
        board.setSize(chest.getWidth() / 4, chest.getHeight() / 4);
        board.setPosition(x + 30, y - 100);
        board.draw(batch);
        font.getData().setScale(0.8f);
        if (currentRecipe != null) {
            int recipeIndex = -1;
            CookedFood[] recipes = CookedFood.values();
            for (int i = 0; i < recipes.length; i++) {
                if (recipes[i] == currentRecipe) {
                    recipeIndex = i;
                    break;
                }
            }

            if (recipeIndex != -1 && unlockedRecipes[recipeIndex]) {
                Sprite sprite = new Sprite(getTexture(currentRecipe));
                sprite.setSize(48, 48);
                sprite.setPosition(x + 33, y - 97);
                sprite.draw(batch);
                font.draw(batch, currentRecipe.getName(), x + 85, y - 67);
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

    private static void drawRecipeInfoState(SpriteBatch batch, float x, float y, Player player) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.draw(batch, "Recipe Info", x + 220, y - 20);
        board.setSize(170, 20);
        board.setPosition(x + 230, y - 65);
        board.draw(batch);
        bigBoard.setSize(170, 120);
        bigBoard.setPosition(x + 230, y - 180);
        bigBoard.draw(batch);

        if (currentRecipe != null) {
            int recipeIndex = -1;
            CookedFood[] recipes = CookedFood.values();
            for (int i = 0; i < recipes.length; i++) {
                if (recipes[i] == currentRecipe) {
                    recipeIndex = i;
                    break;
                }
            }

            font.getData().setScale(0.8f);
            if (recipeIndex != -1 && unlockedRecipes[recipeIndex]) {
                font.draw(batch, currentRecipe.getName(), x + 240, y - 47);
                font.getData().setScale(0.5f);

                // Draw ingredients
                int yPos = (int)(y - 65);
                font.draw(batch, "Ingredients:", x + 240, yPos);
                yPos -= 15;

                for (Map.Entry<org.Group34.model.items.Item, Integer> entry : currentRecipe.getIngredients().entrySet()) {
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

                // Draw energy and price
                yPos -= 5;
                font.draw(batch, "Energy: " + currentRecipe.getEnergy(), x + 240, yPos);
                yPos -= 15;
                font.draw(batch, "Price: " + currentRecipe.getPrice(), x + 240, yPos);
            } else {
                font.draw(batch, "???", x + 240, y - 47);
                font.getData().setScale(0.5f);
                font.draw(batch, "Learn this recipe to see details", x + 240, y - 65);
            }
        }
    }

    private static com.badlogic.gdx.graphics.Texture getTexture(CookedFood food) {
        switch (food) {
            case FRIED_EGG: return CookingAssetManager.friedEgg;
            case BAKED_FISH: return CookingAssetManager.bakedFish;
            case SALAD: return CookingAssetManager.salad;
            case OMELET: return CookingAssetManager.omelet;
            case PUMPKIN_PIE: return CookingAssetManager.pumpkinPie;
            case SPAGHETTI: return CookingAssetManager.spaghetti;
            case PIZZA: return CookingAssetManager.pizza;
            case TORTILLA: return CookingAssetManager.tortilla;
            case MAKI_ROLL: return CookingAssetManager.makiRoll;
            case TRIPLE_SHOT_ESPRESSO: return CookingAssetManager.tripleShotEspresso;
            case COOKIE: return CookingAssetManager.cookie;
            case HASH_BROWNS: return CookingAssetManager.hashBrowns;
            case PANCAKES: return CookingAssetManager.pancakes;
            case FRUIT_SALAD: return CookingAssetManager.fruitSalad;
            case RED_PLATE: return CookingAssetManager.redPlate;
            case BREAD: return CookingAssetManager.bread;
            case SALMON_DINNER: return CookingAssetManager.salmonDinner;
            case VEGETABLE_MEDLEY: return CookingAssetManager.vegetableMedley;
            case FARMERS_LUNCH: return CookingAssetManager.farmersLunch;
            case SURVIVAL_BURGER: return CookingAssetManager.survivalBurger;
            case DISH_O_THE_SEA: return CookingAssetManager.dishOTheSea;
            case SEAFOAM_PUDDING: return CookingAssetManager.seafoamPudding;
            case MINERS_TREAT: return CookingAssetManager.minersTreat;
            default: return null;
        }
    }
}