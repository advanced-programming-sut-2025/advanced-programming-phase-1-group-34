package org.Group34.view.graphic;

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
import org.Group34.controller.menu.ProfileMenuController;
import org.Group34.model.App;
import org.Group34.model.Result;
import org.Group34.model.User;

public class ProfileScreen extends ScreenAdapter {
    private final Stage stage;
    private final Skin skin;
    private final Game game;
    private final ProfileMenuController controller;
    private final Texture backgroundTexture;
    private final Image backgroundImage;
    private Label statusLabel;

    public ProfileScreen(Skin skin, Game game) {
        this.skin = skin;
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.controller = new ProfileMenuController();

        backgroundTexture = new Texture(Gdx.files.internal("images/background-profile.png"));
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
        statusTable.top().right().padTop(400).padRight(100);

        // Fields
        TextField usernameField = new TextField("", skin);
        usernameField.setMessageText("New Username");

        TextField oldPasswordField = new TextField("", skin);
        oldPasswordField.setMessageText("Old Password");
        oldPasswordField.setPasswordMode(true);
        oldPasswordField.setPasswordCharacter('*');

        TextField newPasswordField = new TextField("", skin);
        newPasswordField.setMessageText("New Password");
        newPasswordField.setPasswordMode(true);

        TextField nicknameField = new TextField("", skin);
        nicknameField.setMessageText("New Nickname");

        TextField emailField = new TextField("", skin);
        emailField.setMessageText("New Email");

        // Status label
        statusLabel = new Label("", skin);
        statusLabel.setColor(Color.RED);

        // Buttons
        TextButton changeUsernameButton = new TextButton("Change", skin);
        TextButton changePasswordButton = new TextButton("Change", skin);
        TextButton changeNicknameButton = new TextButton("Change", skin);
        TextButton changeEmailButton = new TextButton("Change", skin);
        TextButton showInfoButton = new TextButton("Show Info", skin);
        TextButton backToMainMenuButton = new TextButton("Back to Main Menu", skin);

        // Button listeners
        changeUsernameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.changeUsername(usernameField.getText().trim(), App.getCurrentUser());
                showStatus(result);
            }
        });

        changePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.changePassword(
                        newPasswordField.getText().trim(),
                        oldPasswordField.getText().trim(),
                        App.getCurrentUser()
                );
                showStatus(result);
            }
        });

        changeNicknameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.changeNickname(nicknameField.getText().trim(), App.getCurrentUser());
                showStatus(result);
            }
        });

        changeEmailButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.changeEmail(emailField.getText().trim(), App.getCurrentUser());
                showStatus(result);
            }
        });

        showInfoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                User user = App.getCurrentUser();
                showUserInfoDialog(
                        user.getUsername(),
                        user.getNickname(),
                        user.getHighestMoney(),
                        user.getPlayedGamesCount()
                );
            }
        });


        backToMainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(skin, game));
                dispose();
            }
        });

        // Layout fields
        formTable.padLeft(50).left();
        formTable.add(new Label("Change Username:", skin)).left().padBottom(10);
        formTable.add(usernameField).width(400).padLeft(10).padBottom(10);
        formTable.add(changeUsernameButton).padLeft(10).row();

        formTable.add(new Label("Change Password:", skin)).left().padBottom(10);
        Table passwordTable = new Table();
        passwordTable.add(oldPasswordField).width(190).padRight(5);
        passwordTable.add(newPasswordField).width(190);
        formTable.add(passwordTable).padLeft(10).padBottom(10);
        formTable.add(changePasswordButton).padLeft(10).row();

        formTable.add(new Label("Change Nickname:", skin)).left().padBottom(10);
        formTable.add(nicknameField).width(400).padLeft(10).padBottom(10);
        formTable.add(changeNicknameButton).padLeft(10).row();

        formTable.add(new Label("Change Email:", skin)).left().padBottom(10);
        formTable.add(emailField).width(400).padLeft(10).padBottom(10);
        formTable.add(changeEmailButton).padLeft(10).row();

        formTable.add(showInfoButton).colspan(3).padTop(20).row();
        formTable.add(backToMainMenuButton).colspan(3).padTop(10);

        statusTable.add(statusLabel).right();

        stage.addActor(backgroundImage);
        stage.addActor(mainTable);
        mainTable.add(formTable).expand().left();
        stage.addActor(statusTable);
    }

    private void showStatus(Result result) {
        statusLabel.setText(result.message());
        statusLabel.clearActions();
        statusLabel.addAction(Actions.sequence(
                Actions.delay(1.5f),
                Actions.run(() -> statusLabel.setText(""))
        ));
    }

    private void showUserInfoDialog(String username, String nickname, int highestMoney, int playedGames) {
        Dialog dialog = new Dialog("", skin);
        dialog.getContentTable().pad(20);

        dialog.getContentTable().add(new Label("Username: " + username, skin)).left().row();
        dialog.getContentTable().add(new Label("Nickname: " + nickname, skin)).left().row();
        dialog.getContentTable().add(new Label("Highest Money: " + highestMoney, skin)).left().row();
        dialog.getContentTable().add(new Label("Played Games:  " + playedGames, skin)).left().row();

        dialog.button("Close", true);
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
