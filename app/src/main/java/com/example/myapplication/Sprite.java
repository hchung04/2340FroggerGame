package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Sprite {

    private ImageView sprite;
    private int livesRemaining;

    public Sprite(ImageView sprite, Bitmap spriteImage, String level) {
        this.sprite = sprite;
        this.sprite.setImageBitmap(spriteImage);
        this.livesRemaining = setLives(level);
    }

    public void moveUp(float jump) {
        if (this.sprite.getTranslationY() - jump >= -10 * jump || sprite.getTranslationY() == 0) {
            float newY = this.sprite.getTranslationY() - jump;
            this.sprite.setTranslationY(newY);
        }
        checkWater();

    }

    public void moveDown(float jump) {
        if (this.sprite.getTranslationY() + jump <= -jump) {
            float newY = this.sprite.getTranslationY() + jump;
            this.sprite.setTranslationY(newY);
        }
//        checkWater();
    }

    public void moveRight(float jump) {
        if (this.sprite.getTranslationX() + jump <= 411) {
            float newX = this.sprite.getTranslationX() + jump;
            this.sprite.setTranslationX(newX);
        }
//        checkWater();
    }

    public void moveLeft(float jump) {
        if (this.sprite.getTranslationX() - jump >= -411) {
            float newX = this.sprite.getTranslationX() - jump;
            this.sprite.setTranslationX(newX);
        }
//        checkWater();
    }

    public boolean movedUp(float oldCoord, float newCoord) {
        return oldCoord != newCoord;
    }

    public void checkWater() {
        float x = this.sprite.getTranslationX();
        float y = this.sprite.getTranslationY();
        if (y == -300 || y == -1050 || y == -1200) {
            if (x != 0) {
                new CountDownTimer(100, 100) {
                    public void onFinish() {
                        resetToStart();
                    }
                    public void onTick(long millisUntilFinished) {}
                }.start();

            }
        } else if (y == -1500) {
            if (x == -300 || x == 300 || x == 450) {
                new CountDownTimer(100, 100) {
                    public void onFinish() {
                        resetToStart();
                    }
                    public void onTick(long millisUntilFinished) {}
                }.start();
            }
        }
    }

    public void resetToStart() {
        this.sprite.setTranslationX(0);
        this.sprite.setTranslationY(0);
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