package org.Group34.model.enums.fishing;

public enum FishingRodType {
    TRAINING("Training Rod", 0.1),
    BAMBOO("Bamboo Pole", 0.5),
    FIBERGLASS("Fiberglass Rod", 0.9),
    IRIDIUM("Iridium Rod", 1.2);

    public final String displayName;
    public final double qualityModifier;

    FishingRodType(String name, double modifier) {
        this.displayName = name;
        this.qualityModifier = modifier;
    }
}