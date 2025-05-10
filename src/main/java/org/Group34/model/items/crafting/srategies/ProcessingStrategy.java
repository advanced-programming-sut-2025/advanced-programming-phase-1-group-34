package org.Group34.model.items.crafting.srategies;

import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;

import java.util.HashMap;

public enum ProcessingStrategy {
    MAYONNAISE_MACHINE {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            return new HashMap<>();
        }
    },
    CHEESE_PRESS {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            return new HashMap<>();
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
            return new HashMap<>();
        }
    },
    LOOM {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            return new HashMap<>();
        }
    },
    KEG {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            return new HashMap<>();
        }
    },
    PRESERVES_JAR {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            return new HashMap<>();
        }
    },
    DEHYDRATOR {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            return new HashMap<>();
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
            return new HashMap<>();
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
