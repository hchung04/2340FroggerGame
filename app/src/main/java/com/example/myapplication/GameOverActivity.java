package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    private TextView scoreText;
    private TextView highScoreText;
    private ImageButton restartButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_screen);

        Intent retrieveConfigurationData = getIntent();

        scoreText = (TextView) findViewById(R.id.gameover_score);
        highScoreText = (TextView) findViewById(R.id.gameover_highscore);
        int score = retrieveConfigurationData.getIntExtra("points", 0);

        //int highScore = retrieveConfigurationData.getIntExtra("high_score", 0);

        //Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
        //intent.putExtra("score", score);
        //startActivity(intent);

        //int scoreNum = getIntent().getIntExtra("score", 0);
        //scoreText.setText(scoreNum + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        int highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore) {
            saveNewHighScore(score, editor);
            highScore = score;
        }

        scoreText.setText("Points: " + score);
        highScoreText.setText("High Score: " + highScore);

        restartButton = (ImageButton) findViewById(R.id.restart_button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchToConfigurationActivity();
            }
        });

        exitButton = (Button) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveNewHighScore(0, editor);
                GameOverActivity.this.finish();
                finishAffinity(); //exits app
            }
        });

    }

    private void saveNewHighScore(int score, SharedPreferences.Editor editor) {
        editor.putInt("HIGH_SCORE", score);
        editor.commit();
    }

    private void switchToConfigurationActivity() {
        Intent switchActivityIntent = new Intent(this, ConfigurationActivity.class);
        this.finish();
        startActivity(switchActivityIntent);
    }
}