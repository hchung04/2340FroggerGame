package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
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

    private MovingObject collidedObject;
    private boolean collided;

    private int livesRemaining;

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

        Score score = new Score();
        //VEHICLES, sets starting position and scale
        Vehicle carRight = new Vehicle(findViewById(R.id.redCar), -600, -620, 15, 15);
        Vehicle carLeft = new Vehicle(findViewById(R.id.brownCar), -600, -900, 15, 15);
        Vehicle truckLeft = new Vehicle(findViewById(R.id.orangeTruck), -600, -770, 15, 15);
        //NOTE: need to change these so that we don't concatenate with setText
        //Use getString and set format in strings.xml instead
        pointsCounter.setText("Points: " + score.getScore());
        //width and height of tiles and sprite
        int width = (int) getResources().getDimension(R.dimen.tile_width);
        int height = (int) getResources().getDimension(R.dimen.tile_height);

        //LOGS, sets starting position and scale
        Log logSmall = new Log(findViewById(R.id.logSmall), -600, -620, 15, 15);
        Log logBig = new Log(findViewById(R.id.logBig), -600, -620, 15, 15);

        Intent retrieveConfigurationData = getIntent();

        //set name and level to player's choice
        String nameInput = retrieveConfigurationData.getStringExtra("name_key");
        String levelInput = retrieveConfigurationData.getStringExtra("level_key");

        Sprite sprite = new Sprite(findViewById(R.id.sprite),
                retrieveConfigurationData.getParcelableExtra("player_key"), levelInput);
        sprite.setTranslationX(-55);
        name.setText(nameInput);
        level.setText(levelInput);
        livesCounter.setText("Lives: " + sprite.getLivesRemaining());

        LinearLayout gridLayout = (LinearLayout) findViewById(R.id.grid_layout);
        ArrayList<LinearLayout> rows = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        //Outer loop sets up individual rows
        //Depending on row, changes tile type
        //Total Rows: 10
        for (int i = 0; i < 10; i++) {
            LinearLayout row = new LinearLayout(this);
            gridLayout.addView(row);
            row.setOrientation(LinearLayout.HORIZONTAL);
            rows.add(row);
            int tileType;
            //Sets number of tiles per row
            //Total tiles per row: 8
            for (int j = 0; j < 8; j++) {
                if (i == 6 || i == 5 || i == 4) {
                    tileType = R.drawable.road_tile;
                } else if (i % 2 == 0 || i == 3) {
                    if (j == 3) {
                        tileType = R.drawable.grass__0;
                    } else {
                        tileType = R.drawable.water;
                    }
                } else {
                    tileType = R.drawable.grass__0;
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
                float oldTranslation = sprite.getTranslationY();
                sprite.moveUp(jump);
                if (sprite.checkWater()) {
                    if (sprite.getLivesRemaining() > 1) {
                        pointsCounter.setText("Points: " + score.subtractScore());
                        sprite.resetToStart();
                        sprite.subtractLife();
                        livesCounter.setText("Lives: " + sprite.getLivesRemaining());
                    } else {
                        sprite.resetLife();
                        switchToGameOverActivity(score.getScore());
                    }
                }

                if (sprite.checkGoal()) {
                    if (sprite.getLivesRemaining() > 1) {
                        pointsCounter.setText("Points: " + score.addGoalScore());
                        sprite.resetToStart();
                        livesCounter.setText("Lives: " + sprite.getLivesRemaining());
                    }
                }

                if (sprite.movedUp(oldTranslation, sprite.getTranslationY())) {
                    pointsCounter.setText("Points: " + score.updateScore(sprite.getTranslationY()));
                }
            }
        });

        ImageView downButton = (ImageView) findViewById(R.id.downButton);
        downButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sprite.moveDown(jump);
                if (sprite.checkWater()) {
                    if (sprite.getLivesRemaining() > 1) {
                        pointsCounter.setText("Points: " + score.subtractScore());
                        sprite.resetToStart();
                        sprite.subtractLife();
                        livesCounter.setText("Lives: " + sprite.getLivesRemaining());
                    } else {
                        sprite.resetLife();
                        switchToGameOverActivity(score.getScore());
                    }
                }
            }
        });

        ImageView leftButton = (ImageView) findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sprite.moveLeft(jump);
                if (sprite.checkWater()) {
                    if (sprite.getLivesRemaining() > 1) {
                        pointsCounter.setText("Points: " + score.subtractScore());
                        sprite.resetToStart();
                        sprite.subtractLife();
                        livesCounter.setText("Lives: " + sprite.getLivesRemaining());
                    } else {
                        sprite.resetLife();
                        switchToGameOverActivity(score.getScore());
                    }
                }
            }
        });

        ImageView rightButton = (ImageView) findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sprite.moveRight(jump);
                if (sprite.checkWater()) {
                    if (sprite.getLivesRemaining() > 1) {
                        pointsCounter.setText("Points: " + score.subtractScore());
                        sprite.resetToStart();
                        sprite.subtractLife();
                        livesCounter.setText("Lives: " + sprite.getLivesRemaining());
                    } else {
                        sprite.resetLife();
                        switchToGameOverActivity(score.getScore());
                    }
                }
            }
        });
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        carRight.updateX("right", 20);
                        carLeft.updateX("left", -50);
                        truckLeft.updateX("left", -70);
                        logSmall.updateX("right", 40);
                        logBig.updateX("right", 40);

                        if (carRight.checkCollision(sprite, 65)) {
                            collidedObject = carRight;
                            collided = true;
                        } else if (carLeft.checkCollision(sprite, 65)) {
                             collidedObject = carLeft;
                             collided = true;
                        } else if (truckLeft.checkCollision(sprite, 65)) {
                             collidedObject = truckLeft;
                             collided = true;
                        }

                        if (collided) {
                            if (sprite.getLivesRemaining() > 1) {
                                sprite.dealWithCollision(collidedObject);
                                score.resetMaxDistance();
                                livesCounter.setText("Lives: " + sprite.getLivesRemaining());
                                pointsCounter.setText("Points: " + score.subtractScore());
                            } else {
                                sprite.resetLife();
                                switchToGameOverActivity(score.getScore());
                            }
                            collided = false;
                        }
                    }
                });
            }
        }, 0, 100);
    }


    private void switchToGameOverActivity(int points) {
        Intent switchActivityIntent = new Intent(this, GameWonActivity.class);
        switchActivityIntent.putExtra("points", points);
        //switchActivityIntent.putExtra("high_score", highScore);
        this.finish();
        startActivity(switchActivityIntent);
    }

    // method for Sprint 4 game over test case
    public static boolean switchToGameOverScreen(int livesRemaining) {
        if (livesRemaining == 0) {
            return true;
        }
        return false;
    }

}
