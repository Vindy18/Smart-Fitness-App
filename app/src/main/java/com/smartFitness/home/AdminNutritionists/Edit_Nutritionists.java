package com.smartFitness.home.AdminNutritionists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartFitness.home.Admin.Edit_admin_activity;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.Model.Nutritionists;
import com.smartFitness.home.R;

public class Edit_Nutritionists extends AppCompatActivity {


    EditText et_Name;
    EditText et_location;
    EditText et_mobileNumber;
    EditText et_email;
    EditText et_description;
    Button btn_add_photo;
    Button btn_save;
    Button btn_cancel;
    String emailExtra;
    Nutritionists nutritionists;

    DBHelperNutritionist dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nutritionists);

        // get intent object
        Intent nutritionistsListIntent = getIntent();
        emailExtra = nutritionistsListIntent.getStringExtra("emailaddress");


        dbHelper = new DBHelperNutritionist(this);
        et_Name = findViewById(R.id.et_ntr_editName);
        et_location = findViewById(R.id.et_ntr_editLocation);
        et_mobileNumber = findViewById(R.id.et_ntr_editContactNumber);
        et_email = findViewById(R.id.et_ntr_editEmail);
        et_description = findViewById(R.id.et_ntr_editDescription);
        //btn_add_photo = findViewById(R.id.btn_ntr_uploadImage);
        btn_save = findViewById(R.id.btn_ntr_editSave);
        btn_cancel = findViewById(R.id.btn_ntr_editCancel);

        nutritionists = dbHelper.getNutritionist(emailExtra);
        et_Name.setText(nutritionists.name);
        et_location.setText(nutritionists.location);
        et_email.setText(nutritionists.email);
        et_mobileNumber.setText(nutritionists.mobileNumber);
        et_description.setText(nutritionists.description);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Edit_Nutritionists.this, Admin_View_Nutritionists_List.class);
                startActivity(intent);
            }
        });


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = et_Name.getText().toString();
                String location = et_location.getText().toString();
                String mobileNumber = et_mobileNumber.getText().toString();
                String email = et_email.getText().toString() ;
                String description = et_description.getText().toString() ;

                int val = dbHelper.updateNutritionist(emailExtra,Name,location,email,mobileNumber,description);

                if (val > 0){
                    Context context = getApplicationContext();
                    Toast.makeText(context,"Update Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Edit_Nutritionists.this, Edit_Nutritionists.class);
                    intent.putExtra ("emailaddress",email);
                    startActivity(intent);
                }
                else{
                    Context context = getApplicationContext();
                    Toast.makeText(context,"Update Fail",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}