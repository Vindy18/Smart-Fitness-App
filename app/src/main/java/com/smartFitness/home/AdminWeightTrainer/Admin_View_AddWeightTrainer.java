package com.smartFitness.home.AdminWeightTrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartFitness.home.DataBase.DBHelperWeightTrainer;
import com.smartFitness.home.R;

public class Admin_View_AddWeightTrainer extends AppCompatActivity {

    EditText et_name;
    EditText et_location;
    EditText et_contactnumber;
    EditText et_email;
    EditText et_about;
    Button btn_save;
    Button btn_cancel;

    String emailExtra;

    DBHelperWeightTrainer dbHelperWeightTrainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_add_weight_trainer);

        // get intent object
        Intent weightTrainerIntent = getIntent();

        dbHelperWeightTrainer = new DBHelperWeightTrainer(this);
        et_name = findViewById(R.id.et_ntr_editName);
        et_location = findViewById(R.id.et_ntr_editLocation);
        et_contactnumber = findViewById(R.id.et_ntr_editContactNumber);
        et_about = findViewById(R.id.et_ntr_editDescription);
        btn_save = findViewById(R.id.btn_ntr_editSave);
        btn_cancel = findViewById(R.id.btn_ntr_cancel);
    }
    @Override
    protected void onResume() {
        super.onResume();

        btn_cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                //Intent intent = new Intent(Admin_View_AddWeightTrainer.this, Admin_View_WeightTrainerList.class);
                //intent.putExtra("emailaddress",emailExtra);
                //startActivity(intent);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String address = et_location.getText().toString();
                String contactnumber = et_contactnumber.getText().toString();
                String email = et_email.getText().toString() ;
                String about = et_about.getText().toString();

                boolean val = dbHelperWeightTrainer.addWeightTrainer(name,address,contactnumber,email,about);

                if(val == true){
                    Toast.makeText(Admin_View_AddWeightTrainer.this,"Add Success",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Admin_View_AddWeightTrainer.this,"Add fail",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
