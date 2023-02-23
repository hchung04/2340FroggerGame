package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private TextView pointsCounter;
    private TextView livesCounter;

    private TextView name;
    private TextView level;
    private ImageView sprite;

    private int points = 0;
    private int livesRemaining;

    private VelocityTracker mVelocityTracker = null;
    private double xMove, yMove;

    private static final int THRESHOLD = 70;  //arbitrary threshold to prevent negligible readings
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pointsCounter = (TextView) findViewById(R.id.pointCounter);
        livesCounter = (TextView) findViewById(R.id.livesCounter);
        name = findViewById(R.id.name);
        level = findViewById(R.id.level);
        sprite = (ImageView) findViewById(R.id.sprite);

        //NOTE: need to change these so that we don't concatenate with setText
        //Use getString and set format in strings.xml instead
        pointsCounter.setText("Points: " + points);
        //width and height of tiles and sprite
        int width = (int) getResources().getDimension(R.dimen.tile_width);
        int height = (int) getResources().getDimension(R.dimen.tile_height);

        Intent retrieveConfigurationData = getIntent();

        String nameInput = retrieveConfigurationData.getStringExtra("name_key");
        // display the string into textView
        name.setText(nameInput);

        String levelInput = retrieveConfigurationData.getStringExtra("level_key");
        level.setText(levelInput);

        if (levelInput.equals("Easy")) {
            livesRemaining = 3;
        } else if (levelInput.equals("Medium")) {
            livesRemaining = 2;
        } else {
            livesRemaining = 1;
        }

        livesCounter.setText("Lives: " + livesRemaining);


        Bitmap spriteInput = retrieveConfigurationData.getParcelableExtra("player_key");
        sprite.setImageBitmap(spriteInput);

        pointsCounter = (TextView) findViewById(R.id.pointCounter);

        LinearLayout gridLayout = (LinearLayout) findViewById(R.id.grid_layout);
        ArrayList<LinearLayout> rows = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        //Outer loop sets up individual rows
        //Depending on row, changes tile type
        //Total Rows: 11
        for (int i = 0; i < 11; i++) {
            LinearLayout row = new LinearLayout(this);
            gridLayout.addView(row);
            row.setOrientation(LinearLayout.HORIZONTAL);
            rows.add(row);
            int tileType;

            if (i % 2 == 0) {
                tileType = R.drawable.grass__0;
            } else {
                tileType = R.drawable.water;
            }

            //Sets number of tiles per row
            //Total tiles per row: 8
            for (int j = 0; j < 8; j++) {
                ImageView tile = new ImageView(this);
                tile.setImageResource(tileType);
                row.addView(tile);
                tile.setLayoutParams(params);
            }
        }
    }

    //Touch controls are not complete - only jumps upwards each touch control
    //Need to differentiate between swiping and tapping
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction(); //differentiates the touch event
        String debugTag = "Debug";
        //amount to jump to next row
        int jump = (int) getResources().getDimension(R.dimen.tile_width);
        float y = sprite.getTranslationY();
        float x = sprite.getTranslationX();
        int pointerId = event.getPointerId(event.getActionIndex());

        switch (action) {
        case (MotionEvent.ACTION_DOWN) :
            Log.d(debugTag, "Action was DOWN");
            if(mVelocityTracker == null) {

                mVelocityTracker = VelocityTracker.obtain();
            }
            else {
                // Reset the velocity tracker back to its initial state.
                mVelocityTracker.clear();
            }
            // Add a user's movement to the tracker.
            mVelocityTracker.addMovement(event);
            return true;
        case (MotionEvent.ACTION_MOVE) :
            mVelocityTracker.addMovement(event);
            mVelocityTracker.computeCurrentVelocity(1000);
            xMove = mVelocityTracker.getXVelocity(pointerId);
            //Log.d("", "X velocity: " + xMove);
            yMove =  mVelocityTracker.getYVelocity(pointerId);
            //Log.d("", "Y velocity: " + yMove);
            Log.d(debugTag, "Action was MOVE");
            return true;
        case (MotionEvent.ACTION_UP) :
            mVelocityTracker.addMovement(event);

            //only moves down, left, right  if on the actual map, not the starting position
            if(sprite.getTranslationY() < 0) {
                if (xMove > THRESHOLD) {
                    if (x + jump <= 411) {
                        sprite.setTranslationX(x + jump);
                    }
                } else if (xMove < -THRESHOLD) {
                    if (x - jump >= -411) {
                        sprite.setTranslationX(x - jump);
                    }
                } else if (yMove < -THRESHOLD) {
                    if (y + jump <= -137) {
                        sprite.setTranslationY(y + jump);
                    }
                } else if (yMove > THRESHOLD) {
                    if (y - jump >= -1507) {
                        sprite.setTranslationY(y - jump);
                    }
                }
            } else {
                sprite.setTranslationY(y - jump);
            }
            yMove = 0;
            xMove = 0;
            Log.d(debugTag, "Action was UP");
            //Log.d(debugTag, event.getY()+"");
            return true;
        case (MotionEvent.ACTION_CANCEL) :
            Log.d(debugTag, "Action was CANCEL");
            return true;
        case (MotionEvent.ACTION_OUTSIDE) :
            Log.d(debugTag, "Movement occurred outside bounds "
                    + "of current screen element");
            return true;
        default :
            return super.onTouchEvent(event);
        }
    }


}