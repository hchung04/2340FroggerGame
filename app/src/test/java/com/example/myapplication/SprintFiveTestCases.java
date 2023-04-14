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

}
