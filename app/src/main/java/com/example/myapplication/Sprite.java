package com.example.myapplication;

public class Sprite{
    public static float moveUp(float initYCoord, float jump){
        if (initYCoord - jump >= -10 * jump || initYCoord == 0) {
            return initYCoord - jump;
        }
        return initYCoord;
    }

}