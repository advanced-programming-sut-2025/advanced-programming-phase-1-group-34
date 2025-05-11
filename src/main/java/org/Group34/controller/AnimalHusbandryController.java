package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.*;
import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.enums.animals.CoopType;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.items.tools.MilkPail;

import java.util.ArrayList;
import java.util.List;

public class AnimalHusbandryController {
    private final List<Coop> coops = new ArrayList<>();
    private final List<Barn> barns = new ArrayList<>();

    public Result buildStructure(String type, int x, int y) {
        if (!validateLocation(x, y)) return new Result(false, "Invalid location");

        switch (type.toUpperCase()) {
            case "COOP" -> coops.add(new Coop(CoopType.BASIC));
            case "BARN" -> barns.add(new Barn(BarnType.BASIC));
            default -> { return new Result(false, "Invalid building type"); }
        }
        return new Result(true, type + " built successfully");
    }

    public Result buyAnimal(AnimalType type, String name) {
        AnimalsBuilding building = findAvailableBuilding(type);
        if (building == null)
            return new Result(false, "No available housing");

        Animal animal = new Animal(name, type);
        boolean success = building.addAnimal(animal);
        return new Result(success, success ? "Animal purchased" : "Failed to purchase");
    }

    public Result feedAnimal(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null)
            return new Result(false, "Animal not found");

        if (animal.isFed())
            return new Result(false, "Already fed today");

        animal.feed();
        return new Result(true, name + " has been fed");
    }

    public Result petAnimal(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null)
            return new Result(false, "Animal not found");

        if (animal.getFriendship() >= 1000)
            return new Result(false, name + " already has max friendship");

        animal.increaseFriendship(100);
        return new Result(true, name + " has been petted (friendship: " + animal.getFriendship() + ")");
    }

    public Result collectProduct(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null)
            return new Result(false, "Animal not found");

        String product = String.valueOf(animal.collectProduct());
        return (product != null && !product.equals("null")) ?
                new Result(true, "Collected: " + product) :
                new Result(false, "No product to collect");
    }

    public Result listAllAnimals() {
        List<String> result = createAnimalsList();
        if (result.isEmpty())
            return new Result(false, "No animals found");
        return new Result(true, String.join("\n", result));
    }

    public Result useMilkPail(Player player, int directionX, int directionY) {
        Entity entity = player.getCurrentSpace().getEntityByLocation(directionX, directionY);

        Animal animal = (Animal) entity;
        MilkPail milkPail = (MilkPail) player.getCurrentTool();

        Product product = animal.collectProduct();
        if (product != null) {
            player.addToInventory(product, 1);
            return new Result(true, "You milked the " + animal.getName() + " and got " + product.getName() + ".");
        }
        else {
            return new Result(false, "This animal has no product to collect now.");
        }
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

    private List<String> createAnimalsList() {
        List<String> result = new ArrayList<>();
        coops.forEach(coop -> coop.getAnimals().forEach(animal ->
                result.add(animal.getName() + " (" + animal.getAnimalType().name() + ") - Friendship: " + animal.getFriendship())
        ));
        barns.forEach(barn -> barn.getAnimals().forEach(animal ->
                result.add(animal.getName() + " (" + animal.getAnimalType().name() + ") - Friendship: " + animal.getFriendship())
        ));
        return result;
    }
}
