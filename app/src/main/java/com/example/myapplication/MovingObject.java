package com.example.myapplication;

import android.widget.ImageView;

public class MovingObject {

    ImageView movingObject;
    private float xCoord, yCoord;

    public MovingObject(ImageView movingObject, float startingX, float startingY, float scaleX, float scaleY) {
        this.movingObject = movingObject;
        this.movingObject.setTranslationX(startingX);
        this.movingObject.setTranslationY(startingY);
        this.movingObject.setScaleX(scaleX);
        this.movingObject.setScaleY(scaleY);
    }

    //for testing purposes
    public MovingObject(float startingX, float startingY) {
        xCoord = startingX;
        yCoord = startingY;
    }

    //for testing purposes
    public double updateX(char direction, int additionalToX) {
        if (direction == 'L') {
            if (xCoord - additionalToX < -600) {
                xCoord = 600;
            } else {
                xCoord = xCoord - additionalToX;
            }
        } else if (direction == 'R') {
            if (xCoord + additionalToX> 600) {
                xCoord = -600;
            } else {
                xCoord = xCoord + additionalToX;
            }
        }
        return xCoord;
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
        return xEqual && yEqual;
    }

    public float[] newCoordForCollision(Sprite sprite) {
        float[] newCoord = new float[2];
        if (this instanceof Vehicle) {
            newCoord[0] = -55;
            newCoord[1] = 0;
        } else if (this instanceof Log) {
            newCoord[0] = this.movingObject.getTranslationX();
            newCoord[1] = sprite.getTranslationY();
        }
        return newCoord;
    }


}
