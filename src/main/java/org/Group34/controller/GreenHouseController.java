package org.Group34.controller;

import org.Group34.model.entities.buildings.GreenHouse;
import org.Group34.model.entities.naturalElements.Crop;
import org.Group34.model.enums.Season;

public class GreenHouseController {
    private final GreenHouse greenhouse;
    private final InventoryController inventoryController;

    public GreenHouseController(GreenHouse greenhouse, InventoryController inventoryController) {
        this.greenhouse = greenhouse;
        this.inventoryController = inventoryController;
    }

    public boolean repairGreenhouse() {
//        if (inventoryController.hasItem("Wood", greenhouse.getRepairWood()) &&
//                inventoryController.hasItem("Stone", greenhouse.getRepairStone())) {
//
//            inventoryController.removeItem("Wood", greenhouse.getRepairWood());
//            inventoryController.removeItem("Stone", greenhouse.getRepairStone());
//            return greenhouse.repair(greenhouse.getRepairWood(), greenhouse.getRepairStone());
//        }
        //TODO add hasItem and removeItem methods to inventory class
        return false;
    }

    public void dailyMaintenance(Season currentSeason, boolean isRaining) {
        greenhouse.dailyUpdate(currentSeason, isRaining);
    }

    public boolean plantCrop(int row, int col, Crop crop) {
        return greenhouse.plantCrop(row, col, crop);
    }

    public void fillWaterTank() {
        greenhouse.fillWaterTank();
    }

    public boolean waterPlot(int row, int col) {
        return greenhouse.waterPlot(row, col);
    }

    // Additional methods for UI interactions
    public String getStatus() {
        return String.format("Greenhouse Status: %s | Water: %d/%d",
                greenhouse.isRepaired() ? "Operational" : "Needs Repair",
                greenhouse.getCurrentWater(),
                greenhouse.getWaterTankCapacity());
    }
}