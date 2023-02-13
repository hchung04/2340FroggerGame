package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ConfigurationActivity extends AppCompatActivity {

    private String name;
    private EditText nameInput;
    private Button submit;
    private String gameDifficulty;
    private Drawable chosenSprite;
    private LinearLayout currentSpriteBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        ImageView sprite1 = (ImageView) findViewById(R.id.sprite1);
        LinearLayout sprite1Bg = (LinearLayout) findViewById(R.id.sprite1Bg);
        currentSpriteBg = sprite1Bg;
        sprite1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetSpriteChoices();
                sprite1Bg.setBackgroundColor(Color.parseColor("#C7B8FF"));
                chosenSprite = sprite1.getDrawable();
                currentSpriteBg = sprite1Bg;
            }
        });

        ImageView sprite2 = (ImageView) findViewById(R.id.sprite2);
        LinearLayout sprite2Bg = (LinearLayout) findViewById(R.id.sprite2Bg);
        sprite2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetSpriteChoices();
                sprite2Bg.setBackgroundColor(Color.parseColor("#C7B8FF"));
                chosenSprite = sprite2.getDrawable();
                currentSpriteBg = sprite2Bg;
            }
        });

        ImageView sprite3 = (ImageView) findViewById(R.id.sprite3);
        LinearLayout sprite3Bg = (LinearLayout) findViewById(R.id.sprite3Bg);
        sprite3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetSpriteChoices();
                sprite3Bg.setBackgroundColor(Color.parseColor("#C7B8FF"));
                chosenSprite = sprite3.getDrawable();
                currentSpriteBg = sprite3Bg;
            }
        });

        ImageView sprite4 = (ImageView) findViewById(R.id.sprite4);
        LinearLayout sprite4Bg = (LinearLayout) findViewById(R.id.sprite4Bg);
        sprite4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetSpriteChoices();
                sprite4Bg.setBackgroundColor(Color.parseColor("#C7B8FF"));
                chosenSprite = sprite4.getDrawable();
                currentSpriteBg = sprite4Bg;
            }
        });

        ImageView sprite5 = (ImageView) findViewById(R.id.sprite5);
        LinearLayout sprite5Bg = (LinearLayout) findViewById(R.id.sprite5Bg);
        sprite5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetSpriteChoices();
                sprite5Bg.setBackgroundColor(Color.parseColor("#C7B8FF"));
                chosenSprite = sprite5.getDrawable();
                currentSpriteBg = sprite5Bg;
            }
        });

        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameInput = (EditText) findViewById(R.id.nameInput);
                name = nameInput.getText().toString();

                RadioGroup rg = (RadioGroup) findViewById(R.id.gameDifficulty);
                if (rg.getCheckedRadioButtonId() != -1) {
                    gameDifficulty =
                            ((RadioButton) findViewById(rg.getCheckedRadioButtonId()))
                                    .getText().toString();
                }

                if (checkNameValidity(name) && gameDifficulty != null && chosenSprite != null) {
                    switchToGameActivity();
                } else if (!checkNameValidity(name)) {
                    showInvalidNamePopup();
                } else if (gameDifficulty == null) {
                    showInvalidDifficulty();
                } else if (chosenSprite == null) {
                    showInvalidSprite();
                }
            }
        });

    }

    private boolean checkNameValidity(String name) {
        return !(name.trim().equals(""));
    }
    private void showInvalidNamePopup() {
        Context context = getApplicationContext();
        CharSequence text = "Name cannot be empty!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void showInvalidDifficulty() {
        Context context = getApplicationContext();
        CharSequence text = "Must choose a game difficulty!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void resetSpriteChoices() {
        currentSpriteBg.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    private void showInvalidSprite() {
        Context context = getApplicationContext();
        CharSequence text = "Don't forget to choose your player!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void switchToGameActivity() {
        Intent switchActivityIntent = new Intent(this, GameActivity.class);
        startActivity(switchActivityIntent);
    }
}