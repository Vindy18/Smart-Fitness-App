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
import com.smartFitness.home.AdminCommon.Admin_view_Activity;
import com.smartFitness.home.AdminNutritionists.Add_new_nutritionist;
import com.smartFitness.home.DataBase.DBHelper;
import com.smartFitness.home.R;

public class Add_admin_activity extends AppCompatActivity {

    ImageButton btn_adminProfile;
    Button btn_add;
    Button btn_Cancel;
    EditText et_firstName;
    EditText et_lastName;
    EditText et_city;
    EditText et_mobileNumber;
    EditText et_email;
    EditText et_password;

    String emailExtra;

    DBHelper dbHelper;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        // get intent object
        Intent menuIntent = getIntent();
        emailExtra = menuIntent.getStringExtra("emailaddress");

        dbHelper = new DBHelper(this);
        btn_adminProfile = findViewById(R.id.btn_imageAdminAdd);
        btn_add= findViewById(R.id.btn_addNewAdmin);
        btn_Cancel= findViewById(R.id.btn_CancelNewAdmin);
        et_firstName = findViewById(R.id.et_addfirstName);
        et_lastName = findViewById(R.id.et_addlastName);
        et_city = findViewById(R.id.et_addcity);
        et_mobileNumber = findViewById(R.id.et_addmobilenumber);
        et_email = findViewById(R.id.et_addEmail);
        et_password = findViewById(R.id.et_addpassword);

        awesomeValidation = new AwesomeValidation (BASIC);

        //validations
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        String regexName = "[a-zA-Z\\s]+";

        awesomeValidation.addValidation(Add_admin_activity.this, R.id.et_addfirstName, regexName, R.string.err_name);
        awesomeValidation.addValidation(Add_admin_activity.this, R.id.et_addlastName, regexName, R.string.err_name);
        awesomeValidation.addValidation(Add_admin_activity.this, R.id.et_addmobilenumber, RegexTemplate.TELEPHONE, R.string.err_tel);
        awesomeValidation.addValidation(Add_admin_activity.this, R.id.et_addEmail, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(Add_admin_activity.this, R.id.et_addpassword, regexPassword, R.string.pass);

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

        btn_Cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Add_admin_activity.this, Admin_view_Activity.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Menu Loading",Toast.LENGTH_SHORT).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate()) {

                    String firstName = et_firstName.getText().toString();
                    String lastName = et_lastName.getText().toString();
                    String city = et_city.getText().toString();
                    String mobileNumber = et_mobileNumber.getText().toString();
                    String email = et_email.getText().toString() ;
                    String password = et_password.getText().toString() ;

                    boolean val = dbHelper.addAdmin(firstName,lastName,city,email,mobileNumber,password);

                    if(val == true){

                        Intent intent = new Intent(Add_admin_activity.this, Add_admin_activity.class);
                        intent.putExtra ("emailaddress",emailExtra);
                        startActivity(intent);

                        Toast.makeText(Add_admin_activity.this,"Add Success",Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(Add_admin_activity.this,"Add fail",Toast.LENGTH_SHORT).show();

                    }

                }
                else{

                    //Toast massage
                    Toast.makeText(Add_admin_activity.this,"Invalid details",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}