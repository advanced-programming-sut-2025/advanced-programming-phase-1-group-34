package org.Group34.view.graphic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.Group34.view.graphic.RegisterScreen;

public class GraphicAppView extends Game {

    @Override
    public void create() {
        System.out.println("Graphic view started.");

        Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        this.setScreen(new RegisterScreen(skin, this));
    }

    @Override
    public void render() {
        super.render();

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
        System.out.println("Graphic view closed.");
        super.dispose();
    }
}
