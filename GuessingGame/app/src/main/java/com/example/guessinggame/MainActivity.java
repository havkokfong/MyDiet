package com.example.guessinggame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private EditText guessField;
    private SharedPreferences preferences;
    private TextView progressText;
    private TextView maxText;

    private int secretNumber = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        statusText = (TextView) findViewById(R.id.statusText);
        guessField = (EditText) findViewById(R.id.guessField);
        statusText.setText("Welcome to GuessingApp");

        preferences = getSharedPreferences("value", MODE_PRIVATE);
        Random number = new Random();
        secretNumber = preferences.getInt("seek bar", 0)+ number.nextInt(preferences.getInt
                ("seek bar max",10));
        progressText = (TextView) findViewById(R.id.textViewMin);
        maxText = (TextView) findViewById(R.id.textViewMax);
        guessField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence string, int start, int before, int count) {
                try {
                    int value = Integer.parseInt(string.toString());

                    if (value == secretNumber) {
                        statusText.setText("You won!");
                    }

                    else if (value > secretNumber) {
                        statusText.setText("Please Try Smaller Number!");
                    }

                    else {
                        statusText.setText("Please Try Bigger Number!");
                    }
                } catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressText.setText("The minimum number is: " + preferences.getInt("seek bar", 0));
        maxText.setText("The maximum number is: " + preferences.getInt("seek bar max", 10));
    }

    public void handler(View view) {
        Intent intent = new Intent(this, Secondary.class);
        startActivity(intent);
    }

}
