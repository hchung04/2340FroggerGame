package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    private TextView pointsCounter;
    private TextView livesCounter;

    private TextView name;
    private TextView level;
    private ImageView sprite;

    private ImageView carRight;
    //private ImageView carRight2;

    private ImageView carLeft;
    private ImageView truckLeft;

    private int livesRemaining;

    //position
    private float carRightX;
    //private float carRightX2;
    private float carLeftX;
    private float truckLeftX;

    //Initialize Class
    private Handler handler = new Handler(Looper.myLooper());
    private Timer timer = new Timer();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pointsCounter = (TextView) findViewById(R.id.pointCounter);
        livesCounter = (TextView) findViewById(R.id.livesCounter);
        name = findViewById(R.id.name);
        level = findViewById(R.id.level);
        sprite = (ImageView) findViewById(R.id.sprite);

        Score score = new Score();
        carRight = (ImageView) findViewById(R.id.redCar);
        //carRight2 = (ImageView) findViewById(R.id.redCar2);
        carLeft = (ImageView) findViewById(R.id.brownCar);
        truckLeft = (ImageView) findViewById(R.id.orangeTruck);

        //NOTE: need to change these so that we don't concatenate with setText
        //Use getString and set format in strings.xml instead
        pointsCounter.setText("Points: " + score.getScore());
        //width and height of tiles and sprite
        int width = (int) getResources().getDimension(R.dimen.tile_width);
        int height = (int) getResources().getDimension(R.dimen.tile_height);

        Intent retrieveConfigurationData = getIntent();

        String nameInput = retrieveConfigurationData.getStringExtra("name_key");
        // display the string into textView
        name.setText(nameInput);

        String levelInput = retrieveConfigurationData.getStringExtra("level_key");
        level.setText(levelInput);

        int livesRemaining = setLives(levelInput);
        livesCounter.setText("Lives: " + livesRemaining);


        Bitmap spriteInput = retrieveConfigurationData.getParcelableExtra("player_key");
        sprite.setImageBitmap(spriteInput);

        int startingPoints = setPoints(levelInput);
        pointsCounter = (TextView) findViewById(R.id.pointCounter);
        pointsCounter.setText("Points: " + startingPoints);

        LinearLayout gridLayout = (LinearLayout) findViewById(R.id.grid_layout);
        ArrayList<LinearLayout> rows = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        //Outer loop sets up individual rows
        //Depending on row, changes tile type
        //Total Rows: 11
        for (int i = 0; i < 10; i++) {
            LinearLayout row = new LinearLayout(this);
            gridLayout.addView(row);
            row.setOrientation(LinearLayout.HORIZONTAL);
            rows.add(row);
            int tileType;


            //Sets number of tiles per row
            //Total tiles per row: 8
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) {
                    tileType = R.drawable.water;
                } else if (i == 5) {
                    tileType = R.drawable.road_tile;
                } else {
                    tileType = R.drawable.grass__0;
                }
                if (i == 5) {
                    tileType = R.drawable.road_tile;
                }
                if (i == 4) {
                    tileType = R.drawable.road_tile;
                }
                if (i == 3) {
                    tileType = R.drawable.water;

                }
                    if (j % 2 == 0 && i == 0) {
                        tileType = R.drawable.tileset_brick_wall;
                    }
                    ImageView tile = new ImageView(this);
                    tile.setImageResource(tileType);
                    row.addView(tile);
                    tile.setLayoutParams(params);

                }
            }

            int jump = (int) getResources().getDimension(R.dimen.tile_width);

            ImageView upButton = (ImageView) findViewById(R.id.upButton);
            upButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    float y = sprite.getTranslationY();

                if (y - jump >= -10 * jump || y == 0) {
                    sprite.setTranslationY(y - jump);
                    pointsCounter.setText("Points: " + score.updateScore(sprite.getTranslationY()));
                }
            }});

            ImageView downButton = (ImageView) findViewById(R.id.downButton);
            downButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    float y = sprite.getTranslationY();

                    if (y + jump <= -jump) {
                        sprite.setTranslationY(y + jump);
                    }
                }
            });

            ImageView leftButton = (ImageView) findViewById(R.id.leftButton);
            leftButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    float x = sprite.getTranslationX();

                    if (x - jump >= -411) {
                        sprite.setTranslationX(x - jump);
                    }
                }
            });

            ImageView rightButton = (ImageView) findViewById(R.id.rightButton);
            rightButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    float x = sprite.getTranslationX();

                    if (x + jump <= 411) {
                        sprite.setTranslationX(x + jump);
                    }
                }
        });



        //Move to out of screen
        carRight.setTranslationX(-600);
        carRight.setTranslationY(-220);
        carRight.setScaleX(15);
        carRight.setScaleY(15);

        carLeft.setTranslationX(-600);
        carLeft.setTranslationY(-510);
        carLeft.setScaleX(15);
        carLeft.setScaleY(15);

        truckLeft.setTranslationX(-600);
        truckLeft.setTranslationY(-360);
        truckLeft.setScaleX(15);
        truckLeft.setScaleY(15);

        //carRight2.setTranslationX(-500);
        //carRight2.setTranslationY(-300);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        carRightX = carRight.getX() + 20;
                        //carRightX2 = carRight2.getX() + 20;
                        if (carRight.getTranslationX() > 600) {
                            carRight.setTranslationX(-600);
                        } else {
                            carRight.setX(carRightX);
                        }

                        carLeftX = carLeft.getX() - 50;
                        if (carLeft.getTranslationX() < -600) {
                            carLeft.setTranslationX(600);
                        } else {
                            carLeft.setX(carLeftX);
                        }

                        truckLeftX = truckLeft.getX() - 70;
                        if (truckLeft.getTranslationX() < -600) {
                            truckLeft.setTranslationX(600);
                        } else {
                            truckLeft.setX(truckLeftX);
                        }
                        /*if (carRight2.getTranslationX() > 600) {
                            carRight2.setTranslationX(-600);
                        } else {
                            carRight2.setX(carRightX2);
                        }*/

                    }
                });
            }
        }, 0, 100);

    }


    public int setLives(String level) {
        if (level.equals("Easy")) {
            return 3;
        } else if (level.equals("Medium")) {
            return 2;
        } else {
            return 1;
        }
    }

    public int setPoints(String level) {
        if (level.equals("Easy")) {
            return 2;
        } else if (level.equals("Medium")) {
            return 1;
        } else {
            return 0;
        }
    }

    }
