package com.smartFitness.home.AdminWeightTrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartFitness.home.AdminCommon.Admin_view_Activity;
import com.smartFitness.home.AdminNutritionists.Add_new_nutritionist;
import com.smartFitness.home.AdminNutritionists.Admin_View_Nutritionists_List;
import com.smartFitness.home.AdminNutritionists.NutritionistListView;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.DataBase.DBHelperWeightTrainer;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.Model.WeightTrainer;
import com.smartFitness.home.R;

import java.util.List;

public class Admin_View_WeightTrainerList extends AppCompatActivity {

    //declare variables
    FloatingActionButton btn_wt_AddNew;
    Button btn_wt_menu;
    String emailExtra;
    ListView listView;

    TextView menuTab_nt;
    TextView menuTab_cd;

    List<WeightTrainer> weightTrainer;

    DBHelperWeightTrainer dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_weight_trainer_list2);

        //get intend object
        Intent avIntent = getIntent();
        emailExtra = avIntent.getStringExtra("emailaddress");

        //nutritionist tab
        menuTab_nt = findViewById(R.id.menu_tab_nt);

        // dbHelper weightTrainer object created
        dbHelper = new DBHelperWeightTrainer(this);

        // Get weightTrainers form data base
        weightTrainer = dbHelper.getAllWeightTrainers();

        //get elements by id
        btn_wt_menu = findViewById(R.id. btn_wt_menu);
        btn_wt_AddNew = findViewById(R.id. btn_wt_AddNew);

        //get list view by id
        listView = findViewById(R.id.lv_WeightTrainer);

        //WeightTrainer list view object creation and set adapter
        WeightTrainerListView adapter = new WeightTrainerListView (Admin_View_WeightTrainerList.this,weightTrainer,emailExtra);
        listView.setAdapter(adapter);

    }

    protected void onResume() {
        super.onResume();

        //add button
        btn_wt_AddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pass intent
                Intent intent = new Intent(Admin_View_WeightTrainerList.this, Admin_View_AddWeightTrainer.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);

                //Toast message
                Context context = getApplicationContext();
                Toast.makeText(context, "Page Loading", Toast.LENGTH_SHORT).show();
            }
        });

        //Menu button
        btn_wt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast message
                Context context = getApplicationContext();
                Toast.makeText(context,"Menu Loading",Toast.LENGTH_SHORT).show();

                //pass intent
                Intent intent = new Intent(Admin_View_WeightTrainerList.this, Admin_view_Activity.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);
            }
        });

        //load current page
        menuTab_nt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast message
                Context context = getApplicationContext();
                Toast.makeText(context,"Weight trainers page loading",Toast.LENGTH_SHORT).show();

                //pass intent
                Intent intent = new Intent(Admin_View_WeightTrainerList.this, Admin_View_Nutritionists_List.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);
            }
        });
    }

}