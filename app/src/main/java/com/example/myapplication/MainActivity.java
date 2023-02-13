package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button switchToConfigurationActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchToConfigurationActivity = (Button) findViewById(R.id.startButton);
        switchToConfigurationActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchToConfigurationActivity();
            }
        });
    }

    private void switchToConfigurationActivity() {
        Intent switchActivityIntent = new Intent(this, ConfigurationActivity.class);
        startActivity(switchActivityIntent);
    }
}