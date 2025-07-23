package io.github.alexander_sazonov.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import io.github.alexander_sazonov.GameSettings;

public class Item extends GameObject {

    public enum ItemType {
        BAD,
        GOOD
    }

    public ItemType itemType;
    int speed;

    public Item(int x, int y, int width, int height, ItemType itemType, String texturePath, World world) {
        super(x, y, width, height, texturePath, world);
        this.itemType = itemType;
        this.speed = GameSettings.ITEM_MIN_SPEED + (new Random()).nextInt(GameSettings.ITEM_MAX_SPEED);
    }

    public void move() {
        body.setLinearVelocity(new Vector2(0, -speed));
    }


}
