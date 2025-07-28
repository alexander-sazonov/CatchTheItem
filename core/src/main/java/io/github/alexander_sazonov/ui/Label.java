package io.github.alexander_sazonov.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Label extends View {

    private String text;
    private BitmapFont bitmapFont;

    public Label(float x, float y, BitmapFont bitmapFont) {
        super(x, y);
        this.bitmapFont = bitmapFont;
    }

    public Label(float x, float y, String text, BitmapFont bitmapFont) {
        super(x, y);
        this.text = text;
        this.bitmapFont = bitmapFont;
        GlyphLayout glyphLayout = new GlyphLayout(this.bitmapFont, this.text);
        width = (int) glyphLayout.width;
        height = (int) glyphLayout.height;

    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        GlyphLayout glyphLayout = new GlyphLayout(bitmapFont, this.text);
        width = (int) glyphLayout.width;
        height = (int) glyphLayout.height;
    }

    public BitmapFont getBitmapFont() {
        return bitmapFont;
    }

    public void setBitmapFont(BitmapFont bitmapFont) {
        this.bitmapFont = bitmapFont;
    }

    @Override
    public void draw(SpriteBatch batch) {
        bitmapFont.draw(batch, text, x, y + height);
    }

    @Override
    public void dispose() {
        bitmapFont.dispose();
    }
}
