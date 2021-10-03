package com.smartFitness.home.CustomerNutritionists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.smartFitness.home.AdminCommon.Admin_view_Activity;
import com.smartFitness.home.AdminNutritionists.Admin_View_Nutritionists_List;
import com.smartFitness.home.AdminNutritionists.NutritionistListView;
import com.smartFitness.home.CustomerCommon.Customer_view_Activity;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.R;

import java.util.List;

public class Customer_View_Nutritionists_List extends AppCompatActivity {

    //variable
    Button btn_nutCV_Menu;

    ListView listView;
    DBHelperNutritionist dbHelper;

    List<Nutritionist> nutritionist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_nutritionists_list);

        //get intent
        Intent Intent = getIntent();

        //get element by ID
        btn_nutCV_Menu = findViewById(R.id.btn_nutCV_Menu);

        //Database Connection
        dbHelper = new DBHelperNutritionist(this);

        // Get nutrition form data base
        nutritionist = dbHelper.getAllNutritionists();

        // lord Lay out constrain lay out
        listView = (ListView) findViewById(R.id.lv_CvNutrition);
        NutritionistListCvView  adapter = new NutritionistListCvView (Customer_View_Nutritionists_List.this,nutritionist);
        listView.setAdapter(adapter);

        // event Listener for Menu button
        btn_nutCV_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Customer_View_Nutritionists_List.this, Customer_view_Activity.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Nutritionists are Loading ",Toast.LENGTH_SHORT).show();
            }
        });
    }

}

