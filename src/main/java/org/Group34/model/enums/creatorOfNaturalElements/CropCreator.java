package org.Group34.model.enums.creatorOfNaturalElements;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.naturalElements.Crop;
import org.Group34.model.gameAssetManagers.CropAssetManager;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.foods.*;

/**
 * Enum for creating crops, now using PlantingSource instead of raw source strings.
 */
public enum CropCreator {
    BLUE_JAZZ("Blue Jazz", PlantingSource.JAZZ_SEEDS, new int[]{1, 2, 2, 2}, 7, true, 0, OtherFarmingProduct.BLUE_JAZZ, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getBlueJazzStage1(), CropAssetManager.getBlueJazzStage2(), CropAssetManager.getBlueJazzStage3(), CropAssetManager.getBlueJazzStage4()}),
    CARROT("Carrot", PlantingSource.CARROT_SEEDS, new int[]{1, 1, 1}, 3, true, 0, Vegetable.CARROT, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getCarrotStage1(), CropAssetManager.getCarrotStage2(), CropAssetManager.getCarrotStage3()}),
    CAULIFLOWER("Cauliflower", PlantingSource.CAULIFLOWER_SEEDS, new int[]{1, 2, 4, 4, 1}, 12, true, 0, Vegetable.CAULIFLOWER, new String[]{"Spring"}, true, new Texture[]{CropAssetManager.getCauliflowerStage1(), CropAssetManager.getCauliflowerStage2(), CropAssetManager.getCauliflowerStage3(), CropAssetManager.getCauliflowerStage4(), CropAssetManager.getCauliflowerStage5()}),
    COFFEE_BEAN("Coffee Bean", PlantingSource.COFFEE_BEAN, new int[]{1, 2, 2, 3, 2}, 10, false, 2, CropProduct.COFFEE_BEAN, new String[]{"Spring", "Summer"}, false, new Texture[]{CropAssetManager.getCoffeeBeanStage1(), CropAssetManager.getCoffeeBeanStage2(), CropAssetManager.getCoffeeBeanStage3(), CropAssetManager.getCoffeeBeanStage4(), CropAssetManager.getCoffeeBeanStage5()}),
    GARLIC("Garlic", PlantingSource.GARLIC_SEEDS, new int[]{1, 1, 1, 1}, 4, true, 0, Vegetable.GARLIC, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getGarlicStage1(), CropAssetManager.getGarlicStage2(), CropAssetManager.getGarlicStage3(), CropAssetManager.getGarlicStage4()}),
    GREEN_BEAN("Green Bean", PlantingSource.BEAN_STARTER, new int[]{1, 1, 1, 3, 4}, 10, false, 3, Vegetable.GREEN_BEAN, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getGreenBeanStage1(), CropAssetManager.getGreenBeanStage2(), CropAssetManager.getGreenBeanStage3(), CropAssetManager.getGreenBeanStage4(), CropAssetManager.getGreenBeanStage5()}),
    KALE("Kale", PlantingSource.KALE_SEEDS, new int[]{1, 2, 2, 1}, 6, true, 0, Vegetable.KALE, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getKaleStage1(), CropAssetManager.getKaleStage2(), CropAssetManager.getKaleStage3(), CropAssetManager.getKaleStage4()}),
    PARSNIP("Parsnip", PlantingSource.PARSNIP_SEEDS, new int[]{1, 1, 1, 1}, 4, true, 0, Vegetable.PARSNIP, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getParsnipStage1(), CropAssetManager.getParsnipStage2(), CropAssetManager.getParsnipStage3(), CropAssetManager.getParsnipStage4()}),
    POTATO("Potato", PlantingSource.POTATO_SEEDS, new int[]{1, 1, 1, 2, 1}, 6, true, 0, Vegetable.POTATO, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getPotatoStage1(), CropAssetManager.getPotatoStage2(), CropAssetManager.getPotatoStage3(), CropAssetManager.getPotatoStage4(), CropAssetManager.getPotatoStage5()}),
    RHUBARB("Rhubarb", PlantingSource.RHUBARB_SEEDS, new int[]{2, 2, 2, 3, 4}, 13, true, 0, Fungi.RHUBARB, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getRhubarbStage1(), CropAssetManager.getRhubarbStage2(), CropAssetManager.getRhubarbStage3(), CropAssetManager.getRhubarbStage4(), CropAssetManager.getRhubarbStage5()}),
    STRAWBERRY("Strawberry", PlantingSource.STRAWBERRY_SEEDS, new int[]{1, 1, 2, 2, 2}, 8, false, 4, Fruit.STRAWBERRY, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getStrawberryStage1(), CropAssetManager.getStrawberryStage2(), CropAssetManager.getStrawberryStage3(), CropAssetManager.getStrawberryStage4(), CropAssetManager.getStrawberryStage5()}),
    TULIP("Tulip", PlantingSource.TULIP_BULB, new int[]{1, 1, 2, 2}, 6, true, 0, OtherFarmingProduct.TULIP, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getTulipStage1(), CropAssetManager.getTulipStage2(), CropAssetManager.getTulipStage3(), CropAssetManager.getTulipStage4()}),
    UNMILLED_RICE("Unmilled Rice", PlantingSource.RICE_SHOOT, new int[]{1, 2, 2, 3}, 8, true, 0, CropProduct.UNMILLED_RICE, new String[]{"Spring"}, false, new Texture[]{CropAssetManager.getUnmilledRiceStage1(), CropAssetManager.getUnmilledRiceStage2(), CropAssetManager.getUnmilledRiceStage3(), CropAssetManager.getUnmilledRiceStage4()}),
    BLUEBERRY("Blueberry", PlantingSource.BLUEBERRY_SEEDS, new int[]{1, 3, 3, 4, 2}, 13, false, 4, Fruit.BLUEBERRY, new String[]{"Summer"}, false, new Texture[]{CropAssetManager.getBlueberryStage1(), CropAssetManager.getBlueberryStage2(), CropAssetManager.getBlueberryStage3(), CropAssetManager.getBlueberryStage4(), CropAssetManager.getBlueberryStage5()}),
    CORN("Corn", PlantingSource.CORN_SEEDS, new int[]{2, 3, 3, 3, 3}, 14, false, 4, Vegetable.CORN, new String[]{"Summer", "Autumn"}, false, new Texture[]{CropAssetManager.getCornStage1(), CropAssetManager.getCornStage2(), CropAssetManager.getCornStage3(), CropAssetManager.getCornStage4(), CropAssetManager.getCornStage5()}),
    HOPS("Hops", PlantingSource.HOPS_STARTER, new int[]{1, 1, 2, 3, 4}, 11, false, 1, CropProduct.HOPS, new String[]{"Summer"}, false, new Texture[]{CropAssetManager.getHopsStage1(), CropAssetManager.getHopsStage2(), CropAssetManager.getHopsStage3(), CropAssetManager.getHopsStage4(), CropAssetManager.getHopsStage5()}),
    HOT_PEPPER("Hot Pepper", PlantingSource.PEPPER_SEEDS, new int[]{1, 1, 1, 1, 1}, 5, false, 3, Vegetable.HOT_PEPPER, new String[]{"Summer"}, false, new Texture[]{CropAssetManager.getHotPepperStage1(), CropAssetManager.getHotPepperStage2(), CropAssetManager.getHotPepperStage3(), CropAssetManager.getHotPepperStage4(), CropAssetManager.getHotPepperStage5()}),
    MELON("Melon", PlantingSource.MELON_SEEDS, new int[]{1, 2, 3, 3, 3}, 12, true, 0, Fruit.MELON, new String[]{"Summer"}, true, new Texture[]{CropAssetManager.getMelonStage1(), CropAssetManager.getMelonStage2(), CropAssetManager.getMelonStage3(), CropAssetManager.getMelonStage4(), CropAssetManager.getMelonStage5()}),
    POPPY("Poppy", PlantingSource.POPPY_SEEDS, new int[]{1, 2, 2, 2}, 7, true, 0, OtherFarmingProduct.POPPY, new String[]{"Summer"}, false, new Texture[]{CropAssetManager.getPoppyStage1(), CropAssetManager.getPoppyStage2(), CropAssetManager.getPoppyStage3(), CropAssetManager.getPoppyStage4()}),
    RADISH("Radish", PlantingSource.RADISH_SEEDS, new int[]{2, 1, 2, 1}, 6, true, 0, Vegetable.RADISH, new String[]{"Summer"}, false, new Texture[]{CropAssetManager.getRadishStage1(), CropAssetManager.getRadishStage2(), CropAssetManager.getRadishStage3(), CropAssetManager.getRadishStage4()}),
    RED_CABBAGE("Red Cabbage", PlantingSource.RED_CABBAGE_SEEDS, new int[]{2, 1, 2, 2, 2}, 9, true, 0, Vegetable.RED_CABBAGE, new String[]{"Summer"}, false, new Texture[]{CropAssetManager.getRedCabbageStage1(), CropAssetManager.getRedCabbageStage2(), CropAssetManager.getRedCabbageStage3(), CropAssetManager.getRedCabbageStage4(), CropAssetManager.getRedCabbageStage5()}),
    STARFRUIT("Starfruit", PlantingSource.STARFRUIT_SEEDS, new int[]{2, 3, 2, 3, 3}, 13, true, 0, Fruit.STARFRUIT, new String[]{"Summer"}, false, new Texture[]{CropAssetManager.getStarfruitStage1(), CropAssetManager.getStarfruitStage2(), CropAssetManager.getStarfruitStage3(), CropAssetManager.getStarfruitStage4(), CropAssetManager.getStarfruitStage5()}),
    SUMMER_SPANGLE("Summer Spangle", PlantingSource.SPANGLE_SEEDS, new int[]{1, 2, 3, 1}, 8, true, 0, OtherFarmingProduct.SUMMER_SPANGLE, new String[]{"Summer"}, false, new Texture[]{CropAssetManager.getSummerSpangleStage1(), CropAssetManager.getSummerSpangleStage2(), CropAssetManager.getSummerSpangleStage3(), CropAssetManager.getSummerSpangleStage4()}),
    SUMMER_SQUASH("Summer Squash", PlantingSource.SUMMER_SQUASH_SEEDS, new int[]{1, 1, 1, 2, 1}, 6, false, 3, Vegetable.SUMMER_SQUASH, new String[]{"Summer"}, false, new Texture[]{CropAssetManager.getSummerSquashStage1(), CropAssetManager.getSummerSquashStage2(), CropAssetManager.getSummerSquashStage3(), CropAssetManager.getSummerSquashStage4(), CropAssetManager.getSummerSquashStage5()}),
    SUNFLOWER("Sunflower", PlantingSource.SUNFLOWER_SEEDS, new int[]{1, 2, 3, 2}, 8, true, 0, OtherFarmingProduct.SUNFLOWER, new String[]{"Summer", "Autumn"}, false, new Texture[]{CropAssetManager.getSunflowerStage1(), CropAssetManager.getSunflowerStage2(), CropAssetManager.getSunflowerStage3(), CropAssetManager.getSunflowerStage4()}),
    TOMATO("Tomato", PlantingSource.TOMATO_SEEDS, new int[]{2, 2, 2, 2, 3}, 11, false, 4, Vegetable.TOMATO, new String[]{"Summer"}, false, new Texture[]{CropAssetManager.getTomatoStage1(), CropAssetManager.getTomatoStage2(), CropAssetManager.getTomatoStage3(), CropAssetManager.getTomatoStage4(), CropAssetManager.getTomatoStage5()}),
    WHEAT("Wheat", PlantingSource.WHEAT_SEEDS, new int[]{1, 1, 1, 1}, 4, true, 0, CropProduct.WHEAT, new String[]{"Summer", "Autumn"}, false, new Texture[]{CropAssetManager.getWheatStage1(), CropAssetManager.getWheatStage2(), CropAssetManager.getWheatStage3(), CropAssetManager.getWheatStage4()}),
    AMARANTH("Amaranth", PlantingSource.AMARANTH_SEEDS, new int[]{1, 2, 2, 2}, 7, true, 0, Vegetable.AMARANTH, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getAmaranthStage1(), CropAssetManager.getAmaranthStage2(), CropAssetManager.getAmaranthStage3(), CropAssetManager.getAmaranthStage4()}),
    ARTICHOKE("Artichoke", PlantingSource.ARTICHOKE_SEEDS, new int[]{2, 2, 1, 2, 1}, 8, true, 0, Vegetable.ARTICHOKE, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getArtichokeStage1(), CropAssetManager.getArtichokeStage2(), CropAssetManager.getArtichokeStage3(), CropAssetManager.getArtichokeStage4(), CropAssetManager.getArtichokeStage5()}),
    BEET("Beet", PlantingSource.BEET_SEEDS, new int[]{1, 1, 2, 2}, 6, true, 0, Vegetable.BEET, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getBeetStage1(), CropAssetManager.getBeetStage2(), CropAssetManager.getBeetStage3(), CropAssetManager.getBeetStage4()}),
    BOK_CHOY("Bok Choy", PlantingSource.BOK_CHOY_SEEDS, new int[]{1, 1, 1, 1}, 4, true, 0, Vegetable.BOK_CHOY, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getBokChoyStage1(), CropAssetManager.getBokChoyStage2(), CropAssetManager.getBokChoyStage3(), CropAssetManager.getBokChoyStage4()}),
    BROCCOLI("Broccoli", PlantingSource.BROCCOLI_SEEDS, new int[]{2, 2, 2, 2}, 8, false, 4, Vegetable.BROCCOLI, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getBroccoliStage1(), CropAssetManager.getBroccoliStage2(), CropAssetManager.getBroccoliStage3(), CropAssetManager.getBroccoliStage4()}),
    CRANBERRIES("Cranberries", PlantingSource.CRANBERRY_SEEDS, new int[]{1, 2, 1, 1, 2}, 7, false, 5, Fruit.CRANBERRIES, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getCranberriesStage1(), CropAssetManager.getCranberriesStage2(), CropAssetManager.getCranberriesStage3(), CropAssetManager.getCranberriesStage4(), CropAssetManager.getCranberriesStage5()}),
    EGGPLANT("Eggplant", PlantingSource.EGGPLANT_SEEDS, new int[]{1, 1, 1, 1}, 4, false, 5, Vegetable.EGGPLANT, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getEggplantStage1(), CropAssetManager.getEggplantStage2(), CropAssetManager.getEggplantStage3(), CropAssetManager.getEggplantStage4()}),
    FAIRY_ROSE("Fairy Rose", PlantingSource.FAIRY_SEEDS, new int[]{1, 4, 4, 3}, 12, true, 0, OtherFarmingProduct.FAIRY_ROSE, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getFairyRoseStage1(), CropAssetManager.getFairyRoseStage2(), CropAssetManager.getFairyRoseStage3(), CropAssetManager.getFairyRoseStage4()}),
    GRAPE("Grape", PlantingSource.GRAPE_STARTER, new int[]{1, 1, 2, 3, 3}, 10, false, 3, Fruit.GRAPE, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getGrapeStage1(), CropAssetManager.getGrapeStage2(), CropAssetManager.getGrapeStage3(), CropAssetManager.getGrapeStage4(), CropAssetManager.getGrapeStage5()}),
    PUMPKIN("Pumpkin", PlantingSource.PUMPKIN_SEEDS, new int[]{1, 2, 3, 4, 3}, 13, true, 0, Vegetable.PUMPKIN, new String[]{"Autumn"}, true, new Texture[]{CropAssetManager.getPumpkinStage1(), CropAssetManager.getPumpkinStage2(), CropAssetManager.getPumpkinStage3(), CropAssetManager.getPumpkinStage4(), CropAssetManager.getPumpkinStage5()}),
    YAM("Yam", PlantingSource.YAM_SEEDS, new int[]{1, 3, 3, 3}, 10, true, 0, Vegetable.YAM, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getYamStage1(), CropAssetManager.getYamStage2(), CropAssetManager.getYamStage3(), CropAssetManager.getYamStage4()}),
    SWEET_GEM_BERRY("Sweet Gem Berry", PlantingSource.RARE_SEED, new int[]{2, 4, 6, 6, 6}, 24, true, 0, Fruit.SWEET_GEM_BERRY, new String[]{"Autumn"}, false, new Texture[]{CropAssetManager.getSweetGemBerryStage1(), CropAssetManager.getSweetGemBerryStage2(), CropAssetManager.getSweetGemBerryStage3(), CropAssetManager.getSweetGemBerryStage4(), CropAssetManager.getSweetGemBerryStage5()}),
    POWDERMELON("Powdermelon", PlantingSource.POWDERMELON_SEEDS, new int[]{1, 2, 1, 2, 1}, 7, true, 0, Fruit.POWDERMELON, new String[]{"Winter"}, true, new Texture[]{CropAssetManager.getPowdermelonStage1(), CropAssetManager.getPowdermelonStage2(), CropAssetManager.getPowdermelonStage3(), CropAssetManager.getPowdermelonStage4(), CropAssetManager.getPowdermelonStage5()}),
    ANCIENT_FRUIT("Ancient Fruit", PlantingSource.ANCIENT_SEEDS, new int[]{2, 7, 7, 7, 5}, 28, false, 7, Fruit.ANCIENT_FRUIT, new String[]{"Spring", "Summer", "Autumn"}, false, new Texture[]{CropAssetManager.getAncientFruitStage1(), CropAssetManager.getAncientFruitStage2(), CropAssetManager.getAncientFruitStage3(), CropAssetManager.getAncientFruitStage4(), CropAssetManager.getAncientFruitStage5()});

    private final String name;
    private final PlantingSource source;
    private final int[] stage;
    private final int totalHarvestTime;
    private final boolean isOneTime;
    private final int regrowthTime;
    private final FarmingProduct farmingProduct;
    private final String[] seasons;
    private final boolean canBecomeGiant;
    private final Texture[] stageTexture;

    CropCreator(String name,
                PlantingSource source,
                int[] stage,
                int totalHarvestTime,
                boolean isOneTime,
                int regrowthTime,
                FarmingProduct farmingProduct,
                String[] seasons,
                boolean canBecomeGiant,
                Texture[] stageTexture) {
        this.name = name;
        this.source = source;
        this.stage = stage;
        this.totalHarvestTime = totalHarvestTime;
        this.isOneTime = isOneTime;
        this.regrowthTime = regrowthTime;
        this.farmingProduct = farmingProduct;
        this.seasons = seasons;
        this.canBecomeGiant = canBecomeGiant;
        this.stageTexture = stageTexture;
    }

    public Crop createInstance() {
        return new Crop(
                name,
                source,
                stage,
                totalHarvestTime,
                isOneTime,
                regrowthTime,
                farmingProduct,
                seasons,
                canBecomeGiant,
                stageTexture
        );
    }
}
