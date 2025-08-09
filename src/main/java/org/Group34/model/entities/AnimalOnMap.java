package org.Group34.model.entities;

import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.enums.animals.AnimalType;

public class AnimalOnMap implements Entity {
    private final Animal animal;
    private Texture texture;
    private int x, y;

    public AnimalOnMap(Animal animal, Texture texture, int x, int y) {
        this.animal = animal;
        this.texture = texture;
        this.x = x;
        this.y = y;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    public Animal getAnimal() {
        return animal;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}