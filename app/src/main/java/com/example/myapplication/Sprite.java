package com.example.myapplication;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class Sprite {

    private ImageView sprite;
    private int livesRemaining;

    public Sprite(ImageView sprite, Bitmap spriteImage, String level) {
        this.sprite = sprite;
        this.sprite.setImageBitmap(spriteImage);
        this.livesRemaining = setLives(level);
    }

    public Sprite() {

    }

    public float moveUp(float initYCoord, float jump) {
        if (initYCoord - jump >= -10 * jump || initYCoord == 0) {
            return initYCoord - jump;
        }
        return initYCoord;
    }
  
    public float moveDown(float initYCoord, float jump) {
        if (initYCoord + jump <= -jump) {
            return initYCoord + jump;
        }
        return initYCoord;
    }

    public float moveRight(float initXCoord, float jump) {
        if (initXCoord + jump <= 411) {
            return initXCoord + jump;
        }
        return initXCoord;
    }

    public float moveLeft(float initXCoord, float jump) {
        if (initXCoord - jump >= -411) {
            return initXCoord - jump;
        }
        return initXCoord;
    }

    public int setLives(String level) {
        if (level.equals("Easy")) {
            return 3;
        } else if (level.equals("Medium")) {
            return 2;
        } else {
            return 1;
        }
    }

    public int getLivesRemaining() {
        return this.livesRemaining;
    }

    public float getTranslationX() {
        return this.sprite.getTranslationX();
    }

    public float getTranslationY() {
        return this.sprite.getTranslationY();
    }

    public void setTranslationX(float x) {
        this.sprite.setTranslationX(x);
    }

    public void setTranslationY(float y) {
        this.sprite.setTranslationY(y);
    }
}