package org.Group34.view.graphic;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.controller.GameController;
import org.Group34.model.entities.NPCOnMap;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.npcs.NPC;
import org.Group34.view.graphic.gameMenu.*;
import org.Group34.view.graphic.mapScreen.EnvironmentManager;

import java.util.List;
import java.util.stream.Collectors;

public class GameMenuGraphic {
    private SpriteBatch batch;
    private Player player;
    private GameController gameController;

    public GameMenuGraphic(SpriteBatch batch, Player player, GameController gameController) {
        this.batch = batch;
        this.player = player;
        this.gameController = gameController;
    }

    public void update(OrthographicCamera camera, EnvironmentManager environmentManager) {
        String menu = player.getCurrentGameMenu();

        if (menu != null) {
            if (menu.equals("inventory")) {
                InventoryMenu.draw(batch, player, camera, gameController);
            } else if (menu.equals("skill")) {
                SkillMenu.draw(batch, player, camera);
            } else if (menu.equals("social")) {
                SocialMenu.draw(batch, player, camera, gameController);
            } else if (menu.equals("map")) {
                MapMenu.draw(batch, player, camera);
            } else if (menu.equals("npc")) {
                List<NPC> npcs = environmentManager.getNpcManager().getNpcOnMaps().stream()
                        .map(NPCOnMap::getNpc)
                        .collect(Collectors.toList());

                // Set the NPCs in the menu
                NPCMenu.setNPCs(npcs);
                NPCMenu.draw(batch, player, camera);
            } else if (menu.equals("setting")) {
                SettingMenu.draw(batch, player, camera);
            } else if (menu.equals("animal")) {
                AnimalMenu.draw(batch, player, camera);
            } else if (menu.equals("blacksmith")) {
                BlacksmithMenu.draw(batch, player, camera, gameController);
            } else if (menu.equals("salePlace")) {
                SalePlaceMenu.draw(batch, player, camera, gameController);
            } else if (menu.equals("crafting")) {
                CraftingMenu.draw(batch, player, camera, gameController);
            } else if (menu.equals("cooking")) {
                CookingMenu.draw(batch, player, camera, gameController);
            } else if (menu.equals("fridge")) {
                FridgeMenu.draw(batch, player, camera, gameController);
            }
        }
    }
}
