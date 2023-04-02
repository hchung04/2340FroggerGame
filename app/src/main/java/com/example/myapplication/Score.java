package com.example.myapplication;

public class Score {
    private float maxDistance;
    private int score;

    private final int TILE = 137;

    public Score() {
        maxDistance = 0; //fill w/ starting y coord
        this.score = 0;
    }

    public int updateScore(float yCoord) {
        if (yCoord < maxDistance) {
            maxDistance = yCoord;
            int row = -1 * (int) (yCoord / TILE);
            if (row == 1) { //modify these row values depending on where cars go
                this.score += 2;
            } else if (row == 2) {
                this.score += 3;
            } else if (row == 3) {
                this.score += 4;
            } else {
                this.score++;
            }
        }
        return this.score;
    }

    public int subtractScore() {
        this.score /= 2;
        return this.score;
    }

    public int getScore() {
        return this.score;
    }
}