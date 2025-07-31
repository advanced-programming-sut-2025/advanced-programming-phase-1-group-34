package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.buildings.AnimalsBuilding;
import org.Group34.model.entities.buildings.Barn;
import org.Group34.model.entities.buildings.Coop;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.map.Space;
import java.util.ArrayList;
import java.util.List;

public class AnimalBuildingController {
    private final List<AnimalsBuilding> buildings = new ArrayList<>();

    public Result placeBuilding(BarnType type, int x, int y, Space space) {
        // Check if placement is valid
        if (!canPlaceBuilding(type, x, y, space)) {
            return new Result(false, "Cannot place building here");
        }

        // Create appropriate building
        AnimalsBuilding building;
        if (type.name().contains("COOP")) {
            building = new Coop(type);
        } else {
            building = new Barn(type);
        }

        // Place building on map
        for (int i = 0; i < type.getSizeX(); i++) {
            for (int j = 0; j < type.getSizeY(); j++) {
                space.placingEntity(x + i, y + j, building);
            }
        }

        buildings.add(building);
        return new Result(true, "Building placed successfully");
    }

    private boolean canPlaceBuilding(BarnType type, int x, int y, Space space) {
        // Check boundaries
        if (x < 0 || y < 0 || x + type.getSizeX() > 100 || y + type.getSizeY() > 100) {
            return false;
        }

        // Check if area is clear
        for (int i = 0; i < type.getSizeX(); i++) {
            for (int j = 0; j < type.getSizeY(); j++) {
                if (space.getEntityByLocation(x + i, y + j) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public AnimalsBuilding getBuildingAt(int x, int y) {
        for (AnimalsBuilding building : buildings) {
            BarnType type = BarnType.valueOf(building.type);
            if (x >= building.getX() && x < building.getX() + type.getSizeX() &&
                    y >= building.getY() && y < building.getY() + type.getSizeY()) {
                return building;
            }
        }
        return null;
    }

    public List<AnimalsBuilding> getBuildings() {
        return new ArrayList<>(buildings);
    }
}