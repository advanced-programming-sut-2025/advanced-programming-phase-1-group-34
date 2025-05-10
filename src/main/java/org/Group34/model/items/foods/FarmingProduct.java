package org.Group34.model.items.foods;

public interface FarmingProduct extends Food{
    int getBaseSellPrice();
    boolean isEdible();
    int getEnergy();
    int getHealth();
}
