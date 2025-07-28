package io.github.alexander_sazonov.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.alexander_sazonov.GameResources;

public class Button extends View {

    Texture texture;
    BitmapFont bitmapFont;
    String text;
    float textX;
    float textY;

    public Button(float x, float y, float width, float height, BitmapFont bitmapFont, String text) {
        super(x, y, width, height);
        this.text = text;
        this.bitmapFont = bitmapFont;
        texture = new Texture(GameResources.BUTTON_PATH);
        GlyphLayout glyphLayout = new GlyphLayout(bitmapFont, text);
        float textWidth = glyphLayout.width;
        float textHeight = glyphLayout.height;
        textX = x + (width - textWidth) / 2;
        textY = y + (height + textHeight) / 2;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height);
        bitmapFont.draw(batch, text, textX, textY);
    }

    @Override
    public void dispose() {
        texture.dispose();
        bitmapFont.dispose();
    }
}
