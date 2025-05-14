package org.Group34.model.entities.buildings;

import org.Group34.model.enums.animals.BarnType;

public class Coop extends AnimalsBuilding {
    public Coop(BarnType type) {
        this.type = type.name();
        this.capacity = type.capacity;
    }

    @Override
    public boolean canUpgrade() {
        return BarnType.valueOf(type).ordinal() < BarnType.values().length - 1;
    }

    @Override
    public void upgrade() {
        BarnType current = BarnType.valueOf(type);
        this.type = BarnType.values()[current.ordinal() + 1].name();
        this.capacity = BarnType.valueOf(type).capacity;
    }
}
