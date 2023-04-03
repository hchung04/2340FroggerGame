package com.example.myapplication;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SprintFourTestCases {
    private final float JUMP = 50;
    private final float exampleInitialY = -300;
    @Test
    public void scoreAfterCollision() {
        Score score = new Score();
        score.updateScore(-137); //score = 2
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




}
