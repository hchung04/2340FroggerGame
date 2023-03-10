package com.example.myapplication;

public class Score {
    private float maxDistance;
    private int score;

    public Score(){
        maxDistance = 0; //fill w/ starting y coord
        score = 0;
    }

    public int updateScore(float yCoord) {
        if (yCoord < maxDistance) {
            maxDistance = yCoord;
            score++;
        }
        return score;
    }

    public int getScore(){
        return score;
    }
}
