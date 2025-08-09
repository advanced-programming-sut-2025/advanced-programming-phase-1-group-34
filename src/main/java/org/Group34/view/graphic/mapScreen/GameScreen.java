package org.Group34.view.graphic.mapScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.Group34.controller.GameController;
import org.Group34.controller.AnimalController;
import org.Group34.controller.AnimalBuildingController;
import org.Group34.model.App;
import org.Group34.model.MyGame;
import org.Group34.model.User;
import org.Group34.model.entities.Entity;
import org.Group34.model.entities.NPCOnMap;
import org.Group34.model.entities.Player;
import org.Group34.model.entities.WalkAble;
import org.Group34.model.entities.buildings.GreenHouse;
import org.Group34.model.entities.buildings.shops.*;
import org.Group34.model.entities.npcs.NPC;
import org.Group34.model.enums.Season;
import org.Group34.model.gameAssetManagers.NPCDialogueManager;
import org.Group34.model.gameAssetManagers.PlayerAvatarManager;
import org.Group34.model.map.Map;
import org.Group34.model.map.Space;
import org.Group34.network.client.GameClient;
import org.Group34.view.graphic.GameMenuGraphic;
import org.Group34.view.graphic.GraphicAppView;
import org.Group34.view.graphic.ItemsGraphic;
import org.Group34.view.graphic.dialogs.GreenhouseRepairDialog;
import org.Group34.view.graphic.gameMenu.AnimalMenu;
import org.Group34.view.graphic.menuScreen.MainMenuScreen;

public class GameScreen extends ScreenAdapter {
    private static final int TILE_SIZE = 32;
    private static final int VIEWPORT_WIDTH = 30;
    private static final int VIEWPORT_HEIGHT = 15;
    private static final float MOVE_INTERVAL = 0.13f;
    private static final float MESSAGE_DURATION = 3f;
    private static final float PASSOUT_DURATION = 3f;
    private static final float PASSOUT_ROTATION = 90f;
    private final Stage stage;
    private final Skin skin;
    private final Game game;
    private final MyGame myGame;
    private final GameController gameController;
    private final Player player;
    private final Map gameMap;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final UIManager uiManager;
    private final EnvironmentManager environmentManager;
    private MapRenderer mapRenderer;
    private final WeatherRenderer weatherRenderer;
    private boolean NPCsInitialized = false;
    private NPCDialogueManager npcDialogueManager;
    private float moveTimer = 0;
    private ItemsGraphic toolsGraphic;
    private GameMenuGraphic gameMenuGraphic;
    private BitmapFont messageFont;
    private String currentMessage = "";
    private float messageTimer = 0;
    private Texture mapOverviewTexture;
    private boolean showMapOverview = false;
    private boolean isPassingOut = false;
    private float passoutTimer = 0;
    private float currentRotation = 0;
    private int[] passoutStartLocation;
    private boolean passoutCompleted = false;
    private boolean hidePlayerDuringRender = false;
    private final GraphicAppView app;
    private GameClient client;

    private final AnimalController animalController;
    private final AnimalBuildingController buildingController;
    private Space currentSpace;

    public GameScreen(Skin skin, Game game, MyGame myGame, GameController gameController, GraphicAppView app, GameClient client) {
        this.skin = skin;
        this.game = game;
        this.myGame = myGame;
        this.gameController = gameController;
        this.gameMap = myGame.getMap();
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.stage = new Stage(new ScreenViewport());
        this.app = app;
        this.client = client;
        User currentUser = App.getCurrentUser();
        if (currentUser != null) {
            this.player = myGame.getPlayers().get(currentUser);
        } else {
            this.player = myGame.getPlayers().values().iterator().next();
        }

        this.buildingController = new AnimalBuildingController();
        this.animalController = new AnimalController(buildingController, gameMap.getCurrentPlayerFarm(player));

        this.currentSpace = gameMap.getCurrentPlayerFarm(player);

        AnimalMenu.initialize(animalController, buildingController, currentSpace);

        toolsGraphic = new ItemsGraphic(batch, player, gameController);
        gameMenuGraphic = new GameMenuGraphic(batch, player, gameController);
        this.uiManager = new UIManager(skin, game, stage);
        this.environmentManager = new EnvironmentManager(myGame, gameController);
        this.mapRenderer = new MapRenderer(gameMap, player, TILE_SIZE, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        this.weatherRenderer = new WeatherRenderer();
        this.npcDialogueManager = new NPCDialogueManager();
        messageFont = new BitmapFont();
        messageFont.setColor(Color.WHITE);
        mapOverviewTexture = new Texture(Gdx.files.internal("gameMenu/mapOverView.png"));
        camera.setToOrtho(false, VIEWPORT_WIDTH * TILE_SIZE, VIEWPORT_HEIGHT * TILE_SIZE);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        if (!isPassingOut) {
            handleInput(delta);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Only update camera if not in the middle of passout animation
        if (!isPassingOut || passoutCompleted) {
            updateCamera();
        }
        environmentManager.update();
        uiManager.update(environmentManager, player);
        if (!NPCsInitialized) {
            // به‌روزرسانی فضای فعلی بازیکن
            currentSpace = gameMap.getCurrentPlayerFarm(player);
            environmentManager.initializeNPCs(currentSpace);
            for (NPCOnMap npcOnMap : environmentManager.getNpcManager().getNpcOnMaps()) {
                currentSpace.placingEntity(npcOnMap.getX(), npcOnMap.getY(), npcOnMap);
            }
            NPCsInitialized = true;
        }
        int[] playerPos = player.getLocation();
        npcDialogueManager.update(delta, environmentManager.getNpcManager().getNpcOnMaps(), playerPos);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Temporarily hide player from the map during passout animation
        if (isPassingOut) {
            int[] originalLocation = player.getLocation();
            // Move player to a temporary location outside the visible area
            player.setLocation(new int[]{-1, -1});
            mapRenderer.render(batch, camera, environmentManager, npcDialogueManager);
            // Restore player location
            player.setLocation(originalLocation);
        } else {
            mapRenderer.render(batch, camera, environmentManager, npcDialogueManager);
        }
        renderPlayer();
        renderOtherItems();
        batch.end();
        if (environmentManager.isWeatherActive()) {
            batch.begin();
            weatherRenderer.render(batch, delta, environmentManager);
            batch.end();
        }
        if (messageTimer > 0) {
            messageTimer -= delta;
            batch.begin();
            messageFont.draw(batch, currentMessage, 10, Gdx.graphics.getHeight() - 20);
            batch.end();
        }
        stage.act(delta);
        stage.draw();
        if (showMapOverview) {
            batch.setProjectionMatrix(stage.getCamera().combined);
            batch.begin();
            batch.draw(mapOverviewTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
        }
        if (isPassingOut) {
            passoutTimer += delta;
            currentRotation = (passoutTimer / PASSOUT_DURATION) * PASSOUT_ROTATION;
            if (passoutTimer >= PASSOUT_DURATION) {
                // Move player to initial location
                int[] initialLocation = {72, 10};
                gameMap.movePlayer(player, initialLocation[0], initialLocation[1]);
                gameController.cheatAdvanceDate("1");
                player.setEnergy(500);
                // Reset passout state
                isPassingOut = false;
                passoutTimer = 0;
                currentRotation = 0;
                passoutCompleted = true;
                // Force camera update to new position
                updateCamera();
            }
        } else if (passoutCompleted) {
            // Reset the flag after rendering is complete
            passoutCompleted = false;
        }


        if ("animal".equals(player.getCurrentGameMenu())) {
            batch.begin();
            AnimalMenu.draw(batch, player, camera);
            batch.end();
        }
    }

    private void handleInput(float delta) {
        boolean keyUp = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean keyDown = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean keyLeft = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean keyRight = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            myGame.getTime().cheatAdvanceTime(1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            myGame.getTime().cheatAdvanceDate(1, myGame, player);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            player.addMoney(1000);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            player.setEnergy(player.getEnergy() + 100);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F6)) {
            player.setEnergy(player.getEnergy() - 100);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
            handleGreenhouseInteraction();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F7)) {
            //gameController.nextTurn();
            currentMessage = "Next turn started!";
            messageTimer = MESSAGE_DURATION;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F8)) {
            gameController.exitGame();
            currentMessage = "Game saved successfully!";
            messageTimer = MESSAGE_DURATION;
            game.setScreen(new MainMenuScreen(skin, game, app, client));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            handleNPCDialogue();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            player.setCurrentGameMenu("inventory");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            showMapOverview = !showMapOverview;
        }
        if (AnimalMenu.isPlacingBuilding()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                AnimalMenu.cancelPlacingBuilding();
            }
            return;
        }
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
        if (AnimalMenu.isPlacingBuilding()) {
            return;
        }
        if (newX >= 0 && newX < gameMap.getCurrentPlayerFarm(player).width() &&
                newY >= 0 && newY < gameMap.getCurrentPlayerFarm(player).height()) {
            Entity entity = gameMap.getCurrentPlayerFarm(player).getEntityByLocation(newX, newY);
            if (entity == null ||
                    entity instanceof WalkAble ||
                    (entity instanceof GreenHouse && gameController.greenhouse.isRepaired())) {
                gameMap.movePlayer(player, newX, newY);
                if (player.getEnergy() > 0) {
                    player.decreaseEnergy(1);
                } else {
                    // Start passout sequence
                    isPassingOut = true;
                    passoutTimer = 0;
                    passoutStartLocation = playerLocation.clone();
                }
            }
            handleGoToShop(entity, player);
        }
    }

    private void handleGoToShop(Entity entity, Player player) {
        String menu = null;
        if (entity instanceof Blacksmith) {
            menu = "blacksmith";
        } else if (entity instanceof SalePlace) {
            menu = "salePlace";
        }
        player.setCurrentGameMenu(menu);
    }

    private void handleGreenhouseInteraction() {
        Space currentSpace = gameMap.getCurrentPlayerFarm(player);
        GreenHouse greenhouse = null;
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
                dialog.addListener(new ChangeListener() {
                    @Override
                    public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                mapRenderer.dispose();
                                mapRenderer = new MapRenderer(gameMap, player, TILE_SIZE, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
                            }
                        });
                    }
                });
            } else {
                Dialog infoDialog = new Dialog("", skin);
                infoDialog.text("The greenhouse is already repaired and ready to use!");
                infoDialog.button("OK");
                infoDialog.show(stage);
            }
        } else {
            Dialog infoDialog = new Dialog("", skin);
            infoDialog.text("No greenhouse found in your farm. You need to build one first!");
            infoDialog.button("OK");
            infoDialog.show(stage);
        }
    }

    private void handleNPCDialogue() {
        int[] playerPos = player.getLocation();
        Space currentSpace = gameMap.getCurrentPlayerFarm(player);
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int checkX = playerPos[0] + dir[0];
            int checkY = playerPos[1] + dir[1];
            if (checkX >= 0 && checkX < currentSpace.width() &&
                    checkY >= 0 && checkY < currentSpace.height()) {
                Entity entity = currentSpace.getEntityByLocation(checkX, checkY);
                if (entity instanceof NPCOnMap) {
                    NPCOnMap npcOnMap = (NPCOnMap) entity;
                    if (npcDialogueManager.isDialogueIconVisible(npcOnMap)) {
                        NPC npc = npcOnMap.getNpc();
                        String seasonStr = environmentManager.getCurrentSeason();
                        Season season = Season.valueOf(seasonStr.toUpperCase());
                        String dialogue = npc.getDialogueBySeason(season);
                        Dialog dialog = new Dialog(npc.getName(), skin);
                        dialog.text(dialogue);
                        dialog.button("OK");
                        dialog.show(stage);
                        npc.increaseFriendship(20);
                        npcDialogueManager.activateDialogue(npcOnMap);
                        break;
                    }
                }
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
        int[] pos;
        if (isPassingOut) {
            // Use the passout start location during animation
            pos = passoutStartLocation;
        } else {
            // Use current player location
            pos = player.getLocation();
        }
        Texture playerTexture = PlayerAvatarManager.female_player1;
        if (isPassingOut) {
            batch.draw(playerTexture,
                    pos[0] * TILE_SIZE,
                    pos[1] * TILE_SIZE,
                    TILE_SIZE / 2f,
                    TILE_SIZE / 2f,
                    TILE_SIZE,
                    TILE_SIZE,
                    1,
                    1,
                    currentRotation,
                    0,
                    0,
                    playerTexture.getWidth(),
                    playerTexture.getHeight(),
                    false,
                    false);
        } else {
            batch.draw(playerTexture, pos[0] * TILE_SIZE, pos[1] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    private void renderOtherItems() {
        toolsGraphic.update(TILE_SIZE);
        gameMenuGraphic.update(camera, environmentManager);
    }

    @Override
    public void dispose() {
        mapRenderer.dispose();
        weatherRenderer.dispose();
        npcDialogueManager.dispose();
        AnimalMenu.dispose(); // اضافه کردن dispose برای AnimalMenu
        stage.dispose();
        batch.dispose();
        messageFont.dispose();
        if (mapOverviewTexture != null) {
            mapOverviewTexture.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}