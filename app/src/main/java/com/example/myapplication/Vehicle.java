package com.example.myapplication;

import android.widget.ImageView;

public class Vehicle {

    ImageView vehicle;
    static ImageView vehicle2;

    public Vehicle(ImageView vehicle, float startingX, float startingY, float scaleX, float scaleY) {
        this.vehicle = vehicle;
        this.vehicle.setTranslationX(startingX);
        this.vehicle.setTranslationY(startingY);
        this.vehicle.setScaleX(scaleX);
        this.vehicle.setScaleY(scaleY);
    }

    public void updateX(String direction, int additionalToX) {
        float vehicleX = this.vehicle.getTranslationX() + additionalToX;
        if (direction.equals("left")) {
            if (this.vehicle.getTranslationX() < -600) {
                this.vehicle.setTranslationX(600);
            } else {
                this.vehicle.setTranslationX(vehicleX);
            }
        } else if (direction.equals("right")) {
            if (this.vehicle.getTranslationX() > 600) {
                this.vehicle.setTranslationX(-600);
            } else {
                this.vehicle.setTranslationX(vehicleX);
            }
        }
    }

    public static float updateX2(float translationX, String direction, int additionalToX) {
        float vehicleX = translationX + additionalToX;
        if (direction.equals("left")) {
            if (translationX < -600) {
                return 600;
            } else {
                return vehicleX;
            }
        } else if (direction.equals("right")) {
            if (translationX > 600) {
                return -600;
            } else {
                return vehicleX;
            }
        }
        return vehicleX;
    }


}
