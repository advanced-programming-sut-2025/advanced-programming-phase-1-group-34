package org.Group34.model.items;

import org.Group34.model.entities.naturalElements.PlantAble;
import org.Group34.model.enums.Season;
import org.Group34.model.enums.creatorOfNaturalElements.CropCreator;
import org.Group34.model.enums.creatorOfNaturalElements.TreeCreator;
import java.util.function.Supplier;

/**
 * Enum mapping each planting source (seed or sapling) to its PlantAble factory.
 */
public enum PlantingSource implements Item {
    // Crops – Spring
    JAZZ_SEEDS("Jazz Seeds", CropCreator.BLUE_JAZZ::createInstance, Season.SPRING),
    CARROT_SEEDS("Carrot Seeds", CropCreator.CARROT::createInstance, Season.SPRING),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", CropCreator.CAULIFLOWER::createInstance, Season.SPRING),
    COFFEE_BEAN("Coffee Bean", CropCreator.COFFEE_BEAN::createInstance, Season.SPRING),
    GARLIC_SEEDS("Garlic Seeds", CropCreator.GARLIC::createInstance, Season.SPRING),
    BEAN_STARTER("Bean Starter", CropCreator.GREEN_BEAN::createInstance, Season.SPRING),
    KALE_SEEDS("Kale Seeds", CropCreator.KALE::createInstance, Season.SPRING),
    PARSNIP_SEEDS("Parsnip Seeds", CropCreator.PARSNIP::createInstance, Season.SPRING),
    POTATO_SEEDS("Potato Seeds", CropCreator.POTATO::createInstance, Season.SPRING),
    RHUBARB_SEEDS("Rhubarb Seeds", CropCreator.RHUBARB::createInstance, Season.SPRING),
    STRAWBERRY_SEEDS("Strawberry Seeds", CropCreator.STRAWBERRY::createInstance, Season.SPRING),
    TULIP_BULB("Tulip Bulb", CropCreator.TULIP::createInstance, Season.SPRING),
    RICE_SHOOT("Rice Shoot", CropCreator.UNMILLED_RICE::createInstance, Season.SPRING),

    // Crops – Summer
    BLUEBERRY_SEEDS("Blueberry Seeds", CropCreator.BLUEBERRY::createInstance, Season.SUMMER),
    CORN_SEEDS("Corn Seeds", CropCreator.CORN::createInstance, Season.SUMMER),
    HOPS_STARTER("Hops Starter", CropCreator.HOPS::createInstance, Season.SUMMER),
    PEPPER_SEEDS("Pepper Seeds", CropCreator.HOT_PEPPER::createInstance, Season.SUMMER),
    MELON_SEEDS("Melon Seeds", CropCreator.MELON::createInstance, Season.SUMMER),
    POPPY_SEEDS("Poppy Seeds", CropCreator.POPPY::createInstance, Season.SUMMER),
    RADISH_SEEDS("Radish Seeds", CropCreator.RADISH::createInstance, Season.SUMMER),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", CropCreator.RED_CABBAGE::createInstance, Season.SUMMER),
    STARFRUIT_SEEDS("Starfruit Seeds", CropCreator.STARFRUIT::createInstance, Season.SUMMER),
    SPANGLE_SEEDS("Spangle Seeds", CropCreator.SUMMER_SPANGLE::createInstance, Season.SUMMER),
    SUMMER_SQUASH_SEEDS("Summer Squash Seeds", CropCreator.SUMMER_SQUASH::createInstance, Season.SUMMER),
    SUNFLOWER_SEEDS("Sunflower Seeds", CropCreator.SUNFLOWER::createInstance, Season.SUMMER),
    TOMATO_SEEDS("Tomato Seeds", CropCreator.TOMATO::createInstance, Season.SUMMER),
    WHEAT_SEEDS("Wheat Seeds", CropCreator.WHEAT::createInstance, Season.SUMMER),

    // Crops – Autumn
    AMARANTH_SEEDS("Amaranth Seeds", CropCreator.AMARANTH::createInstance, Season.FALL),
    ARTICHOKE_SEEDS("Artichoke Seeds", CropCreator.ARTICHOKE::createInstance, Season.FALL),
    BEET_SEEDS("Beet Seeds", CropCreator.BEET::createInstance, Season.FALL),
    BOK_CHOY_SEEDS("Bok Choy Seeds", CropCreator.BOK_CHOY::createInstance, Season.FALL),
    BROCCOLI_SEEDS("Broccoli Seeds", CropCreator.BROCCOLI::createInstance, Season.FALL),
    CRANBERRY_SEEDS("Cranberry Seeds", CropCreator.CRANBERRIES::createInstance, Season.FALL),
    EGGPLANT_SEEDS("Eggplant Seeds", CropCreator.EGGPLANT::createInstance, Season.FALL),
    FAIRY_SEEDS("Fairy Seeds", CropCreator.FAIRY_ROSE::createInstance, Season.FALL),
    GRAPE_STARTER("Grape Starter", CropCreator.GRAPE::createInstance, Season.FALL),
    PUMPKIN_SEEDS("Pumpkin Seeds", CropCreator.PUMPKIN::createInstance, Season.FALL),
    YAM_SEEDS("Yam Seeds", CropCreator.YAM::createInstance, Season.FALL),
    RARE_SEED("Rare Seed", CropCreator.SWEET_GEM_BERRY::createInstance, Season.FALL),

    // Crops – Winter
    POWDERMELON_SEEDS("Powdermelon Seeds", CropCreator.POWDERMELON::createInstance, Season.WINTER),

    // Special – All Seasons
    ANCIENT_SEEDS("Ancient Seeds", CropCreator.ANCIENT_FRUIT::createInstance, Season.ALL),
    MIXED_SEEDS("Mixed Seeds", null, Season.ALL),

    // Trees – All Seasons
    APRICOT_SAPLING("Apricot Sapling", TreeCreator.APRICOT_TREE::createInstance, Season.ALL),
    CHERRY_SAPLING("Cherry Sapling", TreeCreator.CHERRY_TREE::createInstance, Season.ALL),
    BANANA_SAPLING("Banana Sapling", TreeCreator.BANANA_TREE::createInstance, Season.ALL),
    MANGO_SAPLING("Mango Sapling", TreeCreator.MANGO_TREE::createInstance, Season.ALL),
    ORANGE_SAPLING("Orange Sapling", TreeCreator.ORANGE_TREE::createInstance, Season.ALL),
    PEACH_SAPLING("Peach Sapling", TreeCreator.PEACH_TREE::createInstance, Season.ALL),
    APPLE_SAPLING("Apple Sappling", TreeCreator.APPLE_TREE::createInstance, Season.ALL),
    POMEGRANATE_SAPLING("Pomegranate Sapling", TreeCreator.POMEGRANATE_TREE::createInstance, Season.ALL),
    OAK_SAPLING("Acorns", TreeCreator.OAK_TREE::createInstance, Season.ALL),
    MAPLE_SAPLING("Maple Seeds", TreeCreator.MAPLE_TREE::createInstance, Season.ALL),
    PINE_SAPLING("Pine Cones", TreeCreator.PINE_TREE::createInstance, Season.ALL),
    MAHOGANY_SAPLING("Mahogany Seeds", TreeCreator.MAHOGANY_TREE::createInstance, Season.ALL),
    MUSHROOM_SAPLING("Mushroom Tree Seeds", TreeCreator.MUSHROOM_TREE::createInstance, Season.ALL),
    MYSTIC_SAPLING("Mystic Tree Seeds", TreeCreator.MYSTIC_TREE::createInstance, Season.ALL);

    private final String name;
    private final Supplier<PlantAble> factory;
    private final Season season;

    PlantingSource(String name, Supplier<PlantAble> factory, Season season) {
        this.name = name;
        this.factory = factory;
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public PlantAble getPlantAble() {
        return factory.get();
    }

    public Season getSeason() {
        return season;
    }
}
