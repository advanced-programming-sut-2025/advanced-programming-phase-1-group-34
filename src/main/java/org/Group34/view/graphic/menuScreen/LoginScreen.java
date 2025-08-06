package org.Group34.view.graphic.menuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.Group34.controller.menu.LoginMenuController;
import org.Group34.model.Result;
import org.Group34.model.User;
import org.Group34.model.App;
import org.Group34.network.client.GameClient;
import org.Group34.view.graphic.GraphicAppView;

public class LoginScreen extends ScreenAdapter {
    private final Stage stage;
    private final Skin skin;
    private final LoginMenuController controller;
    private final Texture backgroundTexture;
    private final Image backgroundImage;
    private final Game game;
    private Label statusLabel;

    private final GraphicAppView app;
    private GameClient client;

    public LoginScreen(Skin skin, Game game, GraphicAppView app, GameClient client) {
        this.skin = skin;
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.controller = new LoginMenuController();

        this.app = app;
        this.client = client;

        backgroundTexture = new Texture(Gdx.files.internal("menuBackgrounds/background-register.png"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);

        Gdx.input.setInputProcessor(stage);
        createUI();
    }

    private void createUI() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        Table formTable = new Table();
        Table statusTable = new Table();
        statusTable.setFillParent(true);
        statusTable.top().right().padTop(400).padRight(200);

        // Fields
        TextField usernameField = new TextField("", skin);
        TextField passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        // Status label
        statusLabel = new Label("", skin);
        statusLabel.setColor(Color.RED);

        // Buttons
        TextButton loginButton = new TextButton("Login", skin);
        TextButton loginWithSaveButton = new TextButton("Login with Save", skin);
        TextButton goToRegisterButton = new TextButton("Go to Register", skin);

        // Forget password as a question mark
        TextButton forgetPasswordButton = new TextButton("?", skin);
        forgetPasswordButton.setColor(Color.YELLOW);

        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.login(
                        usernameField.getText().trim(),
                        passwordField.getText().trim()
                );
                handleResult(result);
            }
        });

        loginWithSaveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.loginWithSave(
                        usernameField.getText().trim(),
                        passwordField.getText().trim()
                );
                handleResult(result);
            }
        });

        forgetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showForgetPasswordDialog(usernameField.getText().trim());
            }
        });

        goToRegisterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new RegisterScreen(skin, game, app, client));
                dispose();
            }
        });

        // Layout fields
        formTable.padLeft(200).left();
        formTable.add(new Label("Username:", skin)).left().padBottom(10);
        formTable.add(usernameField).left().width(400).padLeft(10).padBottom(10).row();
        formTable.add(new Label("Password:", skin)).left().padBottom(10);
        formTable.add(passwordField).left().width(400).padLeft(10).padBottom(10);
        formTable.add(forgetPasswordButton).padLeft(5).row();

        // Buttons row
        Table buttonRow = new Table();
        buttonRow.add(loginButton).padRight(30);
        buttonRow.add(loginWithSaveButton);

        formTable.add(buttonRow).colspan(2).row();
        formTable.add(goToRegisterButton).colspan(2).padTop(50);

        statusTable.add(statusLabel).right();

        stage.addActor(backgroundImage);
        stage.addActor(mainTable);
        mainTable.add(formTable).expand().left();
        stage.addActor(statusTable);
    }

    private void handleResult(Result result) {
        statusLabel.setText(result.message());
        statusLabel.clearActions();
        statusLabel.addAction(Actions.sequence(
                Actions.delay(1.5f),
                Actions.run(() -> statusLabel.setText(""))
        ));

        if (result.success()) {
            game.setScreen(new MainMenuScreen(skin, game, app, client));
            dispose();
        }
    }

    private void showForgetPasswordDialog(String username) {
        if (App.getUserByUsername(username) == null) {
            statusLabel.setText("Username doesn't exist!");
            return;
        }

        User user = App.getUserByUsername(username);

        Label questionLabel = new Label(user.getSecurityQuestion(), skin);

        final TextField answerField = new TextField("", skin);
        answerField.setMessageText("Answer");

        final TextField newPasswordField = new TextField("", skin);
        newPasswordField.setMessageText("New Password");
        newPasswordField.setPasswordMode(true);
        newPasswordField.setPasswordCharacter('*');

        final TextField confirmPasswordField = new TextField("", skin);
        confirmPasswordField.setMessageText("Confirm Password");
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');

        Dialog dialog = new Dialog("", skin) {
            @Override
            protected void result(Object obj) {
                if (obj instanceof Boolean && (Boolean) obj) {
                    if (!answerField.getText().equals(user.getSecurityAnswer())) {
                        statusLabel.setText("Incorrect answer!");
                    } else if (!newPasswordField.getText().equals(confirmPasswordField.getText())) {
                        statusLabel.setText("Passwords do not match!");
                    } else {
                        user.setPassword(newPasswordField.getText().trim());
                        statusLabel.setText("Password changed successfully!");
                    }
                }
            }
        };

        Table content = dialog.getContentTable();
        content.defaults().pad(10);

        content.add(questionLabel).row();
        content.add(answerField).width(400).row();
        content.add(newPasswordField).width(400).row();
        content.add(confirmPasswordField).width(400).row();

        dialog.button("Confirm", true);
        dialog.button("Cancel", false);

        dialog.show(stage);
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
        backgroundTexture.dispose();
    }
}
