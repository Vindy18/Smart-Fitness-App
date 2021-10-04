package com.smartFitness.home.Admin;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.smartFitness.home.DataBase.DBHelper;
import com.smartFitness.home.Model.Admin;
import com.smartFitness.home.R;

public class Edit_admin_pass_activity extends AppCompatActivity {

    ImageButton btn_adminProfile;
    Button btn_change;

    EditText et_oldPass;
    EditText et_pass;
    EditText et_confirmPass;

    String emailExtra;

    DBHelper dbHelper;
    Admin admin;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_pass);

        Intent adminProfileIntent = getIntent();
        emailExtra = adminProfileIntent.getStringExtra("emailaddress");

        btn_adminProfile= findViewById(R.id.btn_imageAdminChegepass);
        btn_change = findViewById(R.id.btn_passwordChange);
        et_oldPass = findViewById(R.id.et_adminOldPass);
        et_pass = findViewById(R.id.et_adminChangepass);
        et_confirmPass = findViewById(R.id.et_adminChangeconfermpass);


        dbHelper = new DBHelper(this);
        admin = dbHelper.getAdmin(emailExtra);

        awesomeValidation = new AwesomeValidation (BASIC);

        //validations
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        String regexName = "[a-zA-Z\\s]+";

        awesomeValidation.addValidation(Edit_admin_pass_activity.this, R.id.et_adminChangepass, regexPassword, R.string.pass);
        awesomeValidation.addValidation(Edit_admin_pass_activity.this, R.id.et_adminChangeconfermpass, regexPassword, R.string.pass);

    }
    @Override
    protected void onResume() {
        super.onResume();

        btn_adminProfile.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Edit_admin_pass_activity.this, Admin_profile_activity.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Admin profile Loading",Toast.LENGTH_SHORT).show();
            }
        });

        btn_change.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                if (awesomeValidation.validate()) {

                    String firstName = admin.firstName;
                    String lastName = admin.lastName;
                    String city = admin.city;
                    String mobileNumber = admin.mobileNumber;
                    String email = admin.email;
                    String oldPass = et_oldPass.getText().toString();
                    String password = et_pass.getText().toString();
                    String confirmPassword = et_confirmPass.getText().toString();

                    if (oldPass.equals(admin.Password)) {

                        if (password.equals(confirmPassword)) {
                            int val = dbHelper.updateAdmin(emailExtra, firstName, lastName, city, email, mobileNumber, confirmPassword);

                            if (val > 0) {

                                Context context = getApplicationContext();
                                Toast.makeText(context, "Password Change Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Edit_admin_pass_activity.this, Admin_profile_activity.class);
                                intent.putExtra("emailaddress", emailExtra);
                                startActivity(intent);

                            } else {

                                Context context = getApplicationContext();
                                Toast.makeText(context, "Password Change Fail", Toast.LENGTH_SHORT).show();

                            }
                        } else {

                            Context context = getApplicationContext();
                            Toast.makeText(context, "Confirm Password Miss Match", Toast.LENGTH_SHORT).show();

                        }

                    } else {

                        Context context = getApplicationContext();
                        Toast.makeText(context, "Old Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    //Toast massage
                    Toast.makeText(Edit_admin_pass_activity.this, "Invalid details", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}