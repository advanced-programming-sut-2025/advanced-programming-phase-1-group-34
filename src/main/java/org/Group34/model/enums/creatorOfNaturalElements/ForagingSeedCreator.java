package org.Group34.model.enums.creatorOfNaturalElements;

import org.Group34.model.entities.naturalElements.ForagingSeed;

public enum ForagingSeedCreator {
    JAZZ_SEEDS("Jazz Seeds", new String[]{"Spring"}),
    CARROT_SEEDS("Carrot Seeds", new String[]{"Spring"}),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", new String[]{"Spring"}),
    COFFEE_BEAN("Coffee Bean", new String[]{"Spring"}),
    GARLIC_SEEDS("Garlic Seeds", new String[]{"Spring"}),
    BEAN_STARTER("Bean Starter", new String[]{"Spring"}),
    KALE_SEEDS("Kale Seeds", new String[]{"Spring"}),
    PARSNIP_SEEDS("Parsnip Seeds", new String[]{"Spring"}),
    POTATO_SEEDS("Potato Seeds", new String[]{"Spring"}),
    RHUBARB_SEEDS("Rhubarb Seeds", new String[]{"Spring"}),
    STRAWBERRY_SEEDS("Strawberry Seeds", new String[]{"Spring"}),
    TULIP_BULB("Tulip Bulb", new String[]{"Spring"}),
    RICE_SHOOT("Rice Shoot", new String[]{"Spring"}),

    BLUEBERRY_SEEDS("Blueberry Seeds", new String[]{"Summer"}),
    CORN_SEEDS("Corn Seeds", new String[]{"Summer"}),
    HOPS_STARTER("Hops Starter", new String[]{"Summer"}),
    PEPPER_SEEDS("Pepper Seeds", new String[]{"Summer"}),
    MELON_SEEDS("Melon Seeds", new String[]{"Summer"}),
    POPPY_SEEDS("Poppy Seeds", new String[]{"Summer"}),
    RADISH_SEEDS("Radish Seeds", new String[]{"Summer"}),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", new String[]{"Summer"}),
    STARFRUIT_SEEDS("Starfruit Seeds", new String[]{"Summer"}),
    SPANGLE_SEEDS("Spangle Seeds", new String[]{"Summer"}),
    SUMMER_SQUASH_SEEDS("Summer Squash Seeds", new String[]{"Summer"}),
    SUNFLOWER_SEEDS("Sunflower Seeds", new String[]{"Summer"}),
    TOMATO_SEEDS("Tomato Seeds", new String[]{"Summer"}),
    WHEAT_SEEDS("Wheat Seeds", new String[]{"Summer"}),

    AMARANTH_SEEDS("Amaranth Seeds", new String[]{"Autumn"}),
    ARTICHOKE_SEEDS("Artichoke Seeds", new String[]{"Autumn"}),
    BEET_SEEDS("Beet Seeds", new String[]{"Autumn"}),
    BOK_CHOY_SEEDS("Bok Choy Seeds", new String[]{"Autumn"}),
    BROCCOLI_SEEDS("Broccoli Seeds", new String[]{"Autumn"}),
    CRANBERRY_SEEDS("Cranberry Seeds", new String[]{"Autumn"}),
    EGGPLANT_SEEDS("Eggplant Seeds", new String[]{"Autumn"}),
    FAIRY_SEEDS("Fairy Seeds", new String[]{"Autumn"}),
    GRAPE_STARTER("Grape Starter", new String[]{"Autumn"}),
    PUMPKIN_SEEDS("Pumpkin Seeds", new String[]{"Autumn"}),
    YAM_SEEDS("Yam Seeds", new String[]{"Autumn"}),
    RARE_SEED("Rare Seed", new String[]{"Autumn"}),

    POWDERMELON_SEEDS("Powdermelon Seeds", new String[]{"Winter"}),

    ANCIENT_SEEDS("Ancient Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"}),
    MIXED_SEEDS("Mixed Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"});
    private final String name;
    private final String[] seasons;

    ForagingSeedCreator(String name, String[] seasons) {
        this.name = name;
        this.seasons = seasons;
    }

    public ForagingSeed createInstance() {
        return new ForagingSeed(name, seasons);
    }
}
