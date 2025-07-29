package org.Group34.view.graphic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
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
    private final MyGame myGame;
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

    // Time
    private Label dateLabel;
    private Label timeLabel;
    private Label weatherLabel;
    private Label seasonLabel;

    // Color effects
    private boolean shouldDarken = false;
    private final Color darkTint = new Color(0.6f, 0.6f, 0.7f, 1f);
    private Color fallTint = new Color(1.0f, 0.8f, 0.4f, 1.0f);
    private Color winterTint = new Color(0.9f, 0.9f, 1.0f, 1.0f);

    // Weather effects
    private boolean isRaining = false;
    private boolean isSnowing = false;
    private boolean isStormy = false;
    private float weatherTimer = 0;
    private static final float WEATHER_INTERVAL = 0.05f;

    // Textures
    private final Texture[] grassTextures;
    private final int[][] grassPattern;

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

        User currentUser = App.getCurrentUser();
        if (currentUser != null) {
            this.player = myGame.players().get(currentUser);
        }
        else {
            this.player = myGame.players().values().iterator().next();
        }

        this.toolsGraphic = new ToolsGraphic(batch, player);
        this.gameMenuGraphic = new GameMenuGraphic(batch, player);

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

        // Time
        dateLabel = new Label("", skin);
        dateLabel.setPosition(20, Gdx.graphics.getHeight() - 30);
        stage.addActor(dateLabel);

        timeLabel = new Label("", skin);
        timeLabel.setPosition(20, Gdx.graphics.getHeight() - 60);
        stage.addActor(timeLabel);

        weatherLabel = new Label("", skin);
        weatherLabel.setPosition(20, Gdx.graphics.getHeight() - 90);
        stage.addActor(weatherLabel);

        seasonLabel = new Label("", skin);
        seasonLabel.setPosition(20, Gdx.graphics.getHeight() - 120);
        stage.addActor(seasonLabel);

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

    private void updateUI() {
        Result timeResult = gameController.displayTime("datetime");
        if (timeResult.success()) {
            String[] timeParts = timeResult.message().split(" ");
            if (timeParts.length >= 3) {
                seasonLabel.setText("Season: " + timeParts[0]);
                dateLabel.setText("Date: " + timeParts[1]);
                timeLabel.setText("Time: " + timeParts[2]);

                try {
                    String timeStr = timeParts[2];
                    String[] hourMinute = timeStr.split(":");
                    int hour = Integer.parseInt(hourMinute[0]);
                    shouldDarken = (hour >= 17);
                } catch (Exception e) {
                    Gdx.app.error("GameScreen", "Error parsing time: " + e.getMessage());
                }
            }
        }

        String weather = myGame.weatherSystem().getTodayCondition().toString();
        weatherLabel.setText("Weather: " + weather.toLowerCase());

        isRaining = weather.equalsIgnoreCase("RAIN");
        isSnowing = weather.equalsIgnoreCase("SNOW");
        isStormy = weather.equalsIgnoreCase("STORM");

        if (isRaining || isStormy) {
            shouldDarken = true;
            darkTint.set(0.6f, 0.6f, 0.7f, 1.0f);
        }

        String season = myGame.weatherSystem().getSeason().getName();
        if (season.equalsIgnoreCase("FALL")) {
            shouldDarken = true;
            darkTint.set(1.0f, 0.8f, 0.4f, 1.0f);
        } else if (season.equalsIgnoreCase("WINTER")) {
            shouldDarken = true;
            darkTint.set(0.9f, 0.9f, 1.0f, 1.0f);
        }
    }

    @Override
    public void render(float delta) {
        handleInput(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        updateCamera();
        batch.setProjectionMatrix(camera.combined);
        updateUI();
        batch.begin();
        renderMap();
        renderPlayer();

        if (isRaining || isSnowing || isStormy) {
            renderWeatherEffects(delta);
        }

        batch.end();
        stage.act(delta);
        stage.draw();
    }

    private void handleInput(float delta) {
        boolean keyUp = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean keyDown = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean keyLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean keyRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            myGame.time().cheatAdvanceTime(1);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            myGame.time().cheatAdvanceDate(1, myGame);
        }

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

        Color originalColor = batch.getColor().cpy();
        String season = myGame.weatherSystem().getSeason().getName();
        String weather = myGame.weatherSystem().getTodayCondition().toString().toLowerCase();

        if (season.equalsIgnoreCase("WINTER")) {
            if (isSnowing) {
                batch.setColor(0.9f, 0.9f, 1.0f, 1.0f);
            } else {
                batch.setColor(0.95f, 0.95f, 1.0f, 1.0f);
            }
        } else if (shouldDarken) {
            batch.setColor(darkTint);
        }

        for (int x = startX; x < startX + VIEWPORT_WIDTH; x++) {
            for (int y = startY; y < startY + VIEWPORT_HEIGHT; y++) {
                int grassType = grassPattern[x][y];

                if (season.equalsIgnoreCase("WINTER")) {
                    int winterGrassType = 5;
                    batch.draw(grassTextures[winterGrassType], x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                    if (isSnowing && Math.random() < 0.3) {
                        batch.setColor(1, 1, 1, 0.7f);
                        batch.draw(grassTextures[winterGrassType], x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                        batch.setColor(originalColor);
                    }
                }
                else {
                    batch.draw(grassTextures[grassType], x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        batch.setColor(originalColor);
        for (int x = startX; x < startX + VIEWPORT_WIDTH; x++) {
            for (int y = startY; y < startY + VIEWPORT_HEIGHT; y++) {
                Entity entity = currentSpace.getEntityByLocation(x, y);
                if (entity != null && entity.getTexture() != null) {
                    if (season.equalsIgnoreCase("WINTER")) {
                        batch.setColor(0.9f, 0.9f, 1.0f, 1.0f);
                    }
                    batch.draw(entity.getTexture(), x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    batch.setColor(originalColor);
                }
            }
        }

        batch.setColor(originalColor);
    }

    private void renderPlayer() {
        int[] pos = player.getLocation();
        Texture playerTexture = PlayerAvatarManager.female_player1;
        batch.draw(playerTexture, pos[0] * TILE_SIZE, pos[1] * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        otherRender();
    }

    private void otherRender() {
        toolsGraphic.update(TILE_SIZE);
        gameMenuGraphic.update(camera);
    }

    private void renderWeatherEffects(float delta) {
        weatherTimer += delta;
        if (weatherTimer >= WEATHER_INTERVAL) {
            weatherTimer = 0;
            Random random = new Random();

            if (isRaining) {
                batch.setColor(0.5f, 0.5f, 0.8f, 0.7f);
                for (int i = 0; i < 150; i++) {
                    float x = random.nextInt(Gdx.graphics.getWidth());
                    float y = random.nextInt(Gdx.graphics.getHeight());
                    float length = random.nextFloat() * 15 + 10;
                    batch.draw(grassTextures[0], x, y, 2, length);
                }
                batch.setColor(1, 1, 1, 1);
            }

            if (isSnowing) {
                batch.setColor(1, 1, 1, 0.8f);
                for (int i = 0; i < 200; i++) {
                    float x = random.nextInt(Gdx.graphics.getWidth());
                    float y = random.nextInt(Gdx.graphics.getHeight());
                    float size = random.nextFloat() * 4 + 2;
                    batch.draw(grassTextures[0], x, y, size, size);
                }
                batch.setColor(1, 1, 1, 1);
            }

            if (isStormy) {
                batch.setColor(0.4f, 0.4f, 0.4f, 0.6f);
                for (int i = 0; i < 250; i++) {
                    float x = random.nextInt(Gdx.graphics.getWidth());
                    float y = random.nextInt(Gdx.graphics.getHeight());
                    float length = random.nextFloat() * 20 + 15;
                    // رسم خطوط ضخیم‌تر برای طوفان
                    batch.draw(grassTextures[0], x, y, 3, length);
                }
                batch.setColor(1, 1, 1, 1);
            }
        }
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