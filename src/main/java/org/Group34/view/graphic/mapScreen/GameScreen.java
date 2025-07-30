package org.Group34.view.graphic.mapScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.Group34.controller.GameController;
import org.Group34.model.App;
import org.Group34.model.MyGame;
import org.Group34.model.User;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.WalkAble;
import org.Group34.model.entities.buildings.GreenHouse;
import org.Group34.model.gameAssetManagers.PlayerAvatarManager;
import org.Group34.model.map.Map;
import org.Group34.model.map.Space;
import org.Group34.view.graphic.GameMenuGraphic;
import org.Group34.view.graphic.ToolsGraphic;
import org.Group34.view.graphic.dialogs.GreenhouseRepairDialog;

public class GameScreen extends ScreenAdapter {
    // Constants
    private static final int TILE_SIZE = 32;
    private static final int VIEWPORT_WIDTH = 30;
    private static final int VIEWPORT_HEIGHT = 15;
    private static final float MOVE_INTERVAL = 0.13f;

    // Main components
    private final Stage stage;
    private final Skin skin;
    private final Game game;
    private final MyGame myGame;
    private final GameController gameController;
    private final Player player;
    private final Map gameMap;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    // Sub-components
    private final UIManager uiManager;
    private final EnvironmentManager environmentManager;
    private MapRenderer mapRenderer;
    private final WeatherRenderer weatherRenderer;

    // Game state
    private float moveTimer = 0;

    // Other items
    private ToolsGraphic toolsGraphic;
    private GameMenuGraphic gameMenuGraphic;

    public GameScreen(Skin skin, Game game, MyGame myGame, GameController gameController) {
        this.skin = skin;
        this.game = game;
        this.myGame = myGame;
        this.gameController = gameController;
        this.gameMap = myGame.map();
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new ScreenViewport());

        // Initialize player
        User currentUser = App.getCurrentUser();
        if (currentUser != null) {
            this.player = myGame.players().get(currentUser);
        } else {
            this.player = myGame.players().values().iterator().next();
        }

        // Initialize other items
        toolsGraphic = new ToolsGraphic(batch, player);
        gameMenuGraphic = new GameMenuGraphic(batch, player);

        // Initialize sub-components
        this.uiManager = new UIManager(skin, game, stage);
        this.environmentManager = new EnvironmentManager(myGame, gameController);
        this.mapRenderer = new MapRenderer(gameMap, player, TILE_SIZE, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.weatherRenderer = new WeatherRenderer();

        // Set up camera
        camera.setToOrtho(false, VIEWPORT_WIDTH * TILE_SIZE, VIEWPORT_HEIGHT * TILE_SIZE);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateCamera();

        // Update environment and UI
        environmentManager.update();
        uiManager.update(environmentManager);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Render game world
        mapRenderer.render(batch, camera, environmentManager);
        renderPlayer();
        renderOtherItems();
        batch.end();

        // Render weather effects on top of everything
        if (environmentManager.isWeatherActive()) {
            batch.begin();
            weatherRenderer.render(batch, delta, environmentManager);
            batch.end();
        }

        stage.act(delta);
        stage.draw();
    }

    private void handleInput(float delta) {
        boolean keyUp = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean keyDown = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean keyLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean keyRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        // Time advancement cheats
        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            myGame.time().cheatAdvanceTime(1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            myGame.time().cheatAdvanceDate(1, myGame);
        }
        // Money increase cheat
        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            player.addMoney(1000);
        }

        // Greenhouse interaction
        if (Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
            handleGreenhouseInteraction();
        }

        // Player movement
        if (keyUp || keyDown || keyLeft || keyRight) {
            moveTimer += delta;
            if (moveTimer < 0) {
                attemptMove(keyUp, keyDown, keyLeft, keyRight);
                moveTimer = 0;
            } else if (moveTimer >= MOVE_INTERVAL) {
                attemptMove(keyUp, keyDown, keyLeft, keyRight);
                moveTimer = 0;
            }
        } else {
            moveTimer = -MOVE_INTERVAL;
        }
    }

    private void attemptMove(boolean keyUp, boolean keyDown, boolean keyLeft, boolean keyRight) {
        int[] playerLocation = player.getLocation();
        int newX = playerLocation[0];
        int newY = playerLocation[1];
        if (keyUp) newY++;
        else if (keyDown) newY--;
        else if (keyLeft) newX--;
        else if (keyRight) newX++;
        if (newX >= 0 && newX < gameMap.getCurrentPlayerFarm(player).width() &&
                newY >= 0 && newY < gameMap.getCurrentPlayerFarm(player).height()) {
            Entity entity = gameMap.getCurrentPlayerFarm(player).getEntityByLocation(newX, newY);
            // Allow movement if:
            // - No entity
            // - Entity is WalkAble
            // - Entity is a repaired greenhouse
            if (entity == null ||
                    entity instanceof WalkAble ||
                    (entity instanceof GreenHouse && gameController.greenhouse.isRepaired())) {
                gameMap.movePlayer(player, newX, newY);
            }
        }
    }

    private void handleGreenhouseInteraction() {
        Space currentSpace = gameMap.getCurrentPlayerFarm(player);
        GreenHouse greenhouse = null;
        // Search for greenhouse in player's current farm
        for (int x = 0; x < currentSpace.width(); x++) {
            for (int y = 0; y < currentSpace.height(); y++) {
                Entity entity = currentSpace.getEntityByLocation(x, y);
                if (entity instanceof GreenHouse) {
                    greenhouse = (GreenHouse) entity;
                    break;
                }
            }
            if (greenhouse != null) break;
        }
        if (greenhouse != null) {
            if (!greenhouse.isRepaired()) {
                GreenhouseRepairDialog dialog = new GreenhouseRepairDialog(
                        "",
                        skin,
                        gameController,
                        greenhouse.getRepairWood(),
                        greenhouse.getRepairMoney()
                );
                dialog.show(stage);
                // Add listener to refresh after dialog closes
                dialog.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                        // Force a refresh of the game state
                        // This will ensure the greenhouse becomes walkable and texture changes
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                // Refresh the map renderer to update textures
                                mapRenderer.dispose();
                                mapRenderer = new MapRenderer(gameMap, player, TILE_SIZE, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
                            }
                        });
                    }
                });
            } else {
                // Greenhouse is already repaired
                Dialog infoDialog = new Dialog("", skin);
                infoDialog.text("The greenhouse is already repaired and ready to use!");
                infoDialog.button("OK");
                infoDialog.show(stage);
            }
        } else {
            // No greenhouse found in the farm
            Dialog infoDialog = new Dialog("", skin);
            infoDialog.text("No greenhouse found in your farm. You need to build one first!");
            infoDialog.button("OK");
            infoDialog.show(stage);
        }
    }

    private void updateCamera() {
        int[] playerPos = player.getLocation();
        camera.position.set(
                playerPos[0] * TILE_SIZE + TILE_SIZE / 2f,
                playerPos[1] * TILE_SIZE + TILE_SIZE / 2f,
                0
        );
        camera.update();
    }

    private void renderPlayer() {
        int[] pos = player.getLocation();
        Texture playerTexture = PlayerAvatarManager.female_player1;
        batch.draw(playerTexture, pos[0] * TILE_SIZE, pos[1] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    private void renderOtherItems() {
        toolsGraphic.update(TILE_SIZE);
        gameMenuGraphic.update(camera);
    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
        weatherRenderer.dispose();
        stage.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}