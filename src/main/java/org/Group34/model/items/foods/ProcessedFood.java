package org.Group34.model.items.foods;

public enum ProcessedFood implements Food {
    HONEY("Honey", "It's a sweet syrup produced by bees.", 75, 350),
    CLOTH("Cloth", "A bolt of fine wool cloth.", 0, 470),
    CHEESE("Cheese", "It's your basic cheese.", 100, 230),
    LARGE_CHEESE("Large Cheese", "It's your basic cheese.", 100, 345),
    MAYONNAISE("Mayonnaise", "It looks spreadable.", 50, 190),
    GOAT_CHEESE("Goat Cheese", "Soft cheese made from goat's milk.", 100, 400),
    LARGE_GOAT_CHEESE("Large Goat Cheese", "Soft cheese made from goat's milk.", 100, 600),
    DUCK_MAYONNAISE("Duck Mayonnaise", "It's a rich, yellow mayonnaise.", 75, 37),
    DINOSAUR_MAYONNAISE("Dinosaur Mayonnaise", "It's thick and creamy, with a vivid green hue. It smells like grass and leather.", 125, 800),
    BEER("Beer", "Drink in moderation.", 50, 200),
    VINEGAR("Vinegar", "An aged fermented liquid used in many cooking recipes.", 13, 100),
    TRUFFLE_OIL("Truffle Oil", "A gourmet cooking ingredient.", 38, 1065),
    COFFEE("Coffee", "It smells delicious. This is sure to give you a boost.", 75, 150),
    OIL("Oil", "All purpose cooking oil.", 13, 100),
    MEAD("Mead", "A fermented beverage made from honey. Drink in moderation.", 100, 300),
    PALE_ALE("Pale Ale", "Drink in moderation.", 50, 300),
    RAISINS("Raisins", "It's said to be the Junimos' favorite food.", 125, 600),

    // Jellies
    APRICOT_JELLY("Apricot Jelly", "Gooey.", 2 * 59, 2 * 59 + 50),
    CHERRY_JELLY("Cherry Jelly", "Gooey.", 2 * 80, 2 * 80 + 50),
    BANANA_JELLY("Banana Jelly", "Gooey.", 2 * 150, 2 * 150 + 50),
    MANGO_JELLY("Mango Jelly", "Gooey.", 2 * 130, 2 * 130 + 50),
    ORANGE_JELLY("Orange Jelly", "Gooey.", 2 * 100, 2 * 100 + 50),
    PEACH_JELLY("Peach Jelly", "Gooey.", 2 * 140, 2 * 140 + 50),
    APPLE_JELLY("Apple Jelly", "Gooey.", 2 * 100, 2 * 100 + 50),
    POMEGRANATE_JELLY("Pomegranate Jelly", "Gooey.", 2 * 140, 2 * 140 + 50),
    STRAWBERRY_JELLY("Strawberry Jelly", "Gooey.", 2 * 120, 2 * 120 + 50),
    BLUEBERRY_JELLY("Blueberry Jelly", "Gooey.", 2 * 50, 2 * 50 + 50),
    CRANBERRIES_JELLY("Cranberries Jelly", "Gooey.", 2 * 75, 2 * 75 + 50),
    GRAPE_JELLY("Grape Jelly", "Gooey.", 2 * 80, 2 * 80 + 50),
    MELON_JELLY("Melon Jelly", "Gooey.", 2 * 250, 2 * 250 + 50),
    POWDERMELON_JELLY("Powdermelon Jelly", "Gooey.", 2 * 60, 2 * 60 + 50),
    STARFRUIT_JELLY("Starfruit Jelly", "Gooey.", 2 * 750, 2 * 750 + 50),
    ANCIENT_FRUIT_JELLY("Ancient Fruit Jelly", "Gooey.", 0, 2 * 550 + 50),
    SWEET_GEM_BERRY_JELLY("Sweet Gem Berry Jelly", "Gooey.", 0, 2 * 3000 + 50),

    // Dried Fruits (excluding GRAPE)
    DRIED_APRICOT("Dried Apricot", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 59 + 25)),
    DRIED_CHERRY("Dried Cherry", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 80 + 25)),
    DRIED_BANANA("Dried Banana", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 150 + 25)),
    DRIED_MANGO("Dried Mango", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 130 + 25)),
    DRIED_ORANGE("Dried Orange", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 100 + 25)),
    DRIED_PEACH("Dried Peach", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 140 + 25)),
    DRIED_APPLE("Dried Apple", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 100 + 25)),
    DRIED_POMEGRANATE("Dried Pomegranate", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 140 + 25)),
    DRIED_STRAWBERRY("Dried Strawberry", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 120 + 25)),
    DRIED_BLUEBERRY("Dried Blueberry", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 50 + 25)),
    DRIED_CRANBERRIES("Dried Cranberries", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 75 + 25)),
    DRIED_MELON("Dried Melon", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 250 + 25)),
    DRIED_POWDERMELON("Dried Powdermelon", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 60 + 25)),
    DRIED_STARFRUIT("Dried Starfruit", "Chewy pieces of dried fruit.", 75, (int)(7.5 * 750 + 25)),
    DRIED_ANCIENT_FRUIT("Dried Ancient Fruit", "Chewy pieces of dried fruit.", 0, (int)(7.5 * 550 + 25)),
    DRIED_SWEET_GEM_BERRY("Dried Sweet Gem Berry", "Chewy pieces of dried fruit.", 0, (int)(7.5 * 3000 + 25)),

    DRIED_COMMON_MUSHROOM("Dried Common Mushroom", "A package of gourmet mushrooms.", 50, 325),

    // Wines
    APRICOT_WINE("Apricot Wine", "A rich, aged fruit wine.", 66, 177),
    CHERRY_WINE("Cherry Wine", "A rich, aged fruit wine.", 66, 240),
    BANANA_WINE("Banana Wine", "A rich, aged fruit wine.", 131, 450),
    MANGO_WINE("Mango Wine", "A rich, aged fruit wine.", 175, 390),
    ORANGE_WINE("Orange Wine", "A rich, aged fruit wine.", 66, 300),
    PEACH_WINE("Peach Wine", "A rich, aged fruit wine.", 66, 420),
    APPLE_WINE("Apple Wine", "A rich, aged fruit wine.", 66, 300),
    POMEGRANATE_WINE("Pomegranate Wine", "A rich, aged fruit wine.", 66, 420),
    STRAWBERRY_WINE("Strawberry Wine", "A rich, aged fruit wine.", 87, 360),
    BLUEBERRY_WINE("Blueberry Wine", "A rich, aged fruit wine.", 43, 150),
    CRANBERRIES_WINE("Cranberries Wine", "A rich, aged fruit wine.", 66, 225),
    GRAPE_WINE("Grape Wine", "A rich, aged fruit wine.", 66, 240),
    MELON_WINE("Melon Wine", "A rich, aged fruit wine.", 197, 750),
    POWDERMELON_WINE("Powdermelon Wine", "A rich, aged fruit wine.", 110, 180),
    STARFRUIT_WINE("Starfruit Wine", "A rich, aged fruit wine.", 218, 2250),
    ANCIENT_FRUIT_WINE("Ancient Fruit Wine", "A rich, aged fruit wine.", 0, 1650),
    SWEET_GEM_BERRY_WINE("Sweet Gem Berry Wine", "A rich, aged fruit wine.", 0, 9000),

    CARROT_JUICE("Carrot Juice", "A pulpy vegetable juice.",       150, (int)(2.25 * 35)),
    CAULIFLOWER_JUICE("Cauliflower Juice", "A pulpy vegetable juice.", 150, (int)(2.25 * 175)),
    GREEN_BEAN_JUICE("Green Bean Juice", "A pulpy vegetable juice.",   50, (int)(2.25 * 40)),
    KALE_JUICE("Kale Juice", "A pulpy vegetable juice.",             100, (int)(2.25 * 110)),
    PARSNIP_JUICE("Parsnip Juice", "A pulpy vegetable juice.",         50, (int)(2.25 * 35)),
    GARLIC_JUICE("Garlic Juice", "A pulpy vegetable juice.",           40, (int)(2.25 * 60)),
    TOMATO_JUICE("Tomato Juice", "A pulpy vegetable juice.",           40, (int)(2.25 * 60)),
    PUMPKIN_JUICE("Pumpkin Juice", "A pulpy vegetable juice.",          0, (int)(2.25 * 320)),
    RADISH_JUICE("Radish Juice", "A pulpy vegetable juice.",            90, (int)(2.25 * 90)),
    POTATO_JUICE("Potato Juice", "A pulpy vegetable juice.",            50, (int)(2.25 * 80)),
    RED_CABBAGE_JUICE("Red Cabbage Juice", "A pulpy vegetable juice.", 150, (int)(2.25 * 260)),
    CORN_JUICE("Corn Juice", "A pulpy vegetable juice.",               50, (int)(2.25 * 50)),
    YAM_JUICE("Yam Juice", "A pulpy vegetable juice.",                 90, (int)(2.25 * 160)),
    AMARANTH_JUICE("Amaranth Juice", "A pulpy vegetable juice.",      100, (int)(2.25 * 150)),
    ARTICHOKE_JUICE("Artichoke Juice", "A pulpy vegetable juice.",      60, (int)(2.25 * 160)),
    BEET_JUICE("Beet Juice", "A pulpy vegetable juice.",               60, (int)(2.25 * 100)),
    BOK_CHOY_JUICE("Bok Choy Juice", "A pulpy vegetable juice.",       50, (int)(2.25 * 80)),
    BROCCOLI_JUICE("Broccoli Juice", "A pulpy vegetable juice.",      126, (int)(2.25 * 70)),
    HOT_PEPPER_JUICE("Hot Pepper Juice", "A pulpy vegetable juice.",   26, (int)(2.25 * 40)),
    EGGPLANT_JUICE("Eggplant Juice", "A pulpy vegetable juice.",       40, (int)(2.25 * 60)),
    SUMMER_SQUASH_JUICE("Summer Squash Juice", "A pulpy vegetable juice.", 126, (int)(2.25 * 45)),

    CARROT_PICKLE("Carrot Pickle", "Crunchy and tangy.", (int)(1.75 * 75), 2 * 35 + 50),
    CAULIFLOWER_PICKLE("Cauliflower Pickle", "Crunchy and tangy.", (int)(1.75 * 75), 2 * 175 + 50),
    GREEN_BEAN_PICKLE("Green Bean Pickle", "Crunchy and tangy.", (int)(1.75 * 25), 2 * 40 + 50),
    KALE_PICKLE("Kale Pickle", "Crunchy and tangy.", (int)(1.75 * 50), 2 * 110 + 50),
    PARSNIP_PICKLE("Parsnip Pickle", "Crunchy and tangy.", (int)(1.75 * 25), 2 * 35 + 50),
    GARLIC_PICKLE("Garlic Pickle", "Crunchy and tangy.", (int)(1.75 * 20), 2 * 60 + 50),
    TOMATO_PICKLE("Tomato Pickle", "Crunchy and tangy.", (int)(1.75 * 20), 2 * 60 + 50),
    PUMPKIN_PICKLE("Pumpkin Pickle", "Crunchy and tangy.", (int)(1.75 * 0), 2 * 320 + 50), // Not edible
    RADISH_PICKLE("Radish Pickle", "Crunchy and tangy.", (int)(1.75 * 45), 2 * 90 + 50),
    POTATO_PICKLE("Potato Pickle", "Crunchy and tangy.", (int)(1.75 * 25), 2 * 80 + 50),
    RED_CABBAGE_PICKLE("Red Cabbage Pickle", "Crunchy and tangy.", (int)(1.75 * 75), 2 * 260 + 50),
    CORN_PICKLE("Corn Pickle", "Crunchy and tangy.", (int)(1.75 * 25), 2 * 50 + 50),
    YAM_PICKLE("Yam Pickle", "Crunchy and tangy.", (int)(1.75 * 45), 2 * 160 + 50),
    AMARANTH_PICKLE("Amaranth Pickle", "Crunchy and tangy.", (int)(1.75 * 50), 2 * 150 + 50),
    ARTICHOKE_PICKLE("Artichoke Pickle", "Crunchy and tangy.", (int)(1.75 * 30), 2 * 160 + 50),
    BEET_PICKLE("Beet Pickle", "Crunchy and tangy.", (int)(1.75 * 30), 2 * 100 + 50),
    BOK_CHOY_PICKLE("Bok Choy Pickle", "Crunchy and tangy.", (int)(1.75 * 25), 2 * 80 + 50),
    BROCCOLI_PICKLE("Broccoli Pickle", "Crunchy and tangy.", (int)(1.75 * 63), 2 * 70 + 50),
    HOT_PEPPER_PICKLE("Hot Pepper Pickle", "Crunchy and tangy.", (int)(1.75 * 13), 2 * 40 + 50),
    EGGPLANT_PICKLE("Eggplant Pickle", "Crunchy and tangy.", (int)(1.75 * 20), 2 * 60 + 50),
    SUMMER_SQUASH_PICKLE("Summer Squash Pickle", "Crunchy and tangy.", (int)(1.75 * 63), 2 * 45 + 50),

    SMOKED_SALMON("Smoked Salmon", "A gourmet smoked salmon slice.", (int)(1.5 * 75), 2 * 75),
    SMOKED_SARDINE("Smoked Sardine", "A smoked version of the common sardine.", (int)(1.5 * 40), 2 * 40),
    SMOKED_SHAD("Smoked Shad", "Rich and smoky.", (int)(1.5 * 60), 2 * 60),
    SMOKED_BLUE_DISCUS("Smoked Blue Discus", "Smoked with a unique tang.", (int)(1.5 * 120), 2 * 120),
    SMOKED_MIDNIGHT_CARP("Smoked Midnight Carp", "Dark and aromatic.", (int)(1.5 * 150), 2 * 150),
    SMOKED_SQUID("Smoked Squid", "Deliciously chewy and smoky.", (int)(1.5 * 80), 2 * 80),
    SMOKED_TUNA("Smoked Tuna", "A rich, flavorful smoked tuna fillet.", (int)(1.5 * 100), 2 * 100),
    SMOKED_PERCH("Smoked Perch", "Light and flavorful.", (int)(1.5 * 55), 2 * 55),
    SMOKED_FLOUNDER("Smoked Flounder", "Flaky and delicately smoked.", (int)(1.5 * 100), 2 * 100),
    SMOKED_LIONFISH("Smoked Lionfish", "An exotic smoked treat.", (int)(1.5 * 100), 2 * 100),
    SMOKED_HERRING("Smoked Herring", "Rich and oily.", (int)(1.5 * 30), 2 * 30),
    SMOKED_GHOSTFISH("Smoked Ghostfish", "Mysteriously flavorful.", (int)(1.5 * 45), 2 * 45),
    SMOKED_TILAPIA("Smoked Tilapia", "Balanced and smoky.", (int)(1.5 * 75), 2 * 75),
    SMOKED_DORADO("Smoked Dorado", "A zesty smoked delicacy.", (int)(1.5 * 100), 2 * 100),
    SMOKED_SUNFISH("Smoked Sunfish", "Crisp and light.", (int)(1.5 * 30), 2 * 30),
    SMOKED_RAINBOW_TROUT("Smoked Rainbow Trout", "Colorful and savory.", (int)(1.5 * 65), 2 * 65),

    // Legendary smoked fish
    SMOKED_LEGEND("Smoked Legend", "Smoked to legendary perfection.", (int)(1.5 * 5000), 2 * 5000),
    SMOKED_GLACIERFISH("Smoked Glacierfish", "Chilled and smoked excellence.", (int)(1.5 * 1000), 2 * 1000),
    SMOKED_ANGLER("Smoked Angler", "Bold and smoky.", (int)(1.5 * 900), 2 * 900),
    SMOKED_CRIMSONFISH("Smoked Crimsonfish", "Spicy and smoked just right.", (int)(1.5 * 1500), 2 * 1500);


    private final String name;
    private final String description;
    private final int energy;
    private final int sellPrice;

    ProcessedFood(String name, String description, int energy, int sellPrice) {
        this.name = name;
        this.description = description;
        this.energy = energy;
        this.sellPrice = sellPrice;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getEnergy() {
        return energy;
    }

    public int getSellPrice() {
        return sellPrice;
    }
}
