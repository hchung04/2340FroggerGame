package com.example.myapplication;

public class Sprite{
    public static float moveUp(float initYCoord, float jump){
        if (initYCoord - jump >= -10 * jump || initYCoord == 0) {
            return initYCoord - jump;
        }
        return initYCoord;
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

    public int setStartingPoints(String level) {
        if (level.equals("Easy")) {
            return 2;
        } else if (level.equals("Medium")) {
            return 1;
        } else {
            return 0;
        }
    }

}