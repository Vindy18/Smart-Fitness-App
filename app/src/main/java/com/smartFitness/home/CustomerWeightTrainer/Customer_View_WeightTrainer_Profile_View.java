package com.smartFitness.home.CustomerWeightTrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smartFitness.home.CustomerNutritionists.Customer_View_Nutritionist_Profile_View;
import com.smartFitness.home.CustomerNutritionists.Customer_View_Nutritionists_List;
import com.smartFitness.home.DataBase.DBHelperWeightTrainer;
import com.smartFitness.home.Model.WeightTrainer;
import com.smartFitness.home.R;

public class Customer_View_WeightTrainer_Profile_View extends AppCompatActivity {

    //variable
    Button btn_wt_cv_close;
    TextView tv_wt_profileName;
    TextView tv_wt_profileLocation;
    TextView tv_wt_profileNo;
    TextView tv_wt_profileEmail;
    TextView tv_wt_profileDes;

    String emailExtra;

    WeightTrainer weightTrainer;
    DBHelperWeightTrainer dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_weight_trainer_profile_view);

        // get intent object
        Intent Intent = getIntent();
        emailExtra = Intent.getStringExtra("emailaddress");

        //db connection
        dbHelper = new DBHelperWeightTrainer(this);

        //get element by ID
        btn_wt_cv_close = findViewById(R.id.btn_wt_cv_close );
        tv_wt_profileName = findViewById(R.id.tv_wt_profileName);
        tv_wt_profileLocation = findViewById(R.id.tv_wt_profileLocation);
        tv_wt_profileNo = findViewById(R.id.tv_wt_profileNo);
        tv_wt_profileEmail = findViewById(R.id.tv_wt_profileEmail);
        tv_wt_profileDes = findViewById(R.id.tv_wt_profileDes);

        //get current  details to text views
        weightTrainer = dbHelper.getWeightTrainers(emailExtra);
        tv_wt_profileName.setText(weightTrainer.name);
        tv_wt_profileLocation.setText(weightTrainer.location);
        tv_wt_profileEmail.setText(weightTrainer.email);
        tv_wt_profileNo.setText(weightTrainer.contactNumber);
        tv_wt_profileDes.setText(weightTrainer.about);

        // event Listener for Close button
        btn_wt_cv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_View_WeightTrainer_Profile_View.this, Customer_View_WeightTrainerList.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Weigh trainers are Loading ",Toast.LENGTH_SHORT).show();
            }
        });
    }
}