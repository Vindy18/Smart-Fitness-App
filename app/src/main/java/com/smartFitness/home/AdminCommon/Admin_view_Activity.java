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
import com.smartFitness.home.R;

public class Admin_view_Activity extends AppCompatActivity {

    // variables
    Button btn_addAdmin;
    ImageButton btn_adminProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        // get intent object
        Intent loginIntent = getIntent();

        // get elements by id
        btn_addAdmin = findViewById(R.id.btn_addAdmin);
        btn_adminProfile = findViewById(R.id.btn_adminProfile);

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
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Admin profile Loading",Toast.LENGTH_SHORT).show();
            }
        });
    }


}