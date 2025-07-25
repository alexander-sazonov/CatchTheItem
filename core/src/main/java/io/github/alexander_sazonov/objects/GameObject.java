package io.github.alexander_sazonov.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import io.github.alexander_sazonov.GameSettings;

public abstract class GameObject {
    private int x;
    private int y;
    public int width;
    public int height;
    protected Texture texture;
    protected Body body;

    public GameObject(int x, int y, int width, int height, String texturePath, World world) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.body = createBody(x,y,world);
        texture = new Texture(texturePath);
    }

    private Body createBody(float x, float y, World world){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        Body body = world.createBody(def);
        CircleShape shape = new CircleShape();
        shape.setRadius(Math.max(width, height) * GameSettings.SCALE / 2f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 1f;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        body.setTransform(x * GameSettings.SCALE, y * GameSettings.SCALE, 0);
        return body;
    }

    public int getX() {
        return (int) (body.getPosition().x / GameSettings.SCALE);
    }

    public int getY() {
        return (int) (body.getPosition().y / GameSettings.SCALE);
    }

    public void setX(int x) {
        body.setTransform(x * GameSettings.SCALE, body.getPosition().y, 0);
    }

    public void setY(int y) {
        body.setTransform(body.getPosition().x, y * GameSettings.SCALE, 0);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, getX() - (width/2f), getY() - (height/2f), width, height);
    }

    public abstract void hit(GameObject gameObject);

}
