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
        Log logSmall = new Log(findViewById(R.id.logSmall), -600, -1180, 15, 15);
        Log logBigR = new Log(findViewById(R.id.logBigR), -600, -1050, 15, 15);
        Log logBigL = new Log(findViewById(R.id.logBigL), -600, -370, 15, 15);

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
        for (int r = 0; r < 10; r++) {
            LinearLayout row = new LinearLayout(this);
            gridLayout.addView(row);
            row.setOrientation(LinearLayout.HORIZONTAL);
            rows.add(row);
            int tileType;
            //Sets number of tiles per row
            //Total tiles per row: 8
            for (int c = 0; c < 8; c++) {
                if (r == 6 || r == 5 || r == 4) {
                    tileType = R.drawable.road_tile;
                } else if (r % 2 == 0 || r == 3) {
                    if (c == 3) {
                        tileType = R.drawable.grass__0;
                    } else {
                        tileType = R.drawable.water;
                    }
                } else {
                    tileType = R.drawable.grass__0;
                }
                if (c % 2 == 0 && r == 0) {
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
                updateStats(sprite, score);
                //game won
                if (sprite.checkGoal() && sprite.getLivesRemaining() > 0) {
                    pointsCounter.setText("Points: " + score.addGoalScore());
                    switchToGameWonActivity(score.getScore());
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
                updateStats(sprite, score);
            }
        });

        ImageView leftButton = (ImageView) findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sprite.moveLeft(jump);
                updateStats(sprite, score);
            }
        });

        ImageView rightButton = (ImageView) findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sprite.moveRight(jump);
                updateStats(sprite, score);
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
                        logSmall.updateX("right", 10);
                        logBigR.updateX("right", 40);
                        logBigL.updateX("left", -20);

                        if (carRight.checkCollision(sprite, 65)) {
                            collidedObject = carRight;
                            collided = true;
                        } else if (carLeft.checkCollision(sprite, 65)) {
                             collidedObject = carLeft;
                             collided = true;
                        } else if (truckLeft.checkCollision(sprite, 65)) {
                             collidedObject = truckLeft;
                             collided = true;
                        } else if (logSmall.checkCollision(sprite, 40)) {
                            collidedObject = logSmall;
                            collided = true;
                        } else if (logBigR.checkCollision(sprite, 40)) {
                            collidedObject = logBigR;
                            collided = true;
                        } else if (logBigL.checkCollision(sprite, 40)) {
                            collidedObject = logBigL;
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

    private void updateStats(Sprite sprite, Score score) {
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


    private void switchToGameOverActivity(int points) {
        Intent switchActivityIntent = new Intent(this, GameOverActivity.class);
        switchActivityIntent.putExtra("points", points);
        this.finish();
        startActivity(switchActivityIntent);
    }

    private void switchToGameWonActivity(int points) {
        Intent switchActivityIntent = new Intent(this, GameWonActivity.class);
        switchActivityIntent.putExtra("points", points);
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
    //method for Sprint 5 game won test case
    public static boolean switchToGameWonScreen(int livesRemaining) {
        if (livesRemaining >= 1) {
            return true;
        }
        return false;
    }


}
