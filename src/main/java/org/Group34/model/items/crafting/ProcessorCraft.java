package org.Group34.model.items.crafting;

import org.Group34.model.entities.Entity;
import org.Group34.model.items.Item;
import org.Group34.model.items.Recipe;
import org.Group34.model.items.crafting.srategies.ProcessingStrategy;
import java.util.Map;

public enum ProcessorCraft implements Craft, Entity {
    BEE_HOUSE(
            "Bee House", Recipe.BEE_HOUSE,
            "Produces honey when placed outside.",
            Map.of(Ingredient.WOOD, 40, Ingredient.COAL, 8, Ingredient.IRON_BAR, 1),
            "Farming Level 1", 0, ProcessingStrategy.BEE_HOUSE),
    CHARCOAL_KILN(
            "Charcoal Kiln", Recipe.CHARCOAL_KILN,
            "Turns 10 wood into 1 coal.",
            Map.of(Ingredient.WOOD, 20, Ingredient.COPPER_BAR, 2),
            "Foraging Level 1", 0, ProcessingStrategy.CHARCOAL_KILN),
    FURNACE(
            "Furnace", Recipe.FURNACE,
            "Smelts ores and coal into bars.",
            Map.of(Ingredient.COPPER_ORE, 20, Ingredient.COAL, 1, Ingredient.STONE, 25),
            "-", 0, ProcessingStrategy.FURNACE),
    CHEESE_PRESS(
            "Cheese Press", Recipe.CHEESE_PRESS,
            "Turns milk into cheese.",
            Map.of(Ingredient.WOOD, 45, Ingredient.STONE, 45, Ingredient.COPPER_BAR, 1),
            "Farming Level 2", 0, ProcessingStrategy.CHEESE_PRESS),
    KEG(
            "Keg", Recipe.KEG,
            "Turns vegetables and fruit into beverages.",
            Map.of(Ingredient.WOOD, 30, Ingredient.COPPER_BAR, 1, Ingredient.IRON_BAR, 1),
            "Farming Level 3", 0, ProcessingStrategy.KEG),
    LOOM(
            "Loom", Recipe.LOOM,
            "Turns wool into cloth.",
            Map.of(Ingredient.WOOD, 60, Ingredient.FIBER, 30),
            "Farming Level 3", 0, ProcessingStrategy.LOOM),
    MAYONNAISE_MACHINE(
            "Mayonnaise Machine", Recipe.MAYONNAISE_MACHINE,
            "Turns eggs into mayonnaise.",
            Map.of(Ingredient.WOOD, 15, Ingredient.STONE, 15, Ingredient.COPPER_BAR, 1),
            "-", 0, ProcessingStrategy.MAYONNAISE_MACHINE),
    OIL_MAKER(
            "Oil Maker", Recipe.OIL_MAKER,
            "Turns truffle into oil.",
            Map.of(Ingredient.WOOD, 100, Ingredient.GOLD_BAR, 1, Ingredient.IRON_BAR, 1),
            "Farming Level 3", 0, ProcessingStrategy.OIL_MAKER),
    PRESERVES_JAR(
            "Preserves Jar", Recipe.PRESERVES_JAR,
            "Turns vegetables into pickles and fruit into jam.",
            Map.of(Ingredient.WOOD, 50, Ingredient.STONE, 40, Ingredient.COAL, 8),
            "Farming Level 2", 0, ProcessingStrategy.PRESERVES_JAR),
    DEHYDRATOR(
            "Dehydrator", Recipe.DEHYDRATOR,
            "Dries fruit or mushrooms.",
            Map.of(Ingredient.WOOD, 30, Ingredient.STONE, 20, Ingredient.FIBER, 30),
            "Pierre's General Store", 0, ProcessingStrategy.DEHYDRATOR),
    FISH_SMOKER(
            "Fish Smoker", Recipe.FISH_SMOKER,
            "Turns any fish into smoked fish with preserved quality.",
            Map.of(Ingredient.WOOD, 50, Ingredient.IRON_BAR, 3, Ingredient.COAL, 10),
            "Fish Shop", 0, ProcessingStrategy.FISH_SMOKER);

    private final String name;
    private final Recipe recipe;
    private final String description;
    private final Map<Item, Integer> ingredients;
    private final String source;
    private final int sellPrice;
    private final ProcessingStrategy strategy;

    ProcessorCraft(String name, Recipe recipe, String description, Map<Item, Integer> ingredients,
                   String source, int sellPrice, ProcessingStrategy strategy) {
        this.name = name;
        this.recipe = recipe;
        this.description = description;
        this.ingredients = ingredients;
        this.source = source;
        this.sellPrice = sellPrice;
        this.strategy = strategy;
    }

    @Override
    public String getName() { return name; }

    public Map<Item, Integer> getIngredients() { return ingredients; }

    public int getPrice() { return sellPrice; }

    public String getSource() { return source; }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getDescription() {
        return description;
    }

    public Map<Item, Integer> process(Item item, int amount) {
        return strategy.process(item, amount);
    }

    public Map<Item, Integer> process(Item item, int amount, Ingredient coal, int coalAmount) {
        return strategy.process(item, amount, coal, coalAmount);
    }

}
