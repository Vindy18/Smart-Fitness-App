package com.smartFitness.home.AdminCommon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.smartFitness.home.Admin.Add_admin_activity;
import com.smartFitness.home.Admin.Admin_profile_activity;
import com.smartFitness.home.AdminNutritionists.Admin_View_Nutritionists_List;
import com.smartFitness.home.R;

public class Admin_view_Activity extends AppCompatActivity {

    // variables
    Button btn_addAdmin;
    Button btn_avNutritionist;
    ImageButton btn_adminProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        // get intent object
        Intent loginIntent = getIntent();
        String emailExtra = loginIntent.getStringExtra("emailaddress");

        // get elements by id
        btn_addAdmin = findViewById(R.id.btn_addAdmin);
        btn_adminProfile = findViewById(R.id.btn_adminProfile);
        btn_avNutritionist = findViewById(R.id.btn_av_nut);

        // event Listener for Add admin button
        btn_addAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_view_Activity.this, Add_admin_activity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Add Admin page Loading",Toast.LENGTH_SHORT).show();
            }
        });

        // event Listener for Admin profile image button
        btn_adminProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Admin_view_Activity.this, Admin_profile_activity.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Admin profile Loading",Toast.LENGTH_SHORT).show();
            }
        });

        // event Listener for Admin profile image button
        btn_avNutritionist.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Admin_view_Activity.this, Admin_View_Nutritionists_List.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Welcome",Toast.LENGTH_SHORT).show();
            }
        });


    }


}