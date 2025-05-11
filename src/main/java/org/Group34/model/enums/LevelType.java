package org.Group34.model.enums;

public enum LevelType {
    FARMING_LEVEL("Farming Level"),
    MINING_LEVEL("Mining Level"),
    FORAGING_LEVEL("Foraging Level"),
    FISHING_LEVEL("Fishing Level");

    private final String name;

    LevelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
