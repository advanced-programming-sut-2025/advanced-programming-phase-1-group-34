package org.Group34.model.enums.creatorOfNaturalElements;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.naturalElements.ForagingTree;
import org.Group34.model.gameAssetManagers.TreeAssetManager;

public enum ForagingTreeCreator {
    ACORNS("Acorns", new String[]{"Spring", "Summer", "Autumn", "Winter"}, TreeAssetManager.getAcorn()),
    MAPLE_SEEDS("Maple Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"}, TreeAssetManager.getMapleSapling()),
    PINE_CONES("Pine Cones", new String[]{"Spring", "Summer", "Autumn", "Winter"}, TreeAssetManager.getPineSapling()),
    MAHOGANY_SEEDS("Mahogany Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"}, TreeAssetManager.getMahoganySapling()),
    MUSHROOM_TREE_SEEDS("Mushroom Tree Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"}, TreeAssetManager.getMushroomSapling());

    private final String name;
    private final String[] seasons;
    private final Texture texture;

    ForagingTreeCreator(String name, String[] seasons, Texture texture) {
        this.name = name;
        this.seasons = seasons;
        this.texture = texture;
    }

    public ForagingTree createInstance() {
        return new ForagingTree(name, seasons, texture);
    }
}
