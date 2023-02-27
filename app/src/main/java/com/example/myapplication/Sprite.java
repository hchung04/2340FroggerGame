package com.example.myapplication;

public class Sprite{
    public static float moveUp(float initYCoord, float jump){
        if (initYCoord - jump >= -10 * jump || initYCoord == 0) {
            return initYCoord - jump;
        }
        return initYCoord;
    }

    public static float moveDown(float initYCoord, float jump){
        if (initYCoord + jump <= -jump) {
            return initYCoord + jump;
        }
        return initYCoord;
    }

    public static float moveRight(float initXCoord, float jump){
        if (initXCoord + jump <= 411) {
            return initXCoord + jump;
        }
        return initXCoord;
    }

}