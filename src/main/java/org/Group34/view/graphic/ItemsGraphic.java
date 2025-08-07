package org.Group34.view.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import org.Group34.controller.GameController;
import org.Group34.model.entities.Player;
import org.Group34.model.items.Fertilizer;
import org.Group34.model.items.Item;
import org.Group34.model.items.PlantingSource;
import org.Group34.model.items.tools.Tool;

public class ItemsGraphic {
    private SpriteBatch batch;
    private Player player;
    private GameController gameController;
    private float time = 10f;

    public ItemsGraphic(SpriteBatch batch, Player player, GameController gameController) {
        this.batch = batch;
        this.player = player;
        this.gameController = gameController;
    }

    public void update(int tileSize) {
        Tool tool = (Tool) player.getCurrentTool();
        if (tool != null) {
            Sprite sprite = new Sprite(tool.getTexture());
            sprite.setSize((float) (sprite.getWidth() * 0.5), (float) (sprite.getHeight() * 0.5));

            time += Gdx.graphics.getDeltaTime();
            if (time < 1f) {
                float offsetY = MathUtils.sin(time * 5f) * 10f;
                float currentY = player.getLocation()[1] * tileSize + 10 + offsetY;
                sprite.setPosition(player.getLocation()[0] * tileSize + 20, currentY);
                sprite.draw(batch);
            } else {
                sprite.setPosition(player.getLocation()[0] * tileSize + 20, player.getLocation()[1] * tileSize + 10);
                sprite.draw(batch);
            }
        }

        Item item = player.getCurrentItem();
        if (item != null) {
            Sprite sprite = new Sprite(item.getTexture());
            sprite.setSize(18, 18);

            time += Gdx.graphics.getDeltaTime();
            if (time < 1f) {
                float offsetY = MathUtils.sin(time * 5f) * 10f;
                float currentY = player.getLocation()[1] * tileSize + 10 + offsetY;
                sprite.setPosition(player.getLocation()[0] * tileSize + 20, currentY);
                sprite.draw(batch);
            } else {
                sprite.setPosition(player.getLocation()[0] * tileSize + 20, player.getLocation()[1] * tileSize + 10);
                sprite.draw(batch);
            }
        }

        handleInput();
    }

    private void handleInput() {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        Item item = player.getCurrentItem();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 717 && x < 773 && y > 416 && y < 480) {
            if (gameController.toolUse("Left", player).success()) {
                time = 0f;
            }
            if (item != null) {
                handleFarming("Left", item);
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 717 && x < 773 && y > 355 && y < 416) {
            if (gameController.toolUse("UpLeft", player).success()) {
                time = 0f;
            }
            if (item != null) {
                handleFarming("UpLeft", item);
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 717 && x < 773 && y > 480 && y < 541) {
            if (gameController.toolUse("DownLeft", player).success()) {
                time = 0f;
            }
            if (item != null) {
                handleFarming("DownLeft", item);
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 773 && x < 827 && y > 355 && y < 416) {
            if (gameController.toolUse("Up", player).success()) {
                time = 0f;
            }
            if (item != null) {
                handleFarming("Up", item);
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 773 && x < 827 && y > 480 && y < 541) {
            if (gameController.toolUse("Down", player).success()) {
                time = 0f;
            }
            if (item != null) {
                handleFarming("Down", item);
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 827 && x < 881 && y > 416 && y < 480) {
            if (gameController.toolUse("Right", player).success()) {
                time = 0f;
            }
            if (item != null) {
                handleFarming("Right", item);
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 827 && x < 881 && y > 355 && y < 416) {
            if (gameController.toolUse("UpRight", player).success()) {
                time = 0f;
            }
            if (item != null) {
                handleFarming("UpRight", item);
            }
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 827 && x < 881 && y > 480 && y < 541) {
            if (gameController.toolUse("DownRight", player).success()) {
                time = 0f;
            }
            if (item != null) {
                handleFarming("DownRight", item);
            }
        }
    }

    private void handleFarming(String direction, Item item) {
        if (item instanceof PlantingSource plantingSource) {
            if (gameController.plant(plantingSource.getName(), direction, player).success()) {
                time = 0f;
            }
        } else if (item instanceof Fertilizer fertilizer) {
            if (gameController.fertilize(fertilizer.getName(), direction, player).success()) {
                time = 0f;
            }
        }
    }
}