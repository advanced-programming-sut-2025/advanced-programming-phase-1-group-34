package org.Group34.view.graphic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.Group34.controller.GameController;
import org.Group34.model.App;
import org.Group34.model.MyGame;
import org.Group34.model.Result;
import org.Group34.model.User;
import org.Group34.model.entities.Player;
import org.Group34.model.gameAssetManagers.PlayerAvatarManager;
import org.Group34.model.map.Map;
import org.Group34.model.map.Space;
import org.Group34.model.entities.Entity;
import org.Group34.view.graphic.menuScreen.MainMenuScreen;
import org.Group34.model.entities.WalkAble;

import java.util.Random;

public class GameScreen extends ScreenAdapter {
    private final Stage stage;
    private final Skin skin;
    private final Game game;
    private final GameController gameController;
    private final Player player;
    private final Map gameMap;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    // Map rendering constants
    private static final int TILE_SIZE = 32;
    private static final int VIEWPORT_WIDTH = 30;
    private static final int VIEWPORT_HEIGHT = 15;

    private float moveTimer = 0;
    private static final float MOVE_INTERVAL = 0.13f;

    // Time and Money
    private Label timeLabel;
    private Label moneyLabel;

    // Textures
    private final Texture[] grassTextures;
    private final int[][] grassPattern;

    public GameScreen(Skin skin, Game game, MyGame myGame, GameController gameController) {
        this.skin = skin;
        this.game = game;
        this.gameController = gameController;
        this.gameMap = myGame.map();
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new ScreenViewport());

        User currentUser = App.getCurrentUser();
        if (currentUser != null) {
            this.player = myGame.players().get(currentUser);
        }
        else {
            this.player = myGame.players().values().iterator().next();
        }

        // Load grass texture
        grassTextures = new Texture[6];
        for (int i = 0; i < 6; i++) {
            grassTextures[i] = new Texture(Gdx.files.internal("tiles/grass_" + i + ".png"));
        }

        int mapWidth = myGame.map().getCurrentPlayerFarm(player).width();
        int mapHeight = myGame.map().getCurrentPlayerFarm(player).height();
        grassPattern = new int[mapWidth][mapHeight];

        Random random = new Random();
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                grassPattern[x][y] = random.nextInt(6);
            }
        }

        // Time and Money setup
        timeLabel = new Label("", skin);
        timeLabel.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 30);
        stage.addActor(timeLabel);

        moneyLabel = new Label("", skin);
        moneyLabel.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 60);
        stage.addActor(moneyLabel);

        // Set up camera
        camera.setToOrtho(false, VIEWPORT_WIDTH * TILE_SIZE, VIEWPORT_HEIGHT * TILE_SIZE);
        Gdx.input.setInputProcessor(stage);
        createUI();
    }

    private void createUI() {
        Label gameLabel = new Label("", skin);
        gameLabel.setPosition(100, Gdx.graphics.getHeight() - 50);
        stage.addActor(gameLabel);

        TextButton backButton = new TextButton("Back to Main Menu", skin);
        backButton.setPosition(100, 50);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(skin, game));
                dispose();
            }
        });
        stage.addActor(backButton);
    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateCamera();
        batch.setProjectionMatrix(camera.combined);

        updateTimeLabel();

        batch.begin();
        renderMap();
        renderPlayer();
        batch.end();
        stage.act(delta);
        stage.draw();
    }

    private void handleInput(float delta) {
        boolean keyUp = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean keyDown = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean keyLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean keyRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        if (keyUp || keyDown || keyLeft || keyRight) {
            moveTimer += delta;

            if (moveTimer < 0) {
                attemptMove(keyUp, keyDown, keyLeft, keyRight);
                moveTimer = 0;
            }
            else if (moveTimer >= MOVE_INTERVAL) {
                attemptMove(keyUp, keyDown, keyLeft, keyRight);
                moveTimer = 0;
            }
        }
        else {
            moveTimer = -MOVE_INTERVAL;
        }
    }

    private void attemptMove(boolean keyUp, boolean keyDown, boolean keyLeft, boolean keyRight) {
        int[] playerLocation = player.getLocation();
        int newX = playerLocation[0];
        int newY = playerLocation[1];

        if (keyUp) {
            newY++;
        } else if (keyDown) {
            newY--;
        } else if (keyLeft) {
            newX--;
        } else if (keyRight) {
            newX++;
        }

        if (newX >= 0 && newX < gameMap.getCurrentPlayerFarm(player).width() &&
                newY >= 0 && newY < gameMap.getCurrentPlayerFarm(player).height()) {
            Entity entity = gameMap.getCurrentPlayerFarm(player).getEntityByLocation(newX, newY);
            if (entity == null || entity instanceof WalkAble) {
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

    private void renderMap() {
        Space currentSpace = gameMap.getCurrentPlayerFarm(player);
        int startX = (int)(camera.position.x / TILE_SIZE) - VIEWPORT_WIDTH / 2;
        int startY = (int)(camera.position.y / TILE_SIZE) - VIEWPORT_HEIGHT / 2;
        startX = Math.max(0, startX);
        startY = Math.max(0, startY);
        startX = Math.min(currentSpace.width() - VIEWPORT_WIDTH, startX);
        startY = Math.min(currentSpace.height() - VIEWPORT_HEIGHT, startY);

        for (int x = startX; x < startX + VIEWPORT_WIDTH; x++) {
            for (int y = startY; y < startY + VIEWPORT_HEIGHT; y++) {
                int grassType = grassPattern[x][y];
                batch.draw(grassTextures[grassType], x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                Entity entity = currentSpace.getEntityByLocation(x, y);
                if (entity != null) {
                    Texture texture = entity.getTexture();
                    if (texture != null) {
                        batch.draw(texture, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    }
                }
            }
        }
    }

    private void renderPlayer() {
        int[] pos = player.getLocation();
        Texture playerTexture = PlayerAvatarManager.female_player1;
        if (playerTexture != null) {
            batch.draw(playerTexture, pos[0] * TILE_SIZE, pos[1] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        } else {
            Gdx.app.error("Rendering", "Player texture not loaded!");
        }
    }

    private void updateTimeLabel() {
        Result timeResult = gameController.displayTime("full");
        if (timeResult.success()) {
            timeLabel.setText(timeResult.message());
        }

        moneyLabel.setText("Money: " + player.getMoney());
    }

    @Override
    public void dispose() {
        for (Texture tex : grassTextures) {
            tex.dispose();
        }
        stage.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}