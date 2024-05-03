package com.example.memorymatchinghaptics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        Button startButton = findViewById(R.id.startbtn);
        Button exitButton = findViewById(R.id.exitbtn);
        startButton.setOnClickListener(v -> {
            switchToGameActivity();
            finish();
        });
        exitButton.setOnClickListener(v -> {
            finish();
        });

    }

    private void switchToGameActivity() {
        // Start the GameActivity
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
}