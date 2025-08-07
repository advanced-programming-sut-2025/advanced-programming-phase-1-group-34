package org.Group34.model.enums.creatorOfNaturalElements;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.naturalElements.ForagingTree;
import org.Group34.model.gameAssetManagers.TreeAssetManager;
import org.Group34.model.items.Item;
import org.Group34.model.items.foods.Fungi;
import org.Group34.model.items.foods.OtherFarmingProduct;

public enum ForagingTreeCreator {
    MAPLE_SEEDS("Maple Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"}, OtherFarmingProduct.MAPLE_SYRUP, TreeAssetManager.getMapleSapling()),
    PINE_CONES("Pine Cones", new String[]{"Spring", "Summer", "Autumn", "Winter"}, OtherFarmingProduct.PINE_TAR, TreeAssetManager.getPineSapling()),
    MAHOGANY_SEEDS("Mahogany Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"}, OtherFarmingProduct.SAP, TreeAssetManager.getMahoganySapling()),
    MUSHROOM_TREE_SEEDS("Mushroom Tree Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"}, Fungi.COMMON_MUSHROOM, TreeAssetManager.getMushroomSapling());

    private final String name;
    private final String[] seasons;
    private final Item product;
    private final Texture texture;

    ForagingTreeCreator(String name, String[] seasons, Item product, Texture texture) {
        this.name = name;
        this.seasons = seasons;
        this.product = product;
        this.texture = texture;
    }

    public ForagingTree createInstance() {
        return new ForagingTree(name, seasons, texture, product);
    }
}
