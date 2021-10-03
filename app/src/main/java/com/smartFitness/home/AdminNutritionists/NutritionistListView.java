package com.smartFitness.home.AdminNutritionists;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smartFitness.home.Admin.Admin_profile_activity;
import com.smartFitness.home.AdminCommon.MainAdminLogin;
import com.smartFitness.home.CustomerNutritionists.Customer_View_Nutritionist_Profile_View;
import com.smartFitness.home.DataBase.DBHelper;
import com.smartFitness.home.DataBase.DBHelperNutritionist;
import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.R;

import java.util.List;

public class NutritionistListView extends ArrayAdapter<Nutritionist> {
    Context context;

    List<Nutritionist> nutritionists;

    String emailExtra;

    NutritionistListView(Context context,List<Nutritionist> nutritionists,String emailExtra ) {
        super(context, R.layout.single_row_n,nutritionists);
        this.context  = context;
        this.nutritionists = nutritionists;
        this.emailExtra = emailExtra;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.single_row_n,parent, false);

        Button btn_edit = row.findViewById(R.id.btn_edit);
        Button btn_delete = row.findViewById(R.id.btn_delete);
        TextView ntr_name= row.findViewById(R.id.ntr_name);
        TextView ntr_location= row.findViewById(R.id.ntr_location);
        TextView ntr_phone= row.findViewById(R.id.ntr_phone);

        //get nutritionist by position
        Nutritionist nutritionist = nutritionists.get(position);

        ntr_name.setText(nutritionist.name);
        ntr_location.setText(nutritionist.location);
        ntr_phone.setText(nutritionist.mobileNumber);

        //button -> move "Customer_View_Nutritionist_Profile_View" page
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pass intent
                Intent intent = new Intent(context, Edit_Nutritionists.class);
                intent.putExtra("emailaddress",emailExtra);
                intent.putExtra("nutritionistemail",nutritionist.email);
                context.startActivity(intent);

                //Toast massage
                Toast.makeText(context, "Nutritionist Loading", Toast.LENGTH_SHORT).show();
            }
        });



        //Delete row
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelperNutritionist dbHelper = new DBHelperNutritionist(context);
                dbHelper.deleteNutritionists(nutritionist.email);

                Toast.makeText(context,"Deleting..",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, Admin_View_Nutritionists_List.class);
                intent.putExtra("emailaddress",emailExtra);
                context.startActivity(intent);
            }
        });

        return row;
    }
}
