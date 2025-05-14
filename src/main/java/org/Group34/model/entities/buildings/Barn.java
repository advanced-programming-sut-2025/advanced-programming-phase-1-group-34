package org.Group34.model.entities.buildings;

import org.Group34.model.enums.animals.BarnType;

public class Barn extends AnimalsBuilding {
    public Barn(BarnType type) {
        this.type = type.name();
        this.capacity = type.getCapacity();
    }

    @Override
    public boolean canUpgrade() {
        return BarnType.valueOf(type).ordinal() < BarnType.values().length - 1;
    }

    @Override
    public void upgrade() {
        BarnType current = BarnType.valueOf(type);
        this.type = BarnType.values()[current.ordinal() + 1].name();
        this.capacity = BarnType.valueOf(type).getCapacity();
    }
}