package org.Group34.model.entities.buildings;

import org.Group34.model.entities.naturalElements.Crop;
import org.Group34.model.enums.Color;
import org.Group34.model.enums.Season;

public class GreenHouse extends Building{
    private static final int REPAIR_WOOD = 500;
    private static final int REPAIR_STONE = 1000;

    private boolean isRepaired;
    private Plot[][] plots;
    private final int waterTankCapacity = 100;
    private int currentWater;

    public GreenHouse() {
        this.isRepaired = false;
        this.plots = new Plot[6][5]; // 6 rows x 5 columns
        initializePlots();
    }

    private void initializePlots() {
        for (int i = 0; i < plots.length; i++) {
            for (int j = 0; j < plots[i].length; j++) {
                plots[i][j] = new Plot();
            }
        }
    }

    public boolean repair(int availableWood, int availableStone) {
        if (!isRepaired && availableWood >= REPAIR_WOOD && availableStone >= REPAIR_STONE) {
            this.isRepaired = true;
            return true;
        }
        return false;
    }

    public void dailyUpdate(Season currentSeason, boolean isRaining) {
        if (!isRepaired) return;

        for (Plot[] row : plots) {
            for (Plot plot : row) {
                if (plot.hasCrop()) {
                    // Greenhouse-specific growth rules
                    //plot.getCrop().grow(currentSeason, false); // plot.getCrop().wither(); //TODO add crop.grow()

                    // Mandatory watering system
                    if (!plot.isWatered()) {
                        // plot.getCrop().wither(); //TODO add crop.wither()
                    }
                }
                plot.setWatered(false); // Reset watering for next day
            }
        }
    }

    // Special greenhouse planting rules
    public boolean plantCrop(int row, int col, Crop crop) {
        if (!isRepaired || row < 0 || row >= plots.length || col < 0 || col >= plots[0].length) {
            return false;
        }
        return plots[row][col].plantCrop(crop);
    }

    public void fillWaterTank() {
        this.currentWater = waterTankCapacity;
    }

    public boolean waterPlot(int row, int col) {
        if (currentWater > 0 && isRepaired) {
            if (plots[row][col].water()) {
                currentWater--;
                return true;
            }
        }
        return false;
    }

    // Getters and status methods
    public boolean isRepaired() {
        return isRepaired;
    }

    public int getRepairWood() {
        return REPAIR_WOOD;
    }

    public int getRepairStone() {
        return REPAIR_STONE;
    }

    public Plot[][] getPlots() {
        return plots;
    }

    public int getCurrentWater() {
        return currentWater;
    }

    public int getWaterTankCapacity() {
        return waterTankCapacity;
    }


    @Override
    public String toString() {
        return Color.YELLOW + "G" + Color.RESET;
    }
}
