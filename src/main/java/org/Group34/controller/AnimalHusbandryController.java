package org.Group34.controller;

import org.Group34.model.entities.Animal;
import org.Group34.model.entities.buildings.*;
import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.enums.animals.CoopType;

import java.util.ArrayList;
import java.util.List;

public class AnimalHusbandryController {
    private final List<Coop> coops = new ArrayList<>();
    private final List<Barn> barns = new ArrayList<>();

    public String buildStructure(String type, int x, int y) {
        if (!validateLocation(x, y)) return "Invalid location";

        switch (type.toUpperCase()) {
            case "COOP" -> coops.add(new Coop(CoopType.BASIC));
            case "BARN" -> barns.add(new Barn(BarnType.BASIC));
            default -> { return "Invalid building type"; }
        }
        return type + " built successfully";
    }

    public String buyAnimal(AnimalType type, String name) {
        AnimalsBuilding building = findAvailableBuilding(type);
        if (building == null) return "No available housing";

        Animal animal = new Animal(name, type);
        return building.addAnimal(animal) ? "Animal purchased" : "Failed to purchase";
    }

    public String feedAnimal(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null) return "Animal not found";
        if (animal.isFed()) return "Already fed today";

        animal.feed();
        return name + " has been fed";
    }

    public String petAnimal(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null) return "Animal not found";

        if (animal.getFriendship() >= 1000) return name + " already has max friendship";
        animal.increaseFriendship(100);
        return name + " has been petted (friendship: " + animal.getFriendship() + ")";
    }

    public String collectProduct(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null) return "Animal not found";

        String product = String.valueOf(animal.collectProduct());
        return product != null ? "Collected: " + product : "No product to collect";
    }

    public List<String> listAllAnimals() {
        List<String> result = new ArrayList<>();
        coops.forEach(coop -> coop.getAnimals().forEach(animal ->
                result.add(animal.getName() + " (" + animal.getType().name() + ") - Friendship: " + animal.getFriendship())
        ));
        barns.forEach(barn -> barn.getAnimals().forEach(animal ->
                result.add(animal.getName() + " (" + animal.getType().name() + ") - Friendship: " + animal.getFriendship())
        ));
        return result;
    }

    // ---------- Helper Methods ----------

    private AnimalsBuilding findAvailableBuilding(AnimalType type) {
        return switch (type.requiredBuilding) {
            case "Coop" -> coops.stream()
                    .filter(c -> c.getAnimals().size() < c.capacity)
                    .findFirst()
                    .orElse(null);
            case "Barn" -> barns.stream()
                    .filter(b -> b.getAnimals().size() < b.capacity)
                    .findFirst()
                    .orElse(null);
            default -> null;
        };
    }


    private Animal findAnimalByName(String name) {
        for (Coop coop : coops) {
            for (Animal animal : coop.getAnimals()) {
                if (animal.getName().equalsIgnoreCase(name)) return animal;
            }
        }
        for (Barn barn : barns) {
            for (Animal animal : barn.getAnimals()) {
                if (animal.getName().equalsIgnoreCase(name)) return animal;
            }
        }
        return null;
    }

    private boolean validateLocation(int x, int y) {
        return x >= 0 && y >= 0;
    }
}
