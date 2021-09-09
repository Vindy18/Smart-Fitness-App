package com.smartFitness.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Customer_view_Activity extends AppCompatActivity {

    // variables
    Button btn_bmiCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        // get intent object
        Intent mainIntent = getIntent();

        // get elements by id
        btn_bmiCal = findViewById(R.id.btn_bmiCal);

        // event Listener for BMI Calculator button
        btn_bmiCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_view_Activity.this,Customer_view_BMI_calculeter.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"BMI Calculator is Loading ",Toast.LENGTH_SHORT).show();
            }
        });

    }


}