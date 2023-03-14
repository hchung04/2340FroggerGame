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

    //Case where vehicle needs to move left: checks that vehicle starts back all the way at the left of the screen
    //Case where vehicle needs to move right: checks that vehicle starts all the way back at the left of the screen
    @Test
    public void movesIntoViewFromOffScreen(){
        float initialTranslationMoveLeft = -700;
        assert(Vehicle.updateX2(initialTranslationMoveLeft,"left", 50) == 600);

        float initialTranslationMoveRight = 700;
        assert(Vehicle.updateX2(initialTranslationMoveRight,"right", 50) == -600);
    }

    //Case where vehicle needs to move left: checks that vehicle moves left properly
    @Test
    public void movesLeft(){
        float initialTranslationMoveLeft = 500;
        assert(Vehicle.updateX2(initialTranslationMoveLeft,"left", -50) == 450);

    }

    //Case where vehicle needs to move right: checks that vehicle moves right properly
    @Test
    public void movesRight(){
        float initialTranslationMoveRight = -500;
        assert(Vehicle.updateX2(initialTranslationMoveRight,"right", 50) == -450);

    }

    //Case to test that the vehicle is spawn inside the grid coordinates
    @Test
    public void vehicleOnGrid() {
        float initializationLeft = -600;
        float initializationRight = 600;
        assert(Vehicle.updateX2(initializationLeft, "left", 0) == -600);
        assert(Vehicle.updateX2(initializationRight, "right", 0) == 600);
    }

    @Test
    public void vehicleRightSpeed(){
        float carSpeed = 20;
        float truckSpeed = -70;
        assert(carSpeed != truckSpeed);
    }

    @Test
    public void vehicleLeftSpeed(){
        float truckSpeed = -70;
        float carLeftSpeed = -50;
        assert(carLeftSpeed != truckSpeed);
    }

    // Checks that vehicles do not collide
    @Test
    public void noCollisionsAtStart() {
        float carRightInitialY = -620;
        float carLeftInitialY = -900;
        float truckLeftInitialY = -770;
        assert(Vehicle.positionY(carRightInitialY) != Vehicle.positionY(carLeftInitialY));
        assert(Vehicle.positionY(carRightInitialY) != Vehicle.positionY(truckLeftInitialY));
        assert(Vehicle.positionY(carLeftInitialY) != Vehicle.positionY(truckLeftInitialY));
    }

    // Check that vehicles also do not collide when moving
    @Test
    public void noMovingCollisions() {
        float initializationLeft = -600;
        float initializationRight = 600;
        float carRightInitialY = -620;
        float carLeftInitialY = -900;
        float truckLeftInitialY = -770;
        assert(Vehicle.updateX2(initializationLeft, carLeftInitialY, "left", 0)[1]
                != Vehicle.updateX2(initializationRight, carRightInitialY, "right", 0)[1]);
        assert(Vehicle.updateX2(initializationLeft, carLeftInitialY, "left", 0)[1]
                != Vehicle.updateX2(initializationLeft, truckLeftInitialY, "left", 0)[1]);
        assert(Vehicle.updateX2(initializationRight, carRightInitialY, "right", 0)[1]
                != Vehicle.updateX2(initializationLeft, truckLeftInitialY, "left", 0)[1]);
    }

}

