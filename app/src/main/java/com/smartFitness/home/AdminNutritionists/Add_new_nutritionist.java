package com.smartFitness.home.AdminNutritionists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.R;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class Add_new_nutritionist extends AppCompatActivity {

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

    DBHelperNutritionist dbHelper;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_nutritionist);


        // get intent object
        Intent nutritionistsListIntent = getIntent();
        emailExtra = nutritionistsListIntent.getStringExtra("emailaddress");

        //validation object created
        awesomeValidation = new AwesomeValidation (BASIC);

        //dbHelper nutritionist object
        dbHelper = new DBHelperNutritionist(this);

        //get elements by ID
        et_Name = findViewById(R.id.et_ntr_Name);
        et_location = findViewById(R.id.et_ntr_Location);
        et_mobileNumber = findViewById(R.id.et_ntrContactNumber);
        et_email = findViewById(R.id.et_ntr_email);
        et_description = findViewById(R.id.et_ntr_Description);
        //btn_add_photo = findViewById(R.id.btn_ntr_uploadImage);
        btn_save = findViewById(R.id.btn_ntr_Save);
        btn_cancel = findViewById(R.id.btn_ntr_cancel);

        //validations
        awesomeValidation.addValidation(Add_new_nutritionist.this, R.id.et_ntr_Name, "[a-zA-Z\\s]+", R.string.err_name);
        awesomeValidation.addValidation(Add_new_nutritionist.this, R.id.et_ntrContactNumber, RegexTemplate.TELEPHONE, R.string.err_tel);
        awesomeValidation.addValidation(Add_new_nutritionist.this, R.id.et_ntr_email, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Cancel new nutrition
        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                //pass intent object
                Intent intent = new Intent(Add_new_nutritionist.this, Admin_View_Nutritionists_List.class);
                intent.putExtra("emailaddress",emailExtra);
                startActivity(intent);
            }
        });

        //save new nutrition
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
                    String description = et_description.getText().toString();


                    //pass data to DBHelperNutritionist and return "val"
                    boolean val = dbHelper.addNutritionist(Name, location, email, mobileNumber, description);


                        //check "val" variable, if addNutritionist() Success return true
                        if(val == true){

                            //pass intent to same page
                            Intent intent = new Intent(Add_new_nutritionist.this, Add_new_nutritionist.class);
                            intent.putExtra("emailaddress",emailExtra);
                            startActivity(intent);

                            //Toast message
                            Toast.makeText(Add_new_nutritionist.this,"Add Success",Toast.LENGTH_SHORT).show();

                        }else{
                            //Toast message
                            Toast.makeText(Add_new_nutritionist.this,"Add fail",Toast.LENGTH_SHORT).show();
                        }
                }
                else{
                    //Toast message
                    Toast.makeText(Add_new_nutritionist.this,"Invalid details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}