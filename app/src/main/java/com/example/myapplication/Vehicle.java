package com.example.myapplication;

import android.widget.ImageView;

public class Vehicle extends MovingObject {

    private ImageView vehicle;
    private static ImageView vehicle2;

    public Vehicle(ImageView vehicle, float startingX, float startingY,
                   float scaleX, float scaleY) {
        super(vehicle, startingX, startingY, scaleX, scaleY);

    }

  //method used for test cases
//    public static float updateX2(float translationX, String direction, int additionalToX) {
//        float vehicleX = translationX + additionalToX;
//        if (direction.equals("left")) {
//            if (translationX < -600) {
//                return 600;
//            } else {
//                return vehicleX;
//            }
//        } else if (direction.equals("right")) {
//            if (translationX > 600) {
//                return -600;
//            } else {
//                return vehicleX;
//            }
//        }
//        return vehicleX;
//    }



}
