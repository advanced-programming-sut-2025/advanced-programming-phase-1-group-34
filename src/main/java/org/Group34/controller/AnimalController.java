package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.Player;
import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.items.Item;
import org.Group34.model.items.tools.MilkPail;
import org.Group34.model.items.tools.Shear;

import java.util.*;

public class AnimalController {
    private final Map<String, Animal> animals = new HashMap<>();

    public boolean addAnimal(String name, AnimalType type) {
        if (animals.containsKey(name)) return false;

        animals.put(name, new Animal(name, type));
        return true;
    }

    public Animal getAnimal(String name) {
        return animals.get(name);
    }

    public boolean feedAnimal(String name) {
        Animal animal = animals.get(name);

        if (animal == null || animal.isFed()) return false;

        animal.feed();
        return true;
    }

    public boolean petAnimal(String name) {
        Animal animal = animals.get(name);

        if (animal == null) return false;

        animal.increaseFriendship(15);
        return true;
    }

    public void passDay() {
        for (Animal animal : animals.values()) {
            animal.addDaysSinceLastProduce();
            if (!animal.isFed()) {
                animal.decreaseFriendship(5);
            }
        }
    }

    public Product collectProduct(String name, Player player) {
        Animal animal = animals.get(name);
        if (animal == null) return null;

        AnimalType type = animal.getAnimalType();
        Product product = animal.collectProduct();

        if (product != null && product.getType() == type) {
            if (requiresTool(type) && !hasTool(player, type)) {
                return null;
            }
        }

        return product;
    }

    public boolean hasTool(Player player, AnimalType type) {
        Item tool = player.getCurrentTool();

        if (type == AnimalType.COW || type == AnimalType.GOAT) {
            return tool instanceof MilkPail && ((MilkPail) tool).canMilk(type);
        } else if (type == AnimalType.SHEEP) {
            return tool instanceof Shear;
        }

        return true;
    }

    public boolean requiresTool(AnimalType type) {
        return type == AnimalType.COW || type == AnimalType.GOAT || type == AnimalType.SHEEP;
    }

    public List<Animal> getAllAnimals() {
        return new ArrayList<>(animals.values());
    }

    public List<Animal> getAnimalsByBarnType(BarnType barnType) {
        List<Animal> result = new ArrayList<>();
        for (Animal animal : animals.values()) {
            if (animal.getAnimalType().getRequiredBuilding() == barnType) {
                result.add(animal);
            }
        }
        return result;
    }

    public boolean setOutside(String name, boolean outside) {
        Animal animal = animals.get(name);
        if (animal == null) return false;
        animal.setOutside(outside);
        return true;
    }

    public boolean isAnimalFed(String name) {
        Animal animal = animals.get(name);
        return animal != null && animal.isFed();
    }

    public int getFriendship(String name) {
        Animal animal = animals.get(name);
        return animal != null ? animal.getFriendship() : -1;
    }
}
