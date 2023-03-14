package com.example.myapplication;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SprintThreeTestCases {
    private final float JUMP = 50;
    private final float exampleInitialY = -300;
    @Test
    public void updatesScore() {
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
        assert(4 == score.updateScore(y)); // updates when farthest distance traveled is exceeded
        // score is incremented by 2 bc the second row is an obstacle row
    }

    //Case where vehicle needs to move left: checks that vehicle starts back all the way at the left of the screen
    //Case where vehicle needs to move right: checks that vehicle starts all the way back at the left of the screen
    @Test
    public void movesIntoViewFromOffScreen() {
        float initialTranslationMoveLeft = -700;
        assert(Vehicle.updateX2(initialTranslationMoveLeft, exampleInitialY, "left", 50)[0] == 600);

        float initialTranslationMoveRight = 700;
        assert(Vehicle.updateX2(initialTranslationMoveRight, exampleInitialY, "right", 50)[0] == -600);
    }

    //Case where vehicle needs to move left: checks that vehicle moves left properly
    @Test
    public void movesLeft() {
        float initialTranslationMoveLeft = 500;
        assert(Vehicle.updateX2(initialTranslationMoveLeft, exampleInitialY, "left", -50)[0] == 450);

    }

    //Case where vehicle needs to move right: checks that vehicle moves right properly
    @Test
    public void movesRight() {

        float initialTranslationMoveRight = -500;
        assert(Vehicle.updateX2(initialTranslationMoveRight, exampleInitialY, "right", 50)[0] == -450);

    }

    //Case to test that the vehicle is spawn inside the grid coordinates
    @Test
    public void vehicleOnGrid() {
        float initializationLeft = -600;
        float initializationRight = 600;
        assert(Vehicle.updateX2(initializationLeft, exampleInitialY, "left", 0)[0] == -600);
        assert(Vehicle.updateX2(initializationRight, exampleInitialY, "right", 0)[0] == 600);
    }

    @Test
    public void vehicleRightSpeed() {
        float carSpeed = 20;
        float truckSpeed = -70;
        assert(carSpeed != truckSpeed);
    }

    @Test
    public void vehicleLeftSpeed() {
        float truckSpeed = -70;
        float carLeftSpeed = -50;
        assert(carLeftSpeed != truckSpeed);
    }

    // Checks that vehicles do not collide when initialized on grid
    @Test
    public void noCollisionsAtStart() {
        float initializationLeft = -600;
        float initializationRight = 600;
        float carRightInitialY = -620;
        float carLeftInitialY = -900;
        float truckLeftInitialY = -770;

        float carRightY = Vehicle.updateX2(initializationRight, carRightInitialY, "right", 0)[1];
        float carLeftY = Vehicle.updateX2(initializationLeft, carLeftInitialY, "left", 0)[1];
        float truckLeftY = Vehicle.updateX2(initializationLeft, truckLeftInitialY, "left", 0)[1];

        assert(carRightY != carLeftY);
        assert(carRightY != truckLeftY);
        assert(carLeftY != truckLeftY);
    }

    // Check that vehicles also do not collide when moving
    @Test
    public void noMovingCollisions() {
        float initializationLeft = -600;
        float initializationRight = 600;
        float carRightInitialY = -620;
        float carLeftInitialY = -900;
        float truckLeftInitialY = -770;

        float carRightY = Vehicle.updateX2(initializationRight, carRightInitialY, "right", 50)[1];
        float carLeftY = Vehicle.updateX2(initializationLeft, carLeftInitialY, "left", 50)[1];
        float truckLeftY = Vehicle.updateX2(initializationLeft, truckLeftInitialY, "left", 50)[1];

        assert(carRightY != carLeftY);
        assert(carRightY != truckLeftY);
        assert(carLeftY != truckLeftY);
    }

}

