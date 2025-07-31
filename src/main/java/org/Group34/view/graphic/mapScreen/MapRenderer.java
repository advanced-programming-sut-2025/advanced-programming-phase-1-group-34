package org.Group34.view.graphic.mapScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.Group34.model.entities.NPCOnMap;
import org.Group34.model.entities.buildings.GreenHouse;
import org.Group34.model.gameAssetManagers.BuildingsAssetManager;
import org.Group34.model.map.Map;
import org.Group34.model.map.Space;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;

import java.util.Random;

public class MapRenderer {
    private final Map gameMap;
    private final Player player;
    private final int tileSize;
    private final int viewportWidth;
    private final int viewportHeight;
    private final Texture[] grassTextures;
    private final int[][] grassPattern;
    private final boolean[][] snowPattern;

    public MapRenderer(Map gameMap, Player player, int tileSize, int viewportWidth, int viewportHeight) {
        this.gameMap = gameMap;
        this.player = player;
        this.tileSize = tileSize;
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;

        // Load grass textures
        grassTextures = new Texture[6];
        for (int i = 0; i < 6; i++) {
            grassTextures[i] = new Texture(Gdx.files.internal("tiles/grass_" + i + ".png"));
        }

        // Generate grass pattern
        int mapWidth = gameMap.getCurrentPlayerFarm(player).width();
        int mapHeight = gameMap.getCurrentPlayerFarm(player).height();
        grassPattern = new int[mapWidth][mapHeight];
        snowPattern = new boolean[mapWidth][mapHeight];

        Random random = new Random();
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                grassPattern[x][y] = random.nextInt(6);
                // Precompute snow pattern (30% chance of snow)
                snowPattern[x][y] = random.nextFloat() < 0.3f;
            }
        }
    }

    public void render(SpriteBatch batch, OrthographicCamera camera, EnvironmentManager environmentManager) {
        Space currentSpace = gameMap.getCurrentPlayerFarm(player);
        // Calculate visible area based on camera position
        int startX = (int)(camera.position.x / tileSize) - viewportWidth / 2;
        int startY = (int)(camera.position.y / tileSize) - viewportHeight / 2;
        startX = Math.max(0, startX);
        startY = Math.max(0, startY);
        startX = Math.min(currentSpace.width() - viewportWidth, startX);
        startY = Math.min(currentSpace.height() - viewportHeight, startY);

        // Apply environment tint
        batch.setColor(environmentManager.getEnvironmentTint());

        // Render grass with seasonal variations
        for (int x = startX; x < startX + viewportWidth; x++) {
            for (int y = startY; y < startY + viewportHeight; y++) {
                renderGrassTile(batch, x, y, environmentManager);
            }
        }

        // Get player position
        int[] playerPos = player.getLocation();

        // Render NPCs
        for (NPCOnMap npcOnMap : environmentManager.getNpcManager().getNpcOnMaps()) {
            // Get NPC position
            int npcX = npcOnMap.getX();
            int npcY = npcOnMap.getY();

            // Only render NPCs in visible area
            if (npcX >= startX && npcX < startX + viewportWidth &&
                    npcY >= startY && npcY < startY + viewportHeight) {

                // Check if player is on this tile
                boolean playerOnThisTile = (npcX == playerPos[0] && npcY == playerPos[1]);

                // Save original color
                Color originalColor = new Color(batch.getColor());

                // If player is on this tile, add some transparency
                if (playerOnThisTile) {
                    batch.setColor(1, 1, 1, 0.7f);
                }

                batch.draw(npcOnMap.getTexture(), npcX * tileSize, npcY * tileSize, tileSize, tileSize);

                // Restore original color
                batch.setColor(originalColor);
            }
        }

        // Render entities
        for (int x = startX; x < startX + viewportWidth; x++) {
            for (int y = startY; y < startY + viewportHeight; y++) {
                Entity entity = currentSpace.getEntityByLocation(x, y);
                if (entity != null) {
                    // Check if player is on this tile
                    boolean playerOnThisTile = (x == playerPos[0] && y == playerPos[1]);
                    if (entity instanceof GreenHouse) {
                        // Get the greenhouse at this specific location
                        GreenHouse greenhouse = environmentManager.getGameController().greenhouse;
                        Texture texture = greenhouse.isRepaired() ?
                                BuildingsAssetManager.greenhouse_repaired :
                                BuildingsAssetManager.greenhouse;
                        // Save original color
                        Color originalColor = new Color(batch.getColor());
                        // If player is on this tile, draw with partial transparency
                        if (playerOnThisTile) {
                            batch.setColor(1, 1, 1, 0.7f); // 70% opacity so greenhouse is still visible
                        }
                        batch.draw(texture, x * tileSize, y * tileSize, tileSize, tileSize);
                        // Restore original color
                        batch.setColor(originalColor);
                    }
                    else if (entity.getTexture() != null) {
                        batch.draw(entity.getTexture(), x * tileSize, y * tileSize, tileSize, tileSize);
                    }
                }
            }
        }

        // Reset color
        batch.setColor(Color.WHITE);
    }

    private void renderGrassTile(SpriteBatch batch, int x, int y, EnvironmentManager environmentManager) {
        int grassType = grassPattern[x][y];
        String season = environmentManager.getCurrentSeason();

        if (season.equalsIgnoreCase("WINTER")) {
            // Winter: use original grass pattern with white tint
            batch.draw(grassTextures[grassType], x * tileSize, y * tileSize, tileSize, tileSize);

            // Add snow patches using precomputed pattern
            if (snowPattern[x][y]) {
                // Save current color
                Color originalColor = new Color(batch.getColor());
                // Set semi-transparent white for snow
                batch.setColor(1.0f, 1.0f, 1.0f, 0.7f);
                // Draw snow patch
                batch.draw(grassTextures[0], x * tileSize, y * tileSize, tileSize, tileSize);
                // Restore original color
                batch.setColor(originalColor);
            }
        } else if (season.equalsIgnoreCase("FALL")) {
            // Fall: yellow/orange grass
            int fallGrassType = (grassType % 3) + 3;
            batch.draw(grassTextures[fallGrassType], x * tileSize, y * tileSize, tileSize, tileSize);
        } else {
            // Normal grass for other seasons
            batch.draw(grassTextures[grassType], x * tileSize, y * tileSize, tileSize, tileSize);
        }
    }

    public void dispose() {
        for (Texture tex : grassTextures) {
            tex.dispose();
        }
    }
}