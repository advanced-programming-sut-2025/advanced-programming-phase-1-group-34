package org.Group34.model.entities.buildings;

import org.Group34.model.entities.naturalElements.Crop;

public class Plot {
    private Crop crop;
    private boolean watered;

    public boolean plantCrop(Crop crop) {
        if (this.crop == null) {
            this.crop = crop;
            return true;
        }
        return false;
    }

    public boolean water() {
        if (!watered) {
            watered = true;
            return true;
        }
        return false;
    }

    // Getters and status methods
    public boolean hasCrop() {
        return crop != null;
    }

    public boolean isWatered() {
        return watered;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setWatered(boolean watered) {
        this.watered = watered;
    }
}
