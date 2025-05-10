package org.Group34.model.entities.buildings;

import org.Group34.model.enums.animals.CoopType;

public class Coop extends AnimalsBuilding {
    public Coop(CoopType type) {
        this.type = type.name();
        this.capacity = type.capacity;
    }

    @Override
    public boolean canUpgrade() {
        return CoopType.valueOf(type).ordinal() < CoopType.values().length - 1;
    }

    @Override
    public void upgrade() {
        CoopType current = CoopType.valueOf(type);
        this.type = CoopType.values()[current.ordinal() + 1].name();
        this.capacity = CoopType.valueOf(type).capacity;
    }
}
