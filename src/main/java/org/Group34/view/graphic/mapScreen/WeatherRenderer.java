package org.Group34.view.graphic.mapScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class WeatherRenderer {
    private static final float WEATHER_INTERVAL = 0.05f;
    private float weatherTimer = 0;
    private final Texture particleTexture;

    public WeatherRenderer() {
        // Load a simple texture for weather particles
        particleTexture = new Texture(Gdx.files.internal("tiles/grass_0.png"));
    }

    public void render(SpriteBatch batch, float delta, EnvironmentManager environmentManager) {
        weatherTimer += delta;
        if (weatherTimer < WEATHER_INTERVAL) {
            return;
        }
        weatherTimer = 0;

        Random random = new Random();
        String weather = environmentManager.getCurrentWeather();

        if (weather.equalsIgnoreCase("RAIN")) {
            renderRain(batch, random);
        } else if (weather.equalsIgnoreCase("SNOW")) {
            renderSnow(batch, random);
        } else if (weather.equalsIgnoreCase("STORM")) {
            renderStorm(batch, random);
        }
    }

    private void renderRain(SpriteBatch batch, Random random) {
        for (int i = 0; i < 200; i++) {
            float x = random.nextInt(Gdx.graphics.getWidth());
            float y = (random.nextInt(Gdx.graphics.getHeight()) + (Gdx.graphics.getDeltaTime() * 500)) % Gdx.graphics.getHeight();
            float length = random.nextFloat() * 15 + 10;
            float alpha = random.nextFloat() * 0.5f + 0.3f;
            batch.setColor(0.7f, 0.7f, 0.9f, alpha);
            batch.draw(particleTexture, x, y, 1, length);
        }
        batch.setColor(Color.WHITE);
    }

    private void renderSnow(SpriteBatch batch, Random random) {
        for (int i = 0; i < 120; i++) {
            float x = (random.nextInt(Gdx.graphics.getWidth()) + (random.nextFloat() * 20 - 10) * Gdx.graphics.getDeltaTime());
            float y = (random.nextInt(Gdx.graphics.getHeight()) + (Gdx.graphics.getDeltaTime() * 200) % Gdx.graphics.getHeight());
            float size = random.nextFloat() * 4 + 1;
            float alpha = random.nextFloat() * 0.7f + 0.3f;
            batch.setColor(1.0f, 1.0f, 1.0f, alpha);
            batch.draw(particleTexture, x, y, size, size);
        }
        batch.setColor(Color.WHITE);
    }

    private void renderStorm(SpriteBatch batch, Random random) {
        for (int i = 0; i < 250; i++) {
            float x = (random.nextInt(Gdx.graphics.getWidth()) + (Gdx.graphics.getDeltaTime() * 300));
            float y = (random.nextInt(Gdx.graphics.getHeight()) + (Gdx.graphics.getDeltaTime() * 600)) % Gdx.graphics.getHeight();
            float length = random.nextFloat() * 20 + 10;
            float alpha = random.nextFloat() * 0.6f + 0.2f;
            batch.setColor(0.4f, 0.4f, 0.5f, alpha);
            batch.draw(particleTexture, x, y, 2, length);
        }

        if (random.nextFloat() < 0.02f) { // 2% chance of lightning
            batch.setColor(1.0f, 1.0f, 1.0f, 0.8f);
            batch.draw(particleTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.setColor(Color.WHITE);
        }
    }

    public void dispose() {
        particleTexture.dispose();
    }
}