package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.Player;
import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.items.Item;
import org.Group34.model.items.Recipe;
import org.Group34.model.items.crafting.Ingredient;
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

    public boolean canFeedAnimal(String name) {
        Animal animal = animals.get(name);

        if (animal == null || animal.isFed()) return false;

        animal.feed();
        animal.increaseFriendship(20);
        return true;
    }

    public Result petAnimal(String name){
        if (this.canPetAnimal(name)) {
            return new Result(true, "You have been pet animal\nYour friendship increased by 15");
        }
        else {
            return new Result(false, "No animal found");
        }
    }


    private boolean canPetAnimal(String name) {
        Animal animal = animals.get(name);

        if (animal == null) return false;

        animal.increaseFriendship(15);
        animal.setHasPet(true);
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
            player.decreaseEnergy(4);
        }

        animal.increaseFriendship(5);
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

    public boolean setOutside(String name) {
        Animal animal = animals.get(name);
        if (animal == null) return false;
        if (animal.setOutside()) {
            animal.feed();
            animal.increaseFriendship(8);
            return true;
        }
        else {
            return false;
        }
    }

    public void sellAnimal(String name) {
        Animal animal = animals.get(name);
        animals.remove(animal.getName());
    }

    public boolean cheatSetFriendship(String name, int amount) {
        Animal animal = animals.get(name);

        if (animal == null) return false;

        animal.setFriendship(amount);
        return true;
    }

    public Result listAnimals() {
        List<Animal> animals = this.getAllAnimals();
        StringBuilder result = new StringBuilder();
        for (Animal animal : animals) {
            result.append(animal.getName()).append(": ").append(animal.getFriendship()).append("\n");
        }

        return new Result(true, result.toString());
    }

    public Result shepherdAnimal(String name, int x, int y) {
        if (x > 100 || y > 100 || x < 0 || y < 0) {
            return new Result(false, "Invalid coordinates");
        }
        else if (this.getAnimal(name) == null) {
            return new Result(false, "No animal found");
        }
        else if (this.setOutside(name)){
            return new Result(true, "Operation successful.\nAnimal is now fed and your friendship was increased.");
        }
        else {
            return new Result(false, "This animal cannot be shepherd");
        }
    }

    public Result feedAnimal(String name, Player player){
        if (player.getAmountOfItem(Ingredient.MAHOGANY_SEED) >= 1 &&
                this.canFeedAnimal(name)) {
            player.removeFromInventory(Ingredient.MAHOGANY_SEED, 1);
            return new Result(true, "Animal have been fed and your friendship was increased!");
        }
        else {
            return new Result(false, "No animal found or animal is already fed.");
        }
    }

    public Result collectProductNow(String name, Player player) {
        Product product = this.collectProduct(name, player);
        if (product == null) {
            return new Result(false, "No product found");
        }
        else {
            player.addToInventory(product.getType(), 1);
            return new Result(true, "You have been collected one" + product.getType());
        }
    }

    public Result showProducts() {
        List<Animal> animals = this.getAllAnimals();

        StringBuilder result = new StringBuilder();
        for (Animal animal : animals) {
            if (animal.isCollected()) {
                result.append(animal.getName()).append(": ").append(animal.getAnimalType().getPossibleProducts()).append("\n");
            }
        }
        return new Result(true, result.toString());
    }

    public Result buyAnimalNow(String animal, String animalName, Player player) {
        AnimalType targetAnimal = AnimalType.valueOf(animal);
        if (targetAnimal == null) {
            return new Result(false, "No animal found");
        }

        if (player.getMoney() >= targetAnimal.getPrice()) {
            if (this.addAnimal(animalName, targetAnimal)) {
                return new Result(true, "You have bought a " + animal);
            }
            else {
                return new Result(false, "You have a animal with this type and name");
            }
        }
        else {
            return new Result(false, "You don't have enough money");
        }
    }

    public Result sellAnimalNow(String name, Player player) {
        Animal animal = this.getAnimal(name);
        if (this.getAllAnimals().contains(animal)) {
            player.addMoney((int)(((double) animal.getFriendship() / 1000) + 0.3) * animal.getAnimalType().getPrice());
            this.sellAnimal(name);
            return new Result(true, "You sold this animal\nCurrent Gold: " + player.getMoney());
        }
        else {
            return new Result(false, "No animal found");
        }
    }

    public Result cheatAnimalFriendShip(String name, int amount) {
        if (amount > 100 || amount < 0) return new Result(false, "Invalid amount!");
        if (this.cheatSetFriendship(name, amount)) {
            return new Result(true, "Friendship set to: " + amount);
        }
        else {
            return new Result(false, "No animal found");
        }
    }
}
