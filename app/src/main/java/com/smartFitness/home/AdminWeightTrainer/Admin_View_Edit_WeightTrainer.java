package com.smartFitness.home.AdminWeightTrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.smartFitness.home.Admin.Admin_profile_activity;
import com.smartFitness.home.Admin.Edit_admin_activity;
import com.smartFitness.home.DataBase.DBHelper;
import com.smartFitness.home.DataBase.DBHelperWeightTrainer;
import com.smartFitness.home.Model.Admin;
import com.smartFitness.home.R;

public class Admin_View_Edit_WeightTrainer extends AppCompatActivity {

    ImageButton btn_ImageView;
    String emailExtra;
    EditText et_name;
    EditText et_address;
    EditText et_contactNumber;
    EditText et_workingHours;
    EditText et_about;
    Button btn_save;

    DBHelperWeightTrainer dbHelperWeightTrainer;
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_edit_weight_trainer);
        // get intent object
        Intent adminPageIntent = getIntent();
        emailExtra = adminPageIntent.getStringExtra("emailaddress");

        btn_ImageView= findViewById(R.id.btn_imageAdminEdit);
        dbHelperWeightTrainer = new DBHelperWeightTrainer(this);
        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);
        et_contactNumber= findViewById(R.id.et_contactNumber);
        et_workingHours = findViewById(R.id.et_workinghours);
        et_about = findViewById(R.id.et_about);
        btn_save= findViewById(R.id.button_edit);




    }
    @Override
    protected void onResume() {
        super.onResume();

        btn_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Admin_View_Edit_WeightTrainer.this, Admin_profile_activity.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Admin profile Loading",Toast.LENGTH_SHORT).show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String Name = et_name.getText().toString();
                String Address = et_address.getText().toString();
                String ContactNumber = et_contactNumber.getText().toString();
                String email = et_workingHours.getText().toString();
                String about = et_about.getText().toString();
            }

            //int val = dbHelperWeightTrainer.updateWeightTrainer(et_name,et_address,et_contactNumber,et_workingHours,et_about);

            // if (val > 0){
            // Context context = getApplicationContext();
            // Toast.makeText(context,"Update Successful",Toast.LENGTH_SHORT).show();
            // Intent intent = new Intent(Admin_View_Edit_WeightTrainer.this, Edit_admin_activity.class);
            // intent.putExtra ("emailaddress",email);
            // startActivity(intent);
            //}
            // else{
            // Context context = getApplicationContext();)
            //Toast.makeText(context,"Update Fail",Toast.LENGTH_SHORT).show();
            //}

            // }
            });

        };
}
