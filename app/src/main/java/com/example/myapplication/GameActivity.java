package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private TextView pointsCounter;
    private TextView livesCounter;

    private TextView name;
    private TextView level;
    private ImageView sprite;

    private int points = 0;
    private int livesRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pointsCounter = (TextView) findViewById(R.id.pointCounter);
        livesCounter = (TextView) findViewById(R.id.livesCounter);
        name = findViewById(R.id.name);
        level = findViewById(R.id.level);
        sprite = (ImageView) findViewById(R.id.sprite);

        pointsCounter.setText("Points:" + points);

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

    }


}