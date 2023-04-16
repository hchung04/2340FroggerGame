package com.example.myapplication;

import android.graphics.Bitmap;

import org.junit.Test;

public class SprintFiveTestCases {
    private final float JUMP = -137;

    @Test
    public void wonGameWithLivesLeft(){
        assert (Sprite.hasEnoughLivesToWin(3));
        assert (Sprite.hasEnoughLivesToWin(2));
        assert (Sprite.hasEnoughLivesToWin(1));
        assert (!Sprite.hasEnoughLivesToWin(0));
    }

    //Checks if log moves left properly
    @Test
    public void logMovesLeft() {
        Log log = new Log(600, 0);
        assert(log.updateX('L', 30) == 570);
    }

    //Checks if log will respawn on the right side of the screen after going off screen to the left
    @Test
    public void logMovesOffScreenLeft() {
        Log log = new Log(-600, 0);
        assert(log.updateX('L', 30) == 600);
    }

    //Checks if log moves right properly
    @Test
    public void logMovesRight() {
        Log log = new Log(-600, 0);
        assert(log.updateX('R', 30) == -570);
    }

    //Checks if log will respawn on the left side of the screen after going off screen to the right
    @Test
    public void logMovesOffScreenRight() {
        Log log = new Log(600, 0);
        assert(log.updateX('R', 30) == -600);
    }

    //Checks to see if landing on a goal will award the highest amount of points
    @Test
    public void scoreAfterGoal() {
        Score score = new Score();
        score.updateScore(JUMP); //score = 2
        assert(score.addGoalScore() == 12);
    }

    //Checks to see if it will switch to GameWonScreen if livesRemaining is >= 1
    @Test
    public void gameWonScreen(){
        int livesRemaining = 2;
        Sprite sprite = new Sprite(livesRemaining);
        livesRemaining = sprite.subtractLife(livesRemaining); // lives = 1
        assert(GameActivity.switchToGameWonScreen(livesRemaining) == true);
    }


}
