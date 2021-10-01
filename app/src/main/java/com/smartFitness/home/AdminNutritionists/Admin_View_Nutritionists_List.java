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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.smartFitness.home.Admin.Admin_profile_activity;
import com.smartFitness.home.AdminCommon.Admin_view_Activity;
import com.smartFitness.home.AdminCommon.MainAdminLogin;
import com.smartFitness.home.AppCommon.MainActivity;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.R;

public class Admin_View_Nutritionists_List extends AppCompatActivity {

    Button btn_add;
    Button btn_delete;
    String emailExtra;

    String ntr_name[] = {"John", "smith"};
    String ntr_location[] = {"Kandy", "Colombo"};
    String ntr_phone[] = {"0714186616","0765509315"};
    int person_image[] = {R.drawable.person,R.drawable.person};

    DBHelperNutritionist dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_nutritionists_list);

        Intent avIntent = getIntent();
        String emailextra = avIntent.getStringExtra("emailaddress");

        dbHelper = new DBHelperNutritionist(this);
        btn_add= findViewById(R.id.btn_ntr_AddNew);
        btn_delete= findViewById(R.id.btn_delete1);

    }

    protected void onResume() {
        super.onResume();


        //Log out
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_View_Nutritionists_List.this, Add_new_nutritionist.class);
                startActivity(intent);

                Context context = getApplicationContext();
                Toast.makeText(context, "Page Loading", Toast.LENGTH_SHORT).show();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteNutritionists(emailExtra);
                Context context = getApplicationContext();
                Toast.makeText(context,"Deleting..",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Admin_View_Nutritionists_List.this, Admin_View_Nutritionists_List.class);
                startActivity(intent);
            }
        });
    }
    class CustomeAdapter extends ArrayAdapter<String>{
        Context context;

        CustomeAdapter(Context context, String[] ntr_name) {
            super(context,R.layout.single_row_n,R.id.ntr_name,ntr_name);
            this.context  = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

           /* inflater.inflate(R.layout.single_row_n,parent,attachToRoot, false);*/
            return super.getView(position, convertView, parent);
        }
    }
    }
