package com.smartFitness.home.CustomerBmiCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.smartFitness.home.R;

import java.text.DecimalFormat;

public class Customer_view_BMI_calculeter extends AppCompatActivity {
    // variables
    Button btn_calculate;
    EditText et_height;
    EditText et_weight;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_bmi_calculeter);

        //get intent object from customer view
        Intent mainIntent = getIntent();

        // get elements by id
        btn_calculate = findViewById(R.id.btn_bmi_cal);
        et_height =  findViewById(R.id.et_height);
        et_weight = findViewById(R.id.et_weight);
        tv_result = findViewById(R.id.tv_bmiCalResult);
    }

    protected void onResume() {
        super.onResume();

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  calculateBmiValue(); }

        });
    }

    private void calculateBmiValue(){

        CalculateBmi cb = new CalculateBmi();

        String temp_height = et_height.getText().toString();
        String temp_weight = et_weight.getText().toString();

        if(TextUtils.isEmpty(temp_height) || TextUtils.isEmpty(temp_weight)){

            Toast.makeText(this, "Please Enter Value", Toast.LENGTH_SHORT).show();

            String result  = "0.0 kg/m^2";

            tv_result.setText(result);
        }else{

            Double height = Double.parseDouble(temp_height);
            Double weight = Double.parseDouble(temp_weight);

            Double result = cb.bmiValue(height,weight);

            DecimalFormat df = new DecimalFormat("###.##");

            tv_result.setText(new Double(df.format(result)).toString()+" kg/m^2");

        }
    }
}