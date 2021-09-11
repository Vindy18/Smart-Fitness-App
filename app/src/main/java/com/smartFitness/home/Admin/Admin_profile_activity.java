package com.smartFitness.home.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smartFitness.home.AdminCommon.Admin_view_Activity;
import com.smartFitness.home.AdminCommon.MainAdminLogin;
import com.smartFitness.home.AppCommon.MainActivity;
import com.smartFitness.home.CustomerCommon.Customer_view_Activity;
import com.smartFitness.home.R;

import java.util.List;

public class Admin_profile_activity extends AppCompatActivity {

    Button btn_logout;
    Button btn_edit;
    Button btn_delete;
    Button btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        // get intent object
        Intent adminMenuIntent = getIntent();

        btn_logout = findViewById(R.id.btn_logOut);
        btn_edit = findViewById(R.id.btn_adminEdit);
        btn_delete = findViewById(R.id.btn_admindelete);
        btn_menu = findViewById(R.id.btn_adminMenu);

    }

    protected void onResume() {
        super.onResume();

        //Log out
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_profile_activity.this, MainActivity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Log out success",Toast.LENGTH_SHORT).show();
            }
        });

        //move edit page
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_profile_activity.this, Edit_admin_activity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Edit page is Loading",Toast.LENGTH_SHORT).show();
            }
        });

        //move menu page
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_profile_activity.this, Admin_view_Activity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Menu Is Loading",Toast.LENGTH_SHORT).show();
            }
        });

    }
}