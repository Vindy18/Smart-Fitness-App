package com.smartFitness.home.Admin;

import androidx.appcompat.app.AppCompatActivity;

import com.smartFitness.home.AdminNutritionists.Admin_View_Nutritionists_List;
import com.smartFitness.home.AdminNutritionists.NutritionistListView;
import com.smartFitness.home.DataBase.DBHelper;
import com.smartFitness.home.Model.Admin;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Admin_List_Activity extends AppCompatActivity {

    Button btn_back;

    String emailExtra;

    List<Admin> adminsList;

    DBHelper dbHelper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);

        Intent intent = getIntent();
        emailExtra = intent.getStringExtra("emailaddress");

        btn_back = findViewById(R.id.btn_adminsBack);

        dbHelper = new DBHelper(this);
        adminsList = dbHelper.getAllAdmins();

        listView = findViewById(R.id.lv_adminlist);
        AdminList adapter = new AdminList(Admin_List_Activity.this,adminsList);
        listView.setAdapter(adapter);

    }

    protected void onResume() {
        super.onResume();

        //move edit page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_List_Activity.this, Admin_profile_activity.class);
                intent.putExtra("emailaddress",emailExtra);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context,"Admin Profile Loading",Toast.LENGTH_SHORT).show();
            }
        });
    }
}