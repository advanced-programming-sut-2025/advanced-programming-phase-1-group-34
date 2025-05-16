package org.Group34.model.items.tools;

import org.Group34.model.items.Item;

public enum ToolType implements Item {
    PLASTIC_AXE("Plastic Axe", "Plastic", 5, 0, 0),
    COPPER_AXE("Copper Axe", "Copper", 4, 0, 0),
    IRON_AXE("Iron Axe", "Iron", 3, 0, 0),
    GOLD_AXE("Gold Axe", "Gold", 2, 0, 0),
    IRIDIUM_AXE("Iridium Axe", "Iridium", 1, 0, 0),

    PLASTIC_HOE("Plastic Hoe", "Plastic", 5, 0, 0),
    COPPER_HOE("Copper Hoe", "Copper", 4, 0, 0),
    IRON_HOE("Iron Hoe", "Iron", 3, 0, 0),
    GOLD_HOE("Gold Hoe", "Gold", 2, 0, 0),
    IRIDIUM_HOE("Iridium Hoe", "Iridium", 1, 0, 0),

    PLASTIC_PICKAXE("Plastic Pickaxe", "Plastic", 5, 0, 0),
    COPPER_PICKAXE("Copper Pickaxe", "Copper", 4, 0, 0),
    IRON_PICKAXE("Iron Pickaxe", "Iron", 3, 0, 0),
    GOLD_PICKAXE("Gold Pickaxe", "Gold", 2, 0, 0),
    IRIDIUM_PICKAXE("Iridium Pickaxe", "Iridium", 1, 0, 0),

    PLASTIC_WATERING_CAN("Plastic Watering Can", "Plastic", 5, 0, 40),
    COPPER_WATERING_CAN("Copper Watering Can", "Copper", 4, 0, 55),
    IRON_WATERING_CAN("Iron Watering Can", "Iron", 3, 0, 70),
    GOLD_WATERING_CAN("Gold Watering Can", "Gold", 2, 0, 85),
    IRIDIUM_WATERING_CAN("Iridium Watering Can", "Iridium", 1, 0, 100),

    TRAINING_FISHING_POLE("Training Fishing Pole", "Training", 5, 0.1, 0),
    BAMBOO_FISHING_POLE("Bamboo Fishing Pole", "Bamboo", 4, 0.5, 0),
    FIBERGLASS_FISHING_POLE("Fiberglass Fishing Pole", "Fiberglass", 3, 0.9, 0),
    IRIDIUM_FISHING_POLE("Iridium Fishing Pole", "Iridium", 2, 1.2, 0)
    ;

    private final String name;
    private final String material;
    private final int energy;
    private final double qualityModifier;
    private final int capacity;

    ToolType(String name, String material, int energy, double qualityModifier, int capacity) {
        this.name = name;
        this.material = material;
        this.energy = energy;
        this.qualityModifier = qualityModifier;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public int getEnergy() {
        return energy;
    }

    public double getQualityModifier() {
        return qualityModifier;
    }

    public int getCapacity() {
        return capacity;
    }
}
