package org.Group34.model.items.tools;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.gameAssetManagers.ToolAssetManager;
import org.Group34.model.items.Item;

public enum ToolType implements Item {
    BASIC_AXE("Basic Axe", "Basic", 5, 0, 0, 0, ToolAssetManager.getBasicAxe()),
    COPPER_AXE("Copper Axe", "Copper", 4, 0, 0, 0, ToolAssetManager.getCopperAxe()),
    IRON_AXE("Iron Axe", "Iron", 3, 0, 0, 0, ToolAssetManager.getIronAxe()),
    GOLD_AXE("Gold Axe", "Gold", 2, 0, 0, 0, ToolAssetManager.getGoldAxe()),
    IRIDIUM_AXE("Iridium Axe", "Iridium", 1, 0, 0, 0, ToolAssetManager.getIridiumAxe()),

    BASIC_HOE("Basic Hoe", "Basic", 5, 0, 0, 0, ToolAssetManager.getBasicHoe()),
    COPPER_HOE("Copper Hoe", "Copper", 4, 0, 0, 0, ToolAssetManager.getCopperHoe()),
    IRON_HOE("Iron Hoe", "Iron", 3, 0, 0, 0, ToolAssetManager.getIronHoe()),
    GOLD_HOE("Gold Hoe", "Gold", 2, 0, 0, 0, ToolAssetManager.getGoldHoe()),
    IRIDIUM_HOE("Iridium Hoe", "Iridium", 1, 0, 0, 0, ToolAssetManager.getIridiumHoe()),

    BASIC_PICKAXE("Basic Pickaxe", "Basic", 5, 0, 0, 0, ToolAssetManager.getBasicPickaxe()),
    COPPER_PICKAXE("Copper Pickaxe", "Copper", 4, 0, 0, 0, ToolAssetManager.getCopperPickaxe()),
    IRON_PICKAXE("Iron Pickaxe", "Iron", 3, 0, 0, 0, ToolAssetManager.getIronPickaxe()),
    GOLD_PICKAXE("Gold Pickaxe", "Gold", 2, 0, 0, 0, ToolAssetManager.getGoldPickaxe()),
    IRIDIUM_PICKAXE("Iridium Pickaxe", "Iridium", 1, 0, 0, 0, ToolAssetManager.getIridiumPickaxe()),

    BASIC_WATERING_CAN("Basic Watering Can", "Basic", 5, 0, 40, 0, ToolAssetManager.getBasicWateringCan()),
    COPPER_WATERING_CAN("Copper Watering Can", "Copper", 4, 0, 55, 0, ToolAssetManager.getCopperWateringCan()),
    IRON_WATERING_CAN("Iron Watering Can", "Iron", 3, 0, 70, 0, ToolAssetManager.getIronWateringCan()),
    GOLD_WATERING_CAN("Gold Watering Can", "Gold", 2, 0, 85, 0, ToolAssetManager.getGoldWateringCan()),
    IRIDIUM_WATERING_CAN("Iridium Watering Can", "Iridium", 1, 0, 100, 0, ToolAssetManager.getIridiumWateringCan()),

    TRAINING_FISHING_POLE("Training Fishing Pole", "Training", 5, 0.1, 0, 0, ToolAssetManager.getTrainingFishingPole()),
    BAMBOO_FISHING_POLE("Bamboo Fishing Pole", "Bamboo", 4, 0.5, 0, 0, ToolAssetManager.getBambooFishingPole()),
    FIBERGLASS_FISHING_POLE("Fiberglass Fishing Pole", "Fiberglass", 3, 0.9, 0, 0, ToolAssetManager.getFiberglassFishingPole()),
    IRIDIUM_FISHING_POLE("Iridium Fishing Pole", "Iridium", 2, 1.2, 0, 0, ToolAssetManager.getIridiumFishingPole()),

    BASIC_BACKPACK("Basic Backpack", "", 0, 0, 12, 0, ToolAssetManager.getBasicBackpack()),
    BIG_BACKPACK("Big Backpack", "", 0, 0, 24, 0, ToolAssetManager.getBigBackpack()),
    DELUXE_BACKPACK("Deluxe Backpack", "", 0, 0, 10000, 0, ToolAssetManager.getDeluxeBackpack()),

    BASIC_TRASH_CAN("Basic Trash Can", "Basic", 0, 0, 0, 0, ToolAssetManager.getBasicTrashCan()),
    COPPER_TRASH_CAN("Copper Trash Can", "Copper", 0, 0, 0, 15, ToolAssetManager.getCopperTrashCan()),
    IRON_TRASH_CAN("Iron Trash Can", "Iron", 0, 0, 0, 30, ToolAssetManager.getIronTrashCan()),
    GOLD_TRASH_CAN("Gold Trash Can", "Gold", 0, 0, 0, 45, ToolAssetManager.getGoldTrashCan()),
    IRIDIUM_TRASH_CAN("Iridium Trash Can", "Iridium", 0, 0, 0, 60, ToolAssetManager.getIridiumTrashCan())
    ;

    private final String name;
    private final String material;
    private final int energy;
    private final double qualityModifier;
    private final int capacity;
    private final int returnPercentage;
    private final Texture texture;

    ToolType(String name, String material, int energy, double qualityModifier, int capacity, int returnPercentage, Texture texture) {
        this.name = name;
        this.material = material;
        this.energy = energy;
        this.qualityModifier = qualityModifier;
        this.capacity = capacity;
        this.returnPercentage = returnPercentage;
        this.texture = texture;
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

    public Texture getTexture() {
        return texture;
    }
}
