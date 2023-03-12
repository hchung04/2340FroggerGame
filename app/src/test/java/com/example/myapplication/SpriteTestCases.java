package com.example.myapplication;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SpriteTestCases {
    private final float JUMP = 50;
    Sprite sprite = new Sprite();

    @Test
    public void movesUp(){
        float y = -137;
        assert(sprite.moveUp(y, JUMP) - y == -50);
    }

    @Test
    public void movesUpOutOfBounds() {
        float y = -10 * JUMP;
        assert(sprite.moveUp(y, JUMP)  == y);
    }

    @Test
    public void checkLives() {
        assert(sprite.setLives("Easy") == 3);
        assert(sprite.setLives("Medium") == 2);
        assert(sprite.setLives("Hard") == 1);
    }

    @Test
    public void checkStartingPoints() {
        assert(sprite.setStartingPoints("Easy") == 2);
        assert(sprite.setStartingPoints("Medium") == 1);
        assert(sprite.setStartingPoints("Hard") == 0);
    }

    @Test
    public void movesDown(){
        float y = -637;
        assert(sprite.moveDown(y, JUMP) - y == 50);
    }

    @Test
    public void movesDownOutOfBounds() {
        float y = -JUMP;
        assert (sprite.moveDown(y, JUMP) == y);

    }

    @Test

    public void movesRight(){
        float x = -411;
        assert(sprite.moveRight(x, JUMP) - x == 50);
    }

    @Test
    public void movesRightOutOfBounds() {
        float x = 411;
        assert(sprite.moveRight(x, JUMP)  == x);

    }

    @Test
    public void movesLeft() {
        float x = 411;
        assert(sprite.moveLeft(x, JUMP) - x == -50);
    }

    @Test
    public void movesLeftOutOfBounds() {
        float x = -411;
        assert (sprite.moveLeft(x, JUMP) == x);
    }

}

