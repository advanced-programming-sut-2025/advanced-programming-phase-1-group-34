package org.Group34.view.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.controller.GameController;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.naturalElements.PloughedLand;
import org.Group34.model.items.tools.Tool;
import org.Group34.model.map.Map;

public class ToolsGraphic {
    private SpriteBatch batch;
    private Player player;
    private GameController gameController;

    public ToolsGraphic(SpriteBatch batch, Player player, GameController gameController) {
        this.batch = batch;
        this.player = player;
        this.gameController = gameController;
    }

    public void update(int tileSize) {
        Tool tool = (Tool) player.getCurrentTool();
        if (tool != null) {
            Sprite sprite = new Sprite(tool.getTexture());
            sprite.setSize((float) (sprite.getWidth() * 0.5), (float) (sprite.getHeight() * 0.5));
            sprite.setPosition(player.getLocation()[0] * tileSize + 20, player.getLocation()[1] * tileSize + 10);
            sprite.draw(batch);
        }

        handleInput();
    }

    private void handleInput() {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 717 && x < 773 && y > 416 && y < 480) {
            gameController.toolUse("Left", player);
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 717 && x < 773 && y > 355 && y < 416) {
            gameController.toolUse("UpLeft", player);
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 717 && x < 773 && y > 480 && y < 541) {
            gameController.toolUse("DownLeft", player);
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 773 && x < 827 && y > 355 && y < 416) {
            gameController.toolUse("Up", player);
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 773 && x < 827 && y > 480 && y < 541) {
            gameController.toolUse("Down", player);
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 827 && x < 881 && y > 416 && y < 480) {
            gameController.toolUse("Right", player);
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 827 && x < 881 && y > 355 && y < 416) {
            gameController.toolUse("UpRight", player);
        } else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && x > 827 && x < 881 && y > 480 && y < 541) {
            gameController.toolUse("DownRight", player);
        }
    }
}