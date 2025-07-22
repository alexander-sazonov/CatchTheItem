package io.github.alexander_sazonov.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Hero extends GameObject {
    public Hero(int x, int y, int width, int height, String texturePath, World world) {
        super(x, y, width, height, texturePath, world);
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
}
