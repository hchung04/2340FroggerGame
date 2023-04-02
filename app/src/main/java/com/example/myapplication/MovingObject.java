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

    public boolean checkCollision(Sprite sprite, int offset) {
        boolean xEqual = Math.abs(sprite.getX() - this.movingObject.getX()) <= offset;
        boolean yEqual = Math.abs(sprite.getY() - this.movingObject.getY()) <= offset;
//        System.out.println(sprite.getY());
//        System.out.println(movingObject.getY());
        return xEqual && yEqual;
    }

    public float[] newCoordForCollision() {
        float[] newCoord = new float[2];
        if (this instanceof Vehicle) {
            newCoord[0] = -55;
            newCoord[1] = 0;
        } // else if Log, newCoord = coord of Log [implement later]
        return newCoord;
    }


}
