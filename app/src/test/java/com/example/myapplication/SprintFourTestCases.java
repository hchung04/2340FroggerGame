package com.example.myapplication;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SprintFourTestCases {
    private final float JUMP = -137;

    @Test
    public void scoreAfterCollision() {
        Score score = new Score();
        score.updateScore(JUMP); //score = 2
        assert(score.subtractScore() == 1);
    }

    @Test
    public void checkWaterTiles() {
        assert (Sprite.checkWater(82, -274));
        assert (Sprite.checkWater(219, -959));
        assert (Sprite.checkWater(493, -1096));
        assert (Sprite.checkWater(-329, -1370));
        assert (Sprite.checkWater(219, -1370));
        assert (Sprite.checkWater(493, -1370));
    }

    @Test
    public void checkHighScore() {
        int score = 10;
        int highScore = 0;
        assert(GameOverActivity.saveNewHighScore(score, highScore) == 10);
    }

    @Test
    public void respawnedAtStart() {
        float[] newPosition = Sprite.dealWithCollision();
        float newX = newPosition[0];
        float newY = newPosition[1];
        assert(newX == 0);
        assert(newY == 0);
    }

    @Test
    public void waterCollisionLife(){
        int livesRemaining = 2;
        Sprite sprite = new Sprite(livesRemaining);
        Score score = new Score();
        score.updateScore(2 * JUMP); // move up 2 rows
        assert(sprite.subtractLife(sprite.getLivesRemaining()) == 1);
    }

    @Test
    public void vehicleCollisionLife(){
        int livesRemaining = 2;
        Sprite sprite = new Sprite(livesRemaining);
        Score score = new Score();
        score.updateScore(4 * JUMP); // vehicles on 4th, 5th, and 6th rows
        score.updateScore(5 * JUMP);
        score.updateScore(6 * JUMP);
        assert(sprite.subtractLife(sprite.getLivesRemaining()) == 1);
    }

    @Test
    public void scoreRespawn(){
        Score score = new Score();
        score.updateScore(JUMP); //score = 2
        float[] newPosition = Sprite.dealWithCollision();
        float newX = newPosition[0];
        float newY = newPosition[1];
        assert(newX == 0);
        assert(newY == 0);
        assert(score.subtractScore() == 1);
    }

    @Test
    public void gameOverScreen(){
        int livesRemaining = 1;
        Sprite sprite = new Sprite(livesRemaining);
        livesRemaining = sprite.subtractLife(livesRemaining); // lives = 0
        assert(GameActivity.switchToGameOverScreen(livesRemaining) == true);
    }

    @Test
    public void waterCollisionScore(){
        Score score = new Score();
        score.updateScore(JUMP);
        score.updateScore(2 * JUMP); //score = 5
        assert(score.subtractScore() == 2);
    }

    @Test
    public void vehicleCollisionScore(){
        Score score = new Score();
        score.updateScore(4 * JUMP); // vehicles on 4th, 5th, and 6th rows
        score.updateScore(5 * JUMP);
        score.updateScore(6 * JUMP); // score = 3
        assert(score.subtractScore() == 1);
    }


}