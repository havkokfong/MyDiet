package com.example.guessinggame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class Secondary extends AppCompatActivity {

    private SeekBar seekBarMin;
    private SeekBar seekBarMax;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        seekBarMin = (SeekBar) findViewById(R.id.seekBarMin);
        seekBarMax = (SeekBar) findViewById(R.id.seekBarMax);
        preferences = getSharedPreferences("value", MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int progressMin = preferences.getInt("seek bar", 0);
        int progressMax = preferences.getInt("seek bar max", 10);
        seekBarMin.setProgress(progressMin);
        seekBarMax.setProgress(progressMax);
    }

    @Override
    protected void onStop() {
        super.onStop();
        preferences.edit().putInt("seek bar", seekBarMin.getProgress()).apply();
        preferences.edit().putInt("seek bar max", seekBarMax.getProgress()).apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void back (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
