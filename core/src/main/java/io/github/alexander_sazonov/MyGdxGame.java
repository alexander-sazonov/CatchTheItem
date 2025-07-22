package io.github.alexander_sazonov;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import io.github.alexander_sazonov.screens.GameScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class MyGdxGame extends Game {

    public World world;
    public OrthographicCamera camera;
    public SpriteBatch batch;
    float accumulator = 0;

    @Override
    public void create() {
        Box2D.init();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
        batch = new SpriteBatch();
        world = new World(new Vector2(0, 0), false);
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();
        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= GameSettings.STEP_TIME) {
            accumulator -= GameSettings.STEP_TIME;
            world.step(
                GameSettings.STEP_TIME,
                GameSettings.VELOCITY_ITERATIONS,
                GameSettings.POSITION_ITERATIONS);
        }
    }

}
