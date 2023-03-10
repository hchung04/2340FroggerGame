package com.example.myapplication;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SprintThreeTestCases {
    private final float JUMP = 50;
    @Test
    public void updatesScore(){
        Score score = new Score();
        float y = 0;
        assert(0 == score.getScore());
        y -= JUMP;
        assert(1 == score.updateScore(y));
        y -= JUMP;
        assert(2 == score.updateScore(y));
    }

    @Test
    public void doNotUpdateScore() {
        Score score = new Score();
        float y = 0;
        assert(0 == score.getScore());
        y -= JUMP;
        score.updateScore(y); //have to update jumps individually to simulate the game
        y -= JUMP;
        assert(2 == score.updateScore(y));
        y += JUMP;
        assert(2 == score.updateScore(y)); //no points for moving backward
        y += JUMP;
        assert(2 == score.updateScore(y)); //back at starting position
        y -= JUMP;
        assert(2 == score.updateScore(y)); //moves forward but points aren't added
        y -= JUMP;
        assert(2 == score.updateScore(y)); // at farthest distance traveled
        y -= JUMP;
        assert(3 == score.updateScore(y)); // updates when farthest distance traveled is exceeded
    }

}

