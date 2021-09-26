package com.smartFitness.home.AdminWeightTrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartFitness.home.Admin.Add_admin_activity;
import com.smartFitness.home.DataBase.DBHelper;
import com.smartFitness.home.DataBase.DBHelperWeightTrainer;
import com.smartFitness.home.R;

public class Admin_View_AddWeightTrainer extends AppCompatActivity {

    EditText et_name;
    EditText et_address;
    EditText et_contactnumber;
    EditText et_workinghours;
    EditText et_about;
    Button btn_add;

    DBHelperWeightTrainer dbHelperWeightTrainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_add_weight_trainer);

        // get intent object
        Intent weightTrainerIntent = getIntent();

        dbHelperWeightTrainer = new DBHelperWeightTrainer(this);
        et_name = findViewById(R.id.et_wtName);
        et_address = findViewById(R.id.et_wtaddress);
        et_contactnumber = findViewById(R.id.et_wtContactnumber);
        et_workinghours= findViewById(R.id.et_workinghours);
        et_about = findViewById(R.id.et_about);
        btn_add= findViewById(R.id.btn_addNewWeightTrainer);
    }
    @Override
    protected void onResume() {
        super.onResume();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String address = et_address.getText().toString();
                String contactnumber = et_contactnumber.getText().toString();
                String workinghours = et_workinghours.getText().toString();
                String about = et_about.getText().toString();
                boolean val;
                val = dbHelperWeightTrainer.addWeightTrainer(name,address,contactnumber,workinghours,about);

                if(val == true){
                    Toast.makeText(Admin_View_AddWeightTrainer.this,"Add Success",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Admin_View_AddWeightTrainer.this,"Add fail",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
