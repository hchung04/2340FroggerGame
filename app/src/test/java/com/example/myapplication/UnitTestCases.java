package com.example.myapplication;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTestCases{
    private final float JUMP = 50;

    @Test
    public void movesUp(){
        float y = -137;
        assert(Sprite.moveUp(y, JUMP) - y == -50);
    }

    @Test
    public void movesUpOutOfBounds() {
        float y = -10 * JUMP;
        assert(Sprite.moveUp(y, JUMP)  == y);

    }

    @Test
    public void movesDown(){
        float y = -637;
        assert(Sprite.moveDown(y, JUMP) - y == 50);
    }

    @Test
    public void movesDownOutOfBounds() {
        float y = -JUMP;
        assert (Sprite.moveDown(y, JUMP) == y);

    }

    @Test
    public void movesRight(){
        float x = -411;
        assert(Sprite.moveRight(x, JUMP) - x == 50);
    }

    @Test
    public void movesRightOutOfBounds() {
        float x = 411;
        assert(Sprite.moveRight(x, JUMP)  == x);

    }


}

