package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.widget.ImageView;

public class Sprite {

    private ImageView sprite;
    private int livesRemaining;


    public Sprite(ImageView sprite, Bitmap spriteImage, String level) {
        this.sprite = sprite;
        this.sprite.setImageBitmap(spriteImage);
        this.livesRemaining = setLives(level);
    }

    public Sprite(int livesRemaining) {
        this.livesRemaining = livesRemaining;
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
        if (this.sprite.getTranslationX() + jump <= 493) {
            float newX = this.sprite.getTranslationX() + jump;
            this.sprite.setTranslationX(newX);
        }
    }

    public void moveLeft(float jump) {
        if (this.sprite.getTranslationX() - jump >= -466) {
            float newX = this.sprite.getTranslationX() - jump;
            this.sprite.setTranslationX(newX);
        }
    }

    public boolean movedUp(float oldCoord, float newCoord) {
        return oldCoord != newCoord;
    }

    public boolean checkWater() {
        float x = this.sprite.getTranslationX();
        float y = this.sprite.getTranslationY();
        if (y == -274 || y == -959 || y == -1096) {
            return true;
        } else if (y == -1370) {
            if (x != -466 && x != -192 && x != 82 && x != 356) {
                return true;
            }
        }
        return false;
    }
    public boolean checkGoal() {
        float x = this.sprite.getTranslationX();
        float y = this.sprite.getTranslationY();
        if (y == -1370) {
            if (x == -466 || x == -192 || x == 82 || x == 356) {
                return true;
            }
        }
        return false;
    }

    public void resetToStart() {
        new CountDownTimer(100, 100) {
            public void onFinish() {
                setToZero();
            }
            public void onTick(long millisUntilFinished) {

            }
        }.start();
    }

    public void setToZero() {
        this.sprite.setTranslationX(-55);
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

    public void dealWithCollision(MovingObject collidedObject) {
        float[] newCoord = collidedObject.newCoordForCollision(this);
        setCoord(newCoord);
        if (collidedObject instanceof Vehicle) {
            subtractLife();
        }
    }

    public void setCoord(float[] newCoord) {
        sprite.setTranslationX(newCoord[0]);
        sprite.setTranslationY(newCoord[1]);
    }

    public void subtractLife() {
        this.livesRemaining -= 1;
    }

    public void resetLife() {
        this.livesRemaining++;
    }

    public boolean hasNoLives() {
        return this.livesRemaining == 0;
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

    //static method for Sprint 4 water collision test case
    public static boolean checkWater(float x, float y) {
        if (y == -274 || y == -959 || y == -1096) {
            if (x != -55) {
                return true;
            }
        } else if (y == -1370) {
            if (x == -329 || x == 219 || x == 493) {
                return true;
            }
        }
        return false;
    }

    // static method for Sprint 4 respawning test case
    public static float[] dealWithCollision() {
        float spritePosX = 0;
        float spritePosY = 0;
        float[] newPosition = new float[2];
        newPosition[0] = spritePosX;
        newPosition[1] = spritePosY;

        return newPosition;
    }

    // method for Sprint 4 collision life test cases
    public int subtractLife(int livesRemaining) {
        livesRemaining--;
        return livesRemaining;
    }

    //method for Sprint 5 test case
    public static boolean hasEnoughLivesToWin(int livesRemaining) {
        if (livesRemaining >= 1) {
            return true;
        }
        return false;
    }

    public boolean isOffScreen() {
        return this.sprite.getTranslationX() + 50 > 493 || this.sprite.getTranslationX() + 50 < -466;
    }

    //test case
    public static boolean isOffScreen(float x, float y) {
        return x + 50 > 493 || y + 50 < -466;
    }


}