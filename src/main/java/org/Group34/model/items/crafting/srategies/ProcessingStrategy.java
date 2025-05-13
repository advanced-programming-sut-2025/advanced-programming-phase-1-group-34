package org.Group34.model.items.crafting.srategies;

import org.Group34.model.enums.animals.Product;
import org.Group34.model.enums.FishType;
import org.Group34.model.items.foods.*;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;

import java.util.HashMap;

public enum ProcessingStrategy {
    MAYONNAISE_MACHINE {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(Product.EGG) || input.equals(Product.LARGE_EGG) ||
                    input.equals(Product.DUCK_EGG))
                return this.output(input, new ProcessedFood(ProcessedFoodType.MAYONNAISE, input), amount, 1);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    CHEESE_PRESS {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(Product.MILK) || input.equals(Product.LARGE_MILK) ||
                    input.equals(Product.GOAT_MILK) || input.equals(Product.LARGE_GOAT_MILK))
                return this.output(input, new ProcessedFood(ProcessedFoodType.CHEESE, input), amount, 1);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    FURNACE {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(Ingredient.IRON_ORE))
                return this.output(Ingredient.IRON_ORE, Ingredient.IRON_BAR, amount, 1);
            if (input.equals(Ingredient.COPPER_ORE))
                return this.output(Ingredient.COPPER_ORE, Ingredient.COPPER_BAR, amount, 1);
            if (input.equals(Ingredient.GOLD_ORE))
                return this.output(Ingredient.GOLD_ORE, Ingredient.GOLD_BAR, amount, 1);
            if (input.equals(Ingredient.IRIDIUM_ORE))
                return this.output(Ingredient.IRIDIUM_ORE, Ingredient.IRON_BAR, amount, 1);
            else return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    OIL_MAKER {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(Product.TRUFFLE))
                return this.output(input, new ProcessedFood(ProcessedFoodType.OIL, input), amount, 1);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    LOOM {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(Product.SHEEP_WOOL))
                return this.output(input, Ingredient.SHEEP_FABRIC, amount, 1);
            if (input.equals(Product.RABBIT_WOOL))
                return this.output(input, Ingredient.RABBIT_FABRIC, amount, 1);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    KEG {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input instanceof Fruit || input instanceof Vegetable)
                return this.output(input, new ProcessedFood(ProcessedFoodType.JUICE, input), amount, 10);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    PRESERVES_JAR {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input instanceof Fruit)
                return this.output(input, new ProcessedFood(ProcessedFoodType.JAM, input), amount, 1);
            if (input instanceof Vegetable)
                return this.output(input, new ProcessedFood(ProcessedFoodType.PICKLE, input), amount, 1);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    DEHYDRATOR {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input instanceof Fruit || input instanceof Fungi)
                return this.output(input, new ProcessedFood(ProcessedFoodType.DEHYDRATED_FOOD, input), amount, 1);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    CHARCOAL_KILN {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(Ingredient.WOOD))
                return this.output(Ingredient.WOOD, Ingredient.COAL, amount, 10);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    FISH_SMOKER {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input instanceof FishType)
                return this.output(input, new ProcessedFood(ProcessedFoodType.SMOKED_FISH, input), amount, 1);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    };

    public abstract HashMap<Item, Integer> process(Item input, int amount);

    protected HashMap<Item, Integer> output(Item input, Item output, int inputAmount, int ratio){
        HashMap<Item, Integer> result = new HashMap<>();

        int outputAmount = inputAmount / ratio;
        int remainingInput = inputAmount % ratio;
        result.put(output, outputAmount);
        if (remainingInput != 0) result.put(input, remainingInput);

        return result;
    }
}
