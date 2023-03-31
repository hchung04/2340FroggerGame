package com.example.myapplication;

import android.content.Intent;
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
        String score = retrieveConfigurationData.getStringExtra("points");
        String highScore = retrieveConfigurationData.getStringExtra("high_score");
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
                GameOverActivity.this.finish();
                finishAffinity(); //exits app
            }
        });

    }

    private void switchToConfigurationActivity() {
        Intent switchActivityIntent = new Intent(this, ConfigurationActivity.class);
        startActivity(switchActivityIntent);
    }
}