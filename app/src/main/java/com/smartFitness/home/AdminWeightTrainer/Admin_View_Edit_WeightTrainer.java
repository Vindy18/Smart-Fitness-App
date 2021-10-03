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
import com.smartFitness.home.DataBase.DBHelperWeightTrainer;
import com.smartFitness.home.DataBase.WeightTrainerMaster;
import com.smartFitness.home.DataBase.WeightTrainerMaster.WeightTrainer;
import com.smartFitness.home.Model.Admin;
import com.smartFitness.home.R;

public class Admin_View_Edit_WeightTrainer<name> extends AppCompatActivity {

    ImageButton btn_ImageView;
    String emailExtra;
    EditText et_name;
    EditText et_location;
    EditText et_contactNumber;
    EditText et_email;
    EditText et_about;
    Button btn_save;
    Button btn_cancel;

    //String emailExtra;
    String WeightTraineremail;

    //WeightTrainer
    DBHelperWeightTrainer dbHelperWeightTrainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_edit_weight_trainer);
        // get intent object
        Intent adminPageIntent = getIntent();
        emailExtra = adminPageIntent.getStringExtra("emailaddress");
        //WeightTraineremail = WeightTrainerListIntent.getStringExtra("nutritionistemail");

        dbHelperWeightTrainer = new DBHelperWeightTrainer(this);

        et_name = findViewById(R.id.et_name);
        et_location= findViewById(R.id.et_location);
        et_contactNumber= findViewById(R.id.et_contactNumber);
        et_email=findViewById(R.id.et_email);
        et_about = findViewById(R.id.et_about);

        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);

        //get current weightTrainer details to Edit texts
        //nutritionist = dbHelper.getNutritionist(nutritionistEmail);
       // et_name.setText(nutritionist.name);
        //et_location.setText(nutritionist.location);
       // et_email.setText(nutritionist.email);
        //et_mobileNumber.setText(nutritionist.mobileNumber);
        //et_description.setText(nutritionist.description);


    }
    @Override
    protected void onResume() {
        super.onResume();


        btn_save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Admin_View_Edit_WeightTrainer.this, Admin_profile_activity.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Admin profile Loading",Toast.LENGTH_SHORT).show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String Name = et_name.getText().toString();
               // String Address = et_address.getText().toString();
                String ContactNumber = et_contactNumber.getText().toString();
                //String email = et_workingHours.getText().toString();
                String about = et_about.getText().toString();
            }


            //int val = dbHelperWeightTrainer.updateWeightTrainer(et_name,et_address,et_contactNumber,et_workingHours,et_about);

             //if (val > 0)
             {
             Context context = getApplicationContext();
            Toast.makeText(context,"Update Successful",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Admin_View_Edit_WeightTrainer.this, Edit_admin_activity.class);
            intent.putExtra ("id",emailExtra);
            startActivity(intent);
            }
           // else
                {
             Context context = getApplicationContext();
            Toast.makeText(context,"Update Fail",Toast.LENGTH_SHORT).show();
            }


            });

        };
}
