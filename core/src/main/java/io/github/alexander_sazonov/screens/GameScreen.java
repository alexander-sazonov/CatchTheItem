package io.github.alexander_sazonov.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.alexander_sazonov.GameResources;
import io.github.alexander_sazonov.MyGdxGame;
import io.github.alexander_sazonov.objects.Hero;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class GameScreen implements Screen {
    Hero hero;
    MyGdxGame game;

    public GameScreen(MyGdxGame game) {
        this.game = game;
        hero = new Hero(
            Gdx.graphics.getWidth() / 2,
            50,
            66,
            92,
            GameResources.HERO_IMG,
            game.world
        );
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        ScreenUtils.clear(Color.CLEAR);
        game.batch.begin();
        hero.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if (width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
