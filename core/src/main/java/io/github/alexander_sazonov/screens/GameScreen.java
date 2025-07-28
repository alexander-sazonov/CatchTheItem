package io.github.alexander_sazonov.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.github.alexander_sazonov.GameResources;
import io.github.alexander_sazonov.GameSession;
import io.github.alexander_sazonov.GameSettings;
import io.github.alexander_sazonov.MyGdxGame;
import io.github.alexander_sazonov.objects.Hero;
import io.github.alexander_sazonov.objects.Item;
import io.github.alexander_sazonov.ui.Button;
import io.github.alexander_sazonov.ui.Label;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class GameScreen implements Screen {
    Hero hero;
    List<Item> items;
    MyGdxGame game;
    Label livesLabel;
    Label pointsLabel;
    Label timerLabel;
//    Label gameOverLabel;
//    Button restartButton;
    GameSession gameSession;

    public GameScreen(MyGdxGame game) {
        this.game = game;
        hero = new Hero(
            Gdx.graphics.getWidth() / 2,
            100,
            66,
            92,
            GameResources.HERO_IMG,
            game.world
        );
        gameSession = new GameSession(hero);
        items = new ArrayList<>();
        livesLabel = new Label(
            600f,
            1200f,
            String.format("LIVES: %d", hero.getLives()),
            game.labelFont
        );
        pointsLabel = new Label(
            600f,
            1170f,
            String.format("POINTS: %d", hero.getPoints()),
            game.labelFont
        );
        timerLabel = new Label(
            50,
            1200,
            String.format("TIME: %d", gameSession.getGameTime()),
            game.labelFont
        );
//        gameOverLabel = new Label(
//            Gdx.graphics.getWidth() / 2 - 210,
//            Gdx.graphics.getHeight() / 2 - 50,
//            game.gameOverFont
//        );
//        restartButton = new Button((float)(Gdx.graphics.getWidth() / 2) - 95,
//            (float)(Gdx.graphics.getHeight() / 2) - 122, 190, 45, game.buttonFont, "Restart");
    }

    @Override
    public void show() {
        // Prepare your screen here.
        gameSession.restartGame();
        items.clear();
        items = addItems();
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        if (gameSession.gameState == GameSession.GameState.ENDED){
            game.setScreen(game.gameOverScreen);
        }
        handleInput();
        updateLabels();
        updateItems();
        gameSession.updateSession();
        game.stepWorld();
        draw();
    }

    private void draw() {
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        ScreenUtils.clear(Color.CLEAR);
        game.batch.begin();
//        switch (gameSession.gameState) {
//            case PLAYING:
                livesLabel.draw(game.batch);
                pointsLabel.draw(game.batch);
                timerLabel.draw(game.batch);
                hero.draw(game.batch);
                for (Item item : items) {
                    item.draw(game.batch);
                    item.move();
                }
//                break;
//            case ENDED:
//              ScreenUtils.clear(Color.CLEAR);
//                gameOverLabel.setText(String.format("Game Over\nPoints: %d", hero.getPoints()));
//                gameOverLabel.draw(game.batch);
//                restartButton.draw(game.batch);
//                break;
//        }

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

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector3 touch = game.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            float x = touch.x;
            float y = touch.y;
//            switch (gameSession.gameState) {
//                case PLAYING:
                    if (x <= hero.getX()) {
                        hero.moveLeft();
                    } else {
                        hero.moveRight();
                    }
//                    break;
//                case ENDED:
//                    if (restartButton.isPressed(x,y)){
//                        System.out.println("Restart");
//                        gameSession.restartGame();
////                        items = addItems();
//                    }
//                    break;
//            }

        } else {
            hero.stop();
        }
    }

    private List<Item> addItems() {
        Random rnd = new Random();
        for (int i = 0; i < GameSettings.ITEMS_COUNT; i++) {
            int item_size = 70;
            int x = item_size / 2 + rnd.nextInt(GameSettings.SCREEN_WIDTH - item_size / 2);
            int y = GameSettings.SCREEN_HEIGHT + item_size / 2;
            int typeNumber = rnd.nextInt(2);
            Item.ItemType itemType;
            String texturePath;
            switch (typeNumber) {
                case 0:
                    itemType = Item.ItemType.GOOD;
                    texturePath = GameResources.GOOD_ITEM_IMG;
                    break;
                case 1:
                    itemType = Item.ItemType.BAD;
                    texturePath = GameResources.BAD_ITEM_IMG;
                    break;
                default:
                    itemType = Item.ItemType.GOOD;
                    texturePath = GameResources.GOOD_ITEM_IMG;
            }
            Item item = new Item(x, y, item_size, item_size, itemType, texturePath, game.world);
            items.add(item);
        }
        return items;
    }

    private boolean isItemInScreen(Item item) {
        return item.getY() > -item.height / 2;
    }

    private void moveItemToStart(Item item) {
        Random rnd = new Random();
        item.setTouched(false);
        int x = item.width / 2 + rnd.nextInt(GameSettings.SCREEN_WIDTH - item.width / 2);
        int y = GameSettings.SCREEN_HEIGHT + item.height / 2;
        item.setX(x);
        item.setY(y);
    }

    private void updateItems() {
//        switch (gameSession.gameState) {
//            case PLAYING:
                for (Item item : items) {
                    if (!isItemInScreen(item) || item.isTouched()) {
                        moveItemToStart(item);
                    }
                }
//                break;
//            case ENDED:
//                items.clear();
//                break;
//        }

    }

    private void updateLabels() {
        livesLabel.setText(String.format("LIVES: %d", hero.getLives()));
        pointsLabel.setText(String.format("POINTS: %d", hero.getPoints()));
        timerLabel.setText(String.format("TIME: %d", gameSession.getGameTime()));
    }
}
