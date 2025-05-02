package org.Group34.model.entities;

import org.Group34.model.map.Space;

public class Player extends Entity {
    private Space currentSpace;
    private float energy = 200;


    public Player(int[] initialLocation) {
        super(initialLocation, true);
    }

    public Space getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(Space currentSpace) {
        this.currentSpace = currentSpace;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "P";
    }
}
