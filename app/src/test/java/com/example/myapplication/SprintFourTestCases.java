package com.example.myapplication;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SprintFourTestCases {
    private final float JUMP = 50;
    private final float exampleInitialY = -300;
    @Test
    public void scoreAfterCollision() {
        Score score = new Score();
        score.updateScore(-137); //score = 2
        assert(score.subtractScore() == 1);
    }


}
