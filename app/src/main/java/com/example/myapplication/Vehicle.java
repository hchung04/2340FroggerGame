package com.example.myapplication;

import android.widget.ImageView;

public class Vehicle {

    ImageView vehicle;

    public Vehicle(ImageView vehicle, float startingX, float startingY, float scaleX, float scaleY) {
        this.vehicle = vehicle;
        this.vehicle.setTranslationX(startingX);
        this.vehicle.setTranslationY(startingY);
        this.vehicle.setScaleX(scaleX);
        this.vehicle.setScaleY(scaleY);
    }

    public float getTranslationX() {
        return this.vehicle.getTranslationX();
    }

    public float getTranslationY() {
        return this.vehicle.getTranslationY();
    }

    public void setScaleX(float x) {
        this.vehicle.setScaleX(x);
    }

    public void setScaleY(float y) {
        this.vehicle.setScaleY(y);
    }

    public void setTranslationX(float x) {
        this.vehicle.setTranslationX(x);
    }

    public void setTranslationY(float y) {
        this.vehicle.setTranslationY(y);
    }




}
