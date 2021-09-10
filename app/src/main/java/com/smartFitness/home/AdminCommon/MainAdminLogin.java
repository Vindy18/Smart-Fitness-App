package com.smartFitness.home.AdminCommon;

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

import java.util.List;

public class MainAdminLogin extends AppCompatActivity {

    EditText et_email;
    EditText et_password;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_login);

        Intent mainIntent = getIntent();

        et_email = findViewById(R.id.et_addfirstName);
        et_password = findViewById(R.id.et_password);

    }

    public void PushOnclick(View view){
        Context context = getApplicationContext();
        Toast.makeText(context,"Login Success",Toast.LENGTH_SHORT).show();
    }


    public void Login(View view){

        DBHelper dbHelper = new DBHelper(this);

        List emails = dbHelper.readAllAdminInfo("email");
        List passwords = dbHelper.readAllAdminInfo("Password");

        String email = et_email.getText().toString();
        String password = et_password.getText().toString();


       if(emails.indexOf(email)>=0 ){

            if(passwords.get(emails.indexOf(email)).equals(password )){
                Intent intent = new Intent(MainAdminLogin.this, Admin_view_Activity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Login Success",Toast.LENGTH_SHORT).show();

            }else{

                Context context = getApplicationContext();
                Toast.makeText(context,"Login Fail",Toast.LENGTH_SHORT).show();

            }
        }
    }
}