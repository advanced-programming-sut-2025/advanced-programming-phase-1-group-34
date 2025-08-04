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
import org.Group34.controller.menu.RegisterMenuController;
import org.Group34.model.Result;
import org.Group34.view.graphic.GraphicAppView;

import java.util.ArrayList;

public class RegisterScreen extends ScreenAdapter {
    private final Stage stage;
    private final Skin skin;
    private final RegisterMenuController controller;
    private final Texture backgroundTexture;
    private final Image backgroundImage;
    private Label statusLabel;
    private final Game game;

    private final GraphicAppView app;

    public RegisterScreen(Skin skin, Game game, GraphicAppView app) {
        this.skin = skin;
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.controller = new RegisterMenuController();

        this.app = app;

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
        statusTable.top().right();
        statusTable.padTop(400).padRight(200);

        TextField usernameField = new TextField("", skin);
        TextField passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);

        TextField confirmPasswordField = new TextField("", skin);
        confirmPasswordField.setPasswordMode(true);

        TextField nicknameField = new TextField("", skin);
        TextField emailField = new TextField("", skin);
        SelectBox<String> genderSelect = new SelectBox<>(skin);
        genderSelect.setItems("Male", "Female");

        statusLabel = new Label("", skin);
        statusLabel.setColor(Color.RED);

        // random password button
        TextButton randomPassButton = new TextButton("?", skin);
        randomPassButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String randomPassword = generateRandomPassword();
                passwordField.setText(randomPassword);
                confirmPasswordField.setText(randomPassword);
            }
        });

        // register button
        TextButton registerButton = new TextButton("Register", skin);
        registerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.register(
                        null,
                        usernameField.getText().trim(),
                        passwordField.getText().trim(),
                        confirmPasswordField.getText().trim(),
                        nicknameField.getText().trim(),
                        emailField.getText().trim(),
                        genderSelect.getSelected().toLowerCase()
                );

                statusLabel.setText(result.message());
                statusLabel.clearActions();
                statusLabel.addAction(Actions.sequence(
                        Actions.delay(1f),
                        Actions.run(() -> statusLabel.setText(""))
                ));

                if (result.success()) {
                    showSecurityQuestionDialog();
                }
            }
        });

        TextButton goToLoginButton = new TextButton("Go to Login", skin);
        goToLoginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoginScreen(skin, game, app));
            }
        });

        formTable.padLeft(300).left();
        formTable.add(new Label("Username:", skin)).left().padBottom(10);
        formTable.add(usernameField).left().width(400).padLeft(10).padBottom(10).row();

        // password row with random button
        Table passwordRow = new Table();
        passwordRow.add(passwordField).width(400).padRight(20);
        passwordRow.add(randomPassButton).width(100);

        formTable.add(new Label("Password:", skin)).left().padBottom(10);
        formTable.add(passwordRow).left().padBottom(10).padLeft(10).row();

        formTable.add(new Label("Confirm:", skin)).left().padBottom(10);
        formTable.add(confirmPasswordField).left().width(400).padLeft(10).padBottom(10).row();

        formTable.add(new Label("Nickname:", skin)).left().padBottom(10);
        formTable.add(nicknameField).left().width(400).padLeft(10).padBottom(10).row();

        formTable.add(new Label("Email:", skin)).left().padBottom(10);
        formTable.add(emailField).left().width(400).padLeft(10).padBottom(10).row();

        formTable.add(new Label("Gender:", skin)).left().padBottom(20);
        formTable.add(genderSelect).left().width(400).padLeft(10).padBottom(10).row();

        Table buttonTable = new Table();
        buttonTable.add(registerButton).padRight(50);
        buttonTable.add(goToLoginButton);
        formTable.add(buttonTable).colspan(2).padTop(10).row();

        statusTable.add(statusLabel).right();

        stage.addActor(backgroundImage);
        stage.addActor(mainTable);
        mainTable.add(formTable).expand().left();
        stage.addActor(statusTable);
    }

    private String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        java.util.Random rand = new java.util.Random();

        String LOWER = "abcdefghijklmnopqrstuvwxyz";
        String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String DIGITS = "0123456789";
        String SPECIAL = "?><,\"';:\\/|][}{+=)(*&^%$#!";

        password.append(LOWER.charAt(rand.nextInt(LOWER.length())));
        password.append(UPPER.charAt(rand.nextInt(UPPER.length())));
        password.append(DIGITS.charAt(rand.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(rand.nextInt(SPECIAL.length())));

        String allChars = LOWER + UPPER + DIGITS + SPECIAL;
        for (int i = 4; i < 10; i++) {
            password.append(allChars.charAt(rand.nextInt(allChars.length())));
        }

        java.util.List<Character> passwordChars = new java.util.ArrayList<>();
        for (char c : password.toString().toCharArray()) {
            passwordChars.add(c);
        }
        java.util.Collections.shuffle(passwordChars);

        StringBuilder finalPassword = new StringBuilder();
        for (char c : passwordChars) {
            finalPassword.append(c);
        }

        return finalPassword.toString();
    }

    private void showSecurityQuestionDialog() {
        ArrayList<String> questions = controller.getSecurityQuestions();

        final SelectBox<String> questionBox = new SelectBox<>(skin);
        questionBox.setItems(questions.toArray(new String[0]));

        final TextField answerField = new TextField("", skin);
        answerField.setMessageText("Your answer");

        final TextField confirmAnswerField = new TextField("", skin);
        confirmAnswerField.setMessageText("Confirm answer");

        Dialog dialog = new Dialog("Security Question", skin) {
            @Override
            protected void result(Object obj) {
                if (obj instanceof Boolean && (Boolean) obj) {
                    int selectedIndex = questionBox.getSelectedIndex() + 1;
                    Result res = controller.pickQuestion(
                            selectedIndex,
                            answerField.getText().trim(),
                            confirmAnswerField.getText().trim()
                    );
                    if (res.success()) {
                        System.out.println("Registered successfully!");
                        game.setScreen(new MainMenuScreen(skin, game, app));
                        //app.switchToLobbyMenu();
                        dispose();
                    } else {
                        System.out.println(res.message());
                        showSecurityQuestionDialog();
                    }
                }
            }
        };

        Table content = dialog.getContentTable();
        content.defaults().pad(10);

        content.add(new Label("Choose a question:", skin)).row();
        content.add(questionBox).width(500).row();
        content.add(new Label("Answer:", skin)).row();
        content.add(answerField).width(350).row();
        content.add(new Label("Confirm answer:", skin)).row();
        content.add(confirmAnswerField).width(350).row();

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