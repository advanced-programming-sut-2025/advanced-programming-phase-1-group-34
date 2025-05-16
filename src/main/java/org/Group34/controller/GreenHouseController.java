package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.GreenHouse;
import org.Group34.model.entities.naturalElements.Crop;
import org.Group34.model.items.crafting.Ingredient;

public class GreenHouseController {
    private final GreenHouse greenhouse;

    public GreenHouseController(GreenHouse greenhouse) {
        this.greenhouse = greenhouse;
    }

    public Result repairGreenhouse(Player player) {
        if (player.isExistInInventory(Ingredient.WOOD) &&
                player.getAmountOfItem(Ingredient.WOOD) >= greenhouse.getRepairWood() &&
                player.getMoney() >= greenhouse.getRepairMoney()) {

            player.removeFromInventory(Ingredient.WOOD, greenhouse.getRepairWood());
            player.addMoney(-greenhouse.getRepairMoney());
            greenhouse.repair();

            return new Result(true, "Greenhouse repaired!\n Gold: " + player.getMoney() +
                                                                         "\nWood: " + player.getAmountOfItem(Ingredient.WOOD));
        }
        return new Result(false, "Not enough resources.");
    }

    public void dailyMaintenance() {
        greenhouse.dailyUpdate();
    }

    public Result plantCrop(int row, int col, Crop crop) {
        boolean success = greenhouse.plantCrop(row, col, crop);
        return new Result(success, success
                ? "Crop planted successfully."
                : "Failed to plant crop. Maybe plot is already occupied.");
    }

    public Result fillWaterTank() {
        greenhouse.fillWaterTank();
        return new Result(true, "Water tank filled.");
    }

    public Result waterPlot(int row, int col) {
        boolean success = greenhouse.waterPlot(row, col);
        return new Result(success, success
                ? String.format("Plot (%d,%d) watered successfully.", row, col)
                : "Failed to water plot. Not enough water.");
    }

    public Result getStatus() {
        String status = String.format("Greenhouse Status: %s | Water: %d/%d",
                greenhouse.isRepaired() ? "Operational" : "Needs Repair",
                greenhouse.getCurrentWater(),
                greenhouse.getWaterTankCapacity());
        return new Result(true, status);
    }
}
