package com.smartFitness.home.AdminWeightTrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.smartFitness.home.AdminNutritionists.Add_new_nutritionist;
import com.smartFitness.home.AdminNutritionists.Admin_View_Nutritionists_List;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.DataBase.DBHelperWeightTrainer;
import com.smartFitness.home.R;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class Admin_View_AddWeightTrainer extends AppCompatActivity {

    //declare variables
    EditText et_Name;
    EditText et_location;
    EditText et_mobileNumber;
    EditText et_email;
    EditText et_description;
    Button btn_add_photo;
    Button btn_save;
    Button btn_cancel;

    String emailExtra;

    DBHelperWeightTrainer dbHelper;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_add_weight_trainer);

        //validation object created
        awesomeValidation = new AwesomeValidation (BASIC);

        // get intent object
        Intent Intent = getIntent();
        emailExtra = Intent.getStringExtra("emailaddress");

        //dbHelper weight trainer object
        dbHelper = new DBHelperWeightTrainer(this);

        //get elements by ID
        et_Name = findViewById(R.id.et_name);
        et_location = findViewById(R.id.et_Location);
        et_mobileNumber = findViewById(R.id.et_contactNumber);
        et_email = findViewById(R.id.et_email);
        et_description = findViewById(R.id.et_about);
        //btn_add_photo = findViewById(R.id.btn_ntr_uploadImage);
        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);

        //validations
        awesomeValidation.addValidation(Admin_View_AddWeightTrainer.this, R.id.et_name, "[a-zA-Z\\s]+", R.string.err_name);
        awesomeValidation.addValidation(Admin_View_AddWeightTrainer.this, R.id.et_contactNumber, RegexTemplate.TELEPHONE, R.string.err_tel);
        awesomeValidation.addValidation(Admin_View_AddWeightTrainer.this, R.id.et_email, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Cancel new weightTrainer
        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                //pass intent object
                Intent intent = new Intent(Admin_View_AddWeightTrainer.this, Admin_View_WeightTrainerList.class);
                intent.putExtra("emailaddress",emailExtra);
                startActivity(intent);
            }
        });

        //save new weightTrainer
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check validations
                if (awesomeValidation.validate()) {

                    //get text to variables
                    String Name = et_Name.getText().toString();
                    String location = et_location.getText().toString();
                    String mobileNumber = et_mobileNumber.getText().toString();
                    String email = et_email.getText().toString();
                    String about = et_description.getText().toString();

                    //pass data to DBHelperWeightTrainer and return "val"
                    boolean val = dbHelper.addWeightTrainer(Name, location, mobileNumber, email, about);

                    //check "val" variable, if addWeightTrainer() Success return true
                    if (val == true) {

                        //pass intent to same page
                        Intent intent = new Intent(Admin_View_AddWeightTrainer.this, Admin_View_AddWeightTrainer.class);
                        intent.putExtra("emailaddress", emailExtra);
                        startActivity(intent);

                        //Toast message
                        Toast.makeText(Admin_View_AddWeightTrainer.this, "Add Success", Toast.LENGTH_SHORT).show();

                    } else {
                        //Toast message
                        Toast.makeText(Admin_View_AddWeightTrainer.this, "Add fail", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    //Toast message
                    Toast.makeText(Admin_View_AddWeightTrainer.this, "Invalid details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
