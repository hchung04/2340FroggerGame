package com.example.myapplication;

import android.graphics.Bitmap;

import org.junit.Test;

public class SprintFiveTestCases {

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

}
