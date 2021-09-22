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

import com.smartFitness.home.AdminCommon.Admin_view_Activity;
import com.smartFitness.home.DataBase.DBHelper;
import com.smartFitness.home.R;

public class Add_admin_activity extends AppCompatActivity {

    ImageButton btn_adminProfile;
    EditText et_firstName;
    EditText et_lastName;
    EditText et_city;
    EditText et_mobileNumber;
    EditText et_email;
    EditText et_password;
    Button btn_add;
    String emailExtra;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        // get intent object
        Intent menuIntent = getIntent();
        emailExtra = menuIntent.getStringExtra("emailaddress");

        dbHelper = new DBHelper(this);
        btn_adminProfile = findViewById(R.id.btn_imageAdminAdd);
        et_firstName = findViewById(R.id.et_addfirstName);
        et_lastName = findViewById(R.id.et_addlastName);
        et_city = findViewById(R.id.et_addcity);
        et_mobileNumber = findViewById(R.id.et_addmobilenumber);
        et_email = findViewById(R.id.et_addEmail);
        et_password = findViewById(R.id.et_addpassword);
        btn_add= findViewById(R.id.btn_addNewAdmin);

    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_adminProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Add_admin_activity.this, Admin_profile_activity.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Admin profile Loading",Toast.LENGTH_SHORT).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = et_firstName.getText().toString();
                String lastName = et_lastName.getText().toString();
                String city = et_city.getText().toString();
                String mobileNumber = et_mobileNumber.getText().toString();
                String email = et_email.getText().toString() ;
                String password = et_email.getText().toString() ;

             boolean val = dbHelper.addAdmin(firstName,lastName,city,email,mobileNumber,password);

                if(val == true){
                    Toast.makeText(Add_admin_activity.this,"Add Success",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Add_admin_activity.this,"Add fail",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}