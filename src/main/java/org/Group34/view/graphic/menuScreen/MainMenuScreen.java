package org.Group34.view.graphic.menuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.Group34.view.graphic.GraphicAppView;

public class MainMenuScreen extends ScreenAdapter {
    private final Game game;
    private final Skin skin;
    private final Stage stage;
    private final Texture backgroundTexture;
    private final Image backgroundImage;

    private final GraphicAppView app;

    public MainMenuScreen(Skin skin, Game game, GraphicAppView app) {
        this.skin = skin;
        this.game = game;
        this.stage = new Stage(new ScreenViewport());

        this.app = app;

        backgroundTexture = new Texture(Gdx.files.internal("menuBackgrounds/background-mainmenu.png"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);

        Gdx.input.setInputProcessor(stage);
        createUI();
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);

        TextButton profileButton = new TextButton("Profile Menu", skin);
        TextButton gameButton = new TextButton("Game Menu", skin);
        TextButton logoutButton = new TextButton("Logout", skin);

        profileButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                System.out.println("Switch to Profile Menu");
                game.setScreen(new ProfileScreen(skin, game, app));
            }
        });

        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                System.out.println("Switch to MyGame Menu");
                //game.setScreen(new GameMenuScreen(skin, game, app));
                game.setScreen(new LobbyMenuScreen(skin, game, app));
            }
        });

        logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                System.out.println("Logout: back to Login");
                game.setScreen(new LoginScreen(skin, game, app));
            }
        });

        table.add(profileButton).width(450).padBottom(15).row();
        table.add(gameButton).width(450).padBottom(50).row();
        table.add(logoutButton).width(300);

        stage.addActor(backgroundImage);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
