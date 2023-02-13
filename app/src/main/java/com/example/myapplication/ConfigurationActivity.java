package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ConfigurationActivity extends AppCompatActivity {

    private String name;
    private EditText nameInput;
    private Button submit;
    private String gameDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

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
                if (isNameValid(name) && gameDifficulty != null) {
                    switchToGameActivity(name);
                } else if (!isNameValid(name)) {
                    showInvalidNamePopup();
                } else if (gameDifficulty == null) {
                    showInvalidDifficulty();
                }




            }
        });

    }

    private boolean isNameValid(String name) {
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

    //Sends name here to GameActivity
    private void switchToGameActivity(String name) {
        Intent switchActivityIntent = new Intent(this, GameActivity.class);
        switchActivityIntent.putExtra("name_key", name);
        startActivity(switchActivityIntent);
    }
}