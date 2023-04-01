package com.example.myapplication;

import android.widget.ImageView;

public class MovingObject {

    ImageView movingObject;

    public MovingObject(ImageView movingObject, float startingX, float startingY, float scaleX, float scaleY) {
        this.movingObject = movingObject;
        this.movingObject.setTranslationX(startingX);
        this.movingObject.setTranslationY(startingY);
        this.movingObject.setScaleX(scaleX);
        this.movingObject.setScaleY(scaleY);
    }

    public void updateX(String direction, int additionalToX) {
        float vehicleX = this.movingObject.getTranslationX() + additionalToX;
        if (direction.equals("left")) {
            if (this.movingObject.getTranslationX() < -600) {
                this.movingObject.setTranslationX(600);
            } else {
                this.movingObject.setTranslationX(vehicleX);
            }
        } else if (direction.equals("right")) {
            if (this.movingObject.getTranslationX() > 600) {
                this.movingObject.setTranslationX(-600);
            } else {
                this.movingObject.setTranslationX(vehicleX);
            }
        }
    }

    public boolean checkCollision(Sprite sprite) {
        boolean xEqual = sprite.getTranslationX() == this.movingObject.getTranslationX();
        boolean yEqual = sprite.getTranslationY() == this.movingObject.getTranslationY();
        return xEqual && yEqual;
    }

}
