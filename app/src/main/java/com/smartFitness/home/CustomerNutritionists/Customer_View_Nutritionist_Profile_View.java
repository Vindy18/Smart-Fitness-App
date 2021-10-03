package com.smartFitness.home.CustomerNutritionists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smartFitness.home.CustomerBmiCalculator.Customer_view_BMI_calculeter;
import com.smartFitness.home.CustomerCommon.Customer_view_Activity;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.R;

public class Customer_View_Nutritionist_Profile_View extends AppCompatActivity {

    //variable
    Button btn_nut_cv_close;
    TextView tv_nut_profileName;
    TextView tv_nut_profileLocation;
    TextView tv_nut_profileNo;
    TextView tv_nut_profileEmail;
    TextView tv_nut_profileDes;

    String emailExtra;

    Nutritionist nutritionist;
    DBHelperNutritionist dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_nutritionist_profile_view);

        // get intent object
        Intent Intent = getIntent();
        emailExtra = Intent.getStringExtra("emailaddress");

        //db connection
        dbHelper = new DBHelperNutritionist(this);

        //get element by ID
        btn_nut_cv_close = findViewById(R.id.btn_nut_cv_close);
        tv_nut_profileName = findViewById(R.id.tv_nut_profileName);
        tv_nut_profileLocation = findViewById(R.id.tv_nut_profileLocation);
        tv_nut_profileNo = findViewById(R.id.tv_nut_profileNo);
        tv_nut_profileEmail = findViewById(R.id.tv_nut_profileEmail);
        tv_nut_profileDes = findViewById(R.id.tv_nut_profileDes);

        //get current nutrition details to text views
        nutritionist = dbHelper.getNutritionist(emailExtra);
        tv_nut_profileName.setText(nutritionist.name);
        tv_nut_profileLocation.setText(nutritionist.location);
        tv_nut_profileEmail.setText(nutritionist.email);
        tv_nut_profileNo.setText(nutritionist.mobileNumber);
        tv_nut_profileDes.setText(nutritionist.description);

        // event Listener for Close button
        btn_nut_cv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_View_Nutritionist_Profile_View.this, Customer_View_Nutritionists_List.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Nutritionists are Loading ",Toast.LENGTH_SHORT).show();
            }
        });
    }
}