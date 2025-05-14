package org.Group34.model.items.crafting.srategies;

import org.Group34.model.entities.Player;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.enums.FishType;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.foods.*;
import org.Group34.model.items.Item;
import org.Group34.model.items.crafting.Ingredient;


public enum ProcessingStrategy {

    BEE_HOUSE {
        @Override
        public Item process(Player player, Item input) {
            // No input consumed, always returns honey
            return ProcessedFood.HONEY;
        }
    },
    MAYONNAISE_MACHINE {
        @Override
        public Item process(Player player, Item input) {
            if (input.equals(Product.EGG) || input.equals(Product.LARGE_EGG)) {
                return output(input, ProcessedFood.MAYONNAISE, player, 1);
            }
            if (input.equals(Product.DUCK_EGG)) {
                return output(input, ProcessedFood.DUCK_MAYONNAISE, player, 1);
            }
            if (input.equals(Product.DINOSAUR_EGG)) {
                return output(input, ProcessedFood.DINOSAUR_MAYONNAISE, player, 1);
            }
            return null;
        }
    },
    FURNACE {
        @Override
        public Item process(Player player, Item input_1, Item input_2) {
            // Requires one ore and one coal
            if (input_2.equals(Ingredient.COAL)){
                if (input_1.equals(Ingredient.IRON_ORE)) {
                    return output(Ingredient.IRON_ORE, Ingredient.COAL, Ingredient.IRON_BAR, player, 5, 1);
                }
                if (input_1.equals(Ingredient.COPPER_ORE)) {
                    return output(Ingredient.COPPER_ORE, Ingredient.COAL, Ingredient.COPPER_BAR, player, 5, 1);
                }
                if (input_1.equals(Ingredient.GOLD_ORE)) {
                    return output(Ingredient.GOLD_ORE, Ingredient.COAL, Ingredient.GOLD_BAR, player, 5, 1);
                }
                if (input_1.equals(Ingredient.IRIDIUM_ORE)) {
                    return output(Ingredient.IRIDIUM_ORE, Ingredient.COAL, Ingredient.IRON_BAR, player, 5, 1);
                }
            }
            return null;
        }
    },
    OIL_MAKER {
        @Override
        public Item process(Player player, Item input) {
            if (input.equals(Product.TRUFFLE)) {
                return output(input, ProcessedFood.TRUFFLE_OIL, player, 1);
            }
            if (input.equals(Vegetable.CORN)
                    || input.equals(OtherFarmingProduct.SUNFLOWER)
                    || input.equals(PlantingSource.SUNFLOWER_SEEDS)) {
                return output(input, ProcessedFood.OIL, player, 1);
            }
            return null;
        }
    },
    LOOM {
        @Override
        public Item process(Player player, Item input) {
            if (input.equals(Product.SHEEP_WOOL) || input.equals(Product.RABBIT_WOOL)) {
                return output(input, ProcessedFood.CLOTH, player, 1);
            }
            return null;
        }
    },
    KEG {
        @Override
        public Item process(Player player, Item input) {
            if (input.equals(CropProduct.WHEAT)) {
                return output(input, ProcessedFood.BEER, player, 1);
            }
            if (input.equals(CropProduct.HOPS)) {
                return output(input, ProcessedFood.PALE_ALE, player, 1);
            }
            if (input.equals(ProcessedFood.HONEY)) {
                return output(input, ProcessedFood.MEAD, player, 1);
            }
            if (input.equals(CropProduct.COFFEE_BEAN)) {
                return output(input, ProcessedFood.COFFEE, player, 5);
            }
            if (input.equals(CropProduct.UNMILLED_RICE)) {
                return output(input, ProcessedFood.VINEGAR, player, 1);
            }
            if (input instanceof Fruit) {
                return output(input, ((Fruit) input).getWineForm(), player, 1);
            }
            if (input instanceof Vegetable) {
                return output(input, ((Vegetable) input).getJuiceForm(), player, 1);
            }
            return null;
        }
    },
    PRESERVES_JAR {
        @Override
        public Item process(Player player, Item input) {
            if (input instanceof Fruit) {
                return output(input, ((Fruit) input).getJellyForm(), player, 1);
            }
            if (input instanceof Vegetable) {
                return output(input, ((Vegetable) input).getPickleForm(), player, 1);
            }
            return null;
        }
    },
    DEHYDRATOR {
        @Override
        public Item process(Player player, Item input) {
            if (input.equals(CropProduct.GRAPE)) {
                return output(input, ProcessedFood.RAISINS, player, 5);
            }
            if (input.equals(Fungi.COMMON_MUSHROOM)) {
                return output(input, ProcessedFood.DRIED_COMMON_MUSHROOM, player, 5);
            }
            if (input instanceof Fruit) {
                return output(input, ((Fruit) input).getDriedForm(), player, 5);
            }
            return null;
        }
    },
    CHARCOAL_KILN {
        @Override
        public Item process(Player player, Item input) {
            if (input.equals(Ingredient.WOOD)) {
                return output(input, Ingredient.COAL, player, 10);
            }
            return null;
        }
    },
    CHEESE_PRESS {
        @Override
        public Item process(Player player, Item input) {
            if (input.equals(Product.MILK)) {
                return output(input, ProcessedFood.CHEESE, player, 1);
            }
            if (input.equals(Product.LARGE_MILK)) {
                return output(input, ProcessedFood.LARGE_CHEESE, player, 1);
            }
            if (input.equals(Product.GOAT_MILK)) {
                return output(input, ProcessedFood.GOAT_CHEESE, player, 1);
            }
            if (input.equals(Product.LARGE_GOAT_MILK)) {
                return output(input, ProcessedFood.LARGE_GOAT_CHEESE, player, 1);
            }
            return null;
        }
    },
    FISH_SMOKER {
        @Override
        public Item process(Player player, Item input_1, Item input_2) {
            if (input_1 instanceof FishType && input_2.equals(Ingredient.COAL)) {
                return output(input_1, input_2, ((FishType) input_1).getSmokedForm(), player, 1, 1);
            }
            return null;
        }
    };

    // New abstract method signature
    public Item process(Player player, Item input){
        return null;
    }
    public Item process(Player player, Item input_1, Item input_2){
        return null;
    }

    // Single-input output
    protected Item output(Item input, Item output, Player player, int inputNeeded) {
        int inventoryAmount = player.getAmountOfItem(input);
        if (inventoryAmount < inputNeeded) return input;
        player.removeFromInventory(input, inputNeeded);
        return output;
    }

    // Two-input output (ore + coal)
    protected Item output(Item input1, Item input2, Item output, Player player, int input1Needed, int input2Needed) {
        int inv1 = player.getAmountOfItem(input1);
        int inv2 = player.getAmountOfItem(input2);

        if (inv1 < input1Needed) return input1;
        if (inv2 < input2Needed) return input2;

        player.removeFromInventory(input1, input1Needed);
        player.removeFromInventory(input2, input2Needed);
        return output;
    }
}
