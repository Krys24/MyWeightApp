package com.example.myweight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the ActivityResultLauncher
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Handle the result here if needed
                    }
                });

        // Find and set listener for "CalculateBMI" button
        Button calculateBMI = findViewById(R.id.btnCalculateBMI);
        calculateBMI.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CalculateBMI.class);
            launcher.launch(intent);
        });

        // Find and set listener for "RecordWeight" button
        Button recordWeight = findViewById(R.id.btnRecordWeight);
        recordWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordWeight.class);
                startActivity(intent);
            }
        });

        // Find and set listener for "TrackWeight" button
        Button trackWeight = findViewById(R.id.btnTrackWeight);
        trackWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrackWeight.class);
                startActivity(intent);
            }
        });




    }
}
