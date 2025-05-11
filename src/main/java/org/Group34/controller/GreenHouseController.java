package org.Group34.controller;

import org.Group34.model.Result;
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

    public Result repairGreenhouse() {

        // if (inventoryController.hasItem("Wood", greenhouse.getRepairWood()) &&
        //         inventoryController.hasItem("Stone", greenhouse.getRepairStone())) {
        //     inventoryController.removeItem("Wood", greenhouse.getRepairWood());
        //     inventoryController.removeItem("Stone", greenhouse.getRepairStone());
        //     boolean repaired = greenhouse.repair(greenhouse.getRepairWood(), greenhouse.getRepairStone());
        //     return new Result(repaired, repaired ? "Greenhouse repaired!" : "Repair failed.");
        // }

        //TODO add hasItem and removeItem methods to inventory class
        return new Result(false, "Inventory system not implemented yet.");
    }

    public void dailyMaintenance(Season currentSeason, boolean isRaining) {
        greenhouse.dailyUpdate(currentSeason, isRaining);
    }

    public Result plantCrop(int row, int col, Crop crop) {
        boolean success = greenhouse.plantCrop(row, col, crop);
        return new Result(success, success
                ? "Crop planted successfully."
                : "Failed to plant crop. Maybe plot is already occupied?");
    }

    public Result fillWaterTank() {
        greenhouse.fillWaterTank();
        return new Result(true, "Water tank filled.");
    }

    public Result waterPlot(int row, int col) {
        boolean success = greenhouse.waterPlot(row, col);
        return new Result(success, success
                ? String.format("Plot (%d,%d) watered successfully.", row, col)
                : "Failed to water plot. Not enough water or invalid position?");
    }

    public Result getStatus() {
        String status = String.format("Greenhouse Status: %s | Water: %d/%d",
                greenhouse.isRepaired() ? "Operational" : "Needs Repair",
                greenhouse.getCurrentWater(),
                greenhouse.getWaterTankCapacity());
        return new Result(true, status);
    }
}
