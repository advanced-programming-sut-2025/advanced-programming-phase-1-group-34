package org.Group34.model.items.tools;

import org.Group34.model.items.Item;

public enum ToolType implements Item {
    PLASTIC_AXE("Plastic Axe", "Plastic", 5, 0, 0, 0),
    COPPER_AXE("Copper Axe", "Copper", 4, 0, 0, 0),
    IRON_AXE("Iron Axe", "Iron", 3, 0, 0, 0),
    GOLD_AXE("Gold Axe", "Gold", 2, 0, 0, 0),
    IRIDIUM_AXE("Iridium Axe", "Iridium", 1, 0, 0, 0),

    PLASTIC_HOE("Plastic Hoe", "Plastic", 5, 0, 0, 0),
    COPPER_HOE("Copper Hoe", "Copper", 4, 0, 0, 0),
    IRON_HOE("Iron Hoe", "Iron", 3, 0, 0, 0),
    GOLD_HOE("Gold Hoe", "Gold", 2, 0, 0, 0),
    IRIDIUM_HOE("Iridium Hoe", "Iridium", 1, 0, 0, 0),

    PLASTIC_PICKAXE("Plastic Pickaxe", "Plastic", 5, 0, 0, 0),
    COPPER_PICKAXE("Copper Pickaxe", "Copper", 4, 0, 0, 0),
    IRON_PICKAXE("Iron Pickaxe", "Iron", 3, 0, 0, 0),
    GOLD_PICKAXE("Gold Pickaxe", "Gold", 2, 0, 0, 0),
    IRIDIUM_PICKAXE("Iridium Pickaxe", "Iridium", 1, 0, 0, 0),

    PLASTIC_WATERING_CAN("Plastic Watering Can", "Plastic", 5, 0, 40, 0),
    COPPER_WATERING_CAN("Copper Watering Can", "Copper", 4, 0, 55, 0),
    IRON_WATERING_CAN("Iron Watering Can", "Iron", 3, 0, 70, 0),
    GOLD_WATERING_CAN("Gold Watering Can", "Gold", 2, 0, 85, 0),
    IRIDIUM_WATERING_CAN("Iridium Watering Can", "Iridium", 1, 0, 100, 0),

    TRAINING_FISHING_POLE("Training Fishing Pole", "Training", 5, 0.1, 0, 0),
    BAMBOO_FISHING_POLE("Bamboo Fishing Pole", "Bamboo", 4, 0.5, 0, 0),
    FIBERGLASS_FISHING_POLE("Fiberglass Fishing Pole", "Fiberglass", 3, 0.9, 0, 0),
    IRIDIUM_FISHING_POLE("Iridium Fishing Pole", "Iridium", 2, 1.2, 0, 0),

    BASIC_BACKPACK("Basic Backpack", "", 0, 0, 12, 0),
    BIG_BACKPACK("Big Backpack", "", 0, 0, 24, 0),
    DELUXE_BACKPACK("Deluxe Backpack", "", 0, 0, 10000, 0),

    PLASTIC_TRASH_CAN("Plastic Trash Can", "Plastic", 0, 0, 0, 0),
    COPPER_TRASH_CAN("Copper Trash Can", "Copper", 0, 0, 0, 15),
    IRON_TRASH_CAN("Iron Trash Can", "Iron", 0, 0, 0, 30),
    GOLD_TRASH_CAN("Gold Trash Can", "Gold", 0, 0, 0, 45),
    IRIDIUM_TRASH_CAN("Iridium Trash Can", "Iridium", 0, 0, 0, 60)
    ;

    private final String name;
    private final String material;
    private final int energy;
    private final double qualityModifier;
    private final int capacity;
    private final int returnPercentage;

    ToolType(String name, String material, int energy, double qualityModifier, int capacity, int returnPercentage) {
        this.name = name;
        this.material = material;
        this.energy = energy;
        this.qualityModifier = qualityModifier;
        this.capacity = capacity;
        this.returnPercentage = returnPercentage;
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

    public int getReturnPercentage() {
        return returnPercentage;
    }
}
