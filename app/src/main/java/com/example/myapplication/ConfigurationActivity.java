package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigurationActivity extends AppCompatActivity {

    String name;

    EditText nameInput;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);


        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameInput = (EditText) findViewById(R.id.nameInput);
                String name = nameInput.getText().toString();

                if (checkNameValidity(name)) {
                    switchToGameActivity();
                } else {
                    showInvalidNamePopup();
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

    private void switchToGameActivity() {
        Intent switchActivityIntent = new Intent(this, GameActivity.class);
        startActivity(switchActivityIntent);
    }
}