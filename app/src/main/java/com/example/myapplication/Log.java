package com.example.myapplication;

import android.widget.ImageView;

public class Log extends MovingObject {

    ImageView log;

    public Log (ImageView log, float startingX, float startingY, float scaleX, float scaleY) {
        super(log, startingX, startingY, scaleX, scaleY);
    }

    // for testing
    public Log (float startingX, float startingY) {
        super(startingX, startingY);
    }
}
