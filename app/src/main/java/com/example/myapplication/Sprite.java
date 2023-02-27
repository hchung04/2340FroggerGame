package com.example.myapplication;

public class Sprite {
    public static float moveUp(float initYCoord, float jump) {
        if (initYCoord - jump >= -10 * jump || initYCoord == 0) {
            return initYCoord - jump;
        }
        return initYCoord;
    }
  
    public static float moveDown(float initYCoord, float jump) {
        if (initYCoord + jump <= -jump) {
            return initYCoord + jump;
        }
        return initYCoord;
    }

    public static float moveRight(float initXCoord, float jump) {
        if (initXCoord + jump <= 411) {
            return initXCoord + jump;
        }
        return initXCoord;
    }

    public static float moveLeft(float initXCoord, float jump) {
        if (initXCoord - jump >= -411) {
            return initXCoord - jump;
        }
        return initXCoord;
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