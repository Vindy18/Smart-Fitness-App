package com.smartFitness.home.CustomerWeightTrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartFitness.home.CustomerCommon.Customer_view_Activity;
import com.smartFitness.home.CustomerNutritionists.Customer_View_Nutritionists_List;
import com.smartFitness.home.CustomerNutritionists.NutritionistListCvView;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.DataBase.DBHelperWeightTrainer;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.Model.WeightTrainer;
import com.smartFitness.home.R;

import java.util.List;

public class Customer_View_WeightTrainerList extends AppCompatActivity {

    //declare variables
    Button btn_wtCV_Menu;

    TextView menuTab_nt;
    TextView menuTab_cd;
    TextView menuTab_wt;

    ListView listView;
    DBHelperWeightTrainer dbHelper;

    List<WeightTrainer> weightTrainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_weight_trainer_list);

        //get intent
        Intent Intent = getIntent();

        //get element by ID
        btn_wtCV_Menu = findViewById(R.id.btn_wtCV_Menu);
        menuTab_nt = findViewById(R.id.menu_tab_nt);
        menuTab_wt = findViewById(R.id.menu_tab_wt);

        //dbHelperWeightTrainer object creation
        dbHelper = new DBHelperWeightTrainer(this);

        // Get all weight Trainers form data base
        weightTrainer = dbHelper.getAllWeightTrainers();

        //lord Lay out (constrain lay out)
        listView = (ListView) findViewById(R.id.lv_CvWightTrainer);
        WeightTrainerCvView adapter = new WeightTrainerCvView(Customer_View_WeightTrainerList.this, weightTrainer);
        listView.setAdapter(adapter);

        // event Listener for Menu button
        btn_wtCV_Menu .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pass intent
                Intent intent = new Intent(Customer_View_WeightTrainerList.this, Customer_view_Activity.class);
                startActivity(intent);

                //toast message
                Context context = getApplicationContext();
                Toast.makeText(context,"Menu Loading ",Toast.LENGTH_SHORT).show();
            }
        });

        //moving to nutritionist page
        menuTab_nt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pass intent
                Intent intent = new Intent(Customer_View_WeightTrainerList.this, Customer_View_Nutritionists_List.class);
                startActivity(intent);

                //toast message
                Context context = getApplicationContext();
                Toast.makeText(context, "Nutritionists page loading", Toast.LENGTH_SHORT).show();
            }
        });

        //loading current page
        menuTab_wt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pass intent
                Intent intent = new Intent(Customer_View_WeightTrainerList.this, Customer_View_WeightTrainerList.class);
                startActivity(intent);

                //toast message
                Context context = getApplicationContext();
                Toast.makeText(context, "Weight Trainers page loading", Toast.LENGTH_SHORT).show();
            }
        });
    }
}