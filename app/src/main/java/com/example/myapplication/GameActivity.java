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

    private boolean offScreen;

    private ArrayList<MovingObject> obstacles = new ArrayList<>();

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
        offScreen = false;

        Vehicle carRight = new Vehicle(findViewById(R.id.redCar), -600, -620, 15, 15);
        Vehicle carLeft = new Vehicle(findViewById(R.id.brownCar), -600, -900, 15, 15);
        Vehicle truckLeft = new Vehicle(findViewById(R.id.orangeTruck), -600, -770, 15, 15);

        pointsCounter.setText("Points: " + score.getScore());
        int width = (int) getResources().getDimension(R.dimen.tile_width);
        int height = (int) getResources().getDimension(R.dimen.tile_height);
        Log logSmall = new Log(findViewById(R.id.logSmall), -600, -1180, 15, 15);
        Log logBigL = new Log(findViewById(R.id.logBigR), -600, -1050, 15, 15);
        Log logBigR = new Log(findViewById(R.id.logBigL), -600, -370, 15, 15);

        obstacles.add(carRight);
        obstacles.add(carLeft);
        obstacles.add(truckLeft);
        obstacles.add(logSmall);
        obstacles.add(logBigR);
        obstacles.add(logBigL);

        Intent retrieveConfigurationData = getIntent();
        String nameInput = retrieveConfigurationData.getStringExtra("name_key");
        String levelInput = retrieveConfigurationData.getStringExtra("level_key");
        Sprite sprite = new Sprite(findViewById(R.id.sprite),
                retrieveConfigurationData.getParcelableExtra("player_key"), levelInput);
        sprite.setTranslationX(-55);
        sprite.setTranslationY(0);

        name.setText(nameInput);
        level.setText(levelInput);
        livesCounter.setText("Lives: " + sprite.getLivesRemaining());
        LinearLayout gridLayout = (LinearLayout) findViewById(R.id.grid_layout);
        ArrayList<LinearLayout> rows = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);

        for (int rowNum = 0; rowNum < 10; rowNum++) {
            LinearLayout row = new LinearLayout(this);
            gridLayout.addView(row);
            row.setOrientation(LinearLayout.HORIZONTAL);
            rows.add(row);
            int tileType;
            for (int colNum = 0; colNum < 8; colNum++) {
                if (rowNum == 6 || rowNum == 5 || rowNum == 4) {
                    tileType = R.drawable.road_tile;
                } else if (rowNum % 2 == 0 || rowNum == 3) {
                        tileType = R.drawable.water;
                } else {
                    tileType = R.drawable.grass__0;
                }
                if (colNum % 2 == 0 && rowNum == 0) {
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
                checkAllCollisions(sprite, obstacles);
                updateStats(sprite, score);
                if (sprite.movedUp(oldTranslation, sprite.getTranslationY())) {
                    pointsCounter.setText("Points: " + score.updateScore(sprite.getTranslationY()));
                }
            }
        });
        ImageView downButton = (ImageView) findViewById(R.id.downButton);
        downButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sprite.moveDown(jump);
                checkAllCollisions(sprite, obstacles);
                updateStats(sprite, score);
            }
        });
        ImageView leftButton = (ImageView) findViewById(R.id.leftButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sprite.moveLeft(jump);
                checkAllCollisions(sprite, obstacles);
                updateStats(sprite, score);
            }
        });
        ImageView rightButton = (ImageView) findViewById(R.id.rightButton);
        rightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sprite.moveRight(jump);
                checkAllCollisions(sprite, obstacles);
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
                        logBigL.updateX("left", -40);
                        logBigR.updateX("right", 20);

                        checkAllCollisions(sprite, obstacles);

                        if (sprite.isOffScreen()) {
                            updateStats(sprite, score);
                        }

                        if (collided) {
                            if (sprite.getLivesRemaining() > 0) {
                                sprite.dealWithCollision(collidedObject);
                                score.resetMaxDistance();
                                livesCounter.setText("Lives: " + sprite.getLivesRemaining());
                                if (collidedObject instanceof Vehicle) {
                                    pointsCounter.setText("Points: " + score.subtractScore());
                                }
                            }
                            if (sprite.getLivesRemaining() == 0) {
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
        if (sprite.checkWater() && !(collidedObject instanceof Log) && !offScreen) {
            if (sprite.getLivesRemaining() > 1) {
                pointsCounter.setText("Points: " + score.subtractScore());
                sprite.resetToStart();
                sprite.subtractLife();
                livesCounter.setText("Lives: " + sprite.getLivesRemaining());
                offScreen = true;
            } else {
                sprite.resetLife();
                switchToGameOverActivity(score.getScore());
            }
        } else {
            offScreen = false;
        }
        if (sprite.checkGoal() && sprite.getLivesRemaining() > 0) {
            pointsCounter.setText("Points: " + score.addGoalScore());
            switchToGameWonActivity(score.getScore());
        }
    }

    private void checkAllCollisions(Sprite sprite, ArrayList<MovingObject> obstacles) {
        collidedObject = null;
        collided = false;
        for (MovingObject obs : obstacles) {
            if (obs.checkCollision(sprite, 65)) {
                collidedObject = obs;
                collided = true;
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
