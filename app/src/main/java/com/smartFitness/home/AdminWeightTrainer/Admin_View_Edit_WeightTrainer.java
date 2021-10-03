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
import com.smartFitness.home.AdminNutritionists.Admin_View_Nutritionists_List;
import com.smartFitness.home.AdminNutritionists.Edit_Nutritionists;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.DataBase.DBHelperWeightTrainer;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.Model.WeightTrainer;
import com.smartFitness.home.R;

public class Admin_View_Edit_WeightTrainer extends AppCompatActivity {

    //variables
    EditText et_Name;
    EditText et_location;
    EditText et_mobileNumber;
    EditText et_email;
    EditText et_description;
    Button btn_add_photo;
    Button btn_save;
    Button btn_cancel;

    String emailExtra;
    String weightTrainerEmail;

    WeightTrainer weightTrainer;
    DBHelperWeightTrainer dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_edit_weight_trainer);

        // get intent object
        Intent intent = getIntent();
        emailExtra = intent.getStringExtra("emailaddress");
        weightTrainerEmail = intent.getStringExtra("weighttraineremail");

        dbHelper = new DBHelperWeightTrainer(this);

        et_Name = findViewById(R.id.et_editName);
        et_location = findViewById(R.id.et_editLocation);
        et_mobileNumber = findViewById(R.id.et_editContactNumber);
        et_email = findViewById(R.id.et_editEmail);
        et_description = findViewById(R.id.et_editAbout);
        //btn_add_photo = findViewById(R.id.btn_ntr_uploadImage);
        btn_save = findViewById(R.id.btn_editSave);
        btn_cancel = findViewById(R.id.btn_editCancel);

        //get current Weight trainer details to Edit texts
        weightTrainer = dbHelper.getWeightTrainers(weightTrainerEmail);
        et_Name.setText(weightTrainer.name);
        et_location.setText(weightTrainer.location);
        et_email.setText(weightTrainer.email);
        et_mobileNumber.setText(weightTrainer.contactNumber);
        et_description.setText(weightTrainer.about);

    }
    @Override
    protected void onResume() {
        super.onResume();

        //cancel button
        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Admin_View_Edit_WeightTrainer.this, Admin_View_WeightTrainerList.class);
                intent.putExtra("emailaddress",emailExtra);
                startActivity(intent);
            }
        });

        //update button
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //assign values to variable
                String Name = et_Name.getText().toString();
                String location = et_location.getText().toString();
                String mobileNumber = et_mobileNumber.getText().toString();
                String email = et_email.getText().toString() ;
                String description = et_description.getText().toString() ;

                //pass the assigned values to DBHelperNutritionist and Return "val"
                int val = dbHelper.updateWeightTrainer(weightTrainerEmail,Name,location,email,mobileNumber,description);

                //check "val" variable, if addNutritionist() Success success return grater than 0 value
                if (val > 0){
                    //Toast massage
                    Context context = getApplicationContext();
                    Toast.makeText(context,"Update Successful",Toast.LENGTH_SHORT).show();

                    //pass intent to same page
                    Intent intent = new Intent(Admin_View_Edit_WeightTrainer.this, Admin_View_WeightTrainerList.class);
                    intent.putExtra ("emailaddress",emailExtra);
                    startActivity(intent);
                }
                else{
                    //Toast massage
                    Context context = getApplicationContext();
                    Toast.makeText(context,"Update Fail",Toast.LENGTH_SHORT).show();
                }

            }
        });


        };
}
