package com.example.myapplication;

import android.os.Bundle;

public class GameWonActivity extends GameOverActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_win_screen);
        onCreateBody();
    }
}