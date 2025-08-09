package org.Group34.model.entities.buildings;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Animal;
import org.Group34.model.gameAssetManagers.AnimalAssetManager;

import java.util.ArrayList;
import java.util.List;

public class AnimalsBuilding implements Entity {
    public int capacity = 10;
    protected final List<Animal> animals = new ArrayList<>();
    public String type;
    protected int x;
    protected int y;

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

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }


    @Override
    public Texture getTexture() {
        // Return appropriate texture based on building type
        if (type != null) {
            if (type.contains("COOP")) {
                return AnimalAssetManager.coop;
            } else {
                return AnimalAssetManager.barn;
            }
        }
        return AnimalAssetManager.barn; // Default texture
    }
}
