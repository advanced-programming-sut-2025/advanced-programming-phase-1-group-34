package org.Group34.model.enums.creatorOfNaturalElements;

import org.Group34.model.entities.naturalElements.Tree;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.foods.*;

/**
 * Enum for creating trees, now using PlantingSource instead of raw source strings.
 */
public enum TreeCreator {
    APRICOT_TREE("Apricot Tree", PlantingSource.APRICOT_SAPLING, new int[]{7,7,7,7}, 28, Fruit.APRICOT, new String[]{"Spring"}),
    CHERRY_TREE("Cherry Tree", PlantingSource.CHERRY_SAPLING, new int[]{7,7,7,7}, 28, Fruit.CHERRY, new String[]{"Spring"}),

    BANANA_TREE("Banana Tree", PlantingSource.BANANA_SAPLING, new int[]{7,7,7,7}, 28, Fruit.BANANA, new String[]{"Summer"}),
    MANGO_TREE("Mango Tree", PlantingSource.MANGO_SAPLING, new int[]{7,7,7,7}, 28, Fruit.MANGO, new String[]{"Summer"}),
    ORANGE_TREE("Orange Tree", PlantingSource.ORANGE_SAPLING, new int[]{7,7,7,7}, 28, Fruit.ORANGE, new String[]{"Summer"}),
    PEACH_TREE("Peach Tree", PlantingSource.PEACH_SAPLING, new int[]{7,7,7,7}, 28, Fruit.PEACH, new String[]{"Summer"}),

    APPLE_TREE("Apple Tree", PlantingSource.APPLE_SAPLING, new int[]{7,7,7,7}, 28, Fruit.APPLE, new String[]{"Autumn"}),
    POMEGRANATE_TREE("Pomegranate Tree", PlantingSource.POMEGRANATE_SAPLING, new int[]{7,7,7,7}, 28, Fruit.POMEGRANATE, new String[]{"Autumn"}),

    OAK_TREE("Oak Tree", PlantingSource.OAK_SAPLING, new int[]{7,7,7,7}, 28, OtherFarmingProduct.OAK_RESIN, new String[]{"Spring","Summer","Autumn","Winter"}),
    MAPLE_TREE("Maple Tree", PlantingSource.MAPLE_SAPLING, new int[]{7,7,7,7}, 28, OtherFarmingProduct.MAPLE_SYRUP, new String[]{"Spring","Summer","Autumn","Winter"}),
    PINE_TREE("Pine Tree", PlantingSource.PINE_SAPLING, new int[]{7,7,7,7}, 28, OtherFarmingProduct.PINE_TAR, new String[]{"Spring","Summer","Autumn","Winter"}),
    MAHOGANY_TREE("Mahogany Tree", PlantingSource.MAHOGANY_SAPLING, new int[]{7,7,7,7}, 28, OtherFarmingProduct.SAP, new String[]{"Spring","Summer","Autumn","Winter"}),
    MUSHROOM_TREE("Mushroom Tree", PlantingSource.MUSHROOM_SAPLING, new int[]{7,7,7,7}, 28, Fungi.COMMON_MUSHROOM, new String[]{"Spring","Summer","Autumn","Winter"}),
    MYSTIC_TREE("Mystic Tree", PlantingSource.MYSTIC_SAPLING, new int[]{7,7,7,7}, 28, OtherFarmingProduct.MYSTIC_SYRUP, new String[]{"Spring","Summer","Autumn","Winter"});

    private final String name;
    private final PlantingSource source;
    private final int[] stages;
    private final int totalHarvestTime;
    private final FarmingProduct farmingProduct;
    private final String[] seasons;

    TreeCreator(String name,
                PlantingSource source,
                int[] stages,
                int totalHarvestTime,
                FarmingProduct farmingProduct,
                String[] seasons) {
        this.name = name;
        this.source = source;
        this.stages = stages;
        this.totalHarvestTime = totalHarvestTime;
        this.farmingProduct = farmingProduct;
        this.seasons = seasons;
    }

    /**
     * Creates a new Tree instance using the PlantAble from the PlantingSource.
     */
    public Tree createInstance() {
        return new Tree(
                name,
                source,
                stages,
                totalHarvestTime,
                farmingProduct,
                seasons
        );
    }
}
