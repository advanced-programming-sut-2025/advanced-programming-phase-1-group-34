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

        backgroundTexture = new Texture(Gdx.files.internal("menuBackgrounds/background-profile.png"));
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

        // === fields ===
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

        // === status label ===
        statusLabel = new Label("", skin);
        statusLabel.setColor(Color.RED);

        // === buttons ===
        TextButton changeUsernameButton = new TextButton("Change", skin);
        TextButton changePasswordButton = new TextButton("Change", skin);
        TextButton changeNicknameButton = new TextButton("Change", skin);
        TextButton changeEmailButton = new TextButton("Change", skin);
        TextButton changeAvatarButton = new TextButton("Save Avatar", skin); // NEW
        TextButton showInfoButton = new TextButton("Show Info", skin);
        TextButton backToMainMenuButton = new TextButton("Back to Main Menu", skin);

        // === avatar setup ===
        User currentUser = App.getCurrentUser();
        String gender = currentUser.getGender().toLowerCase(); // "male" or "female"

        // Initialize avatar based on user's saved avatar
        final boolean[] isFirstAvatar = {currentUser.getAvatar() == 1}; // MODIFIED
        final Texture avatarTexture = new Texture(Gdx.files.internal(
                "playerAvatars/" + gender + "_avatar" + currentUser.getAvatar() + ".png")); // MODIFIED
        final Image avatarImage = new Image(avatarTexture);

        avatarImage.setSize(150, 150);

        avatarImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isFirstAvatar[0] = !isFirstAvatar[0];
                String avatarPath = "playerAvatars/" + gender + "_" +
                        (isFirstAvatar[0] ? "avatar1.png" : "avatar2.png");
                avatarImage.setDrawable(new Image(new Texture(Gdx.files.internal(avatarPath))).getDrawable());
            }
        });

        // NEW: Change Avatar Button Listener
        changeAvatarButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int newAvatar = isFirstAvatar[0] ? 1 : 2;
                currentUser.setAvatar(newAvatar);
                statusLabel.setText("Avatar changed successfully!");
                statusLabel.clearActions();
                statusLabel.addAction(Actions.sequence(
                        Actions.delay(1.5f),
                        Actions.run(() -> statusLabel.setText(""))
                ));
            }
        });

        // === button listeners ===
        changeUsernameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.changeUsername(usernameField.getText().trim(), currentUser);
                showStatus(result);
            }
        });

        changePasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.changePassword(
                        newPasswordField.getText().trim(),
                        oldPasswordField.getText().trim(),
                        currentUser
                );
                showStatus(result);
            }
        });

        changeNicknameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.changeNickname(nicknameField.getText().trim(), currentUser);
                showStatus(result);
            }
        });

        changeEmailButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.changeEmail(emailField.getText().trim(), currentUser);
                showStatus(result);
            }
        });

        showInfoButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showUserInfoDialog(
                        currentUser.getUsername(),
                        currentUser.getNickname(),
                        currentUser.getHighestMoney(),
                        currentUser.getPlayedGamesCount()
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

        // === layout left side (form) ===
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
        formTable.padLeft(50).left();

        Table avatarTable = new Table();
        avatarTable.add(avatarImage).size(150).row();
        avatarTable.add(changeAvatarButton).padTop(10);

        statusTable.add(statusLabel).right();

        // === add all to main table ===
        stage.addActor(backgroundImage);
        stage.addActor(mainTable);

        // left form + right avatar
        Table contentTable = new Table();
        contentTable.add(formTable).expand().left().padRight(100);
        contentTable.add(avatarTable).right().top().padTop(50).padRight(230);

        mainTable.add(contentTable).expand().fill();
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
