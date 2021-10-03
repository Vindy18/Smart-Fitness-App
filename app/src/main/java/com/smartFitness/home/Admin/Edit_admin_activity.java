package com.smartFitness.home.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.smartFitness.home.DataBase.DBHelper;
import com.smartFitness.home.Model.Admin;
import com.smartFitness.home.R;

public class Edit_admin_activity extends AppCompatActivity {

    ImageButton btn_adminProfile;
    Button btn_save;

    EditText et_firstName;
    EditText et_lastName;
    EditText et_city;
    EditText et_mobileNumber;
    EditText et_email;

    String emailExtra;

    DBHelper dbHelper;
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin);

        // get intent object
        Intent adminPageIntent = getIntent();
        emailExtra = adminPageIntent.getStringExtra("emailaddress");

        btn_adminProfile = findViewById(R.id.btn_imageAdminEdit);
        et_firstName = findViewById(R.id.et_admineditFirstName);
        et_lastName = findViewById(R.id.et_adminEditLastName);
        et_city = findViewById(R.id.et_adminEditCity);
        et_mobileNumber = findViewById(R.id.et_adminEditmobilenumber);
        et_email = findViewById(R.id.et_adminEditEmail);
        btn_save= findViewById(R.id.btn_editAdmin);

        dbHelper = new DBHelper(this);
        admin = dbHelper.getAdmin(emailExtra);

        et_firstName.setText(admin.firstName);
        et_lastName.setText(admin.lastName);
        et_city.setText(admin.city);
        et_email.setText(admin.email);
        et_mobileNumber.setText(admin.mobileNumber);


    }
    @Override
    protected void onResume() {
        super.onResume();

        btn_adminProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Edit_admin_activity.this, Admin_profile_activity.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Admin profile Loading",Toast.LENGTH_SHORT).show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                String firstName = et_firstName.getText().toString();
                String lastName = et_lastName.getText().toString();
                String city = et_city.getText().toString();
                String mobileNumber = et_mobileNumber.getText().toString();
                String email = et_email.getText().toString() ;
                String password = admin.Password;

                int val = dbHelper.updateAdmin(emailExtra,firstName,lastName,city,email,mobileNumber,password);

                if (val > 0){

                    Context context = getApplicationContext();
                    Toast.makeText(context,"Update Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Edit_admin_activity.this, Admin_profile_activity.class);
                    intent.putExtra ("emailaddress",email);
                    startActivity(intent);

                }
                else{

                    Context context = getApplicationContext();
                    Toast.makeText(context,"Update Fail",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}