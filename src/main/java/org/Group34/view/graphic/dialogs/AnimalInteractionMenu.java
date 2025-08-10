package org.Group34.view.graphic.dialogs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import org.Group34.controller.AnimalController;
import org.Group34.model.entities.Animal;
import org.Group34.model.entities.Player;
import org.Group34.model.enums.animals.AnimalType;
import org.Group34.model.enums.animals.Product;
import org.Group34.model.items.foods.Vegetable;
import org.Group34.model.map.Space;
import java.util.Random;

public class AnimalInteractionMenu implements Disposable {
    private final Animal animal;
    private final Skin skin;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final AnimalController animalController;
    private final Space currentSpace;
    private final Player player;
    private final Stage stage;
    private boolean active = true;
    private float menuX, menuY;
    private final float optionHeight = 30;
    private final float menuWidth = 150;
    private final BitmapFont font;
    private int selectedOption = 0;
    private boolean isShepherding = false;
    private float shepherdTimer = 0;
    private static final float SHEPHERD_DURATION = 3.0f;
    private int[] originalPosition;
    private int[] shepherdCenter;
    private Texture productTexture;
    private static final String[] OPTIONS = {"Information", "Pet", "Shepherd", "Feed", "Collect Product", "Sell", "Move"};

    // Icon display system
    public enum IconType {
        HEART, WOOL, CARROT, COINS, FOOTSTEPS, PRODUCT
    }
    private IconType activeIcon = null;
    private float iconTimer = 0;
    private static final float ICON_DURATION = 2.0f; // Duration to show the icon

    // Icon textures
    private Texture heartTexture;
    private Texture woolTexture;
    private Texture carrotTexture;
    private Texture coinsTexture;
    private Texture footstepsTexture;

    // Flag to indicate if we should continue rendering icons after menu closes
    private boolean continueRendering = false;

    public AnimalInteractionMenu(Animal animal, Skin skin, SpriteBatch batch, OrthographicCamera camera,
                                 AnimalController animalController, Space currentSpace, Player player, Stage stage) {
        this.animal = animal;
        this.skin = skin;
        this.batch = batch;
        this.camera = camera;
        this.animalController = animalController;
        this.currentSpace = currentSpace;
        this.player = player;
        this.stage = stage;

        // Position menu near the animal
        menuX = animal.getX() * 32; // TILE_SIZE is 32
        menuY = animal.getY() * 32 + 50; // Above the animal

        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(1.0f);

        // Load icon textures
        heartTexture = new Texture(Gdx.files.internal("gameMenu/Emojis046.png"));
        woolTexture = new Texture(Gdx.files.internal("animals/Wool.png"));
        carrotTexture = new Texture(Gdx.files.internal("crops/Carrot.png"));
        coinsTexture = new Texture(Gdx.files.internal("gameMenu/coin.png"));
        footstepsTexture = new Texture(Gdx.files.internal("animals/footstep.png"));

        // Debug message
        System.out.println("Animal interaction menu initialized at position: " + menuX + ", " + menuY);
    }

    public void render() {
        // Draw menu if active
        if (active) {
            batch.begin();

            // Draw semi-transparent background
            batch.setColor(0.8f, 0.8f, 0.8f, 0.9f);
            batch.draw(skin.getRegion("white"), menuX, menuY - OPTIONS.length * optionHeight, menuWidth, OPTIONS.length * optionHeight);

            // Draw menu border
            batch.setColor(0.2f, 0.2f, 0.2f, 1.0f);
            batch.draw(skin.getRegion("white"), menuX, menuY - OPTIONS.length * optionHeight, menuWidth, 2); // Top
            batch.draw(skin.getRegion("white"), menuX, menuY - OPTIONS.length * optionHeight, 2, OPTIONS.length * optionHeight); // Left
            batch.draw(skin.getRegion("white"), menuX, menuY - 2, menuWidth, 2); // Bottom
            batch.draw(skin.getRegion("white"), menuX + menuWidth - 2, menuY - OPTIONS.length * optionHeight, 2, OPTIONS.length * optionHeight); // Right

            // Draw options
            for (int i = 0; i < OPTIONS.length; i++) {
                float optionY = menuY - (i + 1) * optionHeight;

                // Highlight selected option
                if (i == selectedOption) {
                    batch.setColor(0.5f, 0.7f, 0.9f, 0.7f);
                    batch.draw(skin.getRegion("white"), menuX + 2, optionY - optionHeight + 2, menuWidth - 4, optionHeight - 4);
                }

                // Draw option text
                font.setColor(Color.BLACK);
                font.draw(batch, OPTIONS[i], menuX + 10, optionY - optionHeight / 2 + 5);
            }

            batch.end();
        }

        if (isShepherding) {
            shepherdTimer += Gdx.graphics.getDeltaTime();
            if (shepherdTimer < SHEPHERD_DURATION) {
                float angle = (shepherdTimer / SHEPHERD_DURATION) * 2 * (float)Math.PI;
                int radius = 3;
                int newX = shepherdCenter[0] + (int)(Math.cos(angle) * radius);
                int newY = shepherdCenter[1] + (int)(Math.sin(angle) * radius);

                currentSpace.placingEntity(animal.getX(), animal.getY(), null);

                animal.setX(newX);
                animal.setY(newY);

                currentSpace.placingEntity(newX, newY, animal);
            } else {
                isShepherding = false;

                currentSpace.placingEntity(animal.getX(), animal.getY(), null);

                animal.setX(originalPosition[0]);
                animal.setY(originalPosition[1]);

                currentSpace.placingEntity(originalPosition[0], originalPosition[1], animal);
            }
        }

        // Draw active icon if any (even if menu is not active)
        if (activeIcon != null && iconTimer > 0) {
            batch.begin();
            // Calculate position above the animal
            float iconX = animal.getX() * 32 + 16; // Center of the tile
            float iconY = animal.getY() * 32 + 40; // Above the animal
            // Make the icon float up and fade out
            float floatAmount = (ICON_DURATION - iconTimer) * 10;
            iconY += floatAmount;
            // Fade out effect
            float alpha = Math.min(1.0f, iconTimer);
            batch.setColor(1, 1, 1, alpha);

            // Draw the appropriate icon
            switch (activeIcon) {
                case HEART:
                    batch.draw(heartTexture, iconX - 16, iconY - 16, 32, 32);
                    break;
                case WOOL:
                    batch.draw(woolTexture, iconX - 16, iconY - 16, 32, 32);
                    break;
                case CARROT:
                    batch.draw(carrotTexture, iconX - 16, iconY - 16, 32, 32);
                    break;
                case COINS:
                    batch.draw(coinsTexture, iconX - 16, iconY - 16, 32, 32);
                    break;
                case FOOTSTEPS:
                    batch.draw(footstepsTexture, iconX - 16, iconY - 16, 32, 32);
                    break;
                case PRODUCT:
                    if (productTexture != null) {
                        batch.draw(productTexture, iconX - 16, iconY - 16, 32, 32);
                    }
                    break;
            }
            batch.setColor(1, 1, 1, 1); // Reset color
            batch.end();

            // Update the timer
            iconTimer -= Gdx.graphics.getDeltaTime();
            if (iconTimer <= 0) {
                activeIcon = null;
            }
        }
    }

    public void handleInput() {
        if (!active) return;

        // Handle up/down to select option
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectedOption = (selectedOption - 1 + OPTIONS.length) % OPTIONS.length;
            System.out.println("Selected option: " + OPTIONS[selectedOption]);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedOption = (selectedOption + 1) % OPTIONS.length;
            System.out.println("Selected option: " + OPTIONS[selectedOption]);
        }

        // Handle enter to activate
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            System.out.println("Activating option: " + OPTIONS[selectedOption]);
            activateOption();
        }

        // Handle escape to close
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            System.out.println("Closing menu");
            active = false;
        }
    }

    private void activateOption() {
        switch (OPTIONS[selectedOption]) {
            case "Information":
                showInformation();
                return;
            case "Pet":
                petAnimal();
                break;
            case "Shepherd":
                shepherd();
                return;
            case "Feed":
                feedAnimal();
                break;
            case "Collect Product":
                collectProduct();
                return;
            case "Sell":
                sellAnimal();
                break;
            case "Move":
                moveAnimal();
                break;
        }

        active = false;
        continueRendering = true;
    }

    private void showInformation() {
        // For information, we'll keep the dialog since it shows detailed information
        com.badlogic.gdx.scenes.scene2d.ui.Dialog infoDialog = new com.badlogic.gdx.scenes.scene2d.ui.Dialog("Animal Information", skin);
        infoDialog.text("Name: " + animal.getName() +
                "\nType: " + animal.getAnimalType().getName() +
                "\nFriendship: " + animal.getFriendship() +
                "\nFed: " + (animal.isFed() ? "Yes" : "No") +
                "\nOutside: " + (animal.isOutside() ? "Yes" : "No"));
        infoDialog.button("OK");
        infoDialog.show(stage);

        active = false;
    }

    private void petAnimal() {
        animal.increaseFriendship(10);
        // Show heart icon
        activeIcon = IconType.HEART;
        iconTimer = ICON_DURATION;
    }

    private void shepherd() {
        originalPosition = new int[]{animal.getX(), animal.getY()};

        currentSpace.placingEntity(animal.getX(), animal.getY(), null);

        int[] playerPos = player.getLocation();
        shepherdCenter = new int[]{playerPos[0], playerPos[1]};
        isShepherding = true;
        shepherdTimer = 0;
        activeIcon = IconType.FOOTSTEPS;
        iconTimer = ICON_DURATION;
    }

    private void feedAnimal() {
        if (true) {
            player.removeFromInventory(Vegetable.CARROT, 1);
            animal.feed(); // Using the feed method from Animal class
            // Show carrot icon
            activeIcon = IconType.CARROT;
            iconTimer = ICON_DURATION;
        } else {
            // Show information dialog for this case since it's an error condition
            Dialog feedDialog = new com.badlogic.gdx.scenes.scene2d.ui.Dialog("", skin);
            feedDialog.text("You don't have any carrots to feed the animal!");
            feedDialog.button("OK");
            feedDialog.show(stage);
        }
    }

    private void sellAnimal() {
        // Calculate sell price based on animal type and friendship
        int basePrice = animal.getAnimalType().getPrice();
        int friendshipBonus = animal.getFriendship() / 20; // 5 gold per 20 friendship points
        int sellPrice = basePrice / 2 + friendshipBonus; // Half base price plus friendship bonus
        player.addMoney(sellPrice);
        // Remove the animal from the controller and space
        animalController.sellAnimal(animal.getName()); // Using the existing sellAnimal method
        currentSpace.placingEntity(animal.getX(), animal.getY(), null);
        // Show coins icon
        activeIcon = IconType.COINS;
        iconTimer = ICON_DURATION;
    }

    private void moveAnimal() {
        Random random = new Random();
        int currentX = animal.getX();
        int currentY = animal.getY();
        boolean moved = false;

        currentSpace.placingEntity(currentX, currentY, null);

        // Try up to 20 times to find a valid position
        for (int i = 0; i < 20; i++) {
            // Generate random position within 5 tiles (Euclidean distance)
            int newX = currentX + random.nextInt(11) - 5; // -5 to 5
            int newY = currentY + random.nextInt(11) - 5; // -5 to 5

            // Check if position is valid (within bounds and empty)
            if (newX >= 0 && newX < currentSpace.width() &&
                    newY >= 0 && newY < currentSpace.height() &&
                    currentSpace.getEntityByLocation(newX, newY) == null) {
                // Move the animal
                animal.setX(newX);
                animal.setY(newY);
                currentSpace.placingEntity(newX, newY, animal); // Set new position
                moved = true;
                break;
            }
        }

        if (!moved) {
            animal.setX(currentX);
            animal.setY(currentY);
            currentSpace.placingEntity(currentX, currentY, animal);
        }

        // Show footsteps icon
        activeIcon = IconType.FOOTSTEPS;
        iconTimer = ICON_DURATION;
    }

    private void collectProduct() {
        if (animal.isFed()) {
            Product product = animal.collectProduct();
            if (product != null) {
                player.addToInventory(product, 1);
                productTexture = product.getTexture();
                activeIcon = IconType.PRODUCT;
                iconTimer = ICON_DURATION;
                Dialog dialog = new Dialog("", skin);
                dialog.text("Collected " + product.name() + "!");
                dialog.button("OK");
                dialog.show(stage);

                active = false;
            } else {
                Dialog dialog = new Dialog("", skin);
                dialog.text("This animal has no product to collect right now.");
                dialog.button("OK");
                dialog.show(stage);

                active = false;
            }
        } else {
            Dialog dialog = new Dialog("", skin);
            dialog.text("This animal is not ready to produce. Make sure it's fed and healthy.");
            dialog.button("OK");
            dialog.show(stage);

            active = false;
        }
    }

    public boolean isActive() {
        return active || (continueRendering && activeIcon != null && iconTimer > 0);
    }

    public IconType getActiveIcon() {
        return activeIcon;
    }

    public float getIconTimer() {
        return iconTimer;
    }

    @Override
    public void dispose() {
        font.dispose();
        heartTexture.dispose();
        woolTexture.dispose();
        carrotTexture.dispose();
        coinsTexture.dispose();
        footstepsTexture.dispose();
        if (productTexture != null) {
            productTexture.dispose();
        }
    }
}