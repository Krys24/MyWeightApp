package com.example.myweight;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RecordWeight extends AppCompatActivity {
    private AppDatabase database;
    private EditText etWeight, etDate, etAge;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_weight);

        // Get the database instance from MyApp
        database = MyApp.getDatabase();

        // Initialize views
        etWeight = findViewById(R.id.etWeight);
        etDate = findViewById(R.id.etDate);
        etAge = findViewById(R.id.etAge);
        btnSave = findViewById(R.id.btnSave);

        // Set click listener for save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWeightEntry();
            }
        });
    }

    private void saveWeightEntry() {
        // Retrieve input values from EditText fields
        String weightText = etWeight.getText().toString().trim();
        String dateText = etDate.getText().toString().trim();
        String ageText = etAge.getText().toString().trim();

        // Validate input values
        if (weightText.isEmpty() || dateText.isEmpty() || ageText.isEmpty()) {
            Toast.makeText(this, "Please enter information in all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse input values
        double weight = Double.parseDouble(weightText);
        int age = Integer.parseInt(ageText);

        // Create WeightEntry object
        WeightEntry weightEntry = new WeightEntry(weight, dateText, age);

        // Insert WeightEntry into the database
        new Thread(new Runnable() {
            @Override
            public void run() {
                database.weightEntryDao().insertWeightEntry(weightEntry);
            }
        }).start();

        // Show success message
        Toast.makeText(this, "Weight entry saved", Toast.LENGTH_SHORT).show();

        // Clear input fields
        etWeight.getText().clear();
        etDate.getText().clear();
        etAge.getText().clear();
    }
}
