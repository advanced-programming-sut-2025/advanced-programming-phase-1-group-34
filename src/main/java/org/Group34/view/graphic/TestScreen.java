package org.Group34.view.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.Group34.model.gameAssetManagers.ToolAssetManager;

public class TestScreen extends ScreenAdapter {
    private final Stage stage;

    public TestScreen() {
        this.stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);
        Texture texture = ToolAssetManager.getIridiumFishingPole();
        Image image = new Image(texture);
        stage.addActor(image);
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

