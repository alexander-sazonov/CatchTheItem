package io.github.alexander_sazonov.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public abstract class View implements Disposable {
    float x;
    float y;
    float width;
    float height;


    public View(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public View(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean isPressed(float pressX, float pressY){
        return (pressX >= x && pressX <= x + width && pressY >= y && pressY <= y + height);
    }

    public abstract void draw(SpriteBatch batch);

}
