package org.Group34.model.items.foods;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.enums.FishType;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.gameAssetManagers.CookingAssetManager;
import org.Group34.model.items.Item;
import org.Group34.model.items.Recipe;
import org.Group34.model.items.crafting.Craft;
import org.Group34.model.items.crafting.Ingredient;

import java.util.Map;

public enum CookedFood implements Craft, Food {
    FRIED_EGG("Fried Egg", Recipe.FRIED_EGG, Map.of(Product.EGG, 1), 50, 35),
    BAKED_FISH("Baked Fish", Recipe.BAKED_FISH, Map.of(FishType.SARDINE, 1, FishType.SALMON, 1, CropProduct.WHEAT, 1), 75, 100),
    SALAD("Salad", Recipe.SALAD, Map.of(CropProduct.LEEK, 1, CropProduct.DANDELION, 1), 113, 110),
    OMELET("Omelet", Recipe.OMELET, Map.of(Product.EGG, 1, Product.MILK, 1), 100, 125),
    PUMPKIN_PIE("Pumpkin Pie", Recipe.PUMPKIN_PIE, Map.of(Vegetable.PUMPKIN, 1, ProcessedFood.WHEAT_FLOUR, 1, Product.MILK, 1, ProcessedFood.SUGAR, 1), 225, 385),
    SPAGHETTI("Spaghetti", Recipe.SPAGHETTI, Map.of(ProcessedFood.WHEAT_FLOUR, 1, Vegetable.TOMATO, 1), 75, 120),
    PIZZA("Pizza", Recipe.PIZZA, Map.of(ProcessedFood.WHEAT_FLOUR, 1, Vegetable.TOMATO, 1, ProcessedFood.CHEESE, 1), 150, 300),
    TORTILLA("Tortilla", Recipe.TORTILLA, Map.of(Vegetable.CORN, 1), 50, 50),
    MAKI_ROLL("Maki Roll", Recipe.MAKI_ROLL, Map.of(FishType.ALL, 1, CropProduct.UNMILLED_RICE, 1, Ingredient.FIBER, 1), 100, 220),
    TRIPLE_SHOT_ESPRESSO("Triple Shot Espresso", Recipe.TRIPLE_SHOT_ESPRESSO, Map.of(CropProduct.COFFEE_BEAN, 3), 200, 450),
    COOKIE("Cookie", Recipe.COOKIE, Map.of(ProcessedFood.WHEAT_FLOUR, 1, ProcessedFood.SUGAR, 1, Product.EGG, 1), 90, 140),
    HASH_BROWNS("Hash Browns", Recipe.HASH_BROWNS, Map.of(Vegetable.POTATO, 1, ProcessedFood.OIL, 1), 90, 120),
    PANCAKES("Pancakes", Recipe.PANCAKES, Map.of(ProcessedFood.WHEAT_FLOUR, 1, Product.EGG, 1), 90, 80),
    FRUIT_SALAD("Fruit Salad", Recipe.FRUIT_SALAD, Map.of(Fruit.BLUEBERRY, 1, Fruit.MELON, 1, Fruit.APRICOT, 1), 263, 450),
    RED_PLATE("Red Plate", Recipe.RED_PLATE, Map.of(Vegetable.RED_CABBAGE, 1, Vegetable.RADISH, 1), 240, 400),
    BREAD("Bread", Recipe.BREAD, Map.of(ProcessedFood.WHEAT_FLOUR, 1), 50, 60),
    SALMON_DINNER("Salmon Dinner", Recipe.SALMON_DINNER, Map.of(FishType.SALMON, 1, Vegetable.AMARANTH, 1, Vegetable.KALE, 1), 125, 300),
    VEGETABLE_MEDLEY("Vegetable Medley", Recipe.VEGETABLE_MEDLEY, Map.of(Vegetable.TOMATO, 1, Vegetable.BEET, 1), 165, 120),
    FARMERS_LUNCH("Farmer's Lunch", Recipe.FARMERS_LUNCH, Map.of(CookedFood.OMELET, 1, Vegetable.PARSNIP, 1), 200, 150),
    SURVIVAL_BURGER("Survival Burger", Recipe.SURVIVAL_BURGER, Map.of(CookedFood.BREAD, 1, Vegetable.CARROT, 1, Vegetable.EGGPLANT, 1), 125, 180),
    DISH_O_THE_SEA("Dish O' the Sea", Recipe.DISH_O_THE_SEA, Map.of(FishType.SARDINE, 2, CookedFood.HASH_BROWNS, 1), 150, 220),
    SEAFOAM_PUDDING("Seafoam Pudding", Recipe.SEAFOAM_PUDDING, Map.of(FishType.FLOUNDER, 1, FishType.MIDNIGHT_CARP, 1), 175, 300),
    MINERS_TREAT("Miner's Treat", Recipe.MINERS_TREAT, Map.of(Vegetable.CARROT, 2, ProcessedFood.SUGAR, 1, Product.MILK, 1), 125, 200);

    private final String name;
    private final Recipe recipe;
    private final Map<Item, Integer> ingredients;
    private final int energy;
    private final int price;

    CookedFood(String name, Recipe recipe, Map<Item, Integer> ingredients, int energy, int price) {
        this.name = name;
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.energy = energy;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public String getDescription() {
        return null;
    }

    public Map<Item, Integer> getIngredients() {
        return ingredients;
    }

    public int getEnergy() {
        return energy;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String getSource() {
        return null;
    }

    @Override
    public Texture getTexture() {
        return switch (this) {
            // Cooked food recipes
            case FRIED_EGG -> CookingAssetManager.friedEgg;
            case BAKED_FISH -> CookingAssetManager.bakedFish;
            case SALAD -> CookingAssetManager.salad;
            case OMELET -> CookingAssetManager.omelet;
            case PUMPKIN_PIE -> CookingAssetManager.pumpkinPie;
            case SPAGHETTI -> CookingAssetManager.spaghetti;
            case PIZZA -> CookingAssetManager.pizza;
            case TORTILLA -> CookingAssetManager.tortilla;
            case MAKI_ROLL -> CookingAssetManager.makiRoll;
            case TRIPLE_SHOT_ESPRESSO -> CookingAssetManager.tripleShotEspresso;
            case COOKIE -> CookingAssetManager.cookie;
            case HASH_BROWNS -> CookingAssetManager.hashBrowns;
            case PANCAKES -> CookingAssetManager.pancakes;
            case FRUIT_SALAD -> CookingAssetManager.fruitSalad;
            case RED_PLATE -> CookingAssetManager.redPlate;
            case BREAD -> CookingAssetManager.bread;
            case SALMON_DINNER -> CookingAssetManager.salmonDinner;
            case VEGETABLE_MEDLEY -> CookingAssetManager.vegetableMedley;
            case FARMERS_LUNCH -> CookingAssetManager.farmersLunch;
            case SURVIVAL_BURGER -> CookingAssetManager.survivalBurger;
            case DISH_O_THE_SEA -> CookingAssetManager.dishOTheSea;
            case SEAFOAM_PUDDING -> CookingAssetManager.seafoamPudding;
            case MINERS_TREAT -> CookingAssetManager.minersTreat;

            // For other recipes, return null or a default texture
            default -> null;
        };
    }
}
