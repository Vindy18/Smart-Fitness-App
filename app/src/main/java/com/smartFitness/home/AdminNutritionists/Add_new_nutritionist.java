package com.smartFitness.home.AdminNutritionists;

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
import com.smartFitness.home.AdminCommon.Admin_view_Activity;
import com.smartFitness.home.DataBase.DBHelper;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.R;

public class Add_new_nutritionist extends AppCompatActivity {


    EditText et_Name;
    EditText et_location;
    EditText et_mobileNumber;
    EditText et_email;
    EditText et_description;
    Button btn_add_photo;
    Button btn_save;
    Button btn_cancel;


    DBHelperNutritionist dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_nutritionist);

        // get intent object
        Intent nutritionistsListIntent = getIntent();



        dbHelper = new DBHelperNutritionist(this);
        et_Name = findViewById(R.id.et_ntrName);
        et_location = findViewById(R.id.et_ntrLocation);
        et_mobileNumber = findViewById(R.id.et_ntrContactNumber);
        et_email = findViewById(R.id.et_ntrEmail);
        et_description = findViewById(R.id.et_ntrDescription);
        //btn_add_photo = findViewById(R.id.btn_ntr_uploadImage);
        btn_save = findViewById(R.id.btn_ntr_save);
        btn_cancel = findViewById(R.id.btn_ntr_cancel);


    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Add_new_nutritionist.this, Admin_View_Nutritionists_List.class);
                startActivity(intent);
            }
        });

        // save new nutrition
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // tack tha values from view activity_add_new_nutritionist.xml file
                String Name = et_Name.getText().toString();
                String location = et_location.getText().toString();
                String mobileNumber = et_mobileNumber.getText().toString();
                String email = et_email.getText().toString() ;
                String description = et_description.getText().toString() ;

                //pass data to DataBase/DbHelpernutritionist and return "val"
                boolean val = dbHelper.addNutritionist(Name,location ,email,mobileNumber,description);

                //check "val" variable if addNutritionist() Success return true
                if(val == true){
                    Intent intent = new Intent(Add_new_nutritionist.this, Add_new_nutritionist.class);
                    startActivity(intent);

                    Toast.makeText(Add_new_nutritionist.this,"Add Success",Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(Add_new_nutritionist.this,"Add fail",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}