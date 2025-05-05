package org.Group34.model.items.crafting.srategies;

import org.Group34.model.items.Item;

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
            return new HashMap<>();
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
    BEE_HOUSE {
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
            return new HashMap<>();
        }
    },
    FISH_SMOKER {
        @Override
        public HashMap<Item, Integer> process(Item input, int amount) {
            return new HashMap<>();
        }
    };

    public abstract HashMap<Item, Integer> process(Item input, int amount);
}
