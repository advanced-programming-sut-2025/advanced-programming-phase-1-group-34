package org.Group34.model.enums.creatorOfNaturalElements;

import org.Group34.model.entities.naturalElements.ForagingTree;

public enum ForagingTreeCreator {
    ACORNS("Acorns", new String[]{"Spring", "Summer", "Autumn", "Winter"}),
    MAPLE_SEEDS("Maple Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"}),
    PINE_CONES("Pine Cones", new String[]{"Spring", "Summer", "Autumn", "Winter"}),
    MAHOGANY_SEEDS("Mahogany Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"}),
    MUSHROOM_TREE_SEEDS("Mushroom Tree Seeds", new String[]{"Spring", "Summer", "Autumn", "Winter"});

    private final String name;
    private final String[] seasons;

    ForagingTreeCreator(String name, String[] seasons) {
        this.name = name;
        this.seasons = seasons;
    }

    public ForagingTree createInstance() {
        return new ForagingTree(name, seasons);
    }
}
