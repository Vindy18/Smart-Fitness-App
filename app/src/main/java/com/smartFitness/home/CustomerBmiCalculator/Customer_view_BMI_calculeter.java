package com.smartFitness.home.CustomerBmiCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.smartFitness.home.R;

public class Customer_view_BMI_calculeter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_bmi_calculeter);

        Intent mainIntent = getIntent();
    }
}