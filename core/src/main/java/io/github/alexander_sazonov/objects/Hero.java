package io.github.alexander_sazonov.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import io.github.alexander_sazonov.GameSettings;

public class Hero extends GameObject {

    private int lives = GameSettings.HERO_LIVES;
    private int points = 0;


    public Hero(int x, int y, int width, int height, String texturePath, World world) {
        super(x, y, width, height, texturePath, world);
    }

    @Override
    public void hit(GameObject gameObject) {
        if (gameObject instanceof Item) {
            Item item = (Item) gameObject;
            switch (item.itemType) {
                case GOOD:
                    points += 1;
                    break;
                case BAD:
                    setLives(getLives() - 1);
                    break;
            }
        }
    }

    public void moveLeft() {
        body.setLinearVelocity(new Vector2(-10, 0));
    }

    public void moveRight() {
        body.setLinearVelocity(new Vector2(10, 0));
    }

    public void stop() {
        body.setLinearVelocity(new Vector2(0, 0));
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        if (lives >= 0) {
            this.lives = lives;
        }
    }

    public int getPoints() {
        return points;
    }

    public boolean isAlive() {
        return lives > 0;
    }
}
