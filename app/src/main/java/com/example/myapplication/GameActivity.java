package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//    private RecyclerViewAdapter recyclerViewAdapter;

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
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction(); //differentiates the touch event
        String DEBUG_TAG = "Debug";
        int jump = (int) getResources().getDimension(R.dimen.tile_width); //amount to jump to next row

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d(DEBUG_TAG,"Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                Log.d(DEBUG_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                //jumps up one row
                float y = sprite.getTranslationY();
                sprite.setTranslationY(y - jump);
                Log.d(DEBUG_TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }


}