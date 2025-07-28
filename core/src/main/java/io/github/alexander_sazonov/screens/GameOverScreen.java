package io.github.alexander_sazonov.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.alexander_sazonov.MyGdxGame;
import io.github.alexander_sazonov.objects.Item;
import io.github.alexander_sazonov.ui.Button;
import io.github.alexander_sazonov.ui.Label;

public class GameOverScreen implements Screen {
    Label gameOverLabel;
    Button restartButton;
    MyGdxGame game;

    public GameOverScreen(MyGdxGame game) {
        this.game = game;
        gameOverLabel = new Label(Gdx.graphics.getWidth() / 2 - 210, Gdx.graphics.getHeight() / 2,
            "Game Over", game.gameOverFont);
        restartButton = new Button((float)(Gdx.graphics.getWidth() / 2) - 95,
            (float)(Gdx.graphics.getHeight() / 2) - 122, 190, 45, game.buttonFont, "Restart");
    }

    @Override
    public void show() {

    }

    private void draw() {
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        ScreenUtils.clear(Color.CLEAR);
        game.batch.begin();
        gameOverLabel.draw(game.batch);
        restartButton.draw(game.batch);
        game.batch.end();
    }

    private void handleInput(){
        if (Gdx.input.justTouched()){
            Vector3 touch = game.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (restartButton.isPressed(touch.x, touch.y)){
                game.setScreen(game.gameScreen);
            }
        }
    }

    @Override
    public void render(float delta) {
        handleInput();
        draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
