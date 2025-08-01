package org.Group34.view.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.model.entities.Player;
import org.Group34.model.gameAssetManagers.TreeAssetManager;
import org.Group34.view.graphic.gameMenu.InventoryMenu;
import org.Group34.view.graphic.gameMenu.NPCMenu;
import org.Group34.view.graphic.gameMenu.SettingMenu;

public class GameMenuGraphic {
    private SpriteBatch batch;
    private Player player;

    public GameMenuGraphic(SpriteBatch batch, Player player) {
        this.batch = batch;
        this.player = player;
    }

    public void update(OrthographicCamera camera) {
        String menu = player.getCurrentGameMenu();

        if (menu != null) {
            if (menu.equals("inventory")) {
                InventoryMenu.draw(batch, player, camera);
            } else if (menu.equals("skill")) {
            } else if (menu.equals("social")) {
            } else if (menu.equals("map")) {
            } else if (menu.equals("npc")) {
                NPCMenu.draw(batch, player, camera);
            } else if (menu.equals("setting")) {
                SettingMenu.draw(batch, player, camera);
            }
        }
        else if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
        }
    }
}
