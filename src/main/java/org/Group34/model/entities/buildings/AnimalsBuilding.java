package org.Group34.model.entities.buildings;

import org.Group34.model.entities.Entity;
import org.Group34.model.entities.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalsBuilding implements Entity {
    public int capacity = 10;
    protected final List<Animal> animals = new ArrayList<>();
    protected String type;

    public boolean addAnimal(Animal animal) {
        if (animals.size() < capacity) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public int getAnimalCount() {
        return animals.size();
    }

    public boolean canUpgrade() {
        return false;
    }

    public void upgrade() {
        //TODO Implement upgrade logic if needed
    }
}
