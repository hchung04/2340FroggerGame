package com.example.myapplication;

public class Score {
    private float maxDistance;
    private int score;

    //private final int TILE = 137;

    public Score() {
        maxDistance = 0; //fill w/ starting y coord
        score = 0;
    }

    public int updateScore(float yCoord) {
        if (yCoord < maxDistance) {
            maxDistance = yCoord;
            int row = -1 * (int) (yCoord / 137);
            if (row == 1) { //modify these row values depending on where cars go
                score += 2;
            } else if (row == 2) {
                score += 3;
            } else if (row == 3) {
                score += 4;
            } else {
                score++;
            }
        }
        return score;
    }

    public int getScore() {
        return score;
    }
}