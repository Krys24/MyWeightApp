package com.example.myweight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculateBMI extends AppCompatActivity {

    private EditText etWeight;
    private EditText etHeight;
    private Button btnCalculate;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);

        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        textResult = findViewById(R.id.textResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = etWeight.getText().toString();
        String heightStr = etHeight.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            textResult.setText(getString(R.string.error_empty_fields));
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr) / 100.0; // Convert cm to meters

        double bmi = weight / (height * height);
        String bmiResult = String.format("%.2f", bmi);

        String message;

        if (bmi < 18.5) {
            message = getString(R.string.bmi_underweight);
        } else if (bmi < 25) {
            message = getString(R.string.bmi_normal);
        } else if (bmi < 30) {
            message = getString(R.string.bmi_overweight);
        } else {
            message = getString(R.string.bmi_obese);
        }

        textResult.setText(getString(R.string.bmi_result, bmiResult) + "\n" + message);
    }

}
