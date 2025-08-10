package org.Group34.model.enums.animals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.Group34.model.gameAssetManagers.AnimalAssetManager;
import org.Group34.model.items.Item;

public enum Product implements Item {
    // Chicken
    EGG(AnimalType.CHICKEN, 50, 0),
    LARGE_EGG(AnimalType.CHICKEN, 95, 100),

    // Duck
    DUCK_EGG(AnimalType.DUCK, 95, 0),
    DUCK_FEATHER(AnimalType.DUCK, 250, 100),

    // Rabbit
    RABBIT_WOOL(AnimalType.RABBIT, 340, 0),
    RABBIT_FOOT(AnimalType.RABBIT, 565, 100),

    // Dinosaur
    DINOSAUR_EGG(AnimalType.DINOSAUR, 350, 0),

    // Cow
    MILK(AnimalType.COW, 125, 0),
    LARGE_MILK(AnimalType.COW, 190, 100),

    // Goat
    GOAT_MILK(AnimalType.GOAT, 225, 0),
    LARGE_GOAT_MILK(AnimalType.GOAT, 345, 100),

    // Sheep
    SHEEP_WOOL(AnimalType.SHEEP, 340, 0),

    // Pig
    TRUFFLE(AnimalType.PIG, 625, 0);

    private final AnimalType type;
    private final int basePrice;
    private final int requiredFriendship;

    Product(AnimalType type, int basePrice, int requiredFriendship) {
        this.type = type;
        this.basePrice = basePrice;
        this.requiredFriendship = requiredFriendship;
    }
    
    

    public AnimalType getType() {
        return type;
    }

    public int getPrice() {
        return basePrice;
    }

    public int getRequiredFriendship() {
        return requiredFriendship;
    }

    @Override
    public String getName() {
        switch (this) {
            case EGG:
                return "Egg";
            case LARGE_EGG:
                return "Large Egg";
            case DUCK_EGG:
                return "Duck Egg";
            case DUCK_FEATHER:
                return "Duck Feather";
            case RABBIT_WOOL:
                return "Rabbit Wool";
            case RABBIT_FOOT:
                return "Rabbit Foot";
            case DINOSAUR_EGG:
                return "Dinosaur Egg";
            case MILK:
                return "Milk";
            case LARGE_MILK:
                return "Large Milk";
            case GOAT_MILK:
                return "Goat Milk";
            case LARGE_GOAT_MILK:
                return "Large Goat Milk";
            case SHEEP_WOOL:
                return "Sheep Wool";
            case TRUFFLE:
                return "Truffle";
            default:
                return "Meow";
        }
    }

    public void setPrice(int price) {

    }

    @Override
    public Texture getTexture() {
        switch (this) {
            case EGG:
                return AnimalAssetManager.egg;
            case LARGE_EGG:
                return AnimalAssetManager.largeEgg;
            case DUCK_EGG:
                return AnimalAssetManager.duckEgg;
            case DUCK_FEATHER:
                return AnimalAssetManager.duckFeather;
            case RABBIT_WOOL:
                return AnimalAssetManager.rabbitWool;
            case RABBIT_FOOT:
                return AnimalAssetManager.rabbitFoot;
            case DINOSAUR_EGG:
                return AnimalAssetManager.dinosaurEgg;
            case MILK:
                return AnimalAssetManager.milk;
            case LARGE_MILK:
                return AnimalAssetManager.largeMilk;
            case GOAT_MILK:
                return AnimalAssetManager.goatMilk;
            case LARGE_GOAT_MILK:
                return AnimalAssetManager.largeGoatMilk;
            case SHEEP_WOOL:
                return AnimalAssetManager.sheepWool;
            case TRUFFLE:
                return AnimalAssetManager.truffle;
            default:
                return new Texture(Gdx.files.internal("gameMenu/coin.png"));
        }
    }
}