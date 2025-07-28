package io.github.alexander_sazonov;

import com.badlogic.gdx.utils.TimeUtils;

import io.github.alexander_sazonov.objects.Hero;

public class GameSession {
    public enum GameState {
        ENDED,
        PAUSED,
        PLAYING
    }

    long startTime;
    long pauseStartTime;
    int gameTime;
    Hero hero;

    public GameState gameState;

    public GameSession(Hero hero) {
        this.hero = hero;
        startTime = TimeUtils.millis();
        gameState = GameState.PLAYING;
    }

    public int getGameTime() {
        gameTime = (int) (TimeUtils.millis() - startTime) / 1000;
        return gameTime;
    }

    private boolean isTimeEnd() {
        return gameTime >= GameSettings.GAME_MAX_TIME;
    }

    public void updateSession() {
        switch (gameState){
            case PLAYING:
                if (isTimeEnd() || !hero.isAlive()){
                    gameState = GameState.ENDED;
                    break;
                }
        }
    }
    public void restartGame(){
        hero.setLives(GameSettings.HERO_LIVES);
        hero.setPoints(0);
        startTime = TimeUtils.millis();
        gameState = GameState.PLAYING;
    }

}
