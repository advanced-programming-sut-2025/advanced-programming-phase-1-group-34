package org.Group34.controller;

import org.Group34.model.Result;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.buildings.AnimalsBuilding;
import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.BarnType;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.items.Item;
import org.Group34.model.items.Recipe;
import org.Group34.model.items.crafting.Ingredient;
import org.Group34.model.items.tools.MilkPail;
import org.Group34.model.items.tools.Shear;
import org.Group34.model.map.Space;

import java.util.*;

public class AnimalController {
    private final Map<String, Animal> animals = new HashMap<>();
    private int barn = 0;
    private final AnimalBuildingController buildingController;
    private final Space space;

    public AnimalController(AnimalBuildingController buildingController, Space space) {
        this.buildingController = buildingController;
        this.space = space;
    }


    public boolean addAnimal(String name, AnimalType type) {
        if (animals.containsKey(name)) return false;

        Animal animal = new Animal(name, type);
        animals.put(name, animal);

        // Find appropriate building
        BarnType requiredType = type.getRequiredBuilding();
        for (AnimalsBuilding building : buildingController.getBuildings()) {
            if (BarnType.valueOf(building.type) == requiredType &&
                    building.getAnimalCount() < building.capacity) {

                // Add to building
                building.addAnimal(animal);

                // Place animal in building (at building's position)
                animal.setX(building.getX());
                animal.setY(building.getY());
                space.placingEntity(animal.getX(), animal.getY(), animal);

                return true;
            }
        }

        // No suitable building found
        animals.remove(name);
        return false;
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

    public int getBarn() {
        return barn;
    }

    public void setBarn(int barn) {
        this.barn = barn;
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

        if (requiresTool(type) && !hasTool(player, type)) {
            System.out.println("You don't have tool for this animal");
            return null;
        }

        if (product != null) {
            if (requiresTool(type) && !hasTool(player, type)) {
                System.out.println("You don't have tool for this animal");
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

        Animal animal = animals.get(name);
        if (animal == null) {
            return new Result(false, "No animal found");
        }

        // Check if animal can be outside
        if (!animal.setOutside()) {
            return new Result(false, "This animal cannot be outside");
        }

        // Remove from current building if inside
        for (AnimalsBuilding building : buildingController.getBuildings()) {
            if (building.getAnimals().contains(animal)) {
                building.getAnimals().remove(animal);
                break;
            }
        }

        // Update position on map
        space.placingEntity(animal.getX(), animal.getY(), null); // Remove from old position
        animal.setX(x);
        animal.setY(y);
        space.placingEntity(x, y, animal); // Place at new position

        // Feed and increase friendship
        animal.feed();
        animal.increaseFriendship(8);

        return new Result(true, "Animal is now outside and fed");
    }

    public Result feedAnimal(String name, Player player){
        if (!this.canFeedAnimal(name))
            return new Result(false, "Animal is already fed.");
        else if (player.getAmountOfItem(Ingredient.SHEEP_FABRIC) >= 1) {
            player.removeFromInventory(Ingredient.SHEEP_FABRIC, 1);
            return new Result(true, "Animal have been fed and your friendship was increased!");
        }
        else {
            return new Result(false, "Not enough resources.");
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
                player.addMoney(-targetAnimal.getPrice());
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
