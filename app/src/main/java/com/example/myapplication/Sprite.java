package com.example.myapplication;

import android.graphics.Bitmap;
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

    }

    public void moveDown(float jump) {
        if (this.sprite.getTranslationY() + jump <= -jump) {
            float newY = this.sprite.getTranslationY() + jump;
            this.sprite.setTranslationY(newY);
        }
    }

    public void moveRight(float jump) {
        if (this.sprite.getTranslationX() + jump <= 411) {
            float newX = this.sprite.getTranslationX() + jump;
            this.sprite.setTranslationX(newX);
        }
    }

    public void moveLeft(float jump) {
        if (this.sprite.getTranslationX() - jump >= -411) {
            float newX = this.sprite.getTranslationX() - jump;
            this.sprite.setTranslationX(newX);
        }
    }

    public boolean movedUp(float oldCoord, float newCoord) {
        return oldCoord != newCoord;
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

    public void dealWithCollision(MovingObject collidedObject) {
        float[] newCoord = collidedObject.newCoordForCollision();
        setCoord(newCoord);
        decreaseLives();
    }

    public void setCoord(float[] newCoord) {
        sprite.setTranslationX(newCoord[0]);
        sprite.setTranslationX(newCoord[1]);
    }

    public void decreaseLives() {
        livesRemaining--;
    }

    public int getLivesRemaining() {
        return this.livesRemaining;
    }

    public float getX() {
        return this.sprite.getX();
    }

    public float getY() {
        return this.sprite.getY();
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