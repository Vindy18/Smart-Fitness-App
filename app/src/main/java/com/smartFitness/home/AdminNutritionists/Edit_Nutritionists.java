package com.smartFitness.home.AdminNutritionists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.R;

public class Edit_Nutritionists extends AppCompatActivity {

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
    String nutritionistEmail;

    Nutritionist nutritionist;
    DBHelperNutritionist dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nutritionists);

        // get intent object
        Intent nutritionistsListIntent = getIntent();
        emailExtra = nutritionistsListIntent.getStringExtra("emailaddress");
        nutritionistEmail = nutritionistsListIntent.getStringExtra("nutritionistemail");


        dbHelper = new DBHelperNutritionist(this);

        //get element by id
        et_Name = findViewById(R.id.et_ntr_editName);
        et_location = findViewById(R.id.et_ntr_editLocation);
        et_mobileNumber = findViewById(R.id.et_ntr_editContactNumber);
        et_email = findViewById(R.id.et_ntr_editEmail);
        et_description = findViewById(R.id.et_ntr_editDescription);
        //btn_add_photo = findViewById(R.id.btn_ntr_uploadImage);
        btn_save = findViewById(R.id.btn_ntr_editSave);
        btn_cancel = findViewById(R.id.btn_ntr_editCancel);

        //get current nutrition details to Edit texts
        nutritionist = dbHelper.getNutritionist(nutritionistEmail);
        et_Name.setText(nutritionist.name);
        et_location.setText(nutritionist.location);
        et_email.setText(nutritionist.email);
        et_mobileNumber.setText(nutritionist.mobileNumber);
        et_description.setText(nutritionist.description);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //cancel button
        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Edit_Nutritionists.this, Admin_View_Nutritionists_List.class);
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
                int val = dbHelper.updateNutritionist(nutritionistEmail,Name,location,email,mobileNumber,description);

                //check "val" variable, if addNutritionist() Success success return grater than 0 value
                if (val > 0){
                    //Toast massage
                    Context context = getApplicationContext();
                    Toast.makeText(context,"Update Successful",Toast.LENGTH_SHORT).show();

                    //pass intent to same page
                    Intent intent = new Intent(Edit_Nutritionists.this, Admin_View_Nutritionists_List.class);
                    intent.putExtra ("emailaddress",email);
                    startActivity(intent);
                }
                else{
                    //Toast massage
                    Context context = getApplicationContext();
                    Toast.makeText(context,"Update Fail",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}