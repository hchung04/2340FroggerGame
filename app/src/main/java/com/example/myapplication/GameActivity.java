package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {
    TextView pointsCounter;
    TextView livesCounter;

    TextView name;
    TextView level;
    ImageView sprite;

    int points = 0;
    int livesRemaining = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pointsCounter = (TextView) findViewById(R.id.pointCounter);
        livesCounter = (TextView) findViewById(R.id.livesCounter);
        name = findViewById(R.id.name);
        level = findViewById(R.id.level);
        sprite = (ImageView) findViewById(R.id.sprite);

        pointsCounter.setText("POINTS:" + points);
        livesCounter.setText("LIVES: " + livesRemaining);

        Intent retrieveConfigurationData = getIntent();

        String nameInput = retrieveConfigurationData.getStringExtra("name_key");
        // display the string into textView
        name.setText(nameInput);

        String levelInput = retrieveConfigurationData.getStringExtra("level_key");
        level.setText(levelInput);

        Bitmap spriteInput = retrieveConfigurationData.getParcelableExtra("player_key");
        sprite.setImageBitmap(spriteInput);
    }


}