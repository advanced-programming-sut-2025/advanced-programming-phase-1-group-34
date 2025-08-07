package org.Group34.model.gameAssetManagers;

import com.badlogic.gdx.graphics.Texture;

public class FertilizerAssetManager {
    private final static Texture basicFertilizer = new Texture("fertilizer/Basic_Fertilizer.png");
    private final static Texture qualityFertilizer = new Texture("fertilizer/Quality_Fertilizer.png");
    private final static Texture deluxeFertilizer = new Texture("fertilizer/Deluxe_Fertilizer.png");
    private final static Texture speedGrowFertilizer = new Texture("fertilizer/Speed-Gro.png");

    // ----- getters -----

    public static Texture getBasicFertilizer() {
        return basicFertilizer;
    }

    public static Texture getQualityFertilizer() {
        return qualityFertilizer;
    }

    public static Texture getDeluxeFertilizer() {
        return deluxeFertilizer;
    }

    public static Texture getSpeedGrowFertilizer() {
        return speedGrowFertilizer;
    }

    // -------------------
}
