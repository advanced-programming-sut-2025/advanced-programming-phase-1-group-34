package org.Group34.model.items.crafting;

import org.Group34.model.entities.Entity;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.srategies.PlacingStrategy;
import org.Group34.model.items.crafting.srategies.ProcessingStrategy;
import org.Group34.model.map.Space;
import java.util.Map;

public enum PlacingCraft implements Item, Entity {
    SPRINKLER(
            "Sprinkler", Recipe.SPRINKLER,
            "Waters 4 adjacent tiles.",
            Map.of(Ingredient.COPPER_BAR, 1, Ingredient.IRON_BAR, 1),
            "Farming Level 1", 0, PlacingStrategy.PLACE),
    QUALITY_SPRINKLER(
            "Quality Sprinkler", Recipe.QUALITY_SPRINKLER,
            "Waters 8 adjacent tiles.",
            Map.of(Ingredient.IRON_BAR, 1, Ingredient.GOLD_BAR, 1),
            "Farming Level 2", 0, PlacingStrategy.PLACE),
    IRIDIUM_SPRINKLER(
            "Iridium Sprinkler", Recipe.IRIDIUM_SPRINKLER,
            "Waters 24 adjacent tiles.",
            Map.of(Ingredient.GOLD_BAR, 1, Ingredient.IRIDIUM_BAR, 1),
            "Farming Level 3", 0, PlacingStrategy.PLACE),
    SCARECROW(
            "Scarecrow", Recipe.SCARECROW,
            "Protects an 8-tile radius from crows.",
            Map.of(Ingredient.WOOD, 50, Ingredient.COAL, 1, Ingredient.FIBER, 20),
            "-", 0, PlacingStrategy.PLACE),
    DELUXE_SCARECROW(
            "Deluxe Scarecrow", Recipe.DELUXE_SCARECROW,
            "Protects a 12-tile radius from crows.",
            Map.of(Ingredient.WOOD, 50, Ingredient.COAL, 1, Ingredient.FIBER, 20, Ingredient.IRIDIUM_ORE, 1),
            "Farming Level 2", 0, PlacingStrategy.PLACE),
    BEE_HOUSE(
            "Bee House", Recipe.BEE_HOUSE,
            "Produces honey when placed outside.",
            Map.of(Ingredient.WOOD, 40, Ingredient.COAL, 8, Ingredient.IRON_BAR, 1),
            "Farming Level 1", 0, PlacingStrategy.PLACE),
    CHERRY_BOMB(
            "Cherry Bomb", Recipe.CHERRY_BOMB,
            "Destroys everything in a 3-tile radius.",
            Map.of(Ingredient.COPPER_ORE, 4, Ingredient.COAL, 1),
            "Mining Level 1", 50, PlacingStrategy.CHERRY_BOMB),
    BOMB(
            "Bomb", Recipe.BOMB,
            "Destroys everything in a 5-tile radius.",
            Map.of(Ingredient.IRON_ORE, 4, Ingredient.COAL, 1),
            "Mining Level 2", 50, PlacingStrategy.BOMB),
    MEGA_BOMB(
            "Mega Bomb", Recipe.MEGA_BOMB,
            "Destroys everything in a 7-tile radius.",
            Map.of(Ingredient.GOLD_ORE, 4, Ingredient.COAL, 1),
            "Mining Level 3", 50, PlacingStrategy.MEGA_BOMB),
    GRASS_STARTER(
            "Grass Starter", Recipe.GRASS_STARTER,
            "Grows grass on the tile where it's placed.",
            Map.of(Ingredient.WOOD, 1, Ingredient.FIBER, 1),
            "Pierre's General Store", 0, PlacingStrategy.GRASS_STARTER),
    MYSTIC_TREE_SEED(
            "Mystic Tree Seed", Recipe.MYSTIC_TREE_SEED,
            "Can be planted to grow a Mystic Tree.",
            Map.of(Ingredient.ACORN, 5, Ingredient.MAPLE_SEED, 5, Ingredient.PINE_CONE, 5, Ingredient.MAHOGANY_SEED, 5),
            "Foraging Level 4", 100, PlacingStrategy.MYSTIC_TREE_SEED);


    private final String name;
    private final Recipe recipe;
    private final String description;
    private final Map<Item, Integer> ingredients;
    private final String source;
    private final int price;
    private final PlacingStrategy placingStrategy;

    PlacingCraft(String name, Recipe recipe, String description, Map<Item, Integer> ingredients,
                 String source, int price, PlacingStrategy placingStrategy) {
        this.name = name;
        this.recipe = recipe;
        this.description = description;
        this.ingredients = ingredients;
        this.source = source;
        this.price = price;
        this.placingStrategy = placingStrategy;
    }

    @Override
    public String getName() { return name; }

    public boolean place(Space space, int x, int y) {
        return placingStrategy.place(space, this, x, y);
    }

    public Map<Item, Integer> getIngredients() { return ingredients; }

    public int getPrice() { return price; }

    public String getSource() { return source; }
}
