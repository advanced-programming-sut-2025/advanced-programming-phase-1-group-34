package org.Group34.model.items;

import org.Group34.model.entities.naturalElements.PlantAble;
import org.Group34.model.enums.creatorOfNaturalElements.CropCreator;
import org.Group34.model.enums.creatorOfNaturalElements.TreeCreator;
import java.util.function.Supplier;

/**
 * Enum mapping each planting source (seed or sapling) to its PlantAble factory.
 */
public enum PlantingSource implements Item {
    // Crops
    JAZZ_SEEDS("Jazz Seeds", CropCreator.BLUE_JAZZ::createInstance),
    CARROT_SEEDS("Carrot Seeds", CropCreator.CARROT::createInstance),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", CropCreator.CAULIFLOWER::createInstance),
    COFFEE_BEAN("Coffee Bean", CropCreator.COFFEE_BEAN::createInstance),
    GARLIC_SEEDS("Garlic Seeds", CropCreator.GARLIC::createInstance),
    BEAN_STARTER("Bean Starter", CropCreator.GREEN_BEAN::createInstance),
    KALE_SEEDS("Kale Seeds", CropCreator.KALE::createInstance),
    PARSNIP_SEEDS("Parsnip Seeds", CropCreator.PARSNIP::createInstance),
    POTATO_SEEDS("Potato Seeds", CropCreator.POTATO::createInstance),
    RHUBARB_SEEDS("Rhubarb Seeds", CropCreator.RHUBARB::createInstance),
    STRAWBERRY_SEEDS("Strawberry Seeds", CropCreator.STRAWBERRY::createInstance),
    TULIP_BULB("Tulip Bulb", CropCreator.TULIP::createInstance),
    RICE_SHOOT("Rice Shoot", CropCreator.UNMILLED_RICE::createInstance),
    BLUEBERRY_SEEDS("Blueberry Seeds", CropCreator.BLUEBERRY::createInstance),
    CORN_SEEDS("Corn Seeds", CropCreator.CORN::createInstance),
    HOPS_STARTER("Hops Starter", CropCreator.HOPS::createInstance),
    PEPPER_SEEDS("Pepper Seeds", CropCreator.HOT_PEPPER::createInstance),
    MELON_SEEDS("Melon Seeds", CropCreator.MELON::createInstance),
    POPPY_SEEDS("Poppy Seeds", CropCreator.POPPY::createInstance),
    RADISH_SEEDS("Radish Seeds", CropCreator.RADISH::createInstance),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", CropCreator.RED_CABBAGE::createInstance),
    STARFRUIT_SEEDS("Starfruit Seeds", CropCreator.STARFRUIT::createInstance),
    SPANGLE_SEEDS("Spangle Seeds", CropCreator.SUMMER_SPANGLE::createInstance),
    SUMMER_SQUASH_SEEDS("Summer Squash Seeds", CropCreator.SUMMER_SQUASH::createInstance),
    SUNFLOWER_SEEDS("Sunflower Seeds", CropCreator.SUNFLOWER::createInstance),
    TOMATO_SEEDS("Tomato Seeds", CropCreator.TOMATO::createInstance),
    WHEAT_SEEDS("Wheat Seeds", CropCreator.WHEAT::createInstance),
    AMARANTH_SEEDS("Amaranth Seeds", CropCreator.AMARANTH::createInstance),
    ARTICHOKE_SEEDS("Artichoke Seeds", CropCreator.ARTICHOKE::createInstance),
    BEET_SEEDS("Beet Seeds", CropCreator.BEET::createInstance),
    BOK_CHOY_SEEDS("Bok Choy Seeds", CropCreator.BOK_CHOY::createInstance),
    BROCCOLI_SEEDS("Broccoli Seeds", CropCreator.BROCCOLI::createInstance),
    CRANBERRY_SEEDS("Cranberry Seeds", CropCreator.CRANBERRIES::createInstance),
    EGGPLANT_SEEDS("Eggplant Seeds", CropCreator.EGGPLANT::createInstance),
    FAIRY_SEEDS("Fairy Seeds", CropCreator.FAIRY_ROSE::createInstance),
    GRAPE_STARTER("Grape Starter", CropCreator.GRAPE::createInstance),
    PUMPKIN_SEEDS("Pumpkin Seeds", CropCreator.PUMPKIN::createInstance),
    YAM_SEEDS("Yam Seeds", CropCreator.YAM::createInstance),
    RARE_SEED("Rare Seed", CropCreator.SWEET_GEM_BERRY::createInstance),
    POWDERMELON_SEEDS("Powdermelon Seeds", CropCreator.POWDERMELON::createInstance),
    ANCIENT_SEEDS("Ancient Seeds", CropCreator.ANCIENT_FRUIT::createInstance),
    // Trees
    APRICOT_SAPLING("Apricot Sapling", TreeCreator.APRICOT_TREE::createInstance),
    CHERRY_SAPLING("Cherry Sapling", TreeCreator.CHERRY_TREE::createInstance),
    BANANA_SAPLING("Banana Sapling", TreeCreator.BANANA_TREE::createInstance),
    MANGO_SAPLING("Mango Sapling", TreeCreator.MANGO_TREE::createInstance),
    ORANGE_SAPLING("Orange Sapling", TreeCreator.ORANGE_TREE::createInstance),
    PEACH_SAPLING("Peach Sapling", TreeCreator.PEACH_TREE::createInstance),
    APPLE_SAPLING("Apple Sapling", TreeCreator.APPLE_TREE::createInstance),
    POMEGRANATE_SAPLING("Pomegranate Sapling", TreeCreator.POMEGRANATE_TREE::createInstance),
    OAK_SAPLING("Acorns", TreeCreator.OAK_TREE::createInstance),
    MAPLE_SAPLING("Maple Seeds", TreeCreator.MAPLE_TREE::createInstance),
    PINE_SAPLING("Pine Cones", TreeCreator.PINE_TREE::createInstance),
    MAHOGANY_SAPLING("Mahogany Seeds", TreeCreator.MAHOGANY_TREE::createInstance),
    MUSHROOM_SAPLING("Mushroom Tree Seeds", TreeCreator.MUSHROOM_TREE::createInstance),
    MYSTIC_SAPLING("Mystic Tree Seeds", TreeCreator.MYSTIC_TREE::createInstance);

    private final String name;
    private final Supplier<PlantAble> factory;

    PlantingSource(String name, Supplier<PlantAble> factory) {
        this.name = name;
        this.factory = factory;
    }

    public String getName() {
        return name;
    }

    public PlantAble getPlantAble() {
        return factory.get();
    }
}
