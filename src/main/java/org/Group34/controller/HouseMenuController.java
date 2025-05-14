package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Player;
import org.Group34.model.items.Item;
import org.Group34.model.items.Recipe;
import org.Group34.model.items.crafting.Craft;
import org.Group34.model.items.crafting.PlacingCraft;
import org.Group34.model.map.Space;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HouseMenuController {

    public Result showRecipes(Player player){
        String message = "Your Recipes:";

        for (Recipe recipe: player.getLearnedRecipes()){
            Map<Item, Integer> ingredients = recipe.getProduct().getIngredients();

            message += "\n" + recipe.getName();
            if (canMake(player, ingredients) != null) message += ":     can make";
            else message += ":      -";
        }
        return new Result(true, message);
    }


    public Result craftItem(Player player, Craft craft){
        ArrayList<Recipe> recipes = player.getLearnedRecipes();
        Map<Item, Integer> ingredients = craft.getIngredients();

        if (!recipes.contains(craft.getRecipe()))
            return new Result(false, "You should learn the recipe first!");

        Result canMake = canMake(player, ingredients);
        if (canMake != null) return canMake;

        player.addToInventory(craft, 1);
        for (Item item: ingredients.keySet())
            player.removeFromInventory(item, ingredients.get(item));

        return new Result(true, "Item " + craft.getName() + " has been crafted.");
    }

    private Result canMake(Player player, Map<Item, Integer> ingredients){
        HashMap<Item, Integer> inventory = player.getInventory();

        for (Item item: ingredients.keySet())
            if (!inventory.containsKey(item) || inventory.get(item) < ingredients.get(item))
                return new Result(false, "You don't have enough " + item.getName() + " in your inventory!");
        return null;
    }
}
