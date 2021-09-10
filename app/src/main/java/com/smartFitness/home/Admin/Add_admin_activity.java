package com.smartFitness.home.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartFitness.home.DataBase.DBHelper;
import com.smartFitness.home.R;

public class Add_admin_activity extends AppCompatActivity {

    EditText et_firstName;
    EditText et_lastName;
    EditText et_city;
    EditText et_mobileNumber;
    EditText et_email;
    EditText et_password;
    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        // get intent object
        Intent loginIntent = getIntent();

        et_firstName = findViewById(R.id.et_addfirstName);
        et_lastName = findViewById(R.id.et_addlastName);
        et_city = findViewById(R.id.et_addcity);
        et_mobileNumber = findViewById(R.id.et_addmobilenumber);
        et_email = findViewById(R.id.et_addEmail);
        et_password = findViewById(R.id.et_password);
        btn_add= findViewById(R.id.btn_addNewAdmin);


    }


    public void addDate(View view){
        DBHelper dbHelper = new DBHelper(this);

        String firstName = et_firstName.getText().toString();
        String lastName = et_lastName.getText().toString();
        String city = et_city.getText().toString();
        String mobileNumber = et_mobileNumber.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        long val = dbHelper.addAdmin(firstName,lastName,city,mobileNumber,email,password);

        //Context context = getApplicationContext();

        if(val>0){
            Toast.makeText(this,"Add Success",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Add fail",Toast.LENGTH_SHORT).show();
        }
    }
}