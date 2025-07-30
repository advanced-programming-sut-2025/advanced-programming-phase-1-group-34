package org.Group34.view.graphic.mapScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.Group34.controller.GameController;
import org.Group34.model.App;
import org.Group34.model.MyGame;
import org.Group34.model.User;
import org.Group34.model.entities.Player;
import org.Group34.model.map.Map;

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
    private final MapRenderer mapRenderer;
    private final WeatherRenderer weatherRenderer;

    // Game state
    private float moveTimer = 0;

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
            org.Group34.model.entities.Entity entity = gameMap.getCurrentPlayerFarm(player).getEntityByLocation(newX, newY);
            if (entity == null || entity instanceof org.Group34.model.entities.WalkAble) {
                gameMap.movePlayer(player, newX, newY);
            }
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
        com.badlogic.gdx.graphics.Texture playerTexture = org.Group34.model.gameAssetManagers.PlayerAvatarManager.female_player1;
        batch.draw(playerTexture, pos[0] * TILE_SIZE, pos[1] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
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