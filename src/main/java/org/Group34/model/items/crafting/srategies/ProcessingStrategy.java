package org.Group34.model.items.crafting.srategies;

import org.Group34.model.enums.animals.Product;
import org.Group34.model.enums.FishType;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.foods.*;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;

import java.util.HashMap;

public enum ProcessingStrategy {

    BEE_HOUSE{
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            return new HashMap<>(){{
                put(ProcessedFood.HONEY, 1);
            }};
        }
    },
    MAYONNAISE_MACHINE{
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(Product.EGG) || input.equals(Product.LARGE_EGG)) {
                return this.output(input, ProcessedFood.MAYONNAISE, amount, 1);
            }
            if (input.equals(Product.DUCK_EGG)) {
                return this.output(input, ProcessedFood.DUCK_MAYONNAISE, amount, 1);
            }
            if (input.equals(Product.DINOSAUR_EGG)) {
                return this.output(input, ProcessedFood.DINOSAUR_MAYONNAISE, amount, 1);
            }
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },

    FURNACE {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount, Ingredient coal, int coalAmount) {
            if (coal.equals(Ingredient.COAL)){
                if (input.equals(Ingredient.IRON_ORE))
                    return this.output(Ingredient.IRON_ORE, Ingredient.IRON_BAR, amount, 5, coalAmount);
                if (input.equals(Ingredient.COPPER_ORE))
                    return this.output(Ingredient.COPPER_ORE, Ingredient.COPPER_BAR, amount, 5, coalAmount);
                if (input.equals(Ingredient.GOLD_ORE))
                    return this.output(Ingredient.GOLD_ORE, Ingredient.GOLD_BAR, amount, 5, coalAmount);
                if (input.equals(Ingredient.IRIDIUM_ORE))
                    return this.output(Ingredient.IRIDIUM_ORE, Ingredient.IRON_BAR, amount, 5, coalAmount);
            }
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    OIL_MAKER {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(Product.TRUFFLE)) {
                return this.output(input, ProcessedFood.TRUFFLE_OIL, amount, 1);
            }
            if (input.equals(Vegetable.CORN) ||
                    input.equals(OtherFarmingProduct.SUNFLOWER) ||
                    input.equals(PlantingSource.SUNFLOWER_SEEDS)) {
                return this.output(input, ProcessedFood.OIL, amount, 1);
            }
            return new HashMap<>() {{
                put(input, amount);
            }};
        }


    },
    LOOM {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(Product.SHEEP_WOOL))
                return this.output(input, ProcessedFood.CLOTH, amount, 1);
            if (input.equals(Product.RABBIT_WOOL))
                return this.output(input, ProcessedFood.CLOTH, amount, 1);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    KEG {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(CropProduct.WHEAT))
                return this.output(input, ProcessedFood.BEER, amount, 1);
            if (input.equals(CropProduct.HOPS))
                return this.output(input, ProcessedFood.PALE_ALE, amount, 1);
            if (input.equals(ProcessedFood.HONEY))
                return this.output(input, ProcessedFood.MEAD, amount, 1);
            if (input.equals(CropProduct.COFFEE_BEAN))
                return this.output(input, ProcessedFood.COFFEE, amount, 5);
            if (input.equals(CropProduct.UNMILLED_RICE))
                return this.output(input, ProcessedFood.VINEGAR, amount, 1);
            if (input instanceof Fruit)
                return this.output(input, ((Fruit) input).getWineForm(), amount, 1);
            if (input instanceof  Vegetable)
                return this.output(input, ((Vegetable) input).getJuiceForm(), amount, 1);

            return new HashMap<>() {{
                put(input, amount);
            }};
        }
    },

    PRESERVES_JAR {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input instanceof Fruit)
                return this.output(input, ((Fruit) input).getJellyForm(), amount, 1);
            if (input instanceof Vegetable)
                return this.output(input, ((Vegetable) input).getPickleForm(), amount, 1);
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    },
    DEHYDRATOR {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(CropProduct.GRAPE)) {
                return this.output(input, ProcessedFood.RAISINS, amount, 5);
            }
            if (input.equals(Fungi.COMMON_MUSHROOM)) {
                return this.output(input, ProcessedFood.DRIED_COMMON_MUSHROOM, amount, 5);
            }
            if (input instanceof Fruit) {
                return this.output(input, ((Fruit) input).getDriedForm(), amount, 5);
            }
            return new HashMap<>() {{
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
    CHEESE_PRESS {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            if (input.equals(Product.MILK))
                return this.output(input, ProcessedFood.CHEESE, amount, 1);
            if (input.equals(Product.LARGE_MILK))
                return this.output(input, ProcessedFood.LARGE_CHEESE, amount, 1);
            if (input.equals(Product.GOAT_MILK))
                return this.output(input, ProcessedFood.GOAT_CHEESE, amount, 1);
            if (input.equals(Product.LARGE_GOAT_MILK))
                return this.output(input, ProcessedFood.LARGE_GOAT_CHEESE, amount, 1);
            return new HashMap<>() {{
                put(input, amount);
            }};
        }
    },
    FISH_SMOKER {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount, Ingredient coal, int coalAmount) {
            if (input instanceof FishType && coal.equals(Ingredient.COAL)) {
                return this.output(input, ((FishType) input).getSmokedForm(), amount, 1, coalAmount);
            }
            return new HashMap<>(){{
                put(input, amount);
            }};
        }
    };

    public HashMap<Item, Integer> process(Item input, int amount){
        return null;
    }
    public HashMap<Item, Integer> process(Item input, int amount, Ingredient coal, int coalAmount){
        return null;
    }


    protected HashMap<Item, Integer> output(Item input, Item output, int inputAmount, int ratio){
        HashMap<Item, Integer> result = new HashMap<>();

        int outputAmount = inputAmount / ratio;
        int remainingInput = inputAmount % ratio;
        result.put(output, outputAmount);
        if (remainingInput != 0) result.put(input, remainingInput);

        return result;
    }

    protected HashMap<Item, Integer> output(Item input, Item output, int amount, int ratio, int coalAmount){
        HashMap<Item, Integer> result;

        if (coalAmount > amount){
            result = this.output(input, output, amount, ratio);
            result.put(Ingredient.COAL, coalAmount-amount);
        }
        if (coalAmount < amount){
            result = this.output(input, output, coalAmount, ratio);
            result.put(input, amount-coalAmount);
        }
        else result = this.output(input, output, amount, ratio);

        return result;
    }

}
