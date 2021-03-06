package com.smartFitness.home.AdminNutritionists;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartFitness.home.Admin.Admin_profile_activity;
import com.smartFitness.home.Admin.Edit_admin_activity;
import com.smartFitness.home.AdminCommon.Admin_view_Activity;
import com.smartFitness.home.AdminCommon.MainAdminLogin;
import com.smartFitness.home.AppCommon.MainActivity;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.R;

import java.util.List;

public class Admin_View_Nutritionists_List extends AppCompatActivity {

    FloatingActionButton btn_add;
    Button btn_nut_menu;
    String emailExtra;
    ListView listView;

    List<Nutritionist> nutritionist;

    DBHelperNutritionist dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_nutritionists_list);

        Intent avIntent = getIntent();
        emailExtra = avIntent.getStringExtra("emailaddress");

        // db connection
        dbHelper = new DBHelperNutritionist(this);

        // Get nutrition form data base
        nutritionist = dbHelper.getAllNutritionists();

        btn_add= findViewById(R.id.btn_ntr_AddNew);
        btn_nut_menu= findViewById(R.id.btn_nut_menu);

        listView = findViewById(R.id.lv_Nutrition);
        NutritionistListView  adapter = new NutritionistListView (Admin_View_Nutritionists_List.this,nutritionist,emailExtra);
        listView.setAdapter(adapter);

    }


    protected void onResume() {
        super.onResume();

        //add button
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_View_Nutritionists_List.this, Add_new_nutritionist.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context, "Page Loading", Toast.LENGTH_SHORT).show();
            }
        });

        //Menu button
        btn_nut_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                Toast.makeText(context,"Menu Loading",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Admin_View_Nutritionists_List.this, Admin_view_Activity.class);
                intent.putExtra ("emailaddress",emailExtra);
                startActivity(intent);
            }
        });
    }

}
