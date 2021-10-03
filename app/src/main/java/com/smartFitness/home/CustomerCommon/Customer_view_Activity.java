package com.smartFitness.home.CustomerCommon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smartFitness.home.AppCommon.MainActivity;
import com.smartFitness.home.CustomerBmiCalculator.Customer_view_BMI_calculeter;
import com.smartFitness.home.CustomerNutritionists.Customer_View_Nutritionists_List;
import com.smartFitness.home.CustomerWeightTrainer.Customer_View_WeightTrainerList;
import com.smartFitness.home.R;

public class Customer_view_Activity extends AppCompatActivity {

    // variables
    Button btn_CVBack;
    Button btn_bmiCal;
    Button btn_cv_nut;
    Button btn_cv_WT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        // get intent object
        Intent mainIntent = getIntent();

        // get elements by id
        btn_CVBack = findViewById(R.id.btn_CVBack);
        btn_bmiCal = findViewById(R.id.btn_bmiCal);
        btn_cv_nut = findViewById(R.id.btn_cv_nut);
        btn_cv_WT = findViewById(R.id.btn_cv_WT);

        // event Listener for Back button
        btn_CVBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_view_Activity.this, MainActivity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"BMI Calculator is Loading ",Toast.LENGTH_SHORT).show();
            }
        });

        // event Listener for BMI Calculator button
        btn_bmiCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_view_Activity.this, Customer_view_BMI_calculeter.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"BMI Calculator is Loading ",Toast.LENGTH_SHORT).show();
            }
        });

        // event Listener for Nutritionists button
        btn_cv_nut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_view_Activity.this, Customer_View_Nutritionists_List.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Nutritionists are loading ",Toast.LENGTH_SHORT).show();
            }
        });

        // event Listener for Nutritionists button
        btn_cv_WT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_view_Activity.this, Customer_View_WeightTrainerList.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Weight trainer are loading ",Toast.LENGTH_SHORT).show();
            }
        });

    }


}