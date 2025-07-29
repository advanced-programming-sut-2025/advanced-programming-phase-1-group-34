package org.Group34.view.graphic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.model.entities.Player;
import org.Group34.model.items.tools.Tool;

public class ToolsGraphic {
    private SpriteBatch batch;
    private Player player;

    public ToolsGraphic(SpriteBatch batch, Player player) {
        this.batch = batch;
        this.player = player;
    }

    public void update(int tileSize) {
        Tool tool = (Tool) player.getCurrentTool();
        if (tool != null) {
            Sprite sprite = new Sprite(tool.getTexture());
            sprite.setSize((float) (sprite.getWidth() * 0.5), (float) (sprite.getHeight() * 0.5));
            sprite.setPosition(player.getLocation()[0] * tileSize + 20, player.getLocation()[1] * tileSize + 10);
            sprite.draw(batch);
        }
    }
}