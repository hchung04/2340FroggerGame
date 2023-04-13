package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.github.jinatonic.confetti.CommonConfetti;
import com.github.jinatonic.confetti.ConfettiView;

public class GameWonActivity extends GameOverActivity {

    private TextView scoreText;
    private TextView highScoreText;
    private ImageButton restartButton;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_win_screen);
        onCreateBody();
    }
    @Override
    protected void onCreateBody(){
        setContentView(R.layout.game_win_screen);

        Intent retrieveConfigurationData = getIntent();

        scoreText = (TextView) findViewById(R.id.gameover_score);
        highScoreText = (TextView) findViewById(R.id.gameover_highscore);
        int score = retrieveConfigurationData.getIntExtra("points", 0);

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
                GameWonActivity.this.finish();
                finishAffinity(); //exits app
            }
        });
    }
}