package org.Group34.model.items;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.naturalElements.PlantAble;
import org.Group34.model.enums.Season;
import org.Group34.model.enums.creatorOfNaturalElements.CropCreator;
import org.Group34.model.enums.creatorOfNaturalElements.TreeCreator;
import org.Group34.model.gameAssetManagers.CropAssetManager;
import org.Group34.model.gameAssetManagers.TreeAssetManager;

import java.util.function.Supplier;

/**
 * Enum mapping each planting source (seed or sapling) to its PlantAble factory.
 */
public enum PlantingSource implements Item {
    // Crops – Spring
    JAZZ_SEEDS("Jazz Seeds", () -> CropCreator.BLUE_JAZZ.createInstance(), Season.SPRING, 37, 45, "Plant in spring. Takes 7 days to produce a blue puffball flower.", CropAssetManager.getBlueJazzSeeds()),
    CARROT_SEEDS("Carrot Seeds", () -> CropCreator.CARROT.createInstance(), Season.SPRING, 5, 0, "Plant in the spring. Takes 3 days to grow.", CropAssetManager.getCarrotSeeds()),
    CAULIFLOWER_SEEDS("Cauliflower Seeds", () -> CropCreator.CAULIFLOWER.createInstance(), Season.SPRING, 100, 120, "Plant these in the spring. Takes 12 days to produce a large cauliflower.", CropAssetManager.getCauliflowerSeeds()),
    COFFEE_BEAN("Coffee Bean", () -> CropCreator.COFFEE_BEAN.createInstance(), Season.SPRING, 200, 0, "Plant in summer or spring. Takes 10 days to grow, Then produces coffee Beans every other day.", CropAssetManager.getCoffeeBeanSeeds()),
    GARLIC_SEEDS("Garlic Seeds", () -> CropCreator.GARLIC.createInstance(), Season.SPRING, 40, 60, "Plant these in the spring. Takes 4 days to mature.", CropAssetManager.getGarlicSeeds()),
    BEAN_STARTER("Bean Starter", () -> CropCreator.GREEN_BEAN.createInstance(), Season.SPRING, 75, 90, "Plant these in the spring. Takes 10 days to mature, but keeps producing after that. Grows on a trellis.", CropAssetManager.getGreenBeanSeeds()),
    KALE_SEEDS("Kale Seeds", () -> CropCreator.KALE.createInstance(), Season.SPRING, 87, 105, "Plant these in the spring. Takes 6 days to mature. Harvest with the scythe.", CropAssetManager.getKaleSeeds()),
    PARSNIP_SEEDS("Parsnip Seeds", () -> CropCreator.PARSNIP.createInstance(), Season.SPRING, 25, 30, "Plant these in the spring. Takes 4 days to mature.", CropAssetManager.getParsnipSeeds()),
    POTATO_SEEDS("Potato Seeds", () -> CropCreator.POTATO.createInstance(), Season.SPRING, 62, 75, "Plant these in the spring. Takes 6 days to mature, and has a chance of yielding multiple potatoes at harvest.", CropAssetManager.getPotatoSeeds()),
    RHUBARB_SEEDS("Rhubarb Seeds", () -> CropCreator.RHUBARB.createInstance(), Season.SPRING, 100, 0, "Plant these in the spring. Takes 13 days to mature.", CropAssetManager.getRhubarbSeeds()),
    STRAWBERRY_SEEDS("Strawberry Seeds", () -> CropCreator.STRAWBERRY.createInstance(), Season.SPRING, 100, 0, "Plant these in spring. Takes 8 days to mature, and keeps producing strawberries after that.", CropAssetManager.getStrawberrySeeds()),
    TULIP_BULB("Tulip Bulb", () -> CropCreator.TULIP.createInstance(), Season.SPRING, 25, 30, "Plant in spring. Takes 6 days to produce a colorful flower. Assorted colors.", CropAssetManager.getTulipSeeds()),
    RICE_SHOOT("Rice Shoot", () -> CropCreator.UNMILLED_RICE.createInstance(), Season.SPRING, 40, 60, "Plant these in the spring. Takes 8 days to mature. Grows faster if planted near a body of water. \nHarvest with the scythe.", CropAssetManager.getUnmilledRiceSeeds()),

    // Crops – Summer
    BLUEBERRY_SEEDS("Blueberry Seeds", () -> CropCreator.BLUEBERRY.createInstance(), Season.SUMMER, 80, 120, "Plant these in the summer. Takes 13 days to mature, and continues to produce after first harvest.", CropAssetManager.getBlueberrySeeds()),
    CORN_SEEDS("Corn Seeds", () -> CropCreator.CORN.createInstance(), Season.SUMMER, 187, 225, "Plant these in the summer or fall. Takes 14 days to mature, and continues to produce after first harvest.", CropAssetManager.getCornSeeds()),
    HOPS_STARTER("Hops Starter", () -> CropCreator.HOPS.createInstance(), Season.SUMMER, 75, 90, "Plant these in the summer. Takes 11 days to grow, but keeps producing after that. Grows on a trellis.", CropAssetManager.getHopsSeeds()),
    PEPPER_SEEDS("Pepper Seeds", () -> CropCreator.HOT_PEPPER.createInstance(), Season.SUMMER, 50, 60, "Plant these in the summer. Takes 5 days to mature, and continues to produce after first harvest.", CropAssetManager.getHotPepperSeeds()),
    MELON_SEEDS("Melon Seeds", () -> CropCreator.MELON.createInstance(), Season.SUMMER, 100, 120, "Plant these in the summer. Takes 12 days to mature.", CropAssetManager.getMelonSeeds()),
    POPPY_SEEDS("Poppy Seeds", () -> CropCreator.POPPY.createInstance(), Season.SUMMER, 125, 150, "Plant in summer. Produces a bright red flower in 7 days.", CropAssetManager.getPoppySeeds()),
    RADISH_SEEDS("Radish Seeds", () -> CropCreator.RADISH.createInstance(), Season.SUMMER, 50, 60, "Plant these in the summer. Takes 6 days to mature.", CropAssetManager.getRadishSeeds()),
    RED_CABBAGE_SEEDS("Red Cabbage Seeds", () -> CropCreator.RED_CABBAGE.createInstance(), Season.SUMMER, 100, 150, "Plant these in the summer. Takes 9 days to mature.", CropAssetManager.getRedCabbageSeeds()),
    STARFRUIT_SEEDS("Starfruit Seeds", () -> CropCreator.STARFRUIT.createInstance(), Season.SUMMER, 400, 0, "Plant these in the summer. Takes 13 days to mature.", CropAssetManager.getStarfruitSeeds()),
    SPANGLE_SEEDS("Spangle Seeds", () -> CropCreator.SUMMER_SPANGLE.createInstance(), Season.SUMMER, 62, 75, "Plant in summer. Takes 8 days to produce a vibrant tropical flower. Assorted colors.", CropAssetManager.getSummerSpangleSeeds()),
    SUMMER_SQUASH_SEEDS("Summer Squash Seeds", () -> CropCreator.SUMMER_SQUASH.createInstance(), Season.SUMMER, 10, 0, "Plant in the summer. Takes 6 days to grow, and continues to produce after first harvest.", CropAssetManager.getSummerSquashSeeds()),
    SUNFLOWER_SEEDS("Sunflower Seeds", () -> CropCreator.SUNFLOWER.createInstance(), Season.SUMMER, 125, 300, "Plant in summer or fall. Takes 8 days to produce a large sunflower. Yields more seeds at harvest.", CropAssetManager.getSunflowerSeeds()),
    TOMATO_SEEDS("Tomato Seeds", () -> CropCreator.TOMATO.createInstance(), Season.SUMMER, 62, 75, "Plant these in the summer. Takes 11 days to mature, and continues to produce after first harvest.", CropAssetManager.getTomatoSeeds()),
    WHEAT_SEEDS("Wheat Seeds", () -> CropCreator.WHEAT.createInstance(), Season.SUMMER, 12, 15, "Plant these in the summer or fall. Takes 4 days to mature. Harvest with the scythe.", CropAssetManager.getWheatSeeds()),

    // Crops – Autumn
    AMARANTH_SEEDS("Amaranth Seeds", () -> CropCreator.AMARANTH.createInstance(), Season.FALL, 87, 105, "Plant these in the fall. Takes 7 days to grow. Harvest with the scythe.", CropAssetManager.getAmaranthSeeds()),
    ARTICHOKE_SEEDS("Artichoke Seeds", () -> CropCreator.ARTICHOKE.createInstance(), Season.FALL, 30, 45, "Plant these in the fall. Takes 8 days to mature.", CropAssetManager.getArtichokeSeeds()),
    BEET_SEEDS("Beet Seeds", () -> CropCreator.BEET.createInstance(), Season.FALL, 20, 0, "Plant these in the fall. Takes 6 days to mature.", CropAssetManager.getBeetSeeds()),
    BOK_CHOY_SEEDS("Bok Choy Seeds", () -> CropCreator.BOK_CHOY.createInstance(), Season.FALL, 62, 75, "Plant these in the fall. Takes 4 days to mature.", CropAssetManager.getBokChoySeeds()),
    BROCCOLI_SEEDS("Broccoli Seeds", () -> CropCreator.BROCCOLI.createInstance(), Season.FALL, 15, 0, "Plant in the fall. Takes 8 days to mature, and continues to produce after first harvest.", CropAssetManager.getBroccoliSeeds()),
    CRANBERRY_SEEDS("Cranberry Seeds", () -> CropCreator.CRANBERRIES.createInstance(), Season.FALL, 300, 360, "Plant these in the fall. Takes 7 days to mature, and continues to produce after first harvest.", CropAssetManager.getCranberriesSeeds()),
    EGGPLANT_SEEDS("Eggplant Seeds", () -> CropCreator.EGGPLANT.createInstance(), Season.FALL, 25, 30, "Plant these in the fall. Takes 5 days to mature, and continues to produce after first harvest.", CropAssetManager.getEggplantSeeds()),
    FAIRY_SEEDS("Fairy Seeds", () -> CropCreator.FAIRY_ROSE.createInstance(), Season.FALL, 250, 300, "Plant in fall. Takes 12 days to produce a mysterious flower. Assorted Colors.", CropAssetManager.getFairyRoseSeeds()),
    GRAPE_STARTER("Grape Starter", () -> CropCreator.GRAPE.createInstance(), Season.FALL, 75, 90, "Plant these in the fall. Takes 10 days to grow, but keeps producing after that. Grows on a trellis.", CropAssetManager.getGrapeSeeds()),
    PUMPKIN_SEEDS("Pumpkin Seeds", () -> CropCreator.PUMPKIN.createInstance(), Season.FALL, 125, 150, "Plant these in the fall. Takes 13 days to mature.", CropAssetManager.getPumpkinSeeds()),
    YAM_SEEDS("Yam Seeds", () -> CropCreator.YAM.createInstance(), Season.FALL, 75, 90, "Plant these in the fall. Takes 10 days to mature.", CropAssetManager.getYamSeeds()),
    RARE_SEED("Rare Seed", () -> CropCreator.SWEET_GEM_BERRY.createInstance(), Season.FALL, 1000, 0, "Sow in fall. Takes all season to grow.", CropAssetManager.getSweetGemBerrySeeds()),

    // Crops – Winter
    POWDERMELON_SEEDS("Powdermelon Seeds", () -> CropCreator.POWDERMELON.createInstance(), Season.WINTER, 20, 0, "This special melon grows in the winter. Takes 7 days to grow.", CropAssetManager.getPowdermelonSeeds()),

    // Special – All Seasons
    ANCIENT_SEEDS("Ancient Seeds", () -> CropCreator.ANCIENT_FRUIT.createInstance(), Season.ALL, 500, 0, "Could these still grow?", CropAssetManager.getAncientFruitSeeds()),
    MIXED_SEEDS("Mixed Seeds", null, Season.ALL, 0, 0, "", CropAssetManager.getMixedSeeds()),

    // Trees – All Seasons
    APRICOT_SAPLING("Apricot Sapling", () -> TreeCreator.APRICOT_TREE.createInstance(), Season.ALL, 2000, 0, "Takes 28 days to produce a mature Apricot tree. Bears fruit in the spring. Only grows if the 8 surrounding \"tiles\" are empty.", TreeAssetManager.getApricotSapling()),
    CHERRY_SAPLING("Cherry Sapling", () -> TreeCreator.CHERRY_TREE.createInstance(), Season.ALL, 3400, 0, "Takes 28 days to produce a mature Cherry tree. Bears fruit in the spring. Only grows if the 8 surrounding \"tiles\" are empty.", TreeAssetManager.getCherrySapling()),
    BANANA_SAPLING("Banana Sapling", () -> TreeCreator.BANANA_TREE.createInstance(), Season.ALL, 0, 0, "", TreeAssetManager.getBananaSapling()),
    MANGO_SAPLING("Mango Sapling", () -> TreeCreator.MANGO_TREE.createInstance(), Season.ALL, 0, 0, "", TreeAssetManager.getMangoSapling()),
    ORANGE_SAPLING("Orange Sapling", () -> TreeCreator.ORANGE_TREE.createInstance(), Season.ALL, 4000, 0, "Takes 28 days to produce a mature Orange tree. Bears fruit in the summer. Only grows if the 8 surrounding \"tiles\" are empty.", TreeAssetManager.getOrangeSapling()),
    PEACH_SAPLING("Peach Sapling", () -> TreeCreator.PEACH_TREE.createInstance(), Season.ALL, 6000, 0, "Takes 28 days to produce a mature Peach tree. Bears fruit in the summer. Only grows if the 8 surrounding \"tiles\" are empty.", TreeAssetManager.getPeachSapling()),
    APPLE_SAPLING("Apple Sappling", () -> TreeCreator.APPLE_TREE.createInstance(), Season.ALL, 4000, 0, "Takes 28 days to produce a mature Apple tree. Bears fruit in the fall. Only grows if the 8 surrounding \"tiles\" are empty.", TreeAssetManager.getAppleSapling()),
    POMEGRANATE_SAPLING("Pomegranate Sapling", () -> TreeCreator.POMEGRANATE_TREE.createInstance(), Season.ALL, 0, 0, "...", TreeAssetManager.getPomegranateSapling()),
    OAK_SAPLING("Acorns", () -> TreeCreator.OAK_TREE.createInstance(), Season.ALL, 0, 0, "", TreeAssetManager.getOakSapling()),
    MAPLE_SAPLING("Maple Seeds", () -> TreeCreator.MAPLE_TREE.createInstance(), Season.ALL, 0, 0, "", TreeAssetManager.getMapleSapling()),
    PINE_SAPLING("Pine Cones", () -> TreeCreator.PINE_TREE.createInstance(), Season.ALL, 0, 0, "", TreeAssetManager.getPineSapling()),
    MAHOGANY_SAPLING("Mahogany Seeds", () -> TreeCreator.MAHOGANY_TREE.createInstance(), Season.ALL, 0, 0, "", TreeAssetManager.getMahoganySapling()),
    MUSHROOM_SAPLING("Mushroom Tree Seeds", () -> TreeCreator.MUSHROOM_TREE.createInstance(), Season.ALL, 0, 0, "", TreeAssetManager.getMushroomSapling()),
    MYSTIC_SAPLING("Mystic Tree Seeds", () -> TreeCreator.MYSTIC_TREE.createInstance(), Season.ALL, 0, 0, "", TreeAssetManager.getMysticSapling());

    private final String name;
    private final Supplier<PlantAble> factory;
    private final Season season;
    private final int price;
    private final int offSeasonPrice;
    private final String description;
    private final Texture texture;

    PlantingSource(String name, Supplier<PlantAble> factory, Season season, int price, int offSeasonPrice, String description, Texture texture) {
        this.name = name;
        this.factory = factory;
        this.season = season;
        this.price = price;
        this.offSeasonPrice = offSeasonPrice;
        this.description = description;
        this.texture = texture;
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

    public int getPrice() {
        return price;
    }

    public int getOffSeasonPrice() {
        return offSeasonPrice;
    }

    public String getDescription() {
        return description;
    }

    public Texture getTexture() {
        return texture;
    }
}
